package unmsm.dycs.orders.infrastructure.persistence.jdbi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import unmsm.dycs.orders.domain.entity.Order;

public class OrderItemMapper implements RowMapper<Order> {
	@Override
	public Order map(ResultSet rs, StatementContext ctx) throws SQLException {
		Order result = new Order();

		result.setAddress(rs.getString("address"));

		result.setOrderDate(rs.getDate("order_date"));

		return result;
	}
}
