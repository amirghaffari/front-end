/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.webservice;

import frontend.model.Comment;
import frontend.model.ModelInterface;
import frontend.utils.Util;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class AtomComment implements ModelInterface {
    public static final String URL=Util.BASE_URL+"comment/";
    private int id;
    private List<AtomLink> voteAtomLinks;
    private AtomLink userAtomLinks;
    private AtomLink publicationAtomLinks;
    private Comment comment;

    public AtomComment() {
        voteAtomLinks=new ArrayList<AtomLink>();
        userAtomLinks=null;
        publicationAtomLinks=null;
        comment=new Comment();
    }

    public AtomComment(List<AtomLink> voteAtomLinks, AtomLink userAtomLinks, AtomLink publicationAtomLinks, Comment comment) {
        this.voteAtomLinks = voteAtomLinks;
        this.userAtomLinks = userAtomLinks;
        this.publicationAtomLinks = publicationAtomLinks;
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public AtomLink getPublicationAtomLinks() {
        return publicationAtomLinks;
    }

    public void setPublicationAtomLinks(AtomLink publicationAtomLinks) {
        this.publicationAtomLinks = publicationAtomLinks;
    }

    public AtomLink getUserAtomLinks() {
        return userAtomLinks;
    }

    public void setUserAtomLinks(AtomLink userAtomLinks) {
        this.userAtomLinks = userAtomLinks;
    }

    public List<AtomLink> getVoteAtomLinks() {
        return voteAtomLinks;
    }

    public void setVoteAtomLinks(List<AtomLink> voteAtomLinks) {
        this.voteAtomLinks = voteAtomLinks;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public int getId() {
        return id;
    }

}
