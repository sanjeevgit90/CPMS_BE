package aurionpro.erp.ipms.ordermgmt.grn;

public interface GRNValidationDto {

	Long getProductId();
	int getSumOfAllGrn();
	Long getProductName();
	int getTotalQuantity();
}