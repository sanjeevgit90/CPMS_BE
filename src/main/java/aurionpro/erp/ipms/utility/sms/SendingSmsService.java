package aurionpro.erp.ipms.utility.sms;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.utility.AppProperties;
import aurionpro.erp.ipms.utility.HttpClientClass;

@Service
public class SendingSmsService extends ConnectorsService {
	
	@Autowired
    private AppProperties appProperties;
	
	@Autowired
	private SmsRepository smsRepo;
		
	public Map<Object, Object> sendSms(String mobileno, String msgBody) {
		LinkedHashMap<Object, Object> responselist = null;
		String url = "http://login.smsgatewayhub.com/api/mt/SendSMS?APIKey=gdpCtonbs0CTM5GCa0JKDA&senderid=testin&channel="
					+ appProperties.getSmsChannel + "&DCS=0&flashsms=0&number="
					+ mobileno + "&text=" + msgBody;
			try {
				
				responselist = getEndpoint(url, null, null, null);
				
				SMS sms = new SMS();
				
				sms.setMobileno(mobileno);
				sms.setSmsbody(msgBody);
				sms.setSmsstatus("SEND");
				sms.setSenddate((new Date()).getTime());
				
				smsRepo.save(sms);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return responselist;
	}
	
	public Map<Object, Object> sendSms(List<String> listOfSms, String msgBody) {
		LinkedHashMap<Object, Object> responselist = null;
		//String message = multilingualService.toUnicode(msgBody);
		for (String address : listOfSms) {
			String url = "http://login.smsgatewayhub.com/api/mt/SendSMS?APIKey=gdpCtonbs0CTM5GCa0JKDA&senderid=testin&channel="
					+ appProperties.getSmsChannel + "&DCS=0&flashsms=0&number="
					+ address + "&text=" + msgBody;
			try {
				
				responselist = getEndpoint(url, null, null, null);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responselist;
	}
	
	 public SMS queueSms(SMS sms) {
		 	sms.setSmsstatus("QUEUED");
			return smsRepo.save(sms);
		}
	 public void sendMobileAppNotification(String notificationReq) {
			String FcmUrl = "https://fcm.googleapis.com/fcm/send";
			String authToken = "key= ";
			
			try{
				HttpClientClass.sendNotification(FcmUrl, authToken, notificationReq, "POST");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
}