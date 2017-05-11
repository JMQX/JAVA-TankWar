package com.Game;

public class prop {
	int x;
	int y;
	int type=0;
	boolean isLive=true;
	public prop(int x,int y,int type){
		this.x=x;
		this.y=y;
		this.type=type;
	}
}

class Missile implements Runnable{
	int x;//子弹初始坐标
	int y;
	int direct;//子弹发射方向
	int speed=8;//子弹速度
	boolean isLive=true;
	public Missile(int x,int y,int direct){
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
			//导弹是否撞到墙壁
			if(x<0||x>700||y<0||y>600){
				isLive=false;
				break;
			}
			//导弹是否击中敌方坦克
			if(isLive==false){
				break;
			}
			//导弹是否击中场景物品
		}
			
	}
}
//大炸弹类
class Nbomb{
	int x;
	int y;
	int life=12;
	boolean isLive=true;
	public Nbomb(int x,int y){
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