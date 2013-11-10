/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.webservice.xmlGenerator;

import frontend.model.Author;
import frontend.model.Comment;
import frontend.model.Esteem;
import frontend.model.KeyWord;
import frontend.model.Login;
import frontend.model.Publication;
import frontend.model.Rating;
import frontend.model.ReferenceMaterial;
import frontend.model.ResearchArea;
import frontend.model.Tag;
import frontend.model.User;
import frontend.model.Vote;
import frontend.model.webservice.AtomLink;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class ConvertorObjectToXML {
    public static final String TYPE="application/xml";

    public static String getAuthorXML(Author author){
        xmlElement name=new xmlElement(ElementType.SIMPLE, "name", "", author.getName(), null);
        xmlElement address=new xmlElement(ElementType.SIMPLE, "address", "", author.getAddress(), null);
        xmlElement affiliation=new xmlElement(ElementType.SIMPLE, "affiliation", "", author.getAffiliation(), null);
        xmlElement email=new xmlElement(ElementType.SIMPLE, "email", "", author.getEmail(), null);
        return xmlDocument.getXMLFormat("author", Arrays.asList(name,address,affiliation,email));
    }

    public static String getCommentXML(Comment comment){
        List<AtomLink> voteAtomList=new ArrayList<AtomLink>();
        xmlElement title=new xmlElement(ElementType.SIMPLE, "title", "", comment.getTitle(), null);
        xmlElement text=new xmlElement(ElementType.SIMPLE, "text", "", comment.getText(), null);
        xmlElement date=new xmlElement(ElementType.SIMPLE, "date", "", comment.getDate(), null);
        xmlElement publication=new xmlElement(ElementType.COMPLEX, "publication", "", "", Arrays.asList(new AtomLink("publication",Publication.URL.concat(Long.toString(comment.getPublicationId())),TYPE)));
        xmlElement user=new xmlElement(ElementType.COMPLEX, "user", "", "", Arrays.asList(new AtomLink("user",User.URL.concat(Integer.toString(comment.getUser().getId())),TYPE)));
        for(Vote v: comment.getVotes()){
            voteAtomList.add(new AtomLink("vote", Vote.URL+v.getId(), TYPE));
        }
        xmlElement votes=new xmlElement(ElementType.LIST, "votes", "vote", "",voteAtomList );
        return xmlDocument.getXMLFormat("comment", Arrays.asList(title,date,text,publication,user,votes));
    }

    public static String getRatingXML(Rating rate){
        xmlElement rating=new xmlElement(ElementType.SIMPLE, "rating", "", Integer.toString(rate.getRating()), null);
        xmlElement publication=new xmlElement(ElementType.COMPLEX, "publication", "", "", Arrays.asList(new AtomLink("publication",Publication.URL.concat(Long.toString(rate.getPublicationId())),TYPE)));
        return xmlDocument.getXMLFormat("rating ", Arrays.asList(publication,rating));
    }

    public static String getUserXML(User user){
        List<AtomLink> researchAreaAtomList=new ArrayList<AtomLink>();
        xmlElement username=new xmlElement(ElementType.SIMPLE, "username", "", user.getUserName(), null);
        xmlElement first_name=new xmlElement(ElementType.SIMPLE, "first_name", "", user.getFirstName(), null);
        xmlElement last_name=new xmlElement(ElementType.SIMPLE, "last_name", "", user.getLastName(), null);
        xmlElement email=new xmlElement(ElementType.SIMPLE, "email", "", user.getEmail(), null);
        xmlElement degree=new xmlElement(ElementType.SIMPLE, "degree", "", user.getDegree(), null);
        xmlElement institution=new xmlElement(ElementType.SIMPLE, "institution", "", user.getInstitution(), null);
        for(ResearchArea research: user.getResearchAreaList()){
            researchAreaAtomList.add(new AtomLink("research_area", ResearchArea.URL+research.getId(), TYPE));
        }
        xmlElement researchAreas=new xmlElement(ElementType.LIST, "research_areas", "research_area", "",researchAreaAtomList );
        xmlElement esteem=new xmlElement(ElementType.COMPLEX, "esteem", "", "", Arrays.asList(new AtomLink("esteem",Esteem.URL.concat(Long.toString(user.getEsteems().getId())),TYPE)));
        return xmlDocument.getXMLFormat("user", Arrays.asList(username,first_name,last_name,email,degree,institution,researchAreas,esteem));
    }

    public static String getReferenceMaterialXML(ReferenceMaterial referenceMaterial){
        xmlElement name=new xmlElement(ElementType.SIMPLE, "name", "", referenceMaterial.getName(), null);
        xmlElement url=new xmlElement(ElementType.SIMPLE, "url", "", referenceMaterial.getUrl(), null);
        xmlElement notes=new xmlElement(ElementType.SIMPLE, "notes", "", referenceMaterial.getNotes(), null);
        xmlElement publication=new xmlElement(ElementType.COMPLEX, "publication", "", "", Arrays.asList(new AtomLink("publication",Publication.URL.concat(Long.toString(referenceMaterial.getPublicationId())),TYPE)));

        return xmlDocument.getXMLFormat("publication", Arrays.asList(name,url,notes,publication));
    }

    public static String getTagXML(Tag tag){
        xmlElement name=new xmlElement(ElementType.SIMPLE, "name", "", tag.getName(), null);
        xmlElement description=new xmlElement(ElementType.SIMPLE, "description", "", tag.getDescription(), null);
        return xmlDocument.getXMLFormat("tag", Arrays.asList(name,description));
    }

    public static String getVoteXML(Vote vote){
        xmlElement votetype=new xmlElement(ElementType.SIMPLE, "votetype", "", vote.getVotetype(), null);
        xmlElement caster=new xmlElement(ElementType.COMPLEX, "caster", "", "", Arrays.asList(new AtomLink("user",User.URL.concat(Long.toString(vote.getCaster().getId())),TYPE)));
        xmlElement comment=new xmlElement(ElementType.COMPLEX, "comment", "", "", Arrays.asList(new AtomLink("comment",Comment.URL.concat(Long.toString(vote.getComment())),TYPE)));
        return xmlDocument.getXMLFormat("vote", Arrays.asList(votetype,caster,comment));
    }

    public static String getLoginXML(Login login){
        xmlElement username=new xmlElement(ElementType.SIMPLE, "username", "", login.getUsername(), null);
        xmlElement password=new xmlElement(ElementType.SIMPLE, "password", "", login.getPassword(), null);
        xmlElement user=new xmlElement(ElementType.COMPLEX, "user", "", "", Arrays.asList(new AtomLink("user",User.URL.concat(Long.toString(login.getUser().getId())),TYPE)));
        return xmlDocument.getXMLFormat("login", Arrays.asList(username,password,user));
    }

    public static String getKeyWordXML(KeyWord keyWord){
        xmlElement keyword=new xmlElement(ElementType.SIMPLE, "keyword", "", keyWord.getKeyWord(), null);
        return xmlDocument.getXMLFormat("keyword", Arrays.asList(keyword));
    }

    public static String getPublicationXML(Publication publication){

        List<AtomLink> ratingAtomList=new ArrayList<AtomLink>();
        List<AtomLink> authorAtomList=new ArrayList<AtomLink>();
        List<AtomLink> commentAtomList=new ArrayList<AtomLink>();
        List<AtomLink> tagAtomList=new ArrayList<AtomLink>();
        List<AtomLink> keywordAtomList=new ArrayList<AtomLink>();
        List<AtomLink> referencematerialAtomList=new ArrayList<AtomLink>();

        xmlElement abstracts=new xmlElement(ElementType.SIMPLE, "abstract", "", publication.getAbstracts(), null);
        xmlElement address=new xmlElement(ElementType.SIMPLE, "address", "", publication.getAddress(), null);
        xmlElement booktitle=new xmlElement(ElementType.SIMPLE, "booktitle", "", publication.getBooktitle(), null);
        xmlElement chapter=new xmlElement(ElementType.SIMPLE, "chapter", "", publication.getChapter(), null);
        xmlElement doi=new xmlElement(ElementType.SIMPLE, "doi", "", publication.getDoi(), null);
        xmlElement edition=new xmlElement(ElementType.SIMPLE, "edition", "", publication.getEdition(), null);
        xmlElement editor=new xmlElement(ElementType.SIMPLE, "editor", "", publication.getEditor(), null);
        xmlElement howpublished=new xmlElement(ElementType.SIMPLE, "howpublished", "", publication.getHowPublished(), null);
        xmlElement institution=new xmlElement(ElementType.SIMPLE, "institution", "", publication.getInstitution(), null);
        xmlElement isbn=new xmlElement(ElementType.SIMPLE, "isbn", "", publication.getIsbn(), null);
        xmlElement journal=new xmlElement(ElementType.SIMPLE, "journal", "", publication.getJournal(), null);
        xmlElement number=new xmlElement(ElementType.SIMPLE, "number", "", publication.getNumber(), null);
        xmlElement organization=new xmlElement(ElementType.SIMPLE, "organization", "", publication.getOrganization(), null);
        xmlElement pages=new xmlElement(ElementType.SIMPLE, "pages", "", publication.getPages(), null);
        xmlElement publisher=new xmlElement(ElementType.SIMPLE, "publisher", "", publication.getPublisher(), null);
        xmlElement review_status=new xmlElement(ElementType.SIMPLE, "review_status", "", Integer.toString(publication.getReviewStatus()), null);
        xmlElement series=new xmlElement(ElementType.SIMPLE, "series", "", publication.getSeries(), null);
        xmlElement publicationtype=new xmlElement(ElementType.SIMPLE, "publicationtype", "", publication.getPublicationType(), null);
        xmlElement volume=new xmlElement(ElementType.SIMPLE, "volume", "", publication.getVolume(), null);
        xmlElement title=new xmlElement(ElementType.SIMPLE, "title", "", publication.getTitle(), null);
        xmlElement month=new xmlElement(ElementType.SIMPLE, "month", "", publication.getMonth(), null);
        xmlElement note=new xmlElement(ElementType.SIMPLE, "note", "", publication.getNote(), null);
        xmlElement year=new xmlElement(ElementType.SIMPLE, "year", "", Integer.toString(publication.getYear()), null);
        xmlElement owner=new xmlElement(ElementType.COMPLEX, "owner", "", "", Arrays.asList(new AtomLink("user",User.URL.concat(Long.toString(publication.getOwner().getId())),TYPE)));
        xmlElement fields=new xmlElement(ElementType.SIMPLE, "fields", "", "", null);

        for(Rating rating:publication.getRatings()){
            ratingAtomList.add(new AtomLink("rating", Rating.URL.concat(Long.toString(rating.getId())), TYPE));
        }
        xmlElement ratingList=new xmlElement(ElementType.LIST, "ratings", "rating", "",ratingAtomList );
        //////////////
        for(Author author:publication.getAuthors()){
            authorAtomList.add(new AtomLink("author", Author.URL.concat(Long.toString(author.getId())), TYPE));
        }
        xmlElement authorList=new xmlElement(ElementType.LIST, "authors", "author", "",authorAtomList );
        //////////////
        for(Comment comment:publication.getComments()){
            commentAtomList.add(new AtomLink("comment", Comment.URL.concat(Long.toString(comment.getId())), TYPE));
        }
        xmlElement commentList=new xmlElement(ElementType.LIST, "comments", "comment", "",commentAtomList );
        //////////////
        for(Tag tag:publication.getTags()){
            tagAtomList.add(new AtomLink("tag", Tag.URL.concat(Long.toString(tag.getId())), TYPE));
        }
        xmlElement tagList=new xmlElement(ElementType.LIST, "tags", "tag", "",tagAtomList );
        //////////////
        for(KeyWord keyWord:publication.getKeyWords()){
            keywordAtomList.add(new AtomLink("keyword", KeyWord.URL.concat(Long.toString(keyWord.getId())), TYPE));
        }
        xmlElement keywordList=new xmlElement(ElementType.LIST, "keywords", "keyword", "",keywordAtomList );
        //////////////////
        for(ReferenceMaterial referenceMaterial:publication.getReferences()){
            referencematerialAtomList.add(new AtomLink("referencematerial", ReferenceMaterial.URL.concat(Long.toString(referenceMaterial.getId())), TYPE));
        }
        xmlElement referenceMaterialList=new xmlElement(ElementType.LIST, "referencematerials", "referencematerial", "",referencematerialAtomList );
        //////////////////

        return xmlDocument.getXMLFormat("publication", Arrays.asList(abstracts, address, booktitle, chapter, doi, edition, editor, howpublished, institution, isbn, journal, number,organization, pages, publisher, review_status, series, publicationtype, volume, title, month, note, year, owner, ratingList, authorList, commentList, tagList, keywordList, referenceMaterialList,fields));

    }

    public static String getResearchAreaXML(ResearchArea researchArea){
        xmlElement title=new xmlElement(ElementType.SIMPLE, "title", "", researchArea.getTitle(), null);
        xmlElement description=new xmlElement(ElementType.SIMPLE, "description", "", researchArea.getDescription(), null);
        return xmlDocument.getXMLFormat("researcharea", Arrays.asList(title,description));
    }    

}
