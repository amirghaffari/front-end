/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.exceptions;

/**
*
* @author Amir Ghaffari
*/
public class UnMarshallingException extends Exception{ 
    public UnMarshallingException(){ 
        super("Error in UnMarshalling XML: "); 
    } 
    public UnMarshallingException(String msg){ 
        super("Error in UnMarshalling XML: "+msg); 
    } 
}
