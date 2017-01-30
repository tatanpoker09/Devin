package io.xchris6041x.devin.gui;

public class PageContainer extends Container {

	@Override
	public int getWidth() {
		return getParent().getWidth();
	}
	@Override
	public int getHeight() {
		return getParent().getHeight();
	}
	
	@Override
	public boolean isValidParent(Container container) {
		return container instanceof PageableContainer;
	}
	
}
