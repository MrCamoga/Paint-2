package com.camoga.paint.gui;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.input.MouseEvent;

public class UIRootPanel extends UIPanel {
	
	public UIRootPanel() {
		super(0, 0, Display.getWidth(), Display.getHeight());
	}

	public boolean onEvent(MouseEvent e) {
		return super.onEvent(e);
	}
	
	public void tick() {
		super.tick();
	}
	
	public void render() {
		super.render();
	}
}