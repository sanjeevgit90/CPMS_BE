package aurionpro.erp.ipms.dashboard;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import aurionpro.erp.ipms.utility.MyPrincipal;

@Service
@Transactional
public class DashboardService {

    @Autowired
   UserProfileService profileService;
    
	public DashboardCount totalRegistrationCount() {
		BigInteger count = profileService.totalRegistrationCount();
		
		DashboardCount dt = new DashboardCount();
		 dt.setName("Total Registration Count");
		 dt.setCount(count);
		 
		 return dt;
	}


	public DashboardCount buWiseCount() {
		long myUserId=MyPrincipal.getMyProfile().getUserProfileId();
		
		Optional<UserProfile> profile = profileService.getUserProfileById(myUserId);
		BigInteger count = profileService.BuWiseRegistrationCount(profile.get().getDepartment());
		
		DashboardCount dt = new DashboardCount();
		 dt.setName(profile.get().getDepartment() +" Registration Count");
		 dt.setCount(count);
		 
		 return dt;
	}


	public DashboardCount teamWiseCount() {
		long myUserId=MyPrincipal.getMyProfile().getUserProfileId();
		
		BigInteger count = profileService.teamRegistrationCount(myUserId);
		
		DashboardCount dt = new DashboardCount();
		 dt.setName("Team Registration Count");
		 dt.setCount(count);
		 
		 return dt;
	}


	public List<DashboardCount> buWiseGraph() {
		List<Object[]> data = profileService.buWiseGraph();
		
		List<DashboardCount> countList = new ArrayList<>();
		
		if (data.size()!= 0)
		{
			for(Object[] obj:data)
			{
				DashboardCount ap = new DashboardCount();
				
				ap.setCount((BigInteger) obj[1]);
				ap.setName((String) obj[0]);
				ap.setCode((String) obj[2]);
				countList.add(ap);
			}
		}
		return countList;
	}


	public List<DashboardCount> officeLocationWiseGraph() {
		List<Object[]> data = profileService.officeLocationWiseGraph();
		
		List<DashboardCount> countList = new ArrayList<>();
		
		if (data.size()!= 0)
		{
			for(Object[] obj:data)
			{
				DashboardCount ap = new DashboardCount();
				
				ap.setCount((BigInteger) obj[1]);
				ap.setName((String) obj[0]);
				ap.setCode((String) obj[2]);
				countList.add(ap);
			}
		}
		return countList;
	}
}