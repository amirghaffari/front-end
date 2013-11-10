package frontend.controller;

/**
*
* @author Amir Ghaffari
*/

import frontend.model.Author;
import frontend.model.Login;
import frontend.model.PeerReview;
import frontend.model.PeerReviewTemplate;
import frontend.model.Publication;
import frontend.model.Rating;
import frontend.model.User;
import frontend.service.SampleData;
import frontend.service.WebService;
import frontend.service.interfaces.PublicationServiceInterface;
import frontend.service.interfaces.UserServiceInterface;
import frontend.utils.Util;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewersController {

        @Autowired
        PublicationServiceInterface publicationService;

        @Autowired
        WebService<Rating> ratingWebService;

        @Autowired
        WebService<Author> authorWebService;

        @Autowired
        UserServiceInterface userService;

        boolean islogin=false;

        @ExceptionHandler(Exception.class)
        public ModelAndView handleException(Exception ex) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error");
            mav.addObject("content", ex.getClass()+ex.getMessage());
            return mav;
        }

        @RequestMapping(value="/reviewers", method = RequestMethod.GET)
	public String getpage(final ModelMap modelMap, HttpSession session)
	{
            Login login;
            List<Publication> publicationList=publicationService.getPublicationsForReview(session);
            String loginStatus=userService.getLastLogInStatus(session,Util.ROLE_REFEREE);
            User user=(User)session.getAttribute(Util.USER_SESSION);
            if(user!=null) {
                login=new Login("username", "password","/reviewers",loginStatus,user.getGroupList(),Util.ROLE_REFEREE);
                login.setUsername(user.getUserName());
            }
            else
                login=new Login("username", "password","/reviewers",loginStatus,null,Util.ROLE_REFEREE);
            modelMap.addAttribute("publicationList",publicationList);
            if(publicationList!=null)
                modelMap.addAttribute("CountOfPublication",publicationList.size());
            else
                modelMap.addAttribute("CountOfPublication",0);
            modelMap.addAttribute("login",login);
            modelMap.addAttribute("templates",SampleData.getLocalData(session).getPeerReviewTemplateList());
            return "reviewers";
	}

        @RequestMapping(value="/reviewers", method = RequestMethod.POST)
	public ModelAndView setResult(final ModelMap modelMap, HttpSession session
                , @RequestParam("template") final int templateId
                , @RequestParam("review") final String review
                , @RequestParam("publicationID") final int publicationID)
	{
            PeerReview peerReview=null;
            Publication publication=publicationService.getPublicationById(publicationID, session);
            User referee=(User)session.getAttribute(Util.USER_SESSION);
            if(referee!=null&&templateId!=0) {
                PeerReviewTemplate peerReviewTemplate=SampleData.getLocalData(session).getPeerReviewTemplateId(templateId);
                peerReview=new PeerReview(referee, publication, peerReviewTemplate,  review);
                SampleData.getLocalData(session).addPeerReview(peerReview);
            }

            return new ModelAndView("redirect:reviewers");
	}

}
