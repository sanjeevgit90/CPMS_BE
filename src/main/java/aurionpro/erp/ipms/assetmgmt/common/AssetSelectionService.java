package aurionpro.erp.ipms.assetmgmt.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.assetmgmt.categorymaster.CategoryMaster;
import aurionpro.erp.ipms.assetmgmt.categorymaster.CategoryRepository;
import aurionpro.erp.ipms.jkdframework.common.SelectionList;

@Service()
public class AssetSelectionService {
	
	@Autowired
    CategoryRepository categoryRepo;

	public List<CategoryMaster> getCategoryList() {
		return categoryRepo.getCategoryList();
	}

	public List<CategoryMaster> getActiveCategoryList() {
		return categoryRepo.getActiveCategoryList();
	}

	public List<SelectionList> getAllParentCategory() {
		return categoryRepo.getAllParentCategory();
	}

	public List<SelectionList> getAllSubCategory() {
		return categoryRepo.getAllSubCategory();
	}
	
	public List<SelectionList> getActiveSubCategory() {
		return categoryRepo.getActiveSubCategory();
	}

	public List<SelectionList> getActiveParentCategory() {
		return categoryRepo.getActiveParentCategory();
	}

	public List<SelectionList> getAllSubCategoryfromParent(String parent) {
		return categoryRepo.getAllSubCategoryfromParent(parent);
	}

	public List<SelectionList> getActiveSubCategoryfromParent(String parent) {
		return categoryRepo.getActiveSubCategoryfromParent(parent);
		
	}

	
}
