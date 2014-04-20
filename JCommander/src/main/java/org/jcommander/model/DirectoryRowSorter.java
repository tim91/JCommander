package org.jcommander.model;

import java.util.Collections;
import java.util.List;

import javax.swing.RowSorter;

import org.apache.log4j.Logger;
import org.jcommander.core.comparator.DirectoryTableRowComparator;
import org.jcommander.core.comparator.DirectoryTableRowComparatorService;
import org.jcommander.model.column.IconAndStringColumn;

public class DirectoryRowSorter extends RowSorter<DirectoryTableModel> {

	static Logger logger = Logger
			.getLogger("org.jcommander.model.DirectoryRowSorter");
	
	DirectoryTableModel model;
	public DirectoryRowSorter(DirectoryTableModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}
	
	List<? extends SortKey> keys = null;

    @Override
    public DirectoryTableModel getModel() {
        return this.model;
    }

    @Override
    public void toggleSortOrder(int column) {
    	logger.debug("SORT : " + column);
    	
    	/*
    	 * Get appropriate comparator by column index
    	 */
    	
    	DirectoryTableRowComparator comparator = DirectoryTableRowComparatorService.getInstance().getComparator(column);
    	
    	
        Collections.sort(this.model.getFiles(),comparator);
        logger.debug("---------------");
        for(File f : this.model.getFiles()){
        	IconAndStringColumn i = (IconAndStringColumn) f.getValueByColumnIndex(0);
        	logger.debug(i.getText());
        }
    }

    @Override
    public int convertRowIndexToModel(int index) {
        return index;
    }

    @Override
    public int convertRowIndexToView(int index) {
        return index;
    }

    @Override
    public void setSortKeys(List<? extends SortKey> keys) {
        this.keys = keys;
    }

    @Override
    public List<? extends SortKey> getSortKeys() {
        return keys;
    }

    @Override
    public int getViewRowCount() {
        return getModelRowCount();
    }

    @Override
    public int getModelRowCount() {
        return this.model.getRowCount();
    }

    @Override
    public void modelStructureChanged() {
    }

    @Override
    public void allRowsChanged() {
    }

    @Override
    public void rowsInserted(int firstRow, int endRow) {
    }

    @Override
    public void rowsDeleted(int firstRow, int endRow) {
    }

    @Override
    public void rowsUpdated(int firstRow, int endRow) {
    }

    @Override
    public void rowsUpdated(int firstRow, int endRow, int column) {
    }
}
