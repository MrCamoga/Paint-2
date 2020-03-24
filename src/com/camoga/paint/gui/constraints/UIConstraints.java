package com.camoga.paint.gui.constraints;

import java.util.ArrayList;

import com.camoga.paint.gui.components.UIComponent;

public class UIConstraints {
	
	private ArrayList<UIConstraint> constraints = new ArrayList<>();
	
	public UIConstraints() {
		
	}
	
	public void tick() {
		for(UIConstraint c : constraints) {
			c.
		}
	}
	
	public void setX(UIConstraint c) {
		this.xc = c;
	}
	
	public void setY(UIConstraint c) {
		this.yc = c;
	}
	
	public void setWidth(UIConstraint c) {
		this.widthc = c;
	}
	
	public void setHeight(UIConstraint c) {
		this.heightc = c;
	}

	public static UIConstraints createDefault() {
		UIConstraints c = new UIConstraints();
		c.setX(new PixelConstraint(0));
		c.setY(new PixelConstraint(0));
		c.setWidth(new RelativeConstraint(1));
		c.setHeight(new RelativeConstraint(1));
		return c;
	}
}