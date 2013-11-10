/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.model;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author Amir Ghaffari
*/

//List<ConditionMaker> conditions=Arrays.asList(new ConditionMaker("table1","field1","value1"),new ConditionMaker("table2","field2","value2"),new ConditionMaker("table3","field3","value3"));
//s=ConditionMaker.getConditionString("myaddress/tablename", conditions);

public class ConditionMaker {

    public static final String PUBLICATION_LIST ="publication";
    public static final String AUTHORS_LIST ="author";
    public static final String KEYWORDS ="keyword";
    public static final String USER ="user";
    public static final String TAG ="tag";
    
    private String tableName;
    private String fieldName;
    private String fieldValue;
    
    public ConditionMaker(String tableName, String fieldName, String fieldValue) {
        this.tableName = tableName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
    
    public static String getConditionString(String URL, List<ConditionMaker> conditions){
        String temp="",table="";
        //remove / from the end
        if(URL.endsWith("/")) URL=URL.substring(0, URL.length()-1);
        //if there is not any other / raise error
        if(URL.lastIndexOf("/")==-1){
            System.out.println("Error in URL format. Can not find the last / :"+URL);
            return URL;
        }
        //find the name of original table from URL
        table=URL.substring(URL.lastIndexOf("/")+1, URL.length());
        List<String> tableHistory=new ArrayList<String>();
        //add / to end of URL
        temp=URL.concat("/");
        //add table name to history
        tableHistory.add(table);
        for(ConditionMaker innerCondition: conditions){
            if(table.equalsIgnoreCase(innerCondition.tableName)) {
                if(temp.lastIndexOf("/")==temp.length()-1) temp+=innerCondition.getFieldName().concat("=").concat(innerCondition.getFieldValue());
                else temp+="&"+innerCondition.getFieldName().concat("=").concat(innerCondition.getFieldValue());
            }
        }
        temp=temp.concat("/");
        for(ConditionMaker condition: conditions){
            boolean find=false;
            table=condition.tableName;
            for(String str: tableHistory) if(str.equalsIgnoreCase(condition.getTableName())) find=true;
            if(find) continue;
            temp+=table.concat("/");

            tableHistory.add(table);
            for(ConditionMaker innerCondition: conditions){
                if(table.equalsIgnoreCase(innerCondition.tableName)) {
                    if(temp.lastIndexOf("/")==temp.length()-1) temp+=innerCondition.getFieldName().concat("=").concat(innerCondition.getFieldValue());
                    else temp+="&"+innerCondition.getFieldName().concat("=").concat(innerCondition.getFieldValue());
                }
            }
            temp=temp.concat("/");
        }
        return temp;
    }
}
