package com.xiaba2.cms.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.internal.compiler.ast.ArrayInitializer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.cms.service.ArticleService;
import com.xiaba2.cms.service.ArticleTypeService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Forum;
import com.xiaba2.task.domain.ForumType;
import com.xiaba2.task.domain.Product;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.ForumService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;
import com.xiaba2.util.WebUtil;

/**
 * 通用网页调用
 * 
 * @author goddie
 *
 */
@RestController
@RequestMapping("/webpage")
public class WebPageController {

	@Resource
	ArticleService articleService;

	@Resource
	ArticleTypeService articleTypeService;
	
	@Resource
	ForumService forumService;
	
	/**
	 * 首页导航
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/webnav")
	public ModelAndView getWebNav(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("web_nav");

		User user = SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			mv.addObject("user", user);
		}

		return mv;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin")
	public ModelAndView admin() {
		ModelAndView mv = new ModelAndView("admin_index");

		return mv;
	}
	
	
	@RequestMapping(value = "/reg")
	public ModelAndView reg() {
		ModelAndView mv = new ModelAndView("member_register");

		return mv;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("member_login");
		
		
		
		mv.addObject("redirect",request.getParameter("redirect"));
		
		
		return mv;
	}
	
	
	/**
	 * 是否登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/islogin")
	public JsonResult isLogin(HttpServletRequest request) {
	 
		JsonResult rs =new JsonResult();
		if(SessionUtil.getInstance().getSessionUser()!=null)
		{
			rs.setCode(JsonResult.SUCCESS);
		}
		
		return rs;
	}

	/**
	 * 网站首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView homeIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");

		// 首页顶部

		StringBuilder sb = new StringBuilder();

		ArticleType subType = articleTypeService.get(UUID
				.fromString("abb6a6b1-a93d-4b01-9090-83539bbb968e"));

		List<Article> list1 = articleService.getTop(null, subType, 5, true);

		for (Article article : list1) {
			
			sb.append("<li><a href=\""+ article.getRedirectUrl() +"\" target=\"_self\">");
			sb.append("<img src=\"" + getThumb(article.getThumb(), null)
					+ "\" width=\"100%\" height=\"100%\" /></a></li>");

		}

		mv.addObject("topslider", sb.toString());
		
		//
		
		
		
		// 成功案例
		StringBuilder sb2 = new StringBuilder();

		ArticleType subType2 = articleTypeService.get(UUID
				.fromString("5445869a-8e32-48fc-82bb-1af4bf841dd6"));
		List<Article> list2 = articleService.getTop(null, subType2, 15, true);

		for (Article article : list2) {
			sb2.append("<li><a target=\"_blank\" href=\""+request.getContextPath()+"/article/detail?id="+article.getId()+"\"> <img class=\"flazy\" src=\""
					+ getThumb(article.getThumb(), null)
					+ "\" width=\"393\" height=\"252\">");
			sb2.append("<div class=\"new-c-caseintro\">");
			sb2.append("<p class=\"new-c-casename\">" + article.getTitle()
					+ "</p>");
			sb2.append("<p>" + article.getDescription() + "</p>");
			sb2.append("<span class=\"new-c-case-bask\"></span> ");
			sb2.append("<span class=\"new-c-case-year\">"+ (article.getCreatedDate().getYear()+1900) +"</span>");
			sb2.append("</div>");
			sb2.append("</a></li>");
		}

		mv.addObject("caselist", sb2.toString());

		return mv;
	}
	
	
	/**
	 * 今日头条
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/indexnews")
	public ModelAndView indexNews(HttpServletRequest request) {
	
		ModelAndView mv = new ModelAndView("index_news");
		
		
		ArticleType subType = articleTypeService.get(UUID.fromString("782e337a-fad4-466c-8225-a237467466dc"));

		List<Article> list1 = articleService.getTop(null, subType, 1, true);

		mv.addObject("list1",list1);
		
		
		
		ArticleType subType2 = articleTypeService.get(UUID.fromString("c53aaaf3-3df0-4c52-985b-7aac4e9ff9b7"));

		List<Article> list2 = articleService.getTop(null, subType2, 3, false);
		
		mv.addObject("list2",list2);
		
		
		return mv;
	}
	
	
	/**
	 * 今日头条
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/newslist")
	public ModelAndView newsList(HttpServletRequest request) {
	
		ModelAndView mv = new ModelAndView("news_list");
		
		ArticleType subType = articleTypeService.get(UUID.fromString("c53aaaf3-3df0-4c52-985b-7aac4e9ff9b7"));


		String pstr = request.getParameter("p");
		int p = 1;
		if (!StringUtils.isEmpty(pstr)) {
			p = Integer.parseInt(pstr);
		}


		Page<Article> page = new Page<Article>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = articleService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("subType", subType));
		// List<Article> list = articleService.findByCriteria(criteria);

		page = articleService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		mv.addObject("pageHtml", HttpUtil.genPageHtml(page, request));

		// 热点
		Page<Forum> page2 = new Page<Forum>();
		page2.setPageSize(10);
		page2.setPageNo(1);
		page2.addOrder("visits", "desc");

		DetachedCriteria criteria2 = forumService.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete", 0));
		// criteria2.add(Restrictions.eq("subType", subType));
		// List<Article> list = articleService.findByCriteria(criteria);

		page2 = forumService.findPageByCriteria(criteria2, page2);

		mv.addObject("list2", page2.getResult());
		mv.addObject("webutil", new WebUtil());

		return mv;

	}
	
	
	
	/**
	 * 在线办公
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/online")
	public ModelAndView online(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("online");
		
		ArticleType subType = articleTypeService.get(UUID.fromString("782e337a-fad4-466c-8225-a237467466dc"));

		List<Article> list1 = articleService.getTop(null, subType, 1, true);

		mv.addObject("list",list1);
		
		
		
		ArticleType subType2 = articleTypeService.get(UUID.fromString("c53aaaf3-3df0-4c52-985b-7aac4e9ff9b7"));

		List<Article> list2 = articleService.getTop(null, subType2, 3, true);
		
		mv.addObject("list2",list2);
		
		
		return mv;
	}
	
	
	
	/**
	 * 在线办公
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/onlineblock")
	public ModelAndView onlineBlock(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("online_block");
		
		
		String subId = request.getParameter("tid");
		ArticleType subType  = articleTypeService.get(UUID.fromString(subId));
		List<Article> list = articleService.getTop(null, subType, 5, true);
		mv.addObject("list", list);
		
		return mv;
	}
	
	/**
	 * 首页顶部
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/indextoproll")
	public ModelAndView indexTopRoll(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index_top_roll");

		// 

		ArticleType subType = articleTypeService.get(UUID.fromString("abb6a6b1-a93d-4b01-9090-83539bbb968e"));

		List<Article> list1 = articleService.getTop(null, subType, 2, true);

//		for (Article article : list1) {
//			
//			sb.append("<li><a href=\""+ article.getRedirectUrl() +"\" target=\"_self\">");
//			sb.append("<img src=\"" + getThumb(article.getThumb(), null)
//					+ "\" width=\"100%\" height=\"100%\" /></a></li>");
//
//		}

		mv.addObject("list",list1);
		
		return mv;
	}
	
	/**
	 * 获得验证串
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/token")
	public JsonResult getToken(HttpServletRequest request)
	{
		JsonResult rs = new JsonResult();
		
		
		return rs;
	}

	String getThumb(String thumb, String replace) {
		if (StringUtils.isEmpty(thumb)) {
			return null;
		}

		String ext = thumb.substring(thumb.lastIndexOf("."));

		if (StringUtils.isEmpty(replace)) {
			return thumb.subSequence(0, thumb.lastIndexOf("_")) + ext;
		}

		return thumb.subSequence(0, thumb.lastIndexOf("_") + 1) + replace + ext;

	}

	String getLink(String url) {
		return null;
	}
	
	
	/**
	 * 获得验证串
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/importaccount")
	public ModelAndView importAccount(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView("admin_importaccount");
		
		
		return mv;
	}
	
	
	/**
	 * 在线办公
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/oa")
	public ModelAndView oa(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView("oa");
		
		User user =  SessionUtil.getInstance().getSessionUser();
		
		if(user == null)
		{
			mv.setViewName("redirect:/webpage/login");
			return mv;
		}
		
		mv.addObject("user", user);
		
		return mv;
	} 
	
	
	/**
	 * 在线合作
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/prj")
	public ModelAndView prj(HttpServletRequest request)
	{
		ModelAndView mv =new ModelAndView("prj");
		User user =  SessionUtil.getInstance().getSessionUser();
		
		if(user == null)
		{
			mv.setViewName("redirect:/webpage/login");
			return mv;
		}
		
		mv.addObject("user", user);
		
		return mv;
	} 

}
