package com.baidu.fbu.asset.dao;

import com.baidu.fbu.asset.entity.AssetPlan;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public interface AssetPlanDao {

    void addByParam(AssetPlan assetPlan) throws SQLException;

    void updateByParam(AssetPlan assetPlan) throws SQLException;

    void deleteById(Long id) throws SQLException;

    AssetPlan findById(Long id) throws SQLException;

    List<AssetPlan> findByAssetManageId(Long amId) throws SQLException;

    long countByParam(AssetPlan assetPlan);

    List<Object> findByParam(Map<String, Object> map);

    long joinCountByParam(AssetPlan assetPlan);

    List<Object> joinFindByParam(Map<String, Object> map);

    void batchUpdateByParam(Map<String, Object> map);

    List<Object> findIdAndNameOfAssetManager();

    String getPlanName(Long apId);

}