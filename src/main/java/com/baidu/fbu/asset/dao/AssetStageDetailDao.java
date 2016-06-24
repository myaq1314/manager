package com.baidu.fbu.asset.dao;

import com.baidu.fbu.asset.entity.AssetStageDetail;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public interface AssetStageDetailDao {

    void addByParam(AssetStageDetail assetStageDetail) throws SQLException;

    void updateByParam(AssetStageDetail assetStageDetail) throws SQLException;

    void deleteById(Long id) throws SQLException;

    AssetStageDetail findById(Long id) throws SQLException;

    long countByParam(AssetStageDetail assetStageDetail);

    List<Object> findByParam(Map<String, Object> map);

    List<AssetStageDetail> exportAssetstages(List<String> stageList);

}