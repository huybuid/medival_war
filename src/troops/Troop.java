package troops;
import javax.swing.*;

public class Troop {
	public int HP, atkRange, team, mov, x, y;
	public boolean isAtk, isMove, isActive, isInvi;
	final int panelSize = 48;
	public JPanel panel;
	public JLabel avatar;
	public Troop() 
	{
		isActive=true;
		isMove=false;
		isAtk=false;
		isInvi=false;
		HP=100;
	}
	public Troop(int xx, int yy)
	{
		this();
		this.x=xx;
		this.y=yy;
	}
}
