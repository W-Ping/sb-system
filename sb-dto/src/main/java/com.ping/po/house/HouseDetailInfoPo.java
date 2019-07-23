package com.ping.po.house;

import com.ping.po.BasePo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 房屋信息明细表
 *
 * @author lwp
 */
@Table(name = "house_detail_info")
public class HouseDetailInfoPo extends BasePo {

    /**
     *
     */
    @Column(name = "house_code")
    private String houseCode;
    /**
     *
     */
    @Column(name = "house_detail_code")
    private String houseDetailCode;
    /**
     *
     */
    @Column(name = "house_detail_name")
    private String houseDetailName;
    /**
     * 房间类型【0：卧室，1：客厅，2：厨房，3：卫生间，4：阳台】
     */
    @Column(name = "room_type")
    private Integer roomType;
    /**
     * 顶面积
     */
    @Column(name = "floor_area_size")
    private BigDecimal floorAreaSize;
    /**
     * 墙面积
     */
    @Column(name = "wall_area_size")
    private BigDecimal wallAreaSize;
    /**
     * 地板面积
     */
    @Column(name = "ceil_area_size")
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
