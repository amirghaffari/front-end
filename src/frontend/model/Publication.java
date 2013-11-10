package frontend.model;

import frontend.utils.Util;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
*
* @author Amir Ghaffari
*/

public class Publication implements Serializable,ModelInterface {

        public static final String URL=Util.BASE_URL+"publication/";
	private int id;
	private String address;
	private String booktitle;
	private String chapter;
	private String edition;
	private String editor;
        private String howPublished;
        private String institution;
        private String isbn;
        private String journal;
        private String number;
        private String organization;
        private String pages;
        private String publisher;
        private String series;
        private String publicationType;
        private String volume;
        @NotBlank
        @Length(max = 100,min=5) 
        private String title;
        private String month;
        private String note;
        private int year;
        private User owner;        
        @NotBlank
        @Length(max = 500,min=10) 
        private String abstracts;
        private int reviewStatus;
        private String doi;

	private List<Author> authors;
        private List<Tag> tags;
        private List<ReferenceMaterial> references;
        private List<Comment> comments;
        private List<Rating> ratings;
        private List<KeyWord> keyWords;
        private boolean fullText;
        private PaperGroup paperGroup;;


	public Publication() {id=-1;fullText=false;}

	public Publication(int id, String address, String booktitle, String chapter, String edition, String editor,
                String howPublished, String institution, String isbn, String journal,
                String number, String organization, String pages, String publisher, String series, String publicationType,
                String volume, String title, String month, String note, int year, User owner, List<Author> authors,List<KeyWord> keywords, 
                List<Tag> tags,String abstracts,List<ReferenceMaterial> references,List<Comment> comments, List<Rating> ratings, 
                boolean fullText, int reviewStatus) {
            this.id=id;
            this.address=address;
            this.booktitle=booktitle;
            this.chapter=chapter;
            this.edition=edition;
            this.editor=editor;
            this.howPublished=howPublished;
            this.institution=institution;
            this.isbn=isbn;
            this.journal=journal;
            this.number=number;
            this.organization=organization;
            this.pages=pages;
            this.publisher=publisher;
            this.series=series;
            this.publicationType=publicationType;
            this.volume=volume;
            this.title=title;
            this.month=month;
            this.note=note;
            this.year=year;
            this.owner=owner;
            this.authors=authors;
            this.tags=tags;
            this.abstracts=abstracts;
            this.references=references;
            this.comments=comments;
            this.ratings=ratings;
            this.keyWords=keywords;
            this.fullText=fullText;
            this.reviewStatus=reviewStatus;
            if(ratings!=null)
            for(Rating rating: ratings){
                rating.setPublicationId(id);
            }
            if(references!=null)
            for(ReferenceMaterial reference: references){
                reference.setPublicationId(id);
            }
            if(comments!=null)
            for(Comment comment: comments){
                comment.setPublicationId(id);
            }
	}

        @Override
	public int getId() {
		return id;
	}
        
        @Override
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address.trim();
	}
	public String getBooktitle() {
            return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle.trim();
	}
        public String getChapter() {
            return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter.trim();
	}
        public String getEdition() {
            return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition.trim();
	}
        public String getEditor() {
            return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor.trim();
	}
        public String getHowPublished() {
            return howPublished;
	}
	public void setHowPublished(String howPublished) {
		this.howPublished = howPublished.trim();
	}
        public String getInstitution() {
            return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution.trim();
	}
        public String getIsbn() {
            return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn.trim();
	}
        public String getJournal() {
            return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal.trim();
	}
        public String getNumber() {
            return number;
	}
	public void setNumber(String number) {
		this.number = number.trim();
	}
        public String getOrganization() {
            return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization.trim();
	}
        public String getPages() {
            return pages;
	}
	public void setPages(String pages) {
		this.pages = pages.trim();
	}
        public String getPublisher() {
            return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher.trim();
	}
        public String getSeries() {
            return series;
	}
	public void setSeries(String series) {
		this.series = series.trim();
	}
        public String getPublicationType() {
            return publicationType;
	}
	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType.trim();
	}
        public String getVolume() {
            return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume.trim();
	}
        public String getTitle() {
            return title;
	}
	public void setTitle(String title) {
		this.title = title.trim();
	}
        public String getMonth() {
            return month;
	}
	public void setMonth(String month) {
		this.month = month.trim();
	}
        public String getNote() {
            return note;
	}
	public void setNote(String note) {
		this. note= note.trim();
	}
        public int getYear() {
            return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
        public User getOwner() {
            return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
        public void setAuthors(List<Author> authors) {
            this.authors = authors;
        }

        public List<Author> getAuthors() {
            return authors;
        }

        public void addAuthor(Author author) {
            authors.add(author);
        }

        public String getAbstracts() {
            return abstracts;
        }

        public void setAbstracts(String abstracts) {
            this.abstracts = abstracts;
        }

        public int getReviewStatus() {
            return reviewStatus;
        }

        public void setReviewStatus(int review_status) {
            this.reviewStatus = review_status;
        }

        public List<Tag> getTags() {
            if(tags!=null)
                return tags;
            else
                tags=new ArrayList<Tag>();
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }

        public List<ReferenceMaterial> getReferences() {
            if(references!=null)
                return references;
            else
                references=new ArrayList<ReferenceMaterial>();
            return references;
        }

        public void setReferences(List<ReferenceMaterial> references) {
            this.references = references;
        }

        public List<Comment> getComments() {
            if(comments!=null)
                return comments;
            else
                comments=new ArrayList<Comment>();
            return comments;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
        }
        
        public List<KeyWord> getKeyWords() {
            if(keyWords!=null)
                return keyWords;
            else
                keyWords=new ArrayList<KeyWord>();
            return keyWords;
        }

        public void setKeyWords(List<KeyWord> keyWords) {
            this.keyWords = keyWords;
        }

        public List<Rating> getRatings() {
            if(ratings!=null)
                return ratings;
            else
                ratings=new ArrayList<Rating>();
            return ratings;
        }

        public void setRatings(List<Rating> ratings) {
            this.ratings = ratings;
        }

        public String getDoi() {
            return doi;
        }

        public void setDoi(String doi) {
            this.doi = doi;
        }

        @Override
        public String getURL() {
            return URL;
        }

        public int getCountOfComments() {
            if(comments==null) return 0;
            return comments.size();
        }

        public int getCountOfRates() {
            if(ratings==null) return 0;
            return ratings.size();
        }

        public int getAverageOfRates() {
            int sum=0;
            if(ratings==null||ratings.size()==0) return 0;
            for(Rating rate:ratings){
                sum+=rate.getRating();
            }
            return Math.round(sum/ratings.size());
        }

        public String getKeyword() {
            StringBuilder result=new StringBuilder();
            for(KeyWord keyword:getKeyWords()){
                result.append(keyword.getKeyWord());
                result.append("\n");
            }
            return result.toString();
        }

        public void setKeyword(String keyword) {
            int newId=0;
            Scanner scanner = new Scanner(keyword);
            while (scanner.hasNextLine()) {
              String line = scanner.nextLine();
              newId=getKeyWords().size()+1;
              keyWords.add(new KeyWord(newId, line));
            }
        }

        public boolean isFullText() {
            return fullText;
        }

        public void setFullText(boolean fullText) {
            this.fullText = fullText;
        }

        public PaperGroup getPaperGroup() {
            return paperGroup;
        }

        public void setPaperGroup(PaperGroup paperGroup) {
            this.paperGroup = paperGroup;
        }

}
