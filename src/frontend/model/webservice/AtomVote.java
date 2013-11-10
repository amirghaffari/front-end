/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.webservice;

import frontend.model.ModelInterface;
import frontend.model.Vote;
import frontend.utils.Util;


/**
*
* @author Amir Ghaffari
*/

public class AtomVote implements ModelInterface{
    public static final String URL=Util.BASE_URL+"vote/";
    private int id;
    private Vote vote;
    private AtomLink casterAtomLink;
    private AtomLink commentAtomLink;

    public AtomVote() {
        vote=new Vote();
    }

    public AtomLink getCasterAtomLink() {
        return casterAtomLink;
    }

    public void setCasterAtomLink(AtomLink casterAtomLink) {
        this.casterAtomLink = casterAtomLink;
    }

    public AtomLink getCommentAtomLink() {
        return commentAtomLink;
    }

    public void setCommentAtomLink(AtomLink commentAtomLink) {
        this.commentAtomLink = commentAtomLink;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
}
