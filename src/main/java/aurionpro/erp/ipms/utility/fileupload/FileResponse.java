package aurionpro.erp.ipms.utility.fileupload;

public class FileResponse {

    public String fileName;
    public String filePath;
    public String status;

    public FileResponse(String fileName, String filePath, String status) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.status = status;
    }

    public FileResponse() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}