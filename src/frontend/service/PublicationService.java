package frontend.service;


import frontend.exceptions.AtomLinkException;
import frontend.model.Author;
import frontend.model.AuthorSubmissionWizard;
import frontend.model.Comment;
import frontend.model.ConditionMaker;
import frontend.model.KeyWord;
import frontend.model.PaperGroup;
import frontend.model.Publication;
import frontend.model.Rating;
import frontend.model.ReferenceMaterial;
import frontend.model.Tag;
import frontend.model.User;
import frontend.model.Vote;
import frontend.model.comparator.PublicationDateComparator;
import frontend.model.comparator.TagListFrequencyComparator;
import frontend.model.comparator.TagListGroupComparator;
import frontend.model.webservice.AtomComment;
import frontend.model.webservice.AtomLink;
import frontend.model.webservice.AtomPublication;
import frontend.model.webservice.AtomVote;
import frontend.model.webservice.Publications;
import frontend.service.interfaces.AuthorServiceInterface;
import frontend.service.interfaces.PublicationServiceInterface;
import frontend.service.interfaces.UserServiceInterface;
import frontend.utils.Util;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
*
* @author Amir Ghaffari
*/

@Service
public class PublicationService implements PublicationServiceInterface {

    private PublicationAddDetails addDetails;
    
    public PublicationService() {
        addDetails=new PublicationAddDetails();
    }
    
    @Autowired
    private PublicationDeleteDetails deleteDetails;
    
    @Autowired
    WebService<Publications> publicaionsWebService;

    @Autowired
    WebService<AtomPublication> atomPublicaionWebService;

    @Autowired
    WebService<Publication> publicaionWebService;

    @Autowired
    AuthorServiceInterface authorService;

    @Autowired
    UserServiceInterface userService;    

    @Autowired
    WebService<Rating> ratingWebService;

    @Autowired
    WebService<Tag> tagWebService;

    @Autowired
    WebService<KeyWord> keywordWebService;

    @Autowired
    WebService<ReferenceMaterial> referenceMaterialWebService;

    @Autowired
    WebService<AtomComment> atomCommentWebService;    

    @Autowired
    WebService<AtomVote> atomVoteWebService;

    @Autowired
    WebService<Comment> commentWebService;  

    @Autowired
    WebService<Author> authorWebService;  

    @Override
    public Publication getPublicationById(int id, HttpSession session) {
        List<ConditionMaker> conditions=Arrays.asList(new ConditionMaker(ConditionMaker.PUBLICATION_LIST,"id",Integer.toString(id)));
        List<Publication> publicationList=fetchPublicationWithDetails(conditions,session);
        if(Util.isLocalData(session)){
            return SampleData.getLocalData(session).getPublicationByID(id);
        }
        return publicationList.get(0);
    }

    @Override
    public List<Publication> getRecentPublications(String type, HttpSession session) {
        List<Publication> publicationList=null;
        List<ConditionMaker> conditions=Arrays.asList(new ConditionMaker(ConditionMaker.PUBLICATION_LIST,"publication_type",type));
        publicationList=fetchPublicationWithDetails(conditions,session);
        if(Util.isLocalData(session)){
            publicationList = SampleData.getLocalData(session).getPublicationByType( type);
        }
        //Sorts into ascending order
        Collections.sort(publicationList, new PublicationDateComparator());
        if(publicationList.size()>5)
        {
            return publicationList.subList(0, 5);
        }
        else
        {
            return publicationList;
        }
    }

    @Override
    public List<Publication> fetchPublicationWithDetails(List<ConditionMaker> conditions,HttpSession session) {
        List<Publication> publicationList=null;
        try{
            //amir
            if(1==2-1) throw new Exception("use local data");
            Publications publications=publicaionsWebService.get(Publications.class, Publication.URL, session, conditions);
            publicationList=getPublicationList(publications, session);
        }
        catch(Exception e){
            //System.out.println("Error in connecting to web service. The local data is used: "+e.getMessage());
        }
        if(publicationList!=null && !publicationList.isEmpty())
        {
            Util.setLocalData(session, false);
            return publicationList;
        }
        Util.setLocalData(session, true);
        return SampleData.getLocalData(session).getAllPublications();
    }

    private List<Publication> getPublicationList(Publications publications,HttpSession session){
        List<Publication> publicationList=new  ArrayList<Publication>();
        List<AtomLink> publicationAtomList=publications.getAll();
        for(AtomLink atomPub : publicationAtomList ){
            AtomPublication atomPublication=getPublication(atomPub.getHref(),session);
            Publication publication=atomPublication.getPublication();
            try {
                publication.setId(atomPub.getId());
            } catch (AtomLinkException ex) {
                System.out.println("Error in setting the publication id: "+ex.getMessage());
            }
            publication.setAuthors(getAuthorList(atomPublication,session));
            publication.setOwner(getAOwner(atomPublication, session));
            publication.setRatings(getRatingList(atomPublication,session));
            publication.setTags(getTagList(atomPublication,session));
            publication.setKeyWords(getKeyWordList(atomPublication,session));
            publication.setReferences(getReferenceMaterialList(atomPublication,session));
            publication.setComments(getCommentList(atomPublication, session));
            publicationList.add(publication);
        }
        return publicationList;
    }

    private List<Author> getAuthorList(AtomPublication atomPublication,HttpSession session){
        //List of author that must be fetch
        List<AtomLink> authors = atomPublication.getAuthorAtomLinks();
        return authorService.getAuthorsList(authors, session);
    }

    private List<Rating> getRatingList(AtomPublication atomPublication, HttpSession session){
        List<AtomLink> ratings = atomPublication.getRatingsAtomLinks();
        List<Rating> ratingsList=new ArrayList<Rating>();
        for(AtomLink atomRating: ratings){
            Rating rating =ratingWebService.get(Rating.class, atomRating.getHref(), session, null);
            try {
                rating.setId(atomRating.getId());
            } catch (AtomLinkException ex) {
                System.out.print("Error in getting Id of rating="+ex.getMessage());
            }
            ratingsList.add(rating);
        }
        return ratingsList;
    }

    private List<Tag> getTagList(AtomPublication atomPublication, HttpSession session){
        List<AtomLink> tags = atomPublication.getTagsAtomLinks();
        List<Tag> tagsList=new ArrayList<Tag>();
        for(AtomLink atomTag: tags){
            Tag tag =tagWebService.get(Tag.class, atomTag.getHref(), session, null);
            try {
                tag.setId(atomTag.getId());
            } catch (AtomLinkException ex) {
                System.out.print("Error in getting Id of tag="+ex.getMessage());
            }
            tagsList.add(tag);
        }
        return tagsList;
    }

    private List<KeyWord> getKeyWordList(AtomPublication atomPublication, HttpSession session){
        List<AtomLink> keywords = atomPublication.getKeywordsAtomLinks();
        List<KeyWord> keywordsList=new ArrayList<KeyWord>();
        for(AtomLink keywordTag: keywords){
            KeyWord keyWord =keywordWebService.get(KeyWord.class, keywordTag.getHref(), session, null);
            try {
                keyWord.setId(keywordTag.getId());
            } catch (AtomLinkException ex) {
                System.out.print("Error in getting Id of keyword="+ex.getMessage());
            }
            keywordsList.add(keyWord);
        }
        return keywordsList;
    }

    private List<ReferenceMaterial> getReferenceMaterialList(AtomPublication atomPublication, HttpSession session){
        List<AtomLink> referenceMaterials = atomPublication.getReferencematerialsAtomLinks();
        List<ReferenceMaterial> referenceMaterialsList=new ArrayList<ReferenceMaterial>();
        for(AtomLink atomTag: referenceMaterials){
            ReferenceMaterial referenceMaterial =referenceMaterialWebService.get(ReferenceMaterial.class, atomTag.getHref(), session, null);
            try {
                referenceMaterial.setId(atomTag.getId());
            } catch (AtomLinkException ex) {
                System.out.print("Error in getting Id of reference material="+ex.getMessage());
            }
            referenceMaterialsList.add(referenceMaterial);
        }
        return referenceMaterialsList;
    }

    private List<Comment> getCommentList(AtomPublication atomPublication, HttpSession session){
        List<AtomLink> comments = atomPublication.getCommentsAtomLinks();
        List<Comment> commentsList=new ArrayList<Comment>();
        for(AtomLink atomTag: comments){
            AtomComment atomComment =atomCommentWebService.get(AtomComment.class, atomTag.getHref(), session, null);
            fetchDetailsOfComment(atomComment, session);
            try {
                atomComment.getComment().setId(atomTag.getId());
            } catch (AtomLinkException ex) {
                System.out.print("Error in getting Id of comment="+ex.getMessage());
            }
            commentsList.add(atomComment.getComment());
        }
        return commentsList;
    }

    private User getAOwner(AtomPublication atomPublication, HttpSession session){
        return userService.getUser(atomPublication.getOwnerAtomLink().getHref(),session);
    }

    private AtomPublication getPublication(String url, HttpSession session) {
        AtomPublication atomPublication=atomPublicaionWebService.get(AtomPublication.class, url, session, null);
        return atomPublication;
    }

    private void fetchDetailsOfComment(AtomComment atomComment, HttpSession session){

         atomComment.getComment().setUser(userService.getUser(atomComment.getUserAtomLinks().getHref(), session));
         atomComment.getComment().setPublicationId(AtomLink.getId(atomComment.getPublicationAtomLinks().getHref()));
         for(AtomLink atomLink: atomComment.getVoteAtomLinks()){
             AtomVote atomVote = atomVoteWebService.get(AtomVote.class, atomLink.getHref(), session, null);
             atomComment.getComment().getVotes().add(fetchDetailsOfVote(atomVote,session));
         }
    }

    private Vote fetchDetailsOfVote(AtomVote atomVote, HttpSession session){
        Vote vote = atomVote.getVote();
        vote.setCommentId(AtomLink.getId(atomVote.getCommentAtomLink().getHref()));
        vote.setCaster(userService.getUser(atomVote.getCasterAtomLink().getHref(), session));
        return vote;
    }

    @Override
    public Tag addTag(HttpSession session, String tagName, String tagDescription, int publicationId){
        Tag tag= addDetails.addTag(tagWebService, session, tagName, tagDescription);
        Publication publication = getPublicationById(publicationId, session);
        publication.getTags().add(tag);
        publicaionWebService.update(publication, session);
        return tag;
    }

    @Override
    public Comment addComment(HttpSession session, String text, int publicationId,User user){        
        Comment comment=new Comment();
        comment.setText(text);
        comment.setPublicationId(publicationId);
        comment.setUser(user);
        AtomComment atomComment=new AtomComment();
        atomComment.setComment(comment);
        atomComment=atomCommentWebService.add(atomComment,session , AtomComment.class);
        fetchDetailsOfComment(atomComment, session);
        atomComment.getComment().setId(atomComment.getId());
        return atomComment.getComment();
    }

    @Override
    public boolean deleteComment(HttpSession session, int commentId, int publicationId,User user){        
        return deleteDetails.deleteComment(commentWebService, session, commentId, publicationId, user);
    }

    @Override
    public Rating addRate(HttpSession session, int rate, int publicationId){        
        return addDetails.addRate(ratingWebService, session, rate, publicationId);
    }

    public static List<Tag> getTagCloud(List<Publication> publications)
    {
        int NUMBER_OF_TAG_IN_CLOUD=10;
        Map<Integer, Tag> tagBuffer = new HashMap<Integer, Tag>();
        List<Tag> tagList = new ArrayList<Tag>();
        for(Publication pub: publications){
            for(Tag tag : pub.getTags()){
                if(tagBuffer.get(tag.getId())==null){
                    tag.setFrequency(1);
                    tagBuffer.put(tag.getId(), tag);
                }
                else
                {
                    tag.setFrequency(tag.getFrequency()+1);
                }
            }
        }
        for( Iterator<Tag> t = tagBuffer.values().iterator(); t.hasNext(); ) {
                Tag tag = t.next();
                tagList.add(tag);
        }

        
        //Sorts into ascending order of frequency
        Collections.sort(tagList, new TagListFrequencyComparator());
        int counter=0;
        int befFrequency=0;
        int group=0;
        for(Tag tag: tagList){
            if(befFrequency!=tag.getFrequency()){
                    ++group;
                    befFrequency=tag.getFrequency();
            }
            tag.setGroupId(group);
        }
        Collections.sort(tagList, new TagListGroupComparator());
        int numberOfGroupInInterval=group/NUMBER_OF_TAG_IN_CLOUD;
        counter=0;
        int groupCounter=0;
        int befGroup=0;
        if(numberOfGroupInInterval>1)
            for(Tag tag: tagList){
                if(befGroup!=tag.getGroupId()){
                    befGroup=tag.getGroupId();
                    ++counter;
                    if(counter>numberOfGroupInInterval && groupCounter<NUMBER_OF_TAG_IN_CLOUD){
                        counter=0;
                        ++groupCounter;
                    }
                }
                tag.setGroupId(groupCounter);
            }
        return tagList;
    }

    @Override
    public void applySubmission(HttpSession session, AuthorSubmissionWizard authorSubmissionWizard){     
         publicaionWebService.add(authorSubmissionWizard.getPublication(), session, Publication.class);
         //for(Author author:authorSubmissionWizard.getAuthors())
           // authorWebService.add(author, session, Author.class);
    }

    @Override
    public List<Publication> getPublicationsForEditorial(HttpSession session) {
        List<Publication> publicationList=null;
        if(Util.isLocalData(session)){
            //fetch all pulication with state "Rejected"/*3*/,"Incompleted"/*4*/,"Being reviewed"/*5*/
            publicationList = SampleData.getLocalData(session).getPublicationsForEditorial();
        }
        else
            publicationList=fetchPublicationWithDetails(null,session);
        return publicationList;
    }

    @Override
    public List<Publication> getPublicationsForReview(HttpSession session) {
        List<Publication> publicationList=null;
        if(Util.isLocalData(session)){
            User user=(User)session.getAttribute(Util.USER_SESSION);
            if(user!=null)
                publicationList = SampleData.getLocalData(session).getPublicationsForReview(user.getUserName());
        }
        else
            publicationList=fetchPublicationWithDetails(null,session);
        return publicationList;
    }

    @Override
    public PaperGroup getPaperGroupOfPublication(Publication publication, HttpSession session){
        if(Util.isLocalData(session)){
            for(PaperGroup paper:getListOfPaperGroup(session)){
                for(Publication pub:paper.getPublicationList()){
                    if(pub.getId()==publication.getId()){
                        return paper;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<PaperGroup> getListOfPaperGroup( HttpSession session){
        if(Util.isLocalData(session)){
            return SampleData.getLocalData(session).getPaperGroupList();
        }
        return null;
    }

}
