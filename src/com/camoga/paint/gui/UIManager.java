package com.camoga.paint.gui;

import com.camoga.paint.gl.input.MouseEvent;

public class UIManager {
	
	private static UIPanel scene = new UIPanel(0, 0, 1280, 720);
	
	private UIManager() {}
	
	public static void render() {
		scene.render();
	}

	public static void tick() {
		scene.tick();
	}
	
	public static void onEvent(MouseEvent e) {
		scene.onEvent(e);
	}
	
	public static UIPanel getScene() {
		return scene;
	}
	
	public static void setScene(UIPanel panel) {
		scene = panel;
		scene.parent = new UIPanel(0, 0, 0, 0);
		scene.parent.entered = true; //TODO esto es to feo
	}
}