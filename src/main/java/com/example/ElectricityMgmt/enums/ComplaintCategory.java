package com.example.ElectricityMgmt.enums;


public enum ComplaintCategory {

    // Billing Issue
    WRONG_BILL_AMOUNT(ComplaintType.BILLING_ISSUE),
    BILL_NOT_RECEIVED(ComplaintType.BILLING_ISSUE),
    PAYMENT_NOT_REFLECTED(ComplaintType.BILLING_ISSUE),

    // Power Outage
    NO_POWER_SUPPLY(ComplaintType.POWER_OUTAGE),
    FREQUENT_POWER_CUTS(ComplaintType.POWER_OUTAGE),
    TRANSFORMER_FAULT(ComplaintType.POWER_OUTAGE),

    // Meter Reading Issue
    WRONG_METER_READING(ComplaintType.METER_READING_ISSUE),
    METER_NOT_WORKING(ComplaintType.METER_READING_ISSUE),
    READING_NOT_UPDATED(ComplaintType.METER_READING_ISSUE);

    private final ComplaintType type;

    ComplaintCategory(ComplaintType type) {
        this.type = type;
    }

    public ComplaintType getType() {
        return type;
    }
}
