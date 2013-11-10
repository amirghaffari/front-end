package frontend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class Search implements Serializable {
	private String allCategories;
	private String authors;
        private String title;
	private String abstracts;
	private String fromDateMonth;
        private String fromDateYear;
	private String untilDateMonth;
        private String untilDateYear;
        private String keywords;
        private String tags;

	public Search() {
            this.allCategories="";
            this.authors="";
            this.title="";
            this.abstracts="";
            this.fromDateMonth="";
            this.fromDateYear="";
            this.untilDateMonth="";
            this.untilDateYear="";
            this.keywords="";
            this.tags="";
        }

	public Search(String allCategories, String authors, String title,String abstracts, String fromDateMonth,  String fromDateYear,String untilDateMonth,String untilDateYear, String keywords ) {
            this.allCategories=allCategories;
            this.authors=authors;
            this.title=title;
            this.abstracts=abstracts;
            this.fromDateMonth=fromDateMonth;
            this.fromDateYear=fromDateYear;
            this.untilDateMonth=untilDateMonth;
            this.untilDateYear=untilDateYear;
            this.keywords=keywords;
	}

	public String getAllCategories() {
		return allCategories;
	}
        
	public void setAllCategories(String allCategories) {
		this.allCategories = allCategories;
	}
	
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
        public String getAbstracts() {
            return abstracts;
	}
	public void setAbstracts(String fullText) {
		this.abstracts = fullText;
	}
        public String getFromDateMonth() {
            return fromDateMonth;
	}
	public void setFromDateMonth(String fromDateMonth) {
		this.fromDateMonth = fromDateMonth;
	}
        public String getFromDateYear() {
            return fromDateYear;
	}
	public void setFromDateYear(String fromDateYear) {
		this.fromDateYear = fromDateYear;
	}        
        public String getUntilDateMonth() {
            return untilDateMonth;
	}
	public void setUntilDateMonth(String untilDateMonth) {
		this.untilDateMonth = untilDateMonth;
	}
        public String getUntilDateYear() {
            return untilDateYear;
	}
	public void setUntilDateYear(String untilDateYear) {
		this.untilDateYear = untilDateYear;
	}
        public String getKeywords() {
            return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public List<ConditionMaker> getCondition(){
            List<ConditionMaker> conditionList=new ArrayList<ConditionMaker>();
            /////////////////////////////////////
            allCategories=allCategories.trim();
            authors=authors.trim();
            title=title.trim();
            abstracts=abstracts.trim();
            fromDateMonth=fromDateMonth.trim();
            fromDateYear=fromDateYear.trim();
            untilDateMonth=untilDateMonth.trim();
            untilDateYear=untilDateYear.trim();
            keywords=keywords.trim();
            tags=tags.trim();
            /////////////////////////////////
            if(!allCategories.isEmpty()){
                authors=allCategories;
                title=allCategories;
                abstracts=allCategories;
                fromDateMonth=allCategories;
                fromDateYear=allCategories;
                untilDateMonth=allCategories;
                untilDateYear=allCategories;
                keywords=allCategories;
            }
            if(!authors.isEmpty()){
                conditionList.add( new ConditionMaker(ConditionMaker.AUTHORS_LIST, "name",authors) );
            }
            if(!title.isEmpty()){
                conditionList.add( new ConditionMaker(ConditionMaker.PUBLICATION_LIST, "title",title) );
            }
            if(!abstracts.isEmpty()){
                conditionList.add( new ConditionMaker(ConditionMaker.PUBLICATION_LIST, "abstract",abstracts) );
            }
            if(!abstracts.isEmpty()){
                conditionList.add( new ConditionMaker(ConditionMaker.KEYWORDS, "keyword",keywords) );
            }
            if(!tags.isEmpty()){
                conditionList.add( new ConditionMaker(ConditionMaker.TAG, "name",tags) );
            }
            return conditionList;
        }
}
