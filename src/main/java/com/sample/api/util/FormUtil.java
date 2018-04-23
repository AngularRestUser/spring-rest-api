package com.sample.api.util;


import java.text.DateFormat;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;



@Service
public class FormUtil {
	

	public String formatPhoneNumber(String input){
		if(!StringUtils.isBlank(input)){
			return input.replaceAll("[^\\d]", "");
		}
		return input;
	}


	public String formatDateToString(String dateString) {
		
		if ((dateString==null) || (dateString.isEmpty())) return "";
		
		String [] dateParts = dateString.split("/");
		if (dateParts.length == 3) {
		String month = dateParts[0];
		if (month.length() == 1) { month = "0" + month; }
		String day = dateParts[1];
		if (day.length() == 1) day = "0" + day;
		String year = dateParts[2];
		if (year.length() == 1) year = "0" + year;
		return month + "/" + day + "/" + year;
		}
		
		return "";
	}	
    
    public String replaceSubString( String strValue, String oldStr, String newStr ){
       //    Return if original string, strValue, is null or with length of 0 ("").
       //    Also, if the search for string, oldStr, is null or with length of 0 ("")
       //    do nothing.
       if ( ( strValue == null ) || ( strValue.length() == 0 ) || ( oldStr == null )
                   || ( oldStr.length() == 0 )){
                       return strValue;
                   }
       String  replaceStr = "";
       boolean bDone = false;
       for ( int beginIndex = 0, endIndex = 0; !bDone;){
           endIndex = strValue.indexOf( oldStr, beginIndex );
           if ( endIndex != -1 ){
               replaceStr = replaceStr + strValue.substring( beginIndex,endIndex ) + newStr;
               beginIndex = endIndex + oldStr.length();    
           }  
           else
           {
               bDone = true;
               replaceStr = replaceStr + strValue.substring( beginIndex );
           }   
       }   

       return replaceStr;

    }

}
