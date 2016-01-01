package com.xiaba2.task.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.cms.service.ArticleService;
import com.xiaba2.cms.service.ArticleTypeService;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Order;
import com.xiaba2.task.domain.PayRecord;
import com.xiaba2.task.domain.Product;
import com.xiaba2.task.domain.Submit;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.gen.EnumSet;
import com.xiaba2.task.gen.EnumSet.CheckStatus;
import com.xiaba2.task.gen.EnumSet.TaskStatus;
import com.xiaba2.task.service.OrderService;
import com.xiaba2.task.service.PayRecordService;
import com.xiaba2.task.service.SubmitService;
import com.xiaba2.task.service.TaskService;
import com.xiaba2.task.service.TaskTypeService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/task")
public class TaskController {
	@Resource
	private TaskService taskService;

	@Resource
	private TaskTypeService taskTypeService;

	@Resource
	private UserService userService;

	@Resource
	private ArticleTypeService articleTypeService;

	@Resource
	private ArticleService articleService;

	@Resource
	private OrderService orderService;
	
	@Resource
	private PayRecordService payRecordService;
	
	@Resource
	private SubmitService submitService;
	
	/**
	 * 页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page/{name}")
	public ModelAndView getPage(@PathVariable String name) {
		return new ModelAndView("task_" + name);
	}

	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID uuid) {
		ModelAndView mv = new ModelAndView("redirect:/task/admin/list?p=1");
		Task entity = taskService.get(uuid);
		entity.setIsDelete(1);
		taskService.saveOrUpdate(entity);
		return mv;
	}
	
	
	@RequestMapping(value = "/action/review")
	public ModelAndView actionReview(@RequestParam("id") UUID uuid,@RequestParam("rs") int rs) {
		ModelAndView mv = new ModelAndView("redirect:/task/admin/list?p=1");
		Task entity = taskService.get(uuid);
	
//		if(entity.getStatus() != TaskStatus.PUBLISH)
//		{
//			return mv;
//		}
		
		if(rs==0)
		{
			entity.setStatus(TaskStatus.REVIEW_FAIL);
		}
		
		if(rs==1)
		{
			entity.setStatus(TaskStatus.SUBMIT);
		}
		
		taskService.saveOrUpdate(entity);
		
		return mv;
	}

	/**
	 * 用户管理页面
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v/list")
	public ModelAndView getUserList(@RequestParam("p") int p, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_task_list");

		int topType = 1;

		if (!StringUtils.isEmpty(request.getParameter("type"))) {
			topType = Integer.parseInt(request.getParameter("type"));
		}

		String name = getTopName(topType);

		DetachedCriteria criteria = taskService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		User user = SessionUtil.getInstance().getSessionUser();
		user = userService.get(user.getId());
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("topType", topType));

		Page<Task> page = new Page<Task>();
		page.setPageNo(p);
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.addOrder("createdDate", "desc");

		page = taskService.findPageByCriteria(criteria, page);

		List<Task> list = page.getResult();

		mv.addObject("list", list);
		mv.addObject("topType", topType);
		mv.addObject("topName", name);
		mv.addObject("p", page.genPageHtml(request));
		return mv;
	}

	/**
	 * 用户管理页面
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView getAdminList(@RequestParam("p") int p, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_task_list");

		int topType = 1;

		if (!StringUtils.isEmpty(request.getParameter("type"))) {
			topType = Integer.parseInt(request.getParameter("type"));
		}

		String name = getTopName(topType);

		DetachedCriteria criteria = taskService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		criteria.add(Restrictions.eq("topType", topType));

		Page<Task> page = new Page<Task>();
		page.setPageNo(p);
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.addOrder("createdDate", "desc");

		page = taskService.findPageByCriteria(criteria, page);

		List<Task> list = page.getResult();

		mv.addObject("list", list);
		mv.addObject("topType", topType);
		mv.addObject("topName", name);
		mv.addObject("p", page.genPageHtml(request));
		return mv;
	}

	@RequestMapping(value = "/v/add")
	public ModelAndView getAdminAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_task_add");

		int topType = 1;

		if (!StringUtils.isEmpty(request.getParameter("type"))) {
			topType = Integer.parseInt(request.getParameter("type"));
		}

		if (topType == 3) {
			User user = (User) SessionUtil.getInstance().getSessionUser();
			if (user != null) {
				user = userService.get(user.getId());
			}

			if (user.getIsCheckCompany() != CheckStatus.SUCCESS) {

				ModelAndView mv2 = new ModelAndView("redirect:/task/v/list?p=1");
				mv2.addObject("msg", "<script>alert('需要企业验证才能发布招标任务')</script>");
				return mv2;
			}
		}

		mv.addObject("topType", topType);

		return mv;
	}
	
	
	/**
	 * 支付任务
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/paytask")
	public ModelAndView payTask(@RequestParam("orderId") UUID orderId, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_paytask");

		Order order = orderService.get(orderId);
		Task task = order.getTask();
		
		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		//单人
		
		float toPay = task.getBounty();
		
		if(user.getMoney()<toPay)
		{
			mv.setViewName("redirect:/recharge/v/recharge?toPay="+toPay+"&orderId="+orderId);
			return mv;
		}
		
		mv.addObject("toPay", toPay);
		mv.addObject("orderId", orderId.toString());
		mv.addObject("task", task);
		return mv;
	}
	
	
	/**
	 * 支付任务
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/paytask")
	public ModelAndView actionPayTask(@RequestParam("orderId") UUID orderId, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/task/v/list?p=1");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		Order order = orderService.get(orderId);
		
		Task task = order.getTask();
		float toPay = task.getBounty();
		
		if(user.getMoney()<toPay)
		{
			mv.setViewName("redirect:/recharge/v/add?orderId="+orderId);
			return mv;
		}
		
		user.setMoney(user.getMoney()-toPay);
		userService.saveOrUpdate(user);
		
		task.setIsPay(1);
		//task.setStatus(TaskStatus.);
		taskService.saveOrUpdate(task);
		
		PayRecord payRecord = new PayRecord();
		payRecord.setCreatedDate(new Date());
		payRecord.setTotal(order.getTotal());
		payRecord.setUser(user);
		payRecord.setOrder(order);
		
		payRecordService.save(payRecord);
		
		order.setStatus(1);
		orderService.saveOrUpdate(order);
		
		return mv;
	}

	/**
	 * 新增任务
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(Task entity, RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// if(StringUtils.isEmpty(entity.getTitle()))
		// {
		// return mv;
		// }

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
			entity.setUser(user);
		}

		String pid = request.getParameter("parentTypeId");
		
		if(!StringUtils.isEmpty(pid))
		{
			TaskType ptype = taskTypeService.get(UUID.fromString(pid));
			entity.setParentType(ptype);
		}

		String sid = request.getParameter("subTypeId");
		if(!StringUtils.isEmpty(sid))
		{
			TaskType stype = taskTypeService.get(UUID.fromString(sid));
			entity.setSubType(stype);
		}
		

		
		

		// entity.setTopType(entity.getBountyMode());
		entity.setCreatedDate(new Date());

		entity.setStatus(TaskStatus.PUBLISH);
		
		entity.setIsPay(0);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date d;
		try {
			d = sdf.parse(request.getParameter("endDateStr"));
			entity.setEndDate(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//单人
		if(entity.getBountyMode()==1)
		{
			entity.setBounty(entity.getBountyPrice());
		}
		
		//记件
		if(entity.getBountyMode()==2)
		{
			entity.setBounty(entity.getBountyPrice()*entity.getBountyCount());
		}

		taskService.save(entity);
		
		Order order =new Order();
		order.setPrice(entity.getBountyPrice());
		order.setCreatedDate(new Date());
		order.setUser(user);
		order.setCount(entity.getBountyCount());
		order.setStatus(0);
		order.setTotal(entity.getBounty());
		order.setTask(entity);
		order.setOrderNum(HttpUtil.getOrderNum());
		
		
		orderService.save(order);
		
		//mv.setViewName("redirect:/task/v/paytask?orderId="+order.getId());
		
		
		attr.addFlashAttribute("js", "<script>alert('提交成功:如果审核通过，客服将尽快和您联系，请保持手机畅通。');</script>");
		//attr.addFlashAttribute("msg", "提交成功:如果审核通过，客服将尽快和您联系，请保持手机畅通。");
		//attr.addFlashAttribute("msg", "发布成功!");
		mv.setViewName("redirect:/task/v/add?type="+entity.getTopType());
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
	 * 任务首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView getIndex() {
		ModelAndView mv = new ModelAndView("task");
		DetachedCriteria criteria = taskService.createDetachedCriteria();
		Page<Task> p = new Page<Task>();
		p.setPageSize(8);
		p.setPageNo(1);
		p.addOrder("createdDate", "desc");
		p = taskService.findPageByCriteria(criteria, p);
		// List<Product> list = productService.findByCriteria(criteria);
		mv.addObject("list", p.getResult());
		return mv;
	}

	@RequestMapping(value = "/sub")
	public ModelAndView sub(HttpServletRequest request) {
		
		
		ModelAndView mv = new ModelAndView("task_sub");

		int p = 1;
		String pstr = request.getParameter("p");
		if (!StringUtils.isEmpty(pstr)) {
			p = Integer.parseInt(pstr);
		}

		List<TaskType> list = taskTypeService.getByParent(null);
		mv.addObject("typeList", list);

		Page<Task> page = new Page<Task>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = taskService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		String tname="";
		
		if (!StringUtils.isEmpty(request.getParameter("ptype"))) {
			TaskType parentTaskType = taskTypeService.get(UUID.fromString(request.getParameter("ptype")));
			criteria.add(Restrictions.eq("parentType", parentTaskType));
			if(parentTaskType!=null)
			{
				tname+=parentTaskType.getName();
			}
			
		}

		if (!StringUtils.isEmpty(request.getParameter("stype"))) {
			TaskType subTaskType = taskTypeService.get(UUID.fromString(request.getParameter("stype")));
			criteria.add(Restrictions.eq("subType", subTaskType));
			if(subTaskType!=null)
			{
				tname+=subTaskType.getName();
			}
			
		}

		page = taskService.findPageByCriteria(criteria, page);

		mv.addObject("name",tname);
		mv.addObject("list", page.getResult());
		mv.addObject("page", HttpUtil.genPageHtml(page, request));

		return mv;
	}
	
	
	
	/**
	 * 搜索
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request) {
		
		
		ModelAndView mv = new ModelAndView("task_search");

		int p = 1;
		String pstr = request.getParameter("p");
		if (!StringUtils.isEmpty(pstr)) {
			p = Integer.parseInt(pstr);
		}

		
		
		String key = request.getParameter("key");
		
		if(StringUtils.isEmpty(key))
		{
			mv.addObject("name","无搜索结果");
			return mv;
		}

		DetachedCriteria criteria = taskService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.like("title", key , MatchMode.ANYWHERE));


		Page<Task> page = new Page<Task>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		page = taskService.findPageByCriteria(criteria, page);

		mv.addObject("name",key+" 搜索结果:");
		mv.addObject("list", page.getResult());
		mv.addObject("page", HttpUtil.genPageHtml(page, request));

		return mv;
	}

	/**
	 * 用户首页首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/index")
	public ModelAndView getUserIndex() {
		ModelAndView mv = new ModelAndView("admin_task_list");
		return mv;
	}

	/**
	 * 任务块
	 * 
	 * @param type
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "/block")
	public ModelAndView getTaskBlock(@RequestParam("type") String type, @RequestParam("count") int count) {
		ModelAndView mv = new ModelAndView("task_block");

		DetachedCriteria criteria = taskService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.not(Restrictions.eq("status", TaskStatus.REVIEW_FAIL)));
		criteria.add(Restrictions.not(Restrictions.eq("status", TaskStatus.END)));
		criteria.add(Restrictions.not(Restrictions.eq("status", TaskStatus.PUBLISH)));
		criteria.add(Restrictions.eq("parentType.id", UUID.fromString(type)));
		
		Page<Task> p = new Page<Task>();
		p.setPageSize(count);
		p.setPageNo(1);
		p.addOrder("createdDate", "desc");

		p = taskService.findPageByCriteria(criteria, p);

		// List<Product> list = productService.findByCriteria(criteria);

		mv.addObject("list", p.getResult());

		return mv;
	}

	/**
	 * 
	 * 任务详细
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView getTaskDetail(@RequestParam("tid") String tid, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("task_detail");

		Task t = taskService.get(UUID.fromString(tid));

		t.setVisit(t.getVisit() + 1);
		taskService.saveOrUpdate(t);

		mv.addObject("entity", t);
		
		if(t.getStatus()==TaskStatus.REVIEW_FAIL)
		{
			mv.setViewName(HttpUtil.getHeaderRef(request));
			attr.addFlashAttribute("js", "<script>alert('审核未通过')</script>");
			return mv;
		}
		
		
		
		//是否可以承接任务
		int canSubmit = 1;
		
		if(
				t.getStatus()!=TaskStatus.REVIEW &&
				t.getStatus()!=TaskStatus.SUBMIT)
		{
			canSubmit = 0;
		}
		
		
		Date d = new Date();
		
		long a = d.getTime();
		long b = t.getEndDate().getTime();
		
		long delta = (a - b)/(60*60*1000);
 
		//开始选稿
		if(delta>48 && t.getStatus() == TaskStatus.SUBMIT)
		{
			t.setStatus(TaskStatus.EVALUATION);
			taskService.saveOrUpdate(t);
			canSubmit = 0;
		}
		
		
		
		
		if(a > b)
		{
			canSubmit = 0;
		}
		
		
		
		mv.addObject("canSubmit",canSubmit);
		
		
		//是否已承接
		int hasSubmit=0;
		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
			Submit submit = submitService.getByTaskUser(user, t);
			if(submit!=null)
			{
				hasSubmit = 1;
			}
		}
		
		
		mv.addObject("hasSubmit",hasSubmit);

		return mv;
	}

	/**
	 * 
	 * 首页调用
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/indextask")
	public ModelAndView indexBlock() {
		ModelAndView mv = new ModelAndView("index_task");

		String[] idList = new String[] { "2ee77ec5-aa19-4fc3-94ca-9a344a32d432", "b3a4c770-347f-47c9-b72b-350ef5252412",
				"e43f9f88-4074-4048-a277-d92aca3084bc", "7d1de774-b21f-4592-9583-53a60439d3df",
				"b57ff88d-ce15-4503-ad15-be4f4196e8e5" };

		List<Article> list = new ArrayList<Article>();
		
		for (int i = 0; i < idList.length; i++) {
			ArticleType subType = articleTypeService.get(UUID.fromString(idList[i]));
			List<Article> list1 = articleService.getTop(null, subType, 1, true);
			if (list1 == null || list1.size() == 0) {
				list.add(new Article());
			} else {
				list.add(list1.get(0));
			}
		}
		
		
		List<Task> list2 = new ArrayList<Task>();
		
		for (Article a : list) {
			if(!StringUtils.isEmpty(a.getRedirectUrl())&&a.getRedirectUrl().indexOf("=")>-1)
			{
				String url = a.getRedirectUrl();
				String taskId = url.split("=")[1];
				
				Task t = taskService.get(UUID.fromString(taskId));
				if(t!=null)
				{
					t.setThumb(a.getThumb());                             
					list2.add(t);
				}
				else
				{
					list2.add(new Task());
				}
				
			}else
			{
				list2.add(new Task());
			}
		}

//		ArticleType subType = articleTypeService.get(UUID.fromString("abb6a6b1-a93d-4b01-9090-83539bbb968e"));
//
//		List<Article> list1 = articleService.getTop(null, subType, 5, true);
//
//		List<Task> list = taskService.getTop(5, true);

		mv.addObject("list", list2);

		return mv;
	}

	/**
	 * 
	 * 任务首页调用
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/taskroll")
	public ModelAndView taskRoll() {
		ModelAndView mv = new ModelAndView("task_index_roll");

		ArticleType subType = articleTypeService.get(UUID.fromString("bda593d7-7dfe-4941-82a5-2d6a87099595"));

		List<Article> list = articleService.getTop(null, subType, 5, true);

		mv.addObject("list", list);

		return mv;
	}
	
 

}