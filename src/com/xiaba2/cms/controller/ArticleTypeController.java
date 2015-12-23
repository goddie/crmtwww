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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.cms.service.ArticleTypeService;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/articletype")
public class ArticleTypeController {
	@Resource
	private ArticleTypeService articleTypeService;

	@RequestMapping(value = "/admin/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("admin_articletype_add");

		DetachedCriteria criteria = articleTypeService.createDetachedCriteria();
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("isDelete", 0));

		List<ArticleType> list = articleTypeService.findByCriteria(criteria);

		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_articletype_list");
		
		Page<ArticleType> page = new Page<ArticleType>();
		
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("parent", "desc");
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = articleTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

//		List<ArticleType> list = articleTypeService.findByCriteria(criteria);
		page = articleTypeService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml", page.genPageHtml(request));
		return mv;
	}

	@RequestMapping(value = "/json/list")
	public List<ArticleType> jsonlist(HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		DetachedCriteria criteria = articleTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		String pid = request.getParameter("parentId");
		if (!StringUtils.isEmpty(pid)) {
			criteria.add(Restrictions.eq("parent.id", UUID.fromString(pid)));
		}else
		{
			criteria.add(Restrictions.isNull("parent"));
		}
		List<ArticleType> list = articleTypeService.findByCriteria(criteria);
		rs.setData(list);
		rs.setCode(JsonResult.SUCCESS);
		return list;
	}

	/**
	 * 删除，只是把version 改大
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID uuid) {
		ModelAndView mv = new ModelAndView("redirect:/articletype/admin/list?p=1");
		ArticleType entity = articleTypeService.get(uuid);
		entity.setIsDelete(1);
		articleTypeService.saveOrUpdate(entity);
		
		
		DetachedCriteria criteria  = articleTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("parent", entity));
		criteria.add(Restrictions.eq("isDelete", 0));
		List<ArticleType> list  = articleTypeService.findByCriteria(criteria);
		for (ArticleType articleType : list) {
			articleType.setIsDelete(1);
			articleTypeService.saveOrUpdate(articleType);
		}
		
		
		
		return mv;
	}

	/**
	 * 新建
	 * 
	 * @param entity
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(ArticleType entity,
			@RequestParam("pid") String pid) {

		ModelAndView mv = new ModelAndView("redirect:/articletype/admin/add");

		if (!pid.equals("0")) {
			ArticleType parent = articleTypeService.get(UUID.fromString(pid));
			entity.setParent(parent);
		}
		entity.setCreatedDate(new Date());
		articleTypeService.save(entity);
		return mv;
	}

	
 
}