package General;
import java.io.Serializable;
import java.util.Random;

public class Vector2 implements Serializable
{
	/**
	 * Clase con <b>row</b> y <b>col</b> para almacenar posiciones y desplacamientos.
	 * 
	 * Los enteros 0 1 2 3 indican una dirección:
	 * <br>0  Arriba<br>1  Derecha<br>2  Abajo<br>3  Izquierda
	 * 
	 * @since Java 8.0
	 * @version 1.0
	 * @author Arnau Marcos Almansa arnaumarcosalmansa@gmail.com
	 */
	
	
	//private static final long serialVersionUID = 4795261839745778974L;
	
	
	private int row;
	private int col;
	
	public Vector2(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	public Vector2()
	{
		this.row = 0;
		this.col = 0;
	}
	
	public int row()
	{
		return this.row;
	}
	
	public int col()
	{
		return this.col;
	}
	
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public void setCol(int col)
	{
		this.col = col;
	}
	
	public void set(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Copia las variables del Vector2.
	 * 
	 * @param v2 Vector a ser copiado.
	 */
	public void set(Vector2 v2)
	{
		this.row = v2.row();
		this.col = v2.col();
	}
	
	/**
	 * Suma un Vector2 y guarda el resultado en el Vector2 que invoca el método.
	 * 
	 * @param adder Vector2 a ser sumado.
	 */
	public void add(Vector2 adder)
	{
		this.row = this.row + adder.row();
		this.col = this.col + adder.col();
	}
	
	/**
	 *  Suma <b>row</b> y <b>col</b> a el contenido del Vector2.
	 * 
	 * @param row
	 * @param col
	 */
	public void add(int row, int col)
	{
		this.row = this.row + row;
		this.col = this.col + col;
	}
	
	/**
	 * Suma un Vector2 al actual y retorna el resultado sin guardarlo.
	 * @param sum Vector2 a sumar.
	 * @return Vector2 con el resultado.
	 */
	public Vector2 sum(Vector2 sum)
	{
		Vector2 ret = new Vector2(this.row, this.col);
		
		ret.add(sum);
		
		return ret;
	}
	/**
	 * Suma <b>row</b> y <b>col</b> al Vector2 y retorna el resultado sin guardarlo.
	 * @param row <b>row</b> a sumar.
	 * @param col <b>col</b> a sumar.
	 * @return Vector2 con el resultado.
	 */
	public Vector2 sum(int row, int col)
	{
		Vector2 ret = new Vector2(this.row, this.col);
		
		ret.add(row, col);
		
		return ret;
	}
	/**
	 * 
	 * @param a El Vector2 que va a ser comparado.
	 * @return true si row y col de ambos són iguales, false en caso contrario.
	 */
	public boolean equals(Vector2 a)
	{
		if(this.row == a.row && this.col == a.col)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * Muestra información del Vector2.
	 */
	public void print()
	{
		System.out.println("row = " + this.row + " col = " + this.col);
	}
	
	/**
	 * Establece <b>row</b> y <b>col</b> a un valor aleatoria delimitado por los parametros dados.
	 * 
	 * @param minRow
	 * @param maxRow
	 * @param minCol
	 * @param maxCol
	 */
	public void randomize(int minRow, int maxRow, int minCol, int maxCol)
	{
		Random rand = new Random();
		
		this.row = rand.nextInt(maxRow - minRow) + minRow; 
		this.col = rand.nextInt(maxCol - minCol) + minCol; 
	}
	
	/**
	 * Determina si la posición que describe un Vector2 forma pate del borde de una matriz del las dimensiones dadas.
	 * @param height Altura de la matriz.
	 * @param width Ancho de la matriz.
	 * @return true si forma parte del borde, false en caso contrario.
	 */
	public boolean isBorder(int height, int width)
	{
		boolean ret = false;
		
		if(this.row == 0 || this.col == 0 || this.row == width - 1 || this.col == height - 1)
		{
			ret = true;
		}
		
		return ret;
	}
	
	/**
	 * Determina en que bordes de una matriz con dimensiones determinadas por los argumentos está la posición que describe el Vector2.
	 * 
	 * @param height Altura de la matriz.
	 * @param width Ancho de la matriz.
	 * @return Array con los bordes.
	 */
	public int[] borders(int height, int width)
	{
		int[] borders = new int[2];
		
		borders[0] = -1;
		borders[1] = -1;
		
		if(this.row == 0)
		{
			borders[0] = 0;
		}
		
		if(this.row == width - 1)
		{
			borders[0] = 2;
		}
		
		if(this.col == height - 1)
		{
			borders[1] = 1;
		}
		
		if(this.col == 0)
		{
			borders[1] = 3;
		}
		
		if(borders[0] == -1)
		{
			borders[0] = borders[1];
		}
		else if(borders[1] == -1)
		{
			borders[1] = borders[0];
		}
		
		return borders;
	}
	
	public String toString()
	{
		String ret = new String();
		
		ret = "row = " + row + " col = " + col;
		
		return ret;
	}
	
	/**
	 * Genera un Vector2 a partir de una dirección y una magnitud.
	 * 
	 * @param dir Dirección.
	 * @param mag Magnitud.
	 * @return El Vector2 creado.
	 */
	public static Vector2 createFromDirection(int dir, int mag)
	{
		Vector2 ret = new Vector2();
		
		if(dir == 0)
		{
			ret.add(-mag, 0);
		}
		
		if(dir == 1)
		{
			ret.add(0, mag);
		}
		
		if(dir == 2)
		{
			ret.add(mag, 0);
		}
		
		if(dir == 3)
		{
			ret.add(0, -mag);
		}
		
		return ret;
	}
	
	/**
	 * Determina la dirección en la que apunta un Vector2.
	 * 
	 * @return La dirección en que apunta el Vector2.
	 */
	public int toDirection()
	{
		int ret = -1;
		
		if(row == 1)
		{
			ret = 2;
		}
		else if(row == -1)
		{
			ret = 0;
		}
		
		if(col == 1)
		{
			ret = 1;
		}
		else if(col == -1)
		{
			ret = 3;
		}
		
		return ret;
	}
	
	/**
	 * Determina si la posición que describe el Vector2 está dentro de una matriz de dimensiones dadas.
	 * @param rows Altura de la matriz.
	 * @param columns Ancho de la matriz.
	 * @return true si está dentro, false si está fuera.
	 */
	public boolean isInsideGrid(int rows, int columns)
	{
		boolean ret = true;
		
		if(this.col < 0 || this.col >= columns)
		{
			ret = false;
		}
		else if(this.row < 0 || this.row >= rows)
		{
			ret = false;
		}
		
		return ret;
	}
}