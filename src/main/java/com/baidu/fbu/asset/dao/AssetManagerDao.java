package com.baidu.fbu.asset.dao;

import com.baidu.fbu.asset.entity.AssetManager;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public interface AssetManagerDao {

    void addByParam(AssetManager assetManager) throws SQLException;

    void updateByParam(AssetManager assetManager) throws SQLException;

    void deleteById(Long id) throws SQLException;

    void logicDeleteById(Map<String, Object> map) throws SQLException;

    AssetManager findById(Long id) throws SQLException;

    long countByParam(AssetManager assetManager);

    List<Object> findByParam(Map<String, Object> map);

}