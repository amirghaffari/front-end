/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.service.interfaces;

import frontend.model.Rating;
import javax.servlet.http.HttpSession;

/**
*
* @author Amir Ghaffari
*/
public interface RatingServiceInterface {
    public Rating addRating(Rating rating, HttpSession session);
    
}
