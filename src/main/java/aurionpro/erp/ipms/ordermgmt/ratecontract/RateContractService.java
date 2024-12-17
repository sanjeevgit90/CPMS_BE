package aurionpro.erp.ipms.ordermgmt.ratecontract;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Lob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.ordermgmt.rcproductdetails.RcProductDetails;
import aurionpro.erp.ipms.ordermgmt.rcproductdetails.RcProductDetailsService;
import aurionpro.erp.ipms.ordermgmt.rcproductdetails.RcProductDetailsView;
import aurionpro.erp.ipms.ordermgmt.rcproductdetails.RcProductDetailsViewRepository;
import aurionpro.erp.ipms.utility.MyPrincipal;
import aurionpro.erp.ipms.utility.ProjectUtil;
import aurionpro.erp.ipms.utility.pdf.NumberToWordsConverter;
import aurionpro.erp.ipms.vendor.addressmaster.AddressMaster;
import aurionpro.erp.ipms.vendor.addressmaster.AddressMasterRepository;
import aurionpro.erp.ipms.vendor.gstmaster.GstMaster;
import aurionpro.erp.ipms.vendor.gstmaster.GstMasterRepository;
import aurionpro.erp.ipms.vendor.partymaster.PartyMaster;
import aurionpro.erp.ipms.vendor.partymaster.PartyMasterRepository;

@Service
public class RateContractService {

	@Autowired
	RateContractRepository rateContractRepo;
	
	@Autowired
	PartyMasterRepository partyMasterRepo;
	
	@Autowired
	AddressMasterRepository addressMasterRepo;
	
	@Autowired
	GstMasterRepository gstMasterRepo;
	
	@Autowired
	RateContractHistoryRepository purchaseOrderHistoryRepo;
	
	@Autowired
	RateContractViewRepository rateContractViewRepo;
	
	@Autowired
	RcProductDetailsService productDetailsService;
	
	@Autowired
	RcProductDetailsViewRepository productDetailsViewRepo;
	
    @Autowired
    ProjectUtil projectFilterService;

	public RateContractMaster createRateContract(RateContractMaster rateContractRequest){
        validateRequest(rateContractRequest);
        List<RateContractMaster> tcheck=rateContractRepo.findByRateContractNo(rateContractRequest.getRateContractNo());
        if (tcheck.size()>0){
            throw new RuntimeException("Rate contract entity already exists.");
        }
        rateContractRequest.setApprovalStatus("PENDING");
        //rateContractRequest.setIsAmendedFlag("NO");
        
        RateContractMaster saveObj = rateContractRepo.save(rateContractRequest);
        
        if(saveObj != null) {
        	saveRcHistoryDetails(saveObj, "SAVE");
        }
        return saveObj;
    }
	
	public RateContractMaster updateRateContract(long orderId, RateContractMaster rateContractRequest){
        if (orderId!=rateContractRequest.getEntityId())
        	throw new RuntimeException("Request mismatch");

        validateRequest(rateContractRequest);
        
        Optional<RateContractMaster> orderTemp=rateContractRepo.findById(orderId);

        if (orderTemp==null) {
        	throw new RuntimeException("Rate contract entity does not exists.");
        }
        /*if (!orderTemp.get().getRateContractNo().equalsIgnoreCase(rateContractRequest.getRateContractNo())) {
        	throw new EntityValidationException("Rate contract entity is invalid", "Rate contract number mismatch.");
        }*/
        
        RateContractMaster updateObj = rateContractRepo.save(rateContractRequest);
        if(updateObj != null) {
        	saveRcHistoryDetails(updateObj, "UPDATE");
        	productDetailsService.checkAllProductCalculations(updateObj);
        	updateGrandTotal(updateObj);
        }
        return updateObj;
    }
	
	public List<RateContractMaster> getAllRateContracts(){
        return rateContractRepo.findAll();
    }
	
	public Optional<RateContractMaster> getOrderById(long id){
        return rateContractRepo.findById(id);
    }
	
	@Lob
	public Optional<RateContractView> getOrderByIdView(long id){
        return rateContractViewRepo.findById(id);
    }
	
	public void deleteOrderById(long id){
		//rateContractRepo.deleteById(id);
		RateContractMaster rc = rateContractRepo.findById(id).get();
		if(!rc.getApprovalStatus().equalsIgnoreCase("PENDING")) {
			throw new RuntimeException("Rate contract cannot be deleted.");
		}
		purchaseOrderHistoryRepo.deleteByRateContract(rc);
		productDetailsService.deleteProductListByRc(rc);
		rateContractRepo.deleteById(id);
    }
	
	public RateContractMaster updateApprovalStatus(long rcId, String status) {
		RateContractMaster rc = rateContractRepo.findById(rcId).get();
		rc.setApprovalStatus(status);
		return rateContractRepo.save(rc);
	}
	
	public List<SelectionList> selectionRcList() {
		Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		return rateContractRepo.selectionRcList(profileId);
	}
	
	public List<SelectionList> selectionApprovedRcList() {
		Long profileId = MyPrincipal.getMyProfile().getUserProfileId();
		return rateContractRepo.selectionApprovedRcList(profileId);
	}
	
	public PageImpl<RateContractView> getAllRcByFilter(RateContractView rcRequest, Integer page, Integer size) {
		ExampleMatcher em = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<RateContractView> poEx = Example.of(rcRequest,em);
		List<RateContractView> list= rateContractViewRepo.findAll(poEx);

		if(rcRequest.getFromDate()!=null && rcRequest.getToDate()!=null) {
			if(rcRequest.getFromDate()>0 && rcRequest.getToDate()>0) {
				list = list.stream().filter(p -> (p.getContractDate() >= rcRequest.getFromDate()) && (p.getContractDate() <= rcRequest.getToDate())).collect(Collectors.toList());
			}
		}
		
	    Long profileId=MyPrincipal.getMyProfile().getUserProfileId();
	    List<Long> projectId = projectFilterService.getProjectsListByProfileId(profileId);
	    List<RateContractView> filteredProject = list.stream().filter(f->projectId.contains(f.getAccountName())).collect(Collectors.toList());
	    		       
	    if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
	    {
	    	Pageable paging = PageRequest.of(page-1, size);
	    	int start =  (int) paging.getOffset();
	    	int end = Math.min((start + paging.getPageSize()), filteredProject.size());
	    	return new PageImpl<RateContractView>(filteredProject.subList(start, end), paging, filteredProject.size());
	    }
	    else
	    {
	    	return new PageImpl<RateContractView>(filteredProject);
	    }
	}
	
	public RateContractView getRateContractInfo(long id) {
		RateContractView po = rateContractViewRepo.findById(id).get();
		po.setAttachments(rateContractRepo.getAttachmentList(id));
		List<RcProductDetailsView> productDetailsList = productDetailsViewRepo.findByRateContract(id);
		po.setRcProductDetailsList(productDetailsList);
		return po;
	}
	
	public RateContractMaster updateGrandTotal(RateContractMaster poRequest) {
		double totalTaxes = 0.0, totalWithoutTaxes = 0.0;
		List<RcProductDetails> prodDetails = productDetailsService.getProductDetailsByRcId(poRequest);
		if (!prodDetails.isEmpty()) {
			for (RcProductDetails obj : prodDetails) {
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
		RateContractMaster rc = rateContractRepo.findById(poRequest.getEntityId()).get();
		rc.setTotalWithoutTaxes(totalWithoutTaxes);
		rc.setTotalTaxes(totalTaxes);
		double grandTotal = totalTaxes + totalWithoutTaxes;
		if (rc.getCurrency().equalsIgnoreCase("INR"))
			rc.setGrandTotal((double)Math.round(grandTotal));
		else
			rc.setGrandTotal(grandTotal);
		
		//for setting amount in words
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumIntegerDigits(15);
		df.setMaximumFractionDigits(2);
		String amountInWords = "";
		if(!rc.getCurrency().equalsIgnoreCase("INR")) {
			String formattedValue = df.format(rc.getMaxLimit());
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
			amountInWords += NumberToWordsConverter.convert(new Double(rc.getMaxLimit()).longValue());
			amountInWords += " Only.";
		}
		rc.setAmountInWords(amountInWords);
		//amount in words

		//po.setWorkflowName(poRequest.getWorkflowName());
		
		return rateContractRepo.save(rc);
	}
	
	public RateContractMaster generateDuplicateOrder(RateContractView request) {
		RateContractView entity = getRateContractInfo(request.getEntityId());
		
		RateContractMaster obj = new RateContractMaster();
		//obj.setId(UUID.randomUUID().toString());
		obj.setRateContractNo(entity.getRateContractNo().concat("-1"));
		obj.setDepartment(entity.getDepartment());
		obj.setAccountName(entity.getAccountName());
		obj.setContractType(entity.getContractType());
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
		obj.setCreatedBy(entity.getCreatedBy());
		obj.setIsHistoricData(entity.getIsHistoricData());
		obj.setOrganisationId(entity.getOrganisationId());
		
		obj.setMaxLimit(entity.getMaxLimit());
		obj.setGrandTotal(entity.getGrandTotal());
		obj.setTotalTaxes(entity.getTotalTaxes());
		obj.setTotalWithoutTaxes(entity.getTotalWithoutTaxes());
		obj.setValidTill(entity.getValidTill());
		obj.setContractDate(entity.getContractDate());
		
		//obj.setPoheadname(entity.getPoheadname());
		//obj.setPoheaddesignation(entity.getPoheaddesignation());
		
		RateContractMaster saveObj = createRateContract(obj);
		
		try{
			if(entity.getRcProductDetailsList()!=null) {
				List<RcProductDetailsView> prodList = entity.getRcProductDetailsList();
				for(RcProductDetailsView prodObj : prodList) {
					RcProductDetails prodReq = new RcProductDetails();
					prodReq.setRateContract(saveObj);
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
					prodReq.setIsHistoricData(saveObj.getIsHistoricData());
					productDetailsService.addProductDetail(prodReq);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return saveObj;
	}

	private boolean validateRequest(RateContractMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		//validations
		if(StringUtils.isEmpty(request.getRateContractNo()))
			errorlist.add("Rate contract no. cannot be null.");
		if(request.getContractDate() == 0)
			errorlist.add("Contract date cannot be null.");
		if(request.getValidTill() == 0)
			errorlist.add("Valid till cannot be null.");
		if(StringUtils.isEmpty(request.getDepartment()))
			errorlist.add("Department cannot be null.");
		if(StringUtils.isEmpty(request.getAccountName()))
			errorlist.add("Project cannot be null.");
		
		if(StringUtils.isEmpty(request.getOrganisationId()))
			errorlist.add("Organisation cannot be null.");
		/*JKDEntityAuditWithGuid organizationId
		if(StringUtils.isEmpty(request.getOrganizationId()))
			errorlist.add("Organisation cannot be null.");*/
		
		if(StringUtils.isEmpty(request.getContractType()))
			errorlist.add("Contract type cannot be null.");
		if(StringUtils.isEmpty(request.getModeOfPayment()))
			errorlist.add("Payment term cannot be null.");
		if(StringUtils.isEmpty(request.getDispatchThrough()))
			errorlist.add("Dispatch through cannot be null.");
		if(StringUtils.isEmpty(request.getCurrency()))
			errorlist.add("Currency cannot be null.");
		if(StringUtils.isEmpty(request.getDeliveryTerm()))
			errorlist.add("Delivery term cannot be null.");
		if(request.getMaxLimit() == 0)
			errorlist.add("Max limit cannot be null.");
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
			throw new EntityValidationException("Rate contract entity is invalid", errorlist);
		}
        return true;
    }

	//---------------for history details table --------------------------------
	public RateContractHistoryDetails saveRcHistoryDetails(RateContractMaster request, String operation) {
		// for supplier details
		RateContractHistoryDetails poHistory = null;
		if(operation.equalsIgnoreCase("SAVE")) {
			poHistory = new RateContractHistoryDetails();
			poHistory.setRateContractMaster(request);
		}
		else if(operation.equalsIgnoreCase("UPDATE")) {
			poHistory = purchaseOrderHistoryRepo.findByRateContractMaster(request);
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

	//this update will never be invoked, because add and update are done in saveRcHistoryDetails().
	public RateContractHistoryDetails updateRcHistoryDetails(long orderId, RateContractHistoryDetails request) {
		if (orderId!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");

        //validated();
        
        Optional<RateContractHistoryDetails> orderTemp=purchaseOrderHistoryRepo.findById(orderId);

        if (orderTemp==null) {
        	throw new RuntimeException("Rate contract history entity does not exists.");
        }
        
        return purchaseOrderHistoryRepo.save(request);
	}

	public void deleteOrderHistoryById(long id) {
		purchaseOrderHistoryRepo.deleteById(id);
	}

	public Optional<RateContractHistoryDetails> getOrderHistoryById(long id) {
		return purchaseOrderHistoryRepo.findById(id);
	}
	//---------------for history details table --------------------------------
}