package aurionpro.erp.ipms.ordermgmt.purchase;

import java.util.List;
import java.util.Optional;

import javax.persistence.Lob;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.DocumentException;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.openbravo.openbravodetails.OpenBravoResponse;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/purchaseorder")
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
    FileUploadService uploadService;
	
	@PreAuthorize("hasAnyAuthority('Purchase_Order_ADD','MOB_PurchaseOrder_ADD')")   
	@PostMapping("/addpurchaseorder")
    public PurchaseOrderMaster createPurchaseOrder(@RequestBody PurchaseOrderMaster purchaseOrderMasterRequest){
        return purchaseOrderService.createPurchaseOrder(purchaseOrderMasterRequest);
    }
	
	@PreAuthorize("hasAnyAuthority('Purchase_Order_EDIT','MOB_PurchaseOrder_EDIT')")   
	@PutMapping("/{orderId}")
    public PurchaseOrderMaster updatePurchaseOrder(@PathVariable(value="orderId") long orderId, @RequestBody PurchaseOrderMaster purchaseOrderMasterRequest){
        return purchaseOrderService.updatePurchaseOrder(orderId, purchaseOrderMasterRequest);
    }
	
	@GetMapping("/getallpurchaseorders")
    public List<PurchaseOrderMaster> getAllPurchaseOrders(){
        return purchaseOrderService.getAllPurchaseOrders();
    }
	
	@GetMapping("/getorderbyid/{id}")
    public Optional<PurchaseOrderMaster> getOrderById(@PathVariable(value="id") long id){
        return purchaseOrderService.getOrderById(id);
    }
	
	@Lob
	@GetMapping("/getorderbyidview/{id}")
    public Optional<PurchaseOrderView> getOrderByIdView(@PathVariable(value="id") long id){
        return purchaseOrderService.getOrderByIdView(id);
    }
	
	@PreAuthorize("hasAnyAuthority('Purchase_Order_EDIT','MOB_PurchaseOrder_EDIT')")   
	@GetMapping("/deleteorderbyid/{id}")
    public void deleteOrderById(@PathVariable(value="id") long id){
		purchaseOrderService.deleteOrderById(id);
    }
	
	
/*	@PostMapping("/getallpobyfilter")
	public PageImpl<PurchaseOrderView> getAllPoByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody PurchaseOrderView poRequest){
		
		PageImpl<PurchaseOrderView> poData =  purchaseOrderService.getAllPoByFilter(poRequest, page,size);
		
		
		return poData;
	}
	
	@PreAuthorize("hasAnyAuthority('Purchase_Order_VIEW','MOB_PurchaseOrder_VIEW')")   
	@PostMapping("/getallpobyfilterByList")
	public PageImpl<PurchaseOrderListView> getallpobyfilterByList(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody PurchaseOrderListView poRequest){
		
		PageImpl<PurchaseOrderListView> poData =  purchaseOrderService.getallpobyfilterByList(poRequest, page,size);
		
		
		return poData;
	}
	
	@PreAuthorize("hasAnyAuthority('Purchase_Order_VIEW','MOB_PurchaseOrder_VIEW')")   
	@PostMapping("/getallpobyfilterbysp")
	public PageImpl<PurchaseOrderView> getallpobyfilterbysp(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody PurchaseOrderView poRequest){
		
		PageImpl<PurchaseOrderView> poData =  purchaseOrderService.getallpobyfilterbysp(poRequest, page,size);
		
		
		return poData;
	}
	
	@PreAuthorize("hasAnyAuthority('Purchase_Order_VIEW','MOB_PurchaseOrder_VIEW')")   
	@PostMapping("/getallpolistbyfilterbysp")
	public PageImpl<PurchaseOrderListView> getallpolistbyfilterbysp(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody PurchaseOrderView poRequest){
		
		PageImpl<PurchaseOrderListView> poData =  purchaseOrderService.getallpolistbyfilterbysp(poRequest, page,size);
		
		
		return poData;
	}
	
	
	*/
	
	@PreAuthorize("hasAnyAuthority('Purchase_Order_VIEW','MOB_PurchaseOrder_VIEW')")   
	@PostMapping("/getallpobyfilter")
	public PageImpl<PurchaseOrderListView> getallpolistbyfilterbysp(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody PurchaseOrderView poRequest){
		
		PageImpl<PurchaseOrderListView> poData =  purchaseOrderService.getallpolistbyfilterbysp(poRequest, page,size);
		
		
		return poData;
	}
	
	@GetMapping("/getpurchaseorderinfo/{id}")
	public PurchaseOrderView getPurchaseOrderInfo(@PathVariable(value="id") long id){
		return purchaseOrderService.getPurchaseOrderInfo(id);
	}
	
	@GetMapping("/poselectionlist")
    public List<SelectionList> getSelectionPoList(){
        return purchaseOrderService.selectionPoList();
    }
	
	@GetMapping("/poapprovedselectionlist")
    public List<SelectionList> getSelectionApprovedPoList(){
        return purchaseOrderService.selectionApprovedPoList();
    }
	
	@PostMapping("/updategrandtotal")
	public PurchaseOrderMaster updateGrandTotal(@RequestBody PurchaseOrderMaster poRequest){
		return purchaseOrderService.updateGrandTotal(poRequest);
	}
	
	@PostMapping("/generateduplicateorder")
	public PurchaseOrderMaster generateDuplicateOrder(@RequestBody PurchaseOrderView poRequest){
		return purchaseOrderService.generateDuplicateOrder(poRequest);
	}
	
	@PostMapping("/updateverifyflag")
	public PurchaseOrderMaster updateVerifyFlag(@RequestBody PurchaseOrderMaster poRequest) throws DocumentException{
		return purchaseOrderService.updateVerifyFlag(poRequest);
	}
	
	@GetMapping("/getlistofproductsinrc/{id}")
	public List<SelectionList> getListOfProductsInRc(@PathVariable(value="id") long id){
		return purchaseOrderService.getListOfProductsInRc(id);
	}
	
    @PostMapping("/uploadpoattachments/{folderName}")
    public FileResponse uploadPoAttachments(@PathVariable(value="folderName") String folderName, @RequestParam("file") MultipartFile file){
        String subFolder = "Purchase Order/Attachments/" + folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PostMapping("/uploadsignedcopy/{folderName}")
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
    	String subFolder = "Purchase Order/Signed Copies/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PostMapping("/uploadannexure/{folderName}")
    public FileResponse uploadAnnexure(@RequestParam("file") MultipartFile file, @PathVariable(value="folderName") String folderName){
    	String subFolder = "Purchase Order/Annexures/"+folderName;
        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @GetMapping("/sendPoMailToVendor/{id}")
	public void sendPoMailToVendor(@PathVariable(value="id") long poId){
		purchaseOrderService.sendPoMailToVendor(poId);
	}
    
    @GetMapping("/getallpolistfromgrn")
    public List<SelectionList> getAllPoListFromGrn(){
        return purchaseOrderService.getAllPoListFromGrn();
    }
    
    @GetMapping("/pushPoToOpenBravo/{id}")
	public OpenBravoResponse pushPoToOpenBravo(@PathVariable(value="id") long poId){
		//purchaseOrderService.pushPoToOpenBravo(poId);
		//JSONObject output = purchaseOrderService.pushPoToOpenBravo(poId);
    	OpenBravoResponse res = new OpenBravoResponse();
		JSONObject response = purchaseOrderService.pushPoToOpenBravo(poId).getJSONObject("response");
		if (Integer.parseInt(response.get("status").toString()) == 0) {
			res.setStatus("SUCCESS");
		} else {
			//res.setStatus("FAILURE");
			//res.setErrorMessage(response.get("error").toString());
			throw new RuntimeException(response.get("error").toString());
		}
		return res;
	}
	
	//---------------for history details table
	@PostMapping("/addpurchaseorderhistorydetails")
    public PurchaseOrderHistoryDetails createPurchaseOrderHistoryDetails(@RequestBody PurchaseOrderMaster request){
		return purchaseOrderService.savePoHistoryDetails(request, "SAVE");
    }
	
	@PutMapping("/updatepurchaseorderhistorydetails/{orderId}")
    public PurchaseOrderHistoryDetails updatePurchaseOrderHistoryDetails(@PathVariable(value="orderId") long orderId, @RequestBody PurchaseOrderHistoryDetails request){
        return purchaseOrderService.updatePurchaseOrderHistoryDetails(orderId, request);
    }
	
	@GetMapping("/getorderhistorybyid/{id}")
    public Optional<PurchaseOrderHistoryDetails> getOrderHistoryById(@PathVariable(value="id") long id){
        return purchaseOrderService.getOrderHistoryById(id);
    }
	
	@GetMapping("/deleteorderhistorybyid/{id}")
    public void deleteOrderHistoryById(@PathVariable(value="id") long id){
		purchaseOrderService.deleteOrderHistoryById(id);
    }
	
	@GetMapping("/cancelverificationofpobyid/{id}")//if final pdf is not generated - only for superadmin
    public PurchaseOrderMaster cancelVerificationOfPoById(@PathVariable(value="id") long id){
		return purchaseOrderService.cancelVerificationOfPoById(id);
    }
	
	public boolean validated() {
		return true;
	}
	//-----------------for history details table
}
