package unmsm.dycs.orders.infrastructure.persistence.jdbi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import unmsm.dycs.orders.domain.entity.Buyer;

public class BuyerMapper implements RowMapper<Buyer> {
	@Override
	public Buyer map(ResultSet rs, StatementContext ctx) throws SQLException {
		Buyer result = new Buyer();

		result.setBuyerId(rs.getLong("buyer_id"));

		result.setUserName(rs.getString("user_name"));

		result.setFirstName(rs.getString("first_name"));

		result.setLastName(rs.getString("last_name"));

		return result;
	}
}
