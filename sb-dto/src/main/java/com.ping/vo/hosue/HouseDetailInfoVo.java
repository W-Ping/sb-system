package com.ping.vo.hosue;

import com.ping.vo.BaseVo;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	 *
	 */
	private Integer roomIndex;
	/**
	 *
	 */
	private String roomNickName;
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

	/**
	 *
	 */
	private BigDecimal totalBudgetAmount;

	private List<HouseBudgetInfoVo> houseBudgetInfoVos;

	public List<HouseBudgetInfoVo> getHouseBudgetInfoVos() {
		return houseBudgetInfoVos;
	}

	public void setHouseBudgetInfoVos(final List<HouseBudgetInfoVo> houseBudgetInfoVos) {
		this.houseBudgetInfoVos = houseBudgetInfoVos;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(final String houseCode) {
		this.houseCode = houseCode;
	}

	public String getRoomNickName() {
		return roomNickName;
	}

	public void setRoomNickName(final String roomNickName) {
		this.roomNickName = roomNickName;
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

	public Integer getRoomIndex() {
		return roomIndex;
	}

	public void setRoomIndex(final Integer roomIndex) {
		this.roomIndex = roomIndex;
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

	public BigDecimal getTotalBudgetAmount() {
		if (!CollectionUtils.isEmpty(houseBudgetInfoVos)) {
			totalBudgetAmount = houseBudgetInfoVos.stream().map(v -> (v.getBudgetAmount() != null ? v.getBudgetAmount() : BigDecimal.ZERO)
					.multiply(v.getBudgetCount() != null ? new BigDecimal(v.getBudgetCount()) : BigDecimal.ZERO)).
					reduce(BigDecimal.ZERO, BigDecimal::add);
			return totalBudgetAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return totalBudgetAmount;
	}

	public void setTotalBudgetAmount(final BigDecimal totalBudgetAmount) {
		this.totalBudgetAmount = totalBudgetAmount;
	}
}
