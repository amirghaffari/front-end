/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.webservice;

import frontend.exceptions.AtomLinkException;
import frontend.utils.Util;
import javax.xml.bind.annotation.*;

/**
*
* @author Amir Ghaffari
*/

@XmlRootElement(name="link", namespace="http://www.w3.org/2005/Atom")
@XmlAccessorType(XmlAccessType.FIELD)
public class AtomLink{
    public static final String REL_NAME="rel";
    public static final String HREF_NAME="href";
    public static final String TYPE_NAME="type";
    @XmlAttribute 
    private String rel;
    @XmlAttribute 
    private  String href;
    @XmlAttribute 
    private  String type;
    private int id;
    public AtomLink() {        
        this.rel = "";
        this.href = "";
        this.type = "";
        this.id=-1;
    }
    public AtomLink(String rel, String href, String type)
    {
        this.rel = rel;
        this.href = href;
        this.type = type;
        this.id=-1;
    }   
    
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getId() throws AtomLinkException{
        if(id!=-1) return id;
        return getId(href);
    }
    public static int getId(String address){
        String temp="";
        if(address==null || address.isEmpty()) return -1;
        //remove / from the end
        if(address.endsWith("/")) address=address.substring(0, address.length()-1);
        int index=address.lastIndexOf("/");
        if(index==-1) return -1; 
        temp=address.substring(index+1);
        return Util.strToInt(temp);
    }
    public String xmlFormat(){
        return "<atom:link rel=\""+rel+"\" type=\""+type+"\" href=\""+href+"\"/>";
    }
}
