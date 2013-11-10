/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.webservice.xmlGenerator;

import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class xmlDocument {

    public final static String CORE_NAME_SPACE="http://ht497.o1.gondor.io";
    public final static String ATOM_NAME_SPACE="http://www.w3.org/2005/atom";

    private static String getRootStart(String rootName){
        return "<"+rootName+" xmlns=\""+CORE_NAME_SPACE+"\" xmlns:atom=\""+ATOM_NAME_SPACE+"\">";
    }

    private static String getRootEnd(String rootName){
        return "</"+rootName+">";
    }

    public static String getXMLFormat(String rootName, List<xmlElement> xmlElementList){
        StringBuilder strBuilder=new StringBuilder();
        strBuilder.append(getRootStart(rootName));
        for(xmlElement item: xmlElementList){
            strBuilder.append(item.getElement());
        }
        strBuilder.append(getRootEnd(rootName));
        return strBuilder.toString();
    }

}
