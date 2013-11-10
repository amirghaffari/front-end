/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.service;

import frontend.model.Comment;
import frontend.model.Rating;
import frontend.model.Tag;
import frontend.model.User;
import frontend.model.webservice.AtomComment;
import javax.servlet.http.HttpSession;

/**
*
* @author Amir Ghaffari
*/

public class PublicationAddDetails {

    public Tag addTag(WebService<Tag> tagService,HttpSession session ,String tagName, String tagDescription){
        Tag tag=new Tag(1,tagName,tagDescription);
        tag=tagService.add(tag,session , Tag.class);
        return tag;
    }

//    public Comment addComment(WebService<AtomComment> commentService,HttpSession session ,String text,int publicationId, User user){
//        
//        Comment comment=new Comment();
//        comment.setText(text);
//        comment.setPublicationId(publicationId);
//        comment.setUser(user);
//        AtomComment atomComment=new AtomComment();
//        atomComment.setComment(comment);
//        AtomComment atomcomment=commentService.add(atomComment,session , AtomComment.class);
//        
//        return comment;
//    }

    public Rating addRate(WebService<Rating> ratingService,HttpSession session ,int rate,int publicationId){
        
        Rating rating=new Rating();
        rating.setRating((short)rate);
        rating.setPublicationId(publicationId);
        rating=ratingService.add(rating,session , Rating.class);
        return rating;
    }
}
