package com.baidu.fbu.asset.dao.impl;

import com.baidu.fbu.asset.dao.AssetDetailDao;
import com.baidu.fbu.asset.entity.vo.AssetDetailVo;
import com.baidu.fbu.asset.mapper.AssetDetailMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AssetDetailDaoImpl implements AssetDetailDao {

    @Resource
    private AssetDetailMapper assetDetailMapper;

    public void addByParam(AssetDetailVo assetDetail) throws SQLException {
        assetDetailMapper.insertSelective(assetDetail);
    }

    public void updateByParam(AssetDetailVo assetDetail) throws SQLException {
        assetDetailMapper.updateSelective(assetDetail);
    }

    public void deleteById(Long id) throws SQLException {
        assetDetailMapper.deleteByPrimaryKey(id);
    }

    public AssetDetailVo findById(Long id) throws SQLException {
        return assetDetailMapper.selectByPrimaryKey(id);
    }

    public long countByParam(Map<String, Object> map) {
        return assetDetailMapper.joinCountSelective(map);
    }

    public List<Object> findByParam(Map<String, Object> map) {
        return assetDetailMapper.joinSelectSelective(map);
    }

    // 计算 资产计划 的几种本息的总金额
    public Map<String, Object> sumInterestInquiry(Long apId) {
        return assetDetailMapper.sumInterestInquiry(apId);
    }

    public void batchUpdateAssetDetailByParam(Map<String, Object> map) {
        assetDetailMapper.batchUpdateByParam(map);
    }

    public void batchUpdateAssetDetailByAssetPlanId(Map<String, Object> map) {
        assetDetailMapper.batchUpdateByRelatedId(map);
    }

    public void removeAssetDetailsNotBuyInAnAssetPlan(Map<String, Object> map) {
        assetDetailMapper.removeAssetDetailsNotBuyInAnAssetPlan(map);
    }

    public void batchUpdateAssetDetailByLoanIds(Map<String, Object> map) {
        assetDetailMapper.batchUpdateByLoanIds(map);
    }

    public List<AssetDetailVo> exportAssets(AssetDetailVo assetDetail) {
        return assetDetailMapper.exportAssets(assetDetail);
    }

    public void updateTransferPrincipalInterest(Map<String, Object> checkResult) {
        assetDetailMapper.updateTransferPrincipalInterest(checkResult);
    }

    public Map<String, Object> checkLoanId(Map<String, Object> paramMap) {
        return assetDetailMapper.checkLoanId(paramMap);
    }

    public void deleteApId(Map<String, String> paramMap) {
        assetDetailMapper.deleteApId(paramMap);
    }

    public void deleteApIdBatch(Map<String, String> paramMap) {
        assetDetailMapper.deleteApIdBatch(paramMap);
    }

    public List<Object> findLoanIdByParam(Map<String, Object> map) {
        return assetDetailMapper.findLoanIdByParam(map);
    }

}