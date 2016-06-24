package com.baidu.fbu.asset.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetPlan;

@Component
public interface AssetPlanMapper {
    int insertSelective(AssetPlan assetPlan);
    int updateSelective(AssetPlan assetPlan);
    int deleteByPrimaryKey( Long id );
    AssetPlan selectByPrimaryKey( Long id );
    long countSelective(AssetPlan assetPlan);
    List<Object> selectSelective( Map<String, Object> map );
    long joinCountSelective(AssetPlan assetPlan);
    List<Object> joinSelectSelective( Map<String, Object> map );
    void batchUpdateByParam( Map<String, Object> map );
    List<AssetPlan> selectByManagerId( Long amId );
    List<Object> findIdAndNameOfAssetManager();
    String getPlanName( Long apId );
}