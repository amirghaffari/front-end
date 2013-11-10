/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

/**
*
* @author Amir Ghaffari
*/

public class PeerReview {
    private int id;
    private User referee;
    private Publication publication;
    private PeerReviewTemplate peerReviewTemplate;
    private String review;

    public PeerReview(User referee, Publication publication, PeerReviewTemplate peerReviewTemplate, String review) {
        id=0;
        this.referee = referee;
        this.publication = publication;
        this.peerReviewTemplate = peerReviewTemplate;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public User getReferee() {
        return referee;
    }

    public void setReferee(User referee) {
        this.referee = referee;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public PeerReviewTemplate getPeerReviewTemplate() {
        return peerReviewTemplate;
    }

    public void setPeerReviewTemplate(PeerReviewTemplate peerReviewTemplate) {
        this.peerReviewTemplate = peerReviewTemplate;
    }

}
