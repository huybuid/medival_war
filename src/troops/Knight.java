package troops;

public class Knight extends Troop {
	public Knight()
	{
		super();
		atkRange=1;
		mov=4;
	}
	public Knight(int xx, int yy, int t)
	{
		this();
		x=xx;
		y=yy;
		team=t;
	}
	public Knight(int hp, int xx, int yy, int t, boolean atk, boolean move, boolean act, boolean invi)
	{
		this(xx,yy,t);
		HP=hp;
		isAtk=atk;
		isMove=move;
		isActive=act;
		isInvi=invi;
	}
	
}
