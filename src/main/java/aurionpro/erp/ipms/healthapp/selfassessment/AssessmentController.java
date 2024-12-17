package aurionpro.erp.ipms.healthapp.selfassessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ipms/selfassessment")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/fetchQuestionnaire")
    public List<Answer> fetchQuestionnaire(){
        return assessmentService.fetchSelfAssessmentQuestions();
    }

    @PostMapping()
    public SelfAssessment submitSelfAssessment(@RequestBody List<Answer> selfAnswer){
            return assessmentService.submitSelfAssessment(selfAnswer);
    }
    
}