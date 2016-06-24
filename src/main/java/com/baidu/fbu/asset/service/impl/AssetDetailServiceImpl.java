package com.baidu.fbu.asset.service.impl;

import com.baidu.fbu.asset.constants.Constants;
import com.baidu.fbu.asset.dao.AssetDetailDao;
import com.baidu.fbu.asset.dao.AssetPlanDao;
import com.baidu.fbu.asset.entity.vo.AssetDetailVo;
import com.baidu.fbu.asset.service.AssetDetailService;
import com.baidu.fbu.asset.util.ExcelUtil;
import com.baidu.fbu.asset.util.IOUtil;
import com.baidu.fbu.asset.util.PageUtil;
import com.baidu.fbu.asset.util.Util;
import com.baidu.fbu.common.service.FormatService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("assetDetailService")
@Transactional
public class AssetDetailServiceImpl implements AssetDetailService {

    /**
     * log日志
     */
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AssetDetailServiceImpl.class);

    @Resource
    private AssetDetailDao assetDetailDao;

    @Resource
    private AssetPlanDao assetPlanDao;

    public void add(AssetDetailVo assetDetail) throws SQLException {
        assetDetail.setCreatetime(new Date(System.currentTimeMillis()));
        assetDetailDao.addByParam(assetDetail);
    }

    public void update(AssetDetailVo assetDetail) throws SQLException {
        assetDetailDao.updateByParam(assetDetail);
    }

    public void deleteById(Long id) throws SQLException {
        assetDetailDao.deleteById(id);
    }

    public AssetDetailVo findById(Long id) throws SQLException {
        return (AssetDetailVo) assetDetailDao.findById(id);
    }

    /**
     * 查询符合条件的记录的条数
     */
    public Long countByParam(AssetDetailVo assetDetail) throws SQLException {
        Map<String, Object> map = IOUtil.beanToMap(assetDetail);
        return assetDetailDao.countByParam(map);
    }

    /**
     * 查询所有符合条件的记录
     */
    public Map<String, Object> findByParam(AssetDetailVo assetDetail) throws SQLException {
        // 传递参数 0, 0 表示 查询全部结果；分页查询时，startRow 默认是 0，pageSize 默认是一个正整数
        return findByParam(assetDetail, 0, 0);
    }

    /**
     * 查询符合条件的记录，分页
     */
    public Map<String, Object> findByParam(AssetDetailVo assetDetail, int startRow, int pageSize) throws SQLException {
        Map<String, Object> map = IOUtil.beanToMap(assetDetail);

        String ids = assetDetail.getRepayStatusStr();
        if (ids != null) {
            List<Object> repayStatusList = Util.idsStringToList(ids);
            map.put("repayStatusList", repayStatusList);
        }

        String stageNoStr = assetDetail.getStageNoStr();
        if (stageNoStr != null) {
            List<Object> stageNoList = Util.idsStringToList(stageNoStr);

            if (!Util.isNullOrEmptyOrZero(stageNoList)) {
                map.put("stageNoList", stageNoList);
            }
        }

        long count = assetDetailDao.countByParam(map);

        map.put("startRow", startRow);
        map.put("pageSize", pageSize);
        List<Object> list = assetDetailDao.findByParam(map);

        // 将结果集转换为 json 往浏览器回写，把 null 转换为 Date 类型时会报错。这时输出一个空字符串
        List<Object> keyList = Util.getArrayList();
        keyList.add("loanTime");
        keyList.add("dueDate");
        keyList.add("amortisementDate");
        IOUtil.formatDateToStr(list, keyList);

        Map<String, Object> result = Util.getHashMap();
        result.put("list", list);
        result.put("count", count);

        // 分页查询时，startRow 默认是 0，pageSize 默认是一个正整数
        // 如果是分页查询，还需要返回页面：有多少页的查询结果
        if (startRow > -1 && pageSize > 0) {
            long howManyPages = PageUtil.calculateHowManyPages(count, pageSize);
            result.put("howManyPages", howManyPages);
        }

        return result;
    }

    // 计算 资产计划 的 几种本息的总金额
    public Map<String, Object> sumInterestInquiry(Long apId) {
        Map<String, Object> map = assetDetailDao.sumInterestInquiry(apId);
        // 格式化 金额的单位 为 万元
        IOUtil.formatMoney(map);
        return map;
    }

    /**
     * 批量为 资产计划 添加 资产
     */
    public void batchAddAssetDetailToAssetPlan(String ids, Long apId) {
        List<Object> loanIdList = Util.idsStringToList(ids);

        Map<String, Object> map = Util.getHashMap();
        map.put("loanIdList", loanIdList);
        map.put("apId", apId);
        map.put("transferStatus", 1); // 更新 资产明细的状态 0 = 未转让 1 = 待转让 2 = 已转让
        assetDetailDao.batchUpdateAssetDetailByParam(map);
    }

    /**
     * 查询所有符合条件的 loanId
     */
    public List<Object> findLoanIdByParam(AssetDetailVo assetDetail) throws SQLException {
        Map<String, Object> map = IOUtil.beanToMap(assetDetail);

        String ids = assetDetail.getRepayStatusStr();
        if (ids != null) {
            List<Object> repayStatusList = Util.idsStringToList(ids);
            map.put("repayStatusList", repayStatusList);
        }

        String stageNoStr = assetDetail.getStageNoStr();
        if (stageNoStr != null) {
            List<Object> stageNoList = Util.idsStringToList(stageNoStr);

            if (!Util.isNullOrEmptyOrZero(stageNoList)) {
                map.put("stageNoList", stageNoList);
            }
        }

        return (List<Object>) assetDetailDao.findLoanIdByParam(map);
    }

    /**
     * 将符合条件的全部 AssetDetail 添加到 AssetPlan
     */
    public void addAllAssetDetailToAssetPlan(AssetDetailVo assetDetail, Long apIdParam) throws SQLException {
        List<Object> loanIdList = findLoanIdByParam(assetDetail);

        Map<String, Object> map = Util.getHashMap();
        map.put("loanIdList", loanIdList);
        map.put("apId", apIdParam);
        map.put("transferStatus", 1);    // 更新 资产明细的状态       0 = 未转让  1 = 待转让   2 = 已转让
        assetDetailDao.batchUpdateAssetDetailByParam(map);
    }

    /**
     * 删除 资产计划 中的 资产
     */
    public void deleteAssetDetailInAssetPlan(AssetDetailVo assetDetail) throws SQLException {
        assetDetail.setApId(0L);
        assetDetail.setTransferStatus((short) 0); // 更新 资产明细的状态 0 = 未转让 1 = 待转让 2 = 已转让
        assetDetailDao.updateByParam(assetDetail);
    }

    /**
     * 删除 AssetPlan 中所有的 AssetDetail
     */
    public void deleteAllAssetDetailInAssetPlan(AssetDetailVo assetDetail) throws SQLException {
        Map<String, Object> map = Util.getHashMap();
        map.put("transferStatus", 0);          // 更新 资产明细的状态 0 = 未转让 1 = 待转让 2 = 已转让
        map.put("apId", assetDetail.getApId());
        assetDetailDao.batchUpdateAssetDetailByAssetPlanId(map);
    }

    public Map<String, Object> findByParamWithSumInfo(AssetDetailVo assetDetail, int startRow, int pageSize)
            throws SQLException {
        Map<String, Object> resultMap = findByParam(assetDetail, startRow, pageSize);

        // 计算资产计划里的资产的几种本息的总金额
        Long apId = assetDetail.getApId();
        if (!Util.isNullOrEmptyOrZero(apId)) {
            Map<String, Object> sumMap = sumInterestInquiry(apId);
            resultMap.put("info", sumMap);
        }
        return resultMap;
    }

    public Map<String, Object> findAssetDetailWithRelatedInfo(Long id) throws SQLException {
        Map<String, Object> result = Util.getHashMap();

        if (id != null) {
            AssetDetailVo assetDetail = (AssetDetailVo) assetDetailDao.findById(id);
            result.put("assetDetail", assetDetail);
        }
        result.put("ProductTypes", Constants.getProductTypes());

        return result;
    }

    /**
     * 批量删除 资产计划 中的 资产
     */
    public void batchDeleteAssetDetailInAssetPlan(String ids) {
        List<Object> loanIdList = Util.idsStringToList(ids);

        Map<String, Object> map = Util.getHashMap();
        map.put("loanIdList", loanIdList);
        map.put("apId", 0L);
        map.put("transferStatus", 0); // 更新 资产明细的状态 1 = 待转让 2 = 已转让 0 = 未转让
        assetDetailDao.batchUpdateAssetDetailByParam(map);
    }

    /**
     * 导入 excel，确定资产管理人要购买的资产 loanIdList 是资产管理人要购买的资产的借据号的集合
     */
    public void sellAsset(Long apId, List<Object> decideToBuy) throws Exception {
        // 查询资产计划中的所有资产
        Map<String, Object> searchParam = Util.getHashMap();
        searchParam.put("apId", apId);
        searchParam.put("startRow", -1);
        searchParam.put("pageSize", -1);
        List<Object> assets = assetDetailDao.findByParam(searchParam);

        // Util.print(apId + "-----");
        // Util.print(decideToBuy);
        // Util.print(assets);

        if (Util.isNullOrEmptyOrZero(assets)) {
            throw new Exception("计划中没有资产明细");
        }

        // 判断 资产计划 中是否包含上传的 资产明细
        for (int j = 0; j < decideToBuy.size(); j++) {
            boolean existFlag = false;
            String loanIdOfAssetToBuy = (String) decideToBuy.get(j);

            for (int i = 0; i < assets.size(); i++) {
                AssetDetailVo asset = (AssetDetailVo) assets.get(i);
                // Util.print(asset.getLoanId() + "  ---- " + loanIdOfAssetToBuy);
                if (loanIdOfAssetToBuy.equals(asset.getLoanId())) {
                    existFlag = true;
                }
            }

            // Util.print(existFlag);
            if (existFlag == false) {
                throw new Exception("资产计划中不包含上传的资产明细, 它的借据号是 " + loanIdOfAssetToBuy);
            }
        }

        // 首先 将 excel 导入的，并且已经校验完的 资产，更新为 已转让 的计划中的资产
        Map<String, Object> map = Util.getHashMap();
        map.put("loanIdList", decideToBuy); // loanIdList 是资产管理人要购买的资产的借据号的集合
        map.put("apId", apId);
        map.put("transferStatus", 2); // 更新 资产明细的状态 2 = 已转让 0 = 未转让 1 = 待转让

        assetDetailDao.batchUpdateAssetDetailByLoanIds(map);

        // 然后 删除 资产计划 中 资产管理人不购买的 资产明细
        assetDetailDao.removeAssetDetailsNotBuyInAnAssetPlan(map);
    }

    public HSSFWorkbook generateAssetToExcel(AssetDetailVo assetDetail) {
        List<AssetDetailVo> list = new ArrayList<AssetDetailVo>();
        HSSFWorkbook listToExcel = null;
        try {
            list = assetDetailDao.exportAssets(assetDetail);
            listToExcel = ExcelUtil.exportAsset(list);
        } catch (DataAccessException e) {
            LOG.error(FormatService.logFormat("generateOrderToExcelByCon error."), e);
        }
        return listToExcel;
    }

    /**
     * 导入借据到数据库
     */
    public int excelInAsset(Long apId, File f) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("apId", apId); // 管理计划ID
        try {
            @SuppressWarnings("resource")
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(f)); // 读取上传的excel文档
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) { // 取得 sheet页的数量
                HSSFSheet sheet = workbook.getSheetAt(i); // 获取每个Sheet表
                int num = sheet.getPhysicalNumberOfRows(); // 获取每个Sheet表行数
                if (num > 1) {
                    HSSFRow titleRow = sheet.getRow(0); // 获取第一行，判断需要的值在哪一列
                    Integer buyOrNotNum = null; // 是否购买 一项 所在列下标
                    Integer loanIdNum = null; // 借据号 一项 所在列下标
                    // 根据名称获取目标项所在 列下标，防止用户修改目标列位置
                    abc:
                    for (int j = 0; j < titleRow.getPhysicalNumberOfCells(); j++) { // 循环获取 目标项 所在列下标
                        HSSFCell cell = titleRow.getCell(j); // 获取标题行 每一单元格
                        if (cell != null) {
                            String cellStr = cell.toString().trim();
                            if ("是否购买".equals(cellStr)) {
                                buyOrNotNum = Integer.valueOf(j);
                            } else if ("借据号".equals(cellStr)) {
                                loanIdNum = Integer.valueOf(j);
                            }
                            if (buyOrNotNum != null && loanIdNum != null) {
                                break abc;
                            }
                        }
                    }
                    buyOrNotNum = buyOrNotNum != null ? buyOrNotNum : 1; // 赋默认值 ，防止用户修改目标列名称
                    loanIdNum = loanIdNum != null ? loanIdNum : 2; // 赋默认值 ，防止用户修改目标列名称

                    // 校验 是否购买一栏 是否按规定填写
                    for (int k = 1; k < sheet.getPhysicalNumberOfRows() - 6; k++) {
                        HSSFRow contentRow = sheet.getRow(k); // 获取每一行
                        HSSFCell buyOrNotCell = contentRow.getCell(buyOrNotNum); // 获取 是否购买项 值
                        if (buyOrNotCell != null) {
                            String buyOrNotStr = buyOrNotCell.toString().trim();
                            if (!"是".equals(buyOrNotStr) && !"否".equals(buyOrNotStr)) {
                                return 6; // 导入失败，填写有误
                            }
                        } else {
                            return 6; // 导入失败，填写有误
                        }
                    }

                    for (int k = 1; k < sheet.getPhysicalNumberOfRows() - 6; k++) { // 去除合计的行数
                        HSSFRow contentRow = sheet.getRow(k); // 获取每一行
                        HSSFCell buyOrNotCell = contentRow.getCell(buyOrNotNum); // 获取 是否购买项 值
                        String buyOrNotStr = buyOrNotCell.toString().trim(); // 前面已经校验过，此处只能是是或否
                        if ("是".equals(buyOrNotStr)) {
                            HSSFCell loanIdCell = contentRow.getCell(loanIdNum);
                            if (loanIdCell != null && loanIdCell.toString().trim().length() != 0) {
                                String loanId = loanIdCell.toString().trim();
                                Map<String, Object> checkResult = new HashMap<String, Object>();
                                paramMap.put("loanId", loanId);
                                try {
                                    checkResult = assetDetailDao.checkLoanId(paramMap); // 检查当前loan_id数据库中是否存在,检查这条loan_id
                                    // 是否属于这个计划
                                } catch (DataAccessException e) {
                                    LOG.error("查询数据库表tbl_asset_detail失败", e);
                                }
                                if (checkResult != null) { // 如果为null，表示该借据不属于该计划
                                    Integer transferStatus = (Integer) checkResult.get("transfer_status"); // 获取转让状态
                                    BigDecimal surplusPrincipalAmount =
                                            (BigDecimal) checkResult.get("surplus_principal_amount"); // 获取剩余本息
                                    BigDecimal surplusAmount = (BigDecimal) checkResult.get("surplus_amount"); // 获取剩余本金
                                    BigDecimal surplusFee = (BigDecimal) checkResult.get("surplus_fee"); // 获取剩余费用
                                    BigDecimal zeroBd = new BigDecimal(0);
                                    surplusPrincipalAmount =
                                            surplusPrincipalAmount != null ? surplusPrincipalAmount : zeroBd;
                                    surplusAmount = surplusAmount != null ? surplusAmount : zeroBd;
                                    surplusFee = surplusFee != null ? surplusFee : zeroBd;

                                    if (transferStatus == 1) {
                                        checkResult.put("transfer_principal_interest", surplusPrincipalAmount);
                                        checkResult.put("transferred_amount", surplusAmount);
                                        checkResult.put("transferred_fee", surplusFee);
                                        checkResult.put("transfer_status", 2);
                                        checkResult.remove("surplus_principal_amount");
                                        checkResult.remove("surplus_amount");
                                        checkResult.remove("surplus_fee");
                                        checkResult.put("ap_id", paramMap.get("apId").toString());
                                        try {
                                            assetDetailDao.updateTransferPrincipalInterest(checkResult);
                                        } catch (DataAccessException e) {
                                            LOG.error("更新数据库表 tbl_asset_detail 失败", e);
                                        }
                                    }
                                } else {
                                    LOG.error("导入功能：数据库中找不到该借据信息，借据号:" + loanId);
                                    return 2; // 有借据号不属于该计划
                                }
                            } else {
                                LOG.error("导入功能：该行借据单元格没有任何值，行号：" + (k + 1));
                                return 3; // 借据单元格没有任何值
                            }
                        } else { // 填否
                            HSSFCell loanIdCell = contentRow.getCell(loanIdNum);
                            if (loanIdCell != null && loanIdCell.toString().trim().length() != 0) {
                                String loanId = loanIdCell.toString().trim();
                                Map<String, Object> checkResult = new HashMap<String, Object>();
                                paramMap.put("loanId", loanId);
                                try {
                                    checkResult = assetDetailDao.checkLoanId(paramMap); // 检查当前loan_id数据库中是否存在,检查这条loan_id
                                    // 是否属于这个计划
                                } catch (DataAccessException e) {
                                    LOG.error("查询数据库表tbl_asset_detail失败", e);
                                }
                                if (checkResult != null) { // 如果为null，表示该借据不属于该计划
                                    checkResult.put("transfer_principal_interest", new BigDecimal(0));
                                    checkResult.put("transferred_amount", new BigDecimal(0));
                                    checkResult.put("transferred_fee", new BigDecimal(0));
                                    checkResult.put("transfer_status", 0);
                                    checkResult.remove("surplus_principal_amount");
                                    checkResult.remove("surplus_amount");
                                    checkResult.remove("surplus_fee");
                                    checkResult.put("ap_id", "");
                                    try {
                                        assetDetailDao.updateTransferPrincipalInterest(checkResult);
                                    } catch (DataAccessException e) {
                                        LOG.error("更新数据库表 tbl_asset_detail 失败", e);
                                    }
                                } else {
                                    LOG.error("导入功能：数据库中找不到该借据信息，借据号:" + loanId);
                                    return 2; // 有借据号不属于该计划
                                }
                            } else {
                                LOG.error("导入功能：该行借据单元格没有任何值，行号：" + (k + 1));
                                return 3; // 借据单元格没有任何值
                            }
                        }
                    }
                } else {
                    LOG.error("该页为空");
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error("找不到要导入的xls文件", e);
            return 5; // 5 表示找不到导入的文件
        } catch (IOException e) {
            LOG.error("IO输入异常", e);
            return 4; // 4 表述输出异常
        }
        return 1; // 1 表示文件导入成功
    }

}