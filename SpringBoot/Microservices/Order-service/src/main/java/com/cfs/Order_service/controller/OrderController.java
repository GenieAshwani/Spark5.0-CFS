package com.cfs.Order_service.controller;


import com.cfs.Order_service.config.UserClient;
import com.cfs.Order_service.dto.OrderResponse;
import com.cfs.Order_service.dto.User;
import com.cfs.Order_service.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    private List<Order> orderList= Arrays.asList(

            new Order(1L,1L,"Washing Machine",54000.00),
            new Order(2L,2L,"Macbook M5",3500000.00),
            new Order(3L,3L,"Headphones",540.00)
    );


    @GetMapping
    public List<Order> getAllorders()
    {
        return  orderList;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id)
    {
        return orderList.stream()
                .filter(order->order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //get order with user details
    @GetMapping("/{id}/with-user")
    public OrderResponse getOrderWithUser(@PathVariable Long id)
    {
        Order order=getOrderById(id);
        if(order==null)
        {
            return null;
        }


//        String url="http://localhost:8081/api/users/"+order.getUserId();
//        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);

        User user=userClient.getUserById(order.getUserId());
        return new OrderResponse(order,user);

    }
}
