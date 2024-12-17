package aurionpro.erp.ipms.authorization.userprofile;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long>{

    @Query("select u.entityId as selectionid, u.userName as selectionvalue from UserProfile u order by u.userName ")
    public List<SelectionList> SelectionUserProfileList();

    @Query("select u.entityId as selectionid, concat(u.firstName, ' ', u.lastName) as selectionvalue from UserProfile u order by u.userName")
    public List<SelectionList> getUserNameList();

    
    public List<UserProfile> findByManagerUserName(String userName);

    public List<UserProfile> findByUserName(String userName);
    	
	public UserProfile findByEntityId(Long entityId);
	
	 @Query(value="select district as selectionid, parent as selectionvalue from authentication.user_district where userid=?1 ", nativeQuery = true)
	 public List<SelectionList> getUserDistrict(Long id);

	
	  
    @Query(value ="select * from ("
			+ " select * from authentication.userprofile u where u.managerid in ( "
			+ " select u1.entityid from authentication.userprofile u1 where u1.managerid=?1) "
			+ " union all "
			+ " SELECT * FROM authentication.userprofile where managerid = ?1) as emplist", nativeQuery = true)
	public List<UserProfile> myTeamData(long manager);
    
    @Query(value ="SELECT count(u.entityid) FROM authentication.userprofile u where u.isdeleted = false", nativeQuery = true)
    public BigInteger totalRegistrationCount();

	@Query(value ="SELECT count(u.entityid) FROM authentication.userprofile u where u.department = ?1 and u.isdeleted = false", nativeQuery = true)
    public BigInteger BuWiseRegistrationCount(String department);

	@Query(value ="SELECT u.department, count(u.entityid), dep.departmentcode FROM authentication.userprofile u"
			+ " JOIN authentication.department dep ON u.department = dep.departmentname "
			+ " where u.isdeleted = false and dep.isdeleted = false group by u.department ,dep.departmentcode", nativeQuery = true)
    public List<Object[]> buWiseGraph();

	@Query(value ="SELECT u.officelocation , count(u.entityid), o.officecode FROM authentication.userprofile u "
			+ " JOIN authentication.officelocation o ON u.officelocation = o.officename "
			+ " where u.isdeleted = false and o.isdeleted = false group by u.officelocation ,o.officecode;", nativeQuery = true)
    public List<Object[]> officeLocationWiseGraph();

	@Query(value ="select sum(entitycount) employeecount from ("
			+ " select count(entityid) entitycount from authentication.userprofile u "
			+ " where u.managerid in ("
			+ " select u1.entityid from authentication.userprofile u1 where u1.managerid=?1)"
			+ "union all"
			+ " SELECT count(entityid) entitycount FROM authentication.userprofile where managerid = ?1) as emplist", nativeQuery = true)
	public BigInteger teamRegistrationCount(Long manager);

	@Query("select u.emailId as selectionid, u.mobileNumber as selectionvalue from UserProfile u where u.entityId=?1 and u.isDeleted=false") 
	public SelectionList getEmailMobileNoByUserProfile(Long id);

	@Query(value="select userprofile_entityid from authentication.user_roles where roles_rolename = ?1", nativeQuery = true) 
	public List<Long> findUserByRolename(String roles);

	@Query(value="select userid from authentication.user_organization where organizationid = ?1", nativeQuery = true) 
	public List<Long> findUserByOrgId(Long organizations);
}