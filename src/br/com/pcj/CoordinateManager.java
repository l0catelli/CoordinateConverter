package br.com.pcj;

import java.awt.geom.Point2D;

import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.UTMRef;

/**
 * Realiza a conversão entre os diferentes padrões de coordenadas.
 * <ul>
 * <li>Transversa de Mercator (UTM) (em metros) -> Graus Decimais (DD)
 * <li>Graus Decimais (DD) -> Graus, Minutos e Segundos (DMS)
 * <li>Universal Transversa de Mercator (UTM) em quilometros -> Universal Transversa de Mercator (UTM) em metros
 * <li>Universal Transversa de Mercator (UTM) -> Graus, Minutos e Segundos (DMS)
 * </ul>
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class CoordinateManager {
	
	/**
	 * Converte os valores de uma coordenada Universal Transversa 
	 * de Mercator (UTM) para o padrão Graus Decimais (DD).
	 * <p>
	 * O padrão de coordenada DD será representado utilizando um
	 * objeto <b>Point2D</b>.
	 *
	 * @param	easting		- Valor Leste da coordenada UTM.
	 * @param	northing	- Valor Norte da coordenada UTM.
	 * @param	lngZone		- Valor da zona de longitude da coordenada UTM.
	 * @param	latZone		- Valor da zona de latitude da coordenada UTM.
	 * @return      		Retorna o valor da coordenada no padrão Graus Decimais (DD).
	 * @see		Point2D
	 */
	public Point2D toLatLong(double easting, double northing, int lngZone, char latZone) {
		
		UTMRef utmRef = new UTMRef(easting, northing, latZone, lngZone);
		
		LatLng latLng = utmRef.toLatLng();
		
		Point2D coordinate = new Point2D.Double(latLng.getLng(), latLng.getLat());
		
		return coordinate;
		
	}
	
	/**
	 * Converte a latitude ou longitude informada no padrão Graus Decimais (DD)
	 * para o padrão Graus, Minutos e Segundos (DMS).
	 *
	 * @param	decimalDegrees	- Valor da latitude ou longitude no padrao Graus Decimais (DD).
	 * @return      			Retorna o valor da coordenada no padrão Graus, Minutos e Segundos (DMS).
	 * @see		DMS
	 */
	public DMS decimalDegreesToDegreesMinutesSeconds(double decimalDegrees) {
		
		boolean isNegative = false;
		
		if (decimalDegrees < 0) {
			
			isNegative = true;
			
			decimalDegrees = Math.abs(decimalDegrees);
			
		}
		
		DMS dms = new DMS();
		
		dms.setDegrees((int) Math.floor(decimalDegrees));
		
		dms.setMinutes((int) Math.floor(((decimalDegrees-dms.getDegrees())*60)));
		
		dms.setSeconds((double) Math.round( ((((decimalDegrees-dms.getDegrees())*60)-dms.getMinutes())*60)*100 )/100);
		
		if (isNegative) {
			dms.setDegrees(dms.getDegrees()*-1);
		}
		
		return dms;
		
	}
	
	/**
	 * Altera uma coordenada no padrão Universal Transversa de Mercator (UTM)
	 * que está em quilometros para metros.
	 *
	 * @param	eastingKm		- Valor Leste da coordenada UTM em quilometros.
	 * @param	northingKm		- Valor Norte da coordenada UTM em quilometros.
	 * @param	centralMeridian	- Valor do Meridiano Central da coordenada UTM.
	 * @return      			O valor da coordenada UTM em metros.
	 * @see		CoordinateUTM
	 */
	public CoordinateUTM utmKilometersToUtmMeters(double eastingKm, double northingKm, int centralMeridian) {
		
		CoordinateUTM coordinateUTM = new CoordinateUTM(); 
		
		switch (centralMeridian) {
			case 75:
				coordinateUTM.setLongitudeZone(18);
				break;
			case 69:
				coordinateUTM.setLongitudeZone(19);		
				break;
			case 63:
				coordinateUTM.setLongitudeZone(20);
				break;
			case 57:
				coordinateUTM.setLongitudeZone(21);
				break;
			case 51:
				coordinateUTM.setLongitudeZone(22);
				break;
			case 45:
				coordinateUTM.setLongitudeZone(23);
				break;
			case 39:
				coordinateUTM.setLongitudeZone(24);
				break;
			case 33:
				coordinateUTM.setLongitudeZone(25);
				break;
		}
		
		coordinateUTM.setEasting(eastingKm*1000);
		
		coordinateUTM.setNorthing(northingKm*1000);
		
		return coordinateUTM;

	}
	
	/**
	 * Converte uma coordenada no padrão Universal Transversa de Mercator (UTM)
	 * para o padrão Graus, Minutos e Segundos (DMS).
	 *
	 * @param	coordinateUTM	- Um objeto contendo os valores da coordenada no padrão UTM.
	 * @return      			Um objeto contendo os valores da coordenada no padrão DMS.
	 * @see		CoordinateDMS
	 * @see		CoordinateUTM
	 */
	public CoordinateDMS getCoordinateDMS(CoordinateUTM coordinateUTM) {
		
		Point2D coordinate = this.toLatLong(coordinateUTM.getEasting(), coordinateUTM.getNorthing(), coordinateUTM.getLongitudeZone(), coordinateUTM.getLatitudezone());
		
		CoordinateDMS coordinateDMS = new CoordinateDMS();
		
		coordinateDMS.setCoordinateDD(coordinate);
		
		coordinateDMS.setLatitude(decimalDegreesToDegreesMinutesSeconds(coordinate.getY()));
		
		coordinateDMS.setLongitude(decimalDegreesToDegreesMinutesSeconds(coordinate.getX()));
		
		return coordinateDMS;
		
	}

}
