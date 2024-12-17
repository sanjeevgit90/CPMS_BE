package aurionpro.erp.ipms.billingmgmt.collectiontagging;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.utility.fileupload.FileResponse;
import aurionpro.erp.ipms.utility.fileupload.FileUploadService;

@RestController
@RequestMapping(value = "/ipms/collection")
public class CollectionTaggingController {

    @Autowired
    CollectionTaggingService collectionService;

    @PreAuthorize("hasAuthority('Invoice_Collection_ADD')")
     @PostMapping()
    public CollectionTagging createColection (@RequestBody @Valid CollectionTagging collection) {
    	 validate();
    	 return collectionService.createColection(collection);
    }
    

    @PreAuthorize("hasAuthority('Invoice_Collection_EDIT')")
    @PutMapping()
    public CollectionTagging updateCollection(@Valid @RequestBody CollectionTagging collection) {
		 validate();
 	 	return collectionService.updateCollection(collection);
    }
    
    private void validate() {
	
	}


 //   @PreAuthorize("hasAuthority('Invoice_Collection_VIEW')")
    @GetMapping("{id}")
    public Optional<CollectionTagging> getCollectionById(@PathVariable(value = "id") Long id) 
    {
		return collectionService.getCollectionById(id);
    }
	
	    
    @PreAuthorize("hasAuthority('Invoice_Collection_DELETE')")
    @DeleteMapping("{id}")
    public CollectionTagging deleteCollection(@PathVariable(value = "id") Long id){
           return collectionService.deleteCollection(id);
        }
    
    @PreAuthorize("hasAuthority('Invoice_Collection_VIEW')")
    @PostMapping("/getCollectionList/{projectid}")
    public List<CollectionView> getCollectionList(@PathVariable(value = "projectid") Long projectid,
    		@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody CollectionView collection){
    	collection.setProjectid(projectid);
        return collectionService.getCollectionList(collection, page, size);
    } 
    @Autowired
    FileUploadService uploadService;

    @PreAuthorize("hasAuthority('Invoice_Collection_ADD')")
    @PostMapping("/uploadPaymentAdvice")
    public FileResponse uploadPaymentAdvice(@RequestParam("file") MultipartFile file){
        String subFolder="collection";
        return uploadService.UploadSingleFile(subFolder, file);
    }
 }
