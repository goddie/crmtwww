package com.xiaba2.cms.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.cms.domain.ArticleType;

import com.xiaba2.cms.service.ArticleBodyService;
import com.xiaba2.cms.service.ArticleService;
import com.xiaba2.cms.service.ArticleTypeService;
import com.xiaba2.core.Page;
import com.xiaba2.util.HttpUtil;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleService articleService;

	@Resource
	private ArticleBodyService aticleBodyService;

	@Resource
	private ArticleTypeService articleTypeService;
	
	@Resource
	private ArticleBodyService articleBodyService;

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView hello2() {

		ArticleBody entity = new ArticleBody();

		ModelAndView mv = new ModelAndView();
		mv.addObject("message", entity.getId().toString());
		mv.setViewName("users");
		return mv;
	}

	@RequestMapping(value = "/action/add")
	public ModelAndView add(Article entity, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/article/admin/add");
		
		if(StringUtils.isEmpty(entity.getTitle()))
		{
			return mv;
		}
		
		entity.setCreatedDate(new Date());

		String pid = request.getParameter("parentTypeId");
		if (!StringUtils.isEmpty(pid)) {
			entity.setType(articleTypeService.get(UUID.fromString(pid)));
		}

		String sid = request.getParameter("subTypeId");
		if (!StringUtils.isEmpty(sid)) {
			entity.setSubType(articleTypeService.get(UUID.fromString(sid)));
		}

		articleService.save(entity);
		// entity.setAuthor(author);
		ArticleBody body = new ArticleBody();

		body.setBody(request.getParameter("content"));
		body.setArticle(entity);
		body.setCreatedDate(new Date());

		aticleBodyService.save(body);
		
		attr.addFlashAttribute("msg", "<script>alert('发布成功')</script>");
		
		

		return mv;
	}

	// @RequestMapping(value = "/{page}")
	// public ModelAndView page(@PathVariable String page) {
	// ModelAndView mv = new ModelAndView("admin_article_" + page);
	// return mv;
	// }

	/**
	 * 新增
	 * @return
	 */
	@RequestMapping(value = "/admin/add")
	public ModelAndView adminAdd() {
		ModelAndView mv = new ModelAndView("admin_article_add");
		return mv;
	}

	/**列表
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_article_list");

		Page<Article> page = new Page<Article>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = articleService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		// List<Article> list = articleService.findByCriteria(criteria);

		page = articleService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		mv.addObject("pageHtml", page.genPageHtml(request));
		return mv;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID uuid) {
		ModelAndView mv = new ModelAndView("redirect:/article/admin/list?p=1");
		Article entity = articleService.get(uuid);
		entity.setIsDelete(1);
		articleService.saveOrUpdate(entity);
		return mv;
	}
	
	
	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam("id") UUID uuid) {
		ModelAndView mv = new ModelAndView("article_detail");
		Article entity = articleService.get(uuid);
		ArticleBody body = articleBodyService.getByArticle(entity);
		
		mv.addObject("entity", entity);
		mv.addObject("body", body);
		
		return mv;
	}

	
	
	
}
