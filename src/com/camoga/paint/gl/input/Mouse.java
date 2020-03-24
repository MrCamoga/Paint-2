package com.camoga.paint.gl.input;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.geom.Vector2f;
import com.camoga.paint.gl.input.MouseEvent.ButtonType;
import com.camoga.paint.gui.UIManager;

public class Mouse {
	
	public static Vector2f current, last, velocity;
	
	public static int[] left = new int[2], right = new int[2], center = new int[2];
	
	public Mouse() {
		current = getPos();
		last = getPos();
		velocity = new Vector2f();
	}
	
	public void tick() {
		current = getPos();
		
		velocity = current.sub(last);
		
		if(!velocity.zero()) {
			UIManager.getCursors().get(0).update(current.x, current.y, 0, 0);
			MouseEvent event = null;
			if(left[0] != 0) UIManager.onEvent(event = new MouseDraggedEvent(ButtonType.LEFT, current, left[1]));
			if(right[0] != 0) UIManager.onEvent(event = new MouseDraggedEvent(ButtonType.RIGHT, current, right[1]));
			if(center[0] != 0) UIManager.onEvent(event = new MouseDraggedEvent(ButtonType.CENTER, current, center[1]));
			if(event == null) UIManager.onEvent(new MouseMovedEvent(current));
		}
		
		last = current;
	}
	
	private boolean cursorInside() {
		return !(current.x < 0 || current.x >= 1 || current.y < 0 || current.y >= 1);
	}
	
	public Vector2f getPos() {
		double[] x = new double[1];
		double[] y = new double[1];
		glfwGetCursorPos(Display.getWindow(), x, y);
		return new Vector2f((float)x[0], (float)y[0]).displayNorm();
	}

	public void invoke(int b, int action, int modifiers) {
		current = getPos();
//		if(!cursorInside()) return;
		MouseEvent event = null;
		if(b==0) left[0] = action;
		if(b==1) right[0] = action;
		if(b==2) center[0] = action;
		ButtonType button = b == 0 ? ButtonType.LEFT:(b==1 ? ButtonType.RIGHT:ButtonType.CENTER);
		if(action == 0) event = new MouseReleasedEvent(button, current, modifiers);
		else if(action == 1) event = new MousePressedEvent(button, current, modifiers);
		UIManager.onEvent(event);
	}
}
