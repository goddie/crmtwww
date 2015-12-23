package com.xiaba2.task.controller;

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
import com.xiaba2.task.domain.PayRecord;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.PayRecordService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/payrecord")
public class PayRecordController {
	@Resource
	private PayRecordService payRecordService;
	
	@Resource
	private UserService userService;
	
	/**
	 * 用户管理页面
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v/list")
	public ModelAndView getUserList(@RequestParam("p") int p, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_payrecord_list");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		

		DetachedCriteria criteria = payRecordService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.or(Restrictions.eq("user", user),Restrictions.eq("toUser", user)));
		
		Page<PayRecord> page = new Page<PayRecord>();
		page.setPageNo(p);
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.addOrder("createdDate", "desc");

		page = payRecordService.findPageByCriteria(criteria, page);

		List<PayRecord> list = page.getResult();

		mv.addObject("list", list);
		mv.addObject("p", page.genPageHtml(request));
		return mv;
	}
	
	
}