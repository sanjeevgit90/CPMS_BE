package aurionpro.erp.ipms.utility.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/fileupload")
public class FileUploadController {
    
    @Autowired
    FileUploadService uploadService;

    @PostMapping()
    public FileResponse UploadfileFormData(@RequestParam("file") MultipartFile file){

        String subFolder="";

        return uploadService.UploadSingleFile(subFolder, file);
    }
    
    @PostMapping("/deletefile")
    public Boolean DeleteFile(@RequestBody String filePath){
        return uploadService.DeleteSingleFile(filePath);
    }

}