package troops;

import javax.swing.ImageIcon;

public class Knight extends Troop {
	public Knight()
	{
		super();
		atkRange=1;
		mov=4;
	}
	public Knight(int xx, int yy, int t)
	{
		super(xx,yy);
		panel.setName("Knight");
		HP=100;
		atkRange=1;
		mov=4;
		team=t;
		ImageIcon ic;
		if (t==1)
			ic = new ImageIcon("src/resources/Knight1.png");
		else
			ic = new ImageIcon("src/resources/Knight2.png");
		avatar.setIcon(ic);
		panel.add(avatar);
		x=xx;
		y=yy;
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
