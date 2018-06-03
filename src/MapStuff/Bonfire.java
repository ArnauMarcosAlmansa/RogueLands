package MapStuff;

import CharacterStuff.GameCharacter;
import CharacterStuff.PlayableCharacter;
import GameEngine.Game;
/**
 * 
 * Clase de casilla para curas y guardado.
 * 
 * @since Java 8.0
 * @version 1.0
 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
 */
public class Bonfire extends MapTile
{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 8880538764268312039L;
	
	public Bonfire()
	{
		id = 23;
	}

	/**
	 * Método llamado cuando un personaje entra en la casilla.
	 * 
	 * Si el personaje es el jugador, lo cura y guarda partida.
	 */
	public void stepInto(GameCharacter character)
	{
		if(character instanceof PlayableCharacter)
		{
			character.heal(character.MaxHealth());
			
			Game.instance().Save();
		}
	}
	
	public void stepOut(GameCharacter character)
	{
		
	}
}
