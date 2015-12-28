package com.xiaba2.cms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.parser.JSONParser;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping({ "/member" })
public class MemberController {
	@Resource
	private MemberService memberService;

	@Resource
	private UserService userService;



//	/**
//	 * 登录页面
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/page/{name}")
//	public String getPage(@PathVariable String name) {
//		return "member_" + name;
//	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView adminLogin() {
		ModelAndView mv = new ModelAndView("admin_login");

		return mv;
	}
	
	
	/**
	 * 新增管理员
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView adminAdd() {
		ModelAndView mv = new ModelAndView("admin_add");
		return mv;
	}
	
	
	
	/**
	 * 注册
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView actrionAdd(Member entity,RedirectAttributes attr,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		//entity.setEmail(entity.getUsername());
		entity.setRegIp(HttpUtil.getIpAddr(request));
		entity.setRegTime(new Date());
		memberService.save(entity);
		attr.addFlashAttribute("msg", "注册成功");
		mv.setViewName("redirect:/member/login");
		return mv;
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reg")
	public ModelAndView adminReg() {
		ModelAndView mv = new ModelAndView("admin_reg");

		return mv;
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView adminIndex() {
		ModelAndView mv = new ModelAndView("admin_index");
		return mv;
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/login")
	public ModelAndView login(Member entity, RedirectAttributes attr, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		DetachedCriteria criteria = memberService.createDetachedCriteria();

		criteria.add(Restrictions.eq("username", entity.getUsername()));
		criteria.add(Restrictions.eq("password", entity.getPassword()));

		List<Member> list = memberService.findByCriteria(criteria);

		if (!list.isEmpty()) {
			// attr.addAttribute("loginMember", list.get(0).getId().toString());

			// DetachedCriteria criteria2 =
			// userService.createDetachedCriteria();
			// criteria2.add(Restrictions.eq("member", list.get(0)));
			//
			// List<User> list2 = userService.findByCriteria(criteria2);
			entity = list.get(0);
			WebUtils.setSessionAttribute(request, "member", list.get(0));
			// WebUtils.setSessionAttribute(request, "user", list2.get(0));

			// session.setAttribute("member", list.get(0));
			// session.setAttribute("user", list2.get(0));

			// save
			SessionUtil.getInstance().saveMemberCookie(entity);

			attr.addFlashAttribute("msg", "登录成功!");
			mv.setViewName("redirect:/member/index");
		} else {
			attr.addFlashAttribute("msg", "账号不存在!");
			mv.setViewName("redirect:/member/login");
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
	public ModelAndView reg(Member entity, RedirectAttributes attr, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		entity.setEmail(entity.getUsername());
		entity.setRegIp(HttpUtil.getIpAddr(request));
		entity.setRegTime(new Date());
		memberService.save(entity);
		attr.addFlashAttribute("msg", "帐号重复存在!");
		mv.setViewName("redirect:/member/login");
		return mv;
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(RedirectAttributes attr) {

		List<Member> list = memberService.loadAll();

		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.addObject("loginMember", attr);
		mv.setViewName("member_list");

		return mv;
	}



}
