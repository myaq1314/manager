package com.baidu.fbu.asset.mapper;

import com.baidu.fbu.asset.entity.AssetManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface AssetManagerMapper {

    int insertSelective(AssetManager assetManager);

    int updateSelective(AssetManager assetManager);

    int deleteByPrimaryKey(Long id);

    AssetManager selectByPrimaryKey(Long id);

    long countSelective(AssetManager assetManager);

    List<Object> selectSelective(Map<String, Object> map);

    void logicDeleteById(Map<String, Object> map);

}