/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.comparator;

import frontend.model.Tag;
import java.util.Comparator;

/**
*
* @author Amir Ghaffari
*/

public class TagListGroupComparator implements Comparator<Tag>{
    @Override
    public int compare(Tag tag1, Tag tag2) {
        if(tag1.getGroupId()>tag2.getGroupId()) return 1;
        if(tag1.getGroupId()<tag2.getGroupId()) return -1;
        return 0;
    }
}
