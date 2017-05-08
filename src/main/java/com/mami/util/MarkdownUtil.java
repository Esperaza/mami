package com.mami.util;

import org.pegdown.PegDownProcessor;

public class MarkdownUtil {
	public static String markdownToHtml(String md){
        return new PegDownProcessor(Integer.MAX_VALUE).markdownToHtml(md);
	}
}
