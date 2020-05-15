class Grid {
    private static int totalSolutions;
    private Cell[] cells = new Cell[81];
    //private int[] possibles = new int[81];
    
    Grid() {
	for( int i = 0; i < cells.length; i++) {
	    cells[i] = new Cell();
	    //possibles[i] = cells[i].numberOfPossibles();
	}
    }

    Cell getCell( int index) {
	return cells[index];
    }

    Cell[] getCells() {
	return cells;
    }

}







/*************************************************************************************    
   
    void decrementPossibles( int index) {
	possibles[index]--;
    }
    
    int[] getPossibles() {
	return possibles;
    }

    void initPossibles( int index) {
	possibles[index] = 0;
    }




 Cell searchRegionforSingle( int searchNumber, int regionID) {
	// returns cell containing a specific possible solutions from a specific region
	for( int i = 0; i < cells.length; i++)
	    if( cells[i].regionID() == regionID)
		if( cells[i].contains( searchNumber) )
		    return cells[i];
	// this is a temporary fix, need to come up with exception handling or something
	return new Cell();
    }

    int[][] getRegionsArray() {
	int[][] arr = new int[9][10];

	for( int i = 0; i < 81; i++) {
	    int regionID = cells[i].regionID();
	    
	    for(int j = 1; j < 10; j++) {
		if(cells[i].contains( j) ) {
		    arr[regionID][j]++;
		}
	    }
	}
	return arr;
    }

    int[][] getRowsArray() {
	int[][] arr = new int[9][10];
	for( int i = 0; i < 81; i++) {
	    for( int j = 1; j < 10; j++) {
		if( cells[i].contains( j) )
		    arr[i / 9][j]++;
	    }
	}
	return arr;
    }
    
    int[][] getColsArray() {
	int[][] arr = new int[9][10];
	for( int i = 0; i < 81; i++) {
	    for( int j = 1; j < 10; j++) {
		if( cells[i].contains( j) )
		    arr[i % 9][j]++;
	    }
	}
	return arr;
    }

    Cell searchRow( int searchNumber, int rowID) {
	int rowStart = rowID*9;
	for( int i = rowStart; i < rowStart + 9; i++) {
	    for( int j = 1; j < 10; j++) {
		if( cells[i].contains( searchNumber) )
		    return cells[i];
	    }
	}
	return new Cell();
    }

    Cell searchCol( int searchNumber, int colID) {
	int colStart = colID;
	for( int i = colStart; i < colStart + 73; i += 9) {
	    for( int j = 1; j < 10; j++) {
		if( cells[i].contains( searchNumber) )
		    return cells[i];
	    }
	}
	return new Cell();
    }
    
    boolean columnContains( int searchNumber, int columnID) {
	for( int i = columnID; i < columnID + 73; i += 9) {
	    if( cells[i].getSolution() == searchNumber)
		return true;
	}
	return false;
    }
    
    boolean regionContains( int searchNumber, int regionID) {
	//  Searches region for an existing solution (ignores POTENTIAL solutions)
	for( int i = 0; i < cells.length; i++) {
	    if( cells[i].regionID() == regionID)
		if( cells[i].getSolution() == searchNumber)
		    return true;
	}
	return false;
    }

}
*************************************************************************************/
