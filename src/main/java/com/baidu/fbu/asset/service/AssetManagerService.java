package com.baidu.fbu.asset.service;

import com.baidu.fbu.asset.entity.AssetManager;

import java.sql.SQLException;
import java.util.Map;

public interface AssetManagerService {

    void add(AssetManager assetManager) throws SQLException;

    void update(AssetManager assetManager) throws SQLException;

    void deleteById(Long id) throws SQLException;

    void logicDeleteById(Long id) throws SQLException;

    void deleteAssetManager(AssetManager assetManager) throws SQLException, Exception;

    AssetManager findById(Long id) throws SQLException;

    Map<String, Object> findByParam(AssetManager assetManager, int startRow, int pageSize) throws SQLException;

    Map<String, Object> findByParam(AssetManager assetManager) throws SQLException;

    Long countByParam(AssetManager assetManager) throws SQLException;

    Map<String, Object> findAssetManagerWithRelatedInfo(Long id) throws SQLException;

}