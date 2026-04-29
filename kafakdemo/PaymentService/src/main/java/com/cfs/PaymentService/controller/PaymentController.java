package com.cfs.PaymentService.controller;

import com.cfs.PaymentService.dto.CreateOrderRequest;
import com.cfs.PaymentService.dto.CreateOrderResponse;
import com.cfs.PaymentService.dto.PaymentVerificationRequest;
import com.cfs.PaymentService.dto.PaymentVerificationResponse;
import com.cfs.PaymentService.model.Course;
import com.cfs.PaymentService.service.CourseCatalogService;
import com.cfs.PaymentService.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final CourseCatalogService courseCatalogService;
    private final PaymentService paymentService;
    private final String razorpayKeyId;

    public PaymentController(
            CourseCatalogService courseCatalogService,
            PaymentService paymentService,
            @Value("${app.razorpay.api.key-id}") String razorpayKeyId
    ) {
        this.courseCatalogService = courseCatalogService;
        this.paymentService = paymentService;
        this.razorpayKeyId = razorpayKeyId;
    }

    @GetMapping("/courses")
    public List<Course> courses() {
        return courseCatalogService.findAll();
    }

    @GetMapping("/config")
    public Map<String, String> config() {
        return Map.of("razorpayKeyId", razorpayKeyId);
    }

    @PostMapping("/payments/orders")
    public CreateOrderResponse creteOrder(@RequestBody CreateOrderRequest request) throws RazorpayException {
        return paymentService.createOrder(request);
    }

    @PostMapping("/payments/verify")
    public PaymentVerificationResponse verify(@RequestBody PaymentVerificationRequest request) {
        paymentService.verifyAndNotify(request);
        return new PaymentVerificationResponse(true, "Payment verified, Notification sent");
    }

    @ExceptionHandler({IllegalArgumentException.class, RazorpayException.class})
    public ResponseEntity<Map<String, String>> handleBadReq(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", ex.getMessage()));
    }
}
