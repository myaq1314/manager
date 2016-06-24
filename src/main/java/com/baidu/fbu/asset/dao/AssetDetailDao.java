package com.baidu.fbu.asset.dao;

import com.baidu.fbu.asset.entity.vo.AssetDetailVo;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public interface AssetDetailDao {

    void addByParam(AssetDetailVo assetDetail) throws SQLException;

    void updateByParam(AssetDetailVo assetDetail) throws SQLException;

    void deleteById(Long id) throws SQLException;

    AssetDetailVo findById(Long id) throws SQLException;

    long countByParam(Map<String, Object> map);

    List<Object> findByParam(Map<String, Object> map);

    void batchUpdateAssetDetailByParam(Map<String, Object> map);

    void batchUpdateAssetDetailByAssetPlanId(Map<String, Object> map);

    void removeAssetDetailsNotBuyInAnAssetPlan(Map<String, Object> map);

    void batchUpdateAssetDetailByLoanIds(Map<String, Object> map);

    // 计算 资产计划 的几种本息的总金额
    Map<String, Object> sumInterestInquiry(Long apId);

    // 导出资产excel，查询SQL
    List<AssetDetailVo> exportAssets(AssetDetailVo assetDetail);

    void updateTransferPrincipalInterest(Map<String, Object> checkResult);

    Map<String, Object> checkLoanId(Map<String, Object> paramMap);

    void deleteApId(Map<String, String> paramMap);

    void deleteApIdBatch(Map<String, String> paramMap);

    List<Object> findLoanIdByParam(Map<String, Object> paramMap);

}