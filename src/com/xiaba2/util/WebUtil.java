package com.xiaba2.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class WebUtil {

	
	public static String getThumb(String filepath,int size)
	{
		String s = filepath;
		
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		if(StringUtils.isEmpty(filepath))
		{
			switch (size) {
			case 1:
				s =  "/resource/web/images/nopic_83.png";
				break;
			case 2:
				s =  "/resource/web/images/nopic_232.png";
				break;
			case 3:
				s =  "/resource/web/images/nopic_800.png";
				break;

			default:
				s =  "/resource/web/images/nopic_83.png";
				break;
			}
			
		}
		
		if(StringUtils.isEmpty(filepath))
		{
			s =  request.getContextPath() + s;
			return s;
		}
		
		
		return s;
	}
	
}
