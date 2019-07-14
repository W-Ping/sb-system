package com.ping.config;

import com.github.pagehelper.PageHelper;
import com.ping.BaseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author lwp
 * @create 2019/07/13 17:33
 */
@Configuration
@MapperScan(value = "tk.mybatis.mapper.annotation", mapperHelperRef = "mapperHelper")
public class MyBatisConfigRef {

	@Bean
	public MapperHelper mapperHelper() {
		Config config = new Config();
		config.setOrder("BEFORE");
		List<Class> mappers = new ArrayList<>();
		mappers.add(BaseMapper.class);
		config.setMappers(mappers);
		MapperHelper mapperHelper = new MapperHelper();
		mapperHelper.setConfig(config);
		return mapperHelper;
	}

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("params", "count=countSql");
		properties.setProperty("autoRuntimeDialect", "true");
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("supportMethodsArguments", "true");
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
