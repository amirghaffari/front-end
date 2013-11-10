/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.model;

import frontend.utils.Util;

/**
*
* @author Amir Ghaffari
*/

public class Vote {

    public static final String URL=Util.BASE_URL+"vote/";
    private int id;
    private String voteType;
    private User caster;
    private int commentId;

    public Vote() {
        id=0;
        voteType="";
        caster=null;
    }

    public Vote(int id, String votetype, User caster) {
        this.id = id;
        this.voteType = votetype;
        this.caster = caster;
    }

    public User getCaster() {
        return caster;
    }

    public void setCaster(User caster) {
        this.caster = caster;
    }

    public int getComment() {
        return commentId;
    }

    public void setComment(int commentId) {
        this.commentId = commentId;
    }

    public String getVotetype() {
        return voteType;
    }

    public void setVotetype(String votetype) {
        this.voteType = votetype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

}
