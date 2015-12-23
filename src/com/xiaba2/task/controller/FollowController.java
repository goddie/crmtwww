package com.xiaba2.task.controller;

import java.util.Date;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.xiaba2.core.JsonResult;
import com.xiaba2.task.domain.Follow;

import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.FollowService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.SessionUtil;

@RestController
@RequestMapping("/follow")
public class FollowController {
	@Resource
	private FollowService followService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(@RequestParam("userId") UUID userId, HttpServletRequest request) {

		JsonResult rs = new JsonResult();
 
		
		
		User user = SessionUtil.getInstance().getSessionUser();
		

		if (user == null) {
			rs.setMsg("请先登录");
			return rs;
		}else
		{
			user = userService.get(user.getId());
		}
		
		
		User guest = userService.get(userId);
		
		Follow entity = new Follow();
		entity.setHost(user);
		entity.setGuest(guest);
		entity.setCreatedDate(new Date());
		entity.setCreatedBy(user.getUsername());

		followService.save(entity);
		
		
		guest.setFollowCount(guest.getFollowCount()+1);
		userService.saveOrUpdate(guest);

		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("关注成功!");
		return rs;

	}
}