package com.xiaba2.task.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.xiaba2.core.Page;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.domain.Work;
import com.xiaba2.task.service.UserService;
import com.xiaba2.task.service.WorkService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/work")
public class WorkController {
	@Resource
	private WorkService workService;
	
	@Resource
	private UserService userService;
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping(value = "/v/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("user_work_add");
		return mv;
	}
	
	
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(Work entity, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/work/v/add");

		User user = SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		
		entity.setUser(user);
		entity.setCreatedDate(new Date());

		attr.addFlashAttribute("msg", "上传成功");
		
		workService.save(entity);

		return mv;
	}
	
	
	
	/**列表
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/list")
	public ModelAndView adminList(@RequestParam("p") int p,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_work_list");

		User user = SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		Page<Work> page = new Page<Work>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
		
		

		DetachedCriteria criteria = workService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", user));

		page = workService.findPageByCriteria(criteria, page);

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
		ModelAndView mv = new ModelAndView("redirect:/work/v/list?p=1");
		Work entity = workService.get(uuid);
		entity.setIsDelete(1);
		workService.saveOrUpdate(entity);
		return mv;
	}
	
	
	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam("id") UUID uuid) {
		ModelAndView mv = new ModelAndView("work_detail");
		Work entity = workService.get(uuid);
 
		mv.addObject("entity", entity);


		return mv;
	}
	
}