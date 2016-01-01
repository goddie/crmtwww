package com.xiaba2.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
 

import com.xiaba2.core.Page;
import com.xiaba2.task.domain.User;

public class HttpUtil {

	public static int IMAGE_SIZE = 120;

	public static int PAGE_SIZE = 20;

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (null == request) {
			return null;
		}

		String proxs[] = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
				"HTTP_X_FORWARDED_FOR" };

		String ip = null;

		for (String prox : proxs) {
			ip = request.getHeader(prox);
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				continue;
			} else {
				break;
			}
		}

		if (StringUtils.isBlank(ip)) {
			return request.getRemoteAddr();
		}

		return ip;
	}

	public static void createPreviewImage(String srcFile, String destFile) {
		try {

			AffineTransform transform = new AffineTransform();

			File fi = new File(srcFile); // src
			File fo = new File(destFile); // dest
			BufferedImage bis = ImageIO.read(fi);

			int w = bis.getWidth();
			int h = bis.getHeight();
			double scale = (double) w / h;
			int nw = IMAGE_SIZE; // final int IMAGE_SIZE = 120;
			int nh = (nw * h) / w;
			if (nh > IMAGE_SIZE) {
				nh = IMAGE_SIZE;
				nw = (nh * w) / h;
			}
			double sx = (double) nw / w;
			double sy = (double) nh / h;

			transform.setToScale(sx, sy);
			AffineTransformOp ato = new AffineTransformOp(transform, null);
			BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
			ato.filter(bis, bid);
			ImageIO.write(bid, " jpeg ", fo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Failed in create preview image. Error:  " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param infile
	 *            输入文件
	 * @param outfile
	 *            输出文件
	 * @param srcFormat
	 *            源格式
	 * @param destFormat
	 *            输出格式
	 * @return
	 * @throws Exception
	 */
	public static boolean convertFormat(InputStream infile, OutputStream outfile, String srcFormat, String destFormat,
			int width, int height) throws Exception {
		boolean flag = false;
		BufferedImage src = ImageIO.read(infile);
		if (height > 0 && width > 0) {// compress the origin image if width and
										// height are non-zero
			height = src.getHeight() > height ? height : src.getHeight();
			width = src.getWidth() > width ? width : src.getWidth();
			Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);// 这个是用来进行图片大小调整的

			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			Graphics g = tag.getGraphics();
			// 可在下面对图片进行绘制和更改
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图

			g.dispose();
			tag.flush();
			flag = ImageIO.write(tag, destFormat, outfile);// 输出到经过缩放的文件流
		} else {
			flag = ImageIO.write(src, destFormat, outfile);// 输出原分辨率的图片
		}
		Logger.getLogger(HttpUtil.class).info("图片转换成功: 从[" + srcFormat + "]到[" + destFormat + "]");
		return flag;
	}

	/**
	 * 获取原图
	 * 
	 * @param obj
	 * @return
	 */
	public static String getOrigin(Object obj) {
		if (obj == null) {
			return "";
		}

		String thumb = obj.toString();

		if (StringUtils.isEmpty(thumb)) {
			return null;
		}

		String ext = thumb.substring(thumb.lastIndexOf("."));

		return thumb.subSequence(0, thumb.lastIndexOf("_")) + ext;

		// if (StringUtils.isEmpty(replace)) {
		// return thumb.subSequence(0, thumb.lastIndexOf("_")) + ext;
		// }
		//
		// return thumb.subSequence(0, thumb.lastIndexOf("_") + 1) + replace +
		// ext;

	}

	/**
	 * 获取正方形
	 * 
	 * @param obj
	 * @return
	 */
	public static String getSquare(Object obj) {
		if (obj == null) {
			return "";
		}

		String thumb = obj.toString();

		if (StringUtils.isEmpty(thumb)) {
			return null;
		}

		String ext = thumb.substring(thumb.lastIndexOf("."));

		return thumb.subSequence(0, thumb.lastIndexOf("_")) + "_100x100" + ext;

		// if (StringUtils.isEmpty(replace)) {
		// return thumb.subSequence(0, thumb.lastIndexOf("_")) + ext;
		// }
		//
		// return thumb.subSequence(0, thumb.lastIndexOf("_") + 1) + replace +
		// ext;

	}

	/**
	 * 获取封面
	 * 
	 * @param obj
	 * @param size
	 * @return
	 */
	public static String getCover(Object obj, int size) {
		if (obj == null) {
			return "/resource/web/images/nopic.jpg";
		}

		String thumb = obj.toString();

		if (StringUtils.isEmpty(thumb)) {
			return null;
		}

		String s = "";

		switch (size) {
		case 1:
			s = "_200x200";
			break;
		case 2:
			s = "_240x180";
			break;
		case 0:
			s = "";
			break;
		default:
			break;
		}

		String ext = thumb.substring(thumb.lastIndexOf("."));

		return thumb.subSequence(0, thumb.lastIndexOf("_")) + s + ext;

	}

	/**
	 * 摘要
	 * 
	 * @param content
	 * @param length
	 * @return
	 */
	public static String getSubContent(String content, int length) {
		if (StringUtils.isEmpty(content)) {
			return "";
		}
		String tmp = clearHTML(content);
		return getSubString(tmp, 0, length) + "...";
	}

	/**
	 * 截取字符 中文两个字符,英文一个字符
	 * 
	 * @param str
	 *            字符串
	 * @param start
	 *            处理的首位置
	 * @param length
	 *            长度
	 * @return
	 */
	public static String getSubString(String str, int start, int length) {
		length += start;
		int len = 0;
		StringBuffer sb = new StringBuffer();
		int k = 0;
		while (len < length && k < str.length()) {
			char c = str.charAt(k++);
			if (c > 255) {
				len += 2;
				if (len > length)
					break;
			} else {
				len += 1;
			}
			if (len <= start || len <= start + 1) {
				continue;
			}
			sb.append(c);
		}
		if (k == str.length()) {
			return sb.toString();
		}
		return sb.toString();
	}

	/**
	 * 清除html标记
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String clearHTML(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 生成分页代码
	 * 
	 * @param <T>
	 */
	public static String genPageHtml(Page<?> page, HttpServletRequest request) {

		String url = request.getRequestURL().toString();

		StringBuffer sb = new StringBuffer();

		sb.append("<div class=\"col-md-12\">");
		sb.append("<div class=\"dataTables_paginate paging_bootstrap\">");
		sb.append("<ul class=\"pagination\">");

		// 判断是否有上一页
		if (page.isHasPre()) {

			sb.append("<li class=\"prev\"><a href=\""
					+ updateQueryStringItem(request, "p", String.valueOf(page.getPageNo() - 1))
					+ "\">&lt;&lt;</a></li>");
		} else {
			sb.append("<li class=\"prev disabled\"><a href=\" \">&lt;&lt;</a></li>");
		}

		// 中间显示
		for (int i = 1; i <= page.getTotalPages(); i++) {

			String spanClzz = "<li class=\"\"><a href=\"" + updateQueryStringItem(request, "p", String.valueOf(i))
					+ "\">" + i + "</a></li>";

			if (page.getPageNo() == i) {
				spanClzz = "<li class=\"active\"><a href=\"" + updateQueryStringItem(request, "p", String.valueOf(i))
						+ "\">" + i + "</a></li>";
			}

			sb.append(spanClzz);

			// 当大于9页数的时候才进行分页显示
			if (page.getTotalPages() - 2 > 7) {
				if (i == 5) {
					i = (int) page.getTotalCount() - 2;
					sb.append("...");
				}
			}
		}
		// 判断是否有下一页
		if (page.isHasNext()) {

			sb.append("<li class=\"next\"><a href=\""
					+ updateQueryStringItem(request, "p", String.valueOf(page.getPageNo() + 1))
					+ "\">&gt;&gt;</a></li>");
		} else {
			sb.append("<li class=\"next disabled\"><a href=\"#\">&gt;&gt;</a></li>");
		}

		sb.append("</ul>");
		sb.append("</div>");
		sb.append("</div>");

		return sb.toString();

	}

	/// <summary>
	/// 更新翻页链接
	/// </summary>
	/// <param name="httpRequest"></param>
	/// <param name="queryStringKey"></param>
	/// <param name="newQueryStringValue"></param>
	/// <returns></returns>
	public static String updateQueryStringItem(HttpServletRequest httpRequest, String queryStringKey,
			String newQueryStringValue) {

		// StringBuffer url_buffer = request.getRequestURL();
		// return http://localhost:8080/ssm/ser.do

		String newURL = httpRequest.getRequestURL().toString() + "?" + httpRequest.getQueryString();

		// QueryString CONTAINS the Key...
		if (httpRequest.getParameter(queryStringKey) != null) {
			String orignalSet = String.format("%s=%s", queryStringKey, httpRequest.getParameter(queryStringKey));
			String newSet = String.format("%s=%s", queryStringKey, newQueryStringValue);

			// REMOVE the key/value completely since the NewValue is blank
			if (newQueryStringValue.trim().equals("")) {
				// Replace Key/Value down the line...

				newURL = Pattern.compile("&" + orignalSet, Pattern.CASE_INSENSITIVE).matcher(newURL).replaceAll("");

				// newURL = Regex.Replace(newURL, "&" + orignalSet, "",
				// RegexOptions.IgnoreCase);

				// Replace Key/Value at the beginning When there is other
				// key/values after...
				newURL = Pattern.compile("?" + orignalSet + "&", Pattern.CASE_INSENSITIVE).matcher(newURL)
						.replaceAll("?");
						// newURL = Regex.Replace(newURL, "?" + orignalSet +
						// "&", "?", RegexOptions.IgnoreCase);

				// Replace Key/Value at the beginning when there
				// are NO other Key/Values.
				newURL = Pattern.compile("?" + orignalSet, Pattern.CASE_INSENSITIVE).matcher(newURL).replaceAll("");
				// newURL = Regex.Replace(newURL, "?" + orignalSet, "",
				// RegexOptions.IgnoreCase);
			}
			// REPLACE old value with new value.
			else {
				newURL = Pattern.compile(orignalSet, Pattern.CASE_INSENSITIVE).matcher(newURL).replaceAll(newSet);
				// newURL = Regex.Replace(newURL, orignalSet, newSet,
				// RegexOptions.IgnoreCase);
			}
		}
		// Only add the key/value IF the new value is not blank.
		else if (!newQueryStringValue.trim().equals("")) {
			// QueryString DOES NOT CONTAIN the Key... and DOES NOT HAVE other
			// key/value pairs.

			if (httpRequest.getQueryString() != null && httpRequest.getQueryString().indexOf("?") < 0) {
				newURL += String.format("?%s=%s", queryStringKey, newQueryStringValue);
			} else {
				newURL += String.format("&%s=%s", queryStringKey, newQueryStringValue);
			}
		}

		return newURL;
	}

	/**
	 * 获取订单号
	 * @return
	 */
	public static long getOrderNum() {
 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = new Date();
		String s = sdf.format(d);
		
		//String s = String.valueOf(System.currentTimeMillis());
		
		long t = Long.parseLong(s);
		
		t = t * 1000 + randomArray(0,1000,1)[0];
		 
		return t;
		
	}

	/**
	 * 随机指定范围内N个不重复的数 在初始化的无重复待选数组中随机产生一个数放入结果中，
	 * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换 然后从len-2里随机产生下一个随机数，如此类推
	 * 
	 * @param max
	 *            指定范围最大值
	 * @param min
	 *            指定范围最小值
	 * @param n
	 *            随机数个数
	 * @return int[] 随机数结果集
	 */
	public static int[] randomArray(int min, int max, int n) {
		int len = max - min + 1;

		if (max < min || n > len) {
			return null;
		}

		// 初始化给定范围的待选数组
		int[] source = new int[len];
		for (int i = min; i < min + len; i++) {
			source[i - min] = i;
		}

		int[] result = new int[n];
		Random rd = new Random(System.currentTimeMillis());
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			// 待选数组0到(len-2)随机一个下标
			index = Math.abs(rd.nextInt() % len--);
			// 将随机到的数放入结果集
			result[i] = source[index];
			// 将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
			source[index] = source[len];
		}
		return result;
	}
	
	
	/**
	 * 返回引用页面
	 * @param request
	 * @param response
	 */
	public static String getReferView(HttpServletRequest request)
	{
 
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
 
		String fullURL = url+"?" + request.getQueryString();	
 
		return fullURL;
		//request.getRequestDispatcher(fullURL).forward(request,response);
	}
	
	
	/**
	 * 引用页面
	 * @param request
	 * @return
	 */
	public static String getHeaderRef(HttpServletRequest request)
	{
		String referer = request.getHeader("Referer");
		referer = "redirect:" + referer;

	    return referer;
	}

}
