package br.com.pcj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe que encapsula a conexão com o arquivo Access e
 * executa instruções SQL.
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class AccessManager {
	
	private String accdbURL;
	
	public String getAccdbURL() {
		return accdbURL;
	}

	public void setAccdbURL(String accdbURL) {
		this.accdbURL = accdbURL;
	}

	/**
	 * Retorna uma sessão do arquivo Access para permitir a manipução
	 * dos dados existentes.
	 *
	 * @return	Retorna uma sessão do arquivo Access.
	 * @see		Connection
	 */
	private Connection getAccess() throws SQLException {
		return DriverManager.getConnection("jdbc:ucanaccess://" + accdbURL);
	}
	
	/**
	 * Executa um comando informado no arquivo Access retornando
	 * um objeto ResultSet.
	 *
	 * @param  sqlQuery	- String contendo o comando que será executado no Access.
	 * @return      	O resultado da consulta executada.
	 * @see         	ResultSet
	 */
	public ResultSet executeQuery(String sqlQuery) throws SQLException {
		
		Connection conn = null;
		
		Statement s = null;
		
		try {
		
			conn = getAccess();
	
			s = conn.createStatement();
	
			return s.executeQuery(sqlQuery);
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			if (s != null) {
				s.close();
			}

			if (conn != null) {
				conn.close();
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * Executa um comando informado no arquivo Access.
	 *
	 * @param  sqlUpdate	- String contendo o comando que será executado no Access.
	 */
	public void executeUpdate(String sqlUpdate) throws SQLException {
		
		Connection conn = null;
		
		Statement s = null;
		
		try {
		
			conn = getAccess();

			s = conn.createStatement();
		
			s.execute(sqlUpdate);
		
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			
			if (s != null) {
				s.close();
			}

			if (conn != null) {
				conn.close();
			}
			
		}

	}

}
