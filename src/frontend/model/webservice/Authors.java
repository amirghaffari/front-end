/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.webservice;

import frontend.exceptions.AtomLinkException;
import frontend.model.ModelInterface;
import frontend.utils.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
*
* @author Amir Ghaffari
*/

public class Authors  implements ModelInterface {

    public static final String URL=Util.BASE_URL+"author/";
    private Map<Integer, AtomLink> atomLinks;
    private int id;
    public Authors(){
        atomLinks= new HashMap<Integer, AtomLink>();
    }

    public void add(AtomLink atomLink) throws AtomLinkException {
            atomLinks.put(atomLink.getId(), atomLink);
    }

    public AtomLink get(int id) {
            return atomLinks.get(id);
    }

    public List<AtomLink> getAll() {
            List<AtomLink> atomList = new ArrayList<AtomLink>();
            for( Iterator<AtomLink> atomLink = atomLinks.values().iterator(); atomLink.hasNext(); ) {
                    AtomLink a = atomLink.next();
                    atomList.add(a);
            }
            return atomList;
    }

    public void remove(int id) {
            atomLinks.remove(id);
    }

    public void update(AtomLink a) throws AtomLinkException {
            atomLinks.put(a.getId(), a);
    }

    public static Authors getAuthors(Document document) throws AtomLinkException{
        Authors authors=new Authors();
        NodeList authorElements = document.getElementsByTagName("author");
        if(authorElements != null && authorElements.getLength() > 0) {
            for (int i = 0; i < authorElements.getLength(); i++) {
                Node authorNode = authorElements.item(i);	    
                if (authorNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element authorElement = (Element) authorNode;
                  authors.add(getAtomAuthor(authorElement));
                }
            }
        }
        return authors;
    }

    public static AtomLink getAtomAuthor(Element authorElement){
        return XMLUtil.getAtomLink(authorElement);
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

}
