package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.response.OrderResponse;
import com.taller.bookstore.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper itemMapper;

    public OrderResponse toResponse(Order order) {
        OrderResponse res = new OrderResponse();

        res.setId(order.getId());
        res.setStatus(order.getStatus().name());
        res.setTotal(order.getTotal());

        res.setItems(order.getItems()
                .stream()
                .map(itemMapper::toResponse)
                .collect(Collectors.toList()));

        return res;
    }
}