package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.OrderRequest;
import com.taller.bookstore.dto.response.ApiResponse;
import com.taller.bookstore.dto.response.OrderResponse;
import com.taller.bookstore.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return new ApiResponse<>(
                "success",
                200,
                "Order created",
                orderService.createOrder(request, email),
                null
        );
    }

    @GetMapping("/my")
    public ApiResponse<List<OrderResponse>> getMyOrders(Authentication auth) {

        return new ApiResponse<>(
                "success",
                200,
                "My orders",
                orderService.getMyOrders(auth.getName()),
                null
        );
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getAllOrders() {

        return new ApiResponse<>(
                "success",
                200,
                "All orders",
                orderService.getAllOrders(),
                null
        );
    }
}