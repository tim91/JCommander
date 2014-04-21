package org.jcommander.core.comparator;


public class AbstractColumnComparator {

	protected int idx;
	
	protected boolean direction;
	
	public AbstractColumnComparator(int idx) {
		this.idx = idx;
	}
	public void setDirection(boolean asc){
		this.direction = asc;
	}

}
