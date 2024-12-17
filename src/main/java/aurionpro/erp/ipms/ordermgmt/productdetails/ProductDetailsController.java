package aurionpro.erp.ipms.ordermgmt.productdetails;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderMaster;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderService;
import aurionpro.erp.ipms.ordermgmt.purchaseordertask.PoTaskMaster;
import aurionpro.erp.ipms.ordermgmt.purchaseordertask.PoTaskMasterRepository;

@RestController
@RequestMapping(value = "/ipms/productdetails")
public class ProductDetailsController {

	@Autowired
	ProductDetailsService productDetailsService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	PoTaskMasterRepository poTaskMasterRepository;
	
	@PostMapping("/addproductdetail")
    public ProductDetails addProductDetail(@RequestBody ProductDetails productDetailsRequest){
		productDetailsRequest.setAmount(productDetailsRequest.getRate()*productDetailsRequest.getQuantity());
		productDetailsRequest.setTotalDiscount(productDetailsRequest.getDiscountAmount()+(productDetailsRequest.getDiscount()/100*productDetailsRequest.getAmount()));
		productDetailsRequest.setTotalAmount(productDetailsRequest.getAmount()-productDetailsRequest.getTotalDiscount()); 
		if(productDetailsRequest.getCurrency().equalsIgnoreCase("INR"))
		{
			if(productDetailsRequest.getIsHistoricData().equalsIgnoreCase("NO")) {
				if(productDetailsRequest.getApplyGstFlag().equalsIgnoreCase("YES"))
				{
					if(productDetailsRequest.isStateFlag())
					{
						productDetailsRequest.setCgstAmount((productDetailsRequest.getCgst()/100)*(productDetailsRequest.getTotalAmount()));
						productDetailsRequest.setSgstAmount((productDetailsRequest.getSgst()/100)*(productDetailsRequest.getTotalAmount()));
						productDetailsRequest.setIgstAmount(0.0f);
						productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+((productDetailsRequest.getCgst()+productDetailsRequest.getSgst())/100*productDetailsRequest.getTotalAmount()));
					}
					else
					{
						productDetailsRequest.setIgstAmount((productDetailsRequest.getIgst()/100)*(productDetailsRequest.getTotalAmount()));
						productDetailsRequest.setCgstAmount(0.0f);
						productDetailsRequest.setSgstAmount(0.0f);
						productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+((productDetailsRequest.getIgst())/100*productDetailsRequest.getTotalAmount()));	
					}
				}
				else
				{
					productDetailsRequest.setCgstAmount(0.0f);
					productDetailsRequest.setSgstAmount(0.0f);
					productDetailsRequest.setIgstAmount(0.0f);
					productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount());
				}
			}
			else if(productDetailsRequest.getIsHistoricData().equalsIgnoreCase("YES"))
			{
				double i = 0.0;
				productDetailsRequest.setCgstAmount(i);
				productDetailsRequest.setSgstAmount(i);
				productDetailsRequest.setIgstAmount(i);
				productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+productDetailsRequest.getServiceTax()+productDetailsRequest.getVat());
			}
		}
		else
		{
			productDetailsRequest.setIgstAmount((productDetailsRequest.getIgst()/100)*(productDetailsRequest.getTotalAmount()));
			productDetailsRequest.setCgstAmount(0.0f);
			productDetailsRequest.setSgstAmount(0.0f);
			productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+productDetailsRequest.getIgstAmount());
		}
		
/*		if(messages.getProperty(Constant.APPLY_BOQ_FLAG.getValue()).equalsIgnoreCase("TRUE")){
			int quantity = purchaseOrderService.getQuantityOfProductsByAccount(request.getAccountId(),request.getProductName());
			
			List<BOQProductChild> objList = purchaseOrderService.getProductsForAccount(request.getAccountId());
			int flag = 0;
			for(BOQProductChild o : objList){
				if(o.getProductId().equalsIgnoreCase(request.getProductName())){
					flag = 1;
					if(request.getRate()>o.getRate()){
						//BadRequestException(Constant.VALIDATION.getValue(), "Rate cannot be greater than the rate in BOQ.");
						BadRequestException(Constant.VALIDATION.getValue(), "Rate cannot exceed than that in BOQ, maximum rate for the selected product is "+o.getRate()+"Rs.");
					}
					if(quantity==o.getQuantity()){
						BadRequestException(Constant.VALIDATION.getValue(), "Quantity cannot exceed than that in BOQ, maximum quantity for the selected product is "+o.getQuantity()+" which has been utilised.");
					}else if((request.getQuantity()+quantity)>o.getQuantity()){
						//BadRequestException(Constant.VALIDATION.getValue(), "Quantity cannot be greater than the quantity in BOQ.");
						BadRequestException(Constant.VALIDATION.getValue(), "Quantity cannot exceed than that in BOQ, maximum quantity for the selected product is "+o.getQuantity()+" out of which "+quantity+" has been utilised.");
					}
				}
			}
			if(flag==0)
				BadRequestException(Constant.VALIDATION.getValue(), "The selected product does not exist in any approved BOQ.");
		}
		
		if(!StringUtils.isEmpty(request.getRcId())){
			float totalValueOfAllPOInRC = purchaseOrderService.getTotalOfPoInRc(request.getRcId());
			RateContractMaster rcEntity = rcService.getOrderById(request.getRcId());
			if((totalValueOfAllPOInRC+request.getFinalAmount())>rcEntity.getMaxLimit())
				BadRequestException(Constant.VALIDATION.getValue(), "The total value of PO's made from RC No. "+rcEntity.getRateContractNo()+" exceeds the max limit.");
		}
		
		//for pulling task by po head
		TaskMaster task = taskMasterService.getTaskByPOId(request.getPurchaseOrderNo(), "PENDING");
		if(task!=null){
			if(!task.getApprovalLevel().equalsIgnoreCase("PO TEAM")){
				TaskMaster tempObj = taskMasterService.updateTaskPulledByPOHead(task,getLoggedInUser().getUsername());
				request.setPoHeadEditFlag(true);
			}
		}//for pulling task by po head
*/
		ProductDetails saveObj = productDetailsService.addProductDetail(productDetailsRequest);
		
		if(saveObj != null) {
			purchaseOrderService.updateGrandTotal(saveObj.getPurchaseOrder());
			
			//for pulling task by po head
	        List<PoTaskMaster> tm = poTaskMasterRepository.getTaskByPoIdAndStatus(productDetailsRequest.getPurchaseOrder().getEntityId(), "PENDING");
	        if(!tm.isEmpty()) {
	        	if(!tm.get(0).getStageName().equalsIgnoreCase("PO TEAM")) {
	        		poTaskMasterRepository.updateTaskPulledByPoHead(tm.get(0).getEntityId());
	        		purchaseOrderService.updateApprovalStatus(productDetailsRequest.getPurchaseOrder().getEntityId(), "PENDING");
	        	}
	        }//for pulling task by po head
		}
        return saveObj;
    }
	
	@PutMapping("/{id}")
    public ProductDetails updateProductDetail(@PathVariable(value="id") long id, @RequestBody ProductDetails productDetailsRequest){
		productDetailsRequest.setAmount(productDetailsRequest.getRate()*productDetailsRequest.getQuantity());
		productDetailsRequest.setTotalDiscount(productDetailsRequest.getDiscountAmount()+(productDetailsRequest.getDiscount()/100*productDetailsRequest.getAmount()));
		productDetailsRequest.setTotalAmount(productDetailsRequest.getAmount()-productDetailsRequest.getTotalDiscount()); 
		if(productDetailsRequest.getCurrency().equalsIgnoreCase("INR"))
		{
			if(productDetailsRequest.getIsHistoricData().equalsIgnoreCase("NO")) {
				if(productDetailsRequest.getApplyGstFlag().equalsIgnoreCase("YES"))
				{
					if(productDetailsRequest.isStateFlag())
					{
						productDetailsRequest.setCgstAmount((productDetailsRequest.getCgst()/100)*(productDetailsRequest.getTotalAmount()));
						productDetailsRequest.setSgstAmount((productDetailsRequest.getSgst()/100)*(productDetailsRequest.getTotalAmount()));
						productDetailsRequest.setIgstAmount(0.0f);
						productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+((productDetailsRequest.getCgst()+productDetailsRequest.getSgst())/100*productDetailsRequest.getTotalAmount()));
					}
					else
					{
						productDetailsRequest.setIgstAmount((productDetailsRequest.getIgst()/100)*(productDetailsRequest.getTotalAmount()));
						productDetailsRequest.setCgstAmount(0.0f);
						productDetailsRequest.setSgstAmount(0.0f);
						productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+((productDetailsRequest.getIgst())/100*productDetailsRequest.getTotalAmount()));	
					}
				}
				else
				{
					productDetailsRequest.setCgstAmount(0.0f);
					productDetailsRequest.setSgstAmount(0.0f);
					productDetailsRequest.setIgstAmount(0.0f);
					productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount());
				}
			}
			else if(productDetailsRequest.getIsHistoricData().equalsIgnoreCase("YES"))
			{
				double i = 0.0;
				productDetailsRequest.setCgstAmount(i);
				productDetailsRequest.setSgstAmount(i);
				productDetailsRequest.setIgstAmount(i);
				productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+productDetailsRequest.getServiceTax()+productDetailsRequest.getVat());
			}
		}
		else
		{
			productDetailsRequest.setIgstAmount((productDetailsRequest.getIgst()/100)*(productDetailsRequest.getTotalAmount()));
			productDetailsRequest.setCgstAmount(0.0f);
			productDetailsRequest.setSgstAmount(0.0f);
			productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+productDetailsRequest.getIgstAmount());
		}
		
/*		if(messages.getProperty(Constant.APPLY_BOQ_FLAG.getValue()).equalsIgnoreCase("TRUE")){
			int quantity = purchaseOrderService.getQuantityOfProductsByAccount(request.getAccountId(),request.getProductName());
			
			List<BOQProductChild> objList = purchaseOrderService.getProductsForAccount(request.getAccountId());
			int flag = 0;
			for(BOQProductChild o : objList){
				if(o.getProductId().equalsIgnoreCase(request.getProductName())){
					flag = 1;
					if(request.getRate()>o.getRate()){
						//BadRequestException(Constant.VALIDATION.getValue(), "Rate cannot be greater than the rate in BOQ.");
						BadRequestException(Constant.VALIDATION.getValue(), "Rate cannot exceed than that in BOQ, maximum rate for the selected product is "+o.getRate()+"Rs.");
					}
					if(quantity==o.getQuantity()){
						BadRequestException(Constant.VALIDATION.getValue(), "Quantity cannot exceed than that in BOQ, maximum quantity for the selected product is "+o.getQuantity()+" which has been utilised.");
					}else if((request.getQuantity()+quantity)>o.getQuantity()){
						//BadRequestException(Constant.VALIDATION.getValue(), "Quantity cannot be greater than the quantity in BOQ.");
						BadRequestException(Constant.VALIDATION.getValue(), "Quantity cannot exceed than that in BOQ, maximum quantity for the selected product is "+o.getQuantity()+" out of which "+quantity+" has been utilised.");
					}
				}
			}
			if(flag==0)
				BadRequestException(Constant.VALIDATION.getValue(), "The selected product does not exist in any approved BOQ.");
		}
		
		if(!StringUtils.isEmpty(request.getRcId())){
			float totalValueOfAllPOInRC = purchaseOrderService.getTotalOfPoInRc(request.getRcId());
			RateContractMaster rcEntity = rcService.getOrderById(request.getRcId());
			if((totalValueOfAllPOInRC+request.getFinalAmount())>rcEntity.getMaxLimit())
				BadRequestException(Constant.VALIDATION.getValue(), "The total value of PO's made from RC No. "+rcEntity.getRateContractNo()+" exceeds the max limit.");
		}
		
		//for pulling task by po head
		TaskMaster task = taskMasterService.getTaskByPOId(request.getPurchaseOrderNo(), "PENDING");
		if(task!=null){
			if(!task.getApprovalLevel().equalsIgnoreCase("PO TEAM")){
				TaskMaster tempObj = taskMasterService.updateTaskPulledByPOHead(task,getLoggedInUser().getUsername());
				request.setPoHeadEditFlag(true);
			}
		}//for pulling task by po head
*/
        ProductDetails updateObj = productDetailsService.updateProductDetail(id, productDetailsRequest);
		if(updateObj != null) {
			purchaseOrderService.updateGrandTotal(updateObj.getPurchaseOrder());
			
			//for pulling task by po head
	        List<PoTaskMaster> tm = poTaskMasterRepository.getTaskByPoIdAndStatus(productDetailsRequest.getPurchaseOrder().getEntityId(), "PENDING");
	        if(!tm.isEmpty()) {
	        	if(!tm.get(0).getStageName().equalsIgnoreCase("PO TEAM")) {
	        		poTaskMasterRepository.updateTaskPulledByPoHead(tm.get(0).getEntityId());
	        		purchaseOrderService.updateApprovalStatus(productDetailsRequest.getPurchaseOrder().getEntityId(), "PENDING");
	        	}
	        }//for pulling task by po head
		}
        return updateObj;
    }
	
	@GetMapping("/getallproductdetails")
    public List<ProductDetails> getAllProductDetails(){
        return productDetailsService.getAllProductDetails();
    }
	
	@GetMapping("/getproductdetailbyid/{id}")
    public Optional<ProductDetails> getProductDetailById(@PathVariable(value="id") long id){
        return productDetailsService.getProductDetailById(id);
    }
	
	//@GetMapping("/deleteproductdetailbyid/{id}")
	@DeleteMapping("/{id}")
    public void deleteProductDetailById(@PathVariable(value="id") long id){
		Optional<ProductDetails> deleteObj = productDetailsService.getProductDetailById(id);
		if(deleteObj == null) {
			throw new RuntimeException("Product entity does not exists.");
		}
		PurchaseOrderMaster po = deleteObj.get().getPurchaseOrder();
		productDetailsService.deleteProductDetailById(id);
		purchaseOrderService.updateGrandTotal(po);
    }
	
	@PostMapping("/getproductdetailsbypo")
    public List<ProductDetails> getProductDetailsByPoId(@RequestBody PurchaseOrderMaster po){
        return productDetailsService.getProductDetailsByPoId(po);
    }
	
	@PostMapping("/searchproductdetailsbypo")
    public List<ProductDetailsView> searchProductDetailsByPoId(@RequestBody PurchaseOrderMaster po){
        return productDetailsService.searchProductDetailsByPoId(po);
    }
	
	@PostMapping("/updatestates")
    public ProductDetails updateStates(@RequestBody ProductDetails productDetailsRequest){
        return productDetailsService.updateStates(productDetailsRequest);
    }
}