package com.camoga.paint.gl.input;

import com.camoga.paint.gl.geom.Vector2f;

public class MouseMovedEvent extends MouseEvent {

	public MouseMovedEvent(Vector2f pos) {
		super(EventType.MOVED, pos);
	}
}