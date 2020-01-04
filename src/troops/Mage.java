package troops;

import javax.swing.ImageIcon;

public class Mage extends Troop {
	public Mage()
	{
		super();
		atkRange=3;
		mov=3;
	}
	public Mage(int xx, int yy, int t)
	{
		super(xx,yy);
		panel.setName("Mage");
		HP=100;
		atkRange=4;
		mov=2;
		team=t;
		ImageIcon ic;
		if (t==1)
			ic = new ImageIcon("src/resources/Wizard1.png");
		else
			ic = new ImageIcon("src/resources/Wizard2.png");
		avatar.setIcon(ic);
		panel.add(avatar);
		x=xx;
		y=yy;
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
