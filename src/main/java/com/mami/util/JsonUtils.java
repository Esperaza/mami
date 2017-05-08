package com.mami.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JsonUtils {
	public static String dateFormat(Date datetime){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));  
		return sdf.format(datetime);
	}
	public static String CamelCase2underLine(String before){
		if(before.length()>1){
			int start = 0;
			String out = "";
			for(int i=1;i<before.length();i++){
				char temp = before.charAt(i);
				if(temp>='A' && temp<='Z'){
					temp += 32;
					out += before.substring(start, i) + "_" + temp;
					i++;
					start = i;
				}
			}
			if(start<before.length()-1)
				out += before.substring(start, before.length());
			before = out;
		}
		return before;
	}
}
