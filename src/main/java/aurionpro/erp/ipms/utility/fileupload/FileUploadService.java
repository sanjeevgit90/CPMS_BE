package aurionpro.erp.ipms.utility.fileupload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import aurionpro.erp.ipms.utility.AppProperties;

@Service
public class FileUploadService {

    @Autowired
    private AppProperties appProperties;
    
    public FileResponse UploadSingleFile(String subFolder, MultipartFile file){
        
        String fileName=UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String parentFolder=appProperties.getBasePhysicalPath + "\\" + subFolder;

        if(Files.notExists(Paths.get(parentFolder))){
            try{Files.createDirectories(Paths.get(parentFolder));}
            catch(IOException ex){return new FileResponse("", "", "FAILED");}
        }

        try{
            byte[] bytes=file.getBytes();
            Path path=Paths.get(parentFolder, fileName);
            Files.write(path,bytes);

            String logicalFolder=appProperties.getBaseLogicalPath + "/" + subFolder.replace("\\", "/") + "/" + fileName;

            return new FileResponse(fileName,logicalFolder,"SUCCESS");
        }catch(IOException ex){
            return new FileResponse("", "", "FAILED");
        }

    }
    
    public Boolean DeleteSingleFile(String subFolder){
        
    	File file=new File(subFolder);
        String filePath=file.getAbsolutePath();
        try{
        	Path path=Paths.get(filePath);
            Files.deleteIfExists(path);
            return true;
        }catch(IOException ex){
            return false;
        }
    }
}