package net.codejava.javaee.provaswii5;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public OrderDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
    
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
    
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
	
	public boolean insertOrder(Order order) throws SQLException {
		String sql = "INSERT INTO orders (ord_no, purch_amt, ord_date, customer_id, salesman_id) VALUES (?, ?, ?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, order.getOrderNo());
		statement.setDouble(2, order.getPurchAmt());
		statement.setDate(3, order.getOrdDate());
		statement.setInt(4, order.getCustomerId());
		statement.setInt(5, order.getSalesmanId());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Order> listAllOrders() throws SQLException {
		List<Order> listOrder = new ArrayList<>();
		
		String sql = "SELECT * FROM orders";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int orderNo = resultSet.getInt("ord_no");
			double purchAmt = resultSet.getDouble("purch_amt");
			Date ordDate = resultSet.getDate("ord_date");
			int customerId = resultSet.getInt("customer_id");
			int salesmanId = resultSet.getInt("salesman_id");
			Order order = new Order(orderNo, purchAmt, ordDate, customerId, salesmanId);
			listOrder.add(order);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listOrder;
	}
	
	public boolean deleteOrder(Order order) throws SQLException {
		String sql = "DELETE FROM orders where ord_no = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, order.getOrderNo());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateOrder(Order order) throws SQLException {
		String sql = "UPDATE orders SET purch_amt = ?, ord_date = ?, customer_id = ?, salesman_id = ?";
		sql += " WHERE ord_no = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setDouble(1, order.getPurchAmt());
		statement.setDate(2, order.getOrdDate());
		statement.setInt(3, order.getCustomerId());
		statement.setInt(4, order.getSalesmanId());
		statement.setInt(5, order.getOrderNo());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public Order getOrder(int id) throws SQLException {
		Order order = null;
		String sql = "SELECT * FROM orders WHERE ord_no = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			int orderNo = resultSet.getInt("ord_no");
			double purchAmt = resultSet.getDouble("purch_amt");
			Date ordDate = resultSet.getDate("ord_date");
			int customerId = resultSet.getInt("customer_id");
			int salesmanId = resultSet.getInt("salesman_id");
			
			order = new Order(orderNo, purchAmt, ordDate, customerId, salesmanId);
		}
		
		resultSet.close();
		statement.close();
		
		return order;
	}
}

