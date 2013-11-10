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

public class ReferenceMaterial implements ModelInterface{
    public static final String URL=Util.BASE_URL+"referencematerial/";
    private int id;
    private String name;
    private String url;
    private String notes;
    private int publicationId;

    
    public ReferenceMaterial(int id, String name, String url, String notes) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.notes = notes;
    }
    
    public ReferenceMaterial() {
        id=0;
        name="";
        url="";
        notes="";
        publicationId=0;
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public String getURL() {
        return URL;
    }
    
}
