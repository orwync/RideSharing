package com.example.ridesharing;


//Model for recycler view
public class Model {
    private String from;
    private String fromDate;
    private String to;
    private String price;
    private String time;
    private String currency;

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getTo() {
        return to;
    }

    public String getToDate() {
        return toDate;
    }

    private String toDate;

    public void setFrom(String from) {
        this.from = from;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFrom() {
        return from;
    }

    public String getFromDate() {
        return fromDate;
    }
}
