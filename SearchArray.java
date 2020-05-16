interface SearchArray {

    Cell searchSingles( int ID, int searchNumber, Cell[] cells); 
}

class SearchRegion implements SearchArray {
    public Cell searchSingles( int regionID, int searchNumber, Cell[] cells) {

	try {
	    for( int i = 0; i < cells.length; i++)
		if( cells[i].regionID() == regionID)
		    if( cells[i].contains( searchNumber) ) {
			System.out.println("Cell " + i
					   + " contains the single " + searchNumber
					   + " in region " + regionID);
			return cells[i];			
		    }
	}
	catch(Exception e) {
	    System.out.println("An Exception occurred in SearchArray : SearchRegion");
	    System.out.print("regionID : " + regionID + "\tsearchNumber : " + searchNumber);
	}
	return new Cell();
    }
}

class SearchRow implements SearchArray {
    public Cell searchSingles( int rowID, int searchNumber, Cell[] cells) {
	int rowStart = rowID*9;

	try {
	    for( int i = rowStart; i < rowStart + 9; i++) {
		if( cells[i].contains( searchNumber) ) {
		    System.out.println("Cell " + i
				       + " contains the single " + searchNumber
				       + " in row " + rowID);
		    return cells[i];
		}
	    }
	    System.out.println("");	    
	}
	catch( Exception e) {
	    System.out.println("An Exception occurred in SearchArray : SearchRow");
	    System.out.print("rowID : " + rowID + "\tsearchNumber : " + searchNumber);
	}
	return new Cell();	
    }
}

class SearchColumn implements SearchArray {
    public Cell searchSingles( int colID, int searchNumber, Cell[] cells) {
	int colStart = colID;

	try {
	    for( int i = colStart; i < colStart + 73; i += 9) {
		if( cells[i].contains( searchNumber) ) {
		    System.out.println("Cell " + i
				       + " contains the single " + searchNumber
				       + " in column " + colID);
		    return cells[i];
		}
	    }
	    System.out.println("");	    
	}
	catch( Exception e) {
	    System.out.println("An Exception occurred in SearchArray : SearchColumn");
	    System.out.print("colID : " + colID + "\tsearchNumber : " + searchNumber);	    
	}
	return new Cell();
    }
}
