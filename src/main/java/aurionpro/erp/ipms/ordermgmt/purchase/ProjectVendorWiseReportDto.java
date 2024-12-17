package aurionpro.erp.ipms.ordermgmt.purchase;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ProjectVendorWiseReportDto {

	String getName();
	//String getSubName();
    BigInteger getCount();
    //BigInteger getSearchCount();
    BigDecimal getAllTotalOfPo();
}