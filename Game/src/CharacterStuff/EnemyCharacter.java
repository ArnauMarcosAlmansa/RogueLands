package CharacterStuff;

import java.util.Random;

import GameEngine.Game;
import General.Vector2;
import MapStuff.GameMap;

public class EnemyCharacter extends GameCharacter
{
	Random rand = new Random();
	
	public EnemyCharacter()
	{
		currentId = 5;
		currentHealth = 1;
		maxHealth = 1;
		damageAbsortion = 1;
		
		attack = 1;
		
		pos = new Vector2();
		movement = new Vector2();
	}
	
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
	
	private void move()
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
		
		Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col()] = null;
		
		pos.add(movement);
		
		Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col()] = this;
	}
	
	private int playerAround()
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
	
	private void attack(Vector2 targetPos)
	{
		GameCharacter target = Game.instance().currentScene().currentMap().charactersGrid()[targetPos.row()][targetPos.col()];
		
		target.damage(attack);
	}
}
