package com.camoga.paint.gui;

import java.util.ArrayList;

import com.camoga.paint.gl.input.Mouse;
import com.camoga.paint.gl.input.MouseEvent;

public abstract class UIComponent {

	protected float xo, yo;

	protected float width, height;
	protected float x, y;
	
	protected boolean entered;
	protected UIComponent parent;	
	protected ArrayList<UIComponent> childs = new ArrayList<>();
	
	public void render() {
		for(UIComponent child : childs) child.render();
	}
	public abstract void tick();
	
	public boolean onEvent(MouseEvent e) {
		for(UIComponent child : childs) {
			if(child.onEvent(e)) return true;
		}
		return false;
	}

	public void add(UIComponent c) {
		childs.add(c);
		c.parent = this;
		c.xo = x+xo;
		c.yo = y+yo;
	}
	
	protected boolean inside() {
		return parent.entered && (x+xo) < Mouse.current.x && (x+xo) + width > Mouse.current.x 
		&& (y+yo) < Mouse.current.y && (y+yo) + height > Mouse.current.y; 
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
}
