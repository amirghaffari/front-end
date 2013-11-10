/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

/**
*
* @author Amir Ghaffari
*/

public class PeerReviewTemplate {
    private int id;
    private String title;
    private String fullTemplate;

    public PeerReviewTemplate(int id, String title ,String fullTemplate) {
        this.id = id;
        this.title=title;
        this.fullTemplate = fullTemplate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullTemplate() {
        return fullTemplate;
    }

    public void setFullTemplate(String fullTemplate) {
        this.fullTemplate = fullTemplate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
