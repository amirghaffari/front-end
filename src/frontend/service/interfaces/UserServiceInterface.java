/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.service.interfaces;

import frontend.model.Login;
import frontend.model.User;
import java.util.List;
import javax.servlet.http.HttpSession;


/**
*
* @author Amir Ghaffari
*/
public interface UserServiceInterface {
    public User getUser(String url, HttpSession session);
    public boolean login(Login login, HttpSession session);
    public boolean isLogIn(HttpSession session);
    public String getLastLogInStatus(HttpSession session,String groupName);
    public void logout(HttpSession session);
    public List<User> getUserList(HttpSession session);
}
