import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Interface extends JPanel implements ActionListener,MouseListener{
	
	/*Components of the interface */
	JPanel towerMenu;
	JButton pause;
	JButton sendMinions;
	JButton upgradeTower;
	JButton deleteTower;
	JLabel towerMenuTitle;
	JLabel description;
	ImageIcon imgIc;
	JButton[] towerTabButton;
	Graphics buffer; 
	Image wallpaper;
	BufferedImage background;
	
	
	/*Attributes */ 
	boolean isPause = true;
	int widthITF;
	int heightITF;
	TowerDefense td;
	int numTowerChosen=-1;
	Image imgPurchasedTower;
	boolean isDeleteTower;
	
	public Interface (int width, int height, String image, TowerDefense td) {
		
		this.widthITF = 300;
		this.heightITF = height-100;
		Font font = new java.awt.Font("MAGNETO",Font.BOLD,15);
		
		/* identification JFrame */
		this.td = td;
		
		/*Interface initialisation */
		this.setBounds(width-widthITF,100,widthITF,heightITF);
		this.setLayout(null);
		this.setBackground(Color.red);
		
		/*Tower Menu initialisation*/
		towerMenu = new JPanel();
		towerMenu.setBounds(50,30,200,200);
		towerMenu.setLayout(new GridLayout(4,2));
		towerMenuTitle = new JLabel("Tower Menu");
		towerMenuTitle.setBounds(110,10,140,20);
		this.add(towerMenuTitle);
		this.add(towerMenu);
		
				
		/*Description text field*/
		description = new JLabel("Description :");
		description.setBounds(60,240,180,150);
		description.setBackground(new Color(100,0,0,128));
		description.setForeground(Color.WHITE);
		description.setFont(font);
		add(description);
		
		/*Pause button creation*/
		pause = new JButton("PAUSE");
		pause.setBounds(160,490,130,80);
		this.add(pause);
		pause.addActionListener(this);

		/*Send Minions button creation */ 
		sendMinions = new JButton("SEND MINIONS");
		sendMinions.setBounds(10,490,130,80);
		this.add(sendMinions);
		sendMinions.addActionListener(this);
		
		
		/*Upgrade Tower button creation */ 
		upgradeTower = new JButton("UPGRADE TOWER");
		upgradeTower.setBounds(10,400,140,80);
		this.add(upgradeTower);
		upgradeTower.addActionListener(this);
		
		
		/*Delete Tower button creation */ 
		deleteTower = new JButton("DELETE TOWER");
		deleteTower.setBounds(150,400,140,80);
		this.add(deleteTower);
		deleteTower.addActionListener(this);
		
		
		/*Creation of the towers buttons*/
		towerTabButton =new JButton[8];
		for(int i=0;  i<towerTabButton.length;i++) {
			int j = i+1;
			imgIc= new ImageIcon("tower"+Integer.toString(j)+".png");
			towerTabButton[i] = new JButton(imgIc);
			towerMenu.add(towerTabButton[i]);
			towerTabButton[i].addMouseListener(this);
		}
		
		
		
		/*Creation of the tool to get images*/
		Toolkit T=Toolkit.getDefaultToolkit();
		
		/*Creation of the background in which we will draw"*/
		background = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
		
		/*Everything is written is the background */
		buffer = background.getGraphics(); 
		
		/*Loading the wallpaper*/
		wallpaper = T.getImage(image);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if ( e.getSource() == pause ) {
			if(!isPause) {
				isPause = true;
				pause.setText("PAUSE");
			}
			else {
				isPause = false;
				pause.setText("RESUME");
			}
		}
		if (e.getSource() == sendMinions) {
			boolean thereIsMinions = false;
			for (int i = 0; i<td.tabMinion.length ; i++) {
				if (td.tabMinion[i].position!=null) {
					thereIsMinions = true;
				}
			}
			if (!thereIsMinions) {
				td.nextLevel();
				td.minionToCreate = 0;
				td.creatingMinions = true;
			}	
			isDeleteTower=false;
			deleteTower.setBackground(null);	
		}
		
		if (e.getSource() == upgradeTower) {
			if(numTowerChosen!=-1) {
				int price = 0;
				if(td.selectedTower().numUpgrade==0) {
					price = td.selectedTower().priceUpgrade1;
				} else if (td.selectedTower().numUpgrade==1) {
					price = td.selectedTower().priceUpgrade2;
				}
				if (td.money >= price) {
					td.selectedTower().upgrade();
					description.setText("<html> Description : <br>"+td.selectedTower()+" </html>");
					td.money -= price;
				}
			}	
			isDeleteTower=false;
			deleteTower.setBackground(null);
		}
		
		if (e.getSource() == deleteTower) {
			// delete the tower a the next position clicked
			description.setText("<html> DELETE TOWER : <br> If you click on a tower <br> 80 % of the initial price <br> will be refund. </html>");	
			this.isDeleteTower=true;
			deleteTower.setBackground(new Color(30,55,112));
		}
			
		
	}
	
	public void paintComponent(Graphics g) {
		buffer.drawImage( wallpaper,0,0, widthITF,heightITF,  this); 
		g.drawImage(background, 0,0,widthITF,heightITF, this);
	}
	
	public void mousePressed(MouseEvent e) {
		for(int i=0; i<towerTabButton.length;i++) {
				if(numTowerChosen==-1 && towerTabButton[i] == e.getSource()){
					towerTabButton[i].setBackground(new Color(30,55,112));
					numTowerChosen =i+1;
					try {
						int j= i+1;
						imgPurchasedTower= ImageIO.read(new File("tower"+Integer.toString(j)+".png")); 
					} catch(Exception err) {
						System.out.println(" not found !");            
						System.exit(0);    
					}
					description.setText("<html> Description : <br>"+td.selectedTower()+" </html>");
				}
				else if(numTowerChosen == i+1 && towerTabButton[i] == e.getSource()){
					numTowerChosen =-1;
					towerTabButton[i].setBackground(null);
				}
		}
		isDeleteTower=false;
		deleteTower.setBackground(null);
	}
	
	public void mouseEntered( MouseEvent e )  {}
	public void mouseClicked (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
}

