package vttp2022.paf.assessment.eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.respositories.LineItemRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderStatusRepository;
import vttp2022.paf.assessment.eshop.services.exceptions.OrderException;

@Service
public class OrderService {
@Autowired
OrderRepository orderRepo;

@Autowired
LineItemRepository lineItemRepo;

@Autowired
OrderStatusRepository orderStatusRepo;

    // if anything fails to save, then rollback  
    @Transactional(rollbackFor = OrderException.class)    
    public void createOrder(Order order) throws OrderException {
        // save into orders table
        if (!orderRepo.createOrder(order)) throw new OrderException(
        "Cannot create order"
        );
        // save each line into line_item table
   

        // if any line items not inserted, throw an exception
        for (LineItem li : order.getLineItems()) {
        if (
            !lineItemRepo.createLineItem(order.getOrderId(), li)
        ) throw new OrderException("Cannot create line item");
        }
    }

    public boolean  createOrderStatus(OrderStatus orderStatus) {
        return orderStatusRepo.createOrderStatus(orderStatus);
    }
}
