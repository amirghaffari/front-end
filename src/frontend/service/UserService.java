/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.service;

import frontend.model.Group;
import frontend.model.Login;
import frontend.model.User;
import frontend.service.interfaces.UserServiceInterface;
import frontend.utils.Util;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
* Do login, logout, session tasks
* @author Amir Ghaffari
*/

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WebService<User> userWebService;

    @Override
    public User getUser(String url, HttpSession session) {
        return userWebService.get(User.class, url, session, null);
    }
/**
     * return true if user is login
     * @param session
     * @return 
     */

    @Override
    public boolean isLogIn(HttpSession session) {
        if(session.getAttribute(Util.USER_SESSION_ID)==null || ((String)session.getAttribute(Util.USER_SESSION_ID)).isEmpty() ||((User)session.getAttribute(Util.USER_SESSION))==null){
            logout(session);
            return false;
        }
        else
            return true;
    }
/**
     * return appropriate string message for user status
     * @param session
     * @return 
     */

    @Override
    public String getLastLogInStatus(HttpSession session,String groupName) {
        if(session.getAttribute(Util.LAST_LOGIN_STATUS)==null||((String)session.getAttribute(Util.LAST_LOGIN_STATUS)).isEmpty())
            return Util.LOGIN_STATUS_NO_TRY;
        else{
            
            String result= ((String)session.getAttribute(Util.LAST_LOGIN_STATUS));
            
            if(result.equals(Util.LOGIN_STATUS_FAILD)|| result.equals(Util.LOGIN_STATUS_ROLE_INCONSISTENT)){
                session.setAttribute(Util.LAST_LOGIN_STATUS, Util.LOGIN_STATUS_NO_TRY);
                return result;
            }
            if(!currentUserGroupIsConsistent(groupName,session)) return Util.LOGIN_STATUS_ROLE_INCONSISTENT;
            return Util.LOGIN_STATUS_SUCCESSFUL;
        }
    }

    public boolean currentUserGroupIsConsistent(String groupName,HttpSession session){
        User user=(User)session.getAttribute(Util.USER_SESSION);
        if(user!=null)
        for(Group group:user.getGroupList()){
            if(group.getName().equalsIgnoreCase(groupName)){
                return true;
            }
        }
        return false;
    }

    /**
     * if login be successful return true
     * Read the session ID from Core and Put it in Http cookie header field for next request
     * @param login
     * @param session
     * @return 
     */

    @Override
    public boolean login(Login login, HttpSession session) {
        logout(session);
        if(!Util.isLocalData(session)){
            HttpEntity<Login> entity  = new HttpEntity<Login>(login);
            ResponseEntity<Login> response=null;
            try{
                response = restTemplate.postForEntity(Login.URL, entity, Login.class);
                Login result = response.getBody();
                HttpHeaders headers = response.getHeaders();
                String sessionId=Util.getHeaderValue("set-cookie",headers);
                if(sessionId==null||sessionId.trim().isEmpty()) {
                    session.setAttribute(Util.LAST_LOGIN_STATUS, Util.LOGIN_STATUS_FAILD);
                    return false;
                }
                sessionId=sessionId.substring(0,sessionId.indexOf(";"));
                session.setAttribute(Util.USER_SESSION_ID, sessionId);
                session.setAttribute(Util.LAST_LOGIN_STATUS, Util.LOGIN_STATUS_SUCCESSFUL);
                login.setUserAtom(result.getUserAtom());
                User user = userWebService.get(User.class, login.getUserAtom().getHref(), session, null);
                login.setUser(user);
                session.setAttribute(Util.USER_SESSION,user);
                return true;
            }
            catch(RestClientException ex){
                session.setAttribute(Util.LAST_LOGIN_STATUS, Util.LOGIN_STATUS_FAILD);
                System.out.println("RestClientException in Login:"+ex.getMessage());
                if(ex instanceof HttpServerErrorException){
                    HttpServerErrorException httpServerErrorException=(HttpServerErrorException)ex;
                    throw new IllegalStateException(httpServerErrorException.getResponseBodyAsString());
                }
                return false;
            }
            catch(Exception ex){
                session.setAttribute(Util.LAST_LOGIN_STATUS, Util.LOGIN_STATUS_FAILD);
                System.out.println("Rest Call Exception in Login:"+ex.getMessage());
                return false;
            }
        }
        else{
            return SampleData.getLocalData(session).login(login, session);
        }
    }

/**
     * logout from system
     * @param session 
     */

    @Override
    public void logout(HttpSession session) {
            session.setAttribute(Util.USER_SESSION,null);
            session.setAttribute(Util.USER_SESSION_ID,"");
            session.setAttribute(Util.LAST_LOGIN_STATUS,Util.LOGIN_STATUS_NO_TRY);
    }

    @Override
    public List<User> getUserList(HttpSession session) {
        if(!Util.isLocalData(session)){
            return null;
        }
        return SampleData.getLocalData(session).getRefereeUserList();
    }

}
