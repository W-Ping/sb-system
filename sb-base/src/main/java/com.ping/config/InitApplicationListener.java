package com.ping.config;

import com.ping.utils.PropertiesUtil;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author lwp
 * @create 2019/07/15 13:32
 */
public class InitApplicationListener implements ApplicationListener<ApplicationStartingEvent> {

	private String propertiesFileName;

	public InitApplicationListener(String propertiesFileName) {
		this.propertiesFileName = propertiesFileName;
	}

	@Override
	public void onApplicationEvent(final ApplicationStartingEvent applicationStartingEvent) {
		PropertiesUtil.loadAllProperties(this.propertiesFileName);
	}
}
