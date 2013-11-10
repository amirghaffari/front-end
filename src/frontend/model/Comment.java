/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

import frontend.utils.Util;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class Comment implements ModelInterface{
    public static final String URL=Util.BASE_URL+"comment/";
    private int id;
    private String title;
    private String text;
    private String date;
    private User user;
    private List<Vote> votes;
    private long publicationId;

    public Comment() {
        id=0;
        publicationId=0;
        title="";
        text="";
        votes=new ArrayList<Vote>();
        user=null;
        date="";
    }

    public Comment(int id, String title, String text, String date, User user, List<Vote> votes) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.votes = votes;
        this.date=date;
        this.user=user;
        if(votes!=null)
        for(Vote vote:votes){
            vote.setCommentId(id);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getURL() {
        return URL;
    }

}
