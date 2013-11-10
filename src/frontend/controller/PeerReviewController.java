/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controller;

/**
*
* @author Amir Ghaffari
*/

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author amir
 */

@Controller
@RequestMapping("/peerreview")
public class PeerReviewController {
    @RequestMapping(method = RequestMethod.GET)
    public String getPage(final ModelMap modelMap){
        modelMap.addAttribute("content","Peer Review");
        return "peerreview";
    }
}
