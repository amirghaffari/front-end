package frontend.controller;

/**
*
* @author Amir Ghaffari
*/

import frontend.model.Group;
import frontend.model.Login;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import frontend.service.interfaces.PublicationServiceInterface;
import frontend.model.Publication;
import frontend.model.ResearchArea;
import frontend.model.Search;
import frontend.model.User;
import frontend.service.SampleData;
import frontend.service.interfaces.UserServiceInterface;
import frontend.utils.Util;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/home")
public class HomeController {

        @ExceptionHandler(Exception.class)
        public ModelAndView handleException(Exception ex) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error");
            mav.addObject("content", ex.getClass());
            return mav;
        }

        @Autowired
        PublicationServiceInterface publicationService;
        @Autowired
        UserServiceInterface userService;

        @RequestMapping(method = RequestMethod.GET)
        public ModelAndView homepage(HttpSession session)
        {
            List<Publication> bookList= publicationService.getRecentPublications("book", session);
            List<Publication> articleList=publicationService.getRecentPublications("article", session);
            ModelAndView mav = new ModelAndView();
            mav.addObject("books", bookList);
            mav.addObject("articles", articleList);
            mav.setViewName("home");
            Search search=new Search();
            mav.addObject("searchItems", search);
            return mav;
        }

        @RequestMapping(value="/createUser", method = RequestMethod.GET)
        public String createUser(HttpSession session, final ModelMap modelMap
            ,@RequestParam("backAddress") final String backAddress
            ,@RequestParam("pageGroup") final String pageGroup){
            modelMap.addAttribute("user", new User());
            modelMap.addAttribute("roleList", SampleData.getLocalData(session).getGroupList());
            modelMap.addAttribute("researchAreaList", SampleData.getLocalData(session).getResearchAreaList());
            modelMap.addAttribute("backAddress", backAddress);
            modelMap.addAttribute("pageGroup", pageGroup);
            return "createUser";
        }

        @RequestMapping(value="/addUser", method = RequestMethod.POST)
        public ModelAndView addPaperGroup(HttpSession session, @ModelAttribute("user") User user,
        @RequestParam("researchAreas") final String researchAreas,
        @RequestParam("roles") final String roles,
        @RequestParam("backAddress") final String backAddress,
        @RequestParam("pageGroup") final String pageGroup)
        {
            
            System.out.println("user name is="+user.getUserName());
            System.out.println("researchAreas is="+researchAreas);
            System.out.println("roles is="+roles);
            System.out.println("backAddress is="+backAddress);
            System.out.println("pageGroup is="+pageGroup);
            String researchAreaList[]=researchAreas.split(",") ;
            for(String researchArea:researchAreaList){
                int researchId=Util.strToInt(researchArea);
                ResearchArea research = SampleData.getLocalData(session).getResearchArea(researchId);
                user.addResearchArea(research);
            }  
            String roleList[]=roles.split(",") ;
            for(String role:roleList){
                int roleId=Util.strToInt(role);
                Group group = SampleData.getLocalData(session).getGroup(roleId);
                user.addRole(group);
            }  
            SampleData.getLocalData(session).addUser(user);
            String loginStatus=userService.getLastLogInStatus(session,pageGroup);
            SampleData.getLocalData(session).login(new Login(user.getUserName(), user.getPassword(), backAddress, loginStatus,user.getGroupList(), pageGroup), session); 
            //return new ModelAndView("redirect:/home");
            return new ModelAndView("redirect:"+backAddress);
        }

}

