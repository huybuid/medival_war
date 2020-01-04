package windows;

import troops.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

public class gameManager {
	private static final Color FakeTransparent = Color.GRAY;
	public Color colorAtk= Color.RED;
	public Color colorMov= Color.BLUE;
	public final int mapHeight=10;
	public final int mapWidth=16;
	public final int panelWidth=48;
	public final int panelHeight=48;
	int prevx=-1, prevy=-1;
	int turn=0;
	JFrame form;
	JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem toolAtk = new JMenuItem("Attack");
	JMenuItem toolWait = new JMenuItem("Wait");
	Troop[][] map= new Troop[mapHeight][mapWidth];
	public ArrayList<Troop> team1 = new ArrayList<Troop>();
	public ArrayList<Troop> team2 = new ArrayList<Troop>();
	public  int[][] dmg = new int[][]
	        {
	            { 52, 25, 67, 82 },
	            { 77, 33, 98, 120 },
	            { 46, 18, 49, 98 },
	            { 110, 70, 100 , 15 }
	        };
	public static int[][] terrain;
	Troop p;
	private TitledBorder group;
	private JLabel picBox;
	private JLabel lbUnit;
	private JLabel lbHp;
	private JLabel lbDescription;
	
	//Methods 
	public gameManager( ) {}
	void SetTroopPosition()
    {
		team1.add(new Soldier(1,0,1));
    	team1.add(new Soldier(2,0,1));
    	team1.add(new Archer(3,0,1));
    	team1.add(new Mage(4,0,1));
    	team1.add(new Mage(5,0,1));
    	team1.add(new Archer(6,0,1));
    	team1.add(new Soldier(7,0,1));
    	team1.add(new Soldier(8,0,1));
    	team1.add(new Soldier(1,1,1));
    	team1.add(new Soldier(2,1,1));
    	team1.add(new Archer(3,1,1));
    	team1.add(new Knight(4,1,1));
    	team1.add(new Knight(5,1,1));
    	team1.add(new Archer(6,1,1));
    	team1.add(new Soldier(7,1,1));
    	team1.add(new Soldier(8,1,1));

    	team2.add(new Soldier(1,mapWidth-1,2));
    	team2.add(new Soldier(2,mapWidth-1,2));
    	team2.add(new Archer(3,mapWidth-1,2));
    	team2.add(new Mage(4,mapWidth-1,2));
    	team2.add(new Mage(5,mapWidth-1,2));
    	team2.add(new Archer(6,mapWidth-1,2));
    	team2.add(new Soldier(7,mapWidth-1,2));
    	team2.add(new Soldier(8,mapWidth-1,2));
    	team2.add(new Soldier(1,mapWidth-2,2));
    	team2.add(new Soldier(2,mapWidth-2,2));
    	team2.add(new Archer(3,mapWidth-2,2));
    	team2.add(new Knight(4,mapWidth-2,2));
    	team2.add(new Knight(5,mapWidth-2,2));
    	team2.add(new Archer(6,mapWidth-2,2));
    	team2.add(new Soldier(7,mapWidth-2,2));
    	team2.add(new Soldier(8,mapWidth-2,2));
        for (int i=1; i<9;i++)
        {
            map[i][0] = team1.get(i-1);
            map[i][1] = team1.get(i + 7);
            map[i][mapWidth-1] = team2.get(i-1);
            map[i][mapWidth-2] = team2.get(i+7);
        }
    }
	
	void HighLightMove(Troop unit, int MoveRange, Color color)
    {
        int x, y, i, move;
        ArrayList<Point> list = new ArrayList<Point>();
        int[][] StepMtrx = new int[2 * MoveRange + 1][2 * MoveRange + 3];
        for (i = 0; i < 2 * MoveRange + 1; i++)
        {
            for (int j = 0; j < 2 * MoveRange + 1; j++)
                StepMtrx[i][j] = MoveRange + 1;
        }
        StepMtrx[MoveRange][MoveRange] = 0;
        i = 0;
        while (i<list.size())
        {
            x = unit.x + list.get(i).x;
            y = unit.y + list.get(i).y;
            map[x][y].panel.setBackground(color);
            if (x > 0)
            {
                move = StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y] + terrain[x - 1][y];
                if (move <= MoveRange && ( map[x - 1][y].team == turn || map[x - 1][y].team == 0) &&
                    StepMtrx[MoveRange + list.get(i).x - 1][MoveRange + list.get(i).y] >= move)
                {
                    if (!list.contains(new Point(list.get(i).x - 1, list.get(i).y)))
                    {
                        list.add(new Point(list.get(i).x - 1, list.get(i).y));
                    }
                    StepMtrx[MoveRange + list.get(i).x - 1][MoveRange + list.get(i).y] =
                        move;
                }
            }
            if (y > 0)
            {
                move = StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y] + terrain[x][y - 1];
                if (move <= MoveRange && (map[x][y - 1].team == turn || map[x][y - 1].team == 0) &&
                    StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y - 1] >= move)
                {
                    if (!list.contains(new Point(list.get(i).x, list.get(i).y - 1)))
                    {
                        list.add(new Point(list.get(i).x, list.get(i).y - 1));
                    }
                    StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y - 1] =
                            move;
                }
            }
            if (x < mapHeight-1)
            {
                move = StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y] + terrain[x + 1][y];
                if (move <= MoveRange && StepMtrx[MoveRange + list.get(i).x + 1][MoveRange + list.get(i).y] >=
                    move && (map[x + 1][y].team == turn || map[x + 1][y].team == 0))
                {
                    if (!list.contains(new Point(list.get(i).x + 1, list.get(i).y)))
                    {
                        list.add(new Point(list.get(i).x + 1, list.get(i).y));
                    }
                    StepMtrx[MoveRange + list.get(i).x + 1][MoveRange + list.get(i).y] =
                        move;
                }
            }
            if (y < mapWidth-1)
            {
                move = StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y] + terrain[x][y + 1];
                if (move <= MoveRange && (map[x][y + 1].team == turn || map[x][y + 1].team == 0) &&
                    StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y + 1] >= move)
                {
                    if (!list.contains(new Point(list.get(i).x, list.get(i).y + 1)))
                    {
                        list.add(new Point(list.get(i).x, list.get(i).y + 1));
                    }
                    StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y + 1] =
                            move;
                }
            }
            i++;
        }
    }

    void HighLightAtk(Troop unit, int dist, int AtkRange, boolean lck)
    {
        if (dist > AtkRange) return;
        int x = unit.x, y = unit.y;
        unit.panel.setBackground((lck)? FakeTransparent: colorAtk);
        if (x > 0 && map[x - 1][y].panel.getBackground() != ((lck) ? FakeTransparent : colorAtk))
            HighLightAtk(map[x - 1][y], Math.abs(((lck) ? map[prevx][prevy] : p).x - (x - 1)) + 
                Math.abs(((lck) ? map[prevx][prevy] : p).y - y), AtkRange, lck);
        if (x < mapHeight - 1 && map[x + 1][y].panel.getBackground() != ((lck) ? FakeTransparent : colorAtk))
            HighLightAtk(map[x + 1][y], Math.abs(((lck) ? map[prevx][prevy] : p).x - (x + 1))
                + Math.abs(((lck) ? map[prevx][prevy] : p).y - y), AtkRange, lck);
        if (y > 0 && map[x][y - 1].panel.getBackground() != ((lck) ? FakeTransparent : colorAtk))
            HighLightAtk(map[x][y - 1], Math.abs(((lck) ? map[prevx][prevy] : p).x - x) 
                + Math.abs(((lck) ? map[prevx][prevy] : p).y - (y - 1)), AtkRange, lck);
        if (y < mapWidth - 1 && map[x][y + 1].panel.getBackground() != ((lck) ? FakeTransparent : colorAtk))
            HighLightAtk(map[x][y + 1], Math.abs(((lck) ? map[prevx][prevy] : p).x - x) 
                + Math.abs(((lck) ? map[prevx][prevy] : p).y - (y + 1)), AtkRange, lck);
        if (unit.team == turn || unit.team == 0 || lck)
            unit.panel.setBackground(null);
    }
	
    public void Move()
    {
        //sound_Move.Play();
        //HighLightMove(p, p.area, MoveColor);
        prevx = p.x;
        prevy = p.y;
    }

    //Tấn công
    public void Attack()
    {
        /*switch (p.panel.Name)
        {
            case "Soldier": sound_SwordAtk.Play(); break;
            case "Knight": sound_SwordAtk.Play(); break;
            case "Archer": sound_Arrow.Play(); break;
            case "Wizard": sound_Wiz.Play(); break;
            default: sound_Move.Play(); break;
        }*/
        HighLightAtk(p, 0, p.atkRange, false);
        prevx = p.x;
        prevy = p.y;
    }
    
	void Win(boolean teamwin)
    {
		
    }
	
	int getDmg(String s)
    {
        switch (s)
        {
            case "Soldier":return 0;
            case "Knight":return 1;
            case "Archer": return 2;
            case "Wizard":return 3;
            default:return 0;
        }
    };
    
    void DeleteUnit(Troop unit, int t)
    {
        unit.isActive=false;
        unit.isAtk = true;
        unit.isMove = true;
        unit.team = 0;
        unit.panel = null;
        if (t == 1)
        {
            team2.remove(unit);
            if (team2.size() <= 0)
            {
                Win(true);
            }
        }
        else
        {
            team1.remove(unit);
            if (team1.size()<=0)
            {
                Win(false);
            }
        }
    }
	
	void DamageCalc(Troop atker, Troop defer)
    {
        Random random = new Random();
        int i=getDmg(atker.getClass().getName()), j=getDmg(defer.getClass().getName());
        int damage = (int)(dmg[i][j] - (dmg[i][j] * 0.8 * (((double)100 - atker.HP) / 100))) -3 + random.nextInt(7);
        defer.HP -= damage;
        if (defer.HP <= 0)
        {
            DeleteUnit(defer, turn);
        }
        else if (defer.atkRange==atker.atkRange && atker.atkRange==1)
        {
            damage = (int) (dmg[j][i] - (dmg[j][i] * 0.8 * (((double)100 - defer.HP) / 100))) -3 + random.nextInt(7);
            atker.HP -= damage;
            if (atker.HP<=0)
            {
                if (turn == 1)
                    DeleteUnit(atker, 2);
                else DeleteUnit(atker, 1);
            }
        }
        atker.isActive = false;
        atker.isAtk = true;
    }
	
	//Tạo map
	public void CreateFrontPanel(JFrame f, TitledBorder gb, JLabel pic, JLabel lb1, JLabel lb2, JLabel lb3, int n)
    {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem toolAtk = new JMenuItem("Attack");
		popupMenu.add(toolAtk);
		popupMenu.add(toolWait);
        SetTroopPosition();
        switch (n)
        {
            case 1:
                terrain = new int[][]
                {
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,2,1,1,2,1,1,1,2,1,1,1,1,1},
                    {1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1},
                    {1,1,1,1,2,1,1,2,1,1,1,1,2,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1}
                };
                break;
            case 2:
                terrain = new int[][]
                {
                    {1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1},
                    {1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1},
                    {1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1},
                    {1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1},
                    {1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,2,1,1,2,1,1,1,1,1,1,2,1,1,1}
                };
                break;
            case 3:
                terrain = new int[][]
                {
                    {1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1},
                    {1,1,1,2,2,1,1,1,1,1,1,1,2,1,1,1},
                    {1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1},
                    {1,1,2,1,2,1,1,1,1,2,1,1,1,1,1,1}
                };
                break;
        }
        form = f;
        for (int i = 0; i < mapHeight; i++)
        {
            for(int j = 0; j < mapWidth; j++)
            {
                if (map[i][j] == null)
                {
                    map[i][j] = new Troop(i,j);
                }
                form.add(map[i][j].panel);
                //Add click event cho tất cả mọi panel
                map[i][j].panel.addMouseListener(new MouseAdapter()
                	{
						public void mouseClicked(MouseEvent e)
                		{
                			
                		}
                		public void mouseEntered(MouseEvent e)
                		{
                			JPanel unit=(JPanel) e.getSource();
                			lbUnit.setText(unit.getClass().toString());
                			ImageIcon icon = null;
                            picBox.setIcon(icon);
                            if (lbUnit.getText() != "None")
                            {
                                lbHp.setText(Integer.toString(map[unit.getLocation().x / panelWidth][unit.getLocation().y / panelHeight].HP));
                                switch (unit.getClass().toString())
                                {
                                    case "Soldier":
                                        icon=new ImageIcon("src/resources/SoldierP.png");
                                        lbDescription.setText("Binh lính cận chiến cơ bản, trang bị giáp và kiếm \nTầm di chuyển: 3\nTầm đánh: 1");
                                        break;
                                    case "Archer":
                                    	icon=new ImageIcon("src/resources/ArcherP.png");
                                        lbDescription.setText("Xạ thủ. Linh hoạt, nhưng sát thương không nhiều\nDi chuyển: 3\nTầm đánh: 3");
                                        break;
                                    case "Knight":
                                    	icon=new ImageIcon("src/resources/KnightP.png");
                                        lbDescription.setText("Hiệp sĩ dũng mãnh, thiện chiến vô song. Kiếm và giáp cực xịn\nDi chuyển: 4\nTầm đánh: 1");
                                        break;
                                    case "Mage":
                                    	icon=new ImageIcon("src/resources/WizardP.png");
                                        lbDescription.setText("Phù thủy với ma pháp cao siêu, chưởng phát chết luôn\nDi chuyển: 2\nTầm đánh: 2");
                                        break;
                                    default:
                                    	icon=new ImageIcon("src/resources/Blank.png");
                                        lbDescription.setText("N/A");
                                        break;
                                }
                            }
                            else
                            {

                                lbHp.setText("");
                                lbDescription.setText("");
                            }
                		}
                	});
            }
        }
        lbUnit = lb1;
        lbHp = lb2;
        lbDescription = lb3;
        group = gb;
        picBox = pic;
        EndTurn();
        form.setVisible(true);
    }
	
	public void EndTurn()
    {
        //Nếu đang có vùng highlight được bung
        if (prevx > -1)
        {
        	HighLightMove(map[prevx][prevy], map[prevx][prevy].mov, null);
            HighLightAtk(map[prevx][prevy], 0, map[prevx][prevy].atkRange, true);
            prevx = -1;
        }
        if (turn == 1)
        {
            turn = 2;
            group.setTitle("Player 2");
            for (Troop unit : team2)
            {
                unit.isActive = true;
            }
            for (Troop unit : team1)
            {
                unit.isActive = false;
                unit.isAtk = false;
                unit.isMove = false;
            }
        }
        else
        {
            turn = 1;
            group.setTitle("Player 1");
            for (Troop unit : team1)
            {
                unit.isActive = true;
            }
            for (Troop unit : team2)
            {
                unit.isActive = false;
                unit.isAtk = false;
                unit.isMove = false;
            }
        }
    }
}
