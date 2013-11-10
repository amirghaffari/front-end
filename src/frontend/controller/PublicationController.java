package frontend.controller;

/**
*
* @author Amir Ghaffari
*/

import frontend.model.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import frontend.service.interfaces.PublicationServiceInterface;
import frontend.model.Publication;
import frontend.model.Tag;
import frontend.model.User;
import frontend.service.PublicationService;
import frontend.service.SampleData;
import frontend.service.interfaces.UserServiceInterface;
import frontend.utils.Util;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This class is a controller for publication
 * all task about publication is done here
 * @author amir
 */
@Controller
@RequestMapping("/publication")
public class PublicationController {
        @Autowired
        PublicationServiceInterface publicationService;
        @Autowired
        UserServiceInterface userService;

        //An exception method for exception
        @ExceptionHandler(Exception.class)
        public ModelAndView handleException(Exception ex) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error");
            mav.addObject("content", ex.getClass());
            return mav;
        }
        /**
         * return abstract of publication
         * @param publicationId
         * @param session
         * @param request
         * @return 
         */
        @RequestMapping(value="/abstract/{publicationId}", method = RequestMethod.GET)
	public ModelAndView  getAbstract(@PathVariable("publicationId") String publicationId, HttpSession session, HttpServletRequest request)
	{
                List<Tag> tagList=null;
                Login login;
                List<Tag> thisPublicationTagsList=new ArrayList<Tag>();
                String loginStatus=userService.getLastLogInStatus(session,Util.ROLE_READER);
                //calculate the tag's weight
                if(Util.isLocalData(session))
                    tagList=PublicationService.getTagCloud(SampleData.getLocalData(session).getPublicationList());
                else
                    tagList=PublicationService.getTagCloud(publicationService.fetchPublicationWithDetails(null, session));
                User user=(User)session.getAttribute(Util.USER_SESSION);
                if(user!=null){
                    login=new Login("username", "password","/publication/abstract/".concat(publicationId),loginStatus, user.getGroupList(),Util.ROLE_READER);
                    login.setUsername(user.getUserName());
                }
                else
                    login=new Login("username", "password","/publication/abstract/".concat(publicationId),loginStatus, null,Util.ROLE_READER);

                ModelAndView mav = new ModelAndView();
                mav.setViewName("publication_abstract");
                Publication publication= publicationService.getPublicationById(Integer.parseInt(publicationId),session);
                
                for(Tag pubtag: publication.getTags()){
                    for(Tag tagcloud: tagList){
                        if(pubtag.getId()==tagcloud.getId()){
                            thisPublicationTagsList.add(tagcloud);
                            pubtag.setFrequency(tagcloud.getFrequency());
                            
                        }
                    }
                } 

                mav.addObject("publication",publication);
                if(user!=null) login.setUsername(user.getUserName());
                mav.addObject("login",login);
                mav.addObject("user", user);
                mav.addObject("tagCloud",thisPublicationTagsList);
                if(session.getAttribute("rating")!=null)
                    mav.addObject("rating", ((String)session.getAttribute("rating")));
                else
                    mav.addObject("rating","");
                return mav;
	}
        /**
         * add new tag to publication
         * @param session
         * @param tagName
         * @param tagDescription
         * @param backAddress
         * @param publicationId
         * @return 
         */
        @RequestMapping(value="/tag/add", method = RequestMethod.POST)
        public ModelAndView addTag(HttpSession session, @RequestParam("tagName") final String tagName, 
        @RequestParam("tagDescription") final String tagDescription, 
        @RequestParam("backAddress") final String backAddress,
        @RequestParam("publicationId") final int publicationId) {
            if(userService.isLogIn(session)){
                if(!Util.isLocalData(session))
                    publicationService.addTag(session, tagName, tagDescription, publicationId);
                else
                    SampleData.getLocalData(session).addTag(tagName, tagDescription, publicationId);
            }
            return new ModelAndView("redirect:"+backAddress);
            
        }
        /**
         * it is called when user login
         * @param session
         * @param backAddress
         * @param login
         * @return 
         */
        @RequestMapping(value="/login", method = RequestMethod.POST)
        public ModelAndView getPage(HttpSession session, @RequestParam("backAddress") final String backAddress,
        @ModelAttribute("login") Login login) {
            userService.login(login,session);
            return new ModelAndView("redirect:"+backAddress);
        }
        /**
         * called when logout
         * @param session
         * @param backAddress
         * @return 
         */
        @RequestMapping(value="/logout", method = RequestMethod.GET)
        public ModelAndView logout(HttpSession session, @RequestParam("backAddress") final String backAddress) {
            userService.logout(session);
            return new ModelAndView("redirect:"+backAddress);
        }
        /**
         * add comment to the publication
         * @param session
         * @param comment
         * @param backAddress
         * @param publicationId
         * @return 
         */
        @RequestMapping(value="/comment/add", method = RequestMethod.POST)
        public ModelAndView addComment(HttpSession session, @RequestParam("comment") String comment, 
        @RequestParam("backAddress") final String backAddress,
        @RequestParam("publicationId") final int publicationId) {
            User user=(User)session.getAttribute(Util.USER_SESSION);
            if(userService.isLogIn(session)){
                if(!Util.isLocalData(session)){
                    publicationService.addComment(session, comment, publicationId, user);
                }
                else
                    SampleData.getLocalData(session).addComment(comment, publicationId, user);
            }
            return new ModelAndView("redirect:"+backAddress);
        }
        /**
         * delete comment from publication
         * user must be owner of comment
         * @param session
         * @param id
         * @param backAddress
         * @param publicationId
         * @return 
         */
        @RequestMapping(value="/comment/delete", method = RequestMethod.GET)
        public ModelAndView deleteComment(HttpSession session, @RequestParam("id") final int id,
        @RequestParam("backAddress") final String backAddress, @RequestParam("publicationId") final int publicationId) {
            User user=(User)session.getAttribute(Util.USER_SESSION);
            if(userService.isLogIn(session)){
                if(!Util.isLocalData(session)){
                    publicationService.deleteComment(session, id, publicationId, user);
                }
                else
                    SampleData.getLocalData(session).deleteComment(id, user, publicationId);
            }
            return new ModelAndView("redirect:"+backAddress);
        }
        /**
         * add rating to the publication
         * @param session
         * @param rate
         * @param backAddress
         * @param publicationId
         * @return 
         */
        @RequestMapping(value="/rating/add", method = RequestMethod.POST)
        public ModelAndView addRating(HttpSession session, @RequestParam("group") final int rate,
        @RequestParam("backAddress") final String backAddress, @RequestParam("publicationId") final int publicationId) {

                if(!Util.isLocalData(session))
                    publicationService.addRate(session, rate, publicationId);
                else
                    SampleData.getLocalData(session).addRating(rate, publicationId);
                session.setAttribute("rating",Integer.toString(rate));
            return new ModelAndView("redirect:"+backAddress);
        }

}
