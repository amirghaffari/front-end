package frontend.service.interfaces;

import java.io.Serializable;
import java.util.List;
import frontend.model.Search;
import frontend.model.Publication;
import javax.servlet.http.HttpSession;

/**
*
* @author Amir Ghaffari
*/

public interface SearchServiceInterface extends Serializable{
    public List<Publication> getSearchResult(Search search, HttpSession session);
}