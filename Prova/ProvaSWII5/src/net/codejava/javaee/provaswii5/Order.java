package net.codejava.javaee.provaswii5;
import java.sql.Date;

public class Order {
    private int orderNo;
    private double purchAmt;
    private Date ordDate;
    private int customerId;
    private int salesmanId;

    public Order(int orderNo) {
        this.orderNo = orderNo;
    }
    
    public Order(double purchAmt, Date ordDate, int customerId, int salesmanId) {
        this.purchAmt = purchAmt;
        this.ordDate = ordDate;
        this.customerId = customerId;
        this.salesmanId = salesmanId;
    }
    
    public Order(int orderNo, double purchAmt, Date ordDate, int customerId, int salesmanId) {
        this.orderNo = orderNo;
        this.purchAmt = purchAmt;
        this.ordDate = ordDate;
        this.customerId = customerId;
        this.salesmanId = salesmanId;
    }

    // Getters e Setters
    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public double getPurchAmt() {
        return purchAmt;
    }

    public void setPurchAmt(double purchAmt) {
        this.purchAmt = purchAmt;
    }

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }
}
