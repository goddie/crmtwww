package com.xiaba2.task.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaba2.task.domain.Forum;
import com.xiaba2.task.domain.ForumReply;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.ForumReplyService;
import com.xiaba2.task.service.ForumService;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/forumreply")
public class ForumReplyController {
	@Resource
	private ForumReplyService forumReplyService;
	
	
	@Resource
	private ForumService forumService;
	
	/**
	 * 发新帖
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView add(ForumReply entity,@RequestParam("forumId") UUID fid, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/forum/detail?id="+fid.toString());
		
		
		User user = SessionUtil.getInstance().getSessionUser();
		if( user==null)
		{
			attr.addFlashAttribute("msg", "<script>alert('请登录再发帖')</script>");
			return mv;
		}else
		{
			entity.setUser(user);
		}
 
		
		Forum forum = forumService.get(fid); 
		
		if(forum==null)
		{
			return mv;
		}
		
		entity.setForum(forum);
		entity.setCreatedDate(new Date());
		entity.setType(forum.getType()); 
		entity.setSubType(forum.getSubType());
		
 
		forumReplyService.save(entity);

		attr.addFlashAttribute("msg", "<script>alert('回复成功')</script>");
		
		return mv;
	}
}