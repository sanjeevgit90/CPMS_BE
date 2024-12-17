package aurionpro.erp.ipms.jkdframework.common;

import java.math.BigInteger;

public interface DashboardCountList {

	//String getModule();
    //BigInteger getCount();
	
	BigInteger getApprovedpo();
	BigInteger getPendingpo();
	BigInteger getApprovedrc();
	BigInteger getPendingrc();
	BigInteger getApprovedprs();
	BigInteger getPendingprs();
	BigInteger getAssets();
	BigInteger getTickets();
}