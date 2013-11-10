package frontend.utils;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
* Utility class to store variables used in common in the classes and pages.
* @author Amir Ghaffari
*/

public final class Util {

    public final static String ERROR_LIST = "Error_List";
    public final static String PAGE_GLOBAL_ERROR = "error";
    public final static String PAGE_GLOBAL_ERROR_CONTENT = "content";
    public static final String UploadedFileFolderPath="/uploadedfiles/";
    public static final String ERROR_AUTHOR_CODE_NOT_INVALID="1001";
    public static final String ERROR_MIN_ONE_AUTHOR_IS_NEEDE="1002";
    public static final String ERROR_FILE_BEFORE_UPLOADED="1003";
    public static final String ERROR_FILE_UPLOAD_EXCEPTION="1004";
    public static final String BASE_URL="http://du865.o1.gondor.io/";
    public static final String USER_SESSION_ID="userSessionId";
    public static final String USER_SESSION="userSession";
    public static final String LAST_LOGIN_STATUS="lastLoginSatus";
    public static final String LOGIN_STATUS_NO_TRY="";
    public static final String LOGIN_STATUS_SUCCESSFUL="ok";
    public static final String LOGIN_STATUS_FAILD="failed";
    public static final String LOGIN_STATUS_ROLE_INCONSISTENT="inconsistent";
    public static final String ROLE_EDITORIAL="Editorial";
    public static final String ROLE_READER="Reader";
    public static final String ROLE_REFEREE="Referee";
    public static final String ROLE_AUTHOR="Author";
    public static final String[] reviewState={"Public"/*1*/,"Reviewed"/*2*/,"Rejected"/*3*/,"Incompleted"/*4*/,"Being reviewed"/*5*/};
    public static List<String> getMonths()
    {
        List<String> months = Arrays.asList("January", "Febraury", "March", "April", "May","June","July","August","September","October","November","December");
        return months;
    }
    public static String getUserName()
    {
        return "amir";
    }

    public static int strToInt(String input)
    {
        if(input==null) return 0;
        try{
            return Integer.parseInt(input.trim());
        }catch(NumberFormatException ex){
            return 0;
        }
    }

    public static String getHeaderValue(String fieldName,HttpHeaders httpHeaders){
        StringBuilder bulider=new StringBuilder();
        Set<String> keys = httpHeaders.keySet();
        for (String header : keys) {
            if(header.trim().equalsIgnoreCase(fieldName)){
                List<String> values = httpHeaders.get(header);
                for (String value : values) {
                       bulider.append(value);
                }
                return bulider.toString();
            }
        }
        return "";
    }

    public static void printHeaderValues(HttpHeaders httpHeaders){
        Set<String> keys = httpHeaders.keySet();
        System.out.println("Printing HttpHeaders Content");
        for (String header : keys) {
            System.out.println("Header field name: "+header);
            List<String> values = httpHeaders.get(header);
            for (String value : values) {
                   System.out.println("Header field value: "+ value);
            }
        }
    }

    public static HttpHeaders configureHeader(HttpSession session){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        if(session.getAttribute(USER_SESSION_ID)==null||((String)session.getAttribute(USER_SESSION_ID)).isEmpty()) 
            return httpHeaders;
        httpHeaders.add("Cookie", (String)session.getAttribute(USER_SESSION_ID));
        return httpHeaders;
    }

    public static boolean isLocalData(HttpSession session){
        if(session.getAttribute("isLocalData")!=null)
            return ((String)session.getAttribute("isLocalData")).equals("true");
        return true;
    }

    public static void setLocalData(HttpSession session,boolean local){
        if(local)
            session.setAttribute("isLocalData","true");
        else
            session.setAttribute("isLocalData","false");
    }
}
