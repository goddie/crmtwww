package com.xiaba2.task.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.domain.Work;
import com.xiaba2.task.gen.EnumSet.CheckStatus;
import com.xiaba2.task.service.UserService;
import com.xiaba2.task.service.WorkService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@Resource
	private WorkService workService;


	@RequestMapping(value = "/usernav")
	public ModelAndView getUserNav(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_nav");
		User user = SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			mv.addObject("user", user);
		}
		return mv;
	}

	/**
	 * 个人页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/usersite")
	public ModelAndView getUserSite(@RequestParam(value = "uuid", required = false) String uuid,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_site");

		UUID uid = null;

		if (!StringUtils.isEmpty(uuid)) {
			uid = UUID.fromString(uuid);
		} else {

			User user = (User) SessionUtil.getInstance().getSessionUser();
			if (user != null) {
				uid = user.getId();
			}
		}

		if (uid == null) {
			return new ModelAndView("redirect:/site/home");
		}

		User siteUser = userService.get(uid);
		siteUser.setVisits(siteUser.getVisits() + 1);
		userService.saveOrUpdate(siteUser);

		mv.addObject("user", siteUser);
		
		
		
		
		List<Work> workList = workService.getUserWork(siteUser);
		mv.addObject("worklist", workList);

		return mv;
	}

	@RequestMapping(value = "/action/logout")
	public ModelAndView getActionLogout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("redirect:/webpage/index");
		WebUtils.setSessionAttribute(request, "user", null);
		SessionUtil.getInstance().saveUserCookie(null);
		SessionUtil.getInstance().saveMemberCookie(null);
		return mv;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID uuid) {
		ModelAndView mv = new ModelAndView("redirect:/user/admin/list?p=1");
		User entity = userService.get(uuid);
		entity.setIsDelete(1);
		userService.saveOrUpdate(entity);
		return mv;
	}
	

	@RequestMapping(value = "/v/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("ucenter_index");

		return mv;
	}

	/**
	 * 个人资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "/v/userinfo")
	public ModelAndView userinfo() {
		ModelAndView mv = new ModelAndView("ucenter_userinfo");

		
		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		mv.addObject("user", user);
		
		return mv;
	}

	/**
	 * 头像页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/avatar")
	public ModelAndView avatar(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_avatar");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		return mv;
	}
	
	
	
	/**
	 * 充值记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/rechargelist")
	public ModelAndView rechargeList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_recharge_list");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 提现
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/cash")
	public ModelAndView cash(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_cash");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 提现记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/cashlist")
	public ModelAndView cashList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_cash_list");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 交易记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/payrecord")
	public ModelAndView tradeList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_payrecord");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		return mv;
	}

	/**
	 * 登录密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/password")
	public ModelAndView password(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_password");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		return mv;
	}

	/**
	 * 实名认证
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/checkperson")
	public ModelAndView checkPerson(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_checkperson");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		
		
		if(user.getIsCheckPerson()==CheckStatus.SUCCESS || user.getIsCheckPerson()==CheckStatus.WAIT)
		{
			ModelAndView mv2 = new  ModelAndView("ucenter_checkperson_done");
			mv2.addObject("status", user.getIsCheckPerson());
			return mv2;
		}
		
		return mv;
	}

	/**
	 * 支付密码密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/paypassword")
	public ModelAndView payPassword(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ucenter_paypassword");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		mv.addObject("user", user);
		return mv;
	}
	
	


	@RequestMapping(value = "/action/password")
	public ModelAndView actionPassword(RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/user/v/password");

		String oldp = request.getParameter("oldpassword");
		String newp = request.getParameter("newpassword");

		if (StringUtils.isEmpty(newp)) {
			return mv;
		}

		if (StringUtils.isEmpty(oldp)) {
			return mv;
		}

		User user = (User) SessionUtil.getInstance().getSessionUser();

		if (user == null) {
			return mv;
		}

		user = userService.get(user.getId());

		if (!user.getPassword().equals(oldp)) {
			attr.addFlashAttribute("msg", "旧密码不正确!");
			return mv;
		}

		user = userService.get(user.getId());
		user.setPassword(newp);

		userService.saveOrUpdate(user);

		attr.addFlashAttribute("msg", "修改成功!");
		return mv;
	}

	/**
	 * 实名认证
	 * 
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/checkperson")
	public ModelAndView actionCheckPerson(RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/user/v/checkperson");

		String realname = request.getParameter("realname");
		String licence = request.getParameter("licence");
		String thumb = request.getParameter("thumb");

		if (StringUtils.isEmpty(realname)) {
			return mv;
		}

		if (StringUtils.isEmpty(licence)) {
			return mv;
		}

		if (StringUtils.isEmpty(thumb)) {
			attr.addFlashAttribute("msg", "请上传证明图片");
			return mv;
		}

		User user = (User) SessionUtil.getInstance().getSessionUser();

		if (user == null) {
			return mv;
		}

		user = userService.get(user.getId());

		user.setRealname(realname);
		user.setLicence(licence);
		user.setLicenceImage(thumb);
		user.setIsCheckPerson(CheckStatus.WAIT);

		userService.saveOrUpdate(user);

		attr.addFlashAttribute("js", "<script>alert('提交成功，等待审核!')</script>");
		return mv;
	}
	
	
	/**
	 * 实名认证
	 * 
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/review")
	public ModelAndView actionReview(@RequestParam("id") UUID id, RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/user/admin/review?type=1&p=1");

		User user = userService.get(id);
		
		String rs = request.getParameter("rs");
		String type = request.getParameter("type");
		
		if(type.equals("1"))
		{
			if(rs.equals("1"))
			{
				user.setIsCheckPerson(CheckStatus.SUCCESS);
			}
			
			if(rs.equals("10"))
			{
				user.setIsCheckPerson(CheckStatus.FAIL);
			}
		}
		
		if(type.equals("2"))
		{
			if(rs.equals("1"))
			{
				user.setIsCheckCompany(CheckStatus.SUCCESS);
			}
			
			if(rs.equals("10"))
			{
				user.setIsCheckCompany(CheckStatus.FAIL);
			}
		}
		
		

		userService.saveOrUpdate(user);

		
		return mv;
	}
	

	/**
	 * 修改头像
	 * 
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/avatar")
	public ModelAndView actionAvatar(RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/user/v/avatar");

		String thumb = request.getParameter("thumb");

		if (StringUtils.isEmpty(thumb)) {
			return mv;
		}

		User user = (User) SessionUtil.getInstance().getSessionUser();

		if (user == null) {
			return mv;
		}

		user = userService.get(user.getId());
		user.setHead(thumb);

		userService.saveOrUpdate(user);

		attr.addFlashAttribute("msg", "修改成功!");
		return mv;
	}

	/**
	 * 编辑个人资料
	 * 
	 * @param attr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/update")
	public ModelAndView actionUpdate(User entity, RedirectAttributes attr, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("redirect:/user/v/userinfo");
		User user = SessionUtil.getInstance().getSessionUser();
		if (user == null) {
			return mv;
		}
		
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date d;
		try {
			d = sdf.parse(request.getParameter("birthdayStr"));
			entity.setBirthday(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		entity.setBusiness(request.getParameter("parentTypeId"));

		
		user.setNickname(entity.getNickname());
		user.setSex(entity.getSex());
		user.setBirthday(entity.getBirthday());
		user.setBusiness(entity.getBusiness());
		user.setPhone(entity.getPhone());
		user.setEmail(entity.getEmail());
		user.setQQ(entity.getQQ());
		user.setProvince(entity.getProvince());
		user.setCity(entity.getCity());
		user.setDistrict(entity.getDistrict());
		user.setAddress(entity.getAddress());
		user.setIntroduce(entity.getIntroduce());
		
		userService.saveOrUpdate(user);

		attr.addFlashAttribute("msg", "修改成功!");
		
		return mv;
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/login")
	public ModelAndView actionLogin(User entity, RedirectAttributes attr, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("username", entity.getUsername()));
		criteria.add(Restrictions.eq("password", entity.getPassword()));
		List<User> list = userService.findByCriteria(criteria);
		if (!list.isEmpty()) {
			entity = list.get(0);
			WebUtils.setSessionAttribute(request, "user", list.get(0));

			// save
			SessionUtil.getInstance().saveUserCookie(entity);

			String s = request.getParameter("redirect");
			if(!StringUtils.isEmpty(s))
			{
				mv.setViewName("redirect:"+s);
			}else
			{
				mv.setViewName("redirect:/user/v/index");
			}
			
			//attr.addFlashAttribute("msg", "<script>alert('登录成功!')</script>");
			
			//mv.setViewName("redirect:/user/v/index");
		} else {
			attr.addFlashAttribute("msg", "<script>alert('账号密码不正确!')</script>");
			mv.setViewName("redirect:/webpage/login");
			
			
		}
		return mv;
	}

	/**
	 * 注册
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/action/reg")
	public ModelAndView actionReg(User entity, RedirectAttributes attr, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("redirect:/webpage/login");

		if (StringUtils.isEmpty(entity.getUsername()) || StringUtils.isEmpty(entity.getPassword())) {
			// throw new RuntimeException("用户名或密码为空。");
			attr.addFlashAttribute("msg", "<script>alert('用户名或密码为空。')</script>");
			mv.setViewName("redirect:/webpage/reg");
			return mv;
		}

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("username", entity.getUsername().trim()));

		List<User> list = userService.findByCriteria(criteria);

		if (!list.isEmpty()) {
			mv.setViewName("redirect:/webpage/reg");
			attr.addFlashAttribute("msg", "<script>alert('用户名已被注册。')</script>");
			// throw new RuntimeException("用户名已被注册。");
			return mv;
		}
		entity.setCreatedDate(new Date());
		entity.setNickname(entity.getUsername());
		entity.setRegIp(HttpUtil.getIpAddr(request));
		entity.setRegTime(new Date());
		// entity.setIsCheckPerson(1);

		userService.save(entity);

		attr.addFlashAttribute("msg", "<script>alert('注册成功！')</script>");
		return mv;
	}

	/**
	 * 任务块
	 * 
	 * @param type
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "/personblock")
	public ModelAndView getTaskUserBlock(@RequestParam("count") int count) {
		ModelAndView mv = new ModelAndView("task_block_person");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("isCheckPerson", 1));
		criteria.add(Restrictions.isNotNull("head"));

		Page<User> p = new Page<User>();
		p.setPageSize(count);
		p.setPageNo(1);
		p.addOrder("createdDate", "desc");

		p = userService.findPageByCriteria(criteria, p);

		List<User> list = p.getResult();
		for (User user : list) {
			String img = user.getHead();
			if (!StringUtils.isEmpty(img)) {
				img = img.replace("_200x200", "_240x180");
				user.setHead(img);
			}
		}

		// List<Product> list = productService.findByCriteria(criteria);

		mv.addObject("list", p.getResult());

		return mv;
	}
	
	
	/**列表
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_user_list");

		Page<User> page = new Page<User>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		
		int type = 0;
		if(!StringUtils.isEmpty(request.getParameter("type")))
		{
			type = Integer.parseInt(request.getParameter("type"));
			if(type==1)
			{
				criteria.add(Restrictions.eq("isCheckPerson", CheckStatus.SUCCESS));
			}
			
			if(type==2)
			{
				criteria.add(Restrictions.eq("isCheckCompany", CheckStatus.SUCCESS));
			}
			
		}
		
		// List<Article> list = articleService.findByCriteria(criteria);

		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		mv.addObject("pageHtml", page.genPageHtml(request));
		return mv;
	}

	
	
	/**
	 * 
	 * 审核实名认证
	 * @param p
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/review")
	public ModelAndView reviewList(@RequestParam("p") int p,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_user_review");

		Page<User> page = new Page<User>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = userService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		
		int type = 0;
		if(!StringUtils.isEmpty(request.getParameter("type")))
		{
			type = Integer.parseInt(request.getParameter("type"));
			
			mv.addObject("type", type);
			
			if(type==1)
			{
				criteria.add(Restrictions.eq("isCheckPerson", CheckStatus.WAIT));
			}
			
			if(type==2)
			{
				criteria.add(Restrictions.eq("isCheckCompany", CheckStatus.WAIT));
			}
			
		}
		
		// List<Article> list = articleService.findByCriteria(criteria);

		page = userService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		mv.addObject("pageHtml", page.genPageHtml(request));
		
		
		return mv;
	}
}