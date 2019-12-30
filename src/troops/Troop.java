package troops;

public abstract class Troop {
	public int HP, atkRange, team, mov, x, y;
	public boolean isAtk, isMove, isActive, isAlive;
	final int panelSize = 48;
	public Troop() 
	{
		isActive=true;
		isAlive=true;
		isMove=false;
		isAtk=false;
		HP=100;
	}
	public Troop(int x, int y)
	{
	}
	public abstract void Move();
	public abstract String toString();
}
