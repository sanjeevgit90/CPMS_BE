package aurionpro.erp.ipms.jkdframework.userlogin;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import aurionpro.erp.ipms.jkdframework.common.DashboardCountList;

@Transactional
public interface UserLoginRepository extends CrudRepository<UserLogin,String>{

    @Modifying
    @Query("update UserLogin ul set ul.failedAttemptCount=ul.failedAttemptCount+1 where ul.username=:username")
    public void IncreamentFailedAttemptCounter(@Param("username") String username);

    @Modifying
    @Query("update UserLogin ul set ul.failedAttemptCount=0 where ul.username=:username")
    public void ResetFailedAttemptCounter(@Param("username") String username);

    @Modifying
    @Query("update UserLogin ul set ul.forgotPasswordToken=:token where ul.username=:username")
    public void SetForgotPasswordToken(@Param("username") String username, @Param("token") String token);
    
	//@Query(value = "select 'approved po' as module, count(*) from ordermgmt.purchase_order_master where approval_status = 'APPROVED' union all select 'pending po' as module, count(*) from ordermgmt.purchase_order_master where approval_status <> 'APPROVED' union all select 'approved rc' as module, count(*) from ordermgmt.rate_contract_master where approval_status = 'APPROVED' union all select 'pending rc' as module, count(*) from ordermgmt.rate_contract_master where approval_status <> 'APPROVED' union all select 'approved prs' as module, count(*) from ordermgmt.prs_master where approval_status = 'APPROVED' union all select 'pending prs' as module, count(*) from ordermgmt.prs_master where approval_status <> 'APPROVED' union all select 'assets' as module, count(*) from assetmgmt.assetmaster union all select 'tickets' as module, count(*) from ticketmgmt.ticketmaster", nativeQuery = true)
  	@Query(value = "select (select count(*) from ordermgmt.purchase_order_master where approval_status = 'APPROVED') AS approvedpo, (select count(*) from ordermgmt.purchase_order_master where approval_status <> 'APPROVED') AS pendingpo, (select count(*) from ordermgmt.rate_contract_master where approval_status = 'APPROVED') AS approvedrc, (select count(*) from ordermgmt.rate_contract_master where approval_status <> 'APPROVED') AS pendingrc, (select count(*) from ordermgmt.prs_master where approval_status = 'APPROVED') AS approvedprs, (select count(*) from ordermgmt.prs_master where approval_status <> 'APPROVED') AS pendingprs, (select count(*) from assetmgmt.assetmaster) AS assets, (select count(*) from ticketmgmt.ticketmaster) AS tickets", nativeQuery = true)
  	public List<DashboardCountList> getDashboardCount();
}
