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

public class Publications implements ModelInterface{

    public static final String URL=Util.BASE_URL+"publication/";
    private int id;
    private Map<Integer, AtomLink> atomPublicationList;
    public Publications(){
        atomPublicationList= new HashMap<Integer, AtomLink>();
    }

    public void add(AtomLink atomLink) throws AtomLinkException {
            atomPublicationList.put(atomLink.getId(), atomLink);
    }

    public AtomLink get(Long id) {
            return atomPublicationList.get(id);
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

    public List<AtomLink> getAll() {
            List<AtomLink> atomLinks = new ArrayList<AtomLink>();
            for( Iterator<AtomLink> atomlink = atomPublicationList.values().iterator(); atomlink.hasNext(); ) {
                    AtomLink a = atomlink.next();
                    atomLinks.add(a);
            }
            return atomLinks;
    }

    public void remove(Long id) {
            atomPublicationList.remove(id);
    }

    public void update(AtomLink a) throws AtomLinkException {
            atomPublicationList.put(a.getId(), a);
    }

    public static Publications getPublications(Document document) throws AtomLinkException{
        Publications publications=new Publications();
        NodeList nList = document.getElementsByTagName("publication");
        if(nList != null && nList.getLength() > 0) {
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);	    
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element pubElement = (Element) nNode;
                    publications.add(XMLUtil.getAtomLink(pubElement));
                }
            }
        }
        return publications;
    }
}
