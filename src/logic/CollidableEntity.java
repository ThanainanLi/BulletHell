package logic;

import javafx.geometry.Rectangle2D;

public abstract class CollidableEntity extends Entity{
	protected int radius;
	
	protected boolean collideWith(CollidableEntity other){
		//double xComponent = (this.x + this.spriteWidth) - (other.x + other.spriteWidth);
		//double yComponent = (this.y + this.spriteHeight) - (other.y + other.spriteHeight);
		//return Math.hypot(xComponent, yComponent) <= this.radius+other.radius;
		Rectangle2D thisHitbox = new Rectangle2D(this.x,this.y,this.spriteWidth,this.spriteHeight);
		Rectangle2D otherHitbox = new Rectangle2D(other.x,other.y,other.spriteWidth,other.spriteHeight);
		return thisHitbox.intersects(otherHitbox);
	}
	protected Rectangle2D getHitBox() {
		return new Rectangle2D(this.x,this.y,this.spriteWidth,this.spriteHeight);
	}
}