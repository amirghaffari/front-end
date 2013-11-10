/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.service;

import frontend.model.Comment;
import frontend.model.User;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

/**
*
* @author Amir Ghaffari
*/
@Service
public class PublicationDeleteDetails {
        public boolean deleteComment(WebService<Comment> commentService,HttpSession session ,int commentId, int publicationId, User user){
        Comment comment=new Comment();
        comment.setId(commentId);
        return commentService.delete(comment, session);
    }
}
