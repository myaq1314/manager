package com.baidu.fbu.asset.dao.impl;

import com.baidu.fbu.asset.dao.AssetPlanDao;
import com.baidu.fbu.asset.entity.AssetPlan;
import com.baidu.fbu.asset.mapper.AssetPlanMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AssetPlanDaoImpl implements AssetPlanDao {

    @Resource
    private AssetPlanMapper assetPlanMapper;

    public void addByParam(AssetPlan assetPlan) throws SQLException {
        assetPlanMapper.insertSelective(assetPlan);
    }

    public String getPlanName(Long apId) {
        return assetPlanMapper.getPlanName(apId);
    }

    public void updateByParam(AssetPlan assetPlan) throws SQLException {
        assetPlanMapper.updateSelective(assetPlan);
    }

    public void deleteById(Long id) throws SQLException {
        assetPlanMapper.deleteByPrimaryKey(id);
    }

    public AssetPlan findById(Long id) throws SQLException {
        return assetPlanMapper.selectByPrimaryKey(id);
    }

    public List<AssetPlan> findByAssetManageId(Long amId) throws SQLException {
        List<AssetPlan> assetPlanList = assetPlanMapper.selectByManagerId(amId);
        return assetPlanList;
    }

    public long countByParam(AssetPlan assetPlan) {
        return assetPlanMapper.countSelective(assetPlan);
    }

    public List<Object> findByParam(Map<String, Object> map) {
        return assetPlanMapper.selectSelective(map);
    }

    public long joinCountByParam(AssetPlan assetPlan) {
        return assetPlanMapper.joinCountSelective(assetPlan);
    }

    public List<Object> joinFindByParam(Map<String, Object> map) {
        return assetPlanMapper.joinSelectSelective(map);
    }

    public void batchUpdateByParam(Map<String, Object> map) {
        assetPlanMapper.batchUpdateByParam(map);
    }

    public List<Object> findIdAndNameOfAssetManager() {
        return assetPlanMapper.findIdAndNameOfAssetManager();
    }

}