package aurionpro.erp.ipms.ordermgmt.grn;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.ordermgmt.grnproduct.ProductList;

public interface GRNMasterRepository extends JpaRepository<GRNMaster, Long> {

	public List<GRNMaster> findByGrnNumber(String grnNo);

	@Query(value = "select * from (select product_id AS productId, SUM(accepted_quantity) AS sumOfAllGrn from ordermgmt.grn_product_details "
	+ "where product_id in (select product_id from ordermgmt.grn_product_details where grn_id in (select entityid from ordermgmt.grn_master where po_no = ?1)) and grn_id in (select entityid from ordermgmt.grn_master where po_no = ?1) "
	+ "group by product_id) tbl1 "
	+ "join (select product_name AS productName, quantity AS totalQuantity from ordermgmt.product_details "
	+ "where purchase_order_no = ?1) tbl2 on tbl1.productId = tbl2.productName", nativeQuery = true)
	public List<GRNValidationDto> checkingSumOfQuantityValidation(Long poNo);

	@Query("select g.entityId as selectionid, g.grnNumber as selectionvalue from GRNMaster g order by g.grnNumber")
	public List<SelectionList> getSelectionGrnList();
	
	@Query("select g.entityId as selectionid, g.grnNumber as selectionvalue from GRNMaster g where g.poNo = ?1 order by g.grnNumber")
	public List<SelectionList> getSelectionGrnListByPo(long poId);
	
	@Query("select p.productName as productId, p.product as productName, p.quantity as quantity from ProductDetailsView p where p.purchaseOrder = ?1 order by p.product")
	public List<ProductList> getProductListByPo(long poId);
}
