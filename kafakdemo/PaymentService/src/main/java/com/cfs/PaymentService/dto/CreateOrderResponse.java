package com.cfs.PaymentService.dto;

public record CreateOrderResponse(

        String keyId,
        String orderId,
        int amountInPaise,
        String currency,
        String courseId,
        String courseTitle,
        String studentName,
        String email
) {
}
