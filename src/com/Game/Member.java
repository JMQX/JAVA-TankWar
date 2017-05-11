package com.Game;

import java.util.Random;
import java.util.Vector;
//坦克类
class Tank{
	int x;
	int y;
	int direct;
    int speed=8;  
    boolean isLive=true;
	public Tank(int x,int y){
		this.x=x;
		this.y=y;	
	}
	public void moveUp(){
		y-=speed;
		if(y<0){
			y=0;
		}
	}
	public void moveDown(){
		y+=speed;
		if(y>570){
			y=570;
		}
	}
	public void moveLeft(){
		x-=speed;
		if(x<0){
			x=0;
		}
	}
	public void moveRight(){
		x+=speed;
		if(x>690){
			x=690;
		}
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setDirect(int direct){
		this.direct=direct;
	}
	public int getDirect(){
		return direct;
	}
	public boolean isAcross(){
			return false;
	}
}
//玩家坦克类
class Hero extends Tank{
	//定义子弹集合
	Vector<Shot> ss=new Vector<Shot>();
	Shot s=null;
	//定义导弹集合
	Vector<Missile> mis=new Vector<Missile>();
	Missile missile=null;
	int misSize=5;

	//定义大炸弹数量
	int c4=1;
	
	int life=3;
	public Hero(int x,int y){
	super(x,y);
	}
	public void lifeDown(){
		if(life>0){
			life--;
		}else{
			this.isLive=false;
		}
	}
	public void shotEnemy(){	
		s=new Shot(x,y,direct);
		ss.add(s);
		Thread t=new Thread(s);
		t.start();
	}
	public void missileEnemy(){
		if(this.misSize>0){
			missile=new Missile(x,y,direct);
			mis.add(missile);
			Thread t=new Thread(missile);
			t.start();
			misSize--;
		}
	}
}
//敌人坦克类
class Enemy extends Tank implements Runnable{		//敌人坦克
	int type;
	int direct=1;
	int life=1;
	boolean begin=false;
	Vector<Shot> es=new Vector<Shot>();
	Shot enemyShot=null;//敌人子弹
	
	Random r=new Random();
	
	public Enemy(int x,int y,int type){
		super(x,y);
		this.type=type;
		if(type==0){
			life=1;
		}
		else if(type==1){
			life=2;
		}
	}
	public void lifeDown(){
		if(life>0){
			life--;
		}
		else{
			isLive=false;
		}
	}
	public int getType(){
		return type;
	}
	public void run() {
		while(isLive){
			try{
				Thread.sleep(200);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			if(this.begin){
				if(r.nextInt(4)==1)
			{
				direct=r.nextInt(4);
			}
				switch(direct){
				case 0:this.moveUp();break;
				case 1:this.moveDown();break;
				case 2:this.moveLeft();break;
				case 3:this.moveRight();break;
				}

			if(r.nextInt(4)==1&&es.size()<2){
				enemyShot=new Shot(x,y,direct);
				es.add(enemyShot);
				Thread t=new Thread(enemyShot);
				t.start();	
			}
			}
			
		}
	}
}
//子弹类
class Shot implements Runnable{
	int x;//子弹初始坐标
	int y;
	int direct;//子弹发射方向
	int speed=8;//子弹速度
	boolean isLive=true;
	public Shot(int x,int y,int direct){
		switch(direct){
		case 0:
			this.x=x+15;
			this.y=y-4;
			break;
		case 1:
			this.x=x+15;
			this.y=y+34;
			break;
		case 2:
			this.x=x-4;
			this.y=y+15;
			break;
		case 3:
			this.x=x+34;
			this.y=y+15;
			break;
		}
		this.direct=direct;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void run() {
		while(isLive){
			try{
				Thread.sleep(60);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			switch(direct){
			case 0:
				y-=speed;
				break;
			case 1:
				y+=speed;
				break;
			case 2:
				x-=speed;
				break;
			case 3:
				x+=speed;
				break;
			}
			//子弹是否撞到墙壁
			if(x<0||x>700||y<0||y>600){
				isLive=false;
				break;
			}
			//子弹是否击中敌方坦克
			if(isLive==false){
				break;
			}
			//子弹是否击中场景物品
		}
			
	}
}
//炸弹类
class Bomb{
	int x;
	int y;
	int life=8;
	boolean isLive=true;
	public Bomb(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void lifeDown(){
		if(life>0){
			life--;
		}
		else{
			isLive=false;
		}
	}
}
//总部类
class Boss{
	int x;
	int y;
	boolean isLive=true;
	int life=2;
	public Boss(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void lifeDown(){
		if(life>0){
			life--;
		}
		else{
			isLive=false;
		}
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
}
