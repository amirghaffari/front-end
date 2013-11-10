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
public class PublicationRateComparator implements Comparator<Publication>{

    //descending sort
    @Override
    public int compare(Publication pub1, Publication pub2) {
        int result=(pub1.getAverageOfRates()>pub2.getAverageOfRates()? -1 : (pub1.getAverageOfRates()<pub2.getAverageOfRates()? 1 : 0));
        if(result!=0) return result;
        result=(pub1.getCountOfRates()>pub2.getCountOfRates()? -1 : (pub1.getCountOfRates()<pub2.getCountOfRates()? 1 : 0));
        return result;
    }
}
