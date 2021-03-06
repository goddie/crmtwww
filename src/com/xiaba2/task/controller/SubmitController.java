package com.xiaba2.task.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.Checksum;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.mysql.fabric.xmlrpc.base.Array;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Submit;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.gen.EnumSet.CheckStatus;
import com.xiaba2.task.gen.EnumSet.TaskStatus;
import com.xiaba2.task.service.SubmitService;
import com.xiaba2.task.service.TaskService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.ListUtil;
import com.xiaba2.util.SessionUtil;

@RestController
@RequestMapping("/submit")
public class SubmitController {
	@Resource
	private SubmitService submitService;

	@Resource
	private UserService userService;

	@Resource
	private TaskService taskService;

	@RequestMapping(value = "/v/useradd")
	public ModelAndView add(@RequestParam("taskId") UUID taskId, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/task/detail?tid=" + taskId.toString());

		Task task = taskService.get(taskId);
		
		if(task.getStatus() == TaskStatus.END 
				||	task.getStatus() == TaskStatus.EVALUATION 
				||	task.getStatus() == TaskStatus.REVIEW_FAIL)
		{
			attr.addFlashAttribute("msg", "<script>alert('无法再投稿!');</script>");
			return mv;
		}

		User user = SessionUtil.getInstance().getSessionUser();
		
		user = userService.get(user.getId());
		
		if (user == null) {

			attr.addFlashAttribute("msg", "<script>alert('请先登录!');</script>");
			return mv;

		}

		if (user != null) {

			if (user.getIsCheckCompany() != CheckStatus.SUCCESS && user.getIsCheckPerson() != CheckStatus.SUCCESS) {
				attr.addFlashAttribute("msg", "<script>alert('请先通过实名认证，再进行投标!');</script>");
				return mv;
			}

		}
		
		if(task.getStatus() < TaskStatus.REVIEW)
		{
			attr.addFlashAttribute("msg", "<script>alert('操作失败:该任务还没有审核通过!');</script>");
			return mv;
		}

		DetachedCriteria criteria = submitService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user.id", user.getId()));
		criteria.add(Restrictions.eq("task.id", taskId));
		//criteria.add(Restrictions.eq("status", CheckStatus.INVALID));


		List<Submit> list = submitService.findByCriteria(criteria);
		if (ListUtil.isEmpty(list)) {
			
			return mv;
			//attr.addFlashAttribute("msg", "<script>alert('请勿重复投稿!');</script>");
			//return mv;
		}

		mv.addObject("user", user);
		mv.addObject("task", task);
		mv.addObject("topType", task.getTopType());
		mv.addObject("topName", getTopName(task.getTopType()));
		mv.addObject("submitId", list.get(0).getId());
		mv.addObject("submit", list.get(0));
		mv.setViewName("admin_submit_add");
		
		return mv;
	}
	
	
	
	@RequestMapping(value = "/v/view")
	public ModelAndView view(@RequestParam("submitId") UUID submitId, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("admin_submit_view");

		Submit submit = submitService.get(submitId);

		User user = SessionUtil.getInstance().getSessionUser();
		
		user = userService.get(user.getId());
		

		mv.addObject("user", user);
		mv.addObject("task", submit.getTask());
		mv.addObject("topType", submit.getTopType());
		mv.addObject("topName", getTopName(submit.getTask().getTopType()));
		mv.addObject("submitId", submit.getId());
		mv.addObject("submit", submit);
		
		return mv;
	}

	/**
	 * 投标详细
	 * 
	 * @param sid
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam("sid") UUID sid, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("admin_submit_detail");

		User user = SessionUtil.getInstance().getSessionUser();

		if (user == null) {
			return new ModelAndView("redirect:/user/index");
		}

		user = userService.get(user.getId());

		Submit entity = submitService.get(sid);

		User user2 = userService.get(entity.getTask().getUser().getId());

		if (!user.getId().toString().equals(user2.getId().toString())) {
			return new ModelAndView("redirect:/user/index");
		}

		mv.addObject("entity", entity);

		return mv;
	}

	/**
	 * 评标
	 * 
	 * @param taskId
	 * @param request
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/v/review")
	public ModelAndView review(@RequestParam("taskId") UUID taskId, HttpServletRequest request,
			RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("admin_submit_review");
		Task task = taskService.get(taskId);

		DetachedCriteria criteria = submitService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("task", task));

		Page<Submit> page = new Page<Submit>();
		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");

		page = submitService.findPageByCriteria(criteria, page);

		mv.addObject("task", task);

		mv.addObject("list", page.getResult());

		return mv;
	}

	/**
	 * 1 单人悬赏 2 计件悬赏 3 项目招标 4 项目交付 5 直接雇佣
	 * 
	 * @param topType
	 * @return
	 */
	String getTopName(int topType) {

		String name = "";
		switch (topType) {
		case 1:
			name = "单人悬赏";
			break;
		case 2:
			name = "计件悬赏";
			break;
		case 3:
			name = "项目招标";
			break;
		case 4:
			name = "项目交付";
			break;
		case 5:
			name = "直接雇佣";
			break;
		default:
			name = "单人悬赏";
			break;
		}

		return name;
	}

	/**
	 * 管理页面
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v/list")
	public ModelAndView getUserList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_submit_list");

		int topType = 1;

		if (!StringUtils.isEmpty(request.getParameter("type"))) {
			topType = Integer.parseInt(request.getParameter("type"));
		}

		String name = getTopName(topType);

		DetachedCriteria criteria = submitService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		User user = SessionUtil.getInstance().getSessionUser();
		user = userService.get(user.getId());
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("topType", topType));

		Page<Submit> page = new Page<Submit>();
		page.setPageNo(1);
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.addOrder("createdDate", "desc");

		page = submitService.findPageByCriteria(criteria, page);

		List<Submit> list = page.getResult();

		mv.addObject("list", list);
		mv.addObject("topType", topType);
		mv.addObject("topName", name);
		mv.addObject("p", page.genPageHtml(request));
		
		List<SubmitResult> dblist = new ArrayList<SubmitResult>();
		
		//List<List<Submit>> winList = new ArrayList<List<Submit>>();
		for (Submit submit : list) {
			
			SubmitResult sr = new SubmitResult();
			sr.submit = submit;
			sr.winList = submitService.getWinList(submit.getTask());
			dblist.add(sr);
			//List<Submit> win = submitService.getWinList(submit.getTask());
			//winList.add(win);
		}
		mv.addObject("dblist", dblist);
		

		return mv;
	}

	/**
	 * 新增投稿
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(Submit entity, @RequestParam("submitId") UUID submitId, RedirectAttributes attr,
			HttpServletRequest request) {
		Submit submit = submitService.get(submitId);
		
		
		ModelAndView mv = new ModelAndView("redirect:/task/detail?tid=" + submit.getTask().getId());

		User user = SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
			entity.setUser(user);
		}


		submit.setCreatedDate(new Date());
		
		submit.setContent(entity.getContent());
		submit.setQQ(entity.getQQ());
		submit.setPhone(entity.getPhone());
		submit.setName(entity.getName());
		submit.setSex(entity.getSex());
		submit.setEmail(entity.getEmail());
		submit.setTel(entity.getTel());
		submit.setTopType(entity.getTopType());
		
		
		if(submit.getStatus()!=CheckStatus.SUCCESS)
		{
			submit.setStatus(CheckStatus.SUCCESS);
			Task t = submit.getTask();
			t.setSubmitCount(t.getSubmitCount() + 1);
			taskService.saveOrUpdate(t);
		}
		
		//BeanUtils.copyProperties(entity, submit, "task","user");
		

		submitService.saveOrUpdate(submit);
		attr.addFlashAttribute("msg", "<script>alert('恭喜您，投稿成功!');</script>");
		
		

		// attr.addFlashAttribute("msg", "投稿成功!");
		return mv;

	}

	/**
	 * 选为中标
	 * 
	 * @param entity
	 * @param taskId
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/win")
	public ModelAndView actionWin(@RequestParam("sid") UUID sid, RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		Submit entity = submitService.get(sid);

		entity.setResult(1);
		entity.setLastModifiedDate(new Date());
		entity.setIsWin(1);
		submitService.saveOrUpdate(entity);

		Task task = entity.getTask();
		
		
		if(task.getTopType()==1)
		{
			task.setWin(entity);
			task.setStatus(TaskStatus.END);
		}
		
		
		if(task.getTopType()==2)
		{
			task.setWinCount(task.getWinCount()+1);
			
			if(task.getWinCount()==task.getBountyCount())
			{
				task.setStatus(TaskStatus.END);
			}
			
		}


		taskService.saveOrUpdate(task);

		
		String taskList = "task/v/list?type="+task.getTopType()+"&p=1";
			mv.setViewName("redirect:/"+taskList);	
		//mv.setViewName("redirect:/submit/review?taskId=" + task.getId());
		attr.addFlashAttribute("msg", "<script>alert('操作成功!');</script>");

		return mv;

	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("uuid") UUID uuid,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Submit entity = submitService.get(uuid);
		entity.setIsDelete(1);
		submitService.saveOrUpdate(entity);
		
		mv.setViewName(HttpUtil.getHeaderRef(request));
		return mv;
	}
	
	/**
	 * 新增投稿
	 * 
	 * @return
	 */
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("taskId") UUID taskId,HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		

		Submit entity = new Submit();
		
		User user = SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
			entity.setUser(user);
		}else
		{
			rs.setCode(JsonResult.FAIL);
			rs.setMsg("投稿失败,请先登录.");
			return rs;
		}

		Task task = taskService.get(taskId);

		// DetachedCriteria criteria = submitService.createDetachedCriteria();
		// criteria.add(Restrictions.eq("isDelete", 0));

		// criteria.add(Restrictions.eq("user.id", user.getId()));
		// criteria.add(Restrictions.eq("task.id", taskId));
		//
		// // 重复投稿
		// List<Submit> list = submitService.findByCriteria(criteria);
		// if (list != null && list.size() == 0) {
		// attr.addFlashAttribute("msg", "<script>alert('请勿重复投稿!');</script>");
		// return mv;
		// }

		entity.setTask(task);
		entity.setCreatedDate(new Date());
		entity.setStatus(CheckStatus.INVALID);

		submitService.save(entity);


		//task.setSubmitCount(task.getSubmitCount() + 1);
		taskService.saveOrUpdate(task);

		// attr.addFlashAttribute("msg", "投稿成功!");
		
		rs.setCode(JsonResult.SUCCESS);
		
		return rs;

	}
	
	public static class SubmitResult
	{
		private Submit submit;
		private List<Submit> winList;
		public Submit getSubmit() {
			return submit;
		}
		public void setSubmit(Submit submit) {
			this.submit = submit;
		}
		public List<Submit> getWinList() {
			return winList;
		}
		public void setWinList(List<Submit> winList) {
			this.winList = winList;
		}
		
		
	}

}