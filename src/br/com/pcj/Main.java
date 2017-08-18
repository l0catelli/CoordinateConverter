package br.com.pcj;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * http://www.jstott.me.uk/jcoord/
 * http://ucanaccess.sourceforge.net/site.html
 */
public class Main {
	
	public static void main(String[] args) {
		
		AccessManager am = new AccessManager();
		
		am.setAccdbURL("C:/temp/Cobranca2017.accdb");
		
		try {
			
			CoordinateUTM utm = new CoordinateUTM();
			
			CoordinateManager cm = new CoordinateManager();
			
			CoordinateDMS dms = new CoordinateDMS();
			
			ResultSet rs = am.executeQuery(
				  "	SELECT 					"
				+ "		ID_USO 				"
				+ "		,COORDENADA_UTM_N 	"
				+ "		,COORDENADA_UTM_E 	"
				+ "		,UTM_MC				"
				+ "	FROM pcjCoordenadas		"
				+ "	WHERE 					"
				+ "		LatD IS NULL 		"
				+ "		OR LatM IS NULL 	"
				+ "		OR LatS IS NULL 	"
				+ "		OR LonD IS NULL 	"
				+ "		OR LonM IS NULL 	"
				+ "		OR LonS IS NULL 	"
				+ "	ORDER BY ID_USO ASC		"
			);
		
			while (rs.next()) {
				
				utm = cm.utmKilometersToUtmMeters(rs.getDouble("COORDENADA_UTM_E"), rs.getDouble("COORDENADA_UTM_N"), rs.getInt("UTM_MC"));
				
				dms = cm.getCoordinateDMS(utm);
				
				String update = "UPDATE pcjCoordenadas SET"
						+ " LatD = " + dms.getLatitude().getDegrees() + ", " 
						+ " LatM = " + dms.getLatitude().getMinutes() + ", "
						+ " LatS = " + dms.getLatitude().getSeconds() + ", "
						+ " LonD = " + dms.getLongitude().getDegrees() + ", "
						+ " LonM = " + dms.getLongitude().getMinutes() + ", "
						+ " LonS = " + dms.getLongitude().getSeconds() + ", "
						+ " LatDD = " + dms.getCoordinateDD().getY() + ", "
						+ " LonDD = " + dms.getCoordinateDD().getX()
						+ " WHERE ID_USO = " + rs.getInt("ID_USO") + ";";
				
				System.out.println(update);
				
				am.executeUpdate(update);
				
			}
			
			am.executeUpdate("UPDATE pcjUSO INNER JOIN pcjCoordenadas ON pcjUSO.ID_USO = pcjCoordenadas.ID_USO SET pcjUSO.LatD = pcjCoordenadas.LatD, pcjUSO.LatM = pcjCoordenadas.LatM, pcjUSO.LatS = pcjCoordenadas.LatS, pcjUSO.LonD = pcjCoordenadas.LonD, pcjUSO.LonM = pcjCoordenadas.LonM, pcjUSO.LonS = pcjCoordenadas.LonS;");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
