/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import frontend.model.UploadFile;
 
/**
 * This class validate the uploaded file by author
 * @author Amir Ghaffari
 */
public class FileUploadValidator implements Validator{
 
	@Override
	public boolean supports(Class clazz) {
		//just validate the FileUpload instances
		return UploadFile.class.isAssignableFrom(clazz);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
 
		UploadFile file = (UploadFile)target;
                //if file size is zero return an error code
		if(file.getFileData().getSize()==0){
			errors.rejectValue("fileData", "required.fileUpload");
		}
	}
}