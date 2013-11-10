/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.model.webservice;

import frontend.model.ModelInterface;
import frontend.model.Publication;
import frontend.utils.Util;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
*
* @author Amir Ghaffari
*/

public class AtomPublication implements ModelInterface {
    public static final String URL=Util.BASE_URL+"publication/";
    private int id;
    private List<AtomLink> authorAtomLinks;
    private List<AtomLink> ratingsAtomLinks;
    private List<AtomLink> commentsAtomLinks;
    private List<AtomLink> tagsAtomLinks;
    private List<AtomLink> keywordsAtomLinks;
    private List<AtomLink> referencematerialsAtomLinks;

    private AtomLink ownerAtomLink;

    private Publication publication;

    public List<AtomLink> getAuthorAtomLinks() {
        return authorAtomLinks;
    }

    public void setAuthors(List<AtomLink> authorAtomLinks) {
        this.authorAtomLinks = authorAtomLinks;
    }

    public AtomPublication(){
        authorAtomLinks=null;
        publication=null;
        ownerAtomLink=null;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public AtomLink getOwnerAtomLink() {
        return ownerAtomLink;
    }

    public void setOwnerAtomLink(AtomLink ownerAtomLink) {
        this.ownerAtomLink = ownerAtomLink;
    }

    public List<AtomLink> getCommentsAtomLinks() {
        return commentsAtomLinks;
    }

    public void setCommentsAtomLinks(List<AtomLink> commentsAtomLinks) {
        this.commentsAtomLinks = commentsAtomLinks;
    }

    public List<AtomLink> getKeywordsAtomLinks() {
        return keywordsAtomLinks;
    }

    public void setKeywordsAtomLinks(List<AtomLink> keywordsAtomLinks) {
        this.keywordsAtomLinks = keywordsAtomLinks;
    }

    public List<AtomLink> getRatingsAtomLinks() {
        return ratingsAtomLinks;
    }

    public void setRatingsAtomLinks(List<AtomLink> ratingsAtomLinks) {
        this.ratingsAtomLinks = ratingsAtomLinks;
    }

    public List<AtomLink> getReferencematerialsAtomLinks() {
        return referencematerialsAtomLinks;
    }

    public void setReferencematerialsAtomLinks(List<AtomLink> referencematerialsAtomLinks) {
        this.referencematerialsAtomLinks = referencematerialsAtomLinks;
    }

    public List<AtomLink> getTagsAtomLinks() {
        return tagsAtomLinks;
    }

    public void setTagsAtomLinks(List<AtomLink> tagsAtomLinks) {
        this.tagsAtomLinks = tagsAtomLinks;
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

    public void getPublication(Document document) {
        publication=new Publication();
        Node root = document.getDocumentElement();
        NodeList nlints = root.getChildNodes();
        if(nlints != null && nlints.getLength() > 0) {
            for (int i = 0; i < nlints.getLength(); i++) {
                Node nNode = nlints.item(i);	    
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if(element.getNodeName().equals("abstract"))
                        publication.setAbstracts(element.getTextContent());
                    if(element.getNodeName().equals("address"))
                        publication.setAddress(element.getTextContent());
                    if(element.getNodeName().equals("booktitle"))
                        publication.setBooktitle(element.getTextContent());
                    if(element.getNodeName().equals("chapter"))
                        publication.setChapter(element.getTextContent());
                    if(element.getNodeName().equals("edition"))
                        publication.setEdition(element.getTextContent());
                    if(element.getNodeName().equals("editor"))
                        publication.setEditor(element.getTextContent());
                    if(element.getNodeName().equals("howpublished"))
                        publication.setHowPublished(element.getTextContent());
                    if(element.getNodeName().equals("institution"))
                        publication.setInstitution(element.getTextContent());
                    if(element.getNodeName().equals("isbn"))
                        publication.setIsbn(element.getTextContent());
                    if(element.getNodeName().equals("journal"))
                        publication.setJournal(element.getTextContent());
                    if(element.getNodeName().equals("number"))
                        publication.setNumber(element.getTextContent());
                    if(element.getNodeName().equals("organization"))
                        publication.setOrganization(element.getTextContent());
                    if(element.getNodeName().equals("pages"))
                        publication.setPages(element.getTextContent());
                    if(element.getNodeName().equals("publisher"))
                        publication.setPublisher(element.getTextContent());
                    if(element.getNodeName().equals("series"))
                        publication.setSeries(element.getTextContent());
                    if(element.getNodeName().equals("publicationtype"))
                        publication.setPublicationType(element.getTextContent());
                    if(element.getNodeName().equals("volume"))
                        publication.setVolume(element.getTextContent());
                    if(element.getNodeName().equals("title"))
                        publication.setTitle(element.getTextContent());
                    if(element.getNodeName().equals("month"))
                        publication.setMonth(element.getTextContent());
                    if(element.getNodeName().equals("note"))
                        publication.setNote(element.getTextContent());
                    if(element.getNodeName().equals("year"))
                        publication.setYear(Util.strToInt(element.getTextContent()));
                    if(element.getNodeName().equals("review_status"))
                        publication.setReviewStatus(Util.strToInt(element.getTextContent()));
                    if(element.getNodeName().equals("authors"))
                        authorAtomLinks=XMLUtil.processSubElements(element,"author");
                    if(element.getNodeName().equals("owner"))
                        ownerAtomLink=XMLUtil.getAtomLink(element);
                    if(element.getNodeName().equals("ratings"))
                        ratingsAtomLinks=XMLUtil.processSubElements(element,"rating");
                    if(element.getNodeName().equals("comments"))
                        commentsAtomLinks=XMLUtil.processSubElements(element,"comment");
                    if(element.getNodeName().equals("tags"))
                        tagsAtomLinks=XMLUtil.processSubElements(element,"tag");
                    if(element.getNodeName().equals("keywords"))
                        keywordsAtomLinks=XMLUtil.processSubElements(element,"keyword");
                    if(element.getNodeName().equals("referencematerials"))
                        referencematerialsAtomLinks=XMLUtil.processSubElements(element,"referencematerial");
                }
            }
        }
    }

}
