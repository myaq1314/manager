package com.baidu.fbu.asset.entity.vo;

import java.math.BigDecimal;
import java.sql.Date;

public class AssetDetailVo {

    private Long id;

    private Long loanId;

    private Long apId;

    private Short transferStatus;

    private Integer loanStatus;

    private Integer installmentCount;

    private BigDecimal creditAmount;

    private BigDecimal loanRate;

    private Date loanDate;

    private Date dueDate;

    private Long penalty;

    private Short repayPlanChange;

    private String guaranteeType;

    private Integer overDueDay;

    private Integer maxOverDueDay;

    private Long historyMaxAmount;

    private Long overDuePrincipal;

    private Long overDueInterest;

    private Short isDiscount;

    private Long discountBase;

    private Long discountRate;

    private Date amortisementDate;

    private BigDecimal amortisementCharge;

    private String productType;

    private String repayType;

    private Long loanAmount;

    private Long loanFee;

    private Long repayAmount;

    private Long repayFee;

    private Long transferredAmount;

    private Long transferredFee;

    private Long surplusAmount;

    private Long surplusFee;

    private String corpId;

    private Date createtime;

    private Date updatetime;

    private Long customerId;

    private String idcard;

    private String name;

    private Short age;

    private Short gender;

    private String degree;

    private String profession;

    private String area;

    private Byte status;

    private Long merchantId;

    private String bankBranchName;

    /** 借据最终还款日 开始日期 条件查询用到的辅助参数  */
    private Date startDueDate;

    /** 借据最终还款日 结束日期 条件查询用到的辅助参数  */
    private Date endDueDate;

    /** 页面提交的 还款状态  条件查询用到的辅助参数 */
    private String repayStatusStr;

    /** 借据放款日期 的开始日期  条件查询用到的辅助参数  */
    private Date startLoanTime;

    /** 借据放款日期 的结束日期  条件查询用到的辅助参数  */
    private Date endLoanTime;

    /** 分期计划数  条件查询用到的辅助参数*/
    private String stageNoStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getApId() {
        return apId;
    }

    public void setApId(Long apId) {
        this.apId = apId;
    }

    public Short getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Short transferStatus) {
        this.transferStatus = transferStatus;
    }

    public Integer getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Integer getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(Integer installmentCount) {
        this.installmentCount = installmentCount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Long getPenalty() {
        return penalty;
    }

    public void setPenalty(Long penalty) {
        this.penalty = penalty;
    }

    public Short getRepayPlanChange() {
        return repayPlanChange;
    }

    public void setRepayPlanChange(Short repayPlanChange) {
        this.repayPlanChange = repayPlanChange;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public Integer getOverDueDay() {
        return overDueDay;
    }

    public void setOverDueDay(Integer overDueDay) {
        this.overDueDay = overDueDay;
    }

    public Integer getMaxOverDueDay() {
        return maxOverDueDay;
    }

    public void setMaxOverDueDay(Integer maxOverDueDay) {
        this.maxOverDueDay = maxOverDueDay;
    }

    public Long getHistoryMaxAmount() {
        return historyMaxAmount;
    }

    public void setHistoryMaxAmount(Long historyMaxAmount) {
        this.historyMaxAmount = historyMaxAmount;
    }

    public Long getOverDuePrincipal() {
        return overDuePrincipal;
    }

    public void setOverDuePrincipal(Long overDuePrincipal) {
        this.overDuePrincipal = overDuePrincipal;
    }

    public Long getOverDueInterest() {
        return overDueInterest;
    }

    public void setOverDueInterest(Long overDueInterest) {
        this.overDueInterest = overDueInterest;
    }

    public Short getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Short isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Long getDiscountBase() {
        return discountBase;
    }

    public void setDiscountBase(Long discountBase) {
        this.discountBase = discountBase;
    }

    public Long getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Long discountRate) {
        this.discountRate = discountRate;
    }

    public Date getAmortisementDate() {
        return amortisementDate;
    }

    public void setAmortisementDate(Date amortisementDate) {
        this.amortisementDate = amortisementDate;
    }

    public BigDecimal getAmortisementCharge() {
        return amortisementCharge;
    }

    public void setAmortisementCharge(BigDecimal amortisementCharge) {
        this.amortisementCharge = amortisementCharge;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Long getLoanFee() {
        return loanFee;
    }

    public void setLoanFee(Long loanFee) {
        this.loanFee = loanFee;
    }

    public Long getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(Long repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Long getRepayFee() {
        return repayFee;
    }

    public void setRepayFee(Long repayFee) {
        this.repayFee = repayFee;
    }

    public Long getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(Long transferredAmount) {
        this.transferredAmount = transferredAmount;
    }

    public Long getTransferredFee() {
        return transferredFee;
    }

    public void setTransferredFee(Long transferredFee) {
        this.transferredFee = transferredFee;
    }

    public Long getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(Long surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public Long getSurplusFee() {
        return surplusFee;
    }

    public void setSurplusFee(Long surplusFee) {
        this.surplusFee = surplusFee;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public Date getStartDueDate() {
        return startDueDate;
    }

    public void setStartDueDate(Date startDueDate) {
        this.startDueDate = startDueDate;
    }

    public Date getEndDueDate() {
        return endDueDate;
    }

    public void setEndDueDate(Date endDueDate) {
        this.endDueDate = endDueDate;
    }

    public String getRepayStatusStr() {
        return repayStatusStr;
    }

    public void setRepayStatusStr(String repayStatusStr) {
        this.repayStatusStr = repayStatusStr;
    }

    public Date getStartLoanTime() {
        return startLoanTime;
    }

    public void setStartLoanTime(Date startLoanTime) {
        this.startLoanTime = startLoanTime;
    }

    public Date getEndLoanTime() {
        return endLoanTime;
    }

    public void setEndLoanTime(Date endLoanTime) {
        this.endLoanTime = endLoanTime;
    }

    public String getStageNoStr() {
        return stageNoStr;
    }

    public void setStageNoStr(String stageNoStr) {
        this.stageNoStr = stageNoStr;
    }
}