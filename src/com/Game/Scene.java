package com.Game;

public class Scene {

}
class Wall{
	int x;
	int y;
	int life=2;
	boolean isLive=true;
	public Wall(int x,int y){
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
class Stone{
	int x;
	int y;
	public Stone(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
}
class Lawn{
	int x;
	int y;
	int life=1;
	boolean isLive=true;
	public Lawn(int x,int y){
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