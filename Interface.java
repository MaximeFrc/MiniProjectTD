/**
 * The class Interface that inherits from JPanel and implements ActionListener and MouseListener
 * to display the interact with the GamePanel 
 * 
 */

// Load libraries
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Interface extends JPanel implements ActionListener,MouseListener{
	
	//Attributes
	
	/*Components of the interface */
	public JPanel towerMenu;
	public JButton pause;
	public JButton sendMinions;
	public JButton upgradeTower;
	public JButton deleteTower;
	public JLabel towerMenuTitle;
	public JLabel description;
	public ImageIcon imgIc;
	public JButton[] towerTabButton;
	public Graphics buffer; 
	public Image wallpaper;
	public BufferedImage background;
	
	
	/* Non graphic attributes */ 
	public int widthITF;
	public int heightITF;
	public int numTowerChosen;
	public boolean isPause;
	public boolean isDeleteTower;
	public TowerDefense td;
	public Image imgPurchasedTower;
	
	
	//Methodes

	/**
	 * The construtor
	 * @param width : int that comes from the width of the TowerDefense frame
	 * @param height : int that comes from the height of the TowerDefense frame
	 * @param image : Sting to access the background image
	 * @param td : TowerDefense informations of the game
	 */
	public Interface (int width, int height, String image, TowerDefense td) {
		
		this.widthITF = 300;
		this.heightITF = height-100;
		this.numTowerChosen=-1;
		this.isPause = true;
		Font font = new java.awt.Font("MAGNETO",Font.BOLD,15);
		
		/* Identification JFrame */
		this.td = td;
		
		/* Interface initialisation */
		this.setBounds(width-widthITF,100,widthITF,heightITF);
		this.setLayout(null);
		
		/* Tower Menu initialisation */
		towerMenu = new JPanel();
		towerMenu.setBounds(50,30,200,200);
		towerMenu.setLayout(new GridLayout(4,2));
		towerMenuTitle = new JLabel("Tower Menu");
		towerMenuTitle.setBounds(110,10,140,20);
		this.add(towerMenuTitle);
		this.add(towerMenu);
		
				
		/* Description text field */
		description = new JLabel("Description :");
		description.setBounds(50,240,220,150);
		description.setBackground(new Color(100,0,0,128));
		description.setForeground(Color.WHITE);
		description.setFont(font);
		add(description);
		
		/* Pause button creation */
		pause = new JButton("PAUSE");
		pause.setBounds(160,490,130,80);
		this.add(pause);
		pause.addActionListener(this);

		/* Send Minions button creation */ 
		sendMinions = new JButton("SEND MINIONS");
		sendMinions.setBounds(10,490,130,80);
		this.add(sendMinions);
		sendMinions.addActionListener(this);
		
		
		/* Upgrade Tower button creation */ 
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
	
	/**
	 * The method to act when a button is clicked
	 * @param e : ActionEvent corresponding to the button clicked
	 */
	public void actionPerformed(ActionEvent e) {
		
		/* Use of pause */
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
		
		/* Use of sendMinion */
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
		
		/* Use of upgradeTower */
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
					description.setText("<html> Description : <br>"+td.selectedTower()+" </html>"); //html to skip a line
					td.money -= price;
				}
			}	
			isDeleteTower=false;
			deleteTower.setBackground(null);
		}
		
		/* Use of deleteTower */
		if (e.getSource() == deleteTower) {

			description.setText("<html> DELETE TOWER : <br> If you click on a tower <br> 70 % of the initial price <br> will be refund. </html>"); //html to skip a line

			// delete the tower a the last position clicked	
			if (numTowerChosen != -1) {
				towerTabButton[numTowerChosen-1].setBackground(null);
				numTowerChosen = -1;
			}
			this.isDeleteTower=true;
			deleteTower.setBackground(new Color(30,55,112));
			System.out.println("delete");
		}
			
		
	}
		
	/**
	 * The method paintComponent to paint when TowerDefense call it
	 * @param g : Graphics where we draw
	 */ 
	public void paintComponent(Graphics g) {
		buffer.drawImage( wallpaper,0,0, widthITF,heightITF,  this); 
		g.drawImage(background, 0,0,widthITF,heightITF, this);
	}
	
	/**
	 * The method mousePressed to interact with the Tour buttons
	 * @param e : MouseEvent that correspond to the mouse when we press it
	 */ 
	public void mousePressed(MouseEvent e) {
		for(int i=0; i<towerTabButton.length;i++) {
				if(towerTabButton[i] == e.getSource()){
					if (numTowerChosen != -1) {
						towerTabButton[numTowerChosen-1].setBackground(null);
					}
					towerTabButton[i].setBackground(new Color(30,55,112));
					numTowerChosen =i+1;
					try {
						int j= i+1;
						imgPurchasedTower= ImageIO.read(new File("tower"+Integer.toString(j)+".png")); 
					} catch(Exception err) {
						System.out.println(" not found !");            
						System.exit(0);    
					}
					description.setText("<html> Description : <br>"+td.selectedTower()+" </html>");//html to skip a line
				}
				else if(numTowerChosen == i+1 && towerTabButton[i] == e.getSource()){
					numTowerChosen =-1;
					towerTabButton[i].setBackground(null);
				}
		}
		isDeleteTower=false;
		deleteTower.setBackground(null);
	}
	
	/* Overload mouseListener methods */
	public void mouseEntered( MouseEvent e )  {}
	public void mouseClicked (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
}

