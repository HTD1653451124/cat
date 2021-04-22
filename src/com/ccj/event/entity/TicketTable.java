package com.ccj.event.entity;

import java.util.Date;

public class TicketTable {
    private Integer ticketId;
    private Integer ticketNumber;
    private Date date;
    private Double price;
    private Integer scenicInfoId;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getScenicInfoId() {
        return scenicInfoId;
    }

    public void setScenicInfoId(Integer scenicInfoId) {
        this.scenicInfoId = scenicInfoId;
    }

    public TicketTable() {
    }

    public TicketTable(Integer ticketId, Integer ticketNumber, Date date, Double price, Integer scenicInfoId) {
        this.ticketId = ticketId;
        this.ticketNumber = ticketNumber;
        this.date = date;
        this.price = price;
        this.scenicInfoId = scenicInfoId;
    }

    @Override
    public String toString() {
        return "TicketTable{" +
                "ticketId=" + ticketId +
                ", ticketNumber=" + ticketNumber +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", scenicInfoId=" + scenicInfoId +
                '}';
    }
}
