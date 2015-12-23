package com.xiaba2.task.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Order;
import com.xiaba2.task.domain.Recharge;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.OrderService;
import com.xiaba2.task.service.RechargeService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/recharge")
public class RechargeController {
	@Resource
	private RechargeService rechargeService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private OrderService orderService;

	/**
	 * 充值
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/add")
	public ModelAndView recharge(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_recharge_add");

		User user = (User)SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		String toPay = request.getParameter("toPay");
		if (!StringUtils.isEmpty(toPay)) {
			mv.addObject("toPay", (int) Float.parseFloat(toPay));
		}

		String orderId = request.getParameter("orderId");
		if (!StringUtils.isEmpty(orderId)) {
			mv.addObject("orderId", orderId);
			mv.addObject("msg", "您账户余额不足，请先充值。");
			// mv.setViewName("redirect:/task/v/paytask?orderId="+orderId);
			// return mv;
		}

		mv.addObject("user", user);

		return mv;
	}

	/**
	 * 充值
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actionRecharge(@RequestParam("money") int money, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/recharge/v/add");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		Recharge entity =new Recharge();
		entity.setUser(user);
		entity.setCreatedDate(new Date());
		entity.setTotal(money);
//		entity.setChargeType(chargeType);
		
		rechargeService.save(entity);
		

		user.setMoney(user.getMoney() + money);
		userService.saveOrUpdate(user);

		mv.addObject("user", user);

		String orderId = request.getParameter("orderId");
		
		//String taskId = request.getParameter("taskId");
		
		//String productId = request.getParameter("productId");
		
		if (!StringUtils.isEmpty(orderId)) {
			
			Order order = orderService.get(UUID.fromString(orderId));
			if(order.getProduct()!=null)
			{
				//mv.addObject("productId", order.getProduct().getId());
				 
				// attr.addFlashAttribute("msg", "成功充值"+entity.getTotal());
				mv.setViewName("redirect:/product/v/payproduct?productId=" + order.getProduct().getId());
				
				return mv;
			}
			
			if(order.getTask()!=null)
			{
				//mv.addObject("taskId", order.getTask().getId());
				mv.setViewName("redirect:/task/v/paytask?orderId=" + orderId);
				return mv;
			}
			

		}
		

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
		ModelAndView mv = new ModelAndView("user_recharge_list");
 
 

		DetachedCriteria criteria = rechargeService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		criteria.add(Restrictions.eq("user", user));

		Page<Recharge> page = new Page<Recharge>();
		page.setPageNo(p);
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.addOrder("createdDate", "desc");

		page = rechargeService.findPageByCriteria(criteria, page);

		List<Recharge> list = page.getResult();

		mv.addObject("list", list);
		mv.addObject("p", page.genPageHtml(request));
		return mv;
	}
	
	
	
}