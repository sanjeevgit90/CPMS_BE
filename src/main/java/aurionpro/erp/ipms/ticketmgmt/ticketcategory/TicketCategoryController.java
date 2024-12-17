package aurionpro.erp.ipms.ticketmgmt.ticketcategory;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

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
import aurionpro.erp.ipms.jkdframework.jkdexception.EntityValidationException;

@RestController
@RequestMapping(value = "/ipms/ticketcategory")
public class TicketCategoryController {

    @Autowired
    TicketCategoryRepository ticketCategoryRepository;

    @PreAuthorize("hasAuthority('Ticket_Category_VIEW')")
    @PostMapping("/categoryByFilter")
    public List<TicketCategory> getAllCategory(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size, @RequestBody TicketCategory ticketCategory){
    	 ExampleMatcher em=ExampleMatcher.matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(StringMatcher.CONTAINING);

    	 Example<TicketCategory> classEx=Example.of(ticketCategory,em);
        
        if (!(StringUtils.isEmpty(page)) && !(StringUtils.isEmpty(size)))
    	{
    		Pageable paging = PageRequest.of(page, size);
    		return ticketCategoryRepository.findAll(classEx, paging).getContent();
    	}
    	else
    	{
    		return ticketCategoryRepository.findAll(classEx);
    	}
    }
    

    @GetMapping("/getAllCategoryList")
    public List<SelectionList> getAllCategoryList(){
        return ticketCategoryRepository.getAllCategoryList();
    }
     
    @GetMapping("/getActiveCategoryList")
    public List<SelectionList> getActiveCategoryList(){
        return ticketCategoryRepository.getActiveCategoryList();
    }
    
	@GetMapping("/getSubcategoryByCategory/{name}")
    public List<SelectionList> getSubcategoryByCategory(@PathVariable(value = "name") String name){
        return ticketCategoryRepository.getSubcategoryByCategory(name);
    } 

	@PreAuthorize("hasAuthority('Ticket_Category_ADD')")
    @PostMapping()
    public TicketCategory createTicketCategory(@RequestBody TicketCategory category){
	   Optional<TicketCategory> categoryTemp= ticketCategoryRepository.findById(category.getCategoryName());
   	
		 if(categoryTemp.isPresent())
        {
            throw new EntityExistsException("The Specified category already exists");
        }
		 else
		 {
			 validate();
			 return ticketCategoryRepository.save(category);
		 }
   }
   
	@PreAuthorize("hasAuthority('Ticket_Category_EDIT')")
    @PutMapping("{categoryname}")
    public TicketCategory updateTickerCategory(@PathVariable(value="categoryname") String categoryname, @RequestBody TicketCategory category){
    	if (!(StringUtils.isEmpty(categoryname)))
   		{
   		 if (!(categoryname.equalsIgnoreCase(category.getCategoryName())))
   		 throw new EntityExistsException("Request Mismatch");
   		
   		 Optional<TicketCategory> categoryTemp= ticketCategoryRepository.findById(category.getCategoryName());
   	    	 
   		 if (categoryTemp == null)
   			 throw new EntityExistsException("The Specified Category doesn't exists");
    		 
   		}
    	else
    	{
    		throw new EntityExistsException("Request Mismatch");
    	}
    		validate();
    		return ticketCategoryRepository.save(category);
   	 	
    }
    
    
    private void validate() {
    }
    

    
//    @DeleteMapping()
//     public TicketCategory deleteCategory(@RequestBody TicketCategory category){
//
//         Optional<TicketCategory> categoryList= ticketCategoryRepository.findById(category.getCategoryName());
//
//         if(categoryList==null)
//         {
//             throw new EntityNotFoundException("The Specified Ticket Category deos not exists");
//         }
//
//         if (categoryList.get().isDeleted()){
//             throw new EntityNotFoundException("Ticket Category already deleted");
//         }
//         else{
//        	 category.setDeleted(true);
//             return ticketCategoryRepository.save(category);
//         }
//     }
     
    @PreAuthorize("hasAuthority('Ticket_Category_VIEW')")
    @GetMapping("/{categoryName}")
     public TicketCategory getTicketCategoryByName(@PathVariable(value = "categoryName") String categoryName){
         Optional<TicketCategory> categoryList= ticketCategoryRepository.findById(categoryName);

         if (categoryList != null)
             return categoryList.get();
         else
             throw new EntityValidationException("Category Invalid","Category Not Found");
     }
     
    @PreAuthorize("hasAuthority('Ticket_Category_DELETE')")
     @DeleteMapping("/{categoryname}")
     public TicketCategory deleteCategory(@PathVariable(value = "categoryname") String categoryname){

         Optional<TicketCategory> categoryList= ticketCategoryRepository.findById(categoryname);

         if(categoryList== null)
         {
             throw new EntityNotFoundException("The Specified Category deos not exists");
         }

         if (categoryList.get().getIsDeleted()){
             throw new EntityNotFoundException("Category already deleted");
         }
         else{
        	 categoryList.get().setIsDeleted(true);
             return ticketCategoryRepository.save(categoryList.get());
         }
     }

}  