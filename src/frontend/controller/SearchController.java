package frontend.controller;

/**
*
* @author Amir Ghaffari
*/

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;

import frontend.service.interfaces.SearchServiceInterface;
import frontend.model.Search;
import frontend.model.Publication;
import frontend.utils.Util;
import frontend.model.validators.SearchValidator;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    
        public final static String PAGE_ADVANCED_SEARCH = "advanced_search";    
        public final static String PAGE_ADVANCED_SEARCH_RESULT = "advanced_search_result";
        public final static String URL_SEARCH = "/search";
        
        @Autowired
        SearchServiceInterface searchService;
        
        @ExceptionHandler(Exception.class)
        public ModelAndView handleException(Exception ex) {
            return new ModelAndView("error", "content", ex.getClass());
        }
        
        @RequestMapping(value=URL_SEARCH, method = RequestMethod.GET)
	public ModelAndView getpage()
	{
            Search search=new Search();
            ModelAndView mav = new ModelAndView();
            mav.setViewName(PAGE_ADVANCED_SEARCH);
            mav.addObject("searchItems", search);
            mav.addObject("months",Util.getMonths());
            return mav;
            
	}

        @RequestMapping(value=URL_SEARCH, method = RequestMethod.POST)
        public ModelAndView afterSubmission(@ModelAttribute("searchItems") Search searchItems, BindingResult result,HttpSession session) {
            new SearchValidator().validate(searchItems, result);
            if (result.hasErrors()) {
                ModelAndView mav = new ModelAndView();
                mav.setViewName(PAGE_ADVANCED_SEARCH);
                mav.addObject("searchItems", searchItems);
                mav.addObject("months",Util.getMonths());
                return mav;
            }
            ModelAndView mav = new ModelAndView();
            mav.setViewName(PAGE_ADVANCED_SEARCH_RESULT);
            List<Publication> searchResult = searchService.getSearchResult(searchItems, session);
            mav.addObject("searchResult",searchResult);
            mav.addObject("searchResultCount",searchResult.size());
            return mav;
        }
        @RequestMapping(value=URL_SEARCH+"/tag", method = RequestMethod.GET)
	public ModelAndView gettags(@RequestParam("tagName") final String tagName, HttpSession session)
	{
            ModelAndView mav = new ModelAndView();
            Search searchItems=new Search();
            searchItems.setTags(tagName);
            mav.setViewName(PAGE_ADVANCED_SEARCH_RESULT);
            List<Publication> searchResult = searchService.getSearchResult(searchItems, session);
            mav.addObject("searchResult",searchResult);
            mav.addObject("searchResultCount",searchResult.size());
            return mav;
	}
        @RequestMapping(value=URL_SEARCH+"/keyword", method = RequestMethod.GET)
	public ModelAndView getkeywords(@RequestParam("keyword") final String keyword, HttpSession session)
	{
            ModelAndView mav = new ModelAndView();
            Search searchItems=new Search();
            searchItems.setKeywords(keyword);
            mav.setViewName(PAGE_ADVANCED_SEARCH_RESULT);
            List<Publication> searchResult = searchService.getSearchResult(searchItems, session);
            mav.addObject("searchResult",searchResult);
            mav.addObject("searchResultCount",searchResult.size());
            return mav;
	}
}
