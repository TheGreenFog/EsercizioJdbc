package com.azienda.jdbcEsercizio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.azienda.jdbcEsercizio.model.Automobile;

public class AutomobileDao implements DaoInterface<Automobile>{
	private Connection con;

	public AutomobileDao(Connection con) {
		this.con = con;
	}

	@Override
	public void create(Automobile ref) throws SQLException{
		String sql = "insert into automobile (id, marca, modello, targa, colore, cilindrata) values (?,?,?,?,?,?)";
		PreparedStatement p=con.prepareStatement(sql);
		p.setInt(1, ref.getId());
		p.setString(2, ref.getMarca());
		p.setString(3, ref.getModello());
		p.setString(4, ref.getTarga());
		p.setString(5, ref.getColore());
		p.setFloat(6, ref.getCilindrata());
		p.executeUpdate();		
	}

	@Override
	public List<Automobile> read() throws SQLException {
		List<Automobile> list=new ArrayList<Automobile>();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from automobile");
		while(rs.next()) {
			list.add(new Automobile(
					rs.getInt("id"), 
					rs.getString("marca"), 
					rs.getString("modello"), 
					rs.getString("targa"), 
					rs.getString("colore"), 
					rs.getFloat("cilindrata")));
		}

		return list;
	}

	@Override
	public void update(Automobile ref) throws SQLException {
		String sql="update automobile set targa = ? where marca like ?";
		PreparedStatement p=con.prepareStatement(sql);
		p.setString(1, ref.getTarga());
		p.setString(2, ref.getMarca()+"%");
		p.executeUpdate();

	}

	@Override
	public void delete(Automobile ref) throws SQLException {
		String sql="delete from automobile where marca like ?";
		PreparedStatement p=con.prepareStatement(sql);
		p.setString(1, ref.getMarca()+"%");
		p.executeUpdate();
	}

	public boolean checkDup(Automobile ref) throws SQLException{
		String sql="select * from automobile where marca=? and modello =? and targa =? and colore=? and cilindrata=?";
		PreparedStatement p=con.prepareStatement(sql);
		p.setString(1, ref.getMarca());
		p.setString(2, ref.getModello());
		p.setString(3, ref.getTarga());
		p.setString(4, ref.getColore());
		p.setFloat(5, ref.getCilindrata());
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			return true;
		} else {
			return false;
		}

	}

	public Integer getMaxId() throws SQLException{
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select max(id) from automobile");
		if(rs.next()) {
			return rs.getInt(1);
		} else {
			return 0;
		}
	}



}
