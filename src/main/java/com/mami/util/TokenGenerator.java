/**
 * 
 */
package com.mami.util;

import java.util.Random;
import java.util.UUID;



/**
 * @author sszheng
 *
 */
public class TokenGenerator {
	
	/**
	 * 根据value获取token
	 * 
	 * @param value
	 * @return
	 */
	public static String getToken(String value) {
		return MD5.encryption(value) + UUID.randomUUID() + MD5.encryption(new Random().toString());
	}
	
}
