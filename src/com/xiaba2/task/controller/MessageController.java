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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.Member;
import com.xiaba2.cms.service.MemberService;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Message;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.MessageService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Resource
	private MessageService messageService;

	@Resource
	private MemberService memberService;

	@Resource
	private UserService userService;

	/**
	 * 发消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_message_add");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		String sendTo = request.getParameter("sendTo");
		if(!StringUtils.isEmpty(sendTo))
		{
			mv.addObject("sendTo", sendTo);
		}

		mv.addObject("user", user);
		return mv;
	}

	/**
	 * 收件
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/v/inbox")
	public ModelAndView inbox(@RequestParam("p") int p,HttpServletRequest request) {

		p = Math.max(p, 1);

		ModelAndView mv = new ModelAndView("user_message_inbox");

		User sendTo = SessionUtil.getInstance().getSessionUser();
		if (sendTo == null) {
			return mv;
		}

		sendTo = userService.get(sendTo.getId());

		
		
		
		
		
		DetachedCriteria criteria = messageService.createDetachedCriteria();
		criteria.add(Restrictions.eq("sendTo", sendTo));
		criteria.add(Restrictions.eq("isDelete", 0));
		
		
		
		
		Page<Message> page = new Page<Message>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
 

		// List<Article> list = articleService.findByCriteria(criteria);

		page = messageService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		mv.addObject("pageHtml", page.genPageHtml(request));
 

		return mv;
	}

	/**
	 * 发件
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/v/outbox")
	public ModelAndView outbox(@RequestParam("p") int p,HttpServletRequest request) {

		p = Math.max(p, 1);
		
		ModelAndView mv = new ModelAndView("user_message_outbox");

		User from = SessionUtil.getInstance().getSessionUser();
		from = userService.get(from.getId());

		if (from == null) {
			return mv;
		}

		DetachedCriteria criteria = messageService.createDetachedCriteria();
		criteria.add(Restrictions.eq("from", from));
		criteria.add(Restrictions.eq("isDelete", 0));
		

		Page<Message> page = new Page<Message>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
 

		// List<Article> list = articleService.findByCriteria(criteria);

		page = messageService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		mv.addObject("pageHtml", page.genPageHtml(request));
 

		return mv;

	}

	@RequestMapping(value = "/action/add")
	public ModelAndView actionAdd(Message entity, HttpServletRequest request, RedirectAttributes attr) {

		ModelAndView mv = new ModelAndView("redirect:/message/v/add");

		String sendto = request.getParameter("sendto");
		// Member member = memberService.getByUsername(sendto);
		User sendtoUser = userService.getByUsername(sendto);
		if(sendtoUser==null)
		{
			attr.addFlashAttribute("js", "<script>alert('收件人不存在!')</script>");
			return mv;
		}
		
		
		User from = SessionUtil.getInstance().getSessionUser();
		

		if (from == null) {

			attr.addFlashAttribute("js", "<script>alert('请先登录!')</script>");
			return mv;
		}else
		{
			from = userService.get(from.getId());
		}

		entity.setFrom(from);
		entity.setSendTo(sendtoUser);
		entity.setCreatedDate(new Date());
		entity.setCreatedBy(from.getUsername());

		messageService.save(entity);

		attr.addFlashAttribute("js", "<script>alert('发送成功!')</script>");
		return mv;

	}

	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID id,HttpServletRequest request) {

		Message entity = messageService.get(id);
		entity.setIsDelete(1);
		
		messageService.saveOrUpdate(entity);
		//messageService.delete(entity);

		ModelAndView mv = new ModelAndView(HttpUtil.getHeaderRef(request));
		
		return mv;

	}

}