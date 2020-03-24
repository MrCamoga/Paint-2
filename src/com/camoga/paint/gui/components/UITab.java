package com.camoga.paint.gui.components;

import com.camoga.paint.gui.UIPanel;

public class UITab extends UIComponent {

	private UIPanel content;
	private String text;
	private float[] color = new float[] {1,0,0};
	
	public UITab(UIPanel content) {
		this.content = content;
		content.parent = this;
	}
	
	@Override
	public void updateOffset() {
		super.updateOffset();
		
	}

	public void render() {
		content.render();
	}
}
