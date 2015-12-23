package com.xiaba2.task.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Cash;
import com.xiaba2.task.domain.Recharge;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.CashService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/cash")
public class CashController {
	@Resource
	private CashService cashService;
	
	@Resource
	private UserService userService;
	
	
	/**
	 * 充值
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/add")
	public ModelAndView recharge(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_cash_add");

		User user = (User) SessionUtil.getInstance().getSessionUser();
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
		ModelAndView mv = new ModelAndView("redirect:/cash/v/add");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		Cash entity =new Cash();
		entity.setUser(user);
		entity.setCreatedDate(new Date());
		entity.setTotal(money);
//		entity.setChargeType(chargeType);
		
		cashService.save(entity);
		

		user.setMoney(user.getMoney() - money);
		userService.saveOrUpdate(user);

		mv.addObject("user", user);

 
		
 

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
		ModelAndView mv = new ModelAndView("user_cash_list");
 
 

		DetachedCriteria criteria = cashService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		criteria.add(Restrictions.eq("user", user));

		Page<Cash> page = new Page<Cash>();
		page.setPageNo(p);
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.addOrder("createdDate", "desc");

		page = cashService.findPageByCriteria(criteria, page);

		List<Cash> list = page.getResult();

		mv.addObject("list", list);
		mv.addObject("p", page.genPageHtml(request));
		return mv;
	}
	
	
}