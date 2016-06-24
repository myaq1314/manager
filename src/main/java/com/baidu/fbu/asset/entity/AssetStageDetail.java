package com.baidu.fbu.asset.entity;

import java.util.Date;

public class AssetStageDetail {
    private Long id;

    private Long loanId;

    private Integer loanScheduleNo;

    private Integer status;

    private Date dueDate;

    private String productType;

    private Long amount;

    private Long amountRepay;

    private Long principal;

    private Long principalRepay;

    private Long interest;

    private Long interestRepay;

    private Long charges;

    private Long chargesRepay;

    private Long penalty;

    private Long penaltyRepay;

    private Long overdue;

    private Long overdueRepay;

    private Long violate;

    private Long violateRepay;

    private Long management;

    private Long managementRepay;

    private Long service;

    private Long serviceRepay;

    private Date createtime;

    private Date updatetime;

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

    public Integer getLoanScheduleNo() {
        return loanScheduleNo;
    }

    public void setLoanScheduleNo(Integer loanScheduleNo) {
        this.loanScheduleNo = loanScheduleNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmountRepay() {
        return amountRepay;
    }

    public void setAmountRepay(Long amountRepay) {
        this.amountRepay = amountRepay;
    }

    public Long getPrincipal() {
        return principal;
    }

    public void setPrincipal(Long principal) {
        this.principal = principal;
    }

    public Long getPrincipalRepay() {
        return principalRepay;
    }

    public void setPrincipalRepay(Long principalRepay) {
        this.principalRepay = principalRepay;
    }

    public Long getInterest() {
        return interest;
    }

    public void setInterest(Long interest) {
        this.interest = interest;
    }

    public Long getInterestRepay() {
        return interestRepay;
    }

    public void setInterestRepay(Long interestRepay) {
        this.interestRepay = interestRepay;
    }

    public Long getCharges() {
        return charges;
    }

    public void setCharges(Long charges) {
        this.charges = charges;
    }

    public Long getChargesRepay() {
        return chargesRepay;
    }

    public void setChargesRepay(Long chargesRepay) {
        this.chargesRepay = chargesRepay;
    }

    public Long getPenalty() {
        return penalty;
    }

    public void setPenalty(Long penalty) {
        this.penalty = penalty;
    }

    public Long getPenaltyRepay() {
        return penaltyRepay;
    }

    public void setPenaltyRepay(Long penaltyRepay) {
        this.penaltyRepay = penaltyRepay;
    }

    public Long getOverdue() {
        return overdue;
    }

    public void setOverdue(Long overdue) {
        this.overdue = overdue;
    }

    public Long getOverdueRepay() {
        return overdueRepay;
    }

    public void setOverdueRepay(Long overdueRepay) {
        this.overdueRepay = overdueRepay;
    }

    public Long getViolate() {
        return violate;
    }

    public void setViolate(Long violate) {
        this.violate = violate;
    }

    public Long getViolateRepay() {
        return violateRepay;
    }

    public void setViolateRepay(Long violateRepay) {
        this.violateRepay = violateRepay;
    }

    public Long getManagement() {
        return management;
    }

    public void setManagement(Long management) {
        this.management = management;
    }

    public Long getManagementRepay() {
        return managementRepay;
    }

    public void setManagementRepay(Long managementRepay) {
        this.managementRepay = managementRepay;
    }

    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    public Long getServiceRepay() {
        return serviceRepay;
    }

    public void setServiceRepay(Long serviceRepay) {
        this.serviceRepay = serviceRepay;
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
}