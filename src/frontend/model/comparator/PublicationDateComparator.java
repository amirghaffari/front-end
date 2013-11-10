/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.comparator;

import frontend.model.Publication;
import java.util.Comparator;

/**
*
* @author Amir Ghaffari
*/
public class PublicationDateComparator implements Comparator<Publication>{

    //descending sort
    @Override
    public int compare(Publication pub1, Publication pub2) {
        int year1,year2;
        short monthVal1,monthVal2;
        String  month1,month2;
        year1=pub1.getYear();
        year2=pub2.getYear();
        if(year1!= year2) {if(year1>year2) return -1; return 1;}
        month1=pub1.getMonth();
        month2=pub2.getMonth();
        monthVal1=getMonthValue(month1);
        monthVal2=getMonthValue(month2);
        if(monthVal1!=0 && monthVal2!=0 && monthVal1!=monthVal2){
            if(monthVal1>monthVal2) return -1;
            return 1;
        }
        long id1,id2;
        id1=pub1.getId();
        id2=pub2.getId();
        if(id1>id2) return -1; 
        return 1;
    }
    private short getMonthValue(String month){
        if(month==null) return 0;
        if(month.toLowerCase().indexOf("jan")!=-1) return 1;
        if(month.toLowerCase().indexOf("feb")!=-1) return 2;
        if(month.toLowerCase().indexOf("mar")!=-1) return 3;
        if(month.toLowerCase().indexOf("apr")!=-1) return 4;
        if(month.toLowerCase().indexOf("may")!=-1) return 5;
        if(month.toLowerCase().indexOf("jun")!=-1) return 6;
        if(month.toLowerCase().indexOf("jul")!=-1) return 7;
        if(month.toLowerCase().indexOf("aug")!=-1) return 8;
        if(month.toLowerCase().indexOf("sep")!=-1) return 9;
        if(month.toLowerCase().indexOf("oct")!=-1) return 10;
        if(month.toLowerCase().indexOf("nov")!=-1) return 11;
        if(month.toLowerCase().indexOf("dec")!=-1) return 12;
        return 0;
        
    }
}
