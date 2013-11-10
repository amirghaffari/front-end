package frontend.service.interfaces;

import frontend.model.AuthorSubmissionWizard;
import frontend.model.Comment;
import frontend.model.ConditionMaker;
import frontend.model.PaperGroup;
import java.io.Serializable;
import java.util.List;
import frontend.model.Publication;
import frontend.model.Rating;
import frontend.model.Tag;
import frontend.model.User;
import javax.servlet.http.HttpSession;

/**
*
* @author Amir Ghaffari
*/
public interface PublicationServiceInterface extends Serializable{
    
    public Publication getPublicationById(int id, HttpSession session) ;
    public List<Publication> getRecentPublications(String type, HttpSession session) ;
    public List<Publication> fetchPublicationWithDetails(List<ConditionMaker> conditions, HttpSession session);
    public Tag addTag(HttpSession session, String tagName, String tagDescription, int publicationId);
    public Comment addComment(HttpSession session, String text, int publicationId,User user);
    public boolean deleteComment(HttpSession session, int commentId, int publicationId,User user);
    public Rating addRate(HttpSession session, int rate, int publicationId);
    public void applySubmission(HttpSession session, AuthorSubmissionWizard authorSubmissionWizard);
    public List<Publication> getPublicationsForEditorial(HttpSession session);
    public List<PaperGroup> getListOfPaperGroup( HttpSession session);
    public PaperGroup getPaperGroupOfPublication(Publication publication, HttpSession session);
    public List<Publication> getPublicationsForReview(HttpSession session);

}