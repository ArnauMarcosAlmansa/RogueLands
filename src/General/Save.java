package General;

import java.io.Serializable;

import WorldStuff.World;

public class Save implements Serializable
{
	/**
	 * Clase con información de guardado.
	 * 
	 * @since Java 8.0
	 * @version 1.0
	 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
	 */
	//private static final long serialVersionUID = 4421915768888753679L;

	public World world;
	
	public Vector2 playerPosOnWorld = new Vector2();
	
	public Vector2 playerPosOnMap = new Vector2();
}
