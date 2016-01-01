package com.xiaba2.task.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.task.controller.TaskTypeController.MenuData;
import com.xiaba2.task.domain.ForumType;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.task.service.ForumTypeService;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/forumtype")
public class ForumTypeController {
	@Resource
	private ForumTypeService forumTypeService;

	@RequestMapping(value = "/admin/add")

	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("admin_forumtype_add");

		DetachedCriteria criteria = forumTypeService.createDetachedCriteria();
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("isDelete", 0));

		List<ForumType> list = forumTypeService.findByCriteria(criteria);

		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p,
			HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("admin_forumtype_list");

//		DetachedCriteria criteria = forumTypeService.createDetachedCriteria();
//		criteria.add(Restrictions.eq("isDelete", 0));
//
//		List<ForumType> list = forumTypeService.findByCriteria(criteria);
//		mv.addObject("list", list);
//		return mv;
		
		
		ModelAndView mv = new ModelAndView("admin_forumtype_list");
		
		Page<ForumType> page = new Page<ForumType>();
		
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("parent.id", "desc");
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = forumTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

//		List<ArticleType> list = articleTypeService.findByCriteria(criteria);
		page = forumTypeService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		mv.addObject("pageHtml", page.genPageHtml(request));
		return mv;
	}

	@RequestMapping(value = "/json/list")
	public List<ForumType> jsonlist(HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		DetachedCriteria criteria = forumTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		String pid = request.getParameter("parentId");
		if (!StringUtils.isEmpty(pid)) {
			criteria.add(Restrictions.eq("parent.id", UUID.fromString(pid)));
		}else
		{
			criteria.add(Restrictions.isNull("parent"));
		}
		List<ForumType> list = forumTypeService.findByCriteria(criteria);
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
	public ModelAndView actionDel(@RequestParam("fid") UUID uuid) {
		ModelAndView mv = new ModelAndView("redirect:/forumtype/admin/list?p=1");
		
		
 
		ForumType entity = forumTypeService.get(uuid);
		entity.setIsDelete(1);
		forumTypeService.saveOrUpdate(entity);
		
		
		DetachedCriteria criteria  = forumTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("parent", entity));
		criteria.add(Restrictions.eq("isDelete", 0));
		List<ForumType> list  = forumTypeService.findByCriteria(criteria);
		for (ForumType forumType : list) {
			forumType.setIsDelete(1);
			forumTypeService.saveOrUpdate(forumType);
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
	public ModelAndView actionAdd(ForumType entity,
			@RequestParam("pid") String pid) {

		ModelAndView mv = new ModelAndView("redirect:/forumtype/admin/add");

		if (!pid.equals("0")) {
			ForumType parent = forumTypeService.get(UUID.fromString(pid));
			entity.setParent(parent);
		}
		entity.setCreatedDate(new Date());
		forumTypeService.save(entity);
		return mv;
	}
	
	
	@RequestMapping(value = "/menu")
	public ModelAndView getForumMenu() {
		ModelAndView mv = new ModelAndView("forum_menu");
		DetachedCriteria criteria = forumTypeService.createDetachedCriteria();
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("isDelete", 0));

		Page<ForumType> page = new Page<ForumType>();
		page.setPageSize(6);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		page = forumTypeService.findPageByCriteria(criteria, page);

		// Page<Task> p=new Page<Task>();
		// p.setPageSize(count);
		// p.setPageNo(1);
		// p.addOrder("createdDate", "desc");
		// p = taskService.findPageByCriteria(criteria, p);
		// //List<Product> list = productService.findByCriteria(criteria);
		// mv.addObject("list", p.getResult());

		List<MenuData> list = new ArrayList<MenuData>();

		for (ForumType parent : page.getResult()) {
			MenuData data = new MenuData(parent, getSubTypes(parent, 10));
			list.add(data);
		}
 
		List<MenuData> list2 = new ArrayList<MenuData>();
		for (int i = list.size()-1; i >=0; i--) {
			if(list.get(i).getParent().getName().equals("站务管理"))
			{
				continue;
			}
			list2.add(list.get(i));
		}
 
		
		mv.addObject("list", list2);

		return mv;
	}
	
	
	/**
	 * 获取子类
	 * 
	 * @param parent
	 * @param count
	 * @return
	 */
	List<ForumType> getSubTypes(ForumType parent, int count) {
		DetachedCriteria criteria = forumTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("parent", parent));
		criteria.add(Restrictions.eq("isDelete", 0));

		Page<ForumType> page = new Page<ForumType>();
		page.setPageSize(count);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		page = forumTypeService.findPageByCriteria(criteria, page);

		return page.getResult();
	}
	
	public class MenuData {
		public MenuData(ForumType p, List<ForumType> s) {
			this.parent = p;
			this.sub = s;
		}

		private ForumType parent;
		private List<ForumType> sub;
		public ForumType getParent() {
			return parent;
		}
		public void setParent(ForumType parent) {
			this.parent = parent;
		}
		public List<ForumType> getSub() {
			return sub;
		}
		public void setSub(List<ForumType> sub) {
			this.sub = sub;
		}

		

	}
	
}