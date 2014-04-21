package org.jcommander.model.column;

import java.util.Comparator;

import javax.swing.Icon;

public interface IconAndStringColumn extends Column {

	public String getText();
	public Icon getIcon();
}
