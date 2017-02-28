package com.kevin.boot.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KevinBlandy on 2017/2/28 14:07
 * 数据库配置,他妈的说好的 "属性宽松的规则" 呢？
 */
@ConfigurationProperties(prefix = "jdbc.datasource",ignoreInvalidFields = false)
public class DataSourceConfig {
	private String masterurl;
	private String masterusername;
	private String masterpassword;
	
	private List<String> slaveurl = new ArrayList<String>();
	private List<String> slaveusername = new ArrayList<String>();
	private List<String> slavepassword = new ArrayList<String>();
	
	private String driverclassname;
	private int maxactive;
	private int initialsize;
	
	public String getMasterurl() {
		return masterurl;
	}
	
	public void setMasterurl(String masterurl) {
		this.masterurl = masterurl;
	}
	
	public String getMasterusername() {
		return masterusername;
	}
	
	public void setMasterusername(String masterusername) {
		this.masterusername = masterusername;
	}
	
	public String getMasterpassword() {
		return masterpassword;
	}
	
	public void setMasterpassword(String masterpassword) {
		this.masterpassword = masterpassword;
	}
	
	public List<String> getSlaveurl() {
		return slaveurl;
	}
	
	public void setSlaveurl(List<String> slaveurl) {
		this.slaveurl = slaveurl;
	}
	
	public List<String> getSlaveusername() {
		return slaveusername;
	}
	
	public void setSlaveusername(List<String> slaveusername) {
		this.slaveusername = slaveusername;
	}
	
	public List<String> getSlavepassword() {
		return slavepassword;
	}
	
	public void setSlavepassword(List<String> slavepassword) {
		this.slavepassword = slavepassword;
	}
	
	public String getDriverclassname() {
		return driverclassname;
	}
	
	public void setDriverclassname(String driverclassname) {
		this.driverclassname = driverclassname;
	}
	
	public int getMaxactive() {
		return maxactive;
	}
	
	public void setMaxactive(int maxactive) {
		this.maxactive = maxactive;
	}
	
	public int getInitialsize() {
		return initialsize;
	}
	
	public void setInitialsize(int initialsize) {
		this.initialsize = initialsize;
	}
	
	/**
	 * 根据配置获取Master连接池对象
	 * @return
	 */
	public DataSource getMasterDataSource(){
		DruidDataSource masterDataSource = new DruidDataSource();
		masterDataSource.setUrl(this.getMasterurl());
		masterDataSource.setUsername(this.getMasterusername());
		masterDataSource.setPassword(this.getMasterpassword());
		masterDataSource.setMaxActive(this.getMaxactive());
		masterDataSource.setInitialSize(this.getInitialsize());
		masterDataSource.setDriverClassName(this.getDriverclassname());
		return masterDataSource;
	}
}
