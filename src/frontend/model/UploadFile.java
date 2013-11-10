/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

import frontend.utils.Util;
import java.io.File;
import org.springframework.web.multipart.MultipartFile;

/**
*
* @author Amir Ghaffari
*/

public class UploadFile {
    
    private String contextPath;
    private String fileType;
    private String localFileName;
    private MultipartFile fileData;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
    
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public MultipartFile getFileData() {
        return fileData;
    }
    
    public long getFileSize() {
        return fileData.getSize();
    }
    
    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }

    public String getLocalFileName() {
        try {
            File file = new File(contextPath+Util.UploadedFileFolderPath+Util.getUserName().concat("/")+getOriginalFileName());
            if ( file.exists() ) {
                String temp=getOriginalFileName();
                temp=temp.substring(0,temp.indexOf("."));
                return Util.getUserName().concat("_")+getOriginalFileName().replace('.', '_');
            }
        } catch (Throwable e) {
            return "";
        }
        return "";
    }

//    public void setLocalFileName(String localFileName) {
//        this.localFileName = localFileName;
//    }

    public String getOriginalFileName() {
        return fileData.getOriginalFilename();
    }

    public String saveFile()
    {
        try {
            File dictionary = new File(contextPath+Util.UploadedFileFolderPath+Util.getUserName());
            if(!dictionary.exists()) dictionary.mkdirs();
            File file = new File(contextPath+Util.UploadedFileFolderPath+Util.getUserName().concat("/")+getOriginalFileName());
            if ( file.exists() ) {return Util.ERROR_FILE_BEFORE_UPLOADED;}
            fileData.transferTo(file);
        } catch (Throwable e) {
            System.err.println("Error in save file: "+e.getMessage());
            return Util.ERROR_FILE_UPLOAD_EXCEPTION;
        }
        return "";
    }
    
    public boolean deleteFile()
    {
        try {
            File file = new File(contextPath+Util.UploadedFileFolderPath+Util.getUserName().concat("/")+getOriginalFileName());
            if ( file.exists() ) {
                file.delete();
                return true;
            }
        } catch (Throwable e) {
            System.err.println("Error in delete file: "+e.getMessage());
            return false;
        }
        return false;
    }
}
