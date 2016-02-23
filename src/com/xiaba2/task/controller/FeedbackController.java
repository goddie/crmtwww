package com.xiaba2.task.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Feedback;
import com.xiaba2.task.domain.Product;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.FeedbackService;
import com.xiaba2.task.service.ProductService;
import com.xiaba2.task.service.TaskService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.SessionUtil;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Resource
	private FeedbackService feedbackService;

	@Resource
	private UserService userService;

	@Resource
	private TaskService taskService;

	@Resource
	private ProductService productService;

	/**
	 * 发表评论
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(HttpServletRequest request) {
		JsonResult js = new JsonResult();

		User user = SessionUtil.getInstance().getSessionUser();
		if (user == null) {
			js.setMsg("请登录!");
			return js;
		}

		user = userService.get(user.getId());

		Feedback entity = new Feedback();
		entity.setCreatedDate(new Date());
		entity.setContent(request.getParameter("content"));
		entity.setAuthor(user);

		String taskId = request.getParameter("taskId");
		if (!StringUtils.isEmpty(taskId)) {
			Task task = taskService.get(UUID.fromString(taskId));
			entity.setTask(task);
		}

		String productId = request.getParameter("productId");
		if (!StringUtils.isEmpty(productId)) {
			Product product = productService.get(UUID.fromString(productId));
			entity.setProduct(product);
		}

		feedbackService.save(entity);

		js.setCode(JsonResult.SUCCESS);

		return js;
	}

	/**
	 * 任务评论列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tasklist")
	public ModelAndView taksList(@RequestParam("taskId") String taskId, @RequestParam("p") int p) {
		ModelAndView mv = new ModelAndView("task_block_feedback");

		Task task = taskService.get(UUID.fromString(taskId));

		DetachedCriteria criteria = feedbackService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("task.id", task.getId()));

		Page<Feedback> page = new Page<Feedback>();

		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");

		page = feedbackService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}

	@RequestMapping(value = "/productlist")
	public ModelAndView productList(@RequestParam("productId") UUID productId, @RequestParam("p") int p) {
		ModelAndView mv = new ModelAndView("task_block_feedback");

		Product product = productService.get(productId);

		DetachedCriteria criteria = feedbackService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("product", product));

		Page<Feedback> page = new Page<Feedback>();

		page.setPageNo(1);
		page.setPageSize(999);
		page.addOrder("createdDate", "desc");

		page = feedbackService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		return mv;
	}

}