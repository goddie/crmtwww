package com.xiaba2.task.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.xiaba2.task.domain.Order;
import com.xiaba2.task.domain.Product;
import com.xiaba2.task.gen.EnumSet.OrderStatus;
import com.xiaba2.task.service.OrderService;
import com.xiaba2.task.service.ProductService;
import com.xiaba2.util.HttpUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderService orderService;
	
	@Resource
	private ProductService productService;

	/**
	 * 已付款
	 * 
	 * @param uuid
	 * @param rs
	 * @return
	 */
	@RequestMapping(value = "/action/ispay")
	public ModelAndView isPay(@RequestParam("id") UUID uuid, @RequestParam("rs") int rs, 
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Order entity = orderService.get(uuid);

		entity.setStatus(rs);
		
		if(rs == OrderStatus.HAS_PAY)
		{
			entity.setIsPay(1);
			
			
			
			
		}
		
		orderService.saveOrUpdate(entity);

		
		//String referer = request.getHeader("Referer");
	    
		
		
		mv.setViewName(HttpUtil.getHeaderRef(request));

		return mv;
	}
	
	
	/**
	 * 已付款
	 * 
	 * @param uuid
	 * @param rs
	 * @return
	 */
	@RequestMapping(value = "/action/changestatus")
	public ModelAndView changeStatus(@RequestParam("id") UUID uuid, @RequestParam("rs") int rs, 
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Order entity = orderService.get(uuid);


		entity.setStatus(rs);
		
		if(rs == OrderStatus.HAS_PAY)
		{
			entity.setIsPay(1);
			
			Product p = entity.getProduct();
			if(p!=null)
			{
				p.setTradeCount(p.getTradeCount()+1);
				productService.saveOrUpdate(p);
			}
			
		}
		
		orderService.saveOrUpdate(entity);

		
		String referer = request.getHeader("Referer");
	    
		mv.setViewName("redirect:"+ referer);

		return mv;
	}

	/**
	 * 取消订单
	 * 
	 * @param uuid
	 * @param rs
	 * @return
	 */
	@RequestMapping(value = "/action/iscancel")
	public ModelAndView isCancel(@RequestParam("id") UUID uuid, @RequestParam("rs") int rs,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Order entity = orderService.get(uuid);

		if (rs == 1) {
			entity.setStatus(OrderStatus.CANCEL);
		}

		orderService.saveOrUpdate(entity);

//		mv.setViewName("redirect:" + HttpUtil.getReferView(request));
		//String referer = request.getHeader("Referer");
	    
		mv.setViewName(HttpUtil.getHeaderRef(request));
		
		return mv;
	}
	
	
	/**
	 * 删除
	 * 
	 * @param uuid
	 * @param rs
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView del(@RequestParam("id") UUID uuid,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Order entity = orderService.get(uuid);
		entity.setIsDelete(1);
		orderService.saveOrUpdate(entity);

//		mv.setViewName("redirect:" + HttpUtil.getReferView(request));
		String referer = request.getHeader("Referer");
	    
		mv.setViewName("redirect:"+ referer);
		
		return mv;
	}
}