package com.baidu.fbu.asset.service;

import com.baidu.fbu.asset.entity.AssetPlan;

import java.sql.SQLException;
import java.util.Map;

public interface AssetPlanService {

    void add(AssetPlan assetPlan) throws SQLException;

    void update(AssetPlan assetPlan) throws SQLException;

    void deleteById(Long id) throws SQLException;

    AssetPlan findById(Long id) throws SQLException;

    Map<String, Object> findByParam(AssetPlan assetPlan, int startRow, int pageSize) throws SQLException;

    Map<String, Object> findByParam(AssetPlan assetPlan) throws SQLException;

    Long countByParam(AssetPlan assetPlan) throws SQLException;

    void cancel(Long id) throws SQLException;

    Map<String, Object> findAssetPlanWithRelatedInfo(Long id) throws SQLException;

    String getPlanName(Long apId);

}