package com.cfs.NotificationService.model;

import org.apache.kafka.common.protocol.types.Field;

import java.time.Instant;

public record EnrollmentNotification(

        String studentName,
        String email,
        String courseId,
        String courseTitle,
        int amountInPaise,
        String razorpayOrderId,
        String razorpayPaymentId,
        Instant paidAt
) {
}
