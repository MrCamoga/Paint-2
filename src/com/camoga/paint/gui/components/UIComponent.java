package com.camoga.paint.gui.components;

import java.util.ArrayList;

import com.camoga.paint.gl.input.Mouse;
import com.camoga.paint.gl.input.MouseEvent;
import com.camoga.paint.gui.constraints.UIConstraints;
import com.camoga.paint.gui.event.UIMouseEventListener;

public abstract class UIComponent implements UIMouseEventListener {

	protected float xo, yo;

	protected float width, height;
	protected float x, y;
	protected float radius;
	
	public boolean entered;
	public UIComponent parent;	
	protected ArrayList<UIComponent> childs = new ArrayList<>();
	protected UIConstraints constraints;
	
	public void render() {
		for(UIComponent child : childs) child.render();
	}
	
	public void tick() {
		entered = inside();
//		constraints.tick();
		for(UIComponent child : childs) child.tick();
	}
	
	public boolean onEvent(MouseEvent e) {
		for(UIComponent child : childs) {
			if(child.onEvent(e)) return true;
		}
		return false;
	}

	public void add(UIComponent c, UIConstraints constraints) {
		childs.add(c);
		c.parent = this;
		c.constraints = constraints;
		c.updateOffset();
	}
	
	public void add(UIComponent c) {
		add(c,UIConstraints.createDefault());
	}
	
	public void updateOffset() {
		xo = parent.xo + parent.x;
		yo = parent.yo + parent.y;
		for(UIComponent c : childs) {
			c.updateOffset();
		}
	}
	
	protected boolean inside() {
		if(parent==null) return true;
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
