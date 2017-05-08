package com.mami.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

public class Util {
	
	/**
	 * 将获取的参数的编码进行修改
	 * @param param
	 * @return
	 */
	public static String getUTF8String(String param){
		try {
			param = new String(param.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return param;
	}
	
	/**
	 * 获取前端传递的中文
	 * 
	 * @param param
	 * @return
	 */
	public static String getURIDecode(String param){
		try {
			param = URLDecoder.decode(param,"UTF-8");			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return param;
	}
	
	public static long str2long(String param) {
		return Long.parseLong(param);
	}
	
	public static boolean isInteger(String str) {    
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
		return pattern.matcher(str).matches();    
	}
	
	public static String lPad(String param){
		String result = "";
		if(param.length()>32){
			return null;
		}
		for (int i = 0; i< 32-param.length();i++) {
			result = result+"0";
		}
		result = result+param;
		return result;
	}
}
