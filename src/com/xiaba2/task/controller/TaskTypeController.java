package com.xiaba2.task.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.task.service.TaskTypeService;
import com.xiaba2.util.HttpUtil;

@RestController
@RequestMapping("/tasktype")
public class TaskTypeController {
	@Resource
	private TaskTypeService taskTypeService;

	/**
	 * 登录页面
	 * 
	 * @return
	 */
//	@RequestMapping(value = "/page/{name}")
//	public ModelAndView getPage(@PathVariable String name) {
//		return new ModelAndView("tasktype_" + name);
//	}
	
	/**
	 * 新增
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/add")
	public ModelAndView adminAdd() {
		
		ModelAndView mv = new ModelAndView("admin_tasktype_add");

		DetachedCriteria criteria = taskTypeService.createDetachedCriteria();
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("isDelete", 0));

		List<TaskType> list = taskTypeService.findByCriteria(criteria);

		mv.addObject("list", list);

		return mv;
	}

	/**
	 * 管理页面
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_tasktype_list");
		
		Page<TaskType> page = new Page<TaskType>();
		
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("parent.id", "desc");
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = taskTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

//		List<ArticleType> list = articleTypeService.findByCriteria(criteria);
		page = taskTypeService.findPageByCriteria(criteria, page);
		
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
	public ModelAndView actionDel(@RequestParam("id") String uuid) {
		ModelAndView mv = new ModelAndView("redirect:/tasktype/admin/list?p=1");
		TaskType entity = taskTypeService.get(UUID.fromString(uuid));
		entity.setIsDelete(1);
		taskTypeService.saveOrUpdate(entity);
		
		
		DetachedCriteria criteria  = taskTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("parent", entity));
		criteria.add(Restrictions.eq("isDelete", 0));
		List<TaskType> list  = taskTypeService.findByCriteria(criteria);
		for (TaskType taskType : list) {
			taskType.setIsDelete(1);
			taskTypeService.saveOrUpdate(taskType);
		}
		
		
		return mv;
	}

	@RequestMapping(value = "/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("admin_tasktype_add");
		DetachedCriteria criteria = taskTypeService.createDetachedCriteria();
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("isDelete", 0));
		List<TaskType> list = taskTypeService.findByCriteria(criteria);
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(value = "/menu")
	public ModelAndView getTaskMenu() {
		ModelAndView mv = new ModelAndView("task_menu");
		DetachedCriteria criteria = taskTypeService.createDetachedCriteria();
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("isDelete", 0));

		Page<TaskType> page = new Page<TaskType>();
		page.setPageSize(11);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		page = taskTypeService.findPageByCriteria(criteria, page);

		// Page<Task> p=new Page<Task>();
		// p.setPageSize(count);
		// p.setPageNo(1);
		// p.addOrder("createdDate", "desc");
		// p = taskService.findPageByCriteria(criteria, p);
		// //List<Product> list = productService.findByCriteria(criteria);
		// mv.addObject("list", p.getResult());

		List<MenuData> list = new ArrayList<MenuData>();

		for (TaskType parent : page.getResult()) {
			MenuData data = new MenuData(parent, getSubTypes(parent, 10));
			list.add(data);
		}

		mv.addObject("list", list);

		return mv;
	}

	/**
	 * 获取子类
	 * 
	 * @param parent
	 * @param count
	 * @return
	 */
	List<TaskType> getSubTypes(TaskType parent, int count) {
		DetachedCriteria criteria = taskTypeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("parent", parent));
		criteria.add(Restrictions.eq("isDelete", 0));

		Page<TaskType> page = new Page<TaskType>();
		page.setPageSize(count);
		page.setPageNo(1);
		page.addOrder("createdDate", "desc");

		page = taskTypeService.findPageByCriteria(criteria, page);

		return page.getResult();
	}

	/**
	 * 新建
	 * 
	 * @param entity
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(TaskType entity,
			@RequestParam("pid") String pid) {

		ModelAndView mv = new ModelAndView("redirect:/tasktype/add");

		if (!pid.equals("0")) {
			TaskType parent = taskTypeService.get(UUID.fromString(pid));
			entity.setParent(parent);
		}
		entity.setCreatedDate(new Date());
		taskTypeService.save(entity);
		return mv;
	}

	// /**
	// * 新增分类
	// *
	// * @param name
	// * @return
	// */
	// @RequestMapping(value = "/add")
	// public ModelAndView add(@RequestParam("parentId") String parentId,
	// TaskType entity, RedirectAttributes attr) {
	//
	// ModelAndView mv = new ModelAndView("redirect:/tasktype/admin/add");
	//
	// if (StringUtils.isBlank(entity.getName())) {
	// return mv;
	// }
	//
	// if (parentId != null && !parentId.equals("-1")) {
	//
	// UUID id = UUID.fromString(parentId);
	// TaskType p = taskTypeService.get(id);
	// entity.setParent(p);
	// attr.addFlashAttribute("selected", p);
	// }
	//
	// taskTypeService.save(entity);
	//
	// //List<TaskType> list = taskTypeService.getByParent(null);
	//
	// attr.addFlashAttribute("msg", "新增成功!");
	//
	// //mv.addObject("list",list);
	//
	// return mv;
	// }

	@RequestMapping(value = "/json/list")
	public List<TaskType> getList(@RequestParam("parentId") String parentId) {

		TaskType parent = null;

		if (!StringUtils.isBlank(parentId)) {
			UUID pid = UUID.fromString(parentId);
			parent = taskTypeService.get(pid);
		}

		List<TaskType> list = taskTypeService.getByParent(parent);

		return list;

	}

	public class MenuData {
		public MenuData(TaskType p, List<TaskType> s) {
			this.parent = p;
			this.sub = s;
		}

		private TaskType parent;
		private List<TaskType> sub;

		public TaskType getParent() {
			return parent;
		}

		public void setParent(TaskType parent) {
			this.parent = parent;
		}

		public List<TaskType> getSub() {
			return sub;
		}

		public void setSub(List<TaskType> sub) {
			this.sub = sub;
		}

	}

}