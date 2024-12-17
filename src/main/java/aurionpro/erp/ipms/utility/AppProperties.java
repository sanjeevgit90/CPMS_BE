package aurionpro.erp.ipms.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

   
	@Value("${auth.forgotpasswordlink}")
    public String getForgotPasswordLink;
    
    @Value("${mail.username}")
    public String getMailUserName;
    
    @Value("${mail.password}")
    public String getMailPassword;
    
    @Value("${mail.authflag}")
    public String getAuthFlag;
    
    @Value("${mail.host}")
    public String getHost;
    
    @Value("${mail.socketport}")
    public String getSocketport;
    
    @Value("${mail.ssl}")
    public String getIsSSL;
    
    @Value("${sms.channel}")
    public String getSmsChannel;


    //File Upload
    @Value("${fileupload.basephysicalpath}")
    public String getBasePhysicalPath;

    @Value("${fileupload.baselogicalpath}")
    public String getBaseLogicalPath;
    
    @Value("${hr.mail}")
    public String getHrMail;
    
    @Value("${hr.hrmailcontent}")
    public String getHrMailContent;
    
    @Value("${hr.hrmailsubject}")
    public String getHrMailSubject;

    @Value("${projectpin}")
    public String getGeneratedPin;
    
    @Value("${EmailSubject}")
    public String getEmailSubject;
    
    @Value("${EmailBody}")
    public String getEmailBody;
    
    @Value("${EmailBodyForMobile}")
    public String getEmailBodyForMobile;
    
    @Value("${TicketEmailSubject}")
    public String getEmailSubjectTicket;
    
    @Value("${TicketEmailBody}")
    public String getEmailBodyTicket;
    
    @Value("${TicketEmailBodyForMoble}")
    public String getEmailBodyTicketForMobile;
    
    @Value("${url}")
    public String getUrl;
    
    @Value("${openbravo.username}")
    public String getOpenBravoUsername;
    
    @Value("${openbravo.password}")
    public String getOpenBravoPassword;
    
    @Value("${openbravo.savepourl}")
    public String getOpenBravoSavePoUrl;
    
    @Value("${openbravo.saveproducturl}")
    public String getOpenBravoSaveProductUrl;

    @Value("${PoVendorEmailSubject}")
    public String getPoVendorEmailSubject;
    
    @Value("${PoVendorEmailBody}")
    public String getPoVendorEmailBody;
    
    @Value("${PoVendorEmailCcList}")
    public String getPoVendorEmailCcList;
    
    @Value("${PoVendorEmailBccList}")
    public String getPoVendorEmailBccList;
    
    @Value("${RejectionEmailSubject}")
    public String getRejectionEmailSubject;
    
    @Value("${RejectionEmailBody}")
    public String getRejectionEmailBody;
    
    @Value("${RejectionEmailBodyForMob}")
    public String getRejectionEmailBodyForMob;    

    @Value("${ApprovedEmailSubject}")
    public String getApprovedEmailSubject;
    
    @Value("${ApprovedEmailBody}")
    public String getApprovedEmailBody;
    
    @Value("${ApprovedEmailBodyForMob}")
    public String getApprovedEmailBodyForMob;
    
    @Value("${login.unlockMsg}")
    public String getUnlockMsg;
    
    @Value("${login.unlockUrl}")
    public String getUnlockUrl;

	@Value("${workflowlevel}")
	public String getWorkflowlevel;

	@Value("${emailid}")
	public String getEmailid;
    
}