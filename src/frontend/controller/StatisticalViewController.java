/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controller;

/**
*
* @author Amir Ghaffari
*/

import frontend.model.Publication;
import frontend.model.Tag;
import frontend.model.comparator.PublicationRateComparator;
import frontend.service.PublicationService;
import frontend.service.SampleData;
import frontend.service.interfaces.PublicationServiceInterface;
import frontend.utils.Util;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author amir
 */




@Controller
@RequestMapping("/statisticalview")
public class StatisticalViewController {

    @Autowired
    PublicationServiceInterface publicationService;

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(final ModelMap modelMap, HttpSession session){
        List<Publication> publicationList,selectedList;
        List<Tag> tagList=null;
        if(Util.isLocalData(session)){
            publicationList=SampleData.getLocalData(session).getPublicationList();
            tagList=PublicationService.getTagCloud(publicationList);
        }
        else{
            publicationList=publicationService.fetchPublicationWithDetails(null, session);
            tagList=PublicationService.getTagCloud(publicationList);
        }
        modelMap.addAttribute("tagCloud",tagList);
        Collections.sort(publicationList, new PublicationRateComparator());
        if(publicationList.size()>10)
            selectedList=publicationList.subList(0, 10);
        else
            selectedList=publicationList;
        modelMap.addAttribute("ratedList",selectedList);
        return "statisticalview";
    }
}
