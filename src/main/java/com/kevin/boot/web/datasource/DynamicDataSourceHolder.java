package com.kevin.boot.web.datasource;

/**
 * ThreadLocal记录当前线程的DB key
 */
public class DynamicDataSourceHolder {
	//写库标识
	public static final String MASTER = "MASTER";
	
	//读库标识
	private static final String SLAVE = "SLAVE";
	
	//使用ThreadLocal记录当前线程的数据源标识
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();
	
	/**
	 * 设置数据源标识
	 */
	private static void putDataSourceKey(String key) {
		holder.set(key);
	}
	
	/**
	 * 获取数据源key
	 */
	public static String getDataSourceKey() {
		return holder.get();
	}
	
	/**
	 * 标记写库
	 */
	public static void markMaster(){
		putDataSourceKey(MASTER);
	}
	
	/**
	 * 标记读库
	 */
	public static void markSlave(){
		putDataSourceKey(SLAVE);
	}
	
	/**
	 * 是否是主库
	 */
	public static boolean isMaster() {
		return  MASTER.equals(holder.get());
	}
}
