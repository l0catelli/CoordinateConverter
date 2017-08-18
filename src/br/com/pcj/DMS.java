package br.com.pcj;

/**
 * Objeto para armazenar o valor de latitude ou longitude 
 * no padrão Graus, Minutos e Segundos (DMS).
 * <ul>
 * <li> degrees - Valor dos graus da coordenada. 
 * <li> minutes	- Valor dos minutos da coordenada.
 * <li> seconds	- Valor dos segundos da coordenada.
 * </ul>
 * 
 * @author      João Augusto Locatelli
 * @version     1.0
 * @since       1.0
 */
public class DMS {
	
	private int degrees;
	
	private int minutes;
	
	private double seconds;

	public int getDegrees() {
		return degrees;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public double getSeconds() {
		return seconds;
	}

	public void setSeconds(double seconds) {
		this.seconds = seconds;
	}

	@Override
	public String toString() {
		return degrees + "°" + minutes + "'" + seconds + "\"";
	}

}
