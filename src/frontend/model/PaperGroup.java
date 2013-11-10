/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class PaperGroup {
    private int id;
    private String title;
    private String description;
    private int blind_review;
    private List<Publication> publicationList;
    private List<User> referees;

    public PaperGroup() {
        id=0;
        title="";
        description="";
        blind_review=0;
        publicationList=new ArrayList<Publication>();
        referees=new ArrayList<User>();
    }

    public PaperGroup(int id, String title, String description, int blind_review, List<User> referees) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.blind_review = blind_review;
        publicationList=new ArrayList<Publication>();
        if(referees!=null)
            this.referees=referees;
        else
            this.referees=new ArrayList<User>();
    }

    public int getBlind_review() {
        return blind_review;
    }

    public void setBlind_review(int blint_review) {
        this.blind_review = blint_review;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Publication> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    public List<User> getReferees() {
        return referees;
    }

    public void setReferees(List<User> referees) {
        this.referees = referees;
    }
    public void addPublication(List<PaperGroup> paperGroups, Publication publication ){
        for(PaperGroup paperGroup:paperGroups){
            int index=0;
            for(Publication pub:paperGroup.getPublicationList()){
                if(pub.getId()==publication.getId()){
                    break;
                }
                ++index;
            }
            if(index!=paperGroup.getPublicationList().size()){
                paperGroup.getPublicationList().remove(index);
            }
        }            
        this.getPublicationList().add(publication);
    }
}
