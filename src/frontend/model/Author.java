package frontend.model;
/**
*
* @author Amir Ghaffari
*/

import frontend.utils.Util;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;


public class Author implements ModelInterface{
    public static final String URL=Util.BASE_URL+"author/";
    private long id;
    private Name name;
    private String address;
    private String affiliation;
    @Email
    @Length(max = 50)
    private String email;
    //is not in data model
    private String degree;

    public Author() {
        id=0;
        address="";
        affiliation="";
        email="";
        degree="";
        name=new Name("");
    }

    public Author(long id, String name, String address, String affiliation, String email, String degree) {
        this.id=id;
        this.name=new Name(name);
        this.address=address;
        this.affiliation=affiliation;
        this.email=email;
        this.degree=degree;
    }
    @Override
        public String getURL(){
            return URL;
        }
    @Override
    public int getId() {
            return (int)id;
    }

    @Override
    public void setId(int id) {
            this.id = id;
    }

    public String getName() {
            return name.getFullName();
    }

    public void setName(String name) {
            this.name.setName(name);
    }

    public String getFirstName() {
        return this.name.getFirstName();
    }

    public void setFirstName(String fname) {
        this.name.setFirstName(fname);
    }

    public String getLastName() {
        return this.name.getLastName();
    }

    public void setLastName(String lname) {
        this.name.setLastName(lname);
    }        

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
            this.address = address;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
            this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void copyContent(Author source){
        setAddress(source.getAddress());
        setAffiliation(source.getAffiliation());
        setDegree(source.getDegree());
        setEmail(source.getEmail());
        setId(source.getId());
        setName(source.getName());
    }

}
