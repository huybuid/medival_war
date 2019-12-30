package troops;

public class Archer extends Troop {
	public Archer()
	{
		super();
		atkRange=3;
		mov=3;
	}
	public Archer(int xx, int yy, int t)
	{
		this();
		x=xx;
		y=yy;
		team=t;
	}
	public Archer(int hp, int xx, int yy, int t, boolean atk, boolean move, boolean act, boolean invi)
	{
		this(xx,yy,t);
		HP=hp;
		isAtk=atk;
		isMove=move;
		isActive=act;
		isInvi=invi;
	}
}
