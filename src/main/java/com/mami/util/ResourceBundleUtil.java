package com.mami.util;

import java.util.ResourceBundle;

public class ResourceBundleUtil {
	private static ResourceBundle rbint = ResourceBundle.getBundle("path");

	public static final String adminurl = rbint.getString("adminurl");

}
