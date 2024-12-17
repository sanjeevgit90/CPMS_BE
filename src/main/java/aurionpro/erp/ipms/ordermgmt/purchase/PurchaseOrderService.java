package aurionpro.erp.ipms.ordermgmt.purchase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.persistence.Lob;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.openbravo.openbravodetails.OpenBravoHitDetails;
import aurionpro.erp.ipms.openbravo.openbravodetails.OpenBravoHitDetailsRepository;
import aurionpro.erp.ipms.openbravo.openbravodetails.OpenBravoProductDetailsRepository;
import aurionpro.erp.ipms.openbravo.openbravodetails.OpenBravoProductDetailsView;
import aurionpro.erp.ipms.openbravo.openbravodetails.OpenBravoPurchaseOrderRepository;
import aurionpro.erp.ipms.openbravo.openbravodetails.OpenBravoPurchaseOrderView;
import aurionpro.erp.ipms.ordermgmt.productdetails.ProductDetails;
import aurionpro.erp.ipms.ordermgmt.productdetails.ProductDetailsService;
import aurionpro.erp.ipms.ordermgmt.productdetails.ProductDetailsView;
import aurionpro.erp.ipms.ordermgmt.productdetails.ProductDetailsViewRepository;
import aurionpro.erp.ipms.ordermgmt.purchaseordertask.AllTaskViewRepository;
import aurionpro.erp.ipms.ordermgmt.purchaseordertask.PoTaskMaster;
import aurionpro.erp.ipms.ordermgmt.purchaseordertask.PoTaskMasterRepository;
import aurionpro.erp.ipms.ordermgmt.ratecontract.RateContractMaster;
import aurionpro.erp.ipms.utility.AppProperties;
import aurionpro.erp.ipms.utility.HttpClientClass;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;
import aurionpro.erp.ipms.utility.email.SendingMailService;
import aurionpro.erp.ipms.utility.pdf.HeaderFooterTable;
import aurionpro.erp.ipms.utility.pdf.NumberToWordsConverter;
import aurionpro.erp.ipms.vendor.addressmaster.AddressMaster;
import aurionpro.erp.ipms.vendor.addressmaster.AddressMasterRepository;
import aurionpro.erp.ipms.vendor.gstmaster.GstMaster;
import aurionpro.erp.ipms.vendor.gstmaster.GstMasterRepository;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;
import aurionpro.erp.ipms.vendor.partymaster.PartyMasterRepository;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderRepository purchaseOrderRepo;
	
	@Autowired
	PurchaseOrderOpenBravoRepository purchaseOrderOpenBravoRepo;
	
	@Autowired
	ProductDetailsService productDetailsService;
	
	@Autowired
	PartyMasterRepository partyMasterRepo;
	
	@Autowired
	AddressMasterRepository addressMasterRepo;
	
	@Autowired
	GstMasterRepository gstMasterRepo;
	
	@Autowired
	PurchaseOrderHistoryRepository purchaseOrderHistoryRepo;
	
	@Autowired
	PurchaseOrderViewRepository purchaseOrderViewRepo;
	
	@Autowired
	ProductDetailsViewRepository productDetailsViewRepo;
	
	@Autowired
	OpenBravoPurchaseOrderRepository openBravoPurchaseOrderRepo;
	
	@Autowired
	OpenBravoProductDetailsRepository openBravoProductDetailsRepo;
	
	@Autowired
	OpenBravoHitDetailsRepository openBravoHitDetailsRepo;
	
	@Autowired
	private AllTaskViewRepository allTaskViewRepo;
	
	@Autowired
	PoTaskMasterRepository poTaskMasterRepository;
	
    @Autowired
    ProjectUtil projectFilterService;
	
	@Autowired
	private Environment env;
	
	@Autowired
    private AppProperties appProperties;
	
	@Autowired
	PurchaseOrderListViewRepository purchaseOrderListViewRepo;
	
	public PurchaseOrderMaster createPurchaseOrder(PurchaseOrderMaster purchaseOrderMasterRequest){
        validateRequest(purchaseOrderMasterRequest);
        List<PurchaseOrderMaster> tcheck=purchaseOrderRepo.findByPurchaseOrderNo(purchaseOrderMasterRequest.getPurchaseOrderNo());
        if (tcheck.size()>0){
            throw new RuntimeException("Purchase order entity already exists.");
        }
        purchaseOrderMasterRequest.setApprovalStatus("PENDING");
        if(StringUtils.isEmpty(purchaseOrderMasterRequest.getIsAmendedFlag())) {
        	purchaseOrderMasterRequest.setIsAmendedFlag("NO");
        }
        if(StringUtils.isEmpty(purchaseOrderMasterRequest.getDiscountAmt())) {
        	purchaseOrderMasterRequest.setDiscountAmt(0.0);
        }
        
        PurchaseOrderMaster saveObj = purchaseOrderRepo.save(purchaseOrderMasterRequest);
        
        if(saveObj != null) {
        	PurchaseOrderOpenBravoDetails openBravoObj = new PurchaseOrderOpenBravoDetails();
        	openBravoObj.setPurchaseOrderMaster(saveObj);
        	openBravoObj.setPoPushedStatus("PENDING");
        	purchaseOrderOpenBravoRepo.save(openBravoObj);
        	savePoHistoryDetails(saveObj, "SAVE");
        }
        return saveObj;
    }
	
	public PurchaseOrderMaster updatePurchaseOrder(long orderId, PurchaseOrderMaster purchaseOrderMasterRequest){
        if (orderId!=purchaseOrderMasterRequest.getEntityId())
        	throw new RuntimeException("Request mismatch");

        validateRequest(purchaseOrderMasterRequest);
        
        Optional<PurchaseOrderMaster> orderTemp=purchaseOrderRepo.findById(orderId);

        if (orderTemp==null) {
        	throw new RuntimeException("Purchase order entity does not exists.");
        }
        if(StringUtils.isEmpty(purchaseOrderMasterRequest.getDiscountAmt())) {
        	purchaseOrderMasterRequest.setDiscountAmt(0.0);
        }
        /*if (!orderTemp.get().getPurchaseOrderNo().equalsIgnoreCase(purchaseOrderMasterRequest.getPurchaseOrderNo())) {
        	throw new EntityValidationException("Purchase order entity is invalid", "Purchase order number mismatch.");
        }*/
        
        //boolean isSameStateOld = addressMasterRepo.compareStateOfAddress(orderTemp.get().getSupplierDetails(), orderTemp.get().getBillToAddress());
        //boolean isSameStateNew = addressMasterRepo.compareStateOfAddress(purchaseOrderMasterRequest.getSupplierDetails(), purchaseOrderMasterRequest.getBillToAddress());
        
        //for pulling task by po head
        List<PoTaskMaster> tm = poTaskMasterRepository.getTaskByPoIdAndStatus(orderId, "PENDING");
        if(!tm.isEmpty()) {
        	if(!tm.get(0).getStageName().equalsIgnoreCase("PO TEAM")) {
        		poTaskMasterRepository.updateTaskPulledByPoHead(tm.get(0).getEntityId());
        		purchaseOrderMasterRequest.setApprovalStatus("PENDING");
        	}
        }//for pulling task by po head
        
        PurchaseOrderMaster updateObj = purchaseOrderRepo.save(purchaseOrderMasterRequest);
        if(updateObj != null) {
        	savePoHistoryDetails(updateObj, "UPDATE");
        	
        	/*//for checking tax calculations of all products after updating po - only if old flag and new flag are different
        	if(isSameStateOld != isSameStateNew)
        		productDetailsService.checkAllProductCalculations(updateObj);*/
        	productDetailsService.checkAllProductCalculations(updateObj);
        	updateGrandTotal(updateObj);
        }
        return updateObj;
    }
	
	public List<PurchaseOrderMaster> getAllPurchaseOrders(){
        return purchaseOrderRepo.findAll();
    }
	
	public Optional<PurchaseOrderMaster> getOrderById(long id){
        return purchaseOrderRepo.findById(id);
    }
	
	@Lob
	public Optional<PurchaseOrderView> getOrderByIdView(long id){
        return purchaseOrderViewRepo.findById(id);
    }
	
	public void deleteOrderById(long id){
		PurchaseOrderMaster po = purchaseOrderRepo.findById(id).get();
		if(!po.getApprovalStatus().equalsIgnoreCase("PENDING")) {
			throw new RuntimeException("Purchase order cannot be deleted.");
		}
		purchaseOrderOpenBravoRepo.deleteByPurchaseOrderMaster(po);
		purchaseOrderHistoryRepo.deleteByPurchaseOrderMaster(po);
		productDetailsService.deleteProductListByPoId(po);
        purchaseOrderRepo.deleteById(id);
    }
	
	public PurchaseOrderMaster updateApprovalStatus(long poId, String status) {
		PurchaseOrderMaster po = purchaseOrderRepo.findById(poId).get();
		po.setApprovalStatus(status);
		return purchaseOrderRepo.save(po);
	}
	
	public List<SelectionList> selectionPoList() {
		 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		return purchaseOrderRepo.selectionPoList(profileId);
	}
	
	public List<SelectionList> selectionApprovedPoList() {
		 Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		return purchaseOrderRepo.selectionApprovedPoList(profileId);
	}
	
	/*
	public PageImpl<PurchaseOrderView> getAllPoByFilter(PurchaseOrderView poRequest, Integer page, Integer size) {
		long step1 = System.currentTimeMillis();
		System.out.println("Step1:: "+step1);
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PurchaseOrderView> poEx = Example.of(poRequest,em);
		List<PurchaseOrderView> list =purchaseOrderViewRepo.findAll(poEx);
		long step2 = System.currentTimeMillis();
		System.out.println("Step2:: "+step2);
	    if(poRequest.getFromDate()!=null && poRequest.getToDate()!=null) {
			if(poRequest.getFromDate()>0 && poRequest.getToDate()>0) {
				list = list.stream().filter(p -> (p.getOrderDate() >= poRequest.getFromDate()) && (p.getOrderDate() <= poRequest.getToDate())).collect(Collectors.toList());
			}
		}
	    
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
        List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	    List<PurchaseOrderView> filteredProject = list.stream().filter(f->projectId.contains(f.getAccountName())).collect(Collectors.toList());
	       
	    if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    {
	    	Pageable paging = PageRequest.of(page-1, size);
	    	int start =  (int) paging.getOffset();
	    	int end = Math.min((start + paging.getPageSize()), filteredProject.size());
	    	return new PageImpl<PurchaseOrderView>(filteredProject.subList(start, end), paging, filteredProject.size());
	    }
	    else
	    {
	    	long step3 = System.currentTimeMillis();
	    	System.out.println("Step3:: "+step3);
	    	return new PageImpl<PurchaseOrderView>(filteredProject);
	    }
	}
	*/
	
	public PurchaseOrderView getPurchaseOrderInfo(long id) {
		PurchaseOrderView po = purchaseOrderViewRepo.findById(id).get();
		po.setAttachments(purchaseOrderRepo.getAttachmentList(id));
		List<ProductDetailsView> productDetailsList = productDetailsViewRepo.findByPurchaseOrderOrderByCreatedDateAsc(id);
		//List<ProductDetailsView> productDetailsList = productDetailsViewRepo.findByPurchaseOrder(id);
		po.setProductDetailsList(productDetailsList);
		return po;
	}
	
	public PurchaseOrderMaster updateGrandTotal(PurchaseOrderMaster poRequest) {
		double totalTaxes = 0.0, totalWithoutTaxes = 0.0;
		List<ProductDetails> prodDetails = productDetailsService.getProductDetailsByPoId(poRequest);
		if (!prodDetails.isEmpty()) {
			for (ProductDetails obj : prodDetails) {
				// grandTotal += obj.getFinalAmount();
				totalWithoutTaxes += obj.getTotalAmount();
				if ((obj.getServiceTax() != 0) && (obj.getVat() != 0)) {
					totalTaxes += (obj.getServiceTax() + obj.getVat());
				} else {
					if ((obj.getCgstAmount() != 0) && (obj.getSgstAmount() != 0)) {
						// totalTaxes += (obj.getCgstAmount() +
						// obj.getSgstAmount());
						totalTaxes += (((obj.getCgst() / 100) * obj.getTotalAmount())
								+ ((obj.getSgst() / 100) * obj.getTotalAmount()));
					} else if (obj.getIgstAmount() != 0) {
						// totalTaxes += obj.getIgstAmount();
						totalTaxes += ((obj.getIgst() / 100) * obj.getTotalAmount());
					}
				}
			}
		}
		poRequest.setGrandTotal(totalTaxes + totalWithoutTaxes);
		//poRequest.setTotalTaxes(totalTaxes);
		//poRequest.setTotalWithoutTaxes(totalWithoutTaxes);
		
		//saving grand total
		PurchaseOrderMaster po = purchaseOrderRepo.findById(poRequest.getEntityId()).get();
		po.setTotalWithoutTaxes(totalWithoutTaxes);
		po.setTotalTaxes(totalTaxes);
		double grandTotal = 0;
		if (poRequest.getGrandTotal() > po.getDiscountAmt())
			grandTotal = poRequest.getGrandTotal() - po.getDiscountAmt();
		else
			grandTotal = poRequest.getGrandTotal();

		if (po.getCurrency().equalsIgnoreCase("INR"))
			po.setGrandTotal((double)Math.round(grandTotal));
		else
			po.setGrandTotal(grandTotal);
		
		//for setting amount in words
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumIntegerDigits(15);
		df.setMaximumFractionDigits(2);
		String amountInWords = "";
		if(!po.getCurrency().equalsIgnoreCase("INR")) {
			String formattedValue = df.format(po.getGrandTotal());
			if(formattedValue.contains(".")) {
				String grandTotalArray[] = formattedValue.split("\\.");
				amountInWords += NumberToWordsConverter.convertNonInr(Long.parseLong(grandTotalArray[0]));
				if(Integer.parseInt(grandTotalArray[1]) > 0) {
					amountInWords += " And ";
					amountInWords += NumberToWordsConverter.convertNonInr(Long.parseLong(grandTotalArray[1]));
				}
				amountInWords += " Only.";
			} else {
				amountInWords += NumberToWordsConverter.convertNonInr(Long.parseLong(formattedValue));
				amountInWords += " Only.";
			}
		} else {
			amountInWords += NumberToWordsConverter.convert(new Double(po.getGrandTotal()).longValue());
			amountInWords += " Only.";
		}
		po.setAmountInWords(amountInWords);
		//amount in words

		//po.setWorkflowName(poRequest.getWorkflowName());
		
		return purchaseOrderRepo.save(po);
	}
	
	public PurchaseOrderMaster generateDuplicateOrder(PurchaseOrderView poRequest) {
		PurchaseOrderView entity = getPurchaseOrderInfo(poRequest.getEntityId());
		if(entity==null) {
			throw new RuntimeException("Purchase order entity does not exists.");
		}

		PurchaseOrderMaster obj = new PurchaseOrderMaster();

		//obj.setId(UUID.randomUUID().toString());
		if (!StringUtils.isEmpty(poRequest.getIsAmendedFlag())) {
			if (poRequest.getIsAmendedFlag().equalsIgnoreCase("YES")) {
				obj.setPurchaseOrderNo(entity.getPurchaseOrderNo().concat("-AMEND"));
				obj.setOldPoId(entity.getEntityId());
				obj.setOldPoNo(entity.getPurchaseOrderNo());
				obj.setOldPoDate(entity.getOrderDate());
				obj.setIsAmendedFlag(poRequest.getIsAmendedFlag().toUpperCase());
			} else
				obj.setPurchaseOrderNo(entity.getPurchaseOrderNo().concat("-1"));
		}
		obj.setDepartment(entity.getDepartment());
		obj.setAccountName(entity.getAccountName());
		obj.setOrderType(entity.getOrderType());
		obj.setModeOfPayment(entity.getModeOfPayment());
		obj.setSuppliersReference(entity.getSuppliersReference());
		obj.setOtherReference(entity.getOtherReference());
		obj.setDispatchThrough(entity.getDispatchThrough());
		obj.setCurrency(entity.getCurrency());
		obj.setDeliveryTerm(entity.getDeliveryTerm());
		obj.setInvoiceToAddress(entity.getInvoiceToAddress());
		obj.setSupplierDetails(entity.getSupplierDetails());
		obj.setBillFromState(entity.getBillFromState());
		obj.setBillToAddress(entity.getBillToAddress());
		obj.setShipToAddress(entity.getShipToAddress());
		obj.setTermsConditions(entity.getTermsConditions());
		obj.setUploadedTermsAnnexure(entity.getUploadedTermsAnnexure());
		obj.setAdditionalTerms(entity.getAdditionalTerms());
		obj.setSignedCopyPath(entity.getSignedCopyPath());
		obj.setApprovalStatus("PENDING");
		obj.setSupplierName(entity.getSupplierName());
		obj.setBuyerName(entity.getBuyerName());
		obj.setBillFromGstNo(entity.getBillFromGstNo());
		obj.setBillToGstNo(entity.getBillToGstNo());
		obj.setIncludeTerms(entity.getIncludeTerms());
		//obj.setCreatedBy(entity.getCreatedBy());
		obj.setIsHistoricData(entity.getIsHistoricData());
		obj.setTotalTaxes(entity.getTotalTaxes());
		obj.setTotalWithoutTaxes(entity.getTotalWithoutTaxes());
		obj.setGrandTotal(entity.getGrandTotal());
		obj.setDiscountAmt(entity.getDiscountAmt());
		obj.setPoMadeFrom(entity.getPoMadeFrom());
		//obj.setPoheadname(entity.getPoheadname());
		//obj.setPoheaddesignation(entity.getPoheaddesignation());
		obj.setPaymentMethod(entity.getPaymentMethod());
		obj.setOrganisationId(entity.getOrganisationId());
		obj.setOrderDate(entity.getOrderDate());
		if (entity.getPoMadeFrom() != null)
			obj.setRateContractId(entity.getRateContractId());

		//saving new po
		PurchaseOrderMaster saveObj = createPurchaseOrder(obj);

		try {
			if (entity.getProductDetailsList() != null) {
				List<ProductDetailsView> prodList = entity.getProductDetailsList();
				for (ProductDetailsView prodObj : prodList) {
					ProductDetails prodReq = new ProductDetails();
					prodReq.setPurchaseOrder(saveObj);
					prodReq.setProductName(prodObj.getProductName());
					prodReq.setRate(prodObj.getRate());
					prodReq.setQuantity(prodObj.getQuantity());
					prodReq.setAmount(prodObj.getAmount());
					prodReq.setDiscount(prodObj.getDiscount());
					prodReq.setDiscountAmount(prodObj.getDiscountAmount());
					prodReq.setTotalDiscount(prodObj.getTotalDiscount());
					prodReq.setTotalAmount(prodObj.getTotalAmount());
					prodReq.setHsnId(prodObj.getHsnId());
					prodReq.setCgst(prodObj.getCgst());
					prodReq.setSgst(prodObj.getSgst());
					prodReq.setIgst(prodObj.getIgst());
					prodReq.setFinalAmount(prodObj.getFinalAmount());
					prodReq.setCgstAmount(prodObj.getCgstAmount());
					prodReq.setSgstAmount(prodObj.getSgstAmount());
					prodReq.setIgstAmount(prodObj.getIgstAmount());
					prodReq.setServiceTax(prodObj.getServiceTax());
					prodReq.setVat(prodObj.getVat());
					prodReq.setDescription(prodObj.getDescription());
					prodReq.setIsHistoricData(saveObj.getIsHistoricData());
					productDetailsService.addProductDetail(prodReq);
				}
				// productDetailsDao.saveProductDetailsInDb(saveObj.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!StringUtils.isEmpty(poRequest.getIsAmendedFlag())) {
			if (poRequest.getIsAmendedFlag().equalsIgnoreCase("YES")) {
				updateAmendedPoId(entity.getEntityId(), saveObj.getEntityId());
			}
		}

		return saveObj;
	}
	
	public PurchaseOrderMaster updateAmendedPoId(long poId, long newPoId) {
		PurchaseOrderMaster po = purchaseOrderRepo.findById(poId).get();
		po.setAmendedPoId(newPoId);
		return purchaseOrderRepo.save(po);
	}

	private boolean validateRequest(PurchaseOrderMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getPurchaseOrderNo()))
			errorlist.add("Purchase order no. cannot be null.");
		if(request.getOrderDate() == 0)
			errorlist.add("Order date cannot be null.");
		if(StringUtils.isEmpty(request.getDepartment()))
			errorlist.add("Department cannot be null.");
		if(StringUtils.isEmpty(request.getAccountName()))
			errorlist.add("Project cannot be null.");
		
		if(StringUtils.isEmpty(request.getOrganisationId()))
			errorlist.add("Organisation cannot be null.");
		/*JKDEntityAuditWithGuid organizationId
		if(StringUtils.isEmpty(request.getOrganizationId()))
			errorlist.add("Organisation cannot be null.");*/
		
		if(StringUtils.isEmpty(request.getOrderType()))
			errorlist.add("Order type cannot be null.");
		if(StringUtils.isEmpty(request.getModeOfPayment()))
			errorlist.add("Payment term cannot be null.");
		if(StringUtils.isEmpty(request.getDispatchThrough()))
			errorlist.add("Dispatch through cannot be null.");
		if(StringUtils.isEmpty(request.getCurrency()))
			errorlist.add("Currency cannot be null.");
		if(StringUtils.isEmpty(request.getDeliveryTerm()))
			errorlist.add("Delivery term cannot be null.");
		if(StringUtils.isEmpty(request.getSuppliersReference()))
			request.setSuppliersReference("NA");
		if(StringUtils.isEmpty(request.getSupplierName()))
			errorlist.add("Supplier cannot be null.");
		if(StringUtils.isEmpty(request.getSupplierDetails()))
			errorlist.add("Supplier address cannot be null.");
		/*if(StringUtils.isEmpty(request.getBillFromState()))
			errorlist.add("Bill from state cannot be null.");*/
		if(StringUtils.isEmpty(request.getBuyerName()))
			errorlist.add("Purchaser cannot be null.");
		if(StringUtils.isEmpty(request.getInvoiceToAddress()))
			errorlist.add("Invoice address cannot be null.");
		if(StringUtils.isEmpty(request.getBillToAddress()))
			errorlist.add("Billing address cannot be null.");
		if(StringUtils.isEmpty(request.getShipToAddress()))
			errorlist.add("Shipping address cannot be null.");
		if(StringUtils.isEmpty(request.getTermsConditions()))
			errorlist.add("Terms & conditions cannot be null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("Purchase order entity is invalid", errorlist);
		}
        return true;
    }

	//---------------for history details table --------------------------------
	public PurchaseOrderHistoryDetails savePoHistoryDetails(PurchaseOrderMaster request, String operation) {
		// for supplier details
		PurchaseOrderHistoryDetails poHistory = null;
		if(operation.equalsIgnoreCase("SAVE")) {
			poHistory = new PurchaseOrderHistoryDetails();
			poHistory.setPurchaseOrderMaster(request);
		}
		else if(operation.equalsIgnoreCase("UPDATE")) {
			poHistory = purchaseOrderHistoryRepo.findByPurchaseOrderMaster(request);
		}
		
		PartyMaster partyObj = partyMasterRepo.findById(request.getSupplierName()).get();
		if (partyObj != null) {
			poHistory.setSuppliernameforhistory(partyObj.getPartyName());
			poHistory.setSuppliercontact(partyObj.getContactPersonName());
			poHistory.setSupplierphone(partyObj.getMobileNo());
			poHistory.setSupplieremail(partyObj.getEmailId());
		}
		AddressMaster partyAddObj = addressMasterRepo.findById(request.getSupplierDetails()).get();
		if (partyAddObj != null) {
			poHistory.setSupplieraddress(partyAddObj.getFullAddress());
		}

		// for buyer details
		PartyMaster partyObj1 = partyMasterRepo.findById(request.getBuyerName()).get();
		
		if (partyObj1 != null) {
			// for invoice-to details
			poHistory.setInvoicetoname(partyObj1.getPartyName());
			poHistory.setInvoicetocontact(partyObj1.getContactPersonName());
			poHistory.setInvoicetophone(partyObj1.getMobileNo());
			poHistory.setInvoicetoemail(partyObj1.getEmailId());
			
			// for bill-to details
			poHistory.setBilltoname(partyObj1.getPartyName());
			poHistory.setBilltocontact(partyObj1.getContactPersonName());
			poHistory.setBilltophone(partyObj1.getMobileNo());
			poHistory.setBilltoemail(partyObj1.getEmailId());
			
			// for ship-to details
			poHistory.setShiptoname(partyObj1.getPartyName());
			poHistory.setShiptocontact(partyObj1.getContactPersonName());
			poHistory.setShiptophone(partyObj1.getMobileNo());
			poHistory.setShiptoemail(partyObj1.getEmailId());
		}
		
		// for invoice-to address
		AddressMaster partyAddObj1 = addressMasterRepo.findById(request.getInvoiceToAddress()).get();
		if (partyAddObj1 != null) {
			poHistory.setInvoicetoaddressforhistory(partyAddObj1.getFullAddress());
		}
		
		// for bill-to address
		AddressMaster partyAddObj2 = addressMasterRepo.findById(request.getBillToAddress()).get();
		if (partyAddObj2 != null) {
			poHistory.setBilltoaddressforhistory(partyAddObj2.getFullAddress());
		}
		
		// for ship-to address
		AddressMaster partyAddObj3 = addressMasterRepo.findById(request.getShipToAddress()).get();
		if (partyAddObj3 != null) {
			poHistory.setShiptoaddressforhistory(partyAddObj3.getFullAddress());
		}

		// for bill-to gst
		GstMaster partyGstObj = gstMasterRepo.getGstByPartyAndPartyId(partyObj1.getEntityId(), partyAddObj2.getEntityId());
		if (partyGstObj != null) {
			poHistory.setBilltogstin(partyGstObj.getGstNo());
		}

		return purchaseOrderHistoryRepo.save(poHistory);
	}

	public PurchaseOrderHistoryDetails updatePurchaseOrderHistoryDetails(long orderId, PurchaseOrderHistoryDetails request) {
		if (orderId!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");

        //validated();
        
        Optional<PurchaseOrderHistoryDetails> orderTemp=purchaseOrderHistoryRepo.findById(orderId);

        if (orderTemp==null) {
        	throw new RuntimeException("Purchase order history entity does not exists.");
        }
        
        return purchaseOrderHistoryRepo.save(request);
	}

	public void deleteOrderHistoryById(long id) {
		purchaseOrderHistoryRepo.deleteById(id);
	}

	public Optional<PurchaseOrderHistoryDetails> getOrderHistoryById(long id) {
		return purchaseOrderHistoryRepo.findById(id);
	}
	//---------------for history details table --------------------------------
	
	//for pushing po to openbravo
	public JSONObject pushPoToOpenBravo(long id) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String errorMsg = "";

		//PurchaseOrderView po = getPurchaseOrderInfo(id);
		//List<ProductDetailsView> prodList = po.getProductDetailsList();
		
		OpenBravoPurchaseOrderView po = openBravoPurchaseOrderRepo.findById(id).get();
		List<OpenBravoProductDetailsView> prodList = openBravoProductDetailsRepo.findByPurchaseOrder(id);

		HashMap<String, String> auth = new HashMap<String, String>();
		auth.put("username", appProperties.getOpenBravoUsername);
		auth.put("password", appProperties.getOpenBravoPassword);

		String savePOUrl = appProperties.getOpenBravoSavePoUrl;
		String poIdOfOB = null;
		JSONObject reqObj = new JSONObject();
		JSONObject outputJson = new JSONObject();
		JSONObject productOutputJson = new JSONObject();
		int status = 0;

		try {
			// for po
			JSONObject org = new JSONObject();
			org.put("id", po.getOrganisationOb());

			JSONObject docType = new JSONObject();
			docType.put("id", po.getDocumentType());

			JSONObject transDoc = new JSONObject();
			transDoc.put("id", po.getTransactionDocumentType());

			JSONObject currency = new JSONObject();
			currency.put("id", po.getCurrencyOb());

			JSONObject priceList = new JSONObject();
			priceList.put("id", po.getPriceList());

			JSONObject businessPartner = new JSONObject();
			businessPartner.put("id", po.getSupplierOb());

			JSONObject partnerAddress = new JSONObject();
			partnerAddress.put("id", po.getSuppaddressOb());

			JSONObject invoiceAddress = new JSONObject();
			invoiceAddress.put("id", po.getBuyinvoaddressOb());

			JSONObject paymentTerms = new JSONObject();
			paymentTerms.put("id", po.getObPaymentTermId());

			JSONObject paymentMethod = new JSONObject();
			paymentMethod.put("id", po.getObPaymentMethodId());

			JSONObject warehouse = new JSONObject();
			warehouse.put("id", po.getWarehouse());

			JSONObject data = new JSONObject();
			data.put("entityName", "Order");
			data.put("active", true);
			data.put("salesTransaction", false);
			data.put("organization", org);
			data.put("documentType", docType);
			data.put("transactionDocument", transDoc);
			data.put("documentNo", po.getPurchaseOrderNo());
			data.put("accountingDate", dateFormat.format(po.getOrderDate()));
			data.put("orderDate", dateFormat.format(po.getOrderDate()));
			data.put("scheduledDeliveryDate", dateFormat.format(po.getOrderDate()));
			data.put("currency", currency);
			data.put("description", po.getTermsConditions());
			data.put("priceList", po.getPriceList());
			data.put("businessPartner", businessPartner);
			data.put("partnerAddress", partnerAddress);
			data.put("invoiceAddress", partnerAddress);
			data.put("paymentTerms", paymentTerms);
			data.put("paymentMethod", paymentMethod);
			data.put("warehouse", warehouse);
			data.put("documentStatus", "CO");
			data.put("documentAction", "--");
			data.put("formOfPayment", "P");
			data.put("invoiceTerms", "I");
			data.put("deliveryMethod", "S");
			data.put("project", po.getProjectOb());
			data.put("orderReference", po.getSuppliersReference());
			data.put("userContact", po.getContactId());

			reqObj.put("data", data);
			System.out.println("\n\nRequest JSON :: \n" + reqObj.toString());

			String outputData = HttpClientClass.sendPoToOb(savePOUrl, auth, reqObj.toString(), "POST");
			// System.out.println("\n\nResponse :: \n\n"+outputData);

			outputJson = new JSONObject(outputData);
			System.out.println("\n\nResponse JSON :: \n\n" + outputJson);

			JSONObject res = outputJson.getJSONObject("response");
			System.out.println("\n\nResponse JSON - res :: \n\n"+res);
			System.out.println("status :: " + res.get("status"));
			System.out.println("status :: " + res.get("status").toString());

			if (Integer.parseInt(res.get("status").toString()) == 0) {
				JSONArray resData = res.getJSONArray("data");
				System.out.println("\n\nResponse Data[] JSON :: \n\n" + resData);

				poIdOfOB = resData.getJSONObject(0).getString("id");
				System.out.println("\n\nPO Id :: \n\n" + poIdOfOB);

				if (prodList != null) {
					int i = 0;
					for (OpenBravoProductDetailsView p : prodList) {
						productOutputJson = sendProductDetailsOfPoToOpenBravo(p, poIdOfOB, po.getProjectOb(), ++i,
									dateFormat.format(po.getOrderDate()), po.getOrganisationOb(), po.getCurrencyOb(),
									po.getWarehouse());
						
						status = Integer.parseInt(productOutputJson.getJSONObject("response").get("status").toString());
						if(status != 0) {
							break;
						}
					}
				}

				// if pushed successfully then change po pushed status
				//purchaseOrderDao.changePushedStatusById(id);
				if(status == 0) {
					PurchaseOrderMaster p = new PurchaseOrderMaster();
					p.setEntityId(id);
					PurchaseOrderOpenBravoDetails obPo = purchaseOrderOpenBravoRepo.findByPurchaseOrderMaster(p);
					obPo.setPoPushedStatus("DONE");
					obPo.setPoPushedDate(new Date().getTime());
					purchaseOrderOpenBravoRepo.save(obPo);
				}
			} else {
				return outputJson;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "Message :: " + e.getMessage() + " Stack Trace ::" + e.getStackTrace();
		} finally {
			// saving openbravo hit request and response
			OpenBravoHitDetails entity = new OpenBravoHitDetails();
			entity.setInputData(reqObj.toString());
			if (errorMsg != "")
				entity.setOutputData(errorMsg);
			else
				entity.setOutputData(outputJson.toString());
			//entity.setCreatedDate(new Date());
			openBravoHitDetailsRepo.save(entity);
		}
		if(status != 0) {
			return productOutputJson;
		} else {
			return outputJson;
		}
	}

	public JSONObject sendProductDetailsOfPoToOpenBravo(OpenBravoProductDetailsView obj, String poId, String project, int lineNo,
			String orderDate, String orgId, String curr, String ware) {
		HashMap<String, String> auth = new HashMap<String, String>();
		auth.put("username", appProperties.getOpenBravoUsername);
		auth.put("password", appProperties.getOpenBravoPassword);

		String saveProductUrl = appProperties.getOpenBravoSaveProductUrl;
		JSONObject reqObj = new JSONObject();
		JSONObject jsonObj = new JSONObject();
		String errorMsg = "";

		try {
			JSONObject org = new JSONObject();
			org.put("id", orgId);

			JSONObject salesOrder = new JSONObject();
			salesOrder.put("id", poId);

			JSONObject product = new JSONObject();
			product.put("id", obj.getObProductId());

			JSONObject uOM = new JSONObject();
			uOM.put("id", obj.getObUomId());

			JSONObject tax = new JSONObject();
			tax.put("id", obj.getObHsnId());

			JSONObject currency = new JSONObject();
			currency.put("id", curr);

			JSONObject warehouse = new JSONObject();
			warehouse.put("id", ware);

			JSONObject data = new JSONObject();
			data.put("entityName", "OrderLine");
			data.put("active", true);
			data.put("organization", org);
			data.put("salesOrder", salesOrder);
			data.put("orderDate", orderDate);
			data.put("lineNo", lineNo);
			data.put("product", product);
			data.put("uOM", uOM);
			data.put("orderedQuantity", obj.getQuantity());
			data.put("currency", currency);
			data.put("tax", tax);
			data.put("warehouse", warehouse);
			data.put("unitPrice", obj.getRate());
			data.put("priceLimit", 0);
			data.put("lineNetAmount", obj.getFinalAmount());
			data.put("project", project);
			data.put("description", obj.getDescription());

			reqObj.put("data", data);
			System.out.println("\n\nRequest JSON :: \n" + reqObj.toString());

			String outputData = HttpClientClass.sendPoToOb(saveProductUrl, auth, reqObj.toString(), "POST");
			// System.out.println("\n\nResponse :: \n\n"+outputData);

			jsonObj = new JSONObject(outputData);
			System.out.println("\n\nResponse JSON :: \n\n" + jsonObj);
		} catch (Exception e1) {
			e1.printStackTrace();
			errorMsg = "Message :: " + e1.getMessage() + " Stack Trace ::" + e1.getStackTrace();
		} finally {
			// saving openbravo hit request and response
			OpenBravoHitDetails entity = new OpenBravoHitDetails();
			entity.setInputData(reqObj.toString());
			if (errorMsg != "")
				entity.setOutputData(errorMsg);
			else
				entity.setOutputData(jsonObj.toString());
			//entity.setCreatedDate(new Date());
			openBravoHitDetailsRepo.save(entity);
		}
		return jsonObj;
	}
//*/
	
	//for verifying po
	public PurchaseOrderMaster updateVerifyFlag(PurchaseOrderMaster purchaseOrderRequest) throws DocumentException {

		PurchaseOrderView pom = getPurchaseOrderInfo(purchaseOrderRequest.getEntityId());
		String attachmentPath = "";
		try {
			attachmentPath = writePDF(pom, false);
			
			//String dirPath = messages.getProperty(Constant.PDF_WRITE_DIR_VR.getValue()) + pom.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") + ".pdf";
			String dirPath = env.getProperty("PDFWriteDirVR") + pom.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") + File.separator + pom.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") + ".pdf";
			File from = new File(attachmentPath);
	        File to = new File(dirPath);
			if (to.getParentFile() != null) {
				to.getParentFile().mkdirs();
			}
	        copyFile(from, to);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (attachmentPath != null) {
			purchaseOrderRequest.setVerfiedPOAttachment(attachmentPath);
			//purchaseOrderRequest.setPoCopy(messages.getProperty(Constant.PDF_WRITE_DIR_RE.getValue()) + pom.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") + ".pdf");
			purchaseOrderRequest.setPoCopy(env.getProperty("PDFWriteDirRelative") + pom.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") + File.separator + pom.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") + ".pdf");
		}

		//int success = purchaseOrderRepo.updateVerifyFlag(purchaseOrderRequest.getVerify(), purchaseOrderRequest.getVerifyDate(), purchaseOrderRequest.getVerfiedPOAttachment(), purchaseOrderRequest.getVerifiedBy(), purchaseOrderRequest.getPoCopy(), purchaseOrderRequest.getEntityId());
		int success = purchaseOrderRepo.updateVerifyFlag("VERIFIED", 1, purchaseOrderRequest.getVerfiedPOAttachment(), MyPrincipal.getMyProfile().getUsername(), purchaseOrderRequest.getPoCopy(), purchaseOrderRequest.getEntityId());
		//System.out.println("updated rows :: "+success);
		
		/*PurchaseOrderMaster po = purchaseOrderRepo.findById(purchaseOrderRequest.getEntityId()).get();
		po.setVerify("VERIFIED");
		po.setVerfiedPOAttachment(purchaseOrderRequest.getVerfiedPOAttachment());
		po.setVerifiedBy(MyPrincipal.getMyProfile().getUsername());
		po.setPoCopy(purchaseOrderRequest.getPoCopy());
		return purchaseOrderRepo.save(po);*/

		return null;
	}
	
	//code for generating po pdf
	public static com.itextpdf.text.Font FONT_BODY = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 9, com.itextpdf.text.Font.NORMAL);
	public static com.itextpdf.text.Font FONT_TABLE_HEADER = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 9, com.itextpdf.text.Font.BOLD);
	public static com.itextpdf.text.Font PRODUCT_FONT = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 8, com.itextpdf.text.Font.NORMAL);
	public static com.itextpdf.text.Font FONT_PAGE_HEADING = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.BOLD);
	public static com.itextpdf.text.Font FOOTER_FONT = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 8, com.itextpdf.text.Font.NORMAL);
	public static com.itextpdf.text.Font FOOTER_FONT_BOLD = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 8, com.itextpdf.text.Font.BOLD);
	
	public String writePDF(PurchaseOrderView po, Boolean flag) throws IOException, com.itextpdf.text.DocumentException {
		System.out.println(":::::::::::::::::::::::: Printing Start :::::::::::::::::::::::::::");
		SimpleDateFormat dateFrmt = new SimpleDateFormat("dd/MM/yyyy");
		
		//for number formatting
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumIntegerDigits(15);
		df.setMaximumFractionDigits(2);
		String file = null;
		int count = 0;
		if (flag)
		{
			count = allTaskViewRepo.getRejectedCount(po.getEntityId());
			
			file = env.getProperty("PDFWriteDirVR")+ po.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_")+ File.separator + po.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") +"_draft_"+ count+ ".pdf";
		}
		else
		{
			file = env.getProperty("PDFWriteDir") + po.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") + ".pdf";
			
		}
		File filePath = new File(file);
		
		if (filePath.getParentFile() != null) {
			filePath.getParentFile().mkdirs();
		}
		
		com.itextpdf.text.Document document = new com.itextpdf.text.Document();
		PdfFont font = PdfFontFactory.createFont(com.itextpdf.io.font.FontConstants.HELVETICA);
		PdfFont bold = PdfFontFactory.createFont(com.itextpdf.io.font.FontConstants.HELVETICA_BOLD);

		// #####################################

		PdfWriter docWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

		//adding header and footer to document
        HeaderFooterTable event = new HeaderFooterTable(getHeaderData(), getFooterData());
        docWriter.setPageEvent(event);
		
		document.setMargins(20, 20, 40, 40); //left, right, top, bottom
		document.open();

		Paragraph reportBody = new Paragraph();

		String buyBilladdress = "";
		String BuyInvoAddress = "";
		String BuyShipToAddress = "";
		String suppAddress = "";

		float[] columnWidths = { 5, 5, 5 }; // total 4 columns and their width.
											// The first three columns will take
											// the same width and the fourth one
											// will be 5/2.
		PdfPTable table = new PdfPTable(columnWidths);
		table.setWidthPercentage(100); // set table with 100% (full page)
		table.getDefaultCell().setUseAscender(true);
		table.setSplitLate(false);
		table.setHeaderRows(4);

		//title on page
		PdfPCell cell;
		if(flag)
			cell = new PdfPCell(new Phrase("DRAFT PURCHASE ORDER\n", FONT_PAGE_HEADING));
		else
			cell = new PdfPCell(new Phrase("PURCHASE ORDER\n", FONT_PAGE_HEADING));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(3);
		cell.setBorder(0);
		table.addCell(cell);
		
		Phrase p = new Phrase();
		
		//1st row 1st column
		p.add(new Chunk("Invoice Postal Address.:\n", FONT_TABLE_HEADER));
		if (po.getInvoicetoaddressforhistory() != null) {
			BuyInvoAddress += po.getInvoicetoaddressforhistory() + "\n";
		}
		if (po.getBuyContactPerson() != null) {
			BuyInvoAddress += "Contact Person: " + po.getInvoicetocontact() + "\n";
		}
		if (po.getBuyMobNo() != null) {
			BuyInvoAddress += "Phone No. " + po.getInvoicetophone() + "\n";
		}
		if (po.getBuyEmail() != null) {
			BuyInvoAddress += "Email: " + po.getInvoicetoemail() + "\n";
		}
		p.add(new Chunk(po.getInvoicetoname() + "\n", FONT_TABLE_HEADER));
		p.add(new Chunk(BuyInvoAddress, FONT_BODY));

		cell = new PdfPCell(new Phrase(p));
		// cell.setHorizontalAlignment(Element.ALIGN_LEFT); // alignment
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		// cell.setBorder(0);
		table.addCell(cell);

		//1st row 2nd column
		p = new Phrase();
		p.add(new Chunk("Order No.:\n", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getPurchaseOrderNo(), FONT_BODY));
		if(!StringUtils.isEmpty(po.getIsAmendedFlag())) {
			if(po.getIsAmendedFlag().equalsIgnoreCase("YES")) {
				p.add(new Chunk("\n\nOld Order No.:\n", FONT_TABLE_HEADER));
				p.add(new Chunk(po.getOldPoNo(), FONT_BODY));
			}
		}
		p.add(new Chunk("\n\nOrder Type.:\n", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getOrderType(), FONT_BODY));
		p.add(new Chunk("\n\nSupplier's Ref.:\n", FONT_TABLE_HEADER));
		if (!StringUtils.isEmpty(po.getSuppliersReference())) {
			p.add(new Chunk(po.getSuppliersReference(), FONT_BODY));
		}
		cell = new PdfPCell(p);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		//1st row 3rd column
		p = new Phrase();
		p.add(new Chunk("Order Date.:\n", FONT_TABLE_HEADER));
		p.add(new Chunk(dateFrmt.format(po.getOrderDate()), FONT_BODY));
		if(!StringUtils.isEmpty(po.getIsAmendedFlag())) {
			if(po.getIsAmendedFlag().equalsIgnoreCase("YES")) {
				p.add(new Chunk("\n\nOld Order Date.:\n", FONT_TABLE_HEADER));
				p.add(new Chunk(dateFrmt.format(po.getOldPoDate()), FONT_BODY));
			}
		}
		p.add(new Chunk("\n\nMode / Terms Of Payment.:\n", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getModeOfPayment(), FONT_BODY));
		p.add(new Chunk("\n\nOther Reference(s).:\n", FONT_TABLE_HEADER));
		if (!StringUtils.isEmpty(po.getOtherReference())) {
			p.add(new Chunk(po.getOtherReference(), FONT_BODY));
		}
		cell = new PdfPCell(p);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setBackgroundColor(new GrayColor(0.90f));
		// cell.setFixedHeight(25);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		//2nd row 1st column
		p = new Phrase();
		p.add(new Chunk("Delivery Term.: ", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getDeliveryTerm(), FONT_BODY));
		cell = new PdfPCell(p);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setBackgroundColor(new GrayColor(0.90f));
		// cell.setFixedHeight(25);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		//2nd row 2nd column
		p = new Phrase();
		p.add(new Chunk("Dispatch Through.: ", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getDispatchThrough(), FONT_BODY));
		cell = new PdfPCell(p);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setBackgroundColor(new GrayColor(0.90f));
		// cell.setFixedHeight(25);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		//2nd row 3rd column
		p = new Phrase();
		p.add(new Chunk("Currency.: ", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getCurrency(), FONT_BODY));
		cell = new PdfPCell(p);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		// cell.setBackgroundColor(new GrayColor(0.90f));
		// cell.setFixedHeight(25);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);


		// --------
		//3rd row 1st column
		p = new Phrase();
		p.add(new Chunk("Supplier Address.:\n", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getSuppliernameforhistory() + "\n", FONT_TABLE_HEADER));
		if (po.getSupplieraddress() != null) {
			suppAddress += po.getSupplieraddress() + "\n";
		}
		if (po.getSuppliercontact() != null) {
			suppAddress += "Contact Person: " + po.getSuppliercontact() + "\n";
		}
		if (po.getSupplierphone() != null) {
			suppAddress += "Phone No. " + po.getSupplierphone() + "\n";
		}
		if (po.getSupplieremail() != null) {
			suppAddress += "Email: " + po.getSupplieremail() + "\n";
		}
		p.add(new Chunk(suppAddress, FONT_BODY));
		p.add(new Chunk("GST No.: ", FONT_TABLE_HEADER));
		if (!StringUtils.isEmpty(po.getBillFromGstNo())) {
			p.add(new Chunk(po.getBillFromGstNo() + "\n", FONT_BODY));
			//suppAddress += "GST No.: " + po.getBillFromGstNo() + "\n";
		} else {
			p.add(new Chunk("NA\n", FONT_BODY));
		}
		cell = new PdfPCell(p);
		// cell.setHorizontalAlignment(Element.ALIGN_LEFT); // alignment
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		table.addCell(cell);

		// --------
		//3rd row 2nd column
		p = new Phrase();
		p.add(new Chunk("Bill To Address.:\n", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getBilltoname() + "\n", FONT_TABLE_HEADER));
		if (po.getBilltoaddressforhistory() != null) {
			buyBilladdress += po.getBilltoaddressforhistory() + "\n";
		}
		if (po.getBilltocontact() != null) {
			buyBilladdress += "Contact Person: " + po.getBilltocontact() + "\n";
		}
		if (po.getBilltophone() != null) {
			buyBilladdress += "Phone No. " + po.getBilltophone() + "\n";
		}
		if (po.getBilltoemail() != null) {
			buyBilladdress += "Email: " + po.getBilltoemail() + "\n";
		}
		p.add(new Chunk(buyBilladdress, FONT_BODY));
		p.add(new Chunk("GST No.: ", FONT_TABLE_HEADER));
		if (!StringUtils.isEmpty(po.getBilltogstin())) {
			p.add(new Chunk(po.getBilltogstin() + "\n", FONT_BODY));
			//buyBilladdress += "GST No.: " + po.getBilltogstin() + "\n";
		} else {
			p.add(new Chunk("NA\n", FONT_BODY));
		}
		cell = new PdfPCell(p);
		// cell.setHorizontalAlignment(Element.ALIGN_LEFT); // alignment
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		table.addCell(cell);

		// --------
		//3rd row 3rd column
		p = new Phrase();
		p.add(new Chunk("Ship To Address.:\n", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getShiptoname() + "\n", FONT_TABLE_HEADER));
		if (po.getBilltoaddressforhistory() != null) {
			BuyShipToAddress += po.getShiptoaddressforhistory() + "\n";
		}
		if (po.getBilltocontact() != null) {
			BuyShipToAddress += "Contact Person: " + po.getShiptocontact() + "\n";
		}
		if (po.getBilltophone() != null) {
			BuyShipToAddress += "Phone No. " + po.getShiptophone() + "\n";
		}
		if (po.getBilltoemail() != null) {
			BuyShipToAddress += "Email: " + po.getShiptoemail() + "\n";
		}
		p.add(new Chunk(BuyShipToAddress, FONT_BODY));
		cell = new PdfPCell(p);
		// cell.setHorizontalAlignment(Element.ALIGN_LEFT); // alignment
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		table.addCell(cell);


		//nested inner table for product details
		//adding product details
		//if po is amended
		PdfPTable productTable = null;
		PurchaseOrderView oldPO = null;
		if(!StringUtils.isEmpty(po.getIsAmendedFlag())) {
			if(po.getIsAmendedFlag().equalsIgnoreCase("YES")) {
				//productTable = productListTable(po,"Amendment Details");
				//oldPO = purchaseOrderDao.getPurchaseOrderInfo(po.getOldPoId());
				oldPO = getPurchaseOrderInfo(po.getOldPoId());
			} else {
				productTable = productListTable(po,null);
			}
		}
		
		if(oldPO != null)
			productTable = productListTable(po,"Amendment Details");
		else
			productTable = productListTable(po,null);

		cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(3);
		cell.addElement(productTable);
		table.addCell(cell);
		
		//------
		//5th row 1st column
		//total excluding taxes
		cell = new PdfPCell(new Phrase("Total Excluding Taxes", FONT_BODY));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(2);
		table.addCell(cell);
		
		//5th row 2nd column
		cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", po.getTotalWithoutTaxes()))), FONT_BODY));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		
		//6th row 1st column
		//total taxes
		cell = new PdfPCell(new Phrase("Total Taxes", FONT_BODY));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(2);
		table.addCell(cell);
		
		//6th row 2nd column
		cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", po.getTotalTaxes()))), FONT_BODY));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		
		//total discount for non INR po
		if(!po.getCurrency().equalsIgnoreCase("INR")) {
			cell = new PdfPCell(new Phrase("Discount", FONT_BODY));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setColspan(2);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", po.getDiscountAmt()))), FONT_BODY));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
		}
		
		//grand total
		cell = new PdfPCell(new Phrase("Grand Total", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", po.getGrandTotal()))), FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);

		//amount in words
		String amountInWords = "";
		p = new Phrase();
		p.add(new Chunk("Amount in words.: ", FONT_BODY));
		if(!po.getCurrency().equalsIgnoreCase("INR")) {
			String formattedValue = df.format(po.getGrandTotal());
			if(formattedValue.contains(".")) {
				String grandTotalArray[] = formattedValue.split("\\.");
				//System.out.println("Grand Total Array :: "+grandTotalArray[0]+" :: "+grandTotalArray[1]);
				amountInWords += NumberToWordsConverter.convertNonInr(Long.parseLong(grandTotalArray[0]));
				if(Integer.parseInt(grandTotalArray[1]) > 0) {
					amountInWords += " And ";
					amountInWords += NumberToWordsConverter.convertNonInr(Long.parseLong(grandTotalArray[1]));
				}
				amountInWords += " Only.";
			} else {
				amountInWords += NumberToWordsConverter.convertNonInr(Long.parseLong(formattedValue));
				amountInWords += " Only.";
			}
		} else {
			amountInWords += NumberToWordsConverter.convert(new Double(po.getGrandTotal()).longValue());
			amountInWords += " Only.";
		}
		p.add(new Chunk(amountInWords, FONT_TABLE_HEADER));
		cell = new PdfPCell(p);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(3);
		table.addCell(cell);
		
		//adding product details of old po
		//if po is amended
		PdfPTable oldProductTable = null;
		if(oldPO != null) {
			oldProductTable = productListTable(oldPO,"Old PO Details");

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setColspan(3);
			cell.addElement(oldProductTable);
			table.addCell(cell);
			
			//------
			//total excluding taxes
			cell = new PdfPCell(new Phrase("Total Excluding Taxes", FONT_BODY));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setColspan(2);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", oldPO.getTotalWithoutTaxes()))), FONT_BODY));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			//total taxes
			cell = new PdfPCell(new Phrase("Total Taxes", FONT_BODY));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setColspan(2);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", oldPO.getTotalTaxes()))), FONT_BODY));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			//total discount for non INR po
			if(!oldPO.getCurrency().equalsIgnoreCase("INR")) {
				cell = new PdfPCell(new Phrase("Discount", FONT_BODY));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setColspan(2);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", oldPO.getDiscountAmt()))), FONT_BODY));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			}
			
			//grand total
			cell = new PdfPCell(new Phrase("Grand Total", FONT_TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setColspan(2);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", oldPO.getGrandTotal()))), FONT_TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);

			//amount in words
			String oldAmountInWords = "";
			p = new Phrase();
			p.add(new Chunk("Amount in words.: ", FONT_BODY));
			if(!oldPO.getCurrency().equalsIgnoreCase("INR")) {
				String formattedValue = df.format(oldPO.getGrandTotal());
				if(formattedValue.contains(".")) {
					String grandTotalArray[] = formattedValue.split("\\.");
					//System.out.println("Grand Total Array :: "+grandTotalArray[0]+" :: "+grandTotalArray[1]);
					oldAmountInWords += NumberToWordsConverter.convert(Long.parseLong(grandTotalArray[0]));
					if(Integer.parseInt(grandTotalArray[1]) > 0) {
						amountInWords += " And ";
						amountInWords += NumberToWordsConverter.convert(Long.parseLong(grandTotalArray[1]));
					}
					oldAmountInWords += " Only.";
				} else {
					oldAmountInWords += NumberToWordsConverter.convert(Long.parseLong(formattedValue));
					oldAmountInWords += " Only.";
				}
			} else {
				oldAmountInWords += NumberToWordsConverter.convert(new Double(oldPO.getGrandTotal()).longValue());
				oldAmountInWords += " Only.";
			}
			p.add(new Chunk(oldAmountInWords, FONT_TABLE_HEADER));
			cell = new PdfPCell(p);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setColspan(3);
			table.addCell(cell);
		}//if end
		
		
		//terms and conditions
		p = new Phrase();
		p.add(new Chunk("Terms & Conditions.:\n", FONT_TABLE_HEADER));
		p.add(new Chunk(po.getTermsConditions(), FONT_BODY));
		cell = new PdfPCell(p);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(3);
		table.addCell(cell);
		
		//additional terms and conditions
		if(!StringUtils.isEmpty(po.getAdditionalTerms())) {
			p = new Phrase();
			p.add(new Chunk("Annexure Terms & Conditions.:\n", FONT_TABLE_HEADER));
			//removing any html tags
			String str = po.getAdditionalTerms();
			str = str.replaceAll("\\<.*?\\>", "");
	        str = str.replaceAll("&nbsp;", " ");
	        str = str.replaceAll("&amp;", " & ");
	        
			p.add(new Chunk(str, FONT_BODY));
			cell = new PdfPCell(p);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setColspan(3);
			table.addCell(cell);
		}
		
		//Authorized Signatory
		p = new Phrase();
		p.add(new Chunk("E. & O. E.", FONT_BODY));
		p.add(new Chunk("\nFor " + po.getSuppName() + ",", FONT_TABLE_HEADER));
		p.add(new Chunk("\nDate.:", FONT_BODY));
		p.add(new Chunk("\n\n\n\n\nAuthorized Signatory", FONT_BODY));
		cell = new PdfPCell(p);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setColspan(2);
		table.addCell(cell);
		
		//Organisation, Authorized Signatory, PO Head Name, Designation
		p = new Phrase();
		/*p.add(new Chunk("For ", FONT_TABLE_HEADER));
		if(!StringUtils.isEmpty(po.getOrganisationName()))
			p.add(new Chunk(po.getOrganisationName() + ",", FONT_TABLE_HEADER));
		p.add(new Chunk("\n\n\n\n\n\n\nAuthorized Signatory", FONT_BODY));
		p.add(new Chunk("\n" + po.getPoheadname(), FONT_TABLE_HEADER));
		p.add(new Chunk("\n" + po.getPoheaddesignation(), FONT_BODY));*/
		cell = new PdfPCell(p);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		table.addCell(cell);
		
		//digitally generated line
		cell = new PdfPCell(new Phrase("**This is an electronically generated Purchase Order. No signatures are required.**", FONT_BODY));
		cell.setFixedHeight(25);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setColspan(3);
		cell.setBorder(0);
		table.addCell(cell);
		
		reportBody.add(table);
		document.add(reportBody);

		document.close();
		System.out.println(":::::::::::::::::::::::: Printing Close :::::::::::::::::::::::::::");
		if (flag) {
			//String file1 = messages.getProperty(Constant.PDF_WRITE_DIR_RE.getValue())+ po.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_")+ File.separator + po.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") +"_draft_"+ count+ ".pdf";
			String file1 = env.getProperty("PDFWriteDirRelative")+ po.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_")+ File.separator + po.getPurchaseOrderNo().replaceAll("[\\/:*?\"<>|]", "_") +"_draft_"+ count+ ".pdf";
			return file1;
		} else {
			return file;
		}
	}

	public void process(Table table, String line, PdfFont font, boolean isHeader) {
		StringTokenizer tokenizer = new StringTokenizer(line, ";");
		while (tokenizer.hasMoreTokens()) {
			if (isHeader) {
				table.addHeaderCell(
						new Cell().add(new com.itextpdf.layout.element.Paragraph(tokenizer.nextToken()).setFont(font)));

			} else {
				table.addCell(
						new Cell().add(new com.itextpdf.layout.element.Paragraph(tokenizer.nextToken()).setFont(font)));
			}
		}
	}

	public PdfPTable productListTable(PurchaseOrderView po, String amendHeading) {
		PdfPTable table = null;
		int table_size = 0;

		if(po.getCurrency().equalsIgnoreCase("INR")){
			if (po.getSuppState().toUpperCase().equalsIgnoreCase(po.getBuyBillToState().toUpperCase())) {
				float[] columnWidths = { 3, 8, 5, 4, 5, 4, 3, 5, 5, 5 }; // 10 - cgst,sgst
				table = new PdfPTable(columnWidths);
				table_size = 10;
			} else {
				float[] columnWidths = { 3, 8, 5, 4, 5, 4, 3, 6, 6 }; // 9 - igst
				table = new PdfPTable(columnWidths);
				table_size = 9;
			}
		} else {
			float[] columnWidths = { 3, 8, 5, 4, 5, 4, 3, 6, 6 }; // 9 - non INR tax
			table = new PdfPTable(columnWidths);
			table_size = 9;
		}
		

		table.setWidthPercentage(100); // set table with 100% (full page)
		table.getDefaultCell().setUseAscender(true);
		
		if(!StringUtils.isEmpty(amendHeading)) {
			PdfPCell cell = new PdfPCell(new Phrase(amendHeading, FONT_TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//cell.setBackgroundColor(new GrayColor(0.90f));
			//cell.setFixedHeight(25);
			cell.setColspan(table_size);
			// cell.setPadding(5);
			// cell.setBorder(NO_BORDER);
			table.addCell(cell);
		}

		PdfPCell cell = new PdfPCell(new Phrase("Sr.No.", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.setBackgroundColor(new GrayColor(0.90f));
		cell.setFixedHeight(15);
		// cell.setPadding(5);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Product", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.setBackgroundColor(new GrayColor(0.90f));
		cell.setFixedHeight(15);
		// cell.setPadding(5);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("HSN/SAC", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.setBackgroundColor(new GrayColor(0.90f));
		cell.setFixedHeight(15);
		// cell.setPadding(5);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Rate", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.setBackgroundColor(new GrayColor(0.90f));
		cell.setFixedHeight(15);
		// cell.setPadding(5);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Qty(UOM)", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.setBackgroundColor(new GrayColor(0.90f));
		cell.setFixedHeight(15);
		// cell.setPadding(5);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Amount", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.setBackgroundColor(new GrayColor(0.90f));
		cell.setFixedHeight(15);
		// cell.setPadding(5);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Disc.", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.setBackgroundColor(new GrayColor(0.90f));
		cell.setFixedHeight(15);
		// cell.setPadding(5);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		if(po.getCurrency().equalsIgnoreCase("INR")){
			if (po.getSuppState().toUpperCase().equalsIgnoreCase(po.getBuyBillToState().toUpperCase())) {
				cell = new PdfPCell(new Phrase("CGST", FONT_TABLE_HEADER));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				//cell.setBackgroundColor(new GrayColor(0.90f));
				cell.setFixedHeight(15);
				// cell.setPadding(5);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("SGST", FONT_TABLE_HEADER));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				//cell.setBackgroundColor(new GrayColor(0.90f));
				cell.setFixedHeight(15);
				// cell.setPadding(5);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);
			} else {
				cell = new PdfPCell(new Phrase("IGST", FONT_TABLE_HEADER));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				//cell.setBackgroundColor(new GrayColor(0.90f));
				cell.setFixedHeight(15);
				// cell.setPadding(5);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);
			}
		} else {
			cell = new PdfPCell(new Phrase("Tax", FONT_TABLE_HEADER));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			//cell.setBackgroundColor(new GrayColor(0.90f));
			cell.setFixedHeight(15);
			// cell.setPadding(5);
			// cell.setBorder(NO_BORDER);
			table.addCell(cell);
		}
		

		cell = new PdfPCell(new Phrase("Final Amt", FONT_TABLE_HEADER));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.setBackgroundColor(new GrayColor(0.90f));
		cell.setFixedHeight(15);
		// cell.setPadding(5);
		// cell.setBorder(NO_BORDER);
		table.addCell(cell);

		// Adding data into table
		List<ProductDetailsView> productList = po.getProductDetailsList();
		int count = 0;
		if (productList.size() > 0) {
			for (ProductDetailsView obj : productList) {
				// sr.no.
				cell = new PdfPCell(new Phrase("" + ++count, PRODUCT_FONT));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				// cell.setPadding(5);
				table.addCell(cell);

				// product name
				if (obj.getProductDesc() != null && obj.getDescription() == null) {
					cell = new PdfPCell(new Phrase(obj.getProduct() + " (" + obj.getProductDesc() + ")", PRODUCT_FONT));
				} else if (obj.getProductDesc() == null && obj.getDescription() != null) {
					cell = new PdfPCell(new Phrase(obj.getProduct() + " (" + obj.getDescription() + ")", PRODUCT_FONT));
				} else if (obj.getProductDesc() != null && obj.getDescription() != null) {
					cell = new PdfPCell(new Phrase(obj.getProduct() + " (" + obj.getProductDesc() + " - " + obj.getDescription() + ")", PRODUCT_FONT));
				} else {
					cell = new PdfPCell(new Phrase(obj.getProduct(), PRODUCT_FONT));
				}
				// cell.setFixedHeight(28);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);

				// hsn code
				cell = new PdfPCell(new Phrase(obj.getHsnId(), PRODUCT_FONT));
				// cell.setFixedHeight(28);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);

				// rate
				cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", obj.getRate()))), PRODUCT_FONT));
				// cell.setFixedHeight(28);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);

				// quantity
				cell = new PdfPCell(new Phrase(String.valueOf(obj.getQuantity()), PRODUCT_FONT));
				// cell.setFixedHeight(28);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);

				// amount
				cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", obj.getAmount()))), PRODUCT_FONT));
				// cell.setFixedHeight(28);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);

				// discount
				cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", obj.getTotalDiscount()))), PRODUCT_FONT));
				// cell.setFixedHeight(28);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);

				if(po.getCurrency().equalsIgnoreCase("INR")){
					// cgst, sgst or igst
					if (po.getSuppState().toUpperCase().equalsIgnoreCase(po.getBuyBillToState().toUpperCase())) {
						cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", obj.getCgstAmount()))) + "("	+ obj.getCgst() + "%)", PRODUCT_FONT));
						// cell.setFixedHeight(28);
						// cell.setBorder(NO_BORDER);
						table.addCell(cell);

						cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", obj.getSgstAmount()))) + "(" + obj.getSgst() + "%)", PRODUCT_FONT));
						// cell.setFixedHeight(28);
						// cell.setBorder(NO_BORDER);
						table.addCell(cell);
					} else {
						cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", obj.getIgstAmount()))) + "("	+ obj.getIgst() + "%)", PRODUCT_FONT));
						// cell.setFixedHeight(28);
						// cell.setBorder(NO_BORDER);
						table.addCell(cell);
					}
				} else {
					cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", obj.getIgstAmount()))) + "("	+ obj.getIgst() + "%)", PRODUCT_FONT));
					// cell.setFixedHeight(28);
					// cell.setBorder(NO_BORDER);
					table.addCell(cell);
				}
				

				// final amount
				cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(String.format("%.2f", obj.getFinalAmount()))), PRODUCT_FONT));
				//cell = new PdfPCell(new Phrase(String.valueOf(new BigDecimal(obj.getFinalAmount())), FONT_BODY));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				// cell.setFixedHeight(28);
				// cell.setBorder(NO_BORDER);
				table.addCell(cell);
			}
		}

		return table;
	}
	
	public PdfPTable getHeaderData() throws BadElementException, MalformedURLException, IOException {
		String imageFile = env.getProperty("AurionproLetterHead");
		com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(imageFile);
        float[] headerColumnWidths = { 5, 5, 5, 5, 5, 5 };
		PdfPTable headerTable = new PdfPTable(headerColumnWidths);
		headerTable.setTotalWidth(556);
		//footerTable.setWidthPercentage(100);

		PdfPCell cell2 = new PdfPCell(img, true);
		cell2.setBorder(0);
		headerTable.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setBorder(0);
        headerTable.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setBorder(0);
        headerTable.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setBorder(0);
        headerTable.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setBorder(0);
        headerTable.addCell(cell2);
        
        cell2 = new PdfPCell();
        cell2.setBorder(0);
        headerTable.addCell(cell2);
        /*cell2 = new PdfPCell(new Phrase("Page "+writer.getPageNumber(), FOOTER_FONT));
		cell2.setBorder(0);
		header.addCell(cell2);*/

		return headerTable;
	}
	
	public PdfPTable getFooterData() {
		//float[] footerColumnWidths = { 6, 8, 2, 5, 7 };
		float[] footerColumnWidths = { 4, 5, 1, 3, 4 };
		PdfPTable footerTable = new PdfPTable(footerColumnWidths);
		footerTable.setTotalWidth(556);
		Phrase p1 = new Phrase();
		p1.add(new Chunk("Aurionpro Solutions Limited", FOOTER_FONT_BOLD));
		
		Phrase p2 = new Phrase();
		p2.add(new Chunk("Synergia IT Park, Plot No. R-270,\nT.T.C Industrial Estate, Gautam Nagar,\nNear Rabale Police Station, Rabale,\nNavi Mumbai - 400 701. MH - INDIA", FOOTER_FONT));
		
		Phrase p3 = new Phrase();
		p3.add(new Chunk("phone\nfax", FOOTER_FONT));
		
		Phrase p4 = new Phrase();
		p4.add(new Chunk("+91 22 4040 7070\n+91 22 4040 7080", FOOTER_FONT));
		
		Phrase p5 = new Phrase();
		p5.add(new Chunk("info@aurionpro.com\n", FOOTER_FONT));
		p5.add(new Chunk("www.aurionpro.com\n", FOOTER_FONT_BOLD));
		p5.add(new Chunk("CIN L99999MH1997PLC111637", FOOTER_FONT));
		
        PdfPCell cell1 = new PdfPCell(p1);
        cell1.setBorder(0);
        cell1.enableBorderSide(com.itextpdf.text.Rectangle.TOP);
        footerTable.addCell(cell1);
        
        cell1 = new PdfPCell(p2);
        cell1.setBorder(0);
        cell1.enableBorderSide(com.itextpdf.text.Rectangle.TOP);
        footerTable.addCell(cell1);
        
        cell1 = new PdfPCell(p3);
        cell1.setBorder(0);
        cell1.enableBorderSide(com.itextpdf.text.Rectangle.TOP);
        footerTable.addCell(cell1);
        
        cell1 = new PdfPCell(p4);
        cell1.setBorder(0);
        cell1.enableBorderSide(com.itextpdf.text.Rectangle.TOP);
        footerTable.addCell(cell1);
        
        cell1 = new PdfPCell(p5);
        cell1.setBorder(0);
        cell1.enableBorderSide(com.itextpdf.text.Rectangle.TOP);
        footerTable.addCell(cell1);

		return footerTable;
	}
	
	public static void copyFile(File src, File dest) throws IOException{
		InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);

            // buffer size 1K
            byte[] buf = new byte[1024];

            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
        } finally {
            is.close();
            os.close();
        }
    }
	
	public String writePdf(long id) throws DocumentException {
		PurchaseOrderView pom = getPurchaseOrderInfo(id);
		String attachmentPath = "";
		try {
			attachmentPath = writePDF(pom, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return attachmentPath;
	}
	
	public List<SelectionList> getListOfProductsInRc(long id) {
		RateContractMaster rc = new RateContractMaster();
		rc.setEntityId(id);
		return purchaseOrderRepo.getListOfProductsInRc(rc);
	}

	@Autowired
	SendingMailService sendingMailService;
	
	public void sendPoMailToVendor(long poId) {
		Optional<PurchaseOrderView> po = purchaseOrderViewRepo.findById(poId);
		if(po.isPresent()) {
			String msgSubject = appProperties.getPoVendorEmailSubject;
			msgSubject = msgSubject.replace("PO", po.get().getPurchaseOrderNo());
			
			String msgBody = appProperties.getPoVendorEmailBody;
			if(!StringUtils.isEmpty(po.get().getSupplieremail())) {
				if(!StringUtils.isEmpty(po.get().getVerfiedPOAttachment()))
					sendingMailService.sendEmail(po.get().getSupplieremail(), appProperties.getPoVendorEmailCcList, appProperties.getPoVendorEmailBccList, msgSubject, msgBody, po.get().getVerfiedPOAttachment());
				else
					sendingMailService.sendEmail(po.get().getSupplieremail(), appProperties.getPoVendorEmailCcList, appProperties.getPoVendorEmailBccList, msgSubject, msgBody, null);
			} else {
				return;
			}
			
			//for updating po pushed status and date
			purchaseOrderRepo.updateMailSentStatus(poId, new Date().getTime(), MyPrincipal.getMyProfile().getUsername());
		}
	}

	public List<SelectionList> getAllPoListFromGrn() {
		return purchaseOrderRepo.getAllPoListFromGrn();
	}

	public PurchaseOrderMaster cancelVerificationOfPoById(long id) {
		PurchaseOrderMaster po = purchaseOrderRepo.findById(id).get();
		if(po!=null) {
			po.setVerify(null);
			po.setVerifyDate(null);
			po.setMailSentDate(null);
			po.setMailSentBy(null);
			purchaseOrderRepo.save(po);
		}
		return po;
	}

	/*
	public PageImpl<PurchaseOrderListView> getallpobyfilterByList(PurchaseOrderListView poRequest, Integer page,
			Integer size) {
		long step1 = System.currentTimeMillis();
		System.out.println("Step1:: "+step1);
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<PurchaseOrderListView> poEx = Example.of(poRequest,em);
		List<PurchaseOrderListView> list =purchaseOrderListViewRepo.findAll(poEx);
		
		long step2 = System.currentTimeMillis();
		System.out.println("Step2:: "+step2);
	    if(poRequest.getFromDate()!=null && poRequest.getToDate()!=null) {
			if(poRequest.getFromDate()>0 && poRequest.getToDate()>0) {
				list = list.stream().filter(p -> (p.getOrderDate() >= poRequest.getFromDate()) && (p.getOrderDate() <= poRequest.getToDate())).collect(Collectors.toList());
			}
		}
	    
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
        List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	    List<PurchaseOrderListView> filteredProject = list.stream().filter(f->projectId.contains(f.getAccountName())).collect(Collectors.toList());
	       
	    if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    {
	    	Pageable paging = PageRequest.of(page-1, size);
	    	int start =  (int) paging.getOffset();
	    	int end = Math.min((start + paging.getPageSize()), filteredProject.size());
	    	return new PageImpl<PurchaseOrderListView>(filteredProject.subList(start, end), paging, filteredProject.size());
	    }
	    else
	    {long step3 = System.currentTimeMillis();
		System.out.println("Step3:: "+step3);
	    	return new PageImpl<PurchaseOrderListView>(filteredProject);
	    }
	}

	public PageImpl<PurchaseOrderView> getallpobyfilterbysp(PurchaseOrderView poRequest, Integer page, Integer size) {
		PurchaseOrderView purchaseOrder = castFilterValues(poRequest);
		
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		long step1 = System.currentTimeMillis();
		System.out.println("Step1:: "+step1);
		List<PurchaseOrderView> poList= purchaseOrderViewRepo.getallpobyfilterbysp(
				purchaseOrder.getPurchaseOrderNo(),
				purchaseOrder.getAccountName(),
				purchaseOrder.getOrganisationId(),
				purchaseOrder.getSupplierName(),
				purchaseOrder.getDepartment(),
				purchaseOrder.getFromDate(),
				purchaseOrder.getToDate(),
				profileId);
		
		long step2 = System.currentTimeMillis();
		System.out.println("Step2:: "+step2);
		
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	           Pageable paging = PageRequest.of(page-1, size);
			   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), poList.size());
	     		
	           return new PageImpl<PurchaseOrderView>(poList.subList(start, end), paging, poList.size());
	      
	    	}
	    	else
	    	{
	    		long step3 = System.currentTimeMillis();
	    		System.out.println("Step3:: "+step3);
	    		return new PageImpl<>(poList);
	    	}
	}
*/
	private PurchaseOrderView castFilterValues(PurchaseOrderView poRequest) {
		PurchaseOrderView pRequest = new PurchaseOrderView();
		long value = 0;
	
		if (poRequest.getPurchaseOrderNo() == null)
		{
			pRequest.setPurchaseOrderNo("");
		}
		else
		{
			pRequest.setPurchaseOrderNo(poRequest.getPurchaseOrderNo());
		}
		
		if (poRequest.getAccountName() == null)
		{
			pRequest.setAccountName(value);
		}
		else
		{
			pRequest.setAccountName(poRequest.getAccountName());
		}
		
		if (poRequest.getDepartment() == null)
		{
			pRequest.setDepartment(value);
		}
		else
		{
			pRequest.setDepartment(poRequest.getDepartment());
		}
		
		if (poRequest.getSupplierName() == null)
		{
			pRequest.setSupplierName(value);
		}
		else
		{
			pRequest.setSupplierName(poRequest.getSupplierName());
		}
		if (poRequest.getOrganisationId() == null)
		{
			pRequest.setOrganisationId(value);
		}
		else
		{
			pRequest.setOrganisationId(poRequest.getOrganisationId());
		}
		
		if (poRequest.getFromDate() == null)
		{
			pRequest.setFromDate(value);
		}
		else
		{
			pRequest.setFromDate(poRequest.getFromDate());
		}
		
		if (poRequest.getToDate() == null)
		{
			pRequest.setToDate(value);
		}
		else
		{
			pRequest.setToDate(poRequest.getToDate());
		}
		
		if (poRequest.getApprovalStatus() == null)
		{
			pRequest.setApprovalStatus("");
		}
		else
		{
			pRequest.setApprovalStatus(poRequest.getApprovalStatus());
		}
		
			
		return pRequest;

	}

	public PageImpl<PurchaseOrderListView> getallpolistbyfilterbysp(PurchaseOrderView poRequest, Integer page,
			Integer size) {
		PurchaseOrderView purchaseOrder = castFilterValues(poRequest);
		
		Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
		
		List<PurchaseOrderListView> poList= purchaseOrderListViewRepo.getallpobyfilterbysp(
				purchaseOrder.getPurchaseOrderNo(),
				purchaseOrder.getAccountName(),
				purchaseOrder.getOrganisationId(),
				purchaseOrder.getSupplierName(),
				purchaseOrder.getDepartment(),
				purchaseOrder.getFromDate(),
				purchaseOrder.getToDate(),
				profileId,purchaseOrder.getApprovalStatus());
		
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    	{
	           Pageable paging = PageRequest.of(page-1, size);
			   int start =  (int) paging.getOffset();
	           int end = Math.min((start + paging.getPageSize()), poList.size());
	     		
	           return new PageImpl<PurchaseOrderListView>(poList.subList(start, end), paging, poList.size());
	      
	    	}
	    	else
	    	{
	    		 return new PageImpl<>(poList);
	    	}
	}
}