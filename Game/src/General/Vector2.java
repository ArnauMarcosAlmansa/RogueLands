package General;
import java.util.Random;

public class Vector2
{
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
	
	public void set(Vector2 v2)
	{
		this.row = v2.row();
		this.col = v2.col();
	}
	
	public void add(Vector2 adder)
	{
		this.row = this.row + adder.row();
		this.col = this.col + adder.col();
	}
	
	public void add(int row, int col)
	{
		this.row = this.row + row;
		this.col = this.col + col;
	}
	
	public Vector2 sum(Vector2 sum)
	{
		Vector2 ret = new Vector2(this.row, this.col);
		
		ret.add(sum);
		
		return ret;
	}
	
	public Vector2 sum(int row, int col)
	{
		Vector2 ret = new Vector2(this.row, this.col);
		
		ret.add(row, col);
		
		return ret;
	}
	
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
	
	public void print()
	{
		System.out.println("row = " + this.row + " col = " + this.col);
	}
	
	public void randomize(int minRow, int maxRow, int minCol, int maxCol)
	{
		Random rand = new Random();
		
		this.row = rand.nextInt(maxRow - minRow) + minRow; 
		this.col = rand.nextInt(maxCol - minCol) + minCol; 
	}
	
	public boolean isBorder(int height, int width)
	{
		boolean ret = false;
		
		if(this.row == 0 || this.col == 0 || this.row == width - 1 || this.col == height - 1)
		{
			ret = true;
		}
		
		return ret;
	}
	
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