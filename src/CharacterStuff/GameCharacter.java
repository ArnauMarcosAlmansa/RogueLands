package CharacterStuff;
import java.io.Serializable;

import GameEngine.Game;
import General.Vector2;

public class GameCharacter implements Serializable
{
	/**
	 * Personaje. Solo sirve para polimorfia.
	 * 
	 * @since Java 8.0
	 * @version 1.0
	 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
	 */
	//private static final long serialVersionUID = 1224401406395817039L;

	protected String name;
	
	protected int baseId;
	
	protected Vector2 pos;
	protected Vector2 movement;
	
	protected int faceDirection;
	
	protected float maxHealth;
	protected float currentHealth;
	
	protected int[] status = new int[4];
	
	protected float attack;
	
	protected int effect;
	protected int effectAmount;
	
	protected float damageReflection;
	
	protected float damageAbsortion;
	
	protected float damageOnTouch;
	
	/**
	 * Metodo llamado cada turno para actualizar el GameCharacter.
	 */
	public void Update()
	{
		
	}
	
	/**
	 * 
	 * @param amount Cantidad de daño a causar.
	 * 
	 */
	public void damage(float amount)
	{
		this.currentHealth = this.currentHealth - amount * damageAbsortion;
		
		if(this.currentHealth <= 0)
		{
			die();
		}
	}
	
	/**
	 * Método que elimina este objeto de la lista de enemigos de la escena y del GameMap.
	 */
	private void die()
	{
		Game.instance().currentScene().characters().remove(this);
		
		Game.instance().currentScene().currentMap().charactersGrid()[pos.row()][pos.col()] = null;
	}
	
	
	/**
	 * <b>En desuso.</b><br>Método para restaurar vida.
	 * @param amount Cantidad a curar.
	 * @return true si ha curado, false si no lo ha hecho.
	 */
	public boolean heal(float amount)
	{
		boolean hasHealed = false;
		
		if(currentHealth < maxHealth)
		{
			currentHealth += amount;
			
			if(currentHealth > maxHealth)
			{
				currentHealth = maxHealth;
			}
			
			hasHealed = true;
		}
		
		return hasHealed;
	}
	
	/**
	 * <b>En desuso.</b><br>Método para colisionar entre dos GameCharacters.
	 * @param character El GameCharacter con el que colisiona.
	 */
	public void bumpInto(GameCharacter character)
	{
		character.damage(damageOnTouch);
		
		this.damage(character.getDamageOnTouch());
	}
	
	/**
	 * 
	 * <b>En desuso.</b><br>Método para añadir estados a un GameCharacter.
	 * 
	 * @param effect El estado que se quiere añadir.
	 * @param amount La cantidad de estado.
	 * 
	 */
	public void setStatus(int effect, int amount)
	{
		status[effect] += amount;
	}
	
	/**
	 * <b>En desuso.</b>
	 * @return Cantidad de daño al entrar en contacto con este GameCharacter.
	 * 
	 */
	public float getDamageOnTouch()
	{
		return damageOnTouch;
	}
	
	/**
	 * <b>En desuso.</b>
	 * @return Cantidad de ataque de un GameCharacter.
	 * 
	 */
	public float getAttack()
	{
		return attack;
	}
	
	/**
	 * 
	 * @return Cantidad de vida actual.
	 */
	public float CurrentHealth()
	{
		return currentHealth;
	}
	
	/**
	 *
	 * @return Cantidad de vida máxima.
	 */
	public float MaxHealth()
	{
		return maxHealth;
	}
	
	/**
	 * <b>En desuso.</b>
	 * @return Ratio entre la vida actual y la vida máxima.
	 */
	public float HealthRatio()
	{
		return currentHealth / maxHealth;
	}
	
	/**
	 * 
	 * @return Posición del GameCharacter.
	 */
	public Vector2 pos()
	{
		return pos;
	}
	
	/**
	 * <b>En desuso.</b><br>Método para establecer una posición
	 * @param newPos La nueva posición de GameCharacter.
	 */
	public void setPos(Vector2 newPos)
	{
		pos = newPos;
	}
	
	/**
	 * 
	 * @return Retorna el movimiento que ha realizado el GameCharacter en el último turno.
	 */
	public Vector2 Movement()
	{
		return movement;
	};
	
	
	/**
	 * 
	 * @return Retorna el numero de sprite. Aquí -1.
	 */
	public int Id()
	{
		return -1;
	}
}
