package CharacterStuff;

import GameEngine.Game;
import General.Vector2;
import MapStuff.Exit;

public class PlayableCharacter extends GameCharacter
{
	public PlayableCharacter()
	{
		//TODO quitar el literal este
		currentId = 2;
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
		
		move();
	}
	
	private boolean input()
	{
		char input = Game.instance().window().getActualChar();
		
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
		else
		{
			movement.set(0, 0);
			
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
}
