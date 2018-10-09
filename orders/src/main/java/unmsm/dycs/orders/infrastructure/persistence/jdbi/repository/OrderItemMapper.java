package unmsm.dycs.orders.infrastructure.persistence.jdbi.repository;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import unmsm.dycs.commons.domain.enumeration.OrderStatus;
import unmsm.dycs.orders.domain.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper implements RowMapper<Order> {
  @Override
  public Order map(ResultSet rs, StatementContext ctx) throws SQLException {
    Order result = new Order();
    
    result.setOrderId(rs.getLong("order_id"));

    result.setAddress(rs.getString("address"));
    
    result.setOrderDate(rs.getDate("order_date"));
    
    result.setOrderStatus(OrderStatus.values()[rs.getInt("order_status")]);

    result.setPaymentAuthCode(rs.getString("payment_authcode"));
    
    result.setOrderTotal(rs.getBigDecimal("order_total"));
    
    
    return result;
  }
}
