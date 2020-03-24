package com.camoga.paint.gui.constraints;

import com.camoga.paint.gl.engine.Display;

public class PixelConstraint extends UIConstraint {

	private int size;
	
	public PixelConstraint(int size) {
		this.size = size;
	}
	
	public float getValue() {
		return size/(float)Display.getWidth();
	}
}