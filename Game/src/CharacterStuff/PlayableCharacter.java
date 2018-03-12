package CharacterStuff;

import GameEngine.Game;
import GameEngine.GameManager;
import General.FileManager;
import General.Vector2;
import MapStuff.Exit;

public class PlayableCharacter extends GameCharacter
{
	boolean attacking = false;
	
	public PlayableCharacter()
	{
		//TODO quitar el literal este
		currentId = 3;
		
		currentHealth = 10;
		maxHealth = 10;
		damageAbsortion = 1;
		
		attack = 1;
		pos = new Vector2();
		movement = new Vector2();
	}
	
	@Override
	public void Update()
	{
		while(!input())
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(attacking)
		{
			attack();
		}
		
		move();
	}
	
	private boolean input()
	{
		char input = Game.instance().window().getActualChar();
		
		movement.set(0, 0);
		
		attacking = false;
		
		if(input == 'w')
		{
			movement.set(-1, 0);
		}
		else if(input == 's')
		{
			movement.set(1, 0);
		}
		else if(input == 'a')
		{
			movement.set(0, -1);
		}
		else if(input == 'd')
		{
			movement.set(0, 1);
		}
		else if(input == 'k')
		{
			attacking = true;
		}
		else if(input == 'l')
		{
			Game.instance().Save();
			System.exit(0);
		}
		else if(input == 'u')
		{
			this.damage(10);
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	private boolean move()
	{
		boolean ableToMove = false;
		
		try
		{
			if(!Game.instance().
					currentScene().
					currentMap().
					tiles()
					[pos.row() + movement.row()]
					[pos.col() + movement.col()].isCollider())
			{
				Game.instance().
					currentScene().
						currentMap().
							charactersGrid()[pos.row()][pos.col()] = null;
				
				Game.instance().currentScene().currentMap().tiles()[pos.row()][pos.col()].stepOut(this);
				
				Game.instance().
					currentScene().
						currentMap().
							charactersGrid()
								[pos.row() + movement.row()]
								[pos.col() + movement.col()] = this;
				
				Game.instance().currentScene().currentMap().tiles()
						[pos.row() + movement.row()]
						[pos.col() + movement.col()].stepOut(this);
				
				pos.add(movement);
				
				ableToMove = true;
			}
		}
		catch(Exception e)
		{
			if(Game.instance().
					currentScene().
					currentMap().
					tiles()
					[pos.row()]
					[pos.col()] instanceof Exit)
			{
				Exit temp = (Exit) Game.instance().
						currentScene().
						currentMap().
						tiles()
						[pos.row()]
						[pos.col()];
				
				temp.stepOut(this);
			}
		}
		
		return ableToMove;
	}
	
	private void attack()
	{
		Vector2[] range = {new Vector2(1, 0), new Vector2(-1, 0), new Vector2(0, 1), new Vector2(0, -1)};
	
		for(int i = 0; i < range.length; i++)
		{
			try
			{
				Vector2 temp = pos.sum(range[i]);
				
				Game.instance().currentScene().currentMap().charactersGrid()[temp.row()][temp.col()].damage(attack);
			}
			catch(Exception e)
			{
				System.out.println("attack " + i + " failed");
			}
		}
	}
	
	@Override
	public float damage(float amount)
	{
		this.currentHealth = this.currentHealth - amount * damageAbsortion;
		
		if(this.currentHealth <= 0)
		{
			die();
		}
		
		return amount * damageReflection;
	}
	
	private void die()
	{
		System.out.println("Loser!");
		
		FileManager.instance().deleteGame(GameManager.instance().currentPlayer());
		
		System.exit(0);
	}
}
