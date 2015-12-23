package com.xiaba2.util;

import com.xiaba2.task.gen.EnumSet.TaskStatus;

public class EnumUtil {

	/**
	 * 任务状态
	 * 
	 * @param status
	 */
	public static String getTaskStatusName(int status) {
		switch (status) {

		case 0:
			return "待审核";
		case 1:
			return "审核成功";
		case 10:
			return "审核失败";
		case 2:
			return "投稿";
		case 3:
			return "选稿";
		case 4:
			return "结束";

		}

		return "";
	}

	/**
	 * 1 单人悬赏 2 计件悬赏 3 项目招标 4 项目交付 5 直接雇佣
	 * 
	 * @param topType
	 * @return
	 */
	public static String getTaskType(int topType) {
		String s = "单人悬赏";

		switch (topType) {
		case 1:
			s = "单人悬赏";
			break;

		case 2:
			s = "计件悬赏";
			break;

		case 3:
			s = "项目招标";
			break;

		case 4:
			s = "项目交付";
			break;

		case 5:
			s = "直接雇佣";
			break;
		default:

			break;
		}

		return s;
	}

	/**
	 * 标书状态 1 = 中标 0 = 未中标
	 * 
	 * @param status
	 * @return
	 */
	public static String getSubmitStatus(int status) {
		String s = "未中标";

		switch (status) {
		case 1:
			s = "中标";
			break;

		case 0:
			s = "未中标";
			break;

		}

		return s;
	}

	/**
	 * 订单支付 1 = 已支付 0 = 未支付
	 * 
	 * @param status
	 * @return
	 */
	public static String getOrderPay(int status) {
		String s = "未支付";

		switch (status) {
		case 1:
			s = "已支付";
			break;

		case 0:
			s = "未支付";
			break;

		}

		return s;
	}

	/**
	 * 订单支付 1 = 已支付 0 = 未支付
	 * 
	 * @param status
	 * @return
	 */
	public static String getProductTopType(int status) {
		String s = "商品";

		switch (status) {
		case 1:
			s = "商品";
			break;

		case 2:
			s = "服务";
			break;

		}

		return s;
	}

	/**
	 * 产品状态
	 * 
	 * @param status
	 * @return
	 */
	public static String getProductStatus(int status) {
		switch (status) {

		case 0:
			return "待审核";
		case 1:
			return "审核成功";
		case 10:
			return "审核失败";

		}

		return "";
	}

	/**
	 * 订单状态
	 * 
	 * @param status
	 * @return
	 */
	public static String getOrderStatus(int status) {
		switch (status) {

		case 0:
			return "无效";
		case 1:
			return "待付款";
		case 2:
			return "已付款待服务";
		case 3:
			return "已服务";
		case 4:
			return "交易关闭";
		case 5:
			return "订单取消";
		}

		return "";
	}

}
