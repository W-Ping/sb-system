package com.ping.vo.hosue;

import com.ping.vo.BaseVo;

import java.math.BigDecimal;

/**
 * 房屋信息明细表
 *
 * @author lwp
 */
public class HouseDetailInfoVo extends BaseVo {

	/**
	 *
	 */
	private String houseCode;
	/**
	 *
	 */
	private String houseDetailCode;
	/**
	 *
	 */
	private String houseDetailName;
	/**
	 * 房间类型【0：卧室，1：客厅，2：厨房，3：卫生间，4：阳台】
	 */
	private Integer roomType;
	/**
	 * 顶面积
	 */
	private BigDecimal floorAreaSize;
	/**
	 * 墙面积
	 */
	private BigDecimal wallAreaSize;
	/**
	 * 地板面积
	 */
	private BigDecimal ceilAreaSize;

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(final String houseCode) {
		this.houseCode = houseCode;
	}

	public String getHouseDetailCode() {
		return houseDetailCode;
	}

	public void setHouseDetailCode(final String houseDetailCode) {
		this.houseDetailCode = houseDetailCode;
	}

	public String getHouseDetailName() {
		return houseDetailName;
	}

	public void setHouseDetailName(final String houseDetailName) {
		this.houseDetailName = houseDetailName;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public BigDecimal getFloorAreaSize() {
		return floorAreaSize;
	}

	public void setFloorAreaSize(final BigDecimal floorAreaSize) {
		this.floorAreaSize = floorAreaSize;
	}

	public BigDecimal getWallAreaSize() {
		return wallAreaSize;
	}

	public void setWallAreaSize(final BigDecimal wallAreaSize) {
		this.wallAreaSize = wallAreaSize;
	}

	public BigDecimal getCeilAreaSize() {
		return ceilAreaSize;
	}

	public void setCeilAreaSize(final BigDecimal ceilAreaSize) {
		this.ceilAreaSize = ceilAreaSize;
	}
}
