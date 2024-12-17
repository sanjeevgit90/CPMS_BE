package aurionpro.erp.ipms.assetmgmt.assetmaster;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@RestController
@RequestMapping(value = "/ipms/asset")
public class AssetMasterController {

    @Autowired
    AssetMasterService assetservice;
    
    @PreAuthorize("hasAuthority('Asset_Master_ADD')")
    @PostMapping()
    public AssetMaster createAsset(@Valid @RequestBody AssetMaster asset){
    		 validate();
 			 return assetservice.createAsset(asset);
    }
    
    @PreAuthorize("hasAuthority('Asset_Master_EDIT')")
    @PutMapping()
    public AssetMaster updateAsset(@Valid @RequestBody AssetMaster asset){
    	 validate();
		 return assetservice.updateAsset(asset);
    }
    
  //  @PreAuthorize("hasAuthority('Asset_Master_VIEW')")
    @GetMapping("/{id}")
    public Optional<AssetView> findAssetById(@PathVariable(value = "id") Long id) 
    {
    	return assetservice.findAssetById(id);
    }
  
    
    @GetMapping("/getAssetList")
    public List<SelectionList> getAssetList(){
        return assetservice.getAssetList();
    } 
    
    @GetMapping("/getActiveAssetList")
    public List<SelectionList> getActiveAssetList(){
        return assetservice.getActiveAssetList();
    } 
     
   @PreAuthorize("hasAuthority('Asset_Master_DELETE')")
    @DeleteMapping("/{id}")
    public AssetMaster deleteAsset(@PathVariable(value = "id") Long id){
           return assetservice.deleteAsset(id);
    }
    
    @GetMapping("/getAuditTrail/{id}")
    public List<AssetAuditTrail> getAuditTrail(@PathVariable(value = "id") Long id){
        return assetservice.getAuditTrail(id);
    } 
    
    @GetMapping("/getAssetByLocation")
    public List<SelectionList> getAssetByLocation(@RequestParam(name = "locationid") String locationid) {
        return assetservice.getAssetByLocation(locationid);
    }
    
    @GetMapping("/getAssetTagByLocation/{name}")
    public List<SelectionList> getAssetTagByLocation(@PathVariable(value = "name") String name){
        return assetservice.getAssetTagByLocation(name);
    } 
    
    
    private void validate() {
    }
    
 //  @PreAuthorize("hasAuthority('Asset_Master_VIEW')")
    @PostMapping("/assetByFilter")
    public PageImpl<AssetView> getAssetByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody AssetView asset){
        return assetservice.getAssetByFilter(asset, page,size);   
    }
    
  @PreAuthorize("hasAuthority('Asset_Master_VIEW')")
    @PostMapping("/oemAssetByFilter")
    public List<AssetView> oemAssetByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody AssetView asset){
        return assetservice.oemAssetByFilter(asset, page,size);   
    }
  
  @PreAuthorize("hasAuthority('Asset_Master_VIEW')")
  @PostMapping("/dcAssetByFilter")
  public List<AssetView> dcAssetByFilter(@RequestParam(name = "page", required = false) Integer page,
  		@RequestParam(name = "size", required = false) Integer size, @RequestBody AssetView asset){
      return assetservice.dcAssetByFilter(asset, page,size);   
  }

}