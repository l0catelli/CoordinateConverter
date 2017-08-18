package br.com.pcj;

/**
 * Objeto para armazenar o valor de uma coordenada no padrão
 * Universal Transversa de Mercator (UTM).
 * <ul>
 * <li> easting			- Valor leste da coordenada. 
 * <li> northing		- Valor norte da coordenada.
 * <li> longitudeZone	- Valor da zona de longitude da coordenada.
 * <li> latitudeZone	- Valor da zona de latitude da coordenada. Atributo fixo com valor igual a "<b>K</b>".
 * </ul>
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class CoordinateUTM {
	
	private double easting;
	
	private double northing;
	
	private int longitudeZone;
	
	private static final char latitudeZone = 'K';

	public double getEasting() {
		return easting;
	}

	public void setEasting(double easting) {
		this.easting = easting;
	}

	public double getNorthing() {
		return northing;
	}

	public void setNorthing(double northing) {
		this.northing = northing;
	}

	public int getLongitudeZone() {
		return longitudeZone;
	}

	public void setLongitudeZone(int longitudeZone) {
		this.longitudeZone = longitudeZone;
	}

	public char getLatitudezone() {
		return latitudeZone;
	}

}
