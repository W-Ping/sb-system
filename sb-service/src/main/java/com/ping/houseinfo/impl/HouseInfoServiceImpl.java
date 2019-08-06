package com.ping.houseinfo.impl;

import com.ping.BaseService;
import com.ping.budgetinfo.IBudgetInfoService;
import com.ping.co.HouseInfoCo;
import com.ping.co.SearchCo;
import com.ping.constant.ResultEnum;
import com.ping.constant.SysConstant;
import com.ping.exception.OptionsException;
import com.ping.exception.ValidateException;
import com.ping.houseinfo.IHouseInfoService;
import com.ping.mapper.IHouseBudgetInfoMapper;
import com.ping.mapper.IHouseDetailInfoMapper;
import com.ping.mapper.IHouseInfoMapper;
import com.ping.po.house.HouseBudgetInfoPo;
import com.ping.po.house.HouseDetailInfoPo;
import com.ping.po.house.HouseInfoPo;
import com.ping.utils.BeanMapperUtil;
import com.ping.vo.hosue.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lwp
 * @create 2019/07/14 10:39
 */
@Service
public class HouseInfoServiceImpl extends BaseService implements IHouseInfoService {

    @Autowired
    private IHouseInfoMapper iHouseInfoMapper;
    @Autowired
    private IHouseDetailInfoMapper iHouseDetailInfoMapper;
    @Autowired
    private IHouseBudgetInfoMapper iHouseBudgetInfoMapper;
    @Autowired
    private IBudgetInfoService iBudgetInfoService;

    @Override
    public boolean saveHouseInfo(final HouseInfoVo houseInfoVo) {
        if (houseInfoVo == null) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR);
        }
        HouseInfoPo houseInfoPo = BeanMapperUtil.map(houseInfoVo, HouseInfoPo.class);
        Example example = new Example(HouseInfoPo.class);
        example.createCriteria().andEqualTo("mobilePhone", houseInfoPo.getMobilePhone())
                .andEqualTo("status", SysConstant.STATUS_0);
        List<HouseInfoPo> houseInfoPos = iHouseInfoMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(houseInfoPos)) {
            houseInfoPo.setId(houseInfoPos.get(0).getId());
            return iHouseInfoMapper.updateByPrimaryKeySelective(houseInfoPo) >= 0;
        } else {
            houseInfoPo.setHouseCode(super.getUniqueId(SysConstant.UNIQUEID_HOUSE_PRIFIX));
            return iHouseInfoMapper.insertSelective(houseInfoPo) > 0;
        }
    }


    @Override
    public HouseInfoVo getHouseInfo(final HouseInfoCo co) {
        Example example = new Example(HouseInfoPo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", SysConstant.STATUS_0);
        if (StringUtils.isNotBlank(co.getHouseCode())) {
            criteria.andEqualTo("houseCode", co.getHouseCode());
        }
        if (co.getHouseType() != null) {
            criteria.andEqualTo("houseType", co.getHouseType());
        }
        if (StringUtils.isNotBlank(co.getMobilePhone())) {
            criteria.andEqualTo("mobilePhone", co.getMobilePhone());
        }
        HouseInfoPo houseInfoPo = iHouseInfoMapper.selectOneByExample(example);
        HouseInfoVo houseInfoVo = BeanMapperUtil.map(houseInfoPo, HouseInfoVo.class);
        return houseInfoVo;
    }

    /**
     * @param co
     * @return
     */
    @Override
    public HouseInfoVo getHouseInfoDetail(HouseInfoCo co) {
        HouseInfoVo houseInfoVo = getHouseInfo(co);
        if (houseInfoVo != null) {
            Example example = new Example(HouseDetailInfoPo.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("status", SysConstant.STATUS_0);
            criteria.andEqualTo("houseCode", houseInfoVo.getHouseCode());
            example.setOrderByClause("room_type asc ,room_index asc");
            List<HouseDetailInfoPo> houseDetailInfoPos = iHouseDetailInfoMapper.selectByExample(example);
            List<HouseDetailInfoVo> houseDetailInfoVos = BeanMapperUtil.mapToList(houseDetailInfoPos, HouseDetailInfoVo.class);
            if (!CollectionUtils.isEmpty(houseDetailInfoVos)) {
                List<String> houseDetailCodes = houseDetailInfoPos.stream().map(HouseDetailInfoPo::getHouseDetailCode).collect(Collectors.toList());
                example = new Example(HouseBudgetInfoPo.class);
                example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                        .andIn("houseDetailCode", houseDetailCodes);
                List<HouseBudgetInfoPo> houseBudgetInfoPos = iHouseBudgetInfoMapper.selectByExample(example);
                if (!CollectionUtils.isEmpty(houseBudgetInfoPos)) {
                    Map<String, List<HouseBudgetInfoPo>> resultMap = houseBudgetInfoPos.stream().collect(Collectors.groupingBy(v -> v.getHouseDetailCode()));
                    List<HouseBudgetInfoVo> houseBudgetInfoVos = null;
                    for (final HouseDetailInfoVo houseDetailInfoVo : houseDetailInfoVos) {
                        houseBudgetInfoPos = resultMap.get(houseDetailInfoVo.getHouseDetailCode());
                        houseBudgetInfoVos = BeanMapperUtil.mapToList(houseBudgetInfoPos, HouseBudgetInfoVo.class);
                        houseDetailInfoVo.setHouseBudgetInfoVos(houseBudgetInfoVos);
                    }
                }
            }
            houseInfoVo.setDetailInfoVos(houseDetailInfoVos);
        }
        return houseInfoVo;
    }

    /**
     * @param mobilePhone
     * @return
     */
    @Override
    public HouseInfoVo getHouseInfoByMobilePhone(final String mobilePhone) {
        HouseInfoCo houseInfoCo = new HouseInfoCo();
        houseInfoCo.setMobilePhone(mobilePhone);
        return getHouseInfoDetail(houseInfoCo);
    }

    /**
     * @param mobilePhone
     * @return
     */
    @Override
    public Map<String, String> calculateBudgetTotalAmount(final String mobilePhone) {
        Map<String, String> totalAmount = iHouseBudgetInfoMapper.calculateBudgetTotalAmount(mobilePhone);
        return totalAmount;
    }

    /**
     * @param mobilePhone
     * @return
     */
    @Override
    public HouseInfoVo getHouseInfo(final String mobilePhone) {
        if (StringUtils.isBlank(mobilePhone)) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "手机号码不能为空");
        }
        HouseInfoCo houseInfoCo = new HouseInfoCo();
        houseInfoCo.setMobilePhone(mobilePhone);
        return getHouseInfo(houseInfoCo);
    }

    /**
     * @param mobilePhone
     * @param roomType
     * @param roomIndex
     * @return
     */
    @Override
    public HouseDetailInfoVo getHouseDetailInfo(final String mobilePhone, final Integer roomType, final Integer roomIndex) {
        HouseInfoVo houseInfo = getHouseInfo(mobilePhone);
        if (houseInfo == null) {
            throw new ValidateException(ResultEnum.HOUSE_IS_NOT_EXISTS);
        }
        Example example = new Example(HouseDetailInfoPo.class);
        example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                .andEqualTo("houseCode", houseInfo.getHouseCode())
                .andEqualTo("roomIndex", roomIndex)
                .andEqualTo("roomType", roomType);
        HouseDetailInfoPo houseDetailInfoPo = iHouseDetailInfoMapper.selectOneByExample(example);
        HouseDetailInfoVo resultVo = BeanMapperUtil.map(houseDetailInfoPo, HouseDetailInfoVo.class);
        if (resultVo != null) {
            resultVo.setHouseBudgetInfoVos(this.queryHouseBudgetInfoByHouseDetailCode(resultVo.getHouseDetailCode()));

        }
        return resultVo;
    }

    /**
     * @param houseDetailCode
     * @return
     */
    @Override
    public HouseDetailInfoVo getHouseDetailInfo(final String houseDetailCode) {
        Example example = new Example(HouseDetailInfoPo.class);
        example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                .andEqualTo("houseDetailCode", houseDetailCode);
        HouseDetailInfoPo houseDetailInfoPo = iHouseDetailInfoMapper.selectOneByExample(example);
        HouseDetailInfoVo resultVo = BeanMapperUtil.map(houseDetailInfoPo, HouseDetailInfoVo.class);
        if (resultVo != null) {
            resultVo.setHouseBudgetInfoVos(this.queryHouseBudgetInfoByHouseDetailCode(houseDetailCode));

        }
        return resultVo;
    }

    /**
     * @param houseBudgetCode
     * @return
     */
    @Override
    public HouseBudgetInfoVo getHouseBudgetInfoByCode(final String houseBudgetCode) {
        if (StringUtils.isBlank(houseBudgetCode)) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "装修编码不能为空");
        }
        Example example = new Example(HouseBudgetInfoPo.class);
        example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                .andEqualTo("houseBudgetCode", houseBudgetCode);
        HouseBudgetInfoPo houseBudgetInfoPo = iHouseBudgetInfoMapper.selectOneByExample(example);
        HouseBudgetInfoVo houseBudgetInfoVo = BeanMapperUtil.map(houseBudgetInfoPo, HouseBudgetInfoVo.class);
        if (houseBudgetInfoVo != null) {
            BudgetClassifyInfoVo fatherClassifyInfo = iBudgetInfoService.getFatherClassifyInfoByName(houseBudgetInfoVo.getClassifyName());
            houseBudgetInfoVo.setFatherClassifyName(fatherClassifyInfo.getClassifyName());
            HouseDetailInfoVo houseDetailInfo = this.getHouseDetailInfo(houseBudgetInfoVo.getHouseDetailCode());
            if (houseDetailInfo != null) {
                houseBudgetInfoVo.setHouseDetailName(houseDetailInfo.getHouseDetailName());
                houseBudgetInfoVo.setRoomNickName(houseDetailInfo.getRoomNickName());
            }
        }
        return houseBudgetInfoVo;
    }

    /**
     * @param houseDetailCode
     * @return
     */
    @Override
    public List<HouseBudgetInfoVo> queryHouseBudgetInfoByHouseDetailCode(final String houseDetailCode) {
        if (StringUtils.isBlank(houseDetailCode)) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "房屋明细编码不能为空");
        }
        Example example = new Example(HouseBudgetInfoPo.class);
        example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                .andEqualTo("houseDetailCode", houseDetailCode);
        List<HouseBudgetInfoPo> houseBudgetInfoPos = iHouseBudgetInfoMapper.selectByExample(example);
        return BeanMapperUtil.mapToList(houseBudgetInfoPos, HouseBudgetInfoVo.class);
    }

    /**
     * @param searchCo
     * @return
     */
    @Override
    public List<HouseDetailInfoVo> searchByKeyword(SearchCo searchCo) {
        List<HouseDetailInfoVo> houseDetailInfoVos = iHouseDetailInfoMapper.searchByKeyword(searchCo);
        return houseDetailInfoVos;
    }

    /**
     * @param houseDetailInfoVo
     * @return
     */
    @Override
    public boolean saveHouseRoomInfo(HouseDetailInfoVo houseDetailInfoVo) {
        checkRoomInfo(houseDetailInfoVo);
        HouseInfoPo houseInfoPo = new HouseInfoPo();
        houseInfoPo.setHouseCode(houseDetailInfoVo.getHouseCode());
        houseInfoPo.setStatus(SysConstant.STATUS_0);
        houseInfoPo = iHouseInfoMapper.selectOne(houseInfoPo);
        if (houseInfoPo == null) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "房间主档信息未填写");
        }
//		checkRoomTypeInfo(houseDetailInfoVo, houseInfoPo);
        houseDetailInfoVo.setHouseDetailCode(super.getUniqueId(SysConstant.UNIQUEID_HOUSE_DETAIL_PRIFIX));
        int count = iHouseDetailInfoMapper.replaceHouseDetailInfo(houseDetailInfoVo);
        return count > 0;
    }

    /**
     * @param houseBudgetInfoVo
     * @return
     */
    @Override
    public boolean saveHouseBudgetInfo(final HouseBudgetInfoVo houseBudgetInfoVo) {
        String houseBudgetCode = houseBudgetInfoVo.getHouseBudgetCode();
        String houseDetailCode = houseBudgetInfoVo.getHouseDetailCode();
        if (StringUtils.isBlank(houseDetailCode)) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "房屋明细编码不能为空");
        }
        HouseDetailInfoVo houseDetailInfo = this.getHouseDetailInfo(houseDetailCode);
        if (houseDetailInfo == null) {
            throw new ValidateException(ResultEnum.DATA_NOT_EXISTS, "房屋信息不存在");
        }
        BudgetClassifyInfoVo budgetClassifyInfo = iBudgetInfoService.getBudgetClassifyInfoByName(houseBudgetInfoVo.getClassifyName());
        houseBudgetInfoVo.setClassifyCode(budgetClassifyInfo.getClassifyCode());
        houseBudgetInfoVo.setClassifyName(budgetClassifyInfo.getClassifyName());
        houseBudgetInfoVo.setHouseCode(houseDetailInfo.getHouseCode());
        HouseBudgetInfoPo houseBudgetInfoPo = BeanMapperUtil.map(houseBudgetInfoVo, HouseBudgetInfoPo.class);
        if (StringUtils.isNotBlank(houseBudgetCode)) {
            Example example = new Example(HouseBudgetInfoPo.class);
            example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                    .andEqualTo("houseBudgetCode", houseBudgetCode);
            houseBudgetInfoPo = iHouseBudgetInfoMapper.selectOneByExample(example);
            if (houseBudgetInfoPo == null) {
                throw new ValidateException(ResultEnum.DATA_NOT_EXISTS, "装修材料信息不存在");
            }
            if (StringUtils.isBlank(houseBudgetInfoVo.getBudgetCode())) {
                houseBudgetInfoPo.setBudgetAmount(houseBudgetInfoVo.getBudgetAmount());
                houseBudgetInfoPo.setBudgetName(houseBudgetInfoVo.getBudgetName());
            }
            houseBudgetInfoPo.setClassifyName(houseBudgetInfoVo.getClassifyName());
            houseBudgetInfoPo.setBudgetCount(houseBudgetInfoVo.getBudgetCount());
            houseBudgetInfoPo.setClassifyName(houseBudgetInfoVo.getClassifyName());
            houseBudgetInfoPo.setClassifyCode(houseBudgetInfoVo.getClassifyCode());
            return iHouseBudgetInfoMapper.updateByPrimaryKeySelective(houseBudgetInfoPo) >= 0;
        } else {
            houseBudgetInfoPo.setHouseBudgetCode(super.getUniqueId(SysConstant.UNIQUEID_HOUSE_BUDGET_PRIFIX));
            int i = iHouseBudgetInfoMapper.insertSelective(houseBudgetInfoPo);
            return i > 0;
        }
    }

    /**
     * @param houseDetailCode
     * @param budgetCodes
     * @return
     */
    @Transactional(rollbackFor = {OptionsException.class, Exception.class})
    @Override
    public boolean saveHouseBudgetInfo(final String houseDetailCode, final List<String> budgetCodes) {

        HouseDetailInfoVo houseDetailInfo = this.getHouseDetailInfo(houseDetailCode);
        if (houseDetailInfo == null) {
            throw new ValidateException(ResultEnum.DATA_NOT_EXISTS, "房屋信息不存在");
        }
        List<BudgetInfoVo> budgetInfoVos = iBudgetInfoService.queryBudgetInfosByCodes(budgetCodes);
        if (CollectionUtils.isEmpty(budgetInfoVos)) {
            throw new ValidateException(ResultEnum.DATA_NOT_EXISTS, "材料信息不存在");
        }
        for (final BudgetInfoVo budgetInfoVo : budgetInfoVos) {
            HouseBudgetInfoPo po = new HouseBudgetInfoPo();
            po.setHouseCode(houseDetailInfo.getHouseCode());
            po.setHouseDetailCode(houseDetailCode);
            po.setHouseBudgetCode(super.getUniqueId(SysConstant.UNIQUEID_HOUSE_BUDGET_PRIFIX));
            po.setBudgetCode(budgetInfoVo.getBudgetCode());
            po.setBudgetName(budgetInfoVo.getBudgetName());
            po.setBudgetAmount(budgetInfoVo.getBudgetAmount());
            po.setBudgetCount(1);
            po.setClassifyCode(budgetInfoVo.getClassifyCode());
            po.setClassifyName(budgetInfoVo.getClassifyName());
            if (iHouseBudgetInfoMapper.insertSelective(po) != 1) {
                throw new OptionsException(ResultEnum.SAVE_BUDGETINFO_FAILED);
            }
        }
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean deleteHouseBudgetInfo(final String houseBudgetCode) {
        if (StringUtils.isBlank(houseBudgetCode)) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "删除信息编码不能为空");
        }
        Example example = new Example(HouseBudgetInfoPo.class);
        example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                .andEqualTo("houseBudgetCode", houseBudgetCode);
        int i = iHouseBudgetInfoMapper.deleteByExample(example);
        return i > 0;
    }

    /**
     * @param mobilePhone
     * @param type
     * @return
     */
    @Override
    public List<HouseBudgetInfoVo> queryHouseBudgetInfoList(final String mobilePhone, final String type) {
        List<HouseBudgetInfoPo> houseBudgetInfoPos = null;
        if (SysConstant.QUERY_MAX.equals(type)) {
            houseBudgetInfoPos = iHouseBudgetInfoMapper.queryBudgetAmountDetailOfMax(mobilePhone);
        } else if (SysConstant.QUERY_min.equals(type)) {
            houseBudgetInfoPos = iHouseBudgetInfoMapper.queryBudgetAmountDetailOfMin(mobilePhone);
        } else {
            houseBudgetInfoPos = iHouseBudgetInfoMapper.queryBudgetAmountDetailOfSelf(mobilePhone);
        }
        List<HouseBudgetInfoVo> houseBudgetInfoVos =
                BeanMapperUtil.mapToList(houseBudgetInfoPos, HouseBudgetInfoVo.class);
        return houseBudgetInfoVos;
    }

    private void checkRoomInfo(HouseDetailInfoVo houseDetailInfoVo) {
        if (houseDetailInfoVo == null) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR);
        }
        if (StringUtils.isBlank(houseDetailInfoVo.getHouseCode())) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "房间编码不能为空");
        }
        if (houseDetailInfoVo.getRoomType() == null) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "房间类型不能为空");
        }
    }

    private void checkRoomTypeInfo(HouseDetailInfoVo houseDetailInfoVo, HouseInfoPo houseInfoPo) {
        Example example = new Example(HouseDetailInfoPo.class);
        example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
                .andEqualTo("roomType", houseDetailInfoVo.getRoomType());
        int roomCount = iHouseDetailInfoMapper.selectCountByExample(example);
        boolean flag = false;
        String error = null;
        switch (houseDetailInfoVo.getRoomType()) {
            case 0:
                flag = houseInfoPo.getBedroom() == null || houseInfoPo.getBedroom() < (roomCount + 1);
                error = flag ? "房间卧室信息不可添加" : null;
                break;
            case 1:
                flag = houseInfoPo.getLivingroom() == null || houseInfoPo.getLivingroom() < (roomCount + 1);
                error = flag ? "房间客厅信息不可添加" : null;
                break;
            case 2:
                flag = houseInfoPo.getKitchen() == null || houseInfoPo.getKitchen() < (roomCount + 1);
                error = flag ? "房间厨房信息不可添加" : null;
                break;
            case 3:
                flag = houseInfoPo.getToilet() == null || houseInfoPo.getToilet() < (roomCount + 1);
                error = flag ? "房间卫生间信息不可添加" : null;
                break;
            case 4:
                flag = houseInfoPo.getBalcony() == null || houseInfoPo.getBalcony() < (roomCount + 1);
                error = flag ? "房间阳台信息不可添加" : null;
                break;
            default:
                break;
        }
        if (flag) {
            throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, error);
        }
    }
}
