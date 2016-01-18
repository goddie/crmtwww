package com.xiaba2.task.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Message;
import com.xiaba2.task.domain.Order;
import com.xiaba2.task.domain.PayRecord;
import com.xiaba2.task.domain.Product;
 
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.TaskType;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.gen.EnumSet.OrderStatus;
import com.xiaba2.task.gen.EnumSet.ProductStatus;
import com.xiaba2.task.gen.EnumSet.TaskStatus;
import com.xiaba2.task.service.OrderService;
import com.xiaba2.task.service.PayRecordService;
import com.xiaba2.task.service.ProductService;
 
import com.xiaba2.task.service.TaskTypeService;
import com.xiaba2.task.service.UserService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.ListUtil;
import com.xiaba2.util.ListUtil.DataClass;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Resource
	private ProductService productService;

	@Resource
	private TaskTypeService taskTypeService;

 

	@Resource
	private UserService userService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private PayRecordService payRecordService;

	/**
	 * 商城首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView getIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("product");
		
		List<TaskType> list = taskTypeService.getByParent(null);
		mv.addObject("typeList",list);
		
		
		
		int p = 1;
		String pstr = request.getParameter("p");
		if(!StringUtils.isEmpty(pstr))
		{
			p = Integer.parseInt(pstr);
		}
		
		
		
		Page<Product> page = new Page<Product>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = productService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("status", ProductStatus.REVIEW));
		criteria.add(Restrictions.eq("isOnSale", 1));

		if(!StringUtils.isEmpty(request.getParameter("ptype")))
		{
			TaskType parentTaskType = taskTypeService.get(UUID.fromString(request.getParameter("ptype")));
			criteria.add(Restrictions.eq("parentType", parentTaskType));
		}
		
		if(!StringUtils.isEmpty(request.getParameter("stype")))
		{
			TaskType subTaskType = taskTypeService.get(UUID.fromString(request.getParameter("stype")));
			criteria.add(Restrictions.eq("subType", subTaskType));
		}


		page = productService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		mv.addObject("page", HttpUtil.genPageHtml(page, request));
		

		return mv;
	}

	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID pid) {
		ModelAndView mv = new ModelAndView("redirect:/product/v/list?p=1");
		Product entity = productService.get(pid);
		entity.setIsDelete(1);
		productService.saveOrUpdate(entity);
		return mv;
	}

	/**
	 * 
	 * 商品详细
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam("pid") String pid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("product_detail");

		Product p = productService.get(UUID.fromString(pid));
		
		
		
		
		mv.addObject("entity", p);
		
		p.setVisitCount(p.getVisitCount()+1);
		productService.saveOrUpdate(p);

		return mv;
	}
	
	
	
	/**
	 * 搜索
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request) {
		
		
		ModelAndView mv = new ModelAndView("product_search");

		int p = 1;
		String pstr = request.getParameter("p");
		if (!StringUtils.isEmpty(pstr)) {
			p = Integer.parseInt(pstr);
		}

		
		
		String key = request.getParameter("key");
		
		if(StringUtils.isEmpty(key))
		{
			mv.addObject("name","无搜索结果");
			return mv;
		}

		DetachedCriteria criteria = productService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.not(Restrictions.eq("status", ProductStatus.PUBLISH)));
		criteria.add(Restrictions.not(Restrictions.eq("status", ProductStatus.REVIEW_FAIL)));
		 
		criteria.add(Restrictions.like("name", key , MatchMode.ANYWHERE));


		Page<Product> page = new Page<Product>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		page = productService.findPageByCriteria(criteria, page);

		mv.addObject("name",key+" 搜索结果:");
		mv.addObject("list", page.getResult());
		mv.addObject("page", HttpUtil.genPageHtml(page, request));

		return mv;
	}

	/**
	 * 添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/add")
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_product_add");

		return mv;
	}
	
	
	/**
	 * 添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/edit")
	public ModelAndView edit(@RequestParam("id") UUID id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_product_edit");
		
		Product product = productService.get(id);
		
		mv.addObject("entity", product);
		
		return mv;
	}

	/**
	 * 新增商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView add(Product entity, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/product/v/add");

		if (StringUtils.isEmpty(entity.getName())) {
			attr.addFlashAttribute("js", "<script>alert('请输入商品名称.')</script>");
			return mv;
		}

		User user = SessionUtil.getInstance().getSessionUser();

		if (user != null) {
			user = userService.get(user.getId());
			entity.setUser(user);
		}

		String pid = request.getParameter("parentTypeId");
		TaskType ptype = taskTypeService.get(UUID.fromString(pid));

		String sid = request.getParameter("subTypeId");
		TaskType stype = taskTypeService.get(UUID.fromString(sid));

		entity.setParentType(ptype);
		entity.setSubType(stype);
		entity.setStatus(ProductStatus.PUBLISH);
		entity.setIsOnSale(0);
		
		entity.setCreatedDate(new Date());
		

		productService.save(entity);
		attr.addFlashAttribute("msg", "发布成功!");
		return mv;
	}
	
	
	
	/**
	 * 编辑商品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/edit")
	public ModelAndView edit(Product entity, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/product/v/add");

		Product old = productService.get(UUID.fromString(request.getParameter("id")));
		
		
		
		
		
		if (StringUtils.isEmpty(entity.getName())) {
			attr.addFlashAttribute("js", "<script>alert('请输入商品名称.')</script>");
			return mv;
		}

		User user = SessionUtil.getInstance().getSessionUser();

		if (user != null) {
			user = userService.get(user.getId());
			entity.setUser(user);
		}

		String pid = request.getParameter("parentTypeId");
		TaskType ptype = taskTypeService.get(UUID.fromString(pid));

		String sid = request.getParameter("subTypeId");
		TaskType stype = taskTypeService.get(UUID.fromString(sid));

		entity.setParentType(ptype);
		entity.setSubType(stype);
		entity.setStatus(ProductStatus.PUBLISH);
		entity.setIsOnSale(0);
		
		entity.setCreatedDate(new Date());
		

		productService.saveOrUpdate(entity);
		attr.addFlashAttribute("msg", "修改成功!");
		
		BeanUtils.copyProperties(entity,old);
		
		productService.saveOrUpdate(old);
		
		return mv;
	}

	/**
	 * 
	 * 我发布的
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v/list")
	public ModelAndView userList(@RequestParam("p") int p,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_product_release");

		DetachedCriteria criteria = productService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
			
		}
		
		criteria.add(Restrictions.eq("user", user));
		
		Page<Product> page = new Page<Product>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
		
		page = productService.findPageByCriteria(criteria, page);
		
		

		List<Product> list = page.getResult();

		mv.addObject("list", list);

		mv.addObject("topName", "我发布的");
		
		mv.addObject("page", HttpUtil.genPageHtml(page, request));

		return mv;
	}

	/**
	 * 我购买的
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/buylist")
	public ModelAndView userBuyList(@RequestParam("p") int p,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_product_buylist");
		



		DetachedCriteria criteria = orderService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
			criteria.add(Restrictions.eq("user", user));
			
		}
		//criteria.add(Restrictions.eq("status", OrderStatus.HAS_PAY));
		criteria.add(Restrictions.eq("isPay", 1));
		
		Page<Order> page = new Page<Order>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
		
		page = orderService.findPageByCriteria(criteria, page);


		List<Order> olist = page.getResult();

		List<Product> plist = new ArrayList<Product>();
		
 
		for (Order pt : olist) {
			
			plist.add(pt.getProduct());
			
			
		}
		
		
		List<DataClass> data = ListUtil.combineList(plist,olist);
 

		// DetachedCriteria criteria2 = productService.createDetachedCriteria();
 
 
		
		mv.addObject("data",data);

		//mv.addObject("orderlist", list);

		mv.addObject("topName", "我购买的");
		
		mv.addObject("page", HttpUtil.genPageHtml(page, request));

		return mv;
	}
	
	
	/**
	 * 待支付
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/notpay")
	public ModelAndView notpay(@RequestParam("p") int p,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_product_notpay");
		



		DetachedCriteria criteria = orderService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		
		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
			criteria.add(Restrictions.eq("user", user));
		}
		
		criteria.add(Restrictions.eq("status", OrderStatus.NOT_PAY));
		
		criteria.add(Restrictions.eq("isPay", 0));
		
		Page<Order> page = new Page<Order>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
		
		page = orderService.findPageByCriteria(criteria, page);



		List<Order> olist = page.getResult();

		List<Product> plist = new ArrayList<Product>();
		
 
		for (Order pt : olist) {
			
			plist.add(pt.getProduct());
			
			
		}
		
		
		List<DataClass> data = ListUtil.combineList(plist,olist);
 

		// DetachedCriteria criteria2 = productService.createDetachedCriteria();
 
 
		
		mv.addObject("data",data);

		//mv.addObject("orderlist", list);

		mv.addObject("topName", "待支付的");
		
		mv.addObject("page", HttpUtil.genPageHtml(page, request));

		return mv;
	}

	/**
	 * 我销售的
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/selllist")
	public ModelAndView userSellList(@RequestParam("p") int p,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_product_selllist");

		DetachedCriteria criteria = orderService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
			
		}
		
		//criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("toUser", user));
		//criteria.add(Restrictions.eq("status", OrderStatus.HAS_PAY));
		//criteria.add(Restrictions.eq("isPay", OrderStatus.HAS_PAY));
		
		Page<Order> page = new Page<Order>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
		
		page = orderService.findPageByCriteria(criteria, page);
		


		List<Order> olist = page.getResult();

		List<Product> plist = new ArrayList<Product>();
		for (Order pt : olist) {
			plist.add(pt.getProduct());
		}

		List<DataClass> data = ListUtil.combineList(plist,olist);

		mv.addObject("data",data);

		mv.addObject("topName", "我销售的");
		
		mv.addObject("page", HttpUtil.genPageHtml(page, request));
		
		return mv;
	}

	/**
	 * 我收藏的
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/collectlist")
	public ModelAndView userCollectList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user_product_collectlist");

		DetachedCriteria criteria = orderService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			criteria.add(Restrictions.eq("user", user));
		}

		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("status", 0));

		List<Order> list = orderService.findByCriteria(criteria);

		List<Product> list2 = new ArrayList<Product>();
		for (Order pt : list) {
			list2.add(pt.getProduct());
		}

		// DetachedCriteria criteria2 = productService.createDetachedCriteria();

		mv.addObject("list", list2);

		mv.addObject("topName", "我收藏的");

		return mv;
	}
	
	/**
	 * 线下支付
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/addorder")
	public ModelAndView addOrder(@RequestParam("productId") UUID productId, HttpServletRequest request,RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("ucenter_addorder");

		User user = (User)SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		Product product = productService.get(productId);
		
		if(product.getStatus()!=ProductStatus.REVIEW||product.getIsOnSale()!=1)
		{
			attr.addFlashAttribute("js", "<script>alert('购买失败：商品已下架。');</script>");
			return new ModelAndView("redirect:/product/detail?pid="+product.getId());
		}
		
		
		Order order =new Order();
		order.setPrice(product.getPrice());
		order.setCreatedDate(new Date());
		order.setUser(user);
		order.setCount(1);
		order.setStatus(OrderStatus.INVALID);
		order.setTotal(product.getPrice());
		order.setProduct(product);
		order.setToUser(product.getUser());
		order.setOrderNum(HttpUtil.getOrderNum());
		
		orderService.save(order);
 

		float toPay = product.getPrice();
		


		mv.addObject("toPay", toPay);
		mv.addObject("order", order);
		mv.addObject("product", product);
		
		return mv;
	}
	
	
	/**
	 * 线下支付
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/finishorder")
	public ModelAndView finishOrder(@RequestParam("orderId") UUID orderId, HttpServletRequest request,RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/product/v/buylist?p=1");

		User user = (User)SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
 
		Order order = orderService.get(orderId);
		order.setStatus(OrderStatus.NOT_PAY);

		
		orderService.saveOrUpdate(order);
 
		attr.addFlashAttribute("js", "<script>alert('已提交成功!\\n请尽快和卖家进行联系，并进行支付。')</script>");
		
		return mv;
	}
	
	/**
	 * 支付任务
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/v/payproduct")
	public ModelAndView payProduct(@RequestParam("productId") UUID productId, HttpServletRequest request,RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("ucenter_payproduct");

		User user = (User)SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		Product product = productService.get(productId);
		
		
		Order order =new Order();
		order.setPrice(product.getPrice());
		order.setCreatedDate(new Date());
		order.setUser(user);
		order.setCount(1);
		order.setStatus(0);
		order.setTotal(product.getPrice());
		order.setProduct(product);
		order.setToUser(product.getUser());
		
		
		orderService.save(order);
 

		float toPay = product.getPrice();
		
		if(user.getMoney()<toPay)
		{
			mv.setViewName("redirect:/recharge/v/add?toPay="+toPay+"&orderId="+order.getId());
			//mv.addObject("msg", "余额不足，请先充值。");
			return mv;
		}

		
		
		
		mv.addObject("toPay", toPay);
		mv.addObject("orderId", order.getId().toString());
		mv.addObject("product", product);
		
		return mv;
	}
	
	
	/**
	 * 支付商品
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/payproduct")
	public ModelAndView actionPayProduct(@RequestParam("orderId") UUID orderId, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/product/v/buylist?p=1");

		User user = (User) SessionUtil.getInstance().getSessionUser();
		if (user != null) {
			user = userService.get(user.getId());
		}
		
		Order order = orderService.get(orderId);
		
		Product product = order.getProduct();
		float toPay = product.getPrice();
		
		if(user.getMoney()<toPay)
		{
			mv.setViewName("redirect:/recharge/v/add?orderId="+orderId);
			return mv;
		}
		
		user.setMoney(user.getMoney()-toPay);
		userService.saveOrUpdate(user);
		
 
		
		PayRecord payRecord = new PayRecord();
		payRecord.setCreatedDate(new Date());
		payRecord.setTotal(order.getTotal());
		payRecord.setUser(user);
		payRecord.setOrder(order);
		
		payRecordService.save(payRecord);
		
		order.setStatus(1);
		orderService.saveOrUpdate(order);
		
		product.setTradeCount(product.getTradeCount()+1);
		productService.saveOrUpdate(product);
		
		return mv;
	}
	
	
	/**
	 * 热门素材
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/hotproduct")
	public ModelAndView hotProduct(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("product_hot");

		
		
		
		int p = 1;
		String pstr = request.getParameter("p");
		if(!StringUtils.isEmpty(pstr))
		{
			p = Integer.parseInt(pstr);
		}
		
		
		
		Page<Product> page = new Page<Product>();
		page.setPageSize(8);
		page.setPageNo(1);
		page.addOrder("tradeCount", "desc");

		DetachedCriteria criteria = productService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.isNotNull("thumb"));

		page = productService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		//mv.addObject("p", HttpUtil.genPageHtml(page, request));
		

		return mv;
		
	}
	
	
	/**
	 * 热门素材
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rightlist")
	public ModelAndView rightList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("product_rightlist");

		
		
		
		int p = 1;
		String pstr = request.getParameter("p");
		if(!StringUtils.isEmpty(pstr))
		{
			p = Integer.parseInt(pstr);
		}
		
		
		
		Page<Product> page = new Page<Product>();
		page.setPageSize(10);
		page.setPageNo(1);
		page.addOrder("visitCount", "desc");

		DetachedCriteria criteria = productService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));


		page = productService.findPageByCriteria(criteria, page);
		
		mv.addObject("list", page.getResult());
		//mv.addObject("p", HttpUtil.genPageHtml(page, request));
		

		return mv;
		
	}
	
	
	/**
	 * 
	 * 我发布的
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_product_list");

		DetachedCriteria criteria = productService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		 
		
		Page<Product> page = new Page<Product>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");
		
		page = productService.findPageByCriteria(criteria, page);
		
		

		List<Product> list = page.getResult();

		mv.addObject("list", list);

		mv.addObject("page", HttpUtil.genPageHtml(page, request));

		return mv;
	}
	
	
	@RequestMapping(value = "/action/review")
	public ModelAndView actionReview(@RequestParam("id") UUID uuid,@RequestParam("rs") int rs) {
		ModelAndView mv = new ModelAndView("redirect:/product/admin/list?p=1");
		Product entity = productService.get(uuid);
	
//		if(entity.getStatus() != TaskStatus.PUBLISH)
//		{
//			return mv;
//		}
		
		if(rs==0)
		{
			entity.setStatus(ProductStatus.REVIEW_FAIL);
		}
		
		if(rs==1)
		{
			entity.setStatus(ProductStatus.REVIEW);
		}
		
		productService.saveOrUpdate(entity);
		
		return mv;
	}

	
	
	/**
	 * 上下架
	 * @param uuid
	 * @param rs
	 * @return
	 */
	@RequestMapping(value = "/action/onsale")
	public ModelAndView actionOnSale(@RequestParam("id") UUID uuid,@RequestParam("rs") int rs,
			HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("redirect:/product/admin/list?p=1");
		Product entity = productService.get(uuid);
		entity.setIsOnSale(rs);
		productService.saveOrUpdate(entity);
		//String url = HttpUtil.getReferView(request);
		
		
		String referer = request.getHeader("Referer");
	    mv.setViewName("redirect:"+referer);
		return mv;
	}
	
	
}