package com.xiaba2.task.gen;

public class EnumSet {

	public static class TaskStatus
	{
		/**
		 * 发布
		 */
		public static int PUBLISH = 0;
		/**
		 * 审核
		 */
		public static int REVIEW = 1;
		/**
		 * 投稿
		 */
		public static int SUBMIT= 2;
		/**
		 * 选稿
		 */
		public static int EVALUATION= 3;
		
		/**
		 * 结束
		 */
		public static int END= 4;
		
		
		/**
		 * 审核失败
		 */
		public static int REVIEW_FAIL = 10;
	}
	
	
	
	/**
	 * 产品状态
	 * @author Administrator
	 *
	 */
	public static class ProductStatus
	{
		/**
		 * 待审核
		 */
		public static int PUBLISH = 0;
		/**
		 * 审核成功
		 */
		public static int REVIEW = 1;
		

		/**
		 * 审核失败
		 */
		public static int REVIEW_FAIL = 10;
	}
	
	
	/**
	 * 订单状态
	 * 0无效 1待付款 2已付款 3已服务 4交易关闭 5订单取消
	 * @author Administrator
	 *
	 */
	public static class OrderStatus
	{
		/**
		 * 0无效
		 */
		public static int INVALID = 0;
		/**
		 * 1待付款 
		 */
		public static int NOT_PAY = 1;
		/**
		 * 2已付款
		 */
		public static int HAS_PAY = 2;
		/**
		 * 3已服务
		 */
		public static int HAS_SERVE = 3;
		/**
		 * 4交易关闭
		 */
		public static int CLOSED = 4;

		
		/**
		 * 5订单取消
		 */
		public static int CANCEL = 5;
 
	}
	
	
}
