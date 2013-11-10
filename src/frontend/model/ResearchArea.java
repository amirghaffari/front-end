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

public class ResearchArea implements ModelInterface{
    public static final String URL=Util.BASE_URL+"researcharea/";

    private int id;
    private String title;
    private String description;

    public ResearchArea() {
        id=0;
        title="";
        description="";
    }

    public ResearchArea(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getURL() {
        return URL;
    }
    
    
}
