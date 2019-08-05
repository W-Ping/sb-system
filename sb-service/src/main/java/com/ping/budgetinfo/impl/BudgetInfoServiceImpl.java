package com.ping.budgetinfo.impl;

import com.ping.BaseService;
import com.ping.budgetinfo.IBudgetInfoService;
import com.ping.co.BudgetInfoCo;
import com.ping.co.SearchCo;
import com.ping.constant.ResultEnum;
import com.ping.constant.SysConstant;
import com.ping.exception.OptionsException;
import com.ping.exception.ValidateException;
import com.ping.mapper.IBudgetClassifyMapper;
import com.ping.mapper.IBudgetInfoMapper;
import com.ping.po.house.BudgetClassifyInfoPo;
import com.ping.po.house.BudgetInfoPo;
import com.ping.utils.BeanMapperUtil;
import com.ping.vo.hosue.BudgetClassifyInfoVo;
import com.ping.vo.hosue.BudgetInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lwp
 * @create 2019/07/14 10:35
 */
@Service
public class BudgetInfoServiceImpl extends BaseService implements IBudgetInfoService {
	@Autowired
	private IBudgetInfoMapper iBudgetInfoMapper;
	@Autowired
	private IBudgetClassifyMapper iBudgetClassifyMapper;

	/**
	 * @param budgetClassifyInfoVo
	 * @return
	 */
	@Override
	public List<BudgetClassifyInfoVo> getBudgetClassifyInfo(BudgetClassifyInfoVo budgetClassifyInfoVo) {
		Example example = new Example(BudgetClassifyInfoPo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", SysConstant.STATUS_0);
		if (budgetClassifyInfoVo != null && StringUtils.isNotBlank(budgetClassifyInfoVo.getFatherClassifyCode())) {
			criteria.andEqualTo("fatherClassifyCode", budgetClassifyInfoVo.getFatherClassifyCode());
		}
		if (budgetClassifyInfoVo != null && StringUtils.isNotBlank(budgetClassifyInfoVo.getClassifyCode())) {
			criteria.andEqualTo("classifyCode", budgetClassifyInfoVo.getClassifyCode());
		}
		List<BudgetClassifyInfoPo> budgetClassifyInfoPos = iBudgetClassifyMapper.selectByExample(example);
		List<BudgetClassifyInfoVo> budgetClassifyInfoVos = BeanMapperUtil.mapToList(budgetClassifyInfoPos, BudgetClassifyInfoVo.class);
		return budgetClassifyInfoVos;
	}

	/**
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getBudgetClassifyInfoListMap() {
		List<Map<String, Object>> resultMapList = new ArrayList<>();
		List<BudgetClassifyInfoVo> budgetClassifyInfos = getBudgetClassifyInfo(null);
		if (!CollectionUtils.isEmpty(budgetClassifyInfos)) {
			List<BudgetClassifyInfoVo> fatherClassifyInfos = budgetClassifyInfos.stream().filter(v -> "0".equals(v.getFatherClassifyCode())).collect(Collectors.toList());
			Map<String, List<BudgetClassifyInfoVo>> subFatherClassifyInfoMap = budgetClassifyInfos.stream().filter(v -> !"0".equals(v.getFatherClassifyCode()))
					.collect(Collectors.groupingBy(BudgetClassifyInfoVo::getFatherClassifyCode));
			for (final BudgetClassifyInfoVo budgetClassifyInfoVo : fatherClassifyInfos) {
				Map<String, Object> resultMap = new HashMap<>();
				String classifyCode = budgetClassifyInfoVo.getClassifyCode();
				List<BudgetClassifyInfoVo> budgetClassifyInfoVos = subFatherClassifyInfoMap != null && !subFatherClassifyInfoMap.isEmpty() ? subFatherClassifyInfoMap.get(classifyCode) : null;
				List<String> subList = new ArrayList<>();
				if (!CollectionUtils.isEmpty(budgetClassifyInfoVos)) {
					subList = budgetClassifyInfoVos.stream().map(BudgetClassifyInfoVo::getClassifyName).collect(Collectors.toList());
				}
				resultMap.put("name", budgetClassifyInfoVo.getClassifyName());
				resultMap.put("sub", subList);
				resultMapList.add(resultMap);
			}
		}
		return resultMapList;
	}

	@Override
	public boolean saveBudgetInfo(final BudgetInfoVo budgetInfoVo) {
//		HouseInfoVo houseInfo = iHouseInfoService.getHouseInfo(budgetInfoVo.getMobilePhone());
//		if (houseInfo == null) {
//			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "请先填写房屋信息");
//		}
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

	/**
	 * @param budgetInfoVo
	 * @return
	 */
	@Override
	public boolean saveCopyBudget(final BudgetInfoVo budgetInfoVo) {
		if (StringUtils.isBlank(budgetInfoVo.getBudgetCode())) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "复制材料信息不存在");
		}
		if (StringUtils.isBlank(budgetInfoVo.getBudgetName())) {
			throw new ValidateException(ResultEnum.REQ_PARAMETER_ERROR, "复制新材料名称不不能为空");
		}
		Example example = new Example(BudgetInfoPo.class);
		example.createCriteria().andEqualTo("status", SysConstant.STATUS_0)
				.andEqualTo("budgetCode", budgetInfoVo.getBudgetCode());
		BudgetInfoPo budgetInfoPo = iBudgetInfoMapper.selectOneByExample(example);
		if (budgetInfoPo == null) {
			throw new OptionsException(ResultEnum.DATA_NOT_EXISTS, "复制材料信息已不存在");
		}
		BudgetInfoPo newPo = new BudgetInfoPo();
		newPo.setBudgetCode(super.getUniqueId(SysConstant.UNIQUEID_BUDGET_PRIFIX));
		newPo.setBudgetName(budgetInfoVo.getBudgetName());
		newPo.setBudgetAmount(budgetInfoPo.getBudgetAmount());
		newPo.setActualCost(budgetInfoPo.getActualCost());
		newPo.setMaxCost(budgetInfoPo.getMaxCost());
		newPo.setMinCost(budgetInfoPo.getMinCost());
		newPo.setMobilePhone(budgetInfoPo.getMobilePhone());
		newPo.setRemark("复制于" + budgetInfoPo.getBudgetName());
		return iBudgetInfoMapper.insertSelective(newPo) > 0;
	}

	@Override
	public boolean deleteBudgetInfoById(final Long id) {
		return iBudgetInfoMapper.deleteByPrimaryKey(id) >= 0;
	}

	/**
	 * @return
	 */
	@Override
	public List<BudgetInfoVo> queryBudgetInfoList(final BudgetInfoCo budgetInfoCo) {
		if (StringUtils.isBlank(budgetInfoCo.getMobilePhone())) {
			return null;
		}
		List<String> ignoreBudgetCodes = budgetInfoCo.getIgnoreBudgetCodes();
		Example example = new Example(BudgetInfoPo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", SysConstant.STATUS_0);
		criteria.andEqualTo("mobilePhone", budgetInfoCo.getMobilePhone());
		if (!CollectionUtils.isEmpty(ignoreBudgetCodes)) {
			criteria.andNotIn("budgetCode", ignoreBudgetCodes);
		}
		example.setOrderByClause(" update_time desc");
		List<BudgetInfoPo> budgetInfoPos = iBudgetInfoMapper.selectByExample(example);
		List<BudgetInfoVo> budgetInfoVos = BeanMapperUtil.mapToList(budgetInfoPos, BudgetInfoVo.class);
		return budgetInfoVos;
	}


	/**
	 * @param budgetCodes
	 * @return
	 */
	@Override
	public List<BudgetInfoVo> queryBudgetInfosByCodes(final List<String> budgetCodes) {
		Example example = new Example(BudgetInfoPo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", SysConstant.STATUS_0);
		criteria.andIn("budgetCode", budgetCodes);
		List<BudgetInfoPo> budgetInfoPos = iBudgetInfoMapper.selectByExample(example);
		List<BudgetInfoVo> budgetInfoVos = BeanMapperUtil.mapToList(budgetInfoPos, BudgetInfoVo.class);
		return budgetInfoVos;
	}

	@Override
	public List<BudgetInfoVo> searchByKeyword(SearchCo searchCo) {
		List<BudgetInfoPo> budgetInfoPos = iBudgetInfoMapper.searchByKeyword(searchCo);
		List<BudgetInfoVo> budgetInfoVos = BeanMapperUtil.mapToList(budgetInfoPos, BudgetInfoVo.class);
		return budgetInfoVos;
	}
}
