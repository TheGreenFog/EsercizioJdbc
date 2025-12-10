package com.azienda.jdbcEsercizio.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.azienda.jdbcEsercizio.businessLogic.Service;
import com.azienda.jdbcEsercizio.dao.AutomobileDao;
import com.azienda.jdbcEsercizio.model.Automobile;

public class ChiamanteAutomobile {

	public static void main(String[] args) {
		String dbUrl = "jdbc:mysql://localhost:3306/eserciziojdbc?useSSL=false&serverTimezone=UTC";
		String dbUser = "root";
		String dbPass = "0885";

		Connection con = null;
		try(Scanner sc = new Scanner(System.in)){
			try {
				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				con.setAutoCommit(false);

				AutomobileDao dao = new AutomobileDao(con);
				Service src = new Service(dao, con);
				menu(sc, src);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	private static void menu(Scanner sc, Service src) throws SQLException, Exception {
		Integer choice=-1;
		try {
			do {
				try {
					System.out.println("1) inserisci nuova auto");
					System.out.println("2) cancella auto per marca");
					System.out.println("3) aggiorna targhe per marca");
					System.out.println("4) visualizza tutte le auto");
					System.out.println("0) per uscire");
					System.out.println("Inserisci Input: ");

					choice=Integer.parseInt(sc.nextLine());

				} catch (NumberFormatException e) {
					System.out.println("Input non valido");
					continue;
				}
				List<Automobile> list=src.returnAll();
				switch (choice) {
				case 1: 
					try {
						System.out.println("Marca: ");
						String marca=sc.nextLine();
						System.out.print("Modello: ");
						String modello = sc.nextLine();
						System.out.print("Targa: ");
						String targa = sc.nextLine();
						System.out.print("Colore: ");
						String colore = sc.nextLine();
						System.out.println("Cilindrata: ");

						Float cilindrata=Float.parseFloat(sc.nextLine());


						Automobile newAuto = new Automobile(marca, modello, targa, colore, cilindrata);
						src.addAuto(newAuto);
					} catch (NumberFormatException e){
						System.out.println("input non valido");
					}
					break;
				case 2:
					if(list.isEmpty()) {
						System.out.println("non sono presenti auto");
					}else {
						System.out.println("inserisci marca da cancellare: ");
						String marcaToDel=sc.nextLine();
						src.delAutoByMarca(marcaToDel);
					}
					break;
				case 3:
					if(list.isEmpty()) {
						System.out.println("non sono presenti auto");
					}else {
						System.out.println("Inserisci marca per aggiornare targhe: ");
						String marcaToUp=sc.nextLine();
						System.out.println("inserisci nuova targa: ");
						String newTarga = sc.nextLine();
						src.updTargaByMarca(marcaToUp, newTarga);
					}
					break;
				case 4:				
					if(list.isEmpty()) {
						System.out.println("non sono presenti auto");
					}else {
						for (Automobile a:list) {
							System.out.println(a.toString());
						}
					}
					break;
				case 0:
					System.out.println("Programma Terminato!");
					break;
				default:
					System.out.println("Inptu non valido");
					break;
				}

			}while(choice!=0);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
