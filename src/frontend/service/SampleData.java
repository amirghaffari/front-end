package frontend.service;

import java.util.LinkedList;
import java.util.List;
import frontend.model.Publication;
import frontend.model.Author;
import frontend.model.AuthorSubmissionWizard;
import frontend.model.Comment;
import frontend.model.Group;
import frontend.model.KeyWord;
import frontend.model.Login;
import frontend.model.PaperGroup;
import frontend.model.PeerReview;
import frontend.model.PeerReviewTemplate;
import frontend.model.Rating;
import frontend.model.ReferenceMaterial;
import frontend.model.ResearchArea;
import frontend.model.Search;
import frontend.model.Tag;
import frontend.model.User;
import frontend.model.Vote;
import frontend.utils.Util;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpSession;

/**
*
* @author Amir Ghaffari
*/

public  class  SampleData
{
    private List<Author> authorsList;
    private List<Publication> publicationList;
    private List<User> userList;
    private List<PaperGroup> paperGroupList;
    private List<Group> groupList;
    private List<ResearchArea> researchAreaList;
    private List<PeerReviewTemplate> peerReviewTemplateList;
    private List<PeerReview> peerReviewList;
    private int tagCounter;
    private int ratingCounter;
    private int commentCounter;
    public SampleData(){
                tagCounter=6;
                ratingCounter=4;
                commentCounter=2;
                Author author1=new Author(1001,"Robert Taylor","UK, Edinburgh, Heriot Watt, School of Computer Science", "Heriot Watt" , "something@hw.ac.uk", "Master of Science Student");
                Author author2=new Author(1002,"Hassan Zibari","UK, Edinburgh, Heriot Watt , School of Mathematics,", "Heriot Watt" , "test@hw.ac.uk", "Bachelor Student");
                Author author3=new Author(1003,"Alex Sinabi","UK, Edinburgh, Heriot Watt , Research Group","Heriot Watt" , "sample@hw.ac.uk", "Researcher");
                Author author4=new Author(1004,"Rooby Samth","UK, Edinburgh, Heriot Watt , Research Group", "Heriot Watt", "Rooby_Samths_ample@hw.ac.uk", "Researcher");
                Author author5=new Author(1005,"Jef Smith","UK, Edinburgh, Heriot Watt , Research Group", "Heriot Watt", "Jef_smith_ample@hw.ac.uk","Staff");

                List<Author> bookAuthors;
                bookAuthors = new LinkedList<Author>();
                bookAuthors.add(author1);
                bookAuthors.add(author2);

                ResearchArea researchArea1=new ResearchArea(1, "Parallel Programming", "A parallel programming model is a concept that enables the expression of parallel programs which can be compiled and executed. ");
                ResearchArea researchArea2=new ResearchArea(2, "Functional Programming", "In computer science, functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids state and mutable data.");
                ResearchArea researchArea3=new ResearchArea(3, "Web Programming", "J2EE, .Net, MVC, Spring FrameWord, Entity Framework");

                Tag tag1=new Tag(1,"java","Java is a popular programming language and runtime environment intended to run unchanged on most platforms.");
                Tag tag2=new Tag(2,"C#","C# is a high level general-purpose, managed, garbage-collected, object-oriented programming language created by Microsoft.");
                Tag tag3=new Tag(3,"JavaScript","The common name for the language used primarily for scripting in web browsers. It is NOT related to Java.");
                Tag tag4=new Tag(4,"Parallel Programming","A parallel programming model is a concept that enables the expression of parallel programs which can be compiled and executed.");
                Tag tag5=new Tag(5,"Functional Programming","In computer science, functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids state and mutable data.");
                Tag tag6=new Tag(6,"Distributed Technology","Distributed Technology has been servicing the Financial Services industry since 1999 by providing highly skilled Business and Technology professionals to effectively manage and maximize Fortune 500 initiatives.");

                ReferenceMaterial referenceMaterial1=new ReferenceMaterial(1,"Parallel and Sequential","www.ag275@hw.ac.uk","Parallel computing, on the other hand, uses multiple processing elements simultaneously to solve a problem.");
                ReferenceMaterial referenceMaterial2=new ReferenceMaterial(2,"J2EE and Spring","www.ag275@hw.ac.uk","How Spring can help to developing enterprise project");
                User user0=new User(0,"test","test","reader@hw.ac.uk","testttttt","12345",new ArrayList<ResearchArea>(Arrays.asList(researchArea1,researchArea2)),null);
                User user1=new User(1,"reader","reader","reader@hw.ac.uk","reader","123",new ArrayList<ResearchArea>(Arrays.asList(researchArea1,researchArea2)),new ArrayList<Group>(Arrays.asList(new Group(1,Util.ROLE_READER))));
                User user2=new User(2,"author","author","amir@hw.ac.uk","author","123",new ArrayList<ResearchArea>(Arrays.asList(researchArea1,researchArea2,researchArea3)),new ArrayList<Group>(Arrays.asList(new Group(2,Util.ROLE_AUTHOR),new Group(1,Util.ROLE_READER))));
                User user3=new User(3,"referee","one","referee_one@hw.ac.uk","referee1","123",new ArrayList<ResearchArea>(Arrays.asList(researchArea2,researchArea3)), new ArrayList<Group>(Arrays.asList(new Group(3,Util.ROLE_REFEREE),new Group(1,Util.ROLE_READER))));
                User user4=new User(4,"referee","two","referee_two@hw.ac.uk","referee2","123",new ArrayList<ResearchArea>(Arrays.asList(researchArea2,researchArea3)), new ArrayList<Group>(Arrays.asList(new Group(3,Util.ROLE_REFEREE),new Group(1,Util.ROLE_READER))));
                User user5=new User(5,"editorial","","editorial@hw.ac.uk","editorial","123",new ArrayList<ResearchArea>(Arrays.asList(researchArea2,researchArea3)), new ArrayList<Group>(Arrays.asList(new Group(4,Util.ROLE_EDITORIAL),new Group(1,Util.ROLE_READER))));
                Vote vote1=new Vote(1, "up", user1);
                Vote vote2=new Vote(2, "down", user2);

                Comment comment1=new Comment(1, "comment1", "Hi,\n How about exception ? How to catch and throw the exception in xml,json format ?\n Regards,\n Cyril","18 August 2011", user1 ,new ArrayList<Vote>(Arrays.asList(vote1)));
                Comment comment2=new Comment(2, "comment2", "Thanks for sharing this. Fantastic work. \n Cheers","28 April 2010", user2 ,new ArrayList<Vote>(Arrays.asList(vote1, vote2)));

                Rating rating1=new Rating(1,(short)2);
                Rating rating2=new Rating(2,(short)3);
                Rating rating3=new Rating(3,(short)1);
                Rating rating4=new Rating(4,(short)5);

                KeyWord keyword1=new KeyWord(1, "java");
                KeyWord keyword2=new KeyWord(2, "Spring");
                KeyWord keyword3=new KeyWord(3, "parallel");
                KeyWord keyword4=new KeyWord(4, "functional");
                KeyWord keyword5=new KeyWord(5, "distributed");

                Publication publication1 = new Publication(2001, "School of Computer, Heriot-Watt University, Riccarton, Edinburgh, UK  ", " Parallel computing ", "chapter", "Second Edition", "editor",
                "howPublished", "institution", "978-0-596-5128-0", "journal",
                "number" , "organization" , "670", "Sample Media, Inc.", "series" ,  "book",
                "volume", " Parallel computing" ,  "March",  "This book is dedicated to whoever decided that learn Java", 2008, user0,bookAuthors, new ArrayList<KeyWord>(Arrays.asList(keyword1, keyword3)),new ArrayList<Tag>(Arrays.asList(tag4,tag5)),
                "Parallel computing is a form of computation in which many calculations are carried out simultaneously,[1] operating on the principle that large problems can often be divided into smaller ones, which are then solved concurrently. There are several different forms of parallel computing: bit-level, instruction level, data, and task parallelism. Parallelism has been employed for many years, mainly in high-performance computing, but interest in it has grown lately due to the physical constraints preventing frequency scaling."
                ,new ArrayList<ReferenceMaterial>(Arrays.asList(referenceMaterial1, referenceMaterial2)),new ArrayList<Comment>(Arrays.asList(comment1,comment2)),new ArrayList<Rating>(Arrays.asList(rating1,rating1)),false,1/*review status 1= public*/);

                Publication publication2 = new Publication(2002, "School of Mathematical, Heriot-Watt University, Riccarton, Edinburgh, UK ", "Functional Programming ", "chapter", "Third Edition", "editor",
                "howPublished", "institution", "91-0-596-51668-0", "journal",
                "number" , "organization" , "345", "Good Media, Inc.", "series" ,  "book",
                "volume", "Functional Programming " ,  "March",  "This book is dedicated to whoever decided that learn Spring", 2010, user0,new ArrayList<Author>(Arrays.asList(author3,author1)),new ArrayList<KeyWord>(Arrays.asList(keyword1, keyword2)), new ArrayList<Tag>(Arrays.asList(tag4,tag5))
                ,"In computer science, functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids state and mutable data. It emphasizes the application of functions, in contrast to the imperative programming style, which emphasizes changes in state.[1] Functional programming has its roots in lambda calculus, a formal system developed in the 1930s to investigate function definition, function application, and recursion. Many functional programming languages can be viewed as elaborations on the lambda calculus."
                ,new ArrayList<ReferenceMaterial>(Arrays.asList(referenceMaterial1)),new ArrayList<Comment>(Arrays.asList(comment1)),new ArrayList<Rating>(Arrays.asList(rating1,rating3)),true,1/*review status 1= public*/); 

                List<Author> articleAuthors;
                articleAuthors = new LinkedList<Author>();
                articleAuthors.add(author4);
                articleAuthors.add(author5);
                Publication publication3 = new Publication(2003, "School of Computer, Heriot-Watt University, Riccarton, Edinburgh, UK  ", " Advantages of JEE ", "chapter", "Edition", "editor",
                "howPublished", "institution", "978-0-596-9928-0", "journal",
                "number" , "organization" , "30", "Best Media, Inc.", "series" ,  "article",
                "volume", "Advantages Of JEE" ,  "March",  "This book is dedicated to whoever decided that learn J2EE", 2007, user0,articleAuthors, Arrays.asList(keyword1, keyword3), new ArrayList<Tag>(Arrays.asList(tag1, tag3)),
                "Java Platform, Enterprise Edition or Java EE is a widely used platform for server programming in the Java programming language. The Java platform (Enterprise Edition) differs from the Java Standard Edition Platform (Java SE) in that it adds libraries which provide functionality to deploy fault-tolerant, distributed, multi-tier Java software, based largely on modular components running on an application server[1]. Java EE is defined by its specification. As with other Java Community Process specifications, providers must meet certain conformance requirements in order to declare their products as Java EE compliant."
                ,new ArrayList<ReferenceMaterial>(Arrays.asList(referenceMaterial1, referenceMaterial2)),new ArrayList<Comment>(Arrays.asList(comment2)),null,false,1/*review status 1= public*/);

                Publication publication4 = new Publication(2004, "School of Mathematical, Heriot-Watt University, Riccarton, Edinburgh, UK ", " Spring FrameWork For Enterprise Projects ", "chapter", " Edition", "editor",
                "howPublished", "institution", "45-0-596-79390-0", "journal",
                "number" , "organization" , "12", "Good Media, Inc.", "series" ,  "article",
                "volume", "Spring FrameWork For Enterprise Projects" ,  "March",  "This book is dedicated to whoever decided that know How FrameWork help us", 2011, user0,new ArrayList<Author>(Arrays.asList(author5)) , new ArrayList<KeyWord>(Arrays.asList(keyword2, keyword3)), new ArrayList<Tag>(Arrays.asList(tag1, tag2, tag3)),
                "The Spring Framework is an open source application framework for the Java platform. The Spring Framework has its own AOP framework which modularizes cross-cutting concerns in aspects. The motivation for creating a separate AOP framework comes from the belief that it would be possible to provide basic AOP features without too much complexity in either design, implementation, or configuration. The Spring AOP framework also takes full advantage of the Spring Container. The Spring AOP framework is interception based, and is configured at run time. This removes the need for a compilation step or load-time weaving. On the other hand, interception only allows for public or protected method-execution on existing objects at a join point."
                ,new ArrayList<ReferenceMaterial>(Arrays.asList(referenceMaterial1)),null,new ArrayList<Rating>(Arrays.asList(rating2,rating3,rating4)),true,1/*review status 1= public*/);                 

                Publication publication5 = new Publication(2005, "School of Mathematical, Heriot-Watt University, Riccarton, Edinburgh, UK ", " Software FrameWork Features ", "chapter", " Edition", "editor",
                "howPublished", "institution", "81-5-987-43938-4", "journal",
                "number" , "organization" , "7", "Good Media, Inc.", "series" ,  "article",
                "volume", "Software FrameWork Features" ,  "March",  "Digital Liberary", 2010, user0,new ArrayList<Author>(Arrays.asList(author5,author3)) , new ArrayList<KeyWord>(Arrays.asList(keyword1)), null,
                "In computer programming, a software framework is an abstraction in which software providing generic functionality can be selectively changed by user code, thus providing application specific software. It is a collection of software libraries providing a defined application programming interface (API). The designers of software frameworks aim to facilitate software development by allowing designers and programmers to devote their time to meeting software requirements rather than dealing with the more standard low-level details of providing a working system, thereby reducing overall development time.[3] For example, a team using a web application framework to develop a banking web-site can focus on the operations of account withdrawals rather than the mechanics of request handling and state management."
                ,new ArrayList<ReferenceMaterial>(Arrays.asList(referenceMaterial1,referenceMaterial2)),null,new ArrayList<Rating>(Arrays.asList(rating1,rating3,rating4)),false,1/*review status 1= public*/);      

                Publication publication6 = new Publication(2006, "School of Mathematical, Heriot-Watt University, Riccarton, Edinburgh, UK ", " Erlang And Functional programming ", "chapter", " Edition", "editor",
                "howPublished", "institution", "14-0-567-73453-3", "journal",
                "number" , "organization" , "7", "Good Media, Inc.", "series" ,  "article",
                "volume", "Erlang And Functional programming " ,  "August",  "Digital Liberary", 2010, user0,new ArrayList<Author>(Arrays.asList(author5)) , new ArrayList<KeyWord>(Arrays.asList( keyword4, keyword5)), new ArrayList<Tag>(Arrays.asList(tag6, tag4, tag5)),
                "Erlang is a programming language used to build massively scalable soft real-time systems with requirements on high availability. Some of its uses are in telecoms, banking, e-commerce, computer telephony and instant messaging. Erlang's runtime system has built-in support for concurrency, distribution and fault tolerance."
                ,new ArrayList<ReferenceMaterial>(Arrays.asList(referenceMaterial1,referenceMaterial2)),null,new ArrayList<Rating>(Arrays.asList(rating1,rating2,rating3,rating4)),false,1/*review status 1= public*/);    
                
                Publication publication7 = new Publication(2007, "School of Mathematical, Heriot-Watt University, Riccarton, Edinburgh, UK ", " Haskell Functional programming ", "chapter", " Edition", "editor",
                "howPublished", "institution", "87-7-967-42503-4", "journal",
                "number" , "organization" , "7", "Good Media, Inc.", "series" ,  "article",
                "volume", "Haskell Functional programming" ,  "August",  "Digital Liberary", 2009, user0,new ArrayList<Author>(Arrays.asList(author1,author3)) , new ArrayList<KeyWord>(Arrays.asList( keyword4, keyword5)), new ArrayList<Tag>(Arrays.asList(tag6, tag5)),
                "Haskell is an advanced purely-functional programming language. An open-source product of more than twenty years of cutting-edge research, it allows rapid development of robust, concise, correct software. With strong support for integration with other languages, built-in concurrency and parallelism, debuggers, profilers, rich libraries and an active community, Haskell makes it easier to produce flexible, maintainable, high-quality software."
                ,new ArrayList<ReferenceMaterial>(Arrays.asList(referenceMaterial1,referenceMaterial2)),null,new ArrayList<Rating>(Arrays.asList(rating1,rating3,rating4)),false,1/*review status 1= public*/); 
                
                Publication publication8 = new Publication(2008, "School of Mathematical, Heriot-Watt University, Riccarton, Edinburgh, UK ", " Concurrent and Parallel Haskell ", "chapter", " Edition", "editor",
                "howPublished", "institution", "14-0-567-73453-3", "journal",
                "number" , "organization" , "7", "Good Media, Inc.", "series" ,  "book",
                "volume", "Concurrent and Parallel Haskell" ,  "August",  "Digital Liberary", 2009, user0,new ArrayList<Author>(Arrays.asList(author4,author2)) , new ArrayList<KeyWord>(Arrays.asList( keyword4, keyword5)), new ArrayList<Tag>(Arrays.asList(tag6, tag5)),
                "Concurrent Haskell is the name given to GHC's concurrency extension. It is enabled by default, so no special flags are required. The Concurrent Haskell paper is still an excellent resource, as is Tackling the awkward squad."
                ,new ArrayList<ReferenceMaterial>(Arrays.asList(referenceMaterial1,referenceMaterial2)),null,new ArrayList<Rating>(Arrays.asList(rating1,rating3,rating4)),false,1/*review status 1= public*/);      

                authorsList = new LinkedList<Author>();
                authorsList.add(author1);
                authorsList.add(author2);
                authorsList.add(author3);
                authorsList.add(author4);
                authorsList.add(author5);

                publicationList = new LinkedList<Publication>();
                publicationList.add(publication1);
                publicationList.add(publication2);
                publicationList.add(publication3);
                publicationList.add(publication4);
                publicationList.add(publication5);
                publicationList.add(publication6);
                publicationList.add(publication7);
                publicationList.add(publication8);

                userList = new ArrayList<User>();
                userList.add(user1);
                userList.add(user2);
                userList.add(user3);
                userList.add(user4);
                userList.add(user5);

                PaperGroup paperGroup1=new PaperGroup(1, "Parallel Programming","A parallel programming model is a concept that enables the expression of parallel programs which can be compiled and executed.", 1, new ArrayList<User>(Arrays.asList(user3,user4)));
                PaperGroup paperGroup2=new PaperGroup(2, "Functional Programming","In computer science, functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids state and mutable data.", 0, new ArrayList<User>(Arrays.asList(user3)));
                PaperGroup paperGroup3=new PaperGroup(3, "Distributed Technology","Distributed Technology has been servicing the Financial Services industry since 1999 by providing highly skilled Business and Technology professionals to effectively manage and maximize Fortune 500 initiatives.", 1, new ArrayList<User>(Arrays.asList(user4)));
                paperGroupList=new ArrayList<PaperGroup>();
                paperGroupList.add(paperGroup1);
                paperGroupList.add(paperGroup2);
                paperGroupList.add(paperGroup3);

                groupList=new ArrayList<Group>();
                groupList.add(new Group(1,Util.ROLE_READER));
                groupList.add(new Group(2,Util.ROLE_AUTHOR));
                groupList.add(new Group(3,Util.ROLE_REFEREE));
                groupList.add(new Group(4,Util.ROLE_EDITORIAL));

                researchAreaList=new ArrayList<ResearchArea>();
                researchAreaList.add(researchArea1);
                researchAreaList.add(researchArea2);
                researchAreaList.add(researchArea3);
                peerReviewTemplateList=new ArrayList<PeerReviewTemplate>();
                PeerReviewTemplate template1=new PeerReviewTemplate(1,"Reject","The article is rejected because of:\r\n The article is too long.\r\n Wide range of poorly-evidenced speculations concerning a loose variety of topics. \r\n There is not detailed analysis in the abstract paper and lack of references. ");
                PeerReviewTemplate template2=new PeerReviewTemplate(2,"Accepted","This article is accepted because of:\r\n It clearly state the nature and significance of the problem \r\n It includes the title and/or log number of the previously published paper. \r\n The authors can provide a rationale in the Introduction by showing both that an important problem exists and that previous investigators have failed to adequately address the problem.");
                PeerReviewTemplate template3=new PeerReviewTemplate(3,"Modification","This article needs some review because of:\r\n Please be sure to keep your main manuscript as number 1 in the list. Using the keywords from the keyword list is essential to the review process because ScholarOne Manuscripts links them to names of potential reviewers who are associated with that area of expertise. \r\n");
                peerReviewTemplateList.add(template1);
                peerReviewTemplateList.add(template2);
                peerReviewTemplateList.add(template3);
                peerReviewList=new ArrayList<PeerReview>();

    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public List<Author> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<Author> authorsList) {
        this.authorsList = authorsList;
    }

    public List<Publication> getPublicationList() {
        return publicationList;
    }

    public void setPublicationList(List<Publication> publicationList) {
        this.publicationList = publicationList;
    }

    public List<User> getRefereeUserList() {
        boolean find=false;
        List<User> result=new ArrayList<User>();
        for(User user:userList){
            find=false;
            for(Group group:user.getGroupList()){
                if(group.getName().equalsIgnoreCase(Util.ROLE_REFEREE)) find=true;
            }
            if(find)
                result.add(user);
        }
        return result;
    }

    public User getUser(int id) {
        for(User user: userList)
            if(user.getId()==id) return user;
        return null;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Publication getPublicationByID(int id)
    {
        for (Publication p : publicationList)
        {
            if(p.getId()==id)
                return p;
        }
        return null;
    }

    public List<Publication> getPublicationByType(String type)
    {
        List<Publication> result=new ArrayList<Publication>();
        for (Publication p : publicationList)
        {
            if(p.getPublicationType().equalsIgnoreCase(type)&&
              (p.getReviewStatus()==1 /*Public*/ || p.getReviewStatus()==2 /*Reviewed*/))
                result.add(p);
        }
        return result;
    }

    public List<Publication> getPublicationsForEditorial()
    {
        List<Publication> result=new ArrayList<Publication>();
        for (Publication p : publicationList)
        {
            if(p.getReviewStatus()!=1/*Public*/&& p.getReviewStatus()!=2/*Reviewed*/) //{"Public"/*1*/,"Reviewed"/*2*/,"Rejected"/*3*/,"Incompleted"/*4*/,"Being reviewed"/*5*/};
                result.add(p);
        }
        return result;
    }

    public List<Publication> getPublicationsForReview(String userName)
    {
        boolean find=false;
        List<Publication> result=new ArrayList<Publication>();
        for (Publication p : publicationList)
        {
            if(p.getReviewStatus()==5 && p.getPaperGroup()!=null/*Being reviewed*/) //{"Public"/*1*/,"Reviewed"/*2*/,"Rejected"/*3*/,"Incompleted"/*4*/,"Being reviewed"/*5*/};
                for(User referee: p.getPaperGroup().getReferees())
                    if(referee.getUserName().equalsIgnoreCase(userName))
                    {
                        find=false;
                        for(PeerReview peerReview: peerReviewList){
                            if(peerReview.getPublication().getId()==p.getId() && peerReview.getReferee().getUserName().equalsIgnoreCase(userName)) find=true;
                        }
                        if(!find)
                            result.add(p);
                    }
        }
        return result;
    }

    public List<Publication> getAllPublications()
    {   List<Publication> pubList=new ArrayList<Publication>();
        for(Publication pub:publicationList){
            if(pub.getReviewStatus()==1 /*Public*/ || pub.getReviewStatus()==2 /*Reviewed*/)
            pubList.add(pub);
        }
        return pubList;
    }

    public List<Author> getAllAuthors()
    {
        return authorsList;
    }

    public boolean login(Login login, HttpSession session)
    {
        for(User user: userList){
            if(user.getUserName().equals(login.getUsername())&&user.getPassword().equals(login.getPassword())) {
                for(Group group: user.getGroupList()){
                    if(login!=null && group.getName().equalsIgnoreCase(login.getPageGroup())){
                        session.setAttribute(Util.USER_SESSION_ID, Integer.toString(user.getId()));
                        session.setAttribute(Util.LAST_LOGIN_STATUS, Util.LOGIN_STATUS_SUCCESSFUL);
                        session.setAttribute(Util.USER_SESSION,user);
                        System.out.println("Login successful");
                        return true;
                    }
                }
                session.setAttribute(Util.LAST_LOGIN_STATUS, Util.LOGIN_STATUS_ROLE_INCONSISTENT);
                System.out.println("Login role inconsistent");
            }
        }
        System.out.println("Login failed");
        session.setAttribute(Util.LAST_LOGIN_STATUS, Util.LOGIN_STATUS_FAILD);
        return false;
    }

    public boolean addTag(String tagName, String tagDescription,int publicationId)
    {
        Publication p=getPublicationByID( publicationId);
        if(p==null){
            System.out.println("Error in add new local Tag. Can not find appropriate publication");
            return false;
        }
        Tag tag=new Tag(++tagCounter,tagName,tagDescription);
        p.getTags().add(tag);
        return true;
    }

    public boolean addComment(String text,int publicationId, User user)
    {
        Publication p=getPublicationByID(publicationId);
        if(p==null){
            System.out.println("Error in add new local Comment. Can not find appropriate publication");
            return false;
        }
        Comment comment=new Comment();
        comment.setId(++commentCounter);
        comment.setText(text);
        comment.setPublicationId(publicationId);
        comment.setUser(user);
        p.getComments().add(comment);
        return true;
    }

    public boolean deleteComment(int commentId, User user, int publicationId)
    {
        Publication p=getPublicationByID( publicationId);
        if(p==null){
            System.out.println("Error in delete Comment id("+commentId+"). Can not find appropriate publication");
            return false;
        }
        int index=0;
        for(Comment comment: p.getComments()){
            if(comment.getId()==commentId && comment.getUser().getId()==user.getId()){
                p.getComments().remove(index);
                return true;
            }
            ++index;
        }
        return false;
    }

    public Rating addRating(int rate, int publicationId)
    {
        Publication p=getPublicationByID( publicationId);
        Rating rating=new Rating();
        if(p==null){
            System.out.println("Error in add Rate value("+rate+"). Can not find appropriate publication");
            return null;
        }
        rating.setId(++ratingCounter);
        rating.setRating((short)rate);
        rating.setPublicationId(publicationId);
        p.getRatings().add(rating);
        return rating;
    }

    public static SampleData getLocalData(HttpSession session){
        if(session.getAttribute("sampleData")==null)
            session.setAttribute("sampleData", new SampleData());
        return ((SampleData)session.getAttribute("sampleData"));
    }

    public List<Publication> applySearch(Search search)
    {
        List<Publication> result=new ArrayList<Publication>();

        String allCategories;
        String author;
        String title;
        String abstracts;
        String fromDateYear;
        String untilDateYear;
        String keyword;
        String tag;

        author=search.getAuthors().toLowerCase();
        keyword=search.getKeywords().toLowerCase();
        title=search.getTitle().toLowerCase();
        abstracts=search.getAbstracts().toLowerCase();
        fromDateYear=search.getFromDateYear().toLowerCase();
        untilDateYear=search.getUntilDateYear().toLowerCase();
        allCategories=search.getAllCategories().toLowerCase();
        tag=search.getTags().toLowerCase();
        if(!allCategories.trim().isEmpty()){
            author=allCategories.toLowerCase();
            keyword=allCategories.toLowerCase();
            title=allCategories.toLowerCase();
            abstracts=allCategories.toLowerCase();
            fromDateYear=allCategories.toLowerCase();
            untilDateYear=allCategories.toLowerCase();
            tag=allCategories.toLowerCase();
        }

        if(allCategories.trim().isEmpty() &&
                allCategories.trim().isEmpty() &&
                author.trim().isEmpty() &&
                keyword.trim().isEmpty() &&
                title.trim().isEmpty() &&
                abstracts.trim().isEmpty() &&
                fromDateYear.trim().isEmpty() &&
                untilDateYear.trim().isEmpty() &&
                tag.trim().isEmpty()
                ) 
            return publicationList;

        for (Publication p : publicationList)
        {
            if(p.getReviewStatus()==1 /*Public*/ || p.getReviewStatus()==2 /*Reviewed*/){
                if(!author.isEmpty())
                for(Author a: p.getAuthors()){
                    if(a.getName().toLowerCase().contains(author))
                        addToList(result,p);
                }
                if(!keyword.isEmpty())
                for(KeyWord k: p.getKeyWords()){
                    if(k.getKeyWord().toLowerCase().contains(keyword))
                        addToList(result,p);
                }
                if(!tag.isEmpty())
                for(Tag t: p.getTags()){
                    if(t.getName().toLowerCase().contains(tag))
                        addToList(result,p);
                }
                if(!title.isEmpty())
                if(p.getTitle().toLowerCase().contains(title))
                        addToList(result,p);
                if(!abstracts.isEmpty())
                if(p.getAbstracts().toLowerCase().contains(abstracts))
                        addToList(result,p);
                if(Util.strToInt(fromDateYear)>0 && Util.strToInt(untilDateYear)>0)
                if(p.getYear()>=Util.strToInt(fromDateYear) && p.getYear()<=Util.strToInt(untilDateYear))
                        addToList(result,p);
            
            }
        }
        return result;
    }

    private void addToList(List<Publication> publicationList, Publication pub){
        for(Publication p: publicationList){
            if(p.getId()==pub.getId()) return;
        }
        publicationList.add(pub);
    }

    public void applySubmission(HttpSession session, AuthorSubmissionWizard authorSubmissionWizard){     
        int max_id=0;
        Publication publication=authorSubmissionWizard.getPublication();
        /////find max author id
        max_id=0;
        for(Author au: authorsList){
            if(au.getId()>max_id) max_id=au.getId();
        }
        /////end of find max author id        
        for(Author author:authorSubmissionWizard.getAuthors()){
            author.setId(max_id+1);
            ++max_id;
        }
        ///////////////////add applicant as a user
        publication.setAuthors(authorSubmissionWizard.getAuthors());

        ////////////////user
        User user = authorSubmissionWizard.getApplicant();
        publication.setOwner(user);
        ////////end of user

        /////find max publication id
        max_id=0;
        for(Publication pub: publicationList){
            if(pub.getId()>max_id) max_id=pub.getId();
        }
        /////end of find max publication id
        publication.setId(max_id+1);
        publicationList.add(publication);
    }

    public List<PaperGroup> getPaperGroupList() {
        return paperGroupList;
    }

    public void setPaperGroupList(List<PaperGroup> paperGroupList) {
        this.paperGroupList = paperGroupList;
    }

    public boolean addPaperGroup(PaperGroup paperGroup)
    {
        /////find max author id
        int max_id=0;
        for(PaperGroup paper: paperGroupList){
            if(paper.getId()>max_id) max_id=paper.getId();
        }
        /////end of find max author id
        paperGroup.setId(max_id+1);
        paperGroupList.add(paperGroup);
        return true;
    }
    public boolean addUser(User newUser)
    {
        int max_id=0;
        for(User user:userList){
            if(user.getUserName().equalsIgnoreCase(newUser.getUserName())){
                System.out.println("Adding user was not successful:"+newUser.getUserName());
                return false;
            }
            if(user.getId()>max_id) max_id=user.getId();
        }
        newUser.setId(max_id);
        userList.add(newUser);
        System.out.println("User was add successfully with id="+newUser.getId()+" user name="+newUser.getUserName());
        return true;
    }

    public List<ResearchArea> getResearchAreaList() {
        return researchAreaList;
    }

    public ResearchArea getResearchArea(int id){
        for(ResearchArea research:researchAreaList){
            if(research.getId()==id){
                return research;
            }
        }
        return null;
    }

    public Group getGroup(int id){
        for(Group group:groupList){
            if(group.getId()==id){
                return group;
            }
        }
        return null;
    }

    public List<PeerReviewTemplate> getPeerReviewTemplateList() {
        return peerReviewTemplateList;
    }

    public void setPeerReviewTemplateList(List<PeerReviewTemplate> peerReviewTemplateList) {
        this.peerReviewTemplateList = peerReviewTemplateList;
    }

    public void addPeerReview(PeerReview peerReview){
        peerReviewList.add(peerReview);
    }
    public List<PeerReview> getPeerReviewByPublicaionId(int publicationId){
        List<PeerReview> resultList=new ArrayList<PeerReview>();
        for(PeerReview peerReview:peerReviewList){
            if(peerReview.getPublication().getId()==publicationId){
                resultList.add(peerReview);
            }
        }
        return resultList;
    }

    public PeerReviewTemplate getPeerReviewTemplateId(int Id){
        for(PeerReviewTemplate peerReviewTemplate:peerReviewTemplateList){
            if(peerReviewTemplate.getId()==Id){
                return peerReviewTemplate;
            }
        }
        return null;
    }
    public List<Publication> getUserPublications(User user)
    {   List<Publication> pubList=new ArrayList<Publication>();
        for(Publication pub:publicationList){
            if(pub.getOwner().getUserName().equalsIgnoreCase(user.getUserName()))
                pubList.add(pub);
            System.out.println("Publication="+pub.getOwner().getUserName()+" Current user="+user.getUserName());
        }
        return pubList;
    }
}
