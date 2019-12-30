package troops;

public class Mage extends Troop {
	public Mage()
	{
		super();
		atkRange=3;
		mov=3;
	}
	public Mage(int xx, int yy, int t)
	{
		this();
		x=xx;
		y=yy;
		team=t;
	}
	public Mage(int hp, int xx, int yy, int t, boolean atk, boolean move, boolean act, boolean invi)
	{
		this(xx,yy,t);
		HP=hp;
		isAtk=atk;
		isMove=move;
		isActive=act;
		isInvi=invi;
	}
}
