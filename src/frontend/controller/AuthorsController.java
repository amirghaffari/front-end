package frontend.controller;

/**
*
* @author Amir Ghaffari
*/

import frontend.model.Author;
import frontend.model.AuthorSubmissionWizard;
import frontend.model.Login;
import frontend.model.Name;
import frontend.model.Publication;
import frontend.model.UploadFile;
import frontend.model.User;
import frontend.model.validators.FileUploadValidator;
import frontend.model.validators.NameValidator;
import frontend.service.SampleData;
import frontend.service.interfaces.AuthorServiceInterface;
import frontend.utils.Util;
import frontend.service.interfaces.PublicationServiceInterface;
import frontend.service.interfaces.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

enum WizarAction {
    Cancel{     
        @Override
        public String toString() {
            return "Cancel";
        }
    }
    , Finish{
        @Override
        public String toString() {
            return "Finish";
        }
    }
    , Next{
        @Override
        public String toString() {
            return "Next";
        }
    }
    , Back{
        @Override
        public String toString() {
            return "Back";
        }
    }
    , Save{
        @Override
        public String toString() {
            return "Save";
        }
    }
    , Reset{
        @Override
        public String toString() {
            return "Reset";
        }
    }
}

@Controller
@SessionAttributes(AuthorsController.WIZARD_SESSION_OBJECT)//"authorSubmissionWizard"
public class AuthorsController {
        public final static String WIZARD_SESSION_OBJECT = "authorSubmissionWizard";

        public final static String URL_AUTHORS = "/authors";
        public final static String URL_AUTHORS_INSTRUCTION = "/authors/instruction";
        public final static String URL_AUTHORS_SUBMISSION_INSTRUCTION = "/authors/submission/instruction";
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_ONE ="/authors/submission/wizard/one";
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_TWO ="/authors/submission/wizard/two";
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_TWO_EDIT ="/authors/submission/wizard/two/edit";
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_TWO_DELETE ="/authors/submission/wizard/two/delete";
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_THREE ="/authors/submission/wizard/three";         
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_FOUR ="/authors/submission/wizard/four";   
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_FOUR_DELETE="/authors/submission/wizard/four/delete";
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_FIVE="/authors/submission/wizard/five";
        public final static String URL_AUTHORS_SUBMISSION_WIZARD_STEP_LAST="/authors/submission/wizard/last";
        public final static String URL_AUTHORS_PROFILES="/authors/profiles";
        public final static String URL_AUTHORS_PREVIOUSQUERY="/authors/previousquery";

        public final static String PAGE_AUTHORS = "authors";
        public final static String PAGE_AUTHORS_REDIRECT = "author_redirect";
        public final static String PAGE_AUTHORS_INSTRUCTION = "authors_instruction";
        public final static String PAGE_AUTHORS_SUBMISSION_INSTRUCTION = "authors_submission_instruction";   
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_ONE = "authors_submission_wizard_one";
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_ONE_REDIRECT = "authors_submission_wizard_one_redirect";
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_TWO = "authors_submission_wizard_two";
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_TWO_REDIRECT = "authors_submission_wizard_two_redirect";
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_THREE = "authors_submission_wizard_three";   
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_THREE_REDIRECT = "authors_submission_wizard_three_redirect";
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR = "authors_submission_wizard_four";   
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR_REDIRECT = "authors_submission_wizard_four_redirect";
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_FIVE = "authors_submission_wizard_five";   
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_FIVE_REDIRECT = "authors_submission_wizard_five_redirect";
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_LAST = "authors_submission_wizard_last";   
        public final static String PAGE_AUTHORS_SUBMISSION_WIZARD_LAST_REDIRECT = "authors_submission_wizard_last_redirect";
        public final static String PAGE_AUTHORS_PROFILES = "authors_profiles";
        public final static String PAGE_AUTHORS_PREVIOUSQUERY = "authors_previous_query";

        @Autowired
        AuthorServiceInterface authorService;

        @Autowired
        PublicationServiceInterface publicationService;        

        @Autowired
        UserServiceInterface userService;
        
        ArrayList<String> listOfWizardPages; 

        public AuthorsController(){
            listOfWizardPages = new ArrayList<String>();
            listOfWizardPages.add(URL_AUTHORS_SUBMISSION_WIZARD_STEP_ONE);
            listOfWizardPages.add(URL_AUTHORS_SUBMISSION_WIZARD_STEP_TWO);
            listOfWizardPages.add(URL_AUTHORS_SUBMISSION_WIZARD_STEP_THREE);
        }

        @ExceptionHandler(Exception.class)
        public ModelAndView handleException(Exception ex) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(Util.PAGE_GLOBAL_ERROR);
            mav.addObject(Util.PAGE_GLOBAL_ERROR_CONTENT, ex.getClass());
            return mav;
        }

        @RequestMapping(value=URL_AUTHORS, method = RequestMethod.GET)
	public String getPage()
	{
                return PAGE_AUTHORS;
	}

        @RequestMapping(value=URL_AUTHORS_INSTRUCTION, method = RequestMethod.GET)
	public String getInstrAuthor()
	{
                return PAGE_AUTHORS_INSTRUCTION;
	}

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_INSTRUCTION, method = RequestMethod.GET)
	public String getInstrSubmit()
	{
                return PAGE_AUTHORS_SUBMISSION_INSTRUCTION;
	}

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_ONE, method = RequestMethod.GET)
	public String startSubmit(final ModelMap modelMap, HttpSession session)
	{
                Login login;
                String loginStatus=userService.getLastLogInStatus(session, Util.ROLE_AUTHOR);
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                if(authorSubmissionWizard==null) authorSubmissionWizard=new AuthorSubmissionWizard();
                authorSubmissionWizard.setCurrentPage(PAGE_AUTHORS_SUBMISSION_WIZARD_ONE);
                User user=(User)session.getAttribute(Util.USER_SESSION);
                if(user!=null) {
                    login=new Login("username", "password",URL_AUTHORS_SUBMISSION_WIZARD_STEP_ONE,loginStatus, user.getGroupList(), Util.ROLE_AUTHOR);
                    login.setUsername(user.getUserName());
                }
                else
                    login=new Login("username", "password",URL_AUTHORS_SUBMISSION_WIZARD_STEP_ONE,loginStatus, null, Util.ROLE_AUTHOR);
                
                
                modelMap.addAttribute("login",login);
                //fill applicatnt info with user info
                authorSubmissionWizard.setApplicant(user);
                modelMap.addAttribute("applicant", authorSubmissionWizard.getApplicant());
                modelMap.addAttribute(WIZARD_SESSION_OBJECT,authorSubmissionWizard);
                return PAGE_AUTHORS_SUBMISSION_WIZARD_ONE;
	}

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_ONE, method = RequestMethod.POST)
        public String AddApplicant(
                @Valid @ModelAttribute("applicant") User applicant, BindingResult result,
                @RequestParam("selected_action") final String selectedAction, 
                final ModelMap modelMap,final SessionStatus status, HttpServletRequest request, HttpSession session) 
        {
                Login login;
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                if(selectedAction.compareToIgnoreCase(WizarAction.Next.toString())==0)
                    if (result.hasErrors()|| !userService.isLogIn(session)) {
                            String loginStatus=userService.getLastLogInStatus(session,Util.ROLE_AUTHOR);
                            User user=(User)session.getAttribute(Util.USER_SESSION);
                            if(user!=null){
                                login=new Login("username", "password",URL_AUTHORS_SUBMISSION_WIZARD_STEP_ONE,loginStatus, user.getGroupList(),Util.ROLE_AUTHOR);
                                login.setUsername(user.getUserName());
                            }
                            else
                                login=new Login("username", "password",URL_AUTHORS_SUBMISSION_WIZARD_STEP_ONE,loginStatus, null,Util.ROLE_AUTHOR);
                            modelMap.addAttribute("login",login);
                            return authorSubmissionWizard.getCurrentPage();
                    }
                String nextPage=getNextSelectedPages(selectedAction,authorSubmissionWizard.getCurrentPage());
                //authorSubmissionWizard.setApplicant(applicant);
                
                //String contextPath = request.getContextPath();
                //String serverName = req.getServerName();
                //int serverPort = req.getServerPort();

                modelMap.addAttribute(WIZARD_SESSION_OBJECT,authorSubmissionWizard);
                modelMap.addAttribute("author",authorSubmissionWizard.createNewAuthor());
                if(selectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) status.setComplete();
                return nextPage;
        }

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_TWO, method = RequestMethod.GET)
	public String stepTwo(final ModelMap modelMap, HttpSession session)
	{
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                authorSubmissionWizard.setCurrentPage(PAGE_AUTHORS_SUBMISSION_WIZARD_TWO);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                modelMap.addAttribute("author",authorSubmissionWizard.getAuther());
                modelMap.addAttribute(AuthorSubmissionWizard.STATUS,authorSubmissionWizard.getStatus());
                authorSubmissionWizard.setStatus(AuthorSubmissionWizard.STATUS_ADD);
                modelMap.addAttribute(Util.ERROR_LIST, authorSubmissionWizard.getErrorList());
                authorSubmissionWizard.setErrorList(null);
                return PAGE_AUTHORS_SUBMISSION_WIZARD_TWO;
	}        

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_TWO, method = RequestMethod.POST)
        public String AddAuthor(@Valid @ModelAttribute("author") Author author, BindingResult result,
            @RequestParam("selected_action") final String selectedAction, 
            final ModelMap modelMap,final SessionStatus status, HttpSession session) {
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);

                if(selectedAction.compareToIgnoreCase(WizarAction.Save.toString())==0) 
                {
                    //validation
                    if (result.hasErrors()) {
                            modelMap.addAttribute(AuthorSubmissionWizard.STATUS,authorSubmissionWizard.getStatus());
                            return authorSubmissionWizard.getCurrentPage();
                    }
                    new NameValidator().validate(new Name(author.getName()) ,result);
                    if (result.hasErrors()) {
                        modelMap.addAttribute(AuthorSubmissionWizard.STATUS,authorSubmissionWizard.getStatus());
                        return authorSubmissionWizard.getCurrentPage();
                    }
                    //end of validation
                    authorSubmissionWizard.saveAuthors(author);
                }
                String nextPage=getNextSelectedPages(selectedAction,PAGE_AUTHORS_SUBMISSION_WIZARD_TWO);
                
                if(selectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) status.setComplete();
                if(selectedAction.compareToIgnoreCase(WizarAction.Next.toString())==0){
                    if(authorSubmissionWizard.getAuthors()==null || authorSubmissionWizard.getAuthors().isEmpty())
                    {
                        String[] errorList={Util.ERROR_MIN_ONE_AUTHOR_IS_NEEDE};
                        authorSubmissionWizard.setErrorList(errorList);
                        nextPage=PAGE_AUTHORS_SUBMISSION_WIZARD_TWO_REDIRECT;
                    }
                }
                return nextPage;
        }

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_TWO_EDIT, method = RequestMethod.GET)
        public String editAuthor(final ModelMap modelMap,HttpSession session,
                @RequestParam("id") final long id) {
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                if(authorSubmissionWizard.getAuthorById(id)!=null){
                    authorSubmissionWizard.setAuther(authorSubmissionWizard.getAuthorById(id));
                    authorSubmissionWizard.setStatus(AuthorSubmissionWizard.STATUS_EDIT);
                }
                else
                {
                    String[] errorList={Util.ERROR_AUTHOR_CODE_NOT_INVALID};
                    authorSubmissionWizard.setErrorList(errorList);
                }
                return PAGE_AUTHORS_SUBMISSION_WIZARD_TWO_REDIRECT;
        }

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_TWO_DELETE, method = RequestMethod.GET)
        public String deleteAuthor(final ModelMap modelMap,HttpSession session,
                @RequestParam("id") final long id) {
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                authorSubmissionWizard.deleteAuthorById(id);
                return PAGE_AUTHORS_SUBMISSION_WIZARD_TWO_REDIRECT;
        }

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_THREE, method = RequestMethod.GET)
	public String stepThree(final ModelMap modelMap, HttpSession session)
	{
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                authorSubmissionWizard.setCurrentPage(PAGE_AUTHORS_SUBMISSION_WIZARD_THREE);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                modelMap.addAttribute("publication",authorSubmissionWizard.getPublication());
                modelMap.addAttribute("PublicationType", authorSubmissionWizard.getPublicationType());
                return PAGE_AUTHORS_SUBMISSION_WIZARD_THREE;
	}                

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_THREE, method = RequestMethod.POST)
        public String stepThree(@Valid @ModelAttribute("publication") Publication publication, BindingResult result,
            @RequestParam("selected_action") final String selectedAction, 
            final ModelMap modelMap,final SessionStatus status, HttpSession session) {
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);

                if(selectedAction.compareToIgnoreCase(WizarAction.Next.toString())==0) 
                {
                    if (result.hasErrors()) {
                            modelMap.addAttribute("PublicationType", authorSubmissionWizard.getPublicationType());
                            return authorSubmissionWizard.getCurrentPage();
                    }
                    authorSubmissionWizard.setPublication(publication);
                }
                String nextPage=getNextSelectedPages(selectedAction,PAGE_AUTHORS_SUBMISSION_WIZARD_THREE);

                if(selectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) status.setComplete();
                return nextPage;
        }

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_FOUR, method = RequestMethod.GET)
	public String stepFour(final ModelMap modelMap, HttpSession session)
	{
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                authorSubmissionWizard.setCurrentPage(PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                modelMap.addAttribute("fileTypes", authorSubmissionWizard.getFileTypes());
                modelMap.addAttribute("uploadFile",authorSubmissionWizard.getUploadFile());
                modelMap.addAttribute(Util.ERROR_LIST, authorSubmissionWizard.getErrorList());
                authorSubmissionWizard.setErrorList(null);
                return PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR;
	}   

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_FOUR, method = RequestMethod.POST)
        public String handleFormUpload(@Valid @ModelAttribute("uploadFile") UploadFile uploadFile, BindingResult result,
            @RequestParam("selected_action") final String selectedAction, 
            final ModelMap modelMap,final SessionStatus status, HttpSession session, HttpServletRequest request) {
                //System.out.println("");
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                if(selectedAction.compareToIgnoreCase(WizarAction.Save.toString())==0) 
                {
                    new FileUploadValidator().validate(uploadFile ,result);
                    if (result.hasErrors()) {
                        for(ObjectError error : result.getAllErrors())
                            System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
                        modelMap.addAttribute("fileTypes", authorSubmissionWizard.getFileTypes());
                        return authorSubmissionWizard.getCurrentPage();
                    }
                    uploadFile.setContextPath(request.getServletContext().getRealPath("/"));
                    String saveResult=uploadFile.saveFile();
                    if(saveResult.trim().equals("")) authorSubmissionWizard.addUploadFilesList(uploadFile);
                    else {
                        String[] errorList={saveResult};
                        authorSubmissionWizard.setErrorList(errorList);
                    }
                        
                }
                String nextPage=getNextSelectedPages(selectedAction,PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR);
                if(selectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) status.setComplete();
                return nextPage;
        }        

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_FOUR_DELETE, method = RequestMethod.GET)
        public String deleteAttachedFile(final ModelMap modelMap,HttpSession session,
                @RequestParam("localFileName") final String localFileName) {
                System.out.println("delete called");
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                authorSubmissionWizard.deleteUploadedFile(localFileName);
                return PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR_REDIRECT;
        }

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_FIVE, method = RequestMethod.GET)
	public String stepFive(final ModelMap modelMap, HttpSession session)
	{
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                authorSubmissionWizard.setCurrentPage(PAGE_AUTHORS_SUBMISSION_WIZARD_FIVE);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                modelMap.addAttribute(Util.ERROR_LIST, authorSubmissionWizard.getErrorList());
                authorSubmissionWizard.setErrorList(null);
                return PAGE_AUTHORS_SUBMISSION_WIZARD_FIVE;
	}

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_FIVE, method = RequestMethod.POST)
        public String stepFive( @RequestParam("selected_action") final String selectedAction, 
            final ModelMap modelMap,final SessionStatus status, HttpSession session, HttpServletRequest request) {
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                authorSubmissionWizard.setCurrentPage(PAGE_AUTHORS_SUBMISSION_WIZARD_FIVE);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                String nextPage=getNextSelectedPages(selectedAction,PAGE_AUTHORS_SUBMISSION_WIZARD_FIVE);
                if(selectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) status.setComplete();
                if(!Util.isLocalData(session))
                    publicationService.applySubmission(session, authorSubmissionWizard);
                else
                    SampleData.getLocalData(session).applySubmission(session, authorSubmissionWizard);
                
                return nextPage;
        }

        @RequestMapping(value=URL_AUTHORS_SUBMISSION_WIZARD_STEP_LAST, method = RequestMethod.GET)
	public String theLastStep(final ModelMap modelMap, HttpSession session)
	{
                AuthorSubmissionWizard authorSubmissionWizard=(AuthorSubmissionWizard) session.getAttribute(WIZARD_SESSION_OBJECT);
                modelMap.addAttribute(WIZARD_SESSION_OBJECT, authorSubmissionWizard);
                
                modelMap.addAttribute("submission", authorSubmissionWizard );
                return PAGE_AUTHORS_SUBMISSION_WIZARD_LAST;
	}        

        private String getNextSelectedPages(String SelectedAction,String CurrentPage)
        {   
            if(PAGE_AUTHORS_SUBMISSION_WIZARD_ONE.compareToIgnoreCase(CurrentPage)==0){
                if(SelectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) return PAGE_AUTHORS_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Next.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_TWO_REDIRECT;
            }
            if(PAGE_AUTHORS_SUBMISSION_WIZARD_TWO.compareToIgnoreCase(CurrentPage)==0) {
                if(SelectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) return PAGE_AUTHORS_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Next.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_THREE_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Back.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_ONE_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Save.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_TWO_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Reset.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_TWO_REDIRECT;
            }
            if(PAGE_AUTHORS_SUBMISSION_WIZARD_THREE.compareToIgnoreCase(CurrentPage)==0) {
                if(SelectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) return PAGE_AUTHORS;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Next.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Back.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_TWO_REDIRECT;
            }
            if(PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR.compareToIgnoreCase(CurrentPage)==0) {
                if(SelectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) return PAGE_AUTHORS;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Save.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Next.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_FIVE_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Back.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_THREE_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Reset.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR_REDIRECT;
            }
            if(PAGE_AUTHORS_SUBMISSION_WIZARD_FIVE.compareToIgnoreCase(CurrentPage)==0) {
                if(SelectedAction.compareToIgnoreCase(WizarAction.Cancel.toString())==0) return PAGE_AUTHORS;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Finish.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_LAST_REDIRECT;
                if(SelectedAction.compareToIgnoreCase(WizarAction.Back.toString())==0) return PAGE_AUTHORS_SUBMISSION_WIZARD_FOUR_REDIRECT;
            }
            return PAGE_AUTHORS;
        }

        @RequestMapping(value=URL_AUTHORS_PROFILES, method = RequestMethod.GET)
	public String authorsProfile(final ModelMap modelMap, HttpSession session)
	{
                //List<Author> authorList=authorService.fetchAuthors( null,session);
                modelMap.addAttribute("authorList",null);//authorList);
                return PAGE_AUTHORS_PROFILES;
	} 

        @RequestMapping(value=URL_AUTHORS_PREVIOUSQUERY, method = RequestMethod.GET)
	public String previousQuery(final ModelMap modelMap, HttpSession session)
	{
            List<Publication> publications=null;

////////////
                Login login;
                String loginStatus=userService.getLastLogInStatus(session, Util.ROLE_AUTHOR);
                User user=(User)session.getAttribute(Util.USER_SESSION);
                if(user!=null) {
                    login=new Login("username", "password",URL_AUTHORS_PREVIOUSQUERY,loginStatus, user.getGroupList(), Util.ROLE_AUTHOR);
                    login.setUsername(user.getUserName());
                }
                else
                    login=new Login("username", "password",URL_AUTHORS_PREVIOUSQUERY,loginStatus, null, Util.ROLE_AUTHOR);

                modelMap.addAttribute("login",login);
            
/////////////
            //User user=(User)session.getAttribute(Util.USER_SESSION);
            if(user!=null) 
                publications=SampleData.getLocalData(session).getUserPublications(user);
            modelMap.addAttribute("publications", publications);
            return PAGE_AUTHORS_PREVIOUSQUERY;
	}        
}
