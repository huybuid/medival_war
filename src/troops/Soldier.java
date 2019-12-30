package troops;

public class Soldier extends Troop {
	public Soldier()
	{
		super();
		atkRange=1;
		mov=3;
	}
	public Soldier(int xx, int yy, int t)
	{
		this();
		x=xx;
		y=yy;
		team=t;
	}
	public Soldier(int hp, int xx, int yy, int t, boolean atk, boolean move, boolean act, boolean invi)
	{
		this(xx,yy,t);
		HP=hp;
		isAtk=atk;
		isMove=move;
		isActive=act;
		isInvi=invi;
	}
}
