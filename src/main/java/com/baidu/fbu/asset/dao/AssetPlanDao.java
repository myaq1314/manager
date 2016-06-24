package com.baidu.fbu.asset.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetPlan;

@Component
public interface AssetPlanDao {
    public void addByParam(AssetPlan assetPlan) throws SQLException;
    public void updateByParam(AssetPlan assetPlan) throws SQLException;
    public void deleteById(Long id) throws SQLException;
    public AssetPlan findById(Long id) throws SQLException;
    public List<AssetPlan> findByAssetManageId( Long amId ) throws SQLException;
    long countByParam(AssetPlan assetPlan);
    List<Object> findByParam( Map<String, Object> map );
    long joinCountByParam(AssetPlan assetPlan);
    List<Object> joinFindByParam( Map<String, Object> map );
    void batchUpdateByParam( Map<String, Object> map );
    List<Object> findIdAndNameOfAssetManager();
    public String getPlanName(Long apId);

}