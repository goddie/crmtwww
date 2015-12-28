package com.xiaba2.task.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.xiaba2.core.JsonResult;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Collect;
import com.xiaba2.task.domain.Order;
import com.xiaba2.task.domain.Product;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.CollectService;
import com.xiaba2.task.service.ProductService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@RestController
@RequestMapping("/collect")
public class CollectController {
	@Resource
	private CollectService collectService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ProductService productService;
	
	@RequestMapping(value = "/json/add")
	public JsonResult jsonAdd(HttpServletRequest request) {
		JsonResult rs = new JsonResult();
		
		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}else
		{
			rs.setMsg("请先登录");
			return rs;
		}
		
		
		String productId = request.getParameter("productId");
		if(StringUtils.isEmpty("productId"))
		{
			rs.setMsg("找不到商品");
			return rs;
		}
		
		
		Product product = productService.get(UUID.fromString(productId));
		
		Collect entity = new Collect();
		entity.setCreatedDate(new Date());
		entity.setProduct(product);
		entity.setUser(user);
		
		collectService.save(entity);
		
		product.setCollectCount(product.getCollectCount()+1);
		productService.saveOrUpdate(product);
		
		rs.setCode(JsonResult.SUCCESS);
		rs.setMsg("收藏成功");
		return rs;
	}
	
	
	
	/**
	 * 用户管理页面
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v/list")
	public ModelAndView getUserList(@RequestParam("p") int p, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_collect_list");

		 
		DetachedCriteria criteria = collectService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		User user = SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}

		criteria.add(Restrictions.eq("user", user));
 

		Page<Collect> page = new Page<Collect>();
		page.setPageNo(p);
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.addOrder("createdDate", "desc");

		page = collectService.findPageByCriteria(criteria, page);


		List<Collect> list = page.getResult();

/*		List<Product> list2 = new ArrayList<Product>();
		for (Collect pt : list) {
			list2.add(pt.getProduct());
		}*/

		// DetachedCriteria criteria2 = productService.createDetachedCriteria();

		mv.addObject("list", list);
		mv.addObject("topName", "我收藏的");
		
		return mv;
	}
	
	
	@RequestMapping(value = "/action/del")
	public ModelAndView del(@RequestParam("id") UUID id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/collect/v/list?p=1");
		
		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
//		Product product = productService.get(id);
		
		DetachedCriteria criteria = collectService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("id", id));
		
		
		
		List<Collect> list = collectService.findByCriteria(criteria);
		if(list!=null&&list.size()>0)
		{
			list.get(0).setIsDelete(1);
			collectService.saveOrUpdate(list.get(0));
		}
		
 
		return mv;
	}
	
}