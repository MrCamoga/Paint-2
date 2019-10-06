package com.camoga.paint;

import com.camoga.paint.gl.engine.Display;

public class PaintMain {
	
	public static void main(String[] args) {
		Display.createDisplay();
		while(!Display.shouldClose()) {
			Display.render();
			Display.tick();
		}
	}
}
