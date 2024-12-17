package aurionpro.erp.ipms.assetmgmt.categorymaster;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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

@RestController
@RequestMapping(value = "/ipms/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepo;
    
    @PreAuthorize("hasAuthority('Category_Master_VIEW')")
    @PostMapping("/categoryByfilter")
    public Iterable<CategoryMaster> getAllCategory(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody CategoryMaster category){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<CategoryMaster> categoryEx=Example.of(category,em);

        
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		return categoryRepo.findAll(categoryEx, paging).getContent();
    	}
    	else
    	{
    		return categoryRepo.findAll(categoryEx);
    	}
    }
    

    @PreAuthorize("hasAuthority('Category_Master_ADD')")
    @PostMapping()
    public CategoryMaster createCategory( @Valid @RequestBody CategoryMaster category){
    	 Optional<CategoryMaster> categoryTemp= categoryRepo.findById(category.getCategoryname());
	    	
   		 if(categoryTemp.isPresent())
            {
                throw new EntityExistsException("The Specified Category already exists");
            }
   		 else
   		 {
   			 validate();
   			 return categoryRepo.save(category);
   		 }
    	
    }
    
    @PreAuthorize("hasAuthority('Category_Master_EDIT')")
    @PutMapping()
    public CategoryMaster updateCategory(@Valid @RequestBody CategoryMaster category){
    	Optional<CategoryMaster> categoryTemp= categoryRepo.findById(category.getCategoryname());
   	    	 
   		 if (categoryTemp == null)
   			 throw new EntityExistsException("The Specified Category doesn't exists");
    		 
   		 validate();
    	 return categoryRepo.save(category);
   	 	
    }
       
   // @PreAuthorize("hasAuthority('Category_Master_VIEW')")
     @GetMapping()
    public Optional<CategoryMaster> getCategoryByName(@RequestParam(name = "categoryname") String categoryname)
    {
    	Optional<CategoryMaster> categoryObj = categoryRepo.findById(categoryname);
       	if (categoryObj == null)
    	{
       		new RuntimeException("Can not find category with categoryname"+ categoryname);
    	}
		return categoryObj;
    }
    
    @GetMapping("/getCategoryList")
    public List<CategoryMaster> getCategoryList(){
        return categoryRepo.getCategoryList();
    } 
    
    @GetMapping("/getActiveCategoryList")
    public List<CategoryMaster> getActiveCategoryList(){
        return categoryRepo.getActiveCategoryList();
    } 
    
    @GetMapping("/getAllParentCategory")
    public List<SelectionList> getAllParentCategory(){
        return categoryRepo.getAllParentCategory();
    }   
    
    @GetMapping("/getAllSubCategory")
    public List<SelectionList> getAllSubCategory(){
        return categoryRepo.getAllSubCategory();
    }
    
    @GetMapping("/getActiveParentCategory")
    public List<SelectionList> getActiveParentCategory(){
        return categoryRepo.getActiveParentCategory();
    }  
    
    @GetMapping("/getActiveSubCategory")
    public List<SelectionList> getActiveSubCategory(){
        return categoryRepo.getActiveSubCategory();
    }   
    
    @GetMapping("/getAllSubCategoryfromParent")
    public List<SelectionList> getAllSubCategoryfromParent(@RequestParam(name  = "parent") String parent){
        return categoryRepo.getAllSubCategoryfromParent(parent);
    } 
    
    @GetMapping("/getActiveSubCategoryfromParent")
    public List<SelectionList> getActiveSubCategoryfromParent(@RequestParam(name  = "parent") String parent){
        return categoryRepo.getActiveSubCategoryfromParent(parent);
    }
    
    @PreAuthorize("hasAuthority('Category_Master_DELETE')")
    @DeleteMapping("/{categoryname}")
    public CategoryMaster deleteCategory(@RequestParam(name = "categoryname") String categoryname){

        Optional<CategoryMaster> categoryList= categoryRepo.findById(categoryname);

        if(categoryList== null)
        {
            throw new EntityNotFoundException("The Specified Category deos not exists");
        }

        if (categoryList.get().getIsDeleted()){
            throw new EntityNotFoundException("Category already deleted");
        }
        else{
        	categoryList.get().setIsDeleted(true);
            return categoryRepo.save(categoryList.get());
        }
    }
    
    private void validate() {
    }
}