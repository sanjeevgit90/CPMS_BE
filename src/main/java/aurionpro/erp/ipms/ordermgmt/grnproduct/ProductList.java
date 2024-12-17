package aurionpro.erp.ipms.ordermgmt.grnproduct;

public interface ProductList {
	Long getProductId();
    String getProductName();
    Long getQuantity();
    Long getRecievedQuantity();
    Long getAcceptedQuantity();
    Long getRejectedQuantity();
}