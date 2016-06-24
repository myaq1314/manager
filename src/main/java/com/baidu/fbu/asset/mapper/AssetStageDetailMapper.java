package com.baidu.fbu.asset.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetStageDetail;
import com.baidu.fbu.asset.entity.vo.AssetStageDetailVo;

@Component
public interface AssetStageDetailMapper {
    int deleteByPrimaryKey( Long id );
    AssetStageDetail selectByPrimaryKey( Long id );
    int insertSelective(AssetStageDetail assetStageDetail);
    int updateSelective(AssetStageDetail assetStageDetail);
    long countSelective(AssetStageDetail assetStageDetail);
    List<Object> selectSelective( Map<String, Object> map );
    List<AssetStageDetailVo> exportAssetstages(List<String> list);

}