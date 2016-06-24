package com.baidu.fbu.asset.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetStageDetail;
import com.baidu.fbu.asset.entity.vo.AssetStageDetailVo;

@Component
public interface AssetStageDetailDao {
    public void addByParam(AssetStageDetail assetStageDetail) throws SQLException;
    public void updateByParam(AssetStageDetail assetStageDetail) throws SQLException;
    public void deleteById( Long id ) throws SQLException;
    public AssetStageDetail findById( Long id ) throws SQLException;
    long countByParam(AssetStageDetail assetStageDetail);
    List<Object> findByParam( Map<String, Object> map );
    List<AssetStageDetailVo> exportAssetstages(List<String> stageList);

}