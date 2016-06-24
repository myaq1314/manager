package com.baidu.fbu.asset.dao.impl;

import com.baidu.fbu.asset.dao.AssetStageDetailDao;
import com.baidu.fbu.asset.entity.AssetStageDetail;
import com.baidu.fbu.asset.mapper.AssetStageDetailMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AssetStageDetailDaoImpl implements AssetStageDetailDao {

    @Resource
    private AssetStageDetailMapper assetStageDetailMapper;

    public void addByParam(AssetStageDetail assetStageDetail) throws SQLException {
        assetStageDetailMapper.insertSelective(assetStageDetail);
    }

    public void updateByParam(AssetStageDetail assetStageDetail) throws SQLException {
        assetStageDetailMapper.updateSelective(assetStageDetail);
    }

    public void deleteById(Long id) throws SQLException {
        assetStageDetailMapper.deleteByPrimaryKey(id);
    }

    public AssetStageDetail findById(Long id) throws SQLException {
        return assetStageDetailMapper.selectByPrimaryKey(id);
    }

    public long countByParam(AssetStageDetail assetStageDetail) {
        return assetStageDetailMapper.countSelective(assetStageDetail);
    }

    public List<Object> findByParam(Map<String, Object> map) {
        return assetStageDetailMapper.selectSelective(map);
    }

    public List<AssetStageDetail> exportAssetstages(List<String> stageList) {
        return assetStageDetailMapper.exportAssetstages(stageList);
    }

}