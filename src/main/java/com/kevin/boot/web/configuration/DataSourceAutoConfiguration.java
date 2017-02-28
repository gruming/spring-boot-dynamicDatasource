package com.kevin.boot.web.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.kevin.boot.web.config.DataSourceConfig;
import com.kevin.boot.web.datasource.DynamicDataSource;
import com.kevin.boot.web.utils.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KevinBlandy on 2017/2/28 14:11
 */
@Configuration
@EnableConfigurationProperties(DataSourceConfig.class)
public class DataSourceAutoConfiguration {
	
	@Autowired
	private DataSourceConfig dataSourceConfig;
	
	@Bean
	public DataSource dataSource(){
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		//从配置获取一个主库
		DataSource masterDataSource = this.dataSourceConfig.getMasterDataSource();
		//默认库为主库
		dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
		Map<Object,Object> dataSources = new HashMap<Object,Object>();
		if(!GeneralUtils.isEmpty(this.dataSourceConfig.getSlaveurl()) && !GeneralUtils.isEmpty(this.dataSourceConfig.getSlaveusername()) && !GeneralUtils.isEmpty(this.dataSourceConfig.getSlavepassword())){
			int length = this.dataSourceConfig.getSlaveurl().size();
			DruidDataSource druidDataSource = null;
			for (int x = 0;x < length ; x++){
				String url = this.dataSourceConfig.getSlaveurl().get(x);
				String userName = this.dataSourceConfig.getSlaveusername().get(x);
				String passWord = this.dataSourceConfig.getSlavepassword().get(x);
				if(!GeneralUtils.isEmpty(url) && !GeneralUtils.isEmpty(userName) && !GeneralUtils.isEmpty(passWord)){
					//添加slave库
					druidDataSource = new DruidDataSource();
					druidDataSource.setUrl(url);
					druidDataSource.setUsername(userName);
					druidDataSource.setPassword(passWord);
					druidDataSource.setMaxActive(this.dataSourceConfig.getMaxactive());
					druidDataSource.setInitialSize(this.dataSourceConfig.getInitialsize());
					dataSources.put("SLAVE"+x,druidDataSource);
				}
			}
		}
		//添加主库到集合,注意此处 MASTER 值
		dataSources.put("MASTER",masterDataSource);
		dynamicDataSource.setTargetDataSources(dataSources);
		return dynamicDataSource;
	}
}
