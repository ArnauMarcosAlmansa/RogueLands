package CharacterStuff;

import java.util.Random;
import General.Vector2;

/**
 * Clase para crear enemigos y determinar la cantidad de estos.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 *
 */

public class EnemyFactory
{
	
	/**
	 * 
	 * Crea un enemigo.
	 * 
	 * @param type Un int que determina el tipo de enemigo a crear.
	 * @param pos La posición del enemigo a crear en un GameMap.
	 * @return Un GameCharacter del tipo introducido.
	 * 
	 */
	public GameCharacter create(int type, Vector2 pos)
	{
		GameCharacter enemy = null;
		
		switch(type)
		{
			case 0:
				enemy = new EnemyCharacter(pos);
				break;
			case 1:
				enemy = new Limo(pos);
				break;
			default:
				break;
		}
		
		return enemy;
	}
	
	/**
	 * 
	 * @param type int que determina el tipo del enemigo.
	 * @return Un int con la canmtidad de enemigos.
	 */
	public int howMany(int type)
	{
		int number = 0;
		
		Random rand = new Random();
		
		switch(type)
		{
			case 0:
				number = rand.nextInt(2) + 2;
				break;
			case 1:
				number = rand.nextInt(2) + 2;
				break;
			default:
				break;
		}
		
		return number;
	}
}
