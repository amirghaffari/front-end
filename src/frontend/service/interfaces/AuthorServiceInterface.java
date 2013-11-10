package frontend.service.interfaces;

import frontend.model.Author;
import frontend.model.webservice.AtomLink;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
*
* @author Amir Ghaffari
*/

public interface AuthorServiceInterface extends Serializable{
    public List<Author> getAuthorsList(List<AtomLink> authors, HttpSession session);
    
}
