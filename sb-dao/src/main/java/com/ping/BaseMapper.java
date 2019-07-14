package com.ping;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author lwp
 * @create 2019/07/13 17:05
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
