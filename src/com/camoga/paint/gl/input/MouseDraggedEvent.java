package com.camoga.paint.gl.input;

import com.camoga.paint.gl.geom.Vector2f;

public class MouseDraggedEvent extends MouseEvent {

	public MouseDraggedEvent(ButtonType button, Vector2f pos, int modifiers) {
		super(EventType.DRAGGED, button, pos, modifiers);
	}
}