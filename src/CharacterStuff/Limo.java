package CharacterStuff;

import GameEngine.Game;
import General.Vector2;

/**
 * Limo enemigo.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */

public class Limo extends EnemyCharacter
{

	int tier = 3;
	
	/**
	 * 
	 * @param pos Posición Vector2 que ocupa el Limo en un GameMap.
	 */
	public Limo(Vector2 pos)
	{
		super(pos);
		
		baseId = 14;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param pos Posición Vector2 que ocupa el Limo en un GameMap.
	 * @param tier Tier del Limo.
	 */
	private Limo(Vector2 pos, int tier)
	{
		super(pos);
		
		baseId = 14;
		
		this.tier = tier;
	}
	
	/**
	 * Método llamado cada turno para actualizar el Limo.
	 */
	@Override
	public void Update()
	{
		int playerAround = super.playerAround();
		
		if(playerAround != -1)
		{
			attack(pos.sum(Vector2.createFromDirection(playerAround, 1)));
		}
		else
		{
			move();
		}
	}

	/**
	 * Elimina el objeto de la lista de enemigos de la escena y del mapa.
	 * <br>
	 * También crea otro Limo de menor tier en la misma posición.
	 * 
	 */
	private void die()
	{
		Game.instance().currentScene().characters().remove(this);
		
		Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col()] = null;
		
		if(tier > 1)
		{
			Limo temp = new Limo(pos, tier - 1);
			
			Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col()] = temp;
			
			Game.instance().currentScene().addCharacterNextTurn(temp);
		}
	}
	
	/**
	 * @return Retorna el numero del sprite.
	 */
	public int Id()
	{
		return baseId + tier;
	}
}
