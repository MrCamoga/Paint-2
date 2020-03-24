package com.camoga.paint.gui.constraints;

public class RelativeConstraint extends UIConstraint {
	
	private float size;
	
	public RelativeConstraint(int size) {
		this.size = size;
	}
	
	public float getValue() {
		return size;
	}
}
