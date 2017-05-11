package com.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


public class MyGamePanel extends JFrame{		
		/*public MyGamePanel(){
			JMenuBar menuBar=new JMenuBar();
			setJMenuBar(menuBar);
			JMenu fileMenu=new JMenu("\u6587\u4EF6(F)");
			fileMenu.setFont(new Font("΢���ź�",Font.PLAIN,16));
			menuBar.add(fileMenu);
		}*/
		public static void main(String[] args){
			JFrame windows=new JFrame();
			Image logo=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/logo.jpg"));
			windows.setIconImage(logo);
			windows.setTitle("TankWar!!");
			windows.setSize(735,638);
			windows.setLocationRelativeTo(null);
			windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windows.setVisible(true);
			MyPanel myPanel=new MyPanel();
			windows.add(myPanel);
			windows.addKeyListener(myPanel);
			Thread t=new Thread(myPanel);
			t.start();
		}
}
class MyPanel extends JPanel implements KeyListener,Runnable{
	
	boolean gameLife=true;
	boolean winGame=false;
	boolean lowGame=false;
	boolean begin=false;
	boolean game=true;
	
	Hero hero=null;  //��ʼ�����̹��
	
	Vector<Enemy> ets=new Vector<Enemy>();//��ʼ������̹��
	int enSize=5; //����̹������
	int allSize=80;
	int count=0;
	
	//����ը������
	Vector<Bomb> bombs=new Vector<Bomb>();
	Vector<prop> props=new Vector<prop>();
	Vector<Nbomb> Nbombs=new Vector<Nbomb>();
	
	Image missile=null;
	Image bomb=null;
	
	//��������ͼƬ��ʵ��һ��ը��
	Image bomb1=null;
	Image bomb2=null;
	Image bomb3=null;
	Image bomb4=null;
	
	Random r=new Random();
	//���忪ʼ����ͼƬ
	Image beg=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/begin.png"));
	//���屳��ͼƬ
	Image background1=null;
	Image background2=null;
	//������ʯ����
	Vector<Stone> stones=new Vector<Stone>();
	//������ʯͼƬ
	Image sto=null;
	//��ʯ����
	int stoneSize=5;
	
	//����ǽ�弯��
	Vector<Wall> walls=new Vector<Wall>();
	//����ǽ��ͼƬ
	Image wal=null;
	
	//����ɭ�ּ���
	Vector<Lawn> lawns=new Vector<Lawn>();
	//����ɭ��ͼƬ
	Image law=null;
	
	//�����ܲ�����
	Vector<Boss> bs=new Vector<Boss>();
	//�����ܲ�ͼƬ
	Image bos=null;
	
	//�������ͼƬ
	Image over=null;
	//����ʤ��ͼƬ
	Image win=null;
	//�������̹��ͼƬ
	Image hero0=null;
	Image hero1=null;
	Image hero2=null;
	Image hero3=null;
	//�������̹��ͼƬ
	Image enemy_1_0=null;
	Image enemy_1_1=null;
	Image enemy_1_2=null;
	Image enemy_1_3=null;
	Image enemy_2_0=null;
	Image enemy_2_1=null;
	Image enemy_2_2=null;
	Image enemy_2_3=null;
	
	public MyPanel(){
		this.beginGame();
		for(int i=0;i<1;i++){
			this.repaint();
		}
	}
	//��ʼ����Աλ��
	public void beginGame(){
		if(gameLife==true){
			//����ͼƬ
			bomb=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb.png"));
			missile=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/Nbomb.png"));
			Image bullet=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bullet.png"));
			//���岢��ʼ�����̹��ͼƬ
			hero0=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/hero0.png"));
			hero1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/hero1.png"));
			hero2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/hero2.png"));
			hero3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/hero3.png"));
			//���岢��ʼ������̹��
			enemy_1_0=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/enemy_1_0.png"));
			enemy_1_1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/enemy_1_1.png"));
			enemy_1_2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/enemy_1_2.png"));
			enemy_1_3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/enemy_1_3.png"));
			
			enemy_2_0=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/enemy_2_0.png"));
			enemy_2_1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/enemy_2_1.png"));
			enemy_2_2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/enemy_2_2.png"));
			enemy_2_3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/enemy_2_3.png"));
		//��ʼ�����̹��
		hero=new Hero(230,550);
		//��ʼ������̹��
		for(int i=0;i<enSize;i++){
			Enemy enemy=new Enemy(50*r.nextInt(10),0,0);
			ets.add(enemy);
			Thread t=new Thread(enemy);
			t.start();
		}
		//��ʼ������ͼƬ
		background1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/background.png"));
		background2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/background2.png"));
		//��ʼ���ܲ�ͼƬ
		bos=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/boss.png"));
		//��ʼ��ը��ͼƬ
		bomb1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/zhadan1.png"));
		bomb2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/zhadan2.png"));
		bomb3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/zhadan3.png"));
		bomb4=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/zhadan4.png"));
		
		//��ʼ����ʯͼƬ
		sto=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/stone.png"));
		//��ʼ��ǽ��ͼƬ
		wal=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/wall.png"));
		//��ʼ��ɭ��ͼƬ
		law=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/lawn.png"));
		//��ʼ������ͼƬ
		over=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/GameOver.png"));
		//��ʼ��ʤ��ͼƬ
		win=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/win.png"));
		
		//��ʼ����ͼ
		this.map();
		}
	}
	//�����ͼ
	public void map(){
		//��ʼ���ܲ�λ��
		for(int i=0;i<1;i++){
			Boss boss=new Boss(350,571);
			bs.add(boss);
		}
		//��ʼ����ʯλ��
		for(int i=0;i<stoneSize;i++){
			Stone stone=new Stone(100+i*40,100+i*40);
			stones.add(stone);
		}
		for(int i=0;i<stoneSize;i++){
			Stone stone=new Stone(700-(100+i*40),100+i*40);
			stones.add(stone);
		}
		for(int i=0;i<1;i++){
			Stone stone=new Stone(220,100);
			stones.add(stone);
		}
		for(int i=0;i<1;i++){
			Stone stone=new Stone(480,100);
			stones.add(stone);
		}
		for(int i=0;i<1;i++){
			Stone stone=new Stone(140,60);
			stones.add(stone);
		}
		for(int i=0;i<1;i++){
			Stone stone=new Stone(560,60);
			stones.add(stone);
		}
		for(int i=0;i<1;i++){
			Stone stone=new Stone(350,150);
			stones.add(stone);
		}
		for(int i=0;i<2;i++){
			Stone stone=new Stone(640+(i*40),360);
			stones.add(stone);
		}
		for(int i=0;i<3;i++){
			Stone stone=new Stone(0,230+(i*40));
			stones.add(stone);
		}
		for(int i=0;i<3;i++){
			Stone stone=new Stone(40,270);
			stones.add(stone);
		}
		//��ʼ��ǽ���λ��
		for(int i=0;i<6;i++){
			Wall wall=new Wall(140+i*40,100+i*40);
			walls.add(wall);
		}
		for(int i=0;i<6;i++){
			Wall wall=new Wall(700-(140+i*40),100+i*40);
			walls.add(wall);
		}

		for(int i=0;i<2;i++){
			Wall wall=new Wall(310,560-(i*40));
			walls.add(wall);
		}
		for(int i=0;i<1;i++){
			Wall wall=new Wall(350,520);
			walls.add(wall);
		}
		for(int i=0;i<2;i++){
			Wall wall=new Wall(390,560-(i*40));
			walls.add(wall);
		}
		//��ʼ��ɭ��λ��
		for(int i=0;i<5;i++){
			Lawn lawn=new Lawn(0,560-(i*40));
			lawns.add(lawn);
		}
		for(int i=0;i<4;i++){
			Lawn lawn=new Lawn(40,560-(i*40));
			lawns.add(lawn);
		}
		for(int i=0;i<3;i++){
			Lawn lawn=new Lawn(80,560-(i*40));
			lawns.add(lawn);
		}
		for(int i=0;i<2;i++){
			Lawn lawn=new Lawn(120,560-(i*40));
			lawns.add(lawn);
		}
		for(int i=0;i<1;i++){
			Lawn lawn=new Lawn(160,560-(i*40));
			lawns.add(lawn);
		}
		for(int i=0;i<1;i++){
			Lawn lawn=new Lawn(310,150);
			lawns.add(lawn);
		}
		for(int i=0;i<1;i++){
			Lawn lawn=new Lawn(390,150);
			lawns.add(lawn);
		}
		for(int i=0;i<1;i++){
			Lawn lawn=new Lawn(350,110);
			lawns.add(lawn);
		}
		for(int i=0;i<1;i++){
			Lawn lawn=new Lawn(350,190);
			lawns.add(lawn);
		}
		for(int i=0;i<7;i++){
			Lawn lawn=new Lawn(440+(i*40),400);
			lawns.add(lawn);
		}
		for(int i=0;i<3;i++){
			Lawn lawn=new Lawn(440,480+(i*40));
			lawns.add(lawn);
		}
	}
	//��ʼ����
	public void begin(Graphics g){
		g.drawImage(beg, 0, 0, 720, 600,0,0,720,600, this);
		g.setFont(new Font("΢���ź�",Font.PLAIN,16));
		g.drawString(" ���س���ʼ��Ϸ ", 290, 520);
		g.drawString(" �ƶ���λ��W S A D ", 270, 540);
		g.drawString(" �����λ��J(�ӵ�) K(����) M(�˵�) ", 240, 560);
	}
	//����
	public  void paint(Graphics g){
		//���ÿ�ʼ���淽��
		if(this.gameLife==true&&this.begin==false){
			this.begin(g);
		}
		if(this.gameLife==true&&this.begin==true){
		//��������
		g.setColor(Color.BLACK);
		g.drawImage(background1, 0, 0, 720, 600,0,0,720,600, this);
		//������ʯ
		for(int i=0;i<stones.size();i++){
			Stone stone=stones.get(i);
			g.drawImage(sto, stone.x, stone.y, 40, 40, this);
		}
		//����ǽ��
		for(int i=0;i<walls.size();i++){
			Wall wall=walls.get(i);
			if(wall.isLive==true){
				g.drawImage(wal, wall.x, wall.y, 40, 40, this);
			}
			if(wall.isLive==false){
				walls.remove(wall);
			}
		}
		//����ɭ��
		for(int i=0;i<lawns.size();i++){
			Lawn lawn=lawns.get(i);
			if(lawn.isLive==true){
				g.drawImage(law, lawn.x, lawn.y, 40, 40, this);
			}
			else if(lawn.isLive==false){
				lawns.remove(lawn);
			}
		}
		//��������
		for(int i=0;i<props.size();i++){
			prop prop=props.get(i);
			if(prop.isLive==true){
				this.drawProp(prop.x,prop.y,g,prop.type);
			}
			else if(prop.isLive==false){
				props.remove(prop);
			}
		}
		//��������̹�����ӵ�
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			if(enemy.isLive==true){
				drawTank(enemy.getX(),enemy.getY(),g,enemy.getType(),enemy.direct);	
			}
			else if(enemy.isLive==false){
				this.allSize--;
				if(enemy.type==1){
					prop prop=new prop(r.nextInt(500)+50,r.nextInt(400)+50,0);
					if(r.nextInt(5)==1){
						prop.type=1;
					}
					props.add(prop);
				}
				ets.remove(enemy);
			}
			for(int j=0;j<enemy.es.size();j++){
				Shot enemyShot=enemy.es.get(j);
				if(enemyShot.isLive==true){
					g.setColor(Color.WHITE);
					g.fillOval(enemyShot.getX(), enemyShot.getY(), 4, 4);
				}
				
				if(enemyShot.isLive==false){
					enemy.es.remove(enemyShot);
				}
			}
		}
		//����ը��
		for(int i=0;i<bombs.size();i++){
					Bomb b=bombs.get(i);
					if(b.life>6){
						g.drawImage(bomb1, b.x, b.y, 30, 30, this);
					}
					else if(b.life>4){
						g.drawImage(bomb2, b.x, b.y, 30, 30, this);
					}
					else if(b.life>2){
						g.drawImage(bomb3, b.x, b.y, 30, 30, this);
					}
					else{
						g.drawImage(bomb4, b.x, b.y, 30, 30, this);
					}
					b.lifeDown();
					if(b.isLive==false){
						bombs.remove(b);
					}
				}
		//������ը��
		for(int i=0;i<Nbombs.size();i++){
			Nbomb Nb=Nbombs.get(i);
			if(Nb.life>9){
				g.drawImage(bomb1, Nb.x, Nb.y, 100, 100, this);
			}
			else if(Nb.life>6){
				g.drawImage(bomb2, Nb.x, Nb.y, 100, 100, this);
			}
			else if(Nb.life>3){
				g.drawImage(bomb3, Nb.x, Nb.y, 100, 100, this);
			}
			else{
				g.drawImage(bomb4, Nb.x, Nb.y, 100, 100, this);
			}
			Nb.lifeDown();
			if(Nb.isLive==false){
				Nbombs.remove(Nb);
			}
		}
		//�������̹���ӵ�
		for(int i=0;i<hero.ss.size();i++)
		{	
			Shot myShot=hero.ss.get(i);
			if(hero.isLive==true&&myShot!=null&&myShot.isLive==true){
				g.setColor(Color.YELLOW);
				g.fillOval(myShot.getX(), myShot.getY(), 4, 4);
			}
			else if(myShot.isLive==false){
				hero.ss.remove(myShot);
			}
		}
		//�������̹�˵���
		for(int i=0;i<hero.mis.size();i++){
			Missile missile=hero.mis.get(i);
			if(hero.isLive==true&&missile!=null&&missile.isLive==true){
				g.setColor(Color.RED);
				g.fillOval(missile.x, missile.y, 4, 4);
			}
			else if(missile.isLive==false){
				hero.mis.remove(missile);
			}
		}
		//�����ܲ�
		for(int i=0;i<bs.size();i++){
			Boss boss=bs.get(i);
			if(boss.isLive==true){
				g.drawImage(bos, boss.x, boss.y, 40, 29, this);
			}
			else if(boss.isLive==false){
				this.game=false;
				this.winGame=false;
				this.lowGame=true;
			}
			
		}
		//�������̹��
		if(this.hero.isLive==true){
			drawTank(hero.getX(),hero.getY(),g,2,hero.getDirect());
		}
		//������ʾ
		g.setColor(Color.red);
		g.setFont(new Font("΢���ź�",Font.PLAIN,16));
		g.drawString("��ǰ�ط�̹��������"+this.allSize, 540, 20);
		g.drawString("��ǰ�ҷ�̹������ֵ��"+this.hero.life, 540, 40);
		g.drawString("��ǰ�ҷ�������������"+this.hero.misSize, 540, 60);
		g.drawString("��ǰ�ҷ��˵���������"+this.hero.c4, 540, 80);
		}
		//��Ϸ����
		if(this.game==false){
			if(lowGame==true){
				g.drawImage(over, 0, 0, 720, 600, 0, 0, 700, 600, this);
				g.setColor(Color.RED);
				g.setFont(new Font("΢���ź�",Font.PLAIN,16));
				g.drawString(" ���س�������Ϸ ", 300, 540);
			}
			else if(winGame==true){
				g.drawImage(win, 0, 0, 720, 600, 0, 0, 700, 600, this);
				g.setColor(Color.BLACK);
				g.setFont(new Font("΢���ź�",Font.PLAIN,16));
				g.drawString(" ���س�������Ϸ ", 300, 540);
			}
		}
	
	}
	//�ж�̹���Ƿ���̹����ײ
	public void strikeTank(){
		//�жϵ�ǰ���̹���Ƿ�ײ����һ��̹��
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			if(enemy.isLive==true){
				this.heroStrikeEnemy(hero, enemy);
			}
		}
		//�жϵ�ǰ����̹���Ƿ�ײ����һ��̹��
		for(int i=0;i<ets.size();i++){
			Enemy enemy1=ets.get(i);
			if(enemy1.isLive==true){
				for(int j=0;j<ets.size();j++){
					//if(j==i){
					//	continue;
					//}
					Enemy enemy2=ets.get(j);
					if(enemy2.isLive==true){
						this.enemyStrikeEnemy(enemy1, enemy2);
					}
				}
			}
		}
	}
	//�жϵ�ǰ���̹���Ƿ�ײ����һ��̹��
	public void heroStrikeEnemy(Hero hero,Enemy enemy){
		switch(hero.direct){
		case 0:
			if((hero.x>enemy.x&&hero.x<enemy.x+30||hero.x+30>enemy.x&&hero.x+30<enemy.x+30)&&hero.y>enemy.y&&hero.y<enemy.y+30){
				//ײ������ʯ
				hero.y=enemy.y+30;
			}
			break;
		case 1:
			if((hero.x>enemy.x&&hero.x<enemy.x+30||hero.x+30>enemy.x&&hero.x+30<enemy.x+30)&&hero.y+30>enemy.y&&hero.y+30<enemy.y+30){
				hero.y=enemy.y-30;
			}
			break;
		case 2:
			if(hero.x>enemy.x&&hero.x<enemy.x+30&&(hero.y>enemy.y&&hero.y<enemy.y+30||hero.y+30>enemy.y&&hero.y+30<enemy.y+30)){
				hero.x=enemy.x+30;
			}
			break;
		case 3:
			if(hero.x+30>enemy.x&&hero.x+30<enemy.x+30&&(hero.y>enemy.y&&hero.y<enemy.y+30||hero.y+30>enemy.y&&hero.y+30<enemy.y+30)){
				hero.x=enemy.x-30;
			}
			break;
		}
	}
	//�жϵ�ǰ����̹���Ƿ�ײ����һ��̹��
	public void enemyStrikeEnemy(Enemy enemy1,Enemy enemy){
		switch(enemy1.direct){
		case 0:
			if((enemy1.x>enemy.x&&enemy1.x<enemy.x+30||enemy1.x+30>enemy.x&&enemy1.x+30<enemy.x+30)&&enemy1.y>enemy.y&&enemy1.y<enemy.y+30){
				//ײ������ʯ
				enemy1.y=enemy.y+30;
			}
			break;
		case 1:
			if((enemy1.x>enemy.x&&enemy1.x<enemy.x+30||enemy1.x+30>enemy.x&&enemy1.x+30<enemy.x+30)&&enemy1.y+30>enemy.y&&enemy1.y+30<enemy.y+30){
				enemy1.y=enemy.y-30;
			}
			break;
		case 2:
			if(enemy1.x>enemy.x&&enemy1.x<enemy.x+30&&(enemy1.y>enemy.y&&enemy1.y<enemy.y+30||enemy1.y+30>enemy.y&&enemy1.y+30<enemy.y+30)){
				enemy1.x=enemy.x+30;
			}
			break;
		case 3:
			if(enemy1.x+30>enemy.x&&enemy1.x+30<enemy.x+30&&(enemy1.y>enemy.y&&enemy1.y<enemy.y+30||enemy1.y+30>enemy.y&&enemy1.y+30<enemy.y+30)){
				enemy1.x=enemy.x-30;
			}
			break;
		}
	}
	
	//�ж�̹���Ƿ�ײ����ǽ��
	public void strikeWall(){
		//�ж�����Ƿ�ײ��ĳһǽ��
		for(int i=0;i<walls.size();i++){
			Wall wall=walls.get(i);
			this.heroStrikeWall(this.hero, wall);
		}
		//�жϵ���̹���Ƿ�ײ����ǽ��
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			if(enemy.isLive==true){
				for(int j=0;j<walls.size();j++){
					Wall wall=walls.get(j);
					this.enemyStrikeWall(enemy, wall);
				}
			}
		}
	}
	//�ж�����Ƿ�ײ��ĳһǽ��
	public void heroStrikeWall(Hero hero,Wall wall){
		switch(hero.direct){
		case 0:
			if((hero.x>wall.x&&hero.x<wall.x+40||hero.x+30>wall.x&&hero.x+30<wall.x+40)&&hero.y>wall.y&&hero.y<wall.y+40){
				//ײ����ǽ��
				hero.y=wall.y+40;
			}
			break;
		case 1:
			if((hero.x>wall.x&&hero.x<wall.x+40||hero.x+30>wall.x&&hero.x+30<wall.x+40)&&hero.y+30>wall.y&&hero.y+30<wall.y+40){
				hero.y=wall.y-30;
			}
			break;
		case 2:
			if(hero.x>wall.x&&hero.x<wall.x+40&&(hero.y>wall.y&&hero.y<wall.y+40||hero.y+30>wall.y&&hero.y+30<wall.y+40)){
				hero.x=wall.x+40;
			}
			break;
		case 3:
			if(hero.x+30>wall.x&&hero.x+30<wall.x+40&&(hero.y>wall.y&&hero.y<wall.y+40||hero.y+30>wall.y&&hero.y+30<wall.y+40)){
				hero.x=wall.x-30;
			}
			break;
		}
	}
	//�жϵ���̹���Ƿ�ײ����ǽ��
	public void enemyStrikeWall(Enemy enemy,Wall wall){
		switch(enemy.direct){
		case 0:
			if((enemy.x>wall.x&&enemy.x<wall.x+40||enemy.x+30>wall.x&&enemy.x+30<wall.x+40)&&enemy.y>wall.y&&enemy.y<wall.y+40){
				//ײ����ǽ��
				enemy.y=wall.y+40;
			}
			break;
		case 1:
			if((enemy.x>wall.x&&enemy.x<wall.x+40||enemy.x+30>wall.x&&enemy.x+30<wall.x+40)&&enemy.y+30>wall.y&&enemy.y+30<wall.y+40){
				enemy.y=wall.y-30;
			}
			break;
		case 2:
			if(enemy.x>wall.x&&enemy.x<wall.x+40&&(enemy.y>wall.y&&enemy.y<wall.y+40||enemy.y+30>wall.y&&enemy.y+30<wall.y+40)){
				enemy.x=wall.x+40;
			}
			break;
		case 3:
			if(enemy.x+30>wall.x&&enemy.x+30<wall.x+40&&(enemy.y>wall.y&&enemy.y<wall.y+40||enemy.y+30>wall.y&&enemy.y+30<wall.y+40)){
				enemy.x=wall.x-30;
			}
			break;
		}
	}
	//�ж��ӵ��Ƿ����ǽ��
	public void hitWall(){
		for(int i=0;i<hero.ss.size();i++){
			Shot myShot=hero.ss.get(i);
			if(myShot.isLive==true){
				for(int j=0;j<walls.size();j++){
					Wall wall=walls.get(j);
					this.shotHitWall(myShot, wall);
				}
			}
		}
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			for(int j=0;j<enemy.es.size();j++){
				Shot enemyShot=enemy.es.get(j);
				if(enemyShot.isLive==true){
					for(int z=0;z<walls.size();z++){
						Wall wall=walls.get(z);
						this.shotHitWall(enemyShot, wall);
					}
				}
			}
		}
	}
	//�ж�ĳһ���ӵ��Ƿ����ǽ��
	public void shotHitWall(Shot s,Wall wall){
		if(s.x+2>wall.x&&s.x+2<wall.x+40&&s.y+2>wall.y&&s.y+2<wall.y+40){
			Bomb b=new Bomb(s.x-13,s.y-13);
			bombs.add(b);
			s.isLive=false;
			wall.lifeDown();
		}
	}
	
	
	
	//�ж�̹���Ƿ�ײ����ɭ��
	public void strikeLawn(){
		//�ж�����Ƿ�ײ��ĳһɭ��
		for(int i=0;i<lawns.size();i++){
			Lawn lawn=lawns.get(i);
			this.heroStrikeLawn(this.hero, lawn);
		}
		//�жϵ���̹���Ƿ�ײ����ɭ��
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			if(enemy.isLive==true){
				for(int j=0;j<lawns.size();j++){
					Lawn lawn=lawns.get(j);
					this.enemyStrikeLawn(enemy, lawn);
				}
			}
		}
	}
	//�ж�����Ƿ�ײ��ĳһɭ��
	public void heroStrikeLawn(Hero hero,Lawn lawn){
		switch(hero.direct){
		case 0:
			if((hero.x>lawn.x&&hero.x<lawn.x+40||hero.x+30>lawn.x&&hero.x+30<lawn.x+40)&&hero.y>lawn.y&&hero.y<lawn.y+40){
				//ײ����ɭ��
				hero.y=lawn.y+40;
			}
			break;
		case 1:
			if((hero.x>lawn.x&&hero.x<lawn.x+40||hero.x+30>lawn.x&&hero.x+30<lawn.x+40)&&hero.y+30>lawn.y&&hero.y+30<lawn.y+40){
				hero.y=lawn.y-30;
			}
			break;
		case 2:
			if(hero.x>lawn.x&&hero.x<lawn.x+40&&(hero.y>lawn.y&&hero.y<lawn.y+40||hero.y+30>lawn.y&&hero.y+30<lawn.y+40)){
				hero.x=lawn.x+40;
			}
			break;
		case 3:
			if(hero.x+30>lawn.x&&hero.x+30<lawn.x+40&&(hero.y>lawn.y&&hero.y<lawn.y+40||hero.y+30>lawn.y&&hero.y+30<lawn.y+40)){
				hero.x=lawn.x-30;
			}
			break;
		}
	}
	//�жϵ���̹���Ƿ�ײ����ɭ��
	public void enemyStrikeLawn(Enemy enemy,Lawn lawn){
		switch(enemy.direct){
		case 0:
			if((enemy.x>lawn.x&&enemy.x<lawn.x+40||enemy.x+30>lawn.x&&enemy.x+30<lawn.x+40)&&enemy.y>lawn.y&&enemy.y<lawn.y+40){
				//ײ����ɭ��
				enemy.y=lawn.y+40;
			}
			break;
		case 1:
			if((enemy.x>lawn.x&&enemy.x<lawn.x+40||enemy.x+30>lawn.x&&enemy.x+30<lawn.x+40)&&enemy.y+30>lawn.y&&enemy.y+30<lawn.y+40){
				enemy.y=lawn.y-30;
			}
			break;
		case 2:
			if(enemy.x>lawn.x&&enemy.x<lawn.x+40&&(enemy.y>lawn.y&&enemy.y<lawn.y+40||enemy.y+30>lawn.y&&enemy.y+30<lawn.y+40)){
				enemy.x=lawn.x+40;
			}
			break;
		case 3:
			if(enemy.x+30>lawn.x&&enemy.x+30<lawn.x+40&&(enemy.y>lawn.y&&enemy.y<lawn.y+40||enemy.y+30>lawn.y&&enemy.y+30<lawn.y+40)){
				enemy.x=lawn.x-30;
			}
			break;
		}
	}
	//�ж��ӵ��Ƿ����ɭ��
		public void hitLawn(){
			for(int i=0;i<hero.ss.size();i++){
				Shot myShot=hero.ss.get(i);
				if(myShot.isLive==true){
					for(int j=0;j<lawns.size();j++){
						Lawn lawn=lawns.get(j);
						this.shotHitLawn(myShot, lawn);
					}
				}
			}
			for(int i=0;i<ets.size();i++){
				Enemy enemy=ets.get(i);
				for(int j=0;j<enemy.es.size();j++){
					Shot enemyShot=enemy.es.get(j);
					if(enemyShot.isLive==true){
						for(int z=0;z<lawns.size();z++){
							Lawn lawn=lawns.get(z);
							this.shotHitLawn(enemyShot, lawn);
						}
					}
				}
			}
		}
		//�ж�ĳһ���ӵ��Ƿ����ɭ��
		public void shotHitLawn(Shot s,Lawn lawn){
			if(s.x+2>lawn.x&&s.x+2<lawn.x+40&&s.y+2>lawn.y&&s.y+2<lawn.y+40){
				Bomb b=new Bomb(s.x-13,s.y-13);
				bombs.add(b);
				s.isLive=false;
				lawn.lifeDown();
			}
		}
	
	//�ж�̹���Ƿ�ײ������ʯ
	public void strikeStone(){
		//�ж�����Ƿ�ײ��ĳһ��ʯ
		for(int i=0;i<stones.size();i++){
			Stone stone=stones.get(i);
			this.heroStrikeStone(this.hero, stone);
		}
		//�жϵ���̹���Ƿ�ײ������ʯ
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			if(enemy.isLive==true){
				for(int j=0;j<stones.size();j++){
					Stone stone=stones.get(j);
					this.enemyStrikeStone(enemy, stone);
				}
			}
		}
	}
	//�ж�����Ƿ�ײ��ĳһ��ʯ
	public void heroStrikeStone(Hero hero,Stone stone){
		switch(hero.direct){
		case 0:
			if((hero.x>stone.x&&hero.x<stone.x+40||hero.x+30>stone.x&&hero.x+30<stone.x+40)&&hero.y>stone.y&&hero.y<stone.y+40){
				//ײ������ʯ
				hero.y=stone.y+40;
			}
			break;
		case 1:
			if((hero.x>stone.x&&hero.x<stone.x+40||hero.x+30>stone.x&&hero.x+30<stone.x+40)&&hero.y+30>stone.y&&hero.y+30<stone.y+40){
				hero.y=stone.y-30;
			}
			break;
		case 2:
			if(hero.x>stone.x&&hero.x<stone.x+40&&(hero.y>stone.y&&hero.y<stone.y+40||hero.y+30>stone.y&&hero.y+30<stone.y+40)){
				hero.x=stone.x+40;
			}
			break;
		case 3:
			if(hero.x+30>stone.x&&hero.x+30<stone.x+40&&(hero.y>stone.y&&hero.y<stone.y+40||hero.y+30>stone.y&&hero.y+30<stone.y+40)){
				hero.x=stone.x-30;
			}
			break;
		}
	}
	//�ж����̹���Ƿ�񵽵���
	public void strikeProp(){
		for(int i=0;i<props.size();i++){
			prop prop=props.get(i);
			if(prop.isLive==true){
				this.heroStrikeProp(hero, prop);
			}
		}
	}
	public void heroStrikeProp(Hero hero,prop stone){
		switch(hero.direct){
		case 0:
			if((hero.x>stone.x&&hero.x<stone.x+30||hero.x+30>stone.x&&hero.x+30<stone.x+30)&&hero.y>stone.y&&hero.y<stone.y+30){
				//ײ������ʯ
				if(stone.type==0){
				hero.misSize+=5;
				}
				else if(stone.type==1){
					hero.c4++;
				}
				stone.isLive=false;
			}
			break;
		case 1:
			if((hero.x>stone.x&&hero.x<stone.x+30||hero.x+30>stone.x&&hero.x+30<stone.x+30)&&hero.y+30>stone.y&&hero.y+30<stone.y+30){
				if(stone.type==0){
					hero.misSize+=5;
				}
				else if(stone.type==1){
					hero.c4++;
				}
				stone.isLive=false;
			}
			break;
		case 2:
			if(hero.x>stone.x&&hero.x<stone.x+30&&(hero.y>stone.y&&hero.y<stone.y+30||hero.y+30>stone.y&&hero.y+30<stone.y+30)){
				if(stone.type==0){
					hero.misSize+=5;
				}
				else if(stone.type==1){
					hero.c4++;
				}
				stone.isLive=false;
			}
			break;
		case 3:
			if(hero.x+30>stone.x&&hero.x+30<stone.x+30&&(hero.y>stone.y&&hero.y<stone.y+30||hero.y+30>stone.y&&hero.y+30<stone.y+30)){
				if(stone.type==0){
					hero.misSize+=5;
				}
				else if(stone.type==1){
					hero.c4++;
				}
				stone.isLive=false;
			}
			break;
		}
	}
	//�жϵ���̹���Ƿ�ײ������ʯ
	public void enemyStrikeStone(Enemy enemy,Stone stone){
		switch(enemy.direct){
		case 0:
			if((enemy.x>stone.x&&enemy.x<stone.x+40||enemy.x+30>stone.x&&enemy.x+30<stone.x+40)&&enemy.y>stone.y&&enemy.y<stone.y+40){
				//ײ������ʯ
				enemy.y=stone.y+40;
			}
			break;
		case 1:
			if((enemy.x>stone.x&&enemy.x<stone.x+40||enemy.x+30>stone.x&&enemy.x+30<stone.x+40)&&enemy.y+30>stone.y&&enemy.y+30<stone.y+40){
				enemy.y=stone.y-30;
			}
			break;
		case 2:
			if(enemy.x>stone.x&&enemy.x<stone.x+40&&(enemy.y>stone.y&&enemy.y<stone.y+40||enemy.y+30>stone.y&&enemy.y+30<stone.y+40)){
				enemy.x=stone.x+40;
			}
			break;
		case 3:
			if(enemy.x+30>stone.x&&enemy.x+30<stone.x+40&&(enemy.y>stone.y&&enemy.y<stone.y+40||enemy.y+30>stone.y&&enemy.y+30<stone.y+40)){
				enemy.x=stone.x-30;
			}
			break;
		}
	}
	//�ж��ӵ��Ƿ������ʯ
	public void hitStone(){
		for(int i=0;i<hero.ss.size();i++){
			Shot myShot=hero.ss.get(i);
			if(myShot.isLive==true){
				for(int j=0;j<stones.size();j++){
					Stone stone=stones.get(j);
					this.shotHitStone(myShot, stone);
				}
			}
		}
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			for(int j=0;j<enemy.es.size();j++){
				Shot enemyShot=enemy.es.get(j);
				if(enemyShot.isLive==true){
					for(int z=0;z<stones.size();z++){
						Stone stone=stones.get(z);
						this.shotHitStone(enemyShot, stone);
					}
				}
			}
		}
	}
	//�ж�̹�˻�����ʯ
	public void shotHitStone(Shot s,Stone stone){
		if(s.x+2>stone.x&&s.x+2<stone.x+40&&s.y+2>stone.y&&s.y+2<stone.y+40){
			Bomb b=new Bomb(s.x-13,s.y-13);
			bombs.add(b);
			s.isLive=false;
		}
	}
	//�ж��Ƿ���̹�˱����
	public void hitTank(){
		//�жϵз��ӵ��Ƿ�������̹��
		for(int i=0;i<ets.size();i++){
			//ȡ���з�̹��
			Enemy enemy=ets.get(i);
			for(int j=0;j<enemy.es.size();j++){
				Shot enemyShot=enemy.es.get(j);
				if(enemyShot.isLive==true){
					this.hitHeroTank(enemyShot, hero);
				}
			}
		}
		//�ж��ӵ��Ƿ���ез�̹��
		for(int i=0;i<hero.ss.size();i++){
			//�Ӽ�����ȡ���ӵ�
			Shot myShot=hero.ss.get(i);
			//�ж��ӵ��Ƿ���Ч
			if(myShot.isLive==true){
				//ȡ��ÿһ������̹����֮�ж�
				for(int j=0;j<ets.size();j++){
					//ȡ��̹��
					Enemy enemy=ets.get(j);
					if(enemy.isLive==true){
						this.hitEnemyTank(myShot,enemy);
					}
				}
			}
		}
		for(int i=0;i<Nbombs.size();i++){
			Nbomb Nb=Nbombs.get(i);
			if(Nb.isLive==true){
				for(int j=0;j<ets.size();j++){
					Enemy enemy=ets.get(j);
					if(enemy.isLive==true){
						this.NbombEnemyTank(Nb, enemy);
					}
				}
			}
		}
	}
	//�ж��Ƿ���е���̹��
	public void hitEnemyTank(Shot s,Enemy enemy){	
			if(s.x+2>enemy.x&&s.x+2<enemy.x+30&&s.y+2>enemy.y&&s.y+2<enemy.y+30){
				Bomb b=new Bomb(enemy.x,enemy.y);
				bombs.add(b);
				s.isLive=false;
				enemy.lifeDown();	
			}
	}
	//�жϵ����Ƿ�ը������̹��
	public void NbombEnemyTank(Nbomb stone,Enemy enemy){	
		switch(enemy.direct){
		case 0:
			if((enemy.x>stone.x&&enemy.x<stone.x+100||enemy.x+30>stone.x&&enemy.x+30<stone.x+100)&&enemy.y>stone.y&&enemy.y<stone.y+100){
				//ײ������ʯ
				enemy.isLive=false;
			}
			break;
		case 1:
			if((enemy.x>stone.x&&enemy.x<stone.x+100||enemy.x+30>stone.x&&enemy.x+30<stone.x+100)&&enemy.y+30>stone.y&&enemy.y+30<stone.y+100){
				enemy.isLive=false;
			}
			break;
		case 2:
			if(enemy.x>stone.x&&enemy.x<stone.x+100&&(enemy.y>stone.y&&enemy.y<stone.y+100||enemy.y+30>stone.y&&enemy.y+30<stone.y+100)){
				enemy.isLive=false;
			}
			break;
		case 3:
			if(enemy.x+30>stone.x&&enemy.x+30<stone.x+100&&(enemy.y>stone.y&&enemy.y<stone.y+100||enemy.y+30>stone.y&&enemy.y+30<stone.y+100)){
				enemy.isLive=false;
			}
			break;
		}
	}
	//�ж��Ƿ�������̹��
	public void hitHeroTank(Shot s,Hero hero){	
		if(s.x+2>hero.x&&s.x+2<hero.x+30&&s.y+2>hero.y&&s.y+2<hero.y+30){
			Bomb b=new Bomb(hero.x,hero.y);
			bombs.add(b);
			s.isLive=false;
			hero.lifeDown();
			hero.x=230;
			hero.y=550;
			hero.direct=0;
			
		}
	}
	//����һ������̹�˺�����һ������̹��
	public void addEnemyTank(){
		if(this.ets.size()<15&&count<75&&this.allSize>0&&r.nextInt(10)==1&&this.begin==true){
			Enemy enemy=new Enemy(60*r.nextInt(10),0,0);
			if(r.nextInt(10)==1){
				enemy.type=1;
			}
			enemy.begin=true;
			ets.add(enemy);
			Thread t=new Thread(enemy);
			t.start();
			count++;
		}
	}

	//�ж��ӵ��Ƿ�����ܲ�
	public void hitBoss(){
		for(int i=0;i<hero.ss.size();i++){
			Shot myShot=hero.ss.get(i);
			if(myShot.isLive==true){
				for(int j=0;j<bs.size();j++){
					Boss boss=bs.get(j);
					this.shotHitBoss(myShot, boss);
				}
			}
		}
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			for(int j=0;j<enemy.es.size();j++){
				Shot enemyShot=enemy.es.get(j);
				if(enemyShot.isLive==true){
					for(int z=0;z<bs.size();z++){
						Boss boss=bs.get(z);
						this.shotHitBoss(enemyShot, boss);
					}
				}
			}
		}
	}
	//�ж�ĳһ���ӵ��Ƿ�����ܲ�
	public void shotHitBoss(Shot s,Boss boss){
		if(s.x+2>boss.x&&s.x+2<boss.x+44&&s.y+2>boss.y&&s.y+2<boss.y+29){
			Bomb b=new Bomb(s.x-13,s.y-13);
			bombs.add(b);
			s.isLive=false;
			boss.lifeDown();
		}
	}
	//���������ط�̹��
	public void missileHitTank(){
		for(int i=0;i<hero.mis.size();i++){
			Missile s=hero.mis.get(i);
			if(s.isLive==true){
				for(int j=0;j<ets.size();j++){
					Enemy enemy=ets.get(j);
					if(s.x+2>enemy.x&&s.x+2<enemy.x+30&&s.y+2>enemy.y&&s.y+2<enemy.y+30){
						Bomb b=new Bomb(enemy.x,enemy.y);
						bombs.add(b);
						Nbomb Nb=new Nbomb(enemy.x-35,enemy.y-35);
						Nbombs.add(Nb);
						s.isLive=false;
						enemy.isLive=false;
					}
				}
			}
		}
	}
	//����̹��
	public void drawTank(int x,int y,Graphics g,int type,int direct){
		switch(direct){
		case 0:
			if(type==0){
				g.drawImage(enemy_1_0, x, y, 30, 30, this);
			}
			else if(type==1){
				g.drawImage(enemy_2_0, x, y, 30, 30, this);
			}
			else if(type==2){
				g.drawImage(hero0, x, y, 30, 30, this);
			}
			break;//̹�˳���
		case 1:
			if(type==0){
				g.drawImage(enemy_1_1, x, y, 30, 30, this);
			}
			else if(type==1){
				g.drawImage(enemy_2_1, x, y, 30, 30, this);
			}
			else if(type==2){
				g.drawImage(hero1, x, y, 30, 30, this);
			}
			break;//̹�˳���
		case 2:
			if(type==0){
				g.drawImage(enemy_1_2, x, y, 30, 30, this);
			}
			else if(type==1){
				g.drawImage(enemy_2_2, x, y, 30, 30, this);
			}
			else if(type==2){
				g.drawImage(hero2, x, y, 30, 30, this);
			}
			break;//̹�˳���
		case 3:
			if(type==0){
				g.drawImage(enemy_1_3, x, y, 30, 30, this);
			}
			else if(type==1){
				g.drawImage(enemy_2_3, x, y, 30, 30, this);
			}
			else if(type==2){
				g.drawImage(hero3, x, y, 30, 30, this);
			}
			break;//̹�˳���
		}
	}
	//������������
	public void drawProp(int x,int y,Graphics g,int type){
		if(type==0){
			g.drawImage(missile, x, y, 30, 30,this);
		}
		else if(type==1){
			g.drawImage(bomb, x, y, 30, 30,this);
		}
		
	}
	public void allEnemyDie(){
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			Bomb b=new Bomb(enemy.x,enemy.y);
			bombs.add(b);
			enemy.isLive=false;
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_W:
			if(this.begin==true){
				this.hero.moveUp();
				if(this.hero.direct!=0){
					this.hero.setDirect(0);	
				}
			}
			
			break;
		case KeyEvent.VK_S:
			if(this.begin==true){
				this.hero.moveDown();
				if(this.hero.direct!=1){
					this.hero.setDirect(1);	
				}
			}
			break;
		case KeyEvent.VK_A:
			if(this.begin==true){
				this.hero.moveLeft();
				if(this.hero.direct!=2){
					this.hero.setDirect(2);	
				}
			}
			break;
		case KeyEvent.VK_D:
			if(this.begin==true){
				this.hero.moveRight();
				if(this.hero.direct!=3){
					this.hero.setDirect(3);	
				}
			}
			break;
		case KeyEvent.VK_J:	
			if(hero.ss.size()<5&&hero.isLive==true&&this.begin==true){
				hero.shotEnemy();
			}	
			break;
		case KeyEvent.VK_K:	
			if(hero.isLive==true&&this.begin==true){
				hero.missileEnemy();
			}	
			break;
		case KeyEvent.VK_M:
			if(this.begin==true){
				if(hero.c4>0){
					this.allEnemyDie();
					hero.c4--;
				}
				
			}
		}	
		if(begin==false){
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
				this.begin=true;
				for(int i=0;i<this.ets.size();i++){
					Enemy enemy=ets.get(i);
					enemy.begin=true;
				}
			}
		}
		else if(begin==true&&game==false){
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
			this.begin=false;
			this.game=true;
			this.winGame=false;
			this.lowGame=false;
			this.beginGame();
			this.count=0;
		}
	}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void run() {	
		while(gameLife){				
			try{
			Thread.sleep(10);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			//�ж�̹���Ƿ���̹����ײ
			this.strikeTank();
			//�ж��ӵ��Ƿ����̹��
			this.hitTank();
			//�жϵ������Ƿ����̹��
			this.missileHitTank();
			//�ж��ӵ��Ƿ������ʯ
			this.hitStone();
			//����һ������̹��
			this.addEnemyTank();		
			//�ж�̹���Ƿ�ײ������ʯ
			this.strikeStone();
			//�ж�̹���Ƿ�ײ����ǽ��
			this.strikeWall();
			//�ж��ӵ��Ƿ����ǽ��
			this.hitWall();
			//�ж�̹���Ƿ�ײ����ɭ��
			this.strikeLawn();
			//�ж��ӵ��Ƿ����ɭ��
			this.hitLawn();
			//�ж��ӵ��Ƿ�����ܲ�
			this.hitBoss();
			//�жϵ���̹���Ƿ�񵽵�������
			this.strikeProp();
			//�жϵ���̹���Ƿ�����
			if(this.allSize<=0){
				this.winGame=true;
				this.game=false;
			}
			//�ж����̹���Ƿ�����
			if(hero.life==0){
				this.game=false;
				this.lowGame=true;
			}
			if(this.game==false){
				this.close();
			}
			//System.out.println(ets.size()+" "+this.allSize+" "+this.count+" "+hero.misSize);
			//�ػ�
			this.repaint();	
		}
		
	}
	//������Ϸ�������������
	public void close(){
		for(int i=0;i<ets.size();i++){
			Enemy enemy=ets.get(i);
			enemy.isLive=false;
			ets.remove(enemy);
			for(int j=0;j<enemy.es.size();j++){
				Shot enemyShot=enemy.es.get(j);
				enemy.es.remove(enemyShot);
			}
		}
		for(int i=0;i<bs.size();i++){
			Boss boss=bs.get(i);
			bs.remove(boss);
		}
		for(int i=0;i<walls.size();i++){
			Wall wall=walls.get(i);
			walls.remove(wall);
		}
		for(int i=0;i<lawns.size();i++){
			Lawn lawn=lawns.get(i);
			lawns.remove(lawn);
		}
		for(int i=0;i<stones.size();i++){
			Stone stone=stones.get(i);
			stones.remove(stone);
		}
		for(int i=0;i<props.size();i++){
			prop prop=props.get(i);
			props.remove(prop);
		}
		this.allSize=80;
	}
}