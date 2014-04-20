package org.jcommander.core.comparator;

import java.util.HashMap;
import java.util.Map;

public class DirectoryTableRowComparatorService {

	private static DirectoryTableRowComparatorService directoryTableRowComparatorService;
	
	public static int FILE_NAME_COLUMN_INDEX = 0;
	public static int EXTENDSION_NAME_COLUMN_INDEX = 1;
	public static int SIZE_COLUMN_INDEX = 2;
	public static int DATE_COLUMN_INDEX = 3;
	
	private static Map<Integer,DirectoryTableRowComparator> comparatorsMap = new HashMap<Integer,DirectoryTableRowComparator>();
	
	static
	{
		comparatorsMap.put(FILE_NAME_COLUMN_INDEX,new FileNameColumnComparator(FILE_NAME_COLUMN_INDEX));
		comparatorsMap.put(EXTENDSION_NAME_COLUMN_INDEX,new FileExtensionColumnComparator(EXTENDSION_NAME_COLUMN_INDEX));
		comparatorsMap.put(SIZE_COLUMN_INDEX,new FileSizeColumnComparator(SIZE_COLUMN_INDEX));
		comparatorsMap.put(DATE_COLUMN_INDEX,new DateColumnComparator(DATE_COLUMN_INDEX));
	}
	
	private DirectoryTableRowComparatorService() {
		// TODO Auto-generated constructor stub
	}
	
	public static DirectoryTableRowComparatorService getInstance(){
		
		if(directoryTableRowComparatorService == null){
			return new DirectoryTableRowComparatorService();
		}
		else{
			return directoryTableRowComparatorService;
		}
		
	}
	
	public DirectoryTableRowComparator getComparator(int column)
	{
		return this.comparatorsMap.get(column);
	}
}
