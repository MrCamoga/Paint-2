package com.camoga.paint.gl.geom;

import com.camoga.paint.gl.engine.Display;

public class Vector2f {
	public float x,y;
	
	public Vector2f() {}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public Vector2f displayNorm() {
		x = x/(float)Display.getWidth();
		y = y/(float)Display.getHeight();
		return this;
	}
	
	public String toString() {
		return x+", "+ y;
	}

	public boolean zero() {
		return x==0&&y==0;
	}
	
	public Vector2f sub(Vector2f last) {
		return new Vector2f(x-last.x,y-last.y);
	}
	
}
