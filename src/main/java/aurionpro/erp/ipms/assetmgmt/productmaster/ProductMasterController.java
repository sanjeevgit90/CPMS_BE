package aurionpro.erp.ipms.assetmgmt.productmaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
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
import aurionpro.erp.ipms.jkdframework.organization.OrgRepository;
import aurionpro.erp.ipms.jkdframework.organization.Organization;

@RestController
@RequestMapping(value = "/ipms/product")
public class ProductMasterController {

    @Autowired
    ProductMasterRepository productRepo;
    
    @Autowired
    private OrgRepository orgRepo;

    @PreAuthorize("hasAuthority('Product_Master_VIEW')")
    @PostMapping("/productByFilter")
    public Iterable<ProductMaster> getAllProduct(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody ProductMaster product){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);

    	 Example<ProductMaster> productEx=Example.of(product,em);

        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		return productRepo.findAll(productEx, paging).getContent();
    	}
    	else
    	{
    		return productRepo.findAll(productEx);
    	}
    }

    @PreAuthorize("hasAuthority('Product_Master_ADD')")
    @PostMapping()
    public ProductMaster createProduct(@Valid @RequestBody ProductMaster product){
    	Optional<ProductMaster> productTemp= productRepo.findByProductname(product.getProductname());
    	
  		 if(productTemp.isPresent())
           {
               throw new EntityExistsException("The Specified Product already exists");
           }
  		 else
  		 {
  			 validate();
  			product.setOrgflag(0);
  			return productRepo.save(product);
  		 }
       
    }
    
    private void validate() {
		
	}

    @PreAuthorize("hasAuthority('Product_Master_EDIT')")
    @PutMapping()
    public ProductMaster updateProduct(@Valid @RequestBody ProductMaster product){
			
		Optional<ProductMaster> productObj = productRepo.findById(product.getEntityId());
        		
       	if (productObj == null)
       			 throw new EntityExistsException("The Specified Product doesn't exists"); 
       		 	
       	if(!(productObj.get().getProductname().equalsIgnoreCase(product.getProductname())))
 	            throw new RuntimeException("Product Name does not match with the Entity Name");	 
       	validate();
        return productRepo.save(product);
    }
    
  //  @PreAuthorize("hasAuthority('Product_Master_VIEW')")
    @GetMapping("/{id}")
    public Optional<ProductMaster> getProductById(@PathVariable(value = "id") Long id) {
    	 Optional<ProductMaster> productObj= productRepo.findById(id);

    	if (productObj == null)
    	{
    		throw new RuntimeException("Can not Find Product with product name");
    	}
		return productObj;
    }
    
    @GetMapping("/getProductList")
    public List<SelectionList> getProductList(){
        return productRepo.getProductList();
    }
    
    @GetMapping("/getProductListByOrg/{orgId}")
    public List<SelectionList> getProductListByOrg(@PathVariable(value = "orgId") Long id){
    	List<SelectionList> list = null;
    	Optional<Organization> org = null;
    	if(id!=null) {
    		org = orgRepo.findById(id);
    	}
    	
    	if(org.isPresent()) {
    		System.out.println("org product flag :: "+org.get().getProductFlag());
			list = productRepo.getProductListByOrg(org.get().getProductFlag());
		} else {
    		list = productRepo.getProductList();
    	}
    	return list;
    }
    
    @GetMapping("/getActiveProductList")
    public List<SelectionList> getActiveProductList(){
        return productRepo.getActiveProductList();
    } 
    
    @GetMapping("/getProductListFromCategory")
    public List<SelectionList> getProductListFromCategory(@RequestParam(name = "category") String category){
        return productRepo.getProductListFromCategory(category);
    } 
    
    @GetMapping("/getActiveProductListFromCategory")
    public List<SelectionList> getActiveProductListFromCategory(@RequestParam(name = "category") String category){
        return productRepo.getActiveProductListFromCategory(category);
    } 
    
    @GetMapping("/getProductListFromSubCategory")
    public List<SelectionList> getProductListFromSubCategory(@RequestParam(name  = "subcategory") String subcategory){
        return productRepo.getProductListFromSubCategory(subcategory);
    } 
    
    @GetMapping("/getActiveProductListFromSubCategory")
    public List<SelectionList> getActiveProductListFromSubCategory(@RequestParam(name = "subcategory") String subcategory){
        return productRepo.getActiveProductListFromSubCategory(subcategory);
    } 
    
    @PreAuthorize("hasAuthority('Product_Master_DELETE')")
    @DeleteMapping("/{id}")
    public ProductMaster deleteProduct(@PathVariable(value="id") Long id){

        Optional<ProductMaster> productList= productRepo.findById(id);

        if(productList== null)
        {
            throw new EntityNotFoundException("The Specified Product deos not exists");
        }

        if (productList.get().getIsDeleted()){
            throw new EntityNotFoundException("Product already deleted");
        }
        else{
        	productList.get().setIsDeleted(true);
            return productRepo.save(productList.get());
        }
    }
    

}