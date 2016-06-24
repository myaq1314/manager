package com.baidu.fbu.asset.service;

import com.baidu.fbu.asset.entity.vo.AssetDetailVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AssetDetailService {

    void add(AssetDetailVo assetDetail) throws SQLException;

    void update(AssetDetailVo assetDetail) throws SQLException;

    void deleteById(Long id) throws SQLException;

    AssetDetailVo findById(Long id) throws SQLException;

    Map<String, Object> findByParam(AssetDetailVo assetDetail, int startRow, int pageSize) throws SQLException;

    Map<String, Object> findByParam(AssetDetailVo assetDetail) throws SQLException;

    Map<String, Object> findByParamWithSumInfo(AssetDetailVo assetDetail,
                                               int startRow, int pageSize) throws SQLException;

    Long countByParam(AssetDetailVo assetDetail) throws SQLException;

    void batchAddAssetDetailToAssetPlan(String ids, Long apId) throws SQLException;

    void addAllAssetDetailToAssetPlan(AssetDetailVo assetDetail, Long apIdParam) throws SQLException;

    void deleteAssetDetailInAssetPlan(AssetDetailVo assetDetail) throws SQLException;

    void deleteAllAssetDetailInAssetPlan(AssetDetailVo assetDetail) throws SQLException;

    void batchDeleteAssetDetailInAssetPlan(String ids) throws SQLException;

    void sellAsset(Long apId, List<Object> idList) throws Exception;

    Map<String, Object> sumInterestInquiry(Long apId) throws SQLException;

    Map<String, Object> findAssetDetailWithRelatedInfo(Long id) throws SQLException;

    // 导出excel，SQL查询语句
    HSSFWorkbook generateAssetToExcel(AssetDetailVo assetDetail);

    int excelInAsset(Long dealId, File f);

}