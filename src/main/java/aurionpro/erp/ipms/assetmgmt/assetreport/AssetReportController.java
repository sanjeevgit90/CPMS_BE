package aurionpro.erp.ipms.assetmgmt.assetreport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurionpro.erp.ipms.assetmgmt.common.AssetCountList;

@RestController
@RequestMapping(value = "/ipms/assetreport")
public class AssetReportController {

    @Autowired
    AssetReportService reportService;
    
    @PreAuthorize("hasAnyAuthority('Assets_Report_VIEW','MOB_Asset_Report')")
    @GetMapping("/{parameters}")
    public List<AssetCountList> getAssetCountByParameters(@RequestParam(name = "district", required = false) String district,
    		@RequestParam(name = "state", required = false) String state,@PathVariable(value = "parameters") String parameters){
    	 return reportService.getAssetCountByParameters(parameters, district, state);
    }

    @PreAuthorize("hasAuthority('Assets_MIS_VIEW')")
    @PostMapping("/tpaReportByFilter")
    public List<AssetTPA> tpaReportByFilter(@RequestParam(name = "page", required = false) Integer page,
    		@RequestParam(name = "size", required = false) Integer size,@RequestBody AssetTPA asset){
        return reportService.tpaReportByFilter(asset, page, size);   
    }
  
}