package com.camoga.paint.gui.components;

import java.util.ArrayList;

public class UITabPane extends UIComponent {

	private ArrayList<UITab> tabs = new ArrayList<>();
	private int selectedTab = -1;
	
	public UITabPane() {
		
	}
	
	public void addTab(UITab tab) {
		tabs.add(tab);
		tab.parent = this;
	}
	
	public void render() {
		if(selectedTab == -1) return;
		for(int i = 0; i < tabs.size(); i++) {
			
		}
	}
	
	public int getSelectedIndex() {
		return selectedTab;
	}
}