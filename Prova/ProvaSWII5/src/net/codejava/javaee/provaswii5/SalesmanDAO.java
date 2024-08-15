package net.codejava.javaee.provaswii5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalesmanDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public SalesmanDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public boolean insertSalesman(Salesman salesman) throws SQLException {
		String sql = "INSERT INTO salesman (salesman_id, name, city, commission) VALUES (?, ?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, salesman.getId());
		statement.setString(2, salesman.getName());
		statement.setString(3, salesman.getCity());
		statement.setDouble(4, salesman.getCommission());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Salesman> listAllSalesman() throws SQLException {
		List<Salesman> listSalesman = new ArrayList<>();
		
		String sql = "SELECT * FROM salesman";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("salesman_id");
			String name = resultSet.getString("name");
			String city = resultSet.getString("city");
			double commission = resultSet.getDouble("commission");
			Salesman salesman = new Salesman(id, name, city, commission);
			listSalesman.add(salesman);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listSalesman;
	}
	
	public boolean deleteSalesman(Salesman salesman) throws SQLException {
		String sql = "DELETE FROM salesman where salesman_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, salesman.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateSalesman(Salesman salesman) throws SQLException {
		String sql = "UPDATE salesman SET name = ?, city = ?, commission = ?";
		sql += " WHERE salesman_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, salesman.getName());
		statement.setString(2, salesman.getCity());
		statement.setDouble(3, salesman.getCommission());
		statement.setInt(4, salesman.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public Salesman getSalesman(int id) throws SQLException {
		Salesman salesman = null;
		String sql = "SELECT * FROM salesman WHERE salesman_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			int salesmanId = resultSet.getInt("salesman_id");
			String name = resultSet.getString("name");
			String city = resultSet.getString("city");
			double commission = resultSet.getDouble("commission");
			salesman = new Salesman(salesmanId, name, city, commission);
		}
		
		resultSet.close();
		statement.close();
		
		return salesman;
	}
}
