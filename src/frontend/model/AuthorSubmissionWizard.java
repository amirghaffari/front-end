/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class AuthorSubmissionWizard {
    private String currentPage;
    public final static String STATUS_EDIT="edit";
    public final static String STATUS_ADD="add";
    public final static String STATUS="status";
    //exist in author data
    private User applicant;
    private List<Author> authors;
    private String status;
    private Author tempAuthor;
    private Publication publication;
    private String[] errorCodeList;
    private UploadFile uploadFile;
    private List<UploadFile> uploadFiles;


    public void addUploadFilesList(UploadFile uploadFile){
        if(uploadFiles==null) uploadFiles = new ArrayList<UploadFile>();
        uploadFiles.add(uploadFile);
    }

    public boolean deleteUploadedFile(String localFileName){
        int index=-1;
        for (UploadFile u : uploadFiles)
        {   ++index;
            if (u.getLocalFileName().equals(localFileName)) {
                if(u.deleteFile()){
                    uploadFiles.remove(index);
                    return true;
                }
            }
        }
        return false;
    }

    public List<UploadFile> getUploadFiles() {
        return uploadFiles;
    }

    public UploadFile getUploadFile() {
        if(uploadFile==null) uploadFile= new UploadFile();
        return uploadFile;
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String[] getPublicationType() {
        String[] publicationType={"book","article","paper"};
        return publicationType;
    }

    public String[] getFileTypes() {
        String[] fileTypes={"Bibtext","Original Manuscript","Figure","Table"};
        return fileTypes;
    }

    public Publication getPublication() {
        if(publication==null) {
            publication=new Publication();
            publication.setReviewStatus(5);//being reviewed  //{"Public"=1,"Reviewed"=2,"Rejected"=3,"Incompleted"=4,"Being reviewed"=5}
        }
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
        publication.setReviewStatus(5);//being reviewed  //{"Public"=1,"Reviewed"=2,"Rejected"=3,"Incompleted"=4,"Being reviewed"=5}
    }

    public String[] getErrorList() {
        return errorCodeList;
    }

    public void setErrorList(String[] errorList) {
        this.errorCodeList = errorList;
    }

    public AuthorSubmissionWizard()
    {
        status=STATUS_ADD;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public User getApplicant() {
        if(applicant==null) applicant=new User();
        return applicant;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

//////////Author

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Author getAuther()
    {
        if(status.equals(STATUS_ADD))  return createNewAuthor();
        if(status.equals(STATUS_EDIT)) return tempAuthor;
        return null;
    }

    public void setAuther(Author author)
    {
        tempAuthor=author;
        status=STATUS_EDIT;
    }

    public Author createNewAuthor() {
        Author author=new Author();
        return author;
    }

    public Author getAuthorById(long id){
        for (Author a : authors)
            if (a.getId()==id) {
                return a;
            }
        return null;
    }

    public boolean deleteAuthorById(long id){
        int index=-1;
        for (Author a : authors)
        {   ++index;
            if (a.getId()==id) {
                authors.remove(index);
                return true;
            }
        }
        return false;
    }    

    private long getNewAuthorId()
    {
        long max=0;
        for (Author a : authors)
            if (a.getId() > max) {
                max=a.getId();
            }
        return max+1;
    }    

    public void saveAuthors(Author author) {
        if(authors==null) authors=new ArrayList<Author>();
        
        if(author.getId()==0)
        {
            author.setId((int)getNewAuthorId());
            Author temp=new Author();
            temp.copyContent(author);
            authors.add(temp);
        }
        
        for (Author a : authors) {
            if (a.getId() == author.getId()) {
                a.copyContent(author);
                return;
            }
        }
    }

}
