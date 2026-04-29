package com.cfs.PaymentService.dto;

public record PaymentVerificationResponse(
        boolean verified,
        String message

) {
}
