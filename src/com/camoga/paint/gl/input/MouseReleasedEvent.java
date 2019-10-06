package com.camoga.paint.gl.input;

import com.camoga.paint.gl.geom.Vector2f;

public class MouseReleasedEvent extends MouseEvent {

	public MouseReleasedEvent(ButtonType button, Vector2f pos, int modifiers) {
		super(EventType.RELEASED, button, pos, modifiers);
	}
}
