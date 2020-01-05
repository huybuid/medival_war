package windows;

//import java.awt.EventQueue;



import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import troops.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;


public class gameWindow {

	JFrame frame;
	JPanel btnpanel = new JPanel();
	JPanel infopanel = new JPanel();
	JLabel lbPortrait = new JLabel();
	JLabel lbUnit = new JLabel();
	JLabel lbHP = new JLabel();
	JLabel lbDescription = new JLabel();
	TitledBorder border=BorderFactory.createTitledBorder("Player 1");
	gameManager game=new gameManager();
	private ImageIcon iconbackground;
	
	private static final Color FakeTransparent = new Color(255,255,255,1);
	private static final Color TrueTransparent = new Color(255,255,255,0);
	private static final ImageIcon iconSoldier=new ImageIcon("src/resources/SoldierP.png");
	private static final ImageIcon iconArcher=new ImageIcon("src/resources/ArcherP.png");
	private static final ImageIcon iconKnight=new ImageIcon("src/resources/KnightP.png");
	private static final ImageIcon iconMage=new ImageIcon("src/resources/WizardP.png");
	private static final ImageIcon iconNone=new ImageIcon("src/resources/Blank.png");
	public Color colorAtk= new Color(255,0,0,100);
	public Color colorMov= new Color(0,89,255,100);
	public final int mapHeight=10;
	public final int mapWidth=16;
	public final int panelWidth=48;
	public final int panelHeight=48;
	int prevx=-1, prevy=-1;
	int turn=0;
	JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem toolMove= new JMenuItem("Move");
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
	ArrayList<Point> list = new ArrayList<Point>();
	Troop p;

	/**
	 * Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameWindow window = new gameWindow(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 * Create the application.
	 */
	public gameWindow(int i) {
		switch (i) {
		case 1:
			iconbackground= new ImageIcon("src/resources/GrassTerrain.png");
			break;
		case 2:
			iconbackground= new ImageIcon("src/resources/IceTerrain.png");
			break;
		case 3:
			iconbackground= new ImageIcon("src/resources/DessertTerrain.png");
			break;
		}
		initialize(i);
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int i) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 645);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		toolWait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.isActive = false;
	            p.isAtk = true;
	            p.isMove = true;
			}
		});
		toolAtk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Attack();
			}
		});
		toolMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Move();
			}
		});

		btnpanel.setBounds(926, 10, 260, 190);
		btnpanel.setBorder(border);
		frame.getContentPane().add(btnpanel);
		btnpanel.setLayout(null);
		
		JButton btnNewButton = new JButton("End Turn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EndTurn();
			}
		});
		btnNewButton.setBounds(13, 25, 235, 68);
		btnpanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Surrender");
		btnNewButton_1.setBounds(13, 109, 235, 68);
		btnpanel.add(btnNewButton_1);
		

		infopanel.setBounds(926, 210, 260, 399);
		infopanel.setBorder(BorderFactory.createTitledBorder("Information"));
		frame.getContentPane().add(infopanel);
		infopanel.setLayout(null);
		

		lbPortrait.setBounds(37, 20, 190, 245);
		infopanel.add(lbPortrait);
		

		lbUnit.setBounds(160, 270, 45, 13);
		infopanel.add(lbUnit);
		

		lbHP.setBounds(160, 290, 45, 13);
		infopanel.add(lbHP);
		lbDescription.setHorizontalAlignment(SwingConstants.TRAILING);
		

		lbDescription.setBounds(10, 320, 240, 76);
		infopanel.add(lbDescription);
		
		JLabel lblNewLabel = new JLabel("Unit:");
		lblNewLabel.setBounds(70, 270, 45, 13);
		infopanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("HP:");
		lblNewLabel_1.setBounds(70, 290, 45, 13);
		infopanel.add(lblNewLabel_1);
		this.CreateFrontPanel(i);
		JLabel lbbackground = new JLabel(iconbackground);
		lbbackground.setBounds(0, 0, 768, 480);
		frame.getContentPane().add(lbbackground);
        frame.setVisible(true);
	}
	//gameManager
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
            System.out.println(map[i][0] == team1.get(i-1));
            System.out.println(map[i][0].equals(team1.get(i-1)));
            map[i][1] = team1.get(i + 7);
            map[i][mapWidth-1] = team2.get(i-1);
            map[i][mapWidth-2] = team2.get(i+7);
        }
    }
	
	void SetupButtons()
    {
        if (p.team == turn && p.isActive)
        {
            toolMove.setEnabled(!(p.isMove));
            toolAtk.setEnabled(!(p.isAtk));
        }
        else
        {
            toolAtk.setEnabled(false);
            toolMove.setEnabled(false);
        }
    }
	
	void HighLightMove(Troop unit, int MoveRange, Color color)
    {
        int x, y, i, move;
        if (prevx==-1)
        {
        list.clear();
        list.add(new Point(0,0));
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
                    StepMtrx[MoveRange + list.get(i).x - 1][MoveRange + list.get(i).y] = move;
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
                    StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y - 1] = move;
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
                    StepMtrx[MoveRange + list.get(i).x + 1][MoveRange + list.get(i).y] = move;
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
                    StepMtrx[MoveRange + list.get(i).x][MoveRange + list.get(i).y + 1] =  move;
                }
            }
            i++;
        }
        }
        else
        {
        	for (i=0;i<list.size();i++)
        	{
        		x = unit.x + list.get(i).x;
                y = unit.y + list.get(i).y;
                map[x][y].panel.setBackground(color);
                map[x][y].panel.setForeground(TrueTransparent);
        	}
        }
    }

    void HighLightAtk(Troop unit, int dist, int AtkRange, boolean lck)
    {
        if (dist > AtkRange) return;
        int x = unit.x, y = unit.y;
        unit.panel.setBackground((lck)? TrueTransparent: colorAtk);
        if (x > 0 && map[x - 1][y].panel.getBackground() != ((lck) ? TrueTransparent : colorAtk))
            HighLightAtk(map[x - 1][y], Math.abs(((lck) ? map[prevx][prevy] : p).x - (x - 1)) + 
                Math.abs(((lck) ? map[prevx][prevy] : p).y - y), AtkRange, lck);
        if (x < mapHeight - 1 && map[x + 1][y].panel.getBackground() != ((lck) ? TrueTransparent : colorAtk))
            HighLightAtk(map[x + 1][y], Math.abs(((lck) ? map[prevx][prevy] : p).x - (x + 1))
                + Math.abs(((lck) ? map[prevx][prevy] : p).y - y), AtkRange, lck);
        if (y > 0 && map[x][y - 1].panel.getBackground() != ((lck) ? TrueTransparent : colorAtk))
            HighLightAtk(map[x][y - 1], Math.abs(((lck) ? map[prevx][prevy] : p).x - x) 
                + Math.abs(((lck) ? map[prevx][prevy] : p).y - (y - 1)), AtkRange, lck);
        if (y < mapWidth - 1 && map[x][y + 1].panel.getBackground() != ((lck) ? TrueTransparent : colorAtk))
            HighLightAtk(map[x][y + 1], Math.abs(((lck) ? map[prevx][prevy] : p).x - x) 
                + Math.abs(((lck) ? map[prevx][prevy] : p).y - (y + 1)), AtkRange, lck);
        if (unit.team == turn || unit.team == 0 || lck)
            unit.panel.setBackground(TrueTransparent);
    }
	
    public void Move()
    {
        //sound_Move.Play();
        HighLightMove(p, p.mov, colorMov);
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
    
    /*void RemoveHighlight(int l)
    {
    	if (prevx==-1) return;
    	int i,j,k;
    	j=-1;
    	for(i=prevx-l;i<prevx+l;i++)
    	{
    		if (i>0 && i<mapWidth-1)
    		{
    			int k=Math.abs(j);
    			for (j;j<=Math.abs(j);j++)
    			{}
    		}
    		if (i<prevx)
    			j-=2;
    		else
    			j+=2;
    	}
    }*/
	
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
	public void CreateFrontPanel(int n)
    {
		popupMenu.add(toolMove);
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
        MouseAdapter listener= new MouseAdapter()
    	{
        	@Override
			public void mouseClicked(MouseEvent e) {
            	System.out.println("clicked");
				JPanel clicked = (JPanel) e.getSource();
	            p=map[clicked.getLocation().y/panelWidth][clicked.getLocation().x/panelHeight];
	            //Nếu không có highlight => xóa hết highlight
	            if (clicked.getBackground().getRGB()==TrueTransparent.getRGB())
	            {
	                SetupButtons();
	                if (p.team == turn && p.isActive == true && prevx == -1)
	                    showMenu(e);
	                if (prevx!=-1)
	                {
	                    HighLightMove(map[prevx][prevy], map[prevx][prevy].mov, TrueTransparent);
	                    HighLightAtk(map[prevx][prevy],0,map[prevx][prevy].atkRange,true);
	                    prevx = -1;
	                    return;
	                }
	            }
	            //Nếu chưa chọn unit (để move hoặc attack)
	            if (prevx==-1)
	            {
	                //Nếu là unit được active => SetupButtons
	                if (p.isActive == true)
	                {
	                    SetupButtons();
	                    return;
	                }
	            }
	            //Neu da co prevX
	            else
	            {
	                //Nếu không có gì và có highlight => di chuyen
	                if (clicked.getName()=="None" && clicked.getBackground().getRGB() == colorMov.getRGB())
	                {
	                    {
	                        HighLightMove(map[prevx][prevy], map[prevx][prevy].mov, TrueTransparent);
	                        Troop temp = map[p.x][p.y];
	                        map[p.x][p.y] = map[prevx][prevy];
	                        map[prevx][prevy] = temp;
	                        map[p.x][p.y].x = temp.x;
	                        map[p.x][p.y].y = temp.y;
	                        map[p.x][p.y].panel.setLocation(p.y * panelWidth, p.x * panelHeight);
	                        map[p.x][p.y].isMove = true;
	                        map[prevx][prevy].panel.setLocation(prevy * panelWidth, prevx * panelHeight);
	                        map[prevx][prevy].x = prevx;
	                        map[prevx][prevy].y = prevy;
	                        
	                        prevx = -1;
	                        prevy = -1;
	                        toolMove.setEnabled(false);
	                        return;
	                    }
	                }
	                if (clicked.getName() != "None")
	                {
	                    //Nếu click vào unit khác thì bung move unit đó
	                    if ((p.x!=prevx || p.y!=prevy) && clicked.getBackground().getRGB() == colorMov.getRGB())
	                    {
	                        HighLightMove(map[prevx][prevy], map[prevx][prevy].mov, TrueTransparent);
	                        SetupButtons();
	                        prevx = p.x;
	                        prevy = p.y;
	                        return;
	                    }
	                    //Nếu unit được highlight tấn công
	                    if (clicked.getBackground().getRGB()==colorAtk.getRGB())
	                    {
	                        HighLightAtk(map[prevx][prevy],0,map[prevx][prevy].atkRange, true);
	                        DamageCalc(map[prevx][prevy],p);
	                        prevx = -1;
	                        return;
	                    }
	                }                    
	            }
			}
			private void showMenu(MouseEvent e) {
				SetupButtons();
				popupMenu.show(e.getComponent(), e.getX()+5, e.getY()+5);
			}
    		public void mouseEntered(MouseEvent e)
    		{
    			JPanel unit=(JPanel) e.getSource();
    			lbUnit.setText(unit.getName());                
                if (lbUnit.getText() != "None")
                {
                    lbHP.setText(Integer.toString(map[unit.getLocation().y / panelWidth][unit.getLocation().x / panelHeight].HP));
                    switch (unit.getName())
                    {
                        case "Soldier":
                        	lbPortrait.setIcon(iconSoldier);
                            lbDescription.setText("Binh lính cận chiến cơ bản, trang bị giáp và kiếm \nTầm di chuyển: 3\nTầm đánh: 1");
                            break;
                        case "Archer":
                        	lbPortrait.setIcon(iconArcher);
                            lbDescription.setText("Xạ thủ. Linh hoạt, nhưng sát thương không nhiều\nDi chuyển: 3\nTầm đánh: 3");
                            break;
                        case "Knight":
                        	lbPortrait.setIcon(iconKnight);
                            lbDescription.setText("Hiệp sĩ dũng mãnh, thiện chiến vô song. Kiếm và giáp cực xịn\nDi chuyển: 4\nTầm đánh: 1");
                            break;
                        case "Mage":
                        	lbPortrait.setIcon(iconMage);
                            lbDescription.setText("Phù thủy với ma pháp cao siêu, chưởng phát chết luôn\nDi chuyển: 2\nTầm đánh: 2");
                            break;
                        default:
                        	lbPortrait.setIcon(iconNone);
                            lbDescription.setText("N/A");
                            break;
                    }
                }
                else
                {
                	lbUnit.setText("");
                    lbHP.setText("");
                    lbDescription.setText("");
                }
    		}
    	};
        for (int i = 0; i < mapHeight; i++)
        {
            for(int j = 0; j < mapWidth; j++)
            {
                if (map[i][j] == null)
                {
                    map[i][j] = new Troop(i,j);
                }
                frame.getContentPane().add(map[i][j].panel);
                //Add click event cho tất cả mọi panel
                map[i][j].panel.addMouseListener(listener);
            }
        }
        EndTurn();
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
            border=BorderFactory.createTitledBorder("Player 2");
            btnpanel.setBorder(border);
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
            border=BorderFactory.createTitledBorder("Player 1");
            btnpanel.setBorder(border);
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
