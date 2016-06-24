package com.baidu.fbu.asset.mapper;

import com.baidu.fbu.asset.entity.AssetStageDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface AssetStageDetailMapper {

    int deleteByPrimaryKey(Long id);

    AssetStageDetail selectByPrimaryKey(Long id);

    int insertSelective(AssetStageDetail assetStageDetail);

    int updateSelective(AssetStageDetail assetStageDetail);

    long countSelective(AssetStageDetail assetStageDetail);

    List<Object> selectSelective(Map<String, Object> map);

    List<AssetStageDetail> exportAssetstages(List<String> list);

}