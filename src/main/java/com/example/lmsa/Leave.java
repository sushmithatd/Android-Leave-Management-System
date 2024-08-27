package com.example.lmsa;

public class Leave {
    private String reason;
    private String startDate;
    private String endDate;

    public Leave(String reason, String startDate, String endDate) {
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}

