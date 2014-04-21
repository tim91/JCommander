package org.jcommander.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;
import org.jcommander.core.DirectoryContentChangeListener;
import org.jcommander.core.comparator.AbstractColumnComparator;
import org.jcommander.core.comparator.DirectoryTableRowComparator;
import org.jcommander.core.comparator.DirectoryTableRowComparatorService;
import org.jcommander.model.column.IconAndStringColumn;

public class DirectoryRowSorter extends TableRowSorter<DirectoryTableModel> implements DirectoryContentChangeListener {

	static Logger logger = Logger
			.getLogger("org.jcommander.model.DirectoryRowSorter");
	
	DirectoryTableModel model;
	
	private Map<Integer,Boolean> columnToCurrentSortDirection = new HashMap<Integer,Boolean>();
	
	public DirectoryRowSorter(DirectoryTableModel model) {
		super(model);
		// TODO Auto-generated constructor stub
		this.model = model;
		
		columnToCurrentSortDirection.put(DirectoryTableRowComparatorService.FILE_NAME_COLUMN_INDEX, true);
		columnToCurrentSortDirection.put(DirectoryTableRowComparatorService.EXTENDSION_NAME_COLUMN_INDEX, true);
		columnToCurrentSortDirection.put(DirectoryTableRowComparatorService.SIZE_COLUMN_INDEX, true);
		columnToCurrentSortDirection.put(DirectoryTableRowComparatorService.DATE_COLUMN_INDEX, true);
		
		
		toggleSortOrder(DirectoryTableRowComparatorService.FILE_NAME_COLUMN_INDEX);
	}

	
    @Override
    public void toggleSortOrder(int column) {
    	logger.debug("SORT : " + column);
    	
    	/*
    	 * Get appropriate comparator by column index
    	 */
    	
    	boolean direction = columnToCurrentSortDirection.get(column);
    	
    	if(direction)
    		direction = false;
    	else{
    		direction = true;
    	}
    	
    	DirectoryTableRowComparator comparator = DirectoryTableRowComparatorService.getInstance().getComparator(column);
    	
    	((AbstractColumnComparator)comparator).setDirection(direction);
    	
    	List<File> files = this.model.getFiles();
        Collections.sort(files,comparator);
        this.model.setFiles(files);
        logger.debug("---------------");
        for(File f : files){
        	IconAndStringColumn i = (IconAndStringColumn) f.getValueByColumnIndex(0);
        	logger.debug(i.getText());
        }
        
        
        columnToCurrentSortDirection.put(column, direction);
    }


	public void onDirectorycontentChange() {
		/*
		 * We don't want change direction, this is refresh not user sort action
		 */
		boolean dir = columnToCurrentSortDirection.get(0);
		columnToCurrentSortDirection.put(0, !dir);
		toggleSortOrder(0);
		
	}

}
