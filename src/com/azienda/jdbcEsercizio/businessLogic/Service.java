package com.azienda.jdbcEsercizio.businessLogic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.azienda.jdbcEsercizio.dao.AutomobileDao;
import com.azienda.jdbcEsercizio.model.Automobile;

public class Service {
	private AutomobileDao dao;
	private Connection con;

	public Service(AutomobileDao dao, Connection con) {
		this.dao = dao;
		this.con = con;
	}

	public void addAuto(Automobile ref) throws SQLException, Exception{
		try {
			if (ref.getMarca() == null || ref.getModello() == null || 
					ref.getTarga() == null || ref.getColore() == null || 
					ref.getCilindrata() == null) {
				throw new Exception("\ninserisci tutti i campi");
			}

			if(dao.checkDup(ref)) {
				throw new Exception("\nesiste già un'auto identica");
			}

			Integer maxId = dao.getMaxId();
			if (maxId ==null) {
				throw new Exception("\nnon sono presenti auto");
			} else {
				ref.setId(maxId+1);
			}

			dao.create(ref);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
	}

	public void delAutoByMarca(String marca) throws SQLException{
		try {
			Automobile ref=new Automobile(null, marca, null, null, null, null);
			dao.delete(ref);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
	}

	public void updTargaByMarca(String marca, String newTarga) throws SQLException{
		try {
			Automobile ref=new Automobile(null, marca, null, newTarga, null, null);
			dao.update(ref);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
	}

	public List<Automobile> returnAll() throws SQLException{
		try {
			List<Automobile> list=dao.read();
			con.commit();
			return list;
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
	}


}
