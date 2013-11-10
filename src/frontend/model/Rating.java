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

public class Rating implements ModelInterface{

    public static final String URL=Util.BASE_URL+"rating/";

    private int id;
    private short rating;
    private long publicationId;

    public Rating() {
        id=0;
        rating=0;
    }
    @Override
    public String getURL(){
        return URL;
    }
    public Rating(int id, short rating) {
        this.id = id;
        this.rating = rating;
    }    

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
    
    public long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(long publicationId) {
        this.publicationId = publicationId;
    }
}
