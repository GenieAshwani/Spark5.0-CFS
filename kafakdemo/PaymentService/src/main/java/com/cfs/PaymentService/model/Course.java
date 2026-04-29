package com.cfs.PaymentService.model;

public record Course(
        String id,
        String title,
        String description,
        int amountInPaise
) {
}
