package com.baidu.fbu.asset.mapper;

import com.baidu.fbu.asset.entity.vo.AssetDetailVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface AssetDetailMapper {

    int insertSelective(AssetDetailVo assetDetail);

    int updateSelective(AssetDetailVo assetDetail);

    int deleteByPrimaryKey(Long id);

    AssetDetailVo selectByPrimaryKey(Long id);

    long countSelective(Map<String, Object> map);

    List<Object> selectSelective(Map<String, Object> map);

    void batchUpdateByParam(Map<String, Object> map);

    void batchUpdateByRelatedId(Map<String, Object> map);

    void removeAssetDetailsNotBuyInAnAssetPlan(Map<String, Object> map);

    void batchUpdateByLoanIds(Map<String, Object> map);

    List<AssetDetailVo> exportAssets(AssetDetailVo assetDetail);

    void updateTransferPrincipalInterest(Map<String, Object> checkResult);

    Map<String, Object> checkLoanId(Map<String, Object> paramMap);

    long joinCountSelective(Map<String, Object> map);

    List<Object> joinSelectSelective(Map<String, Object> map);

    Map<String, Object> sumInterestInquiry(Long apId);

    void deleteApId(Map<String, String> paramMap);

    void deleteApIdBatch(Map<String, String> paramMap);

    List<Object> findLoanIdByParam(Map<String, Object> map);

}