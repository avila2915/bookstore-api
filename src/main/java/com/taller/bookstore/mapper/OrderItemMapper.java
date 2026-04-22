package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.response.OrderItemResponse;
import com.taller.bookstore.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemResponse toResponse(OrderItem item) {
        OrderItemResponse res = new OrderItemResponse();
        res.setBookId(item.getBook().getId());
        res.setTitle(item.getBook().getTitle());
        res.setQuantity(item.getQuantity());
        res.setSubtotal(item.getSubtotal());
        return res;
    }
}