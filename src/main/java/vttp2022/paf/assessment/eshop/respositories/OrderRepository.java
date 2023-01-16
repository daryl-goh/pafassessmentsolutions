package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Order;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.HashMap;
import java.util.Map;
@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// TODO: Task 3

	public boolean createOrder(Order order) {
		Integer rowsInserted = jdbcTemplate.update(SQL_INSERT_INTO_ORDERS, order.getOrderId(), order.getName(),order.getOrderDate());
		return rowsInserted > 0;
	}

	public Map<String, Integer> getOrderCounts(String name) {
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(
		  SQL_SELECT_ORDER_STATUS_COUNTS,
		  name
		);
		Map<String, Integer> orderCountsMap = new HashMap<String, Integer>();
		while (rs.next()) {
		  orderCountsMap.put(rs.getString("status"), rs.getInt("count"));
		}

		System.out.println("dispatched >>> " + orderCountsMap.get("dispatched"));
		System.out.println("pending >>> " + orderCountsMap.get("pending"));
		return orderCountsMap;
	  }
}
