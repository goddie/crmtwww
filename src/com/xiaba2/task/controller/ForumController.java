package com.xiaba2.task.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaba2.cms.domain.Article;
import com.xiaba2.cms.domain.ArticleBody;
import com.xiaba2.cms.domain.ArticleType;
import com.xiaba2.cms.service.ArticleService;
import com.xiaba2.cms.service.ArticleTypeService;
import com.xiaba2.core.Page;
import com.xiaba2.task.domain.Forum;
import com.xiaba2.task.domain.ForumReply;
import com.xiaba2.task.domain.ForumType;
import com.xiaba2.task.domain.Task;
import com.xiaba2.task.domain.User;
import com.xiaba2.task.service.ForumReplyService;
import com.xiaba2.task.service.ForumService;
import com.xiaba2.task.service.ForumTypeService;
import com.xiaba2.util.HttpUtil;
import com.xiaba2.util.SessionUtil;

@Controller
@RequestMapping("/forum")
public class ForumController {
	@Resource
	private ForumService forumService;

	@Resource
	private ForumTypeService forumTypeService;

	@Resource
	private ForumReplyService forumReplyService;

	@Resource
	private ArticleTypeService articleTypeService;

	@Resource
	private ArticleService articleService;

	/**
	 * 社区首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView getIndex() {
		ModelAndView mv = new ModelAndView("forum");

		// 热点
		Page<Forum> page2 = new Page<Forum>();
		page2.setPageSize(10);
		page2.setPageNo(1);
		page2.addOrder("visits", "desc");

		DetachedCriteria criteria2 = forumService.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete", 0));
		criteria2.add(Restrictions.eq("isCheck", 1));
		
		// List<Article> list = articleService.findByCriteria(criteria);

		page2 = forumService.findPageByCriteria(criteria2, page2);

		mv.addObject("list2", page2.getResult());

		return mv;

		// DetachedCriteria criteria = forumService.createDetachedCriteria();
		//
		// Page<Forum> p = new Page<Forum>();
		// p.setPageSize(8);
		// p.setPageNo(1);
		// p.addOrder("createdDate", "desc");
		//
		// p = forumService.findPageByCriteria(criteria, p);
		//
		// // List<Product> list = productService.findByCriteria(criteria);
		//
		// mv.addObject("list", p.getResult());

	}

	/**
	 * 删除，只是把version 改大
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/del")
	public ModelAndView actionDel(@RequestParam("id") UUID uuid) {
		ModelAndView mv = new ModelAndView("redirect:/forum/admin/list?p=1");

		Forum entity = forumService.get(uuid);
		entity.setIsDelete(1);
		forumService.saveOrUpdate(entity);

		return mv;
	}

	/**
	 * 子栏目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sub")
	public ModelAndView getSub(@RequestParam("id") UUID fid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("forum_sub");

		String pstr = request.getParameter("p");
		int p = 1;
		if (!StringUtils.isEmpty(pstr)) {
			p = Integer.parseInt(pstr);
		}

		ForumType subType = forumTypeService.get(fid);

		Page<Forum> page = new Page<Forum>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		DetachedCriteria criteria = forumService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("isCheck", 1));
		criteria.add(Restrictions.eq("subType", subType));
		// List<Article> list = articleService.findByCriteria(criteria);

		page = forumService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		mv.addObject("sub", subType);

		mv.addObject("pageHtml", HttpUtil.genPageHtml(page, request));

		// 热点
		Page<Forum> page2 = new Page<Forum>();
		page2.setPageSize(10);
		page2.setPageNo(1);
		page2.addOrder("visits", "desc");

		DetachedCriteria criteria2 = forumService.createDetachedCriteria();
		criteria2.add(Restrictions.eq("isDelete", 0));
		criteria2.add(Restrictions.eq("subType", subType));
		// List<Article> list = articleService.findByCriteria(criteria);

		page2 = forumService.findPageByCriteria(criteria2, page2);

		mv.addObject("list2", page2.getResult());

		return mv;

	}

	/**
	 * 发新帖
	 * 
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/action/add")
	public ModelAndView add(Forum entity, HttpServletRequest request, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView("redirect:/forum/user/add");

		if (StringUtils.isEmpty(entity.getTitle())) {
			return mv;
		}

		User user = SessionUtil.getInstance().getSessionUser();
		if (user == null) {
			attr.addFlashAttribute("msg", "<script>alert('请登录再发帖')</script>");
			return mv;
		} else {
			entity.setUser(user);
		}

		entity.setCreatedDate(new Date());

		String pid = request.getParameter("parentTypeId");
		if (!StringUtils.isEmpty(pid)) {
			entity.setType(forumTypeService.get(UUID.fromString(pid)));
		}

		String sid = request.getParameter("subTypeId");
		if (!StringUtils.isEmpty(sid)) {
			entity.setSubType(forumTypeService.get(UUID.fromString(sid)));
		}

		forumService.save(entity);

		
		attr.addFlashAttribute("js","<script>alert('发布成功')</script>");
		
		return mv;
	}

	/**
	 * 
	 * 社区首页调用
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/forumroll")
	public ModelAndView forumRoll() {
		ModelAndView mv = new ModelAndView("forum_index_roll");

		ArticleType subType = articleTypeService.get(UUID.fromString("640a01c3-442f-44ea-9732-440f7e072d31"));

		List<Article> list = articleService.getTop(null, subType, 5, true);

		mv.addObject("list", list);

		return mv;
	}

	/**
	 * 首页标签
	 * 
	 * @return
	 */
	@RequestMapping(value = "/forumtab")
	public ModelAndView forumTab() {
		ModelAndView mv = new ModelAndView("forum_tab");

		String[] types = new String[] { "03ffb265-8463-4665-b7ca-11890ee1df79", "86475bb1-2dae-4101-b879-203ff0f0a56e",
				"f2831b1a-ac8d-4b1b-a1ef-4291acd3a0a3", "b70ce034-55ab-4761-8d26-c98a762f7d23",
				"07b7954b-8a33-4bd0-9589-d9179c88e611" };

		for (int i = 0; i < types.length; i++) {

			ForumType type = forumTypeService.get(UUID.fromString(types[i]));
			List<Forum> list = forumService.getTop(type, null, 15, false);
			mv.addObject("list" + (i + 1), list);

		}

		return mv;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detail(@RequestParam("id") UUID fid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("forum_detail");

		String pstr = request.getParameter("p");
		int p = 1;
		if (!StringUtils.isEmpty(pstr)) {
			p = Integer.parseInt(pstr);
		}

		Forum forum = forumService.get(fid);
		mv.addObject("entity", forum);

		forum.setVisits(forum.getVisits() + 1);
		forumService.saveOrUpdate(forum);

		Page<ForumReply> page = new Page<ForumReply>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "asc");

		DetachedCriteria criteria = forumReplyService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		//criteria.add(Restrictions.eq("isCheck", 1));
		criteria.add(Restrictions.eq("forum", forum));
		// List<Article> list = articleService.findByCriteria(criteria);

		page = forumReplyService.findPageByCriteria(criteria, page);

		mv.addObject("list", page.getResult());

		mv.addObject("pageHtml", HttpUtil.genPageHtml(page, request));

		return mv;
	}
	
	
	/**
	 * 搜索
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request) {
		
		
		ModelAndView mv = new ModelAndView("forum_search");

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

		DetachedCriteria criteria = forumService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));
		criteria.add(Restrictions.eq("isCheck", 1));
		
		criteria.add(Restrictions.like("title", key , MatchMode.ANYWHERE));


		Page<Forum> page = new Page<Forum>();
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.setPageNo(p);
		page.addOrder("createdDate", "desc");

		page = forumService.findPageByCriteria(criteria, page);

		mv.addObject("name",key+" 搜索结果:");
		mv.addObject("list", page.getResult());
		mv.addObject("page", HttpUtil.genPageHtml(page, request));

		return mv;
	}

	/**
	 * 帖子列表
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/admin/list")
	public ModelAndView adminList(@RequestParam("p") int p, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("admin_forum_list");

		DetachedCriteria criteria = forumService.createDetachedCriteria();
		criteria.add(Restrictions.eq("isDelete", 0));

		Page<Forum> page = new Page<Forum>();
		page.setPageNo(p);
		page.setPageSize(HttpUtil.PAGE_SIZE);
		page.addOrder("createdDate", "desc");

		page = forumService.findPageByCriteria(criteria, page);

		List<Forum> list = page.getResult();

		mv.addObject("list", list);
		mv.addObject("pageHtml", HttpUtil.genPageHtml(page, request));
		return mv;
	}

	@RequestMapping(value = "/user/add")
	public ModelAndView userAdd() {
		ModelAndView mv = new ModelAndView("forum_add");

		return mv;
	}
	
	
	/**
	 * 审核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/action/ischeck")
	public ModelAndView isCheck(@RequestParam("id") UUID uuid,@RequestParam("rs") int rs) {
		ModelAndView mv = new ModelAndView("redirect:/forum/admin/list?p=1");

		Forum entity = forumService.get(uuid);
		entity.setIsCheck(rs);
		forumService.saveOrUpdate(entity);

		return mv;
	}
}