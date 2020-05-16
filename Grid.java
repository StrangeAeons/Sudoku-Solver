class Grid {

    private Cell[] cells = new Cell[81];

    // CHANGE BACK TO PRIVATE!!!!!!!
    public int[][][] regionsRowsColumns = new int[3][][];

    Grid() {
	for( int i = 0; i < cells.length; i++) {
	    cells[i] = new Cell();
	}
    }

    Cell getCell( int index) {
	return cells[index];
    }

    Cell[] getCells() {
	return cells;
    }
    
    void checkRegionsRowsColumns() {
	SearchArray[] search = { new SearchRegion(), new SearchRow(), new SearchColumn() };
	for( int i = 0; i < 3; i++) {
	    for( int j = 0; j < 9; j++) {
		for( int k = 0; k < 10; k++) {
		    if( regionsRowsColumns[i][j][k] == 1) {
			Printer.printCandidates( this);     // FOR DEBUGGING
			Printer.printGrid( this);          // FOR DEBUGGING
			Cell cell = search[i].searchSingles( j, k, cells);
			cell.setSolution( k, this);
		    }
		}
	    }
	}
    }
    void fillRegionsRowsColumns() {
	Caller[] call = { new GetRegions(), new GetRows(), new GetColumns() };
	for( int i = 0; i < 3; i++) {
	    regionsRowsColumns[i] = call[i].getArray( cells);
	}
    }

}
