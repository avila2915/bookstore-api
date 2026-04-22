package com.taller.bookstore.service.impl;

import com.taller.bookstore.dto.request.OrderItemRequest;
import com.taller.bookstore.dto.request.OrderRequest;
import com.taller.bookstore.dto.response.OrderResponse;
import com.taller.bookstore.entity.*;
import com.taller.bookstore.exception.custom.InsufficientStockException;
import com.taller.bookstore.exception.custom.ResourceNotFoundException;
import com.taller.bookstore.mapper.OrderMapper;
import com.taller.bookstore.repository.*;
import com.taller.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderRequest request, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemReq : request.getItems()) {

            Book book = bookRepository.findById(itemReq.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

            // 🔥 VALIDACIÓN DE STOCK
            if (book.getStock() < itemReq.getQuantity()) {
                throw new InsufficientStockException("Not enough stock for book: " + book.getTitle());
            }

            BigDecimal subtotal = book.getPrice()
                    .multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            OrderItem item = new OrderItem();
            item.setBook(book);
            item.setQuantity(itemReq.getQuantity());
            item.setSubtotal(subtotal);
            item.setOrder(order);

            items.add(item);

            // 🔥 DESCONTAR STOCK
            book.setStock(book.getStock() - itemReq.getQuantity());

            total = total.add(subtotal);
        }

        order.setItems(items);
        order.setTotal(total);

        Order saved = orderRepository.save(order);

        return orderMapper.toResponse(saved);
    }

    @Override
    public List<OrderResponse> getMyOrders(String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return orderRepository.findByUserId(user.getId())
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }
}