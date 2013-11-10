/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model.webservice.xmlGenerator;

import frontend.model.webservice.AtomLink;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

public class xmlElement {
    private ElementType elementType;
    private String fieldName;
    private String subFieldName;
    private String fieldValue;
    private List<AtomLink> atomLinkList;

    public xmlElement(ElementType elementType, String fieldName, String subFieldName, String fieldValue, List<AtomLink> atomLinkList) {
        this.elementType = elementType;
        this.fieldName = fieldName;
        this.subFieldName = subFieldName;
        this.fieldValue = fieldValue;
        this.atomLinkList = atomLinkList;
    }
    
    public String getElement(){
        if(elementType==ElementType.SIMPLE){
            return getprefix(fieldName)+fieldValue+getpostfix(fieldName);
        }
        if(elementType==ElementType.COMPLEX){
            String preTag,postTag,core;
            preTag=getprefix(fieldName);
            postTag=getpostfix(fieldName);
            if(atomLinkList==null || atomLinkList.isEmpty())
                core= preTag+""+postTag;
            else 
                core= atomLinkList.get(0).xmlFormat();
            return preTag+core+postTag;
            
        }
        if(elementType==ElementType.LIST){
            StringBuilder strBuilder=new StringBuilder();
            strBuilder.append(getprefix(fieldName));
            for(AtomLink atom:atomLinkList){
                strBuilder.append(getprefix(subFieldName));
                strBuilder.append(atom.xmlFormat());
                strBuilder.append(getpostfix(subFieldName));
            }
            strBuilder.append(getpostfix(fieldName));
            return strBuilder.toString();
        }
        return "";
    }

    private String getprefix(String fieldName){
        if(!fieldName.trim().isEmpty())
            return "<"+fieldName+">";
        else
            return "";
    }

    private String getpostfix(String fieldName){
        if(!fieldName.trim().isEmpty())
            return "</"+fieldName+">";
        else
            return "";
    }

}
