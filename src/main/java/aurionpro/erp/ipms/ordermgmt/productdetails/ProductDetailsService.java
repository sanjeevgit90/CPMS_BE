package aurionpro.erp.ipms.ordermgmt.productdetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import aurionpro.erp.ipms.assetmgmt.productmaster.ProductMaster;
import aurionpro.erp.ipms.assetmgmt.productmaster.ProductMasterRepository;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderMaster;
import aurionpro.erp.ipms.ordermgmt.purchase.PurchaseOrderRepository;
import aurionpro.erp.ipms.vendor.addressmaster.AddressMasterRepository;

@Service
public class ProductDetailsService {

	@Autowired
	ProductDetailsRepository productDetailsRepository;
	
	@Autowired
    ProductMasterRepository productRepo;
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepo;
	
	@Autowired
	ProductDetailsViewRepository productDetailsViewRepo;
	
	@Autowired
	AddressMasterRepository addressMasterRepo;
	
	public ProductDetails addProductDetail(ProductDetails productDetailsRequest){
		validateRequest(productDetailsRequest);
		
		//for saving historic columns
		ProductMaster product = productRepo.findById(productDetailsRequest.getProductName()).get();
		productDetailsRequest.setBaseUomData(product.getBaseuom());
		String productNameWithDesc;
		productNameWithDesc = product.getProductname();
		if(productDetailsRequest.getDescription()!=null || product.getDescription()!=null) {
			if(productDetailsRequest.getDescription()!=null && product.getDescription()==null)
				productNameWithDesc += " ("+productDetailsRequest.getDescription()+")";
			if(productDetailsRequest.getDescription()==null && product.getDescription()!=null)
				productNameWithDesc += " ("+product.getDescription()+")";
			if(productDetailsRequest.getDescription()!=null && product.getDescription()!=null)
				productNameWithDesc += " ("+product.getDescription()+" - "+productDetailsRequest.getDescription()+")";
		}
		productDetailsRequest.setProductNameWithDesc(productNameWithDesc);
		//for saving historic columns
		
		ProductDetails obj1 = productDetailsRepository.save(productDetailsRequest);
		
		if(obj1!=null){
			if(productDetailsRequest.isPoHeadEditFlag()){
				//purchaseOrderRepo.changeStatusById(productDetailsRequest.getPurchaseOrderNo(), "PENDING");
			}
		}
				
        return obj1;
    }
	
    public ProductDetails updateProductDetail(@PathVariable(value="id") long id, @RequestBody ProductDetails productDetailsRequest){
        if (id!=productDetailsRequest.getEntityId())
        	throw new RuntimeException("Request mismatch");
        
        validateRequest(productDetailsRequest);

        Optional<ProductDetails> orderTemp=productDetailsRepository.findById(id);

        if (orderTemp==null) {
        	throw new RuntimeException("Product detail entity does not exists.");
        }
        
        //for saving historic columns
		ProductMaster product = productRepo.findById(productDetailsRequest.getProductName()).get();
		productDetailsRequest.setBaseUomData(product.getBaseuom());
		String productNameWithDesc;
		productNameWithDesc = product.getProductname();
		if(productDetailsRequest.getDescription()!=null || product.getDescription()!=null) {
			if(productDetailsRequest.getDescription()!=null && product.getDescription()==null)
				productNameWithDesc += " ("+productDetailsRequest.getDescription()+")";
			if(productDetailsRequest.getDescription()==null && product.getDescription()!=null)
				productNameWithDesc += " ("+product.getDescription()+")";
			if(productDetailsRequest.getDescription()!=null && product.getDescription()!=null)
				productNameWithDesc += " ("+product.getDescription()+" - "+productDetailsRequest.getDescription()+")";
		}
		productDetailsRequest.setProductNameWithDesc(productNameWithDesc);
		//for saving historic columns
		
		ProductDetails obj1 = productDetailsRepository.save(productDetailsRequest);
		
		if(productDetailsRequest.isPoHeadEditFlag()){
			//purchaseOrderDao.changeStatusById(productNameWithDesc.getPurchaseOrderNo(), "PENDING");
		}

        return obj1;
    }
	
    public List<ProductDetails> getAllProductDetails(){
        return productDetailsRepository.findAll();
    }
	
    public Optional<ProductDetails> getProductDetailById(@PathVariable(value="id") long id){
        return productDetailsRepository.findById(id);
    }
	
    public void deleteProductDetailById(@PathVariable(value="id") long id){
		productDetailsRepository.deleteById(id);
    }
    
    public void deleteProductListByPoId(PurchaseOrderMaster po){
		productDetailsRepository.deleteProductListByPoId(po);
    }
	
	private boolean validateRequest(ProductDetails request) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getProductName()))
			errorlist.add("Product cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Product details entity is invalid", errorlist);
		}
        return true;
    }

	public List<ProductDetails> getProductDetailsByPoId(PurchaseOrderMaster po) {
		//return productDetailsRepository.findByPurchaseOrder(po);
		return productDetailsRepository.findByPurchaseOrderOrderByCreatedDateAsc(po);
	}
	
	public List<ProductDetailsView> searchProductDetailsByPoId(PurchaseOrderMaster po) {
		//return productDetailsViewRepo.findByPurchaseOrder(po.getEntityId());
		return productDetailsViewRepo.findByPurchaseOrderOrderByCreatedDateAsc(po.getEntityId());
	}

	public ProductDetails updateStates(ProductDetails productDetailsRequest) {
		List<ProductDetails> l = productDetailsRepository.findByPurchaseOrder(productDetailsRequest.getPurchaseOrder());
		for(ProductDetails p : l)
		{
			p.setAmount(p.getRate()*p.getQuantity());
			p.setTotalDiscount(p.getDiscountAmount()+(p.getDiscount()/100*p.getAmount()));
			p.setTotalAmount(p.getAmount()-p.getTotalDiscount()); 
			if(productDetailsRequest.getIsHistoricData().equalsIgnoreCase("NO"))
			{
				if(productDetailsRequest.getApplyGstFlag().equalsIgnoreCase("YES"))
				{
					if(productDetailsRequest.isStateFlag()) {
						p.setCgstAmount((p.getCgst()/100)*p.getTotalAmount());
						p.setSgstAmount((p.getSgst()/100)*p.getTotalAmount());
						p.setIgstAmount(0);
						p.setFinalAmount(p.getTotalAmount()+(((p.getCgst()+p.getSgst())/100)*p.getTotalAmount()));
					} else {
						p.setCgstAmount(0);
						p.setSgstAmount(0);
						p.setIgstAmount((p.getIgst()/100)*p.getTotalAmount());
						p.setFinalAmount(p.getTotalAmount()+((p.getIgst()/100)*p.getTotalAmount()));
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
				float i = 0.0f;
				productDetailsRequest.setCgstAmount(i);
				productDetailsRequest.setSgstAmount(i);
				productDetailsRequest.setIgstAmount(i);
				productDetailsRequest.setFinalAmount(productDetailsRequest.getTotalAmount()+productDetailsRequest.getServiceTax()+productDetailsRequest.getVat());
			}
			productDetailsRepository.save(productDetailsRequest);
		}
		return null;
	}
	
	public void checkAllProductCalculations(PurchaseOrderMaster po) {
		List<ProductDetailsView> prodDetails = productDetailsViewRepo.findByPurchaseOrder(po.getEntityId());
		if(prodDetails.isEmpty() || prodDetails == null)
			return;
		
		if(po.getIsHistoricData().equalsIgnoreCase("YES"))
			return;

		ProductDetails prodReq = null;
		boolean isSameState = false;
		isSameState = addressMasterRepo.compareStateOfAddress(po.getSupplierDetails(), po.getBillToAddress());
		
		for(ProductDetailsView p : prodDetails) {
			prodReq = new ProductDetails();
			prodReq.setEntityId(p.getEntityId());
			if(po.getCurrency().equalsIgnoreCase("INR")){
				if(po.getIsHistoricData().equalsIgnoreCase("NO")){
					if(!po.getBillFromGstNo().equalsIgnoreCase("NA")){
						if(isSameState)
						{
							prodReq.setCgstAmount((p.getCgst()/100)*(p.getTotalAmount()));
							prodReq.setSgstAmount((p.getSgst()/100)*(p.getTotalAmount()));
							prodReq.setIgstAmount(0.0f);
							prodReq.setFinalAmount(p.getTotalAmount()+((p.getCgst()+p.getSgst())/100*p.getTotalAmount()));
						}
						else
						{
							prodReq.setIgstAmount((p.getIgst()/100)*(p.getTotalAmount()));
							prodReq.setCgstAmount(0.0f);
							prodReq.setSgstAmount(0.0f);
							prodReq.setFinalAmount(p.getTotalAmount()+((p.getIgst())/100*p.getTotalAmount()));	
						}
					}
					else {
						prodReq.setCgstAmount(0.0f);
						prodReq.setSgstAmount(0.0f);
						prodReq.setIgstAmount(0.0f);
						prodReq.setFinalAmount(p.getTotalAmount());
					}
				}
				//this is not needed as the final amount will be same for every product as service tax and vat amount remain same.
				/*else if(po.getIsHistoricData().equalsIgnoreCase("YES")){
					double i = 0.0;
					prodReq.setCgstAmount(i);
					prodReq.setSgstAmount(i);
					prodReq.setIgstAmount(i);
					prodReq.setFinalAmount(p.getToatalAmount()+p.getServiceTax()+p.getVat());
				}*/
			}
			else {
				prodReq.setIgstAmount((p.getIgst()/100)*(p.getTotalAmount()));
				prodReq.setCgstAmount(0.0f);
				prodReq.setSgstAmount(0.0f);
				prodReq.setFinalAmount(p.getTotalAmount()+p.getIgstAmount());
			}
			updateAllProductCalculations(prodReq);
		}
	}
	
	public ProductDetails updateAllProductCalculations(ProductDetails request) {
		ProductDetails entity = productDetailsRepository.findById(request.getEntityId()).get();
		entity.setCgstAmount(request.getCgstAmount());
		entity.setSgstAmount(request.getSgstAmount());
		entity.setIgstAmount(request.getIgstAmount());
		entity.setFinalAmount(request.getFinalAmount());
		productDetailsRepository.save(entity);
		return entity;
	}
}
