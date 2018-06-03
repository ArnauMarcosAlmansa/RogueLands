package CharacterStuff;

import java.util.Random;

import GameEngine.Game;
import General.Vector2;
import MapStuff.GameMap;

/**
 * Personaje enemigo.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 * 
 */

public class EnemyCharacter extends GameCharacter
{
	Random rand = new Random();
	
	
	/**
	 * 
	 * @param pos Posición Vector2 que ocupa este enemigo en un GameMap.
	 * 
	 */
	public EnemyCharacter(Vector2 pos)
	{
		baseId = 11;
		currentHealth = 1;
		maxHealth = 1;
		damageAbsortion = 1;
		
		attack = 1;
		
		this.pos = pos;
		movement = new Vector2();
	}
	
	/**
	 * Método llamado cada turno para actualizar el enemigo.
	 */
	@Override
	public void Update()
	{
		int playerAround = playerAround();
		
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
	 * Mueve el EnemyCharacter una casilla en una dirección aleatoria.
	 */
	protected void move()
	{
		while(true)
		{
			movement = Vector2.createFromDirection(rand.nextInt(4), 1);
			
			if(!Game.instance().currentScene().currentMap().tiles()[pos.row() + movement.row()][pos.col() + movement.col()].isCollider()
					&&
				pos.row() + movement.row() > 0 && pos.row() + movement.row() < GameMap.MAP_HEIGHT - 1
					&& 
				pos.col() + movement.col() > 0 && pos.col() + movement.col() < GameMap.MAP_WIDTH - 1
					&&
				Game.instance().currentScene().currentMap().charactersGrid()[pos.row() + movement.row()][pos.col() + movement.col()] == null
				)
			{
				break;
			}
		}
		
		faceDirection = movement.toDirection();
		
		Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col()] = null;
		
		pos.add(movement);
		
		Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col()] = this;
	}

	/**
	 * 
	 * @return int que representa la dirección en la que está el jugado si esta en una casilla adyacente. Retorna -1 si no lo encuentra.
	 */
	protected int playerAround()
	{
		int ret = -1;
		
		try {
			if(Game.instance().currentScene().currentMap().charactersGrid()[pos.row() + 1][pos.col()] instanceof PlayableCharacter)
			{
				ret = 2;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(Game.instance().currentScene().currentMap().charactersGrid()[pos.row() - 1][pos.col()] instanceof PlayableCharacter)
			{
				ret = 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col() + 1] instanceof PlayableCharacter)
			{
				ret = 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			if(Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col() - 1] instanceof PlayableCharacter)
			{
				ret = 3;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * Causa daño al GamCharacter en la posición especificada.
	 * 
	 * @param targetPos Posición del GameCharacter a atacar.
	 */
	protected void attack(Vector2 targetPos)
	{
		GameCharacter target = Game.instance().currentScene().currentMap().charactersGrid()[targetPos.row()][targetPos.col()];
		
		target.damage(attack);
	}
	
	
	/**
	 *  @return Retorna el numero del sprite.
	 */
	public int Id()
	{
		return baseId + faceDirection;
	}
}
