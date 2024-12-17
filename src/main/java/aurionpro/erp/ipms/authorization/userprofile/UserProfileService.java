package aurionpro.erp.ipms.authorization.userprofile;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.userlogin.UserLogin;
import aurionpro.erp.ipms.jkdframework.userlogin.UserLoginRepository;
import aurionpro.erp.ipms.utility.MyPrincipal;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository upRepo;
    
    @Autowired
    private UserProfileViewRepository upViewRepo;
    
    @Autowired
    private UserLoginRepository loginRepo;

    public Iterable<UserProfile> getAllUserProfiles(){
        return upRepo.findAll();
    }

    public List<SelectionList> getUserProfileList(){
        return upRepo.SelectionUserProfileList();
    }
    
    public List<SelectionList> getUserNameList(){
        return upRepo.getUserNameList();
    }

    public Optional<UserProfile> getUserProfileById(Long profileId){
        return upRepo.findById(profileId);
    }

    public List<UserProfile> getMyTeamMembers(){
        long myUserName=MyPrincipal.getMyProfile().getUserProfileId();
        return upRepo.myTeamData(myUserName);
       // return upRepo.findByManagerUserName(myUserName);
    }

    public UserProfile getUserProfileByName(String userName){
        List<UserProfile> profileList= upRepo.findByUserName(userName);

        if(profileList.size()==0){
            return null;
        }
        else{
            return profileList.get(0);
        }
    }

    public List<UserProfileView> getUserProfileByFilter(UserProfileSearchRequest uProfile){
    	
    	UserProfileView uPro = new UserProfileView();
    	uPro.setFullName(uProfile.getFullName());
    	uPro.setEmployeeCode(uProfile.getEmployeeCode());
        
         ExampleMatcher em=ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnoreCase()
                            .withStringMatcher(StringMatcher.CONTAINING);

        Example<UserProfileView> profileEx=Example.of(uPro,em);

        List<UserProfileView> userList= upViewRepo.findAll(profileEx);
        
        if (uProfile.getRoles() != null)
        {
        	List<Long> userid = upRepo.findUserByRolename(uProfile.getRoles());
        	List<UserProfileView> filteredUser = userList.stream()  
   		  	     .filter(f->userid.contains(f.getEntityId())).collect(Collectors.toList());
        	userList = filteredUser;
        }
        if (uProfile.getOrganizations() != null)
        {
        	List<Long> userid = upRepo.findUserByOrgId(uProfile.getOrganizations());
        	List<UserProfileView> filteredUser = userList.stream()  
   		  	     .filter(f->userid.contains(f.getEntityId())).collect(Collectors.toList());
        	userList = filteredUser;
        }
        return userList;
    }

    public UserProfile createUserProfile(UserProfile userProfile){

        List<UserProfile> uProfiles= upRepo.findByUserName(userProfile.getUserName());

        if(uProfiles.size()>0)
        {
            throw new EntityExistsException("The User Profile already exists");
        }
        else{
            validate();
            return upRepo.save(userProfile);
        }
    }

    public UserProfile UpdateUserProfile(UserProfile userProfile){

        Optional<UserProfile> uProfile= upRepo.findById(userProfile.getEntityId());

        if(!uProfile.isPresent())
        {
            throw new EntityNotFoundException("The User Profile does not exists");
        }

        if(!uProfile.get().getUserName().equals(userProfile.getUserName()))
        {
            throw new EntityNotFoundException("The User Profile does not match");
        }
        else{
            userProfile.setRoles(uProfile.get().getRoles());
            userProfile.setOrganizations(uProfile.get().getOrganizations());
            userProfile.setMobileAppKey(uProfile.get().getMobileAppKey());
            validate();
            return upRepo.save(userProfile);
        }
    }

    public UserProfile UpdateUserProfileAdmin(UserProfile userProfile){

        Optional<UserProfile> uProfile= upRepo.findById(userProfile.getEntityId());

        if(!uProfile.isPresent())
        {
            throw new EntityNotFoundException("The User Profile does not exists");
        }

        if(!uProfile.get().getUserName().equals(userProfile.getUserName()))
        {
            throw new EntityNotFoundException("The User Profile does not match");
        }
        else{
            //userProfile.setRoles(uProfile.get().getRoles());
            //userProfile.setOrganizations(uProfile.get().getOrganizations());
            userProfile.setMobileAppKey(uProfile.get().getMobileAppKey());
            validate();
            return upRepo.save(userProfile);
        }
    }

    private void validate() {
    }
    
    
	public BigInteger totalRegistrationCount() {
			return upRepo.totalRegistrationCount();
		}

		public BigInteger teamRegistrationCount(long myUserId) {
			return upRepo.teamRegistrationCount(myUserId);
		}

		public List<Object[]> buWiseGraph() {
			return upRepo.buWiseGraph();
		}

		public List<Object[]> officeLocationWiseGraph() {
			return upRepo.officeLocationWiseGraph();
		}

		public BigInteger BuWiseRegistrationCount(String department) {
			return upRepo.BuWiseRegistrationCount(department);
		}

		public UserProfile deleteProfileById(Long profileId) {

	        Optional<UserProfile> userTemp=upRepo.findById(profileId);

	        if(!userTemp.isPresent())
	        {
	            throw new EntityNotFoundException("User does not exist");
	        }

	        if(userTemp.get().getIsDeleted())
	        {
	            throw new EntityNotFoundException("User already deleted");
	        }
	        else{
	            userTemp.get().setIsDeleted(true);
	            return upRepo.save(userTemp.get());
	        }
		}

		public SelectionList getEmailMobileNoByUserProfile(Long id) {
			return upRepo.getEmailMobileNoByUserProfile(id);
		}

		public UserProfile saveMobileAppKey(UserProfile uProfile) {
			long proFileId =MyPrincipal.getMyProfile().getUserProfileId();
			Optional<UserProfile> userTemp = upRepo.findById(proFileId);

	        if(!userTemp.isPresent())
	        {
	            throw new EntityNotFoundException("User does not exist");
	        }
            userTemp.get().setMobileAppKey(uProfile.getMobileAppKey());
            return upRepo.save(userTemp.get());
		}
		
		public List<SelectionList> getUserDistrict(){
			 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
			    return upRepo.getUserDistrict(profileId);
	    }
		
		

		public UserLogin lockUnlockProfile(Long profileId, String status) {

	        Optional<UserProfile> userTemp=upRepo.findById(profileId);

	        if(!userTemp.isPresent())
	        {
	            throw new EntityNotFoundException("User does not exist");
	        }

			 UserLogin userLogin=loginRepo.findById(userTemp.get().getUserName()).get();

		        if(userLogin == null)
		        {
		            throw new EntityNotFoundException("User Login details does not exist");
		        }
		        
		        if (status.equalsIgnoreCase("LOCK"))
		        {
		        	userLogin.setFailedAttemptCount(3);
		        }
		        else if (status.equalsIgnoreCase("UNLOCK"))
		        {
		        	userLogin.setFailedAttemptCount(0);
		        }

		        return loginRepo.save(userLogin);
		}
}