package com.ping;

import com.ping.utils.SnowFlakeWorkerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwp
 * @create 2019/07/14 10:40
 */
public class BaseService {

	/**
	 * @param prefix
	 * @return
	 */
	protected String getUniqueId(String prefix) {
		SnowFlakeWorkerUtil snowFlakeWorkerUtil = new SnowFlakeWorkerUtil(0, 0);
		long id = snowFlakeWorkerUtil.nextId();
		return prefix != null ? (prefix + id) : id + "";
	}

	/**
	 * @param prefix
	 * @param size
	 * @return
	 */
	protected List<String> getMultiUniqueId(String prefix, int size) {
		List<String> idList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			idList.add(getUniqueId(prefix));
		}
		return idList;
	}
}
