package com.cfs.PaymentService.dto;

public record PaymentVerificationRequest(

        String razorpayOrderId,
        String razorpayPaymentId,
        String razorpaySignature,
        String courseId,
        String studentName,
        String email

) {
}
