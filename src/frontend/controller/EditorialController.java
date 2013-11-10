/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controller;

/**
*
* @author Amir Ghaffari
*/

import frontend.model.Login;
import frontend.model.PaperGroup;
import frontend.model.PeerReview;
import frontend.model.Publication;
import frontend.model.User;
import frontend.service.SampleData;
import frontend.service.interfaces.PublicationServiceInterface;
import frontend.service.interfaces.UserServiceInterface;
import frontend.utils.Util;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author amir
 */

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    @Autowired
    PublicationServiceInterface publicationService;
    
    @Autowired
    UserServiceInterface userService;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("content", ex.getClass());
        return mav;
    }

    @RequestMapping( method = RequestMethod.GET)
    public String getPage(final ModelMap modelMap, HttpSession session){
        PaperGroup paper=null;
        Login login;
        List<Publication> publicationList=publicationService.getPublicationsForEditorial(session);
        String loginStatus=userService.getLastLogInStatus(session,Util.ROLE_EDITORIAL);
        User user=(User)session.getAttribute(Util.USER_SESSION);
        if(user!=null) {
            login=new Login("username", "password","/editorial",loginStatus,user.getGroupList(),Util.ROLE_EDITORIAL);
            login.setUsername(user.getUserName());
        }
        else
            login=new Login("username", "password","/editorial",loginStatus,null,Util.ROLE_EDITORIAL);

        modelMap.addAttribute("publicationList",publicationList);
        if(publicationList!=null)
            modelMap.addAttribute("CountOfPublication",publicationList.size());
        else
            modelMap.addAttribute("CountOfPublication",0);
        modelMap.addAttribute("login",login);
        modelMap.addAttribute("paperGroupList",publicationService.getListOfPaperGroup(session));
        modelMap.addAttribute("reviewers",userService.getUserList(session));
        for(Publication pub:publicationList){
            paper=publicationService.getPaperGroupOfPublication( pub, session);
            pub.setPaperGroup(paper);
        }
        modelMap.addAttribute("paperGroup",new PaperGroup());
        return "editorial";
    }

    @RequestMapping(value="/assignPaperGroup", method = RequestMethod.POST)
    public ModelAndView assignPaperGroup(HttpSession session, @RequestParam("paperGroupId") final int paperGroupId
            , @RequestParam("publicationId") final int publicationId, 
              @RequestParam("reviewStatus") final int reviewStatus){
        int index;
        Publication temp=null;
        Publication publication=publicationService.getPublicationById(publicationId, session);
        publication.setReviewStatus(reviewStatus);
        for(PaperGroup paper:publicationService.getListOfPaperGroup(session)){
            index=0;
            for(Publication pub:paper.getPublicationList()){
                if(pub.getId()==publication.getId()){
                    temp=pub;
                    break;
                }
                ++index;
            }
            if(index<paper.getPublicationList().size()) paper.getPublicationList().remove(temp);
            if(paper.getId()==paperGroupId){
                paper.getPublicationList().add(publication);
                publication.setPaperGroup(paper);
            }
        }
        //publicationService.getPublicationById(publicationId, session).setPaperGroup(paperGroupId);
        return new ModelAndView("redirect:/editorial");
    }

    @RequestMapping(value="/tracking", method = RequestMethod.GET)
    public String tracking(final ModelMap modelMap, HttpSession session, 
    @RequestParam("publicationId") final int publicationId ){
        Publication publication=publicationService.getPublicationById(publicationId, session);
        modelMap.addAttribute("publication",publication);
        List<PeerReview> peerReviewList=SampleData.getLocalData(session).getPeerReviewByPublicaionId(publicationId);
        modelMap.addAttribute("peerReviewList",peerReviewList);
        return "editorial_tracking_publication";
    }

    @RequestMapping(value="/addPaperGroup", method = RequestMethod.POST)
    public ModelAndView addPaperGroup(HttpSession session, @ModelAttribute("paperGroup") PaperGroup paperGroup,
            @RequestParam("selectedReviewers") final String selectedReviewers,
            @RequestParam("publicationId") final int publicationId){
        if(userService.isLogIn(session)){
            if(!Util.isLocalData(session)){
                System.out.println("Online does not work");
            }
            else{
                String referees[]=selectedReviewers.split(",") ;
                for(String referee:referees){
                    int userid=Util.strToInt(referee);
                    User user = SampleData.getLocalData(session).getUser(userid);
                    paperGroup.getReferees().add(user);
                }
                Publication pub= SampleData.getLocalData(session).getPublicationByID(publicationId);
                paperGroup.addPublication(SampleData.getLocalData(session).getPaperGroupList(), pub);
                SampleData.getLocalData(session).addPaperGroup(paperGroup);
            }
        }
        return new ModelAndView("redirect:/editorial");
    }

}
