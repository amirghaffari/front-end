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

public class Tag implements ModelInterface{
    public static final String URL=Util.BASE_URL+"tag/";
    private int id ;
    private String name ;
    private String description;
    private int frequency;
    private int groupId;

    public Tag() {
        this.id = 0;
        this.frequency=0;
        this.groupId=0;
        this.name = "";
        this.description = "";
    }
    
    public Tag(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.frequency=frequency=0;
        this.groupId=0;
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
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getURL() {
        return URL;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    
}
