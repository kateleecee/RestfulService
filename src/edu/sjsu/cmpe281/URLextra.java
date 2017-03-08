package edu.sjsu.cmpe281;

import javax.servlet.http.HttpServletRequest;

public class URLextra {
	private static int p_id;
	private static String contentType;
	
	public static String getBaseUrl(HttpServletRequest request) {
	    String scheme = request.getScheme() + "://";
	    String serverName = request.getServerName();
	    String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
	    String contextPath = request.getContextPath();
	    return scheme + serverName + serverPort + contextPath;
	  }
	
	public static void setID(int id)
	{
		p_id = id;
	}
	
	public static int getID()
	{
		return p_id;
	}
	
	public static void setCType(String str)
	{
		contentType = str;
	}
	
	public static String getCType()
	{
		return contentType;
	}
}
