package com.camoga.paint.gl.input;

import com.camoga.paint.gl.geom.Vector2f;

public class MousePressedEvent extends MouseEvent {

	public MousePressedEvent(ButtonType button, Vector2f pos, int modifiers) {
		super(EventType.PRESSED, button, pos, modifiers);
	}
}