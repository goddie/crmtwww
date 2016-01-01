package com.xiaba2.task.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Company;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.gen.EnumSet.CheckStatus;
import com.xiaba2.task.service.CompanyService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Resource
	private CompanyService companyService;

	@Resource
	private UserService userService;

	/**
	 * 企业认证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/checkcompany")
	public ModelAndView checkCompany(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_checkcompany");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		
		if(user.getIsCheckCompany()==CheckStatus.SUCCESS || user.getIsCheckCompany()==CheckStatus.WAIT)
		{
			
			ModelAndView mv2 = new  ModelAndView("ucenter_checkcompany_done");
			mv2.addObject("status", user.getIsCheckCompany());
			return mv2;
		}
		
		
		return mv;
	}

	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(Company entity, RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/company/v/checkcompany");

		String thumb = request.getParameter("thumb");

		if (StringUtils.isEmpty(thumb)) {
			attr.addFlashAttribute("msg", "请上传证明图片");
			return mv;
		}

		User user = (User) SessionUtil.getInstance().getSessionUser();

		if (user == null) {
			return mv;
		}
		user = userService.get(user.getId());

		entity.setUser(user);
		entity.setCreatedDate(new Date());
		companyService.save(entity);

		user.setIsCheckCompany(CheckStatus.WAIT);
		userService.saveOrUpdate(user);

		attr.addFlashAttribute("js", "<script>alert('提交成功,等待审核!')</script>");

		return mv;
	}

	/**
	 * 任务块
	 * 
	 * @param type
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "/companyblock")
	public ModelAndView getTaskCompanyBlock(@RequestParam("count") int count) {
		ModelAndView mv = new ModelAndView("task_block_company");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("isCheckCompany", CheckStatus.SUCCESS));
		criteria.add(Restrictions.isNotNull("head"));

		Page<User> p = new Page<User>();
		p.setPageSize(count);
		p.setPageNo(1);

		p.addOrder("createdDate", "desc");

		p = userService.findPageByCriteria(criteria, p);

		List<CompanyUser> list2 = new ArrayList<CompanyUser>();

		List<User> list = p.getResult();
		for (User user : list) {
			String img = user.getHead();

			if (!StringUtils.isEmpty(img)) {
				img = img.replace("_200x200", "_240x180");
				user.setHead(img);
			}

			CompanyUser cu = new CompanyUser(companyService.getByUser(user), user);
			list2.add(cu);
		}

		// List<Product> list = productService.findByCriteria(criteria);

		mv.addObject("list", list2);

		return mv;
	}

	public class CompanyUser {
		public CompanyUser(Company c, User u) {
			this.company = c;
			this.user = u;
		}

		private Company company;
		private User user;

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

	}
}