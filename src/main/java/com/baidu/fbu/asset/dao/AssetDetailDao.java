package com.baidu.fbu.asset.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.baidu.fbu.asset.entity.AssetDetail;
import com.baidu.fbu.asset.entity.vo.AssetDetailVo;

@Component
public interface AssetDetailDao {
    public void addByParam(AssetDetail assetDetail) throws SQLException;
    public void updateByParam(AssetDetail assetDetail) throws SQLException;
    public void deleteById(Long id) throws SQLException;
    public AssetDetail findById(Long id) throws SQLException;
    long countByParam(Map<String, Object> map);
    List<Object> findByParam(Map<String, Object> map);
    void batchUpdateAssetDetailByParam(Map<String, Object> map);
    void batchUpdateAssetDetailByAssetPlanId(Map<String, Object> map);
    void removeAssetDetailsNotBuyInAnAssetPlan(Map<String, Object> map);
    void batchUpdateAssetDetailByLoanIds(Map<String, Object> map);
    // 计算 资产计划 的几种本息的总金额
    public Map<String, Object> sumInterestInquiry(Long apId);

    // 导出资产excel，查询SQL
    List<AssetDetailVo> exportAssets(AssetDetail assetDetail);
    public void updateTransferPrincipalInterest(Map<String, Object> checkResult);
    public Map<String, Object> checkLoanId(Map<String, Object> paramMap);
    public void deleteApId(Map<String, String> paramMap);
    public void deleteApIdBatch(Map<String, String> paramMap);
    List<Object> findLoanIdByParam(Map<String, Object> paramMap);

}