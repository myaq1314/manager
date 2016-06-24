package com.baidu.fbu.asset.service;

import java.sql.SQLException;
import java.util.Map;

import com.baidu.fbu.asset.entity.AssetManager;

public interface AssetManagerService {
    public void add(AssetManager assetManager) throws SQLException;
    public void update(AssetManager assetManager) throws SQLException;
    public void deleteById( Long id ) throws SQLException;
    public void logicDeleteById(Long id) throws SQLException;
    public void deleteAssetManager( AssetManager assetManager ) throws SQLException, Exception;
    public AssetManager findById( Long id ) throws SQLException;
    public Map<String, Object> findByParam( AssetManager assetManager, int startRow, int pageSize ) throws SQLException;
    public Map<String, Object> findByParam( AssetManager assetManager ) throws SQLException;
    public Long countByParam( AssetManager assetManager ) throws SQLException;
    public Map<String, Object> findAssetManagerWithRelatedInfo(Long id) throws SQLException;

}