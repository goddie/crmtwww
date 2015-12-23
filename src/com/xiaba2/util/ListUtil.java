package com.xiaba2.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ListUtil {

	/**
	 * 是否是空数组
	 * @param list
	 * @return
	 */
	public static Boolean isEmpty(List<?> list) {

		if (list == null || list.isEmpty()) {
			return true;
		}

		return false;

	}
	
	
	public static List<DataClass> combineList(List<?> list1,List<?> list2)
	{
 
		ArrayList<DataClass> list = new ArrayList<DataClass>();
		
		for (int i = 0; i < list1.size(); i++) {
			
			list.add(new DataClass(list1.get(i),list2.get(i)));
			
		}
		
		return list;
	}
	
	
	public static class DataClass
	{
		private Object obj1;
		private Object obj2;
		
		public DataClass(Object a,Object b)
		{
			obj1 = a;
			obj2 = b;
		}

		public Object getObj1() {
			return obj1;
		}

		public void setObj1(Object obj1) {
			this.obj1 = obj1;
		}

		public Object getObj2() {
			return obj2;
		}

		public void setObj2(Object obj2) {
			this.obj2 = obj2;
		}
		
		
		
	}

}
