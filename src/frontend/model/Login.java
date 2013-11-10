/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

import frontend.model.webservice.AtomLink;
import frontend.utils.Util;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class Login implements ModelInterface{
    public static final String URL=Util.BASE_URL+"user/login/";
    private int id;
    private String username;
    private String password;
    private User user;
    private String backAddress;
    private String lastLoginStatus;
    private AtomLink userAtom;
    private List<Group> groupList;
    private String pageGroup;

    public Login() {
        username="";
        password="";
        backAddress="";
        lastLoginStatus="";
        user=new User();
    }

    public Login(String username, String password, String backAddress, String lastLoginStatus,List<Group> groupList, String pageGroup) {
        this.username = username;
        this.password = password;
        this.backAddress=backAddress;
        this.lastLoginStatus=lastLoginStatus;
        this.groupList=groupList;
        this.pageGroup=pageGroup;
        user=new User();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBackAddress() {
        return backAddress;
    }

    public void setBackAddress(String backAddress) {
        this.backAddress = backAddress;
    }

    public String getLastLoginStatus() {
        return lastLoginStatus;
    }

    public void setLastLoginStatus(String lastLoginStatus) {
        this.lastLoginStatus = lastLoginStatus;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getURL() {
        return URL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AtomLink getUserAtom() {
        return userAtom;
    }

    public void setUserAtom(AtomLink userAtom) {
        this.userAtom = userAtom;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> group) {
        this.groupList = group;
    }

    public String getPageGroup() {
        return pageGroup;
    }

    public void setPageGroup(String pageGroup) {
        this.pageGroup = pageGroup;
    }

}
