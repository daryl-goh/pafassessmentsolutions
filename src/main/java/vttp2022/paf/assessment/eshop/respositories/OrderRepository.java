package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Order;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;
@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// TODO: Task 3

	public boolean createOrder(Order order) {
		Integer rowsInserted = jdbcTemplate.update(SQL_INSERT_INTO_ORDERS, order.getOrderId(), order.getName(),order.getOrderDate());
		return rowsInserted > 0;
	}
}
