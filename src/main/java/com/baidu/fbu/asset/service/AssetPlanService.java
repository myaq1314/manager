package com.baidu.fbu.asset.service;

import java.sql.SQLException;
import java.util.Map;

import com.baidu.fbu.asset.entity.AssetPlan;

public interface AssetPlanService {
    public void add(AssetPlan assetPlan) throws SQLException;
    public void update(AssetPlan assetPlan) throws SQLException;
    public void deleteById( Long id ) throws SQLException;
    public AssetPlan findById( Long id ) throws SQLException;
    public Map<String, Object> findByParam( AssetPlan assetPlan, int startRow, int pageSize ) throws SQLException;
    public Map<String, Object> findByParam( AssetPlan assetPlan ) throws SQLException;
    public Long countByParam( AssetPlan assetPlan ) throws SQLException;
    public void cancel( Long id ) throws SQLException;
    public Map<String, Object> findAssetPlanWithRelatedInfo( Long id ) throws SQLException;
    public String getPlanName(Long apId);

}