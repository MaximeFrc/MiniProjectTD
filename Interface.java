import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class Interface extends JPanel implements ActionListener{
	
	/*Components of the interface */
	JPanel towerMenu;
	JButton pause;
	JButton sendMinions;
	JTextField towerMenuTitle;
	
	Graphics buffer; 
	Image wallpaper;
	BufferedImage background;
	
	
	/*Attributes */ 
	boolean isPause = true;
	int widthITF;
	int heightITF;
	TowerDefense td;
	
	public Interface (int width, int height, String image, TowerDefense td) {
		
		this.widthITF = 300;
		this.heightITF = height-100;
		
		/* identification JFrame */
		this.td = td;
		
		/*Interface initialisation */
		this.setBounds(width-widthITF,100,widthITF,heightITF);
		this.setLayout(null);
		this.setBackground(Color.red);
		
		/*Tower Menu initialisation*/
		towerMenu = new JPanel();
		towerMenu.setBounds(10,50,280,400);
		towerMenuTitle = new JTextField("Tower Menu");
		towerMenuTitle.setBounds(80,20,140,30);
		this.add(towerMenuTitle);
		this.add(towerMenu);
		
		/*Pause button creation*/
		pause = new JButton("PAUSE");
		pause.setBounds(160,470,130,80);
		this.add(pause);
		pause.addActionListener(this);

		
		/*Send Minions button creation */ 
		sendMinions = new JButton("SEND MINIONS");
		sendMinions.setBounds(10,470,130,80);
		this.add(sendMinions);
		sendMinions.addActionListener(this);
		
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
		else if (e.getSource() == sendMinions) {
			/*
			 * if (!isThereMinions) 
			 * 		send new wave
			 * else 
			 * 		do nothing
			 * */
			 
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
		}	
	}
	
	public void paintComponent(Graphics g) {
		buffer.drawImage( wallpaper,0,0, widthITF,heightITF,  this); 
		g.drawImage(background, 0,0,widthITF,heightITF, this);
	}
}

