package frontend.model.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import frontend.model.Search;

/**
*
* @author Amir Ghaffari
*/

@Component
public class SearchValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return Search.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        //ValidationUtils.rejectIfEmpty(e, "fromDateYear", "fromDateYear.empty");
        Search search = (Search) obj;
        if (search.getFromDateYear().trim().compareTo("") != 0) {
            if (Integer.parseInt(search.getFromDateYear()) <= 1990) {
                e.rejectValue("fromDateYear", "advanced_search_page.fromDateYear");
            } else if (Integer.parseInt(search.getFromDateYear()) > 2011) {
                e.rejectValue("fromDateYear", "advanced_search_page.fromDateYear");
            }
        }
        if (search.getUntilDateYear().trim().compareTo("") != 0) {
            if (Integer.parseInt(search.getUntilDateYear()) <= 1990) {
                e.rejectValue("untilDateYear", "advanced_search_page.untilDateYear");
            } else if (Integer.parseInt(search.getUntilDateYear()) > 2011) {
                e.rejectValue("untilDateYear", "advanced_search_page.untilDateYear");
            }
        }
    }
}