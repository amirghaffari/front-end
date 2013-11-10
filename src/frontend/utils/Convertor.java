/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.utils;

import frontend.exceptions.AtomLinkException;
import frontend.exceptions.UnMarshallingException;
import frontend.model.Author;
import frontend.model.Comment;
import frontend.model.KeyWord;
import frontend.model.Login;
import frontend.model.Publication;
import frontend.model.Rating;
import frontend.model.ReferenceMaterial;
import frontend.model.ResearchArea;
import frontend.model.Tag;
import frontend.model.User;
import frontend.model.Vote;
import frontend.model.webservice.AtomComment;
import frontend.model.webservice.AtomPublication;
import frontend.model.webservice.Authors;
import frontend.model.webservice.Publications;
import frontend.model.webservice.XMLUtil;
import frontend.model.webservice.xmlGenerator.ConvertorObjectToXML;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
*
* @author Amir Ghaffari
*/

//XML to objects
public class Convertor {
    public static Object unmarshal(InputStream inputStream) throws UnMarshallingException, AtomLinkException{
                Document document=null;
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setValidating(false);
                dbf.setIgnoringComments(false);
                dbf.setIgnoringElementContentWhitespace(true);
                dbf.setNamespaceAware(true);
                DocumentBuilder db = null;
                try {
                    db = dbf.newDocumentBuilder();
                } catch (ParserConfigurationException ex) {
                    System.out.println("Error in unmarshalling XML 1001"+ex.getMessage());
                    Logger.getLogger(WebServiceXMLConvertor.class.getName()).log(Level.SEVERE, null, ex);
                }
                db.setEntityResolver(new NullResolver());
                try {
                    try {
                        document=db.parse(inputStream);
                    } catch (SAXException ex) {
                        System.out.println("Error in unmarshalling XML 1002"+ex.getMessage());
                        Logger.getLogger(Convertor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println("Error in unmarshalling XML 1003"+ex.getMessage());
                    Logger.getLogger(Convertor.class.getName()).log(Level.SEVERE, null, ex);
                }

                //String XMLContent= docomentToString(document);
                //System.out.println("------Start printing the recieved XML(UnMarshalling)-------");
                //System.out.println(XMLContent);
                //System.out.println("------End of printing the recieved XML(UnMarshalling)-------");
                String root=document.getDocumentElement().getNodeName();
                Object object = selectConvertor( root,document);
                if(object==null) throw new UnMarshallingException(" Can not make "+root+" object from XML ");
                return object;
    }

    private static String docomentToString(Document document){
        String XMLContent="";
        Source source = new javax.xml.transform.dom.DOMSource(document);
        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
            XMLContent= stringWriter.getBuffer().toString();
        } catch (TransformerException ex) {
            System.out.println("Error in unmarshalling XML 1004"+ex.getMessage());
            Logger.getLogger(WebServiceXMLConvertor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return XMLContent;
    }

    //Convert XML to Object
    private static Object selectConvertor(String className,Document document) throws AtomLinkException{
        if(className.equalsIgnoreCase("publications"))
        {   
            try{
                return Publications.getPublications(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to publication List: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("authors"))
        {   
            try{
                return Authors.getAuthors(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to Author List: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("author"))
        {   
            try{
                return XMLUtil.getAuthor(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to Author: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("publication"))
        {   
            try{
                AtomPublication atomPublication=new AtomPublication();
                atomPublication.getPublication(document);
                return atomPublication;
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to Publication: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("user"))
        {   
            try{
                return XMLUtil.getUser(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to user: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("rating"))
        {   
            try{
                return XMLUtil.getRating(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to rating: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("login"))
        {   
            try{
                return XMLUtil.getLogin(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to login: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("tag"))
        {   
            try{
                return XMLUtil.getTag(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to tag: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("keyword"))
        {   
            try{
                return XMLUtil.getKeyWord(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to keyword: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("referencematerial"))
        {   
            try{
                return XMLUtil.getReferenceMaterial(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to referencematerial: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("comment"))
        {   
            try{
                return XMLUtil.getAtomComment(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to comment: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("vote"))
        {   
            try{
                return XMLUtil.getAtomVote(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to vote: "+ex.getMessage());
            }
        }
        if(className.equalsIgnoreCase("researcharea"))
        {   
            try{
                return XMLUtil.getResearchArea(document);
            }
            catch(Exception ex){
                System.out.println("Error in converting XML to ResearchArea: "+ex.getMessage());
            }
        }
        return null;
    }

    //Convert Object to XML
    public static void marshaller(Object object, OutputStream outputStream){
        String className=getKindOfOutputStream(outputStream);
        if(className.equals("java.io.ByteArrayOutputStream")){
            ByteArrayOutputStream byteArrayOutputStream=null;
            byteArrayOutputStream=(ByteArrayOutputStream)outputStream;
            if(byteArrayOutputStream!=null){
                String XML="";

                try{
                    if(object instanceof Author)
                        XML=ConvertorObjectToXML.getAuthorXML(((Author)object));
                }
                catch(Exception e){
                    System.out.println("Error in Converting Author to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof Rating)
                        XML=ConvertorObjectToXML.getRatingXML(((Rating)object));
                        
                }
                catch(Exception e){
                    System.out.println("Error in Converting Rating to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof User)
                        XML=ConvertorObjectToXML.getUserXML(((User)object));
                }
                catch(Exception e){
                    System.out.println("Error in Converting User to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof AtomComment)
                        XML=ConvertorObjectToXML.getCommentXML(((AtomComment)object).getComment());
                }
                catch(Exception e){
                    System.out.println("Error in Converting Comment to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof ReferenceMaterial)
                        XML=ConvertorObjectToXML.getReferenceMaterialXML(((ReferenceMaterial)object));
                }
                catch(Exception e){
                    System.out.println("Error in Converting ReferenceMaterial to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof Tag)
                        XML=ConvertorObjectToXML.getTagXML(((Tag)object));  
                }
                catch(Exception e){
                    System.out.println("Error in Converting Tag to XML  : "+e.getMessage());
                }

                try{
                    if(object instanceof Vote)
                        XML=ConvertorObjectToXML.getVoteXML(((Vote)object));
                }
                catch(Exception e){
                    System.out.println("Error in Converting Vote to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof Login)
                        XML=ConvertorObjectToXML.getLoginXML(((Login)object));
                }
                catch(Exception e){
                    System.out.println("Error in Converting Login to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof KeyWord)
                        XML=ConvertorObjectToXML.getKeyWordXML(((KeyWord)object));
                }
                catch(Exception e){
                    System.out.println("Error in Converting KeyWord to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof Publication)
                        XML=ConvertorObjectToXML.getPublicationXML(((Publication)object));
                }
                catch(Exception e){
                    System.out.println("Error in Converting Publication to XML : "+e.getMessage());
                }

                try{
                    if(object instanceof ResearchArea)
                        XML=ConvertorObjectToXML.getResearchAreaXML(((ResearchArea)object));
                }
                catch(Exception e){
                    System.out.println("Error in Converting ResearchArea to XML : "+e.getMessage());
                }

                if(XML.isEmpty()) System.out.println("Error in converting class to XML. Can not find appropriate module to convert object to XML");
                byte buf[] = XML.getBytes(); 
                byteArrayOutputStream.reset();
                try {
                    byteArrayOutputStream.write(buf);
                } catch (IOException ex) {
                    System.out.println("Error in converting class to XML(writing to output stream): "+ex.getMessage());
                }
            }
        }
    }

    private static String getKindOfOutputStream(OutputStream outputStream){
        if(outputStream instanceof BufferedOutputStream )
            return BufferedOutputStream.class.getName();
        if(outputStream instanceof ByteArrayOutputStream  )
            return ByteArrayOutputStream.class.getName();
        if(outputStream instanceof DataOutputStream  )
            return DataOutputStream.class.getName();
        if(outputStream instanceof FilterOutputStream  )
            return FilterOutputStream.class.getName();
        if(outputStream instanceof BufferedOutputStream  )
            return BufferedOutputStream.class.getName();
        if(outputStream instanceof ObjectOutputStream  )
            return ObjectOutputStream.class.getName();
        if(outputStream instanceof PipedOutputStream  )
            return PipedOutputStream.class.getName();
        if(outputStream instanceof PrintStream  )
            return PrintStream.class.getName();
        return "";
    }

}
