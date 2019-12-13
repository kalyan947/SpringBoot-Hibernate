package com.jersey.rest.DemoRestApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

	List<Alien> aliens;

	Connection con = null;

	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/neon";
		String username = "root";
		String password = "mysql";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from alien ";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aliens;
	}

	public Alien getAlien(int id) {
		Alien a = new Alien();
		String sql = "select * from alien  where id=" + id;
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {

				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;
	}

	public void create(Alien a) {

		String sql = "insert into alien values(?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, a.getId());
			statement.setString(2, a.getName());
			statement.setInt(3, a.getPoints());
			statement.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Alien a1) {
		
		String sql="update alien set id=?, name=?, points=?";
		try {
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1, a1.getId());
			ps.setString(2, a1.getName());
			ps.setInt(3, a1.getPoints());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String sql = "delete from alien where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
