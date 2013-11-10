/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.exceptions;

/**
 *
 * @author Amir Ghaffari
 */
public class AtomLinkException extends Exception{ 
    public AtomLinkException(){ 
        super("Can not extract the id from atom link href"); 
    } 
    public AtomLinkException(String msg){ 
        super("Can not extract the id from atom link href "+msg); 
    } 
}
