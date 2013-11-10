/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
*
* @author Amir Ghaffari
*/

public class Name {
    @NotBlank
    @Length(max = 40,min=3) 
    private String firstName;
    private String middleName;
    @NotBlank
    @Length(max = 40,min=3) 
    private String lastName;
  
    //constructor to create object with a first and last name
    public Name(String fName, String lName) {
        firstName = fName;
        middleName = "";
        lastName = lName;
    }
  
    //constructor to create object with first, middle and last name
    //if there isn't a middle name, that parameter could be an empty String
    public Name(String fName, String mName, String lName) {
        firstName = fName;
        middleName = mName;
        lastName = lName;
    }
    //constructor to create name from full name
    //in the format first name then space then last name
    //or first name then space then middle name then space then last name
    public Name (String fullName) {
        fullName=fullName.trim();
        int spacePos1 = fullName.indexOf(' ');
        if(spacePos1>0)
        {
        firstName = fullName.substring(0, spacePos1);
        int spacePos2 = fullName.lastIndexOf(' ');
        if (spacePos1 == spacePos2)
              middleName = "";
        else 
              middleName = fullName.substring(spacePos1+1, spacePos2);
        lastName = fullName.substring(spacePos2 + 1);
        }
        else
        {
            firstName = fullName;
            middleName = "";
            lastName = "";
        }
    }
    public void setName (String fullName) {
        fullName=fullName.trim();
        int spacePos1 = fullName.indexOf(' ');
        if(spacePos1>0)
        {
            firstName = fullName.substring(0, spacePos1);
            int spacePos2 = fullName.lastIndexOf(' ');
            if (spacePos1 == spacePos2)
                  middleName = "";
            else 
                  middleName = fullName.substring(spacePos1+1, spacePos2);
            lastName = fullName.substring(spacePos2 + 1);
        }
        else
        {
            firstName = fullName;
            middleName = "";
            lastName = "";
        }
    }
    
    public String getStringFormat() {
    if(this.middleName.trim().compareTo("")==0)
          return this.firstName.concat(" ").concat(this.lastName);
    else
          return this.firstName.concat(" ").concat(this.middleName).concat(" ").concat(this.lastName);
    }
    //returns the first name
    public String getFirstName() {return firstName; }
    //returns the last name
    public String getLastName() {return lastName; }
  
    //change the last name to the value provided in the parameter
    public void setLastName(String ln) {
        lastName = ln;
    }
    public void setFirstName(String fn) {
        firstName = fn;
    }
    //returns the first name then a space then the last name
    public String getFirstAndLastName() {
        return firstName + " " + lastName;
    }
  
    //returns the last name followed by a comma and a space
    //  then the first name
    public String getLastCommaFirst() {
        return lastName + ", "+ firstName;
    }
  
    //returns the full name
    //either first name then space then last name
    //or first name then space then middle name then space
    //  and then last name
    public String getFullName() {
          String result = firstName + " ";
          if (!middleName.equals("")) {
                result += middleName + " ";
          }
          result += lastName;
          return result;
    }
    public String getInitialName() {
        return  firstName.charAt(0)+"."+lastName;
    }
    public int compareTo(Name other) {
        String thisName=lastName + " " + firstName + " " + middleName;
        String othername = other.lastName+ " " + other.firstName + other.middleName;
        return thisName.compareTo(othername);
    }
}
