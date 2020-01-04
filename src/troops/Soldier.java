package troops;

import javax.swing.ImageIcon;

public class Soldier extends Troop {
	public Soldier()
	{
		super();
		atkRange=1;
		mov=3;
	}
	public Soldier(int xx, int yy, int t)
	{
		super(xx,yy);
		panel.setName("Soldier");
		HP=100;
		atkRange=1;
		mov=3;
		team=t;
		ImageIcon ic;
		if (t==1)
			ic = new ImageIcon("src/resources/Soldier1.png");
		else
			ic = new ImageIcon("src/resources/Soldier2.png");
		avatar.setIcon(ic);
		panel.add(avatar);
		x=xx;
		y=yy;
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
