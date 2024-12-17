package aurionpro.erp.ipms.healthapp.selfassessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.utility.AppProperties;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.email.SendingMailService;

@Service
public class AssessmentService {

    @Autowired
    private QuestionnaireRepository quesRepo;

    @Autowired
    private SelfAssessmentRepository assessmentRepo;
    
    @Autowired
    SendingMailService emailService;
    
    @Autowired
    private AppProperties appProperties;
    
    @Autowired
    UserProfileService profileService;

    private void validate() {
    }

    public List<Answer> fetchSelfAssessmentQuestions() {
        List<Questionnaires> ques= quesRepo.findByQuestionbankname("SelfAssessment");

        if (ques.size()==0)
            {throw new RuntimeException("Self Assessment question not found");}

        ObjectMapper objMap = new ObjectMapper();
        List<Answer> ans;
        try {
            ans = objMap.readValue(ques.get(0).getBaseQuestions(),new TypeReference<List<Answer>>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

        return ans;
    }

    public SelfAssessment submitSelfAssessment(List<Answer> selfAnswer) {
        validate();

        ObjectMapper objMap = new ObjectMapper();
        String answersubmitted;
        try {
            answersubmitted=objMap.writeValueAsString(selfAnswer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

        //calculate health status
        String healthStatus=getHealthStatus(selfAnswer);
            
        Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
        SelfAssessment selfAssess=new SelfAssessment();
        selfAssess.setUserid(profileId);
        selfAssess.setAssessmentdate(System.currentTimeMillis());
        selfAssess.setHealthstatus(healthStatus);
        selfAssess.setAnswersubmitted(answersubmitted);
        
        SelfAssessment assessment = assessmentRepo.save(selfAssess);
        
        if (assessment != null)
        {
        	if ((healthStatus != null) && (healthStatus.equalsIgnoreCase("RED")))
        	{
        		String hr = appProperties.getHrMail;
        		
        		Optional<UserProfile> profile = profileService.getUserProfileById(profileId);
        		
        		String managerId = null;
        		if (profile.get().getManager()!= null)
        			managerId = profile.get().getManager().getEmailId();
        		        		
        		String msgSubject = appProperties.getHrMailSubject;
        		
        		String msgBody= appProperties.getHrMailContent;
        		        		 
        		msgBody = msgBody.replace("USER", profile.get().getFirstName()+ " "+ profile.get().getLastName());
        		
        		if (profile.get().getEmployeeCode()!= null)
        			msgBody = msgBody.replace("EMPCODE", profile.get().getEmployeeCode());
        		else
        			msgBody = msgBody.replace("EMPCODE", "");
        		
        		if (profile.get().getDepartment()!= null)
        			msgBody = msgBody.replace("DEPARTMENT", profile.get().getDepartment());
        		else
        			msgBody = msgBody.replace("DEPARTMENT", "");
        		
        		if (profile.get().getManager()!= null)
        			msgBody = msgBody.replace("MANAGER", profile.get().getManager().getFirstName()+" "+ profile.get().getManager().getLastName());
        		else
        			msgBody = msgBody.replace("MANAGER", "");
        		
     			emailService.sendEmail(hr, managerId,null,msgSubject, msgBody, "");
        	}
        }
        
        return assessment;
    }

    private String getHealthStatus(List<Answer> ans){

        int weightageTotal=0;
        weightageTotal=ans.stream().filter(a->a.getType().equalsIgnoreCase("selection")).mapToInt(q->getweightage(q)).sum();

        String healthStatus;

        if(weightageTotal<50)
            {healthStatus="GREEN";}
        else if(weightageTotal<75)
            {healthStatus="AMBER";}
        else
            {healthStatus="RED";}

        return healthStatus;
    }
    
    private int getweightage(Answer ans){

        List<Integer> weight=new ArrayList<Integer>();
        int answer=Integer.parseInt(ans.getAnswer());
        ans.getOptions().forEach(p->{ 
            if((p.getId()==answer) && p.isSensitive()){
                weight.add(ans.getWeightage());
            }
        });

        if(weight.size()==0)
            return 0;
        else
            return weight.get(0);
    }
}