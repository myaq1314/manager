package com.baidu.fbu.asset.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.baidu.fbu.asset.entity.AssetDetail;

public interface AssetDetailService {
    public void add(AssetDetail assetDetail) throws SQLException;
    public void update(AssetDetail assetDetail) throws SQLException;
    public void deleteById( Long id ) throws SQLException;
    public AssetDetail findById( Long id ) throws SQLException;
    public Map<String, Object> findByParam( AssetDetail assetDetail,
            int startRow, int pageSize ) throws SQLException;
    public Map<String, Object> findByParam( AssetDetail assetDetail ) throws SQLException;
    public Map<String, Object> findByParamWithSumInfo( AssetDetail assetDetail,
            int startRow, int pageSize ) throws SQLException;
    public Long countByParam( AssetDetail assetDetail ) throws SQLException;
    public void batchAddAssetDetailToAssetPlan(String ids, Long apId) throws SQLException;
    public void addAllAssetDetailToAssetPlan( AssetDetail assetDetail, Long apIdParam ) throws SQLException;
    public void deleteAssetDetailInAssetPlan(AssetDetail assetDetail) throws SQLException;
    public void deleteAllAssetDetailInAssetPlan(AssetDetail assetDetail) throws SQLException;
    public void batchDeleteAssetDetailInAssetPlan(String ids) throws SQLException;
    public void sellAsset(Long apId, List<Object> idList) throws Exception;
    public Map<String, Object> sumInterestInquiry(Long apId) throws SQLException;
    public Map<String, Object> findAssetDetailWithRelatedInfo(Long id) throws SQLException;
    // 导出excel，SQL查询语句
    public HSSFWorkbook generateAssetToExcel(AssetDetail assetDetail);
    public int excelInAsset(Long dealId, File f);

}