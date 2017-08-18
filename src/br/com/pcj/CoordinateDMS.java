package br.com.pcj;

import java.awt.geom.Point2D;

/**
 * Objeto para armazenar o valor de uma coordenada no padrão
 * Graus, Minutos e Segundos (DMS). 
 * <p>
 * O objeto possui um atributo para armazenar a coordenada no padrão DD.
 * <ul>
 * <li>latitude		- Valor da latitude no formato DMS. 
 * <li>longitude	- Valor da longitude no formato DMS.
 * <li>coordinateDD	- Valor da coordenada no formato DD.
 * </ul>
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class CoordinateDMS {
	
	private DMS latitude;
	
	private DMS longitude;
	
	private Point2D coordinateDD;

	public DMS getLatitude() {
		return latitude;
	}

	public void setLatitude(DMS latitude) {
		this.latitude = latitude;
	}

	public DMS getLongitude() {
		return longitude;
	}

	public void setLongitude(DMS longitude) {
		this.longitude = longitude;
	}

	public Point2D getCoordinateDD() {
		return coordinateDD;
	}

	public void setCoordinateDD(Point2D coordinateDD) {
		this.coordinateDD = coordinateDD;
	}

	@Override
	public String toString() {
		return "Latitude=" + latitude.toString() + " Longitude=" + longitude.toString();
	}

}
