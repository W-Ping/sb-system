package com.ping.budgetinfo.impl;

import com.ping.BaseService;
import com.ping.budgetinfo.IBudgetInfoService;
import com.ping.co.BudgetInfoCo;
import com.ping.constant.ResultEnum;
import com.ping.constant.SysConstant;
import com.ping.exception.OptionsException;
import com.ping.exception.ValidateException;
import com.ping.houseinfo.IHouseInfoService;
import com.ping.mapper.IBudgetInfoMapper;
import com.ping.po.house.BudgetInfoPo;
import com.ping.utils.BeanMapperUtil;
import com.ping.vo.hosue.BudgetInfoVo;
import com.ping.vo.hosue.HouseInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author lwp
 * @create 2019/07/14 10:35
 */
@Service
public class BudgetInfoServiceImpl extends BaseService implements IBudgetInfoService {
	@Autowired
	private IBudgetInfoMapper iBudgetInfoMapper;
	@Autowired
	private IHouseInfoService iHouseInfoService;

	@Override
	public boolean saveBudgetInfoBatch(final List<BudgetInfoVo> budgetInfoVos) {
		List<BudgetInfoPo> budgetInfoPos = BeanMapperUtil.mapToList(budgetInfoVos, BudgetInfoPo.class);
		if (CollectionUtils.isEmpty(budgetInfoPos)) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR);
		}
		budgetInfoPos.forEach(v -> {
			v.setBudgetCode(super.getUniqueId(SysConstant.UNIQUEID_BUDGET_PRIFIX));
		});
		int count = iBudgetInfoMapper.insertList(budgetInfoPos);
		return count > 0;
	}

	@Override
	public boolean saveBudgetInfo(final BudgetInfoVo budgetInfoVo) {
		HouseInfoVo houseInfo = iHouseInfoService.getHouseInfo(budgetInfoVo.getMobilePhone());
		if (houseInfo == null) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "请先填写房屋信息");
		}
		String budgetCode = budgetInfoVo.getBudgetCode();
		if (StringUtils.isBlank(budgetCode)) {
			budgetInfoVo.setBudgetCode(super.getUniqueId(SysConstant.UNIQUEID_BUDGET_PRIFIX));
			BudgetInfoPo addPo = BeanMapperUtil.map(budgetInfoVo, BudgetInfoPo.class);
			return iBudgetInfoMapper.insertSelective(addPo) > 0;
		} else {
			Example example = new Example(BudgetInfoPo.class);
			example.createCriteria().andEqualTo("mobilePhone", budgetInfoVo.getMobilePhone())
					.andEqualTo("status", SysConstant.STATUS_0).andEqualTo("budgetCode", budgetCode);
			List<BudgetInfoPo> budgetInfoPos = iBudgetInfoMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(budgetInfoPos)) {
				throw new OptionsException(ResultEnum.UPDATE_BUDGETINFO_FAILED);
			}
			BudgetInfoPo budgetInfoPo = budgetInfoPos.get(0);
			BudgetInfoPo updatePo = BeanMapperUtil.map(budgetInfoVo, BudgetInfoPo.class);
			updatePo.setId(budgetInfoPo.getId());
			return iBudgetInfoMapper.updateByPrimaryKeySelective(updatePo) >= 0;
		}
	}

	@Override
	public boolean deleteBudgetInfoById(final Long id) {
		return iBudgetInfoMapper.deleteByPrimaryKey(id) >= 0;
	}

	@Override
	public List<BudgetInfoVo> queryBudgetInfoListPage(final BudgetInfoCo budgetInfoVo) {
		return null;
	}

	/**
	 * @param budgetInfoVo
	 * @return
	 */
	@Override
	public List<BudgetInfoVo> queryBudgetInfoList(final BudgetInfoCo budgetInfoVo) {
		if (StringUtils.isBlank(budgetInfoVo.getMobilePhone())) {
			return null;
		}
		Example example = new Example(BudgetInfoPo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", SysConstant.STATUS_0);
		criteria.andEqualTo("mobilePhone", budgetInfoVo.getMobilePhone());
		example.setOrderByClause(" update_time desc");
		List<BudgetInfoPo> budgetInfoPos = iBudgetInfoMapper.selectByExample(example);
		List<BudgetInfoVo> budgetInfoVos = BeanMapperUtil.mapToList(budgetInfoPos, BudgetInfoVo.class);
		return budgetInfoVos;
	}

	@Override
	public BudgetInfoVo getBudgetInfo(final BudgetInfoCo budgetInfoVo) {
		return null;
	}
}
