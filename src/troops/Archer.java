package troops;

import javax.swing.ImageIcon;

public class Archer extends Troop {
	public Archer()
	{
		super();
		HP=100;
		atkRange=3;
		mov=3;
	}
	public Archer(int xx, int yy, int t)
	{
		super(xx,yy);
		panel.setName("Archer");
		HP=100;
		atkRange=3;
		mov=3;
		team=t;
		ImageIcon ic;
		if (t==1)
			ic = new ImageIcon("src/resources/Archer1.png");
		else
			ic = new ImageIcon("src/resources/Archer2.png");
		avatar.setIcon(ic);
		panel.add(avatar);
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
