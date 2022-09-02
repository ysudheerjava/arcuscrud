package com.arcus.cms.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/arcus";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_CANDIDATES_SQL = "INSERT INTO candidate" + "  (name, gender,email, qualification,state) VALUES "
			+ " (?, ?, ?,?,?);";

	private static final String SELECT_CANDIDATE_BY_ID = "select id,name,gender,email,qualification,state from candidate where id =?";
	private static final String SELECT_ALL_CANDIDATES = "select * from candidate";
	private static final String DELETE_CANDIDATES_SQL = "delete from candidate where id = ?;";
	private static final String UPDATE_CANDIDATES_SQL = "update candidate set name = ?,gender=?,email= ?, qualification=?,state =? where id = ?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertCandidate(Candidate candidate) throws SQLException {
		System.out.println(INSERT_CANDIDATES_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_CANDIDATES_SQL)) {
			ps.setString(1, candidate.getName());
			ps.setString(2, candidate.getGender());
			ps.setString(3, candidate.getEmail());
			ps.setString(4, candidate.getQualification());
			ps.setString(5, candidate.getState());
			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Candidate selectCandidate(int id) {
		Candidate user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CANDIDATE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String qualification = rs.getString("qualification");
				String state = rs.getString("state");
				user = new Candidate(id, name, gender, email, qualification, state);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<Candidate> selectAllCandidates() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Candidate> candidates = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CANDIDATES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");

				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String qualification = rs.getString("qualification");
				String state = rs.getString("state");
				candidates.add(new Candidate(id, name, gender, email, qualification, state));

			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return candidates;
	}

	public boolean deleteCandidate(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CANDIDATES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateCandidate(Candidate candidate) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_CANDIDATES_SQL);) {

			ps.setString(1, candidate.getName());
			ps.setString(2, candidate.getGender());
			ps.setString(3, candidate.getEmail());
			ps.setString(4, candidate.getQualification());
			ps.setString(5, candidate.getState());

			ps.setInt(6, candidate.getId());
			rowUpdated = ps.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}