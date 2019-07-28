package com.ping.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.ping.constant.SysConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lwp
 * @create 2019/07/13 15:23
 */
public class BaseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 *
	 */
	private Long id;
	/**
	 *
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	/**
	 *
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 *
	 */
	private Integer version = 0;

	/**
	 *
	 */
	private String remark;

	private Integer status = SysConstant.STATUS_0;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(final Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(final String remark) {
		this.remark = remark;
	}
}
