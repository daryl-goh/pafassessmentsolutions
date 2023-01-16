package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.OrderStatus;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.Date;
@Repository
public class OrderStatusRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean createOrderStatus(OrderStatus orderStatus) { 
        return jdbcTemplate.update(SQL_INSERT_INTO_ORDER_STATUS, 
        orderStatus.getOrderId(),
        orderStatus.getDeliveryId(),
        orderStatus.getStatus(),
        new Date()) > 0;
    }
}
