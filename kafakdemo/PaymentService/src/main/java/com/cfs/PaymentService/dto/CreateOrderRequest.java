package com.cfs.PaymentService.dto;

public record CreateOrderRequest(

        String studentName,
        String email,
        String courseId
)
{

}
