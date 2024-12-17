package aurionpro.erp.ipms.utility;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetMaster;
import aurionpro.erp.ipms.assetmgmt.assetmaster.AssetMasterService;
import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMaster;
import aurionpro.erp.ipms.jkdframework.workflow.TaskMasterRepository;
import aurionpro.erp.ipms.jkdframework.workflow.TaskResponse;
import aurionpro.erp.ipms.projectmgmt.projectworkflowlevelmapping.ProjectWorkflowLevelMappingService;
import aurionpro.erp.ipms.ticketmgmt.ticketmaster.TicketMaster;
import aurionpro.erp.ipms.utility.email.SendingMailService;
import aurionpro.erp.ipms.utility.sms.SendingSmsService;

@Service
public  class NotificationMailFormat {
	  
	@Autowired
    AppProperties appProperties;
    
    @Autowired
    private ProjectWorkflowLevelMappingService mappingService;
   
    @Autowired
    SendingMailService emailService;
    
    @Autowired
    SendingSmsService smsService;
    
    @Autowired
    private UserProfileService upService;
    
    @Autowired
    AssetMasterService assetService;
    
    @Autowired
	TaskMasterRepository taskRepo;
	
	 public void sendNotification(TaskResponse task, Long projectId, String TaskFor, String taskNo, String flag) {
		 
		 System.out.println("Notification service Enter");
		 if(task != null) {
			if (!(task.getStatus().equalsIgnoreCase("APPROVED")))
			{
				if (task.getAction().equalsIgnoreCase("APPROVED"))
				{
					sendNotifactionToNextLevel(task, projectId, TaskFor, taskNo, flag);
				}
				else if (task.getAction().equalsIgnoreCase("REJECTED"))
				{
					sendNotifactionToCreator(task, projectId, TaskFor, taskNo, flag);
				}
			}
		 }
	 }
	 
    public void sendNotificationToAllLevel(List<String> emailList, String taskNo) {
    	String msgBody;
   		String msgSubject;
   		
   		msgSubject = appProperties.getApprovedEmailSubject;
   		msgSubject = msgSubject.replace("TASK", taskNo);
   		    		 	 
   		msgBody = appProperties.getApprovedEmailBody;
   		msgBody = msgBody.replace("TASK", taskNo);
   		
   		for(String email : emailList) {
   			emailService.queueEmail(email, null,null,msgSubject, msgBody, "");
   		}
   	}
   
	private void sendNotifactionToCreator(TaskResponse task, Long projectId, String TaskFor, String taskNo,
			String flag) {
		if (task.getAssignToRole() != null) {
	    	if (!(task.getAssignToRole().equalsIgnoreCase("NONE"))) {
	    	 List<SelectionList> emailList = null;
	    	 		emailList = mappingService.getListOfEmailId(task.getAssignToRole(), projectId); 
	    	
			 if ((emailList != null) && ( emailList.size() != 0))
			 {
				for (SelectionList email : emailList) 
				{
					String msgBody;
					String msgSubject;
					String msgBodyFoMob ;
					msgSubject = appProperties.getRejectionEmailSubject;
					msgSubject = msgSubject.replace("LEVEL", task.getAssignToRole());
		    		msgSubject = msgSubject.replace("TASK", taskNo);
		    		    		 	 
		    		msgBody = appProperties.getRejectionEmailBody;
		    		msgBodyFoMob = appProperties.getRejectionEmailBodyForMob;
		    		
		    		msgBody = msgBody.replace("TASK", taskNo);
		    		
		    		msgBodyFoMob = msgBodyFoMob.replace("TASK", taskNo);
		    				    		
		    	UserProfile user = upService.getUserProfileByName(email.getSelectionid());
				String userKey = (user.getMobileAppKey() != null) ? user.getMobileAppKey() : "";
				 String keyUser = userKey.substring(0,3);
				 String key = userKey.substring(4);
				 String notificationReq = null;
				 if (keyUser.equalsIgnoreCase("ios:"))
				 {
					 notificationReq = "{\"to\":\""+ key +"\",\"notification\":{\"title\":\""+ msgSubject + "\",\"body\":\"" + msgBodyFoMob + "\"},\"data\":{\"title\":\""+ msgSubject + "\",\"body\":\"" + msgBodyFoMob + "\",\"purchaseOrderNo\":\"" + taskNo + "\",\"notificationType\":\""+flag+"\"}}"; 
				 }
				 else
				 {
					 notificationReq = "{\"to\":\""+ key +"\",\"data\":{\"title\":\""+ msgSubject + "\",\"body\":\"" + msgBodyFoMob + "\",\"purchaseOrderNo\":\"" + taskNo + "\",\"notificationType\":\""+flag+"\"}}"; 
				 }
				smsService.sendMobileAppNotification(notificationReq);
				
				emailService.queueEmail(email.getSelectionid(), null,null,msgSubject, msgBody, "");
			}
		}
	   }
	}
	}
	
	private void sendNotifactionToNextLevel(TaskResponse task, Long projectId, String TaskFor, String taskNo, String flag) {
		if (task.getAssignToRole() != null) {
	    	if (!(task.getAssignToRole().equalsIgnoreCase("NONE"))) {
	    	 List<SelectionList> emailList = null;
	    	 		emailList = mappingService.getListOfEmailId(task.getAssignToRole(), projectId); 
	    	
			 if ((emailList != null) && ( emailList.size() != 0))
			 {
				for (SelectionList email : emailList) 
				{
					String msgBody;
					String msgSubject;
					String msgBodyForMob;
					
					msgSubject = appProperties.getEmailSubject;
					msgSubject = msgSubject.replace("LEVEL", task.getAssignToRole());
		    		msgSubject = msgSubject.replace("ApprovalTask", TaskFor);
		    		msgSubject = msgSubject.replace("TASK", taskNo);
		    		    		 	 
		    		msgBody = appProperties.getEmailBody;
		    		msgBodyForMob = appProperties.getEmailBodyForMobile;
		    		
		    		msgBody = msgBody.replace("LEVEL", task.getAssignToRole());
		    		msgBody = msgBody.replace("ApprovalTask", TaskFor);
		    		msgBody = msgBody.replace("TASK", taskNo);
		    		String approveUrl = appProperties.getUrl+"/"+task.getTaskid()+"/"+ "APPROVED"+"/"+flag;
		    		String rejectUrl = appProperties.getUrl+"/"+task.getTaskid()+"/"+ "REJECTED"+"/"+flag;
		    		
		    		msgBodyForMob = msgBodyForMob.replace("ApprovalTask", TaskFor);
		    		msgBodyForMob = msgBodyForMob.replace("TASK", taskNo);
		    		
		    		if (flag != null)
		    		{

			    		msgBody = msgBody.replace("APPROVEURL", approveUrl);
			    		msgBody = msgBody.replace("REJECTURL", rejectUrl);
		    		}
		    		
		    	UserProfile user = upService.getUserProfileByName(email.getSelectionid());
				String userKey = (user.getMobileAppKey() != null) ? user.getMobileAppKey() : "";
				if (userKey.isEmpty())
				{
					 String keyUser = userKey.substring(0,3);
					 String key = userKey.substring(4);
					 String notificationReq = null;
					 System.out.println("KeyUser Notification::"+ keyUser);

					 System.out.println("Key Notification::"+ key);
					 if (keyUser.equalsIgnoreCase("ios:"))
					 {
						 System.out.println("IOS Notification");
						 notificationReq ="{\"to\":\""+ key +"\",\"notification\":{\"title\":\""+ msgSubject + "\",\"body\":\"" + msgBodyForMob + "\"},\"data\":{\"title\":\""+ msgSubject + "\",\"body\":\"" + msgBodyForMob + "\",\"purchaseOrderNo\":\"" + taskNo + "\",\"notificationType\":\""+flag+"\"}}";
					 }
					 else
					 {
						 System.out.println("MOB Notification");
						 notificationReq ="{\"to\":\""+ key +"\",\"data\":{\"title\":\""+ msgSubject + "\",\"body\":\"" + msgBodyForMob + "\",\"purchaseOrderNo\":\"" + taskNo + "\",\"notificationType\":\""+flag+"\"}}";
					 }
					smsService.sendMobileAppNotification(notificationReq);

				}
								
				emailService.queueEmail(email.getSelectionid(), null,null,msgSubject, msgBody, "");
			}
		}
	   }
	}
}	


	public void sendTicketNotification(TaskResponse task, TicketMaster ticket) {
		AssetMaster asset = assetService.findAsset(ticket.getAssetid()).get();
			String msgSubject = appProperties.getEmailSubjectTicket;
			String msgBody = appProperties.getEmailBodyTicket;
			
			msgSubject = msgSubject.replace("TICKETNO", ticket.getTicketNo());
			
			msgBody = msgBody.replace("TICKETNO", ticket.getTicketNo());
			msgBody = msgBody.replace("TICKETTITLE", ticket.getTicketTitle());
			msgBody = msgBody.replace("TICKETDESC", ticket.getDescription());
			
			msgBody = (ticket.getAssetid()!= null) ? msgBody.replace("ASSETNAME", asset.getAssetname()) : msgBody.replace("ASSETNAME", "");
			msgBody = (ticket.getPriority()!= null) ? msgBody.replace("PRIORITY", ticket.getPriority()) : msgBody.replace("PRIORITY", "");
			msgBody = (ticket.getClassifications()!= null) ? msgBody.replace("CLASSIFICATION", ticket.getClassifications()) : msgBody.replace("CLASSIFICATION", "");
			
			msgBody = msgBody.replace("DATETIME", new SimpleDateFormat("dd/MM/yyyy").format(ticket.getCreatedDate()));
			msgBody = msgBody.replace("STATUS",ticket.getTicketStatus());
			
			String assignedToMsgBody = msgBody;
			String ownerMsgBody = msgBody;
			String createdByMsgBody = msgBody;
			String previouslyAssignedMsgBody= msgBody;
			
			String assignName = null;
			if (task.getAssignToUser() != null)
			{
				String msg = msgSubject;
				msg = msg.replace("BODY", "assigned to you.");
				
				assignedToMsgBody = assignedToMsgBody.replace("BODY", "assigned to");
				
				UserProfile user = upService.getUserProfileById(ticket.getAssignTo()).get();
				assignName = user.getFirstName()+ " " + user.getLastName();
				 emailService.queueEmail(user.getEmailId(), null,null,msgSubject, assignedToMsgBody, "");
				 
				 String userKey = (user.getMobileAppKey() != null) ? user.getMobileAppKey() : "";
				 String mobMsgBody = appProperties.getEmailBodyTicketForMobile;
				 String keyUser = userKey.substring(0,3);
				 String key = userKey.substring(4);
				 String notificationReq = null;
				 if (keyUser.equalsIgnoreCase("ios:"))
				 {
					 notificationReq = "{\"to\":\""+ key +"\",\"notification\":{\"title\":\""+ msg + "\",\"body\":\"" + mobMsgBody + "\"},\"data\":{\"title\":\""+ msg + "\",\"body\":\"" + mobMsgBody + "\",\"ticketNo\":\"" + ticket.getTicketNo()+ "\",\"notificationType\":\"ticket\"}}";
				 }
				 else
				 {
					 notificationReq = "{\"to\":\""+ key +"\",\"data\":{\"title\":\""+ msg + "\",\"body\":\"" + mobMsgBody + "\",\"ticketNo\":\"" + ticket.getTicketNo()+ "\",\"notificationType\":\"ticket\"}}"; 
				 }
				 smsService.sendMobileAppNotification(notificationReq);
			}
			if (ticket.getTicketOwner() != null)
			{
				String msg = msgSubject;
				msg = msg.replace("BODY", "has been created.");
				ownerMsgBody = ownerMsgBody.replace("BODY", "owned by you.");
				
				UserProfile user = upService.getUserProfileById(ticket.getTicketOwner()).get();
				
				emailService.queueEmail(user.getEmailId(), null,null,msg, ownerMsgBody, "");
			}
			if (task.getTaskid() != null)
			{
				TaskMaster taskObj = taskRepo.findById(task.getTaskid()).get();
				
				if (taskObj.getAssignToUser() != null)
				{
					String msg = msgSubject;
					msg = msg.replace("BODY", ticket.getTicketStatus().toLowerCase() + " by you.");
					ownerMsgBody = ownerMsgBody.replace("BODY", "owned by you.");
					previouslyAssignedMsgBody = previouslyAssignedMsgBody.replace("BODY", ticket.getTicketStatus().toLowerCase()+" by");
					if (assignName != null)
						previouslyAssignedMsgBody = previouslyAssignedMsgBody.concat("<br/>Has been "+ ticket.getTicketStatus().toLowerCase() +" to : "+ assignName);
			
					emailService.queueEmail(taskObj.getAssignToUser(), null,null,msg, previouslyAssignedMsgBody, "");
				}	
			}
			
			
			msgSubject = msgSubject.replace("BODY", "has been created.");
			createdByMsgBody = createdByMsgBody.replace("BODY", "created by");
			
			if (assignName != null)
			{	
				StringBuilder msg = new StringBuilder();
				msg.append(createdByMsgBody).append("<br/>Has been assigned to :").append(assignName);
				createdByMsgBody = msg.toString();	
			}
			emailService.queueEmail(ticket.getCreatedBy(), null,null,msgSubject, createdByMsgBody, "");
			
	}
	 

	 
	
}
