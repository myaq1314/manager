package com.baidu.fbu.asset.service;

import com.baidu.fbu.asset.entity.AssetStageDetail;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.sql.SQLException;
import java.util.Map;

public interface AssetStageDetailService {

    void add(AssetStageDetail assetStageDetail) throws SQLException;

    void update(AssetStageDetail assetStageDetail) throws SQLException;

    void deleteById(Long id) throws SQLException;

    AssetStageDetail findById(Long id) throws SQLException;

    Map<String, Object> findByParam(AssetStageDetail assetStageDetail,
                                    int startRow, int pageSize) throws SQLException;

    Map<String, Object> findByParam(AssetStageDetail assetStageDetail) throws SQLException;

    Long countByParam(AssetStageDetail assetStageDetail) throws SQLException;

    Map<String, Object> findAssetStageDetailByLoanIds(String ids) throws SQLException;

    HSSFWorkbook generateAssetStageToExcel(String ids);

}