package troops;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Troop {
	public int HP, atkRange, team, mov, x, y;
	public boolean isAtk, isMove, isActive, isInvi;
	final int panelSize = 48;
	public JPanel panel= new JPanel();
	public JLabel avatar= new JLabel();
	public Troop() 
	{
		isActive=false;
		isMove=false;
		isAtk=false;
		isInvi=false;
		panel.setName("None");
	}
	public Troop(int xx, int yy)
	{
		this();
		this.x=xx;
		this.y=yy;
		panel.setOpaque(true);
		panel.setBounds(yy*panelSize,xx*panelSize,panelSize,panelSize);
		panel.setBackground(new Color(0,0,0,0));
	}
}
