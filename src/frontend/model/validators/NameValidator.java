/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.validators;

/**
*
* @author Amir Ghaffari
*/

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import frontend.model.Name;

@Component
public class NameValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return Name.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        Name name = (Name) obj;
        if (name.getFirstName().trim().length()<3) 
            e.rejectValue("firstName", "name.firstName");
        if (name.getLastName().trim().length()<3) 
            e.rejectValue("lastName", "name.lastName");
    }
}
