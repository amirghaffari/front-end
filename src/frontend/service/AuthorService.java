package frontend.service;


import frontend.exceptions.AtomLinkException;
import frontend.model.Author;
import frontend.model.ConditionMaker;
import frontend.model.webservice.AtomLink;
import frontend.model.webservice.Authors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import frontend.service.interfaces.AuthorServiceInterface;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
* Is responsible to do author tasks
* @author Amir Ghaffari
*/

@Service
public class AuthorService implements AuthorServiceInterface {

    @Autowired
    WebService<Authors> atomAuthorsWebService;
    
    @Autowired
    WebService<Author> atomAuthorWebService;
    
    @Override
    public List<Author> getAuthorsList(List<AtomLink> authors, HttpSession session) {
        List<Author> authorsList=new ArrayList<Author>();
        for(AtomLink atomAuthor: authors){
            Author author=atomAuthorWebService.get(Author.class, atomAuthor.getHref(), session, null);
            try {
                author.setId(atomAuthor.getId());
            } catch (AtomLinkException ex) {
                System.out.print("Error in getting Id of author="+ex.getMessage());
            }
            authorsList.add(author);
        }
        return authorsList;
    }

}
