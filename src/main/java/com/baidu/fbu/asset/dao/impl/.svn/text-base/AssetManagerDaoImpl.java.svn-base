package com.baidu.fbu.asset.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.baidu.fbu.asset.dao.AssetManagerDao;
import com.baidu.fbu.asset.entity.AssetManager;
import com.baidu.fbu.asset.mapper.AssetManagerMapper;

@Repository
public class AssetManagerDaoImpl implements AssetManagerDao {
    @Resource
    private AssetManagerMapper assetManagerMapper;

    public void addByParam( AssetManager assetManager ) throws SQLException {
        assetManagerMapper.insertSelective(assetManager);
    }

    public void updateByParam(AssetManager assetManager) throws SQLException {
        assetManagerMapper.updateSelective(assetManager);
    }

    public void deleteById( Long id ) throws SQLException {
        assetManagerMapper.deleteByPrimaryKey(id);
    }

    public void logicDeleteById(Map<String, Object> map) throws SQLException {
        assetManagerMapper.logicDeleteById(map);
    }

    public AssetManager findById( Long id ) throws SQLException {
        return (AssetManager) assetManagerMapper.selectByPrimaryKey(id);
    }

    public long countByParam(AssetManager assetManager) {
        return assetManagerMapper.countSelective( assetManager );
    }

    public List<Object> findByParam(Map<String, Object> map) {
        return assetManagerMapper.selectSelective(map);
    }

}