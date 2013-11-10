/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.service;

import frontend.model.ConditionMaker;
import frontend.model.ModelInterface;
import frontend.model.webservice.AtomLink;

import frontend.utils.Util;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
*
* @author Amir Ghaffari
*/

@Service
public class WebService<T> {


    @Autowired
    private RestTemplate restTemplate;


    public T get(Class<T> responseType, String URL, HttpSession session, List<ConditionMaker> conditions){
        String url=URL;
        int newId=-1;
        try{
            HttpEntity<T> entity  = new HttpEntity<T>(Util.configureHeader(session));
            //Util.printHeaderValues(entity.getHeaders());
            if(conditions!=null)
                url=ConditionMaker.getConditionString(url, conditions);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
            T result = response.getBody();
            newId=AtomLink.getId(URL);
            ((ModelInterface)result).setId(newId);
            return result;
        }
        catch(RestClientException ex){
            System.out.println("RestClientException in getting "+responseType+" object: "+ex.getMessage());
            if(ex instanceof HttpServerErrorException || ex instanceof HttpStatusCodeException){
                HttpStatusCodeException httpServerErrorException=(HttpStatusCodeException)ex;
                throw new IllegalStateException(httpServerErrorException.getResponseBodyAsString());
            }
            
        }
        catch(Exception ex){
            System.out.println("Rest Call Exception in getting "+responseType+" object: "+ex.getMessage());
        }
        return null;
    }


    public T add(T objectToAdd, HttpSession session,Class<T> responseType){
        int newId=-1;
        ResponseEntity<T> response=null;
        try{
            HttpEntity<T> entity  = new HttpEntity<T>(objectToAdd,Util.configureHeader(session));
            //Util.printHeaderValues(entity.getHeaders());
            response = restTemplate.postForEntity(((ModelInterface)objectToAdd).getURL(), entity,responseType );
            //Util.printHeaderValues(response.getHeaders());
            String location=response.getHeaders().get("location").get(0);
            newId=AtomLink.getId(location);
            if(newId==-1){
                System.out.println("Can not get new id from new added "+responseType+" object: "+location);
            }
        }
        catch(RestClientException ex){
            System.out.println("RestClientException in adding "+responseType+" object: "+ex.getMessage());
            if(ex instanceof HttpServerErrorException){
                HttpServerErrorException httpServerErrorException=(HttpServerErrorException)ex;
                throw new IllegalStateException(httpServerErrorException.getResponseBodyAsString());
            }
        }
        catch(Exception ex){
            System.out.println("Rest Call Exception in adding "+responseType+" object: "+ex.getMessage());
        }
        T result = response.getBody();
        ((ModelInterface)result).setId(newId);
        return result;
    }    

    public boolean update(T objectToUpdate, HttpSession session){
        try{
            HttpEntity<T> entity  = new HttpEntity<T>(objectToUpdate,Util.configureHeader(session));     
            //Util.printHeaderValues(entity.getHeaders());
            restTemplate.put(((ModelInterface)objectToUpdate).getURL()+"{id}", entity,((ModelInterface)objectToUpdate).getId());
            return true;
        }
        catch(RestClientException ex){
            System.out.println("RestClientException in updating "+objectToUpdate.getClass()+" object: "+ex.getMessage());
            if(ex instanceof HttpServerErrorException){
                HttpServerErrorException httpServerErrorException=(HttpServerErrorException)ex;
                throw new IllegalStateException(httpServerErrorException.getResponseBodyAsString());
            }
        }
        catch(Exception ex){
            System.out.println("Rest Call Exception in updating "+objectToUpdate.getClass()+" object: "+ex.getMessage());
        }

        return false;
    }

    public boolean delete(T objectToDelete, HttpSession session){
        try{
            HttpEntity<T> entity  = new HttpEntity<T>(Util.configureHeader(session));
            restTemplate.exchange(((ModelInterface)objectToDelete).getURL()+"{id}",HttpMethod.DELETE, entity, String.class,((ModelInterface)objectToDelete).getId());
            //Util.printHeaderValues(entity.getHeaders());
            return true;
        }
        catch(RestClientException ex){
            System.out.println("RestClientException in delete "+objectToDelete.getClass()+" object: "+ex.getMessage());
            if(ex instanceof HttpServerErrorException){
                HttpServerErrorException httpServerErrorException=(HttpServerErrorException)ex;
                throw new IllegalStateException(httpServerErrorException.getResponseBodyAsString());
            }
        }
        catch(Exception ex){
            System.out.println("Rest Call Exception in delete "+objectToDelete.getClass()+" object: "+ex.getMessage());
        }
        return false;
    }  

}
