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

public class KeyWord implements ModelInterface{
    public static final String URL=Util.BASE_URL+"keyword/";
    
    private int id;
    private String keyWord;

    public KeyWord() {
        this.id = 0;
        this.keyWord = "";
    }

    public KeyWord(int id, String KeyWord) {
        this.id = id;
        this.keyWord = KeyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String KeyWord) {
        this.keyWord = KeyWord;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getURL() {
        return URL;
    }
    
}
