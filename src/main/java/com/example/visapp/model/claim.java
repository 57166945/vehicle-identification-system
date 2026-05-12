package com.example.visapp.model;

import java.math.BigDecimal;
import java.sql.Date;

public class claim {
    private int claim_id;
    private int policy_id;
    private Date claim_date;
    private BigDecimal claim_amount;
    private String status;

    public claim(){

    }

    public claim(int claim_id, int policy_id, Date claim_date, BigDecimal claim_amount, String status) {
        this.claim_id = claim_id;
        this.policy_id = policy_id;
        this.claim_date = claim_date;
        this.claim_amount = claim_amount;
        this.status = status;
    }

    public int getClaim_id() { return claim_id; }
    public void setClaim_id(int claimId) { this.claim_id = claimId; }

    public int getPolicy_id() { return policy_id; }
    public void setPolicy_id(int policyId) { this.policy_id = policyId; }

    public Date getClaim_date() { return claim_date; }
    public void setClaim_date(Date claim_date) { this.claim_date = claim_date; }

    public BigDecimal getClaim_amount() { return claim_amount; }
    public void setClaim_amount(BigDecimal claim_amount) { this.claim_amount = claim_amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

