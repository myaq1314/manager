package com.baidu.fbu.asset.util;

import com.baidu.fbu.asset.entity.AssetStageDetail;
import com.baidu.fbu.asset.entity.vo.AssetDetailVo;
import com.baidu.fbu.asset.entity.vo.AssetStageDetailVo;
import org.apache.poi.hssf.usermodel.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    /**
     * 导出分期excel
     */
    public static HSSFWorkbook exportAssetstage(List<AssetStageDetail> stageList) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("分期明细");

        HSSFCellStyle style = workBook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 左右居中  

        HSSFCellStyle moneyStyle = workBook.createCellStyle();
        moneyStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00")); // 金钱存储格式

        HSSFCellStyle txtStyle = workBook.createCellStyle();
        HSSFDataFormat txtFormat = workBook.createDataFormat();
        txtStyle.setDataFormat(txtFormat.getFormat("@")); // 文本格式

        HSSFRow row = null;
        // 首行标题设置
        row = sheet.createRow(0);
        row.setHeight((short) 1000);
        List<String> titleList = getStageTitleList();
        int i = 0;
        for (String str : titleList) {
            if (i == 1) {
                sheet.setColumnWidth(i, 10000);
            } else {
                sheet.setColumnWidth(i, 5000);
            }
            sheet.setDefaultColumnStyle((short) i, style);
            row.createCell(i++).setCellValue(str);
        }
        if (stageList != null && stageList.size() > 0) {
            int j = 1;
            for (AssetStageDetail assetStageDetailVo : stageList) {
                row = sheet.createRow(j);
                row.setHeight((short) 500);
                row.createCell(0).setCellValue(j); // 序号
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellStyle(txtStyle);
                cell1.setCellValue(formatExcelObj(assetStageDetailVo.getLoanId())); // 借据号
                row.createCell(2).setCellValue(formatExcelObj(assetStageDetailVo.getStageNo())); // 分期期数
                row.createCell(3).setCellValue(formatExcelObj(assetStageDetailVo.getIppDueDate())); // 分期还款日
                row.createCell(4).setCellValue(formatExcelObj(assetStageDetailVo.getdStageNo())); // 分期计划
                row.createCell(5).setCellValue(formatExcelProductType(assetStageDetailVo.getProductType())); // 分期产品类型
                row.createCell(6).setCellValue(formatExcelObj(assetStageDetailVo.getRepaymentDate())); // 实际还款日
                row.createCell(7).setCellValue(formatExcelRepaymentStatus(assetStageDetailVo.getRepaymentStatus())); // 还款状态
                // 使用double样式展示当期应还金额
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellStyle(moneyStyle);
                cell8.setCellValue(formatExcelMoney(assetStageDetailVo.getAmount())); // 当期应还金额
                // 使用double样式展示当前应还本金
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellStyle(moneyStyle);
                cell9.setCellValue(formatExcelMoney(assetStageDetailVo.getPrincipal())); // 当期应还本金
                // 使用double样式展示当期已还本金
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellStyle(moneyStyle);
                cell10.setCellValue(formatExcelMoney(assetStageDetailVo.getPmtPrincipalAmt())); // 当期已还本金
                // 使用double样式展示当期应还费用
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellStyle(moneyStyle);
                cell11.setCellValue(formatExcelMoney(assetStageDetailVo.getCost())); // 当期应还费用
                // 使用double样式展示当期已还费用
                HSSFCell cell12 = row.createCell(12);
                cell12.setCellStyle(moneyStyle);
                cell12.setCellValue(formatExcelMoney(assetStageDetailVo.getPmtFeeAmt())); // 当期已还费用
                // 使用double样式展示罚息
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellStyle(moneyStyle);
                cell13.setCellValue(formatExcelMoney(assetStageDetailVo.getPenalty())); // 罚息
                // 使用double样式展示已还罚息
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellStyle(moneyStyle);
                cell14.setCellValue(formatExcelMoney(assetStageDetailVo.getRepayPenalty())); // 已还罚息
                row.createCell(15).setCellValue(
                        formatExcelTransferStatus(assetStageDetailVo.getTransferStatus())); // 转让状态
                j++;
            }
        }
        int size = stageList != null ? stageList.size() : 0;
        row = sheet.createRow(size + 1);
        row.setHeight((short) 500);
        row = sheet.createRow(size + 2);
        row.setHeight((short) 500);
        row.createCell(0).setCellValue("合计：");
        row = sheet.createRow(size + 3);
        row.setHeight((short) 500);
        row.createCell(0).setCellValue("总笔数：");
        row.createCell(1).setCellValue(size);
        return workBook;
    }

    /**
     * 导出资产excel
     */
    public static HSSFWorkbook exportAsset(List<AssetDetailVo> excelList) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("资产明细");

        HSSFCellStyle style = workBook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 左右居中  

        HSSFCellStyle moneyStyle = workBook.createCellStyle();
        moneyStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00")); // 金钱存储格式

        HSSFCellStyle txtStyle = workBook.createCellStyle();
        HSSFDataFormat txtFormat = workBook.createDataFormat();
        txtStyle.setDataFormat(txtFormat.getFormat("@")); // 文本格式

        HSSFRow row = null;
        // 首行标题设置
        row = sheet.createRow(0);
        row.setHeight((short) 1000);
        List<String> titleList = getAssetTitleList();
        int i = 0;
        for (String str : titleList) {
            if ("序号".equals(str) || "借据号".equals(str) || "客户身份证号".equals(str)
                    || "客户所在区域".equals(str) || "商户分公司名称".equals(str)) {
                sheet.setColumnWidth(i, 8000);
            } else if ("还款方式".equals(str)) {
                sheet.setColumnWidth(i, 15000);
            } else {
                sheet.setColumnWidth(i, 4000);
            }
            sheet.setDefaultColumnStyle((short) i, style);
            row.createCell(i++).setCellValue(str);
        }
        BigDecimal loanPrincipalAmounts = new BigDecimal(0); // 贷款本息总额
        BigDecimal surplusPrincipalAmounts = new BigDecimal(0); // 剩余本息总额
        BigDecimal repayPrincipalInterests = new BigDecimal(0); // 已还本息总额

        if (excelList != null && excelList.size() > 0) {
            int j = 1; // excel行
            for (AssetDetailVo assetDetailVo : excelList) {
                row = sheet.createRow(j);
                row.setHeight((short) 500);
                row.createCell(0).setCellValue(j); // 序号

                HSSFCell cell1 = row.createCell(1);
                cell1.setCellStyle(txtStyle);
                if (assetDetailVo.getTransferStatus() == 2) {
                    cell1.setCellValue("是"); // 是否购买                    
                } else {
                    cell1.setCellValue("");
                }

                HSSFCell cell2 = row.createCell(2);
                cell2.setCellStyle(txtStyle);
                cell2.setCellValue(formatExcelObj(assetDetailVo.getLoanId())); // 借据号

                row.createCell(3).setCellValue(formatExcelTransferStatus(assetDetailVo.getTransferStatus())); // 转让状态

                row.createCell(4).setCellValue(formatExcelObj(assetDetailVo.getStageNo())); // 分期计划 

                row.createCell(5).setCellValue(formatExcelProductType(assetDetailVo.getProductType())); // 产品类型

                row.createCell(6).setCellValue(formatExcelObj(assetDetailVo.getLoanTime())); // 放款时间

                // 使用double样式展示授信额度
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellStyle(moneyStyle);
                cell7.setCellValue(formatExcelMoney(assetDetailVo.getCreditAmount())); // 授信额度

                row.createCell(8).setCellValue(formatExcelObj(assetDetailVo.getLoanRate())); // 贷款利率(月)

                BigDecimal loanAmount = assetDetailVo.getLoanAmount();
//                if (loanAmount != null) {
//                    loanPrincipalAmounts = loanPrincipalAmounts.add(loanAmount);
//                }
                HSSFCell cell9 = row.createCell(9);
                cell9.setCellStyle(moneyStyle);
                cell9.setCellValue(formatExcelMoney(loanAmount)); // 贷款本金
                BigDecimal loanFee = assetDetailVo.getLoanFee();
//                if (loanFee != null) {
//                    loanPrincipalAmounts = loanPrincipalAmounts.add(loanFee);
//                }
                HSSFCell cell10 = row.createCell(10);
                cell10.setCellStyle(moneyStyle);
                cell10.setCellValue(formatExcelMoney(loanFee)); // 贷款费用

                // 贷款本息
                loanPrincipalAmounts = loanPrincipalAmounts.add(assetDetailVo.getLoanPrincipalAmount());

//                BigDecimal repayAmount = assetDetailVo.getRepayAmount();
//                if (repayAmount != null) {
//                    repayPrincipalInterests = repayPrincipalInterests.add(repayAmount);
//                }
                HSSFCell cell11 = row.createCell(11);
                cell11.setCellStyle(moneyStyle);
                cell11.setCellValue(formatExcelMoney(assetDetailVo.getRepayAmount())); // 已还本金

//                BigDecimal repayFee = assetDetailVo.getRepayFee();
//                if (repayFee != null) {
//                    repayPrincipalInterests = repayPrincipalInterests.add(repayFee);
//                }

                repayPrincipalInterests = repayPrincipalInterests
                        .add(assetDetailVo.getRepayPrincipalInterest()); // 已还本息之和

                HSSFCell cell12 = row.createCell(12);
                cell12.setCellStyle(moneyStyle);
                cell12.setCellValue(formatExcelMoney(assetDetailVo.getRepayFee())); // 已还费用
                HSSFCell cell13 = row.createCell(13);
                cell13.setCellStyle(moneyStyle);
                cell13.setCellValue(formatExcelMoney(assetDetailVo.getTransferredAmount())); // 已转让本金
                HSSFCell cell14 = row.createCell(14);
                cell14.setCellStyle(moneyStyle);
                cell14.setCellValue(formatExcelMoney(assetDetailVo.getTransferredFee())); // 已转让费用

                BigDecimal surplusPrincipalAmount = assetDetailVo.getSurplusPrincipalAmount();
                if (surplusPrincipalAmount != null) {
                    surplusPrincipalAmounts = surplusPrincipalAmounts.add(surplusPrincipalAmount); // 剩余本息之和
                }
                // 使用double样式展示剩余本息
                HSSFCell cell15 = row.createCell(15);
                cell15.setCellStyle(moneyStyle);
                cell15.setCellValue(formatExcelMoney(surplusPrincipalAmount)); // 剩余本息

                row.createCell(16).setCellValue(formatExcelObj(assetDetailVo.getDueDate())); // 借据最终还款日
                row.createCell(17).setCellValue(formatExcelRepaymentStatus(assetDetailVo.getRepayStatus())); // 还款状态

                // 使用double样式展示罚息
                HSSFCell cell18 = row.createCell(18);
                cell18.setCellStyle(moneyStyle);
                cell18.setCellValue(formatExcelMoney(assetDetailVo.getPenaltyInterest())); // 罚息

                // 使用double样式展示已还罚息
                HSSFCell cell19 = row.createCell(19);
                cell19.setCellStyle(moneyStyle);
                cell19.setCellValue(formatExcelMoney(assetDetailVo.getRepayPenaltyInterest())); // 已还罚息

                row.createCell(20).setCellValue(
                        formatExcelRepayPlanChange(assetDetailVo.getRepayPlanChange())); // 还款计划是否变更

                row.createCell(21).setCellValue(formatExcelRepayType(assetDetailVo.getRepayType())); // 还款方式

                row.createCell(22).setCellValue(formatExcelObj(assetDetailVo.getGuaranteeType())); // 担保类型

                row.createCell(23).setCellValue(formatExcelObj(assetDetailVo.getOverDueDay())); // 累计逾期天数
                row.createCell(24).setCellValue(formatExcelObj(assetDetailVo.getMaxOverDueDay())); // 历史最高逾期天数
                // 使用double样式展示历史最高逾期金额
                HSSFCell cell25 = row.createCell(25);
                cell25.setCellStyle(moneyStyle);
                cell25.setCellValue(formatExcelMoney(assetDetailVo.getHistoryMaxAmount())); // 历史最高逾期金额
                // 使用double样式展示逾期本金金额
                HSSFCell cell26 = row.createCell(26);
                cell26.setCellStyle(moneyStyle);
                cell26.setCellValue(formatExcelMoney(assetDetailVo.getOverDuePrincipal())); // 逾期本金金额
                // 使用double样式展示逾期利息金额
                HSSFCell cell27 = row.createCell(27);
                cell27.setCellStyle(moneyStyle);
                cell27.setCellValue(formatExcelMoney(assetDetailVo.getOverDueInterest())); // 逾期利息金额

                row.createCell(28).setCellValue(formatExcelIsDiscount(assetDetailVo.getIsDiscount())); // 是否贴息
                // 使用double样式展示贴息基数
                HSSFCell cell29 = row.createCell(29);
                cell29.setCellStyle(moneyStyle);
                cell29.setCellValue(formatExcelMoney(assetDetailVo.getDiscountBase())); // 贴息基数
                row.createCell(30).setCellValue(formatExcelObj(assetDetailVo.getDiscountRate())); // 贴息比例
                row.createCell(31).setCellValue(formatExcelObj(assetDetailVo.getAmortisementDate())); // 摊销期限
                // 使用double样式展示应收摊销服务费
                HSSFCell cell32 = row.createCell(32);
                cell32.setCellStyle(moneyStyle);
                cell32.setCellValue(formatExcelMoney(assetDetailVo.getAmortisementCharge())); // 应收摊销服务费

                row.createCell(33).setCellValue(formatExcelObj(assetDetailVo.getCustomerName())); // 客户名称
                HSSFCell cell34 = row.createCell(34);
                cell34.setCellStyle(txtStyle);
                cell34.setCellValue(formatExcelObj(assetDetailVo.getCustomerIdcard())); // 客户身份证号
                row.createCell(35).setCellValue(formatExcelGender(assetDetailVo.getCustomerGender())); // 客户性别
                row.createCell(36).setCellValue(formatExcelObj(assetDetailVo.getCustomerAge())); // 客户年龄
                row.createCell(37).setCellValue(formatExcelObj(assetDetailVo.getCustomerDegree())); // 客户学历
                row.createCell(38).setCellValue(formatExcelObj(assetDetailVo.getCustomerProfession())); // 客户职业
                row.createCell(39).setCellValue(formatExcelObj(assetDetailVo.getCustomerArea())); // 客户所在区域
                row.createCell(40).setCellValue(formatExcelObj(assetDetailVo.getMerchantName())); // 商户总公司名称
                row.createCell(41).setCellValue(formatExcelObj(assetDetailVo.getMerchantBranchName())); // 商户分公司名称
                row.createCell(42).setCellValue(formatExcelObj(assetDetailVo.getCorpName())); // 合作机构名称
                j++;
            }
        }
        int size = excelList != null ? excelList.size() : 0;
        row = sheet.createRow(size + 1);
        row.setHeight((short) 500);
        row = sheet.createRow(size + 2);
        row.setHeight((short) 500);
        row.createCell(0).setCellValue("合计：");
        row = sheet.createRow(size + 3);
        row.setHeight((short) 500);
        row.createCell(0).setCellValue("总笔数：");
        row.createCell(1).setCellValue(size);
        row = sheet.createRow(size + 4);
        row.setHeight((short) 500);
        row.createCell(0).setCellValue("贷款本息总金额(万元)：");
        row.createCell(1).setCellValue(formatExcelObjDivide(loanPrincipalAmounts));
        row = sheet.createRow(size + 5);
        row.setHeight((short) 500);
        row.createCell(0).setCellValue("剩余本息总金额(万元)：");
        row.createCell(1).setCellValue(formatExcelObjDivide(surplusPrincipalAmounts));
        row = sheet.createRow(size + 6);
        row.setHeight((short) 500);
        row.createCell(0).setCellValue("已还本息总金额(万元)：");
        row.createCell(1).setCellValue(formatExcelObjDivide(repayPrincipalInterests));
        return workBook;
    }

    private static double formatExcelMoney(BigDecimal bd) {
        if (bd != null) {
            return bd.doubleValue();
        }
        return 0;
    }

    private static double formatExcelObjDivide(BigDecimal bd) {
        if (bd.compareTo(new BigDecimal(0)) == 0) {
            return 0;
        } else {
            return bd.divide(new BigDecimal(10000)).doubleValue();
        }
    }

    private static String formatExcelObj(Object obj) {
        if (obj != null) {
            return obj.toString();
        }
        return "——";
    }

    private static String formatExcelRepayType(String repayType) {
        if (repayType != null && repayType != "") {
            if ("PAYMENT01".equals(repayType)) {
                return "等额本金";
            } else if ("PAYMENT02".equals(repayType)) {
                return "等额本息";
            } else if ("PAYMENT03".equals(repayType)) {
                return "每期还息费，到期还本";
            } else if ("PAYMENT12".equals(repayType)) {
                return "延期2月等额本金";
            } else if ("PAYMENT13".equals(repayType)) {
                return "延期3月等额本金";
            } else if ("PAYMENT16".equals(repayType)) {
                return "延期6月等额本金";
            } else if ("PAYMENT17".equals(repayType)) {
                return "延期4月等额本金";
            } else if ("4P12P00".equals(repayType)) {
                return "前4个月还4%的本金，后12个月还96%的本金";
            } else if ("6012P07".equals(repayType)) {
                return "前6个月还利息，后12个月还本金和利息";
            } else if ("6P12P00".equals(repayType)) {
                return "前6个月还6%的本金，后12个月还94%的本金";
            } else if ("6P12P1288".equals(repayType)) {
                return "前6个月还12%的本金，后12个月还88%的本金";
            } else if ("6PI12PI017".equals(repayType)) {
                return "前6个月还1.98%的本金和利息，后12个月还98.02%的本金和利息";
            } else if ("6PI12PI09".equals(repayType)) {
                return "前6个月还3.6%的本金和利息，后12个月还96.4%的本金和利息";
            } else if ("4P12P00".equals(repayType)) {
                return "前4个月还4%的本金，后12个月还96%的本金";
            } else if ("6012P07".equals(repayType)) {
                return "前6个月还利息，后12个月还本金和利息";
            } else if ("6P12P00".equals(repayType)) {
                return "前6个月还6%的本金，后12个月还94%的本金";
            } else if ("6P12P1288".equals(repayType)) {
                return "前6个月还12%的本金，后12个月还88%的本金";
            } else if ("6PI12PI017".equals(repayType)) {
                return "前6个月还1.98%的本金和利息，后12个月还98.02%的本金和利息";
            } else if ("6PI12PI09".equals(repayType)) {
                return "前6个月还3.6%的本金和利息，后12个月还96.4%的本金和利息";
            } else if ("4P12P0892".equals(repayType)) {
                return "前4个月还8%的本金，后12个月还92%的本金";
            } else if ("6P12P088".equals(repayType)) {
                return "前6个月还1.8%的本金，后12个月还98.2%的本金";
            } else {
                return "——";
            }
        }
        return "——";
    }

    private static String formatExcelProductType(String productType) {
        if (productType != null && productType != "") {
            if (productType.contains("DXJ")) {
                return "度学金";
            } else if (productType.contains("DLQ")) {
                return "度零钱";
            } else if (productType.contains("QNR")) {
                return "去哪儿";
            } else if (productType.contains("BTB")) {
                return "度贴吧";
            } else {
                return productType;
            }
        }
        return "——";
    }

    private static String formatExcelRepayPlanChange(Integer repayPlanChange) {
        if (repayPlanChange != null) {
            if (repayPlanChange == 1) {
                return "已变更";
            }
        }
        return "未变更";
    }

    private static String formatExcelGender(Integer gender) {
        if (gender != null) {
            if (gender == 0) {
                return "女";
            } else if (gender == 1) {
                return "男";
            }
        }
        return "——";
    }

    private static String formatExcelIsDiscount(Integer isDiscount) {
        if (isDiscount != null) {
            if (isDiscount == 0) {
                return "贴息";
            } else if (isDiscount == 1) {
                return "不贴息";
            }
        }
        return "——";
    }

    private static String formatExcelRepaymentStatus(String repaymentStatus) {
        if (repaymentStatus != null && repaymentStatus != "") {
            if ("N".equals(repaymentStatus)) {
                return "正常";
            } else if ("Y".equals(repaymentStatus)) {
                return "结清";
            } else if ("O".equals(repaymentStatus)) {
                return "逾期";
            } else if ("U".equals(repaymentStatus)) {
                return "未到期";
            }
        }
        return "——";
    }

    private static String formatExcelTransferStatus(Integer transferStatus) {
        if (transferStatus != null) {
            if (transferStatus == 0) {
                return "未转让";
            } else if (transferStatus == 1) {
                return "待转让";
            } else if (transferStatus == 2) {
                return "已转让";
            }
        }
        return "——";
    }

    private static List<String> getStageTitleList() {
        List<String> titleList = new ArrayList<String>();
        titleList.add("xu_hao=序号");
        titleList.add("loan_id=借据号");
        titleList.add("loan_schedule_no=分期期数");
        titleList.add("due_date=分期还款日");
        titleList.add("amount=应还总金额");
        titleList.add("amount_repay=已还总金额");
        titleList.add("principal=应还本金");
        titleList.add("principal_repay=已还本金");
        titleList.add("interest=应还利息");
        titleList.add("interest_repay=已还利息");
        titleList.add("charges=bbb");
        titleList.add("charges_repay=bbb");
        titleList.add("penalty=bbb");
        titleList.add("penalty_repay=bbb");
        titleList.add("overdue=bbb");
        titleList.add("overdue_repay=bbb");
        titleList.add("violate=bbb");
        titleList.add("violate_repay=bbb");
        titleList.add("management=bbb");
        titleList.add("management_repay=bbb");
        titleList.add("service=bbb");
        titleList.add("service_repay=bbb");
        return titleList;
    }

    private static List<String> getAssetTitleList() {
        List<String> titleList = new ArrayList<String>();
        titleList.add("序号"); // 0
        titleList.add("是否购买"); // 1
        titleList.add("借据号"); // 2
        titleList.add("转让状态"); // 33
        titleList.add("分期计划"); // 3
        titleList.add("产品类型"); // 4
        titleList.add("放款时间"); // 5
        titleList.add("授信额度"); // 6
        titleList.add("贷款利率（月）"); // 7
        titleList.add("贷款本金（元）"); // 34
        titleList.add("贷款费用（元）"); // 35
        titleList.add("已还本金（元）"); // 36
        titleList.add("已还费用（元）"); // 37
        titleList.add("已转让本金（元）"); // 38
        titleList.add("已转让费用（元）"); // 39
        titleList.add("剩余本息（元）"); // 11
        titleList.add("借据最终还款日"); // 8
        titleList.add("还款状态"); // 40
//        titleList.add("贷款本息（元）"); // 9
//        titleList.add("已还本息（元）"); // 10
//        titleList.add("已转让本息（元）"); // 12
        titleList.add("罚息"); // 13
        titleList.add("已还罚息"); // 14
        titleList.add("还款计划是否变更"); // 15
        titleList.add("还款方式"); // 16
        titleList.add("担保类型"); // 27
        titleList.add("累计逾期天数"); // 28
        titleList.add("历史最高逾期天数"); // 29
        titleList.add("历史最高逾期金额"); // 30
        titleList.add("逾期本金金额"); // 31
        titleList.add("逾期利息金额"); // 32
        titleList.add("是否贴息"); // 34
        titleList.add("贴息基数"); // 35
        titleList.add("贴息比例"); // 36
        titleList.add("摊销期限"); // 37
        titleList.add("应收摊销服务费"); // 38
        titleList.add("客户姓名"); // 17
        titleList.add("客户身份证号"); // 18
        titleList.add("客户性别"); // 19
        titleList.add("客户年龄"); // 20
        titleList.add("客户学历"); // 21
        titleList.add("客户职业"); // 22
        titleList.add("客户所在区域"); // 23
        titleList.add("商户总公司名称"); // 24
        titleList.add("商户分公司名称"); // 25
        titleList.add("合作机构名称"); // 26
        return titleList;
    }
}
