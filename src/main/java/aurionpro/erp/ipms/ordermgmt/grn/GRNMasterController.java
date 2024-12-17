package aurionpro.erp.ipms.ordermgmt.grn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetails;
import aurionpro.erp.ipms.ordermgmt.grnproduct.GRNProductDetailsView;
import aurionpro.erp.ipms.ordermgmt.grnproduct.ProductList;
import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/grn")
public class GRNMasterController {

	@Autowired
	GRNMasterService grnMasterService;
	
	@Autowired
    FileUploadService uploadService;
	
	//@PreAuthorize("hasAuthority('GRN_Master_ADD')")
	@PostMapping("/savegrndetails")
	public GRNMaster saveGrnDetails(@Valid @RequestBody GRNMaster request) {
		validateRequest(request);
        List<GRNMaster> tcheck = grnMasterService.findByGrnNumber(request.getGrnNumber());
        if (tcheck.size()>0){
            throw new RuntimeException("GRN entity already exists");
        }
        
        List<GRNProductDetails> prodRequest = request.getProdDetails();
		List<GRNValidationDto> dtoList = grnMasterService.checkingSumOfQuantityValidation(request.getPoNo());
		
		if(dtoList.size()>0){
			for(GRNProductDetails gr : prodRequest){
				for(GRNValidationDto dto : dtoList){
					System.out.println("Req :: " + gr.getProductId() + "\n Dto ::" + dto.getProductId());
					if(gr.getProductId().equals(dto.getProductId())){
						if((gr.getAcceptedQuantity()+dto.getSumOfAllGrn())>dto.getTotalQuantity()){
							throw new RuntimeException("Accepted quantity of "+ gr.getProductName() +" is exceeding the total quantity in the PO.");
						}
					}
				}
			}
		}
        
		GRNMaster grn = grnMasterService.saveGrnDetails(request);
		
		if(grn != null) {
			grnMasterService.saveProductDetails(prodRequest, grn.getEntityId());
		}
		return grn;
	}
	
	//@PreAuthorize("hasAuthority('GRN_Master_EDIT')")
	@PutMapping("/updategrndetails/{id}")
	public GRNMaster updateGrnDetails(@PathVariable(value="id") long id, @Valid @RequestBody GRNMaster request) {
		if (id!=request.getEntityId())
        	throw new RuntimeException("Request mismatch");
        validateRequest(request);
        
        List<GRNProductDetails> prodRequest = request.getProdDetails();
        List<GRNProductDetailsView> prodDetails =  request.getProdList();
		
		List<GRNValidationDto> dtoList = grnMasterService.checkingSumOfQuantityValidation(request.getPoNo());
		
		if(dtoList.size()>0){
			for(GRNProductDetails gr : prodRequest){
				for(GRNProductDetailsView gv : prodDetails){
					for(GRNValidationDto dto : dtoList){
						System.out.println("\nReq :: " + gr.getProductId() + "\tProdDetails :: " + gv.getProductId()+ "\tDto ::" + dto.getProductId());
						if(gr.getProductId().equals(dto.getProductId())&&(gv.getProductId().equals(dto.getProductId()))){
							if((dto.getSumOfAllGrn()-gv.getAcceptedQuantity()+gr.getAcceptedQuantity())>dto.getTotalQuantity()){
								throw new RuntimeException("Accepted quantity of "+ gv.getProductName() +" is exceeding the total quantity in the PO.");
							}
						}
					}
				}
			}
		}
		
		GRNMaster grn = grnMasterService.updateGrnDetails(id, request);
		if(grn != null) {
			grnMasterService.updateProductDetails(prodRequest);
		}
		return grn;
	}
	
	@GetMapping("/getgrnbyid/{id}")
	public Optional<GRNMasterView> getGrnById(@PathVariable(value="id") long id) {
		return grnMasterService.getGrnById(id);
	}
	
	//@PreAuthorize("hasAuthority('GRN_Master_VIEW')")
	@PostMapping("/getallgrn")
	public PageImpl<GRNMasterView> getAllGrn(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody GRNMasterView request) {
		return grnMasterService.getAllGrn(request, page, size);
	}
	
	@GetMapping("/grnselectionlist")
    public List<SelectionList> getSelectionGrnList(){
        return grnMasterService.getSelectionGrnList();
    }
	
	@GetMapping("/grnselectionlistbypo/{id}")
    public List<SelectionList> getSelectionGrnListByPo(@PathVariable(value="id") long id){
        return grnMasterService.getSelectionGrnListByPo(id);
    }
	
	@GetMapping("/getProductListByPo/{id}")
    public List<ProductList> getProductListByPo(@PathVariable(value="id") long poId){
		List<ProductList> prodList = null;
		try {
			prodList = grnMasterService.getProductListByPo(poId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return prodList;
    }
	
	//@PreAuthorize("hasAuthority('GRN_Master_DELETE')")
	@GetMapping("/deletegrn/{id}")
	public void deleteGrnById(@PathVariable(value="id") long id) {
		grnMasterService.deleteGrnById(id);
	}
	
	@PostMapping("/uploadlrcopy")
    public FileResponse uploadLrCopy(@RequestParam("file") MultipartFile file){
    	String subFolder = "GRN/LR Copies";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	@PostMapping("/uploaddccopy")
    public FileResponse uploadDcCopy(@RequestParam("file") MultipartFile file){
    	String subFolder = "GRN/DC Copies";
        return uploadService.UploadSingleFile(subFolder, file);
    }
	
	private boolean validateRequest(@Valid GRNMaster request) {
		List<String> errorlist = new ArrayList<String>();
		
		if(StringUtils.isEmpty(request.getGrnNumber()))
			errorlist.add("GRN no. should not be empty/null.");
		//if(request.getAccountId() == 0)
			//errorlist.add("Account should not be empty/null.");
		
		if(errorlist.size()>0) {
			throw new EntityValidationException("GRN entity is invalid", errorlist);
		}
        return true;
	}
	
	@PostMapping("/getgrnreport")
	public PageImpl<GRNReportView> getGrnReport(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody GRNReportView request) {
		return grnMasterService.getGrnReport(request, page, size);
	}
/*	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/savegrnproductdetails")
	public Response saveGrnProductDetails(GRNProductDetailsRequest request);
	
	//getting all products of a particular purchase order
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getproductsbypo")
	public Response getProductsByPurchaseOrder(String id);
	
	//getting all po for dropdown
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getallpodata")
	public Response getAllPoData(PurchaseOrderViewRequest request);
	
	//saving assets for grn
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/savegrnassets")
	public Response saveGrnAssets(List<GrnAssetMasterRequest> requestList);
	
	//getting assets for a grn product
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getgrnassetsbyid")
	public Response getGrnAssetsById(GrnAssetMasterRequest request);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteAssetById")
	public Response deleteAssetById(GrnAssetMasterRequest request);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getallgrnfordropdown")
	public Response getAllGrnForDropdown(GRNMasterRequest request);
*/
}
