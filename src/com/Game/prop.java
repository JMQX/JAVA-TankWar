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
	int x;//�ӵ���ʼ����
	int y;
	int direct;//�ӵ����䷽��
	int speed=8;//�ӵ��ٶ�
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
			//�����Ƿ�ײ��ǽ��
			if(x<0||x>700||y<0||y>600){
				isLive=false;
				break;
			}
			//�����Ƿ���ез�̹��
			if(isLive==false){
				break;
			}
			//�����Ƿ���г�����Ʒ
		}
			
	}
}
//��ը����
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