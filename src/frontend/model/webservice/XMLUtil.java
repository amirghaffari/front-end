/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.model.webservice;

import frontend.model.Author;
import frontend.model.KeyWord;
import frontend.model.Login;
import frontend.model.Rating;
import frontend.model.ReferenceMaterial;
import frontend.model.ResearchArea;
import frontend.model.Tag;
import frontend.model.User;
import frontend.utils.Util;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
*
* @author Amir Ghaffari
*/

public class XMLUtil {
    public static AtomLink getAtomLink(Element pubElement){
        AtomLink atomLink=null;
        NodeList links = pubElement.getChildNodes();
        for (int i=0; i < links.getLength(); i++) {
            Node link=links.item(i);
            if(link.getNodeType() == Node.ELEMENT_NODE){
                if(link.getNodeName().equalsIgnoreCase("atom:link")){
                    Element linkElement = (Element) link;
                    String rel = linkElement.getAttribute(AtomLink.REL_NAME);
                    String href = linkElement.getAttribute(AtomLink.HREF_NAME);
                    String type = linkElement.getAttribute(AtomLink.TYPE_NAME);
                    atomLink=new AtomLink(rel, href, type);
                }
            }
        }
        return atomLink;
    }

    public static Author getAuthor(Document document) {
        Author author=new Author();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);	    
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("name"))
                        author.setName(element.getTextContent());
                    if(element.getNodeName().equals("address"))
                        author.setAddress(element.getTextContent());
                    if(element.getNodeName().equals("affiliation"))
                        author.setAffiliation(element.getTextContent());
                    if(element.getNodeName().equals("email"))
                        author.setEmail(element.getTextContent());
                }
            }
        }
        return author;
    }

    public static User getUser(Document document) {
        User user=new User();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);	    
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("username"))
                        user.setUserName(element.getTextContent());
                    if(element.getNodeName().equals("first_name"))
                        user.setFirstName(element.getTextContent());
                    if(element.getNodeName().equals("last_name"))
                        user.setLastName(element.getTextContent());
                    if(element.getNodeName().equals("email"))
                        user.setEmail(element.getTextContent());
                    if(element.getNodeName().equals("degree"))
                        user.setDegree(element.getTextContent());
                    if(element.getNodeName().equals("institution"))
                        user.setInstitution(element.getTextContent());
                }
            }
        }
        return user;
    }

    public static Rating getRating(Document document) {
        Rating rating=new Rating();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("rating"))
                        rating.setRating((short)Util.strToInt(element.getTextContent()));
                }
            }
        }
        return rating;
    }

    public static Login getLogin(Document document) {
        Login login=new Login();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("username"))
                        login.setUsername(element.getTextContent());
                    if(element.getNodeName().equals("password"))
                        login.setPassword(element.getTextContent());
                    if(element.getNodeName().equals("user"))
                        login.setUserAtom(XMLUtil.getAtomLink(element));
                }
            }
        }
        return login;
    }

    public static Tag getTag(Document document) {
        Tag tag=new Tag();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("name"))
                        tag.setName(element.getTextContent());
                    if(element.getNodeName().equals("description"))
                        tag.setDescription(element.getTextContent());
                }
            }
        }
        return tag;
    }

    public static KeyWord getKeyWord(Document document) {
        KeyWord keyword=new KeyWord();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("keyword"))
                        keyword.setKeyWord(element.getTextContent());
                }
            }
        }
        return keyword;
    }

    public static ReferenceMaterial getReferenceMaterial(Document document) {
        ReferenceMaterial referenceMaterial=new ReferenceMaterial();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("name"))
                        referenceMaterial.setName(element.getTextContent());
                    if(element.getNodeName().equals("url"))
                        referenceMaterial.setUrl(element.getTextContent());
                    if(element.getNodeName().equals("notes"))
                        referenceMaterial.setNotes(element.getTextContent());
                }
            }
        }
        return referenceMaterial;
    }

    public static AtomComment getAtomComment(Document document) {
        AtomComment atomComment=new AtomComment();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("title"))
                        atomComment.getComment().setTitle(element.getTextContent());
                    if(element.getNodeName().equals("text"))
                        atomComment.getComment().setText(element.getTextContent());
                    if(element.getNodeName().equals("date"))
                        atomComment.getComment().setDate(element.getTextContent());
                    if(element.getNodeName().equals("publication"))
                        atomComment.setPublicationAtomLinks(XMLUtil.getAtomLink(element));
                    if(element.getNodeName().equals("user"))
                        atomComment.setUserAtomLinks(XMLUtil.getAtomLink(element));
                    if(element.getNodeName().equals("votes"))
                        atomComment.setVoteAtomLinks(processSubElements(element,"vote"));
                }
            }
        }
        return atomComment;
    }

    public static AtomVote getAtomVote(Document document) {
        AtomVote atomVote=new AtomVote();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("votetype"))
                        atomVote.getVote().setVotetype(element.getTextContent());
                    if(element.getNodeName().equals("caster"))
                        atomVote.setCasterAtomLink(XMLUtil.getAtomLink(element));
                    if(element.getNodeName().equals("comment"))
                        atomVote.setCommentAtomLink(XMLUtil.getAtomLink(element));
                }
            }
        }
        return atomVote;
    }

    public static List<AtomLink> processSubElements(Element authorsElement,String name)
    {
        List<AtomLink> atomList=new ArrayList<AtomLink>();
        NodeList authorsList = authorsElement.getChildNodes();
        for (int j = 0; j < authorsList.getLength(); j++) {
            Node subAuthorsNode = authorsList.item(j);
            if (subAuthorsNode.getNodeType() == Node.ELEMENT_NODE) {
                if(subAuthorsNode.getNodeName().equalsIgnoreCase(name)){
                    Element authorElement = (Element) subAuthorsNode;
                    atomList.add(XMLUtil.getAtomLink(authorElement));
                }
            }
        }
        return atomList;
    }
    
    public static ResearchArea getResearchArea(Document document) {
        ResearchArea researchArea=new ResearchArea();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("title"))
                        researchArea.setTitle(element.getTextContent());
                    if(element.getNodeName().equals("description"))
                        researchArea.setDescription(element.getTextContent());
                }
            }
        }
        return researchArea;
    }
}
