/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

import frontend.utils.Util;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
*
* @author Amir Ghaffari
*/

public class User implements ModelInterface{

    public static final String URL=Util.BASE_URL+"user/";
    @NotBlank
    @Length(max = 40,min=3) 
    private String firstName;

    @NotBlank
    @Length(max = 40,min=3) 
    private String lastName;

    @NotBlank
    @Email
    @Length(max = 50)
    private String email;    

    int id;
    private String userName;
    //@Size(min = 1, max = 10, message = "Password must between 1 to 10 Characters.")
    private String password;
    private short isStaff;
    private short isActive;
    private short isSuperUser;
    private String lastLogin;
    private String dateJoined;
    private String degree;
    private String institution;
    private Esteem esteem;
    private List<ResearchArea> researchAreaList;
    private List<Group> groupList;

    public User() {
        id=0;
        firstName="";
        lastName="";
        email="";
        userName="";
        password="";
        lastLogin="";
        dateJoined="";
        degree="";
        institution="";
        researchAreaList=new ArrayList<ResearchArea>();
        groupList=new ArrayList<Group>();
    }

    public User(int id, String firstName, String lastName, String email, String userName, String password, List<ResearchArea> researchAreaList, List<Group> groupList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.researchAreaList = researchAreaList;
        lastLogin="";
        dateJoined="";
        degree="";
        institution="";
        this.groupList=groupList;
    }

    public Esteem getEsteems() {
        return esteem;
    }

    public void setEsteems(Esteem esteem) {
        this.esteem = esteem;
    }

    public String getDateJoined() {
        return dateJoined.trim();
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getEmail() {
        return email.trim();
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getFirstName() {
        return firstName.trim();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName.trim();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    public short getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(short isStaff) {
        this.isStaff = isStaff;
    }

    public short getIsSuperUser() {
        return isSuperUser;
    }

    public void setIsSuperUser(short isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

    public String getLastLogin() {
        return lastLogin.trim();
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin.trim();
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public String getUserName() {
        return userName.trim();
    }

    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    public String getDegree() {
        return degree.trim();
    }

    public void setDegree(String degree) {
        this.degree = degree.trim();
    }

    public String getInstitution() {
        return institution.trim();
    }

    public void setInstitution(String institution) {
        this.institution = institution.trim();
    }

    public List<ResearchArea> getResearchAreaList() {
        return researchAreaList;
    }

    public void setResearchAreaList(List<ResearchArea> researchAreaList) {
        this.researchAreaList = researchAreaList;
    }

    @Override
    public String getURL() {
        return URL;
    }

    // should be added to data model
    @Length(max = 80) 
    private String title;
    @Length(max = 80) 
    private String expert;
    @Length(max = 80) 
    private String position;
    @NumberFormat(style = Style.NUMBER)
    private String phone;
    @NumberFormat(style = Style.NUMBER)
    private String faxNumber;
    @Length(max = 280) 
    private String address;

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
/*
    public void copyContent(User user) {
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.id = user.id;
        this.userName = user.userName;
        this.password = user.password;
        this.researchAreaList = user.researchAreaList;
        this.lastLogin=user.lastLogin;
        this.dateJoined=user.dateJoined;
        this.degree=user.degree;
        this.institution=user.institution;
    }

     */
    public void addResearchArea(ResearchArea research){
        for(ResearchArea researchArea:researchAreaList){
            if(researchArea.getId()==research.getId()){
                return;
            }
        }
        researchAreaList.add(research);
    }
    public void addRole(Group group){
        for(Group roles:groupList){
            if(roles.getId()==group.getId()){
                return;
            }
        }
        groupList.add(group);
    }
}
