/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.service.publication;

import frontend.model.webservice.AtomLink;
import javax.xml.bind.annotation.*;

/**
*
* @author Amir Ghaffari
*/

@XmlRootElement(name="link", namespace="http://www.w3.org/2005/Atom")
@XmlAccessorType(XmlAccessType.FIELD)
public class Publication {
    @XmlElementRef
    private AtomLink link;
}
