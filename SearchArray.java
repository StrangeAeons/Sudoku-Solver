interface SearchArray {

    Cell searchSingles( int ID, int searchNumber, Cell[] cells); 
}

class SearchRegion implements SearchArray {
    public Cell searchSingles( int regionID, int searchNumber, Cell[] cells) {
	for( int i = 0; i < cells.length; i++)
	    if( cells[i].regionID() == regionID)
		if( cells[i].contains( searchNumber) ) {
		    System.out.println("Cell " + i
				       + " contains the single " + searchNumber
				       + " in region " + regionID);
		    return cells[i];			
		}
	return new Cell();
    }
}

class SearchRow implements SearchArray {
    public Cell searchSingles( int rowID, int searchNumber, Cell[] cells) {
	int rowStart = rowID*9;
	for( int i = rowStart; i < rowStart + 9; i++) {
	    if( cells[i].contains( searchNumber) ) {
		System.out.println("Cell " + i
				   + " contains the single " + searchNumber
				   + " in row " + rowID);
		return cells[i];
	    }
	}
	
	return new Cell();	
    }
}

class SearchColumn implements SearchArray {
    public Cell searchSingles( int colID, int searchNumber, Cell[] cells) {
	int colStart = colID;
	for( int i = colStart; i < colStart + 73; i += 9) {
	    if( cells[i].contains( searchNumber) ) {
		System.out.println("Cell " + i
				   + " contains the single " + searchNumber
				   + " in column " + colID);
		return cells[i];
	    }
	}
	return new Cell();
    }
}
