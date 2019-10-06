package com.camoga.paint.gl.input;

import com.camoga.paint.gl.geom.Vector2f;

public abstract class MouseEvent {
	
	protected Vector2f pos;
	protected ButtonType button;
	protected EventType type;
	protected int mod;
	
	public enum ButtonType {
		LEFT(0), RIGHT(1), CENTER(2);
		
		private ButtonType(int button) {
			
		}
	}
	
	public enum EventType {
		PRESSED, RELEASED, MOVED, DRAGGED;
	}
	
	public MouseEvent(EventType type, ButtonType button, Vector2f pos, int modifiers) {
		this.type = type;
		this.button = button;
		this.pos = pos;
		this.mod = modifiers;
	}
	
	public MouseEvent(EventType type, Vector2f pos) {
		this.type = type;
		this.pos = pos;
	}

	public Vector2f getPos() {
		return pos;
	}

	public ButtonType getButton() {
		return button;
	}

	public EventType getType() {
		return type;
	}

	public int getMod() {
		return mod;
	}
}