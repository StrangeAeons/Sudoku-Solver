class Grid {

    private Cell[] cells = new Cell[81];

    // CHANGE BACK TO PRIVATE!!!!!!!
    public int[][][] regionsRowsColumns = new int[3][][];

    Grid() {
	for( int i = 0; i < cells.length; i++) {
	    cells[i] = new Cell();
	}
    }

    class BinaryCandidates {
	private     byte[][] binaryCandidates;
	private     byte[][] binaryRegionRows;
	private Object[][][] regionRows;
	
	BinaryCandidates() {
	    binaryCandidates = this.binaryCandidates();
	    binaryRegionRows = this.binaryRegionRows();
	    //regionRows       = this.regionRows();
	}
	
	byte[][] binaryCandidates() {
	    byte[][] arr = new byte[9][81];
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 81; j++) {
		    if( cells[j].contains( i + 1) )
			arr[i][j]++;
		}
	    }
	    
	    return arr;
	}
	
	byte[][] binaryRegionRows() {
	    byte[][] arr = new byte[9][27];
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 81; j++) {
		    if( arr[i][j/3] < 1)
			arr[i][j/3] = binaryCandidates[i][j];
		}
	    }
	    return arr;
	}

	void printBinRegRows() {
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 27; j++) {
		    System.out.print( binaryRegionRows[i][j] + " ");
		    
		}
		System.out.println();
	    }
	}

	Object[][][] regionRows() {
	    Object[][][] arr = new Object[9][9][2];
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 27; j++) {
		    arr[i][j%3 + j/9*3][1] = binaryRegionRows[i][j] == 1 ? j%3 : 0;
		    arr[i][j%3 + j/9*3][0] = (byte)arr[i][j%3 + j/9*3][0] + binaryRegionRows[i][j];
		}
	    }
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 9; j++) {
		    if( (byte)arr[i][j][0] == 1)
			arr[i][j][0] = true;
		}
	    }
	    return arr;
	}

	void printRegionRows() {
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 9; j++) {
		    for( int k = 0; k < 2; k++) {
			System.out.print( regionRows[i][j][k] + " ");
		    }
		    System.out.print("\t");
		}
		System.out.println();
	    }
	}
	
	boolean isContained() {
	    return false;
	}
    }


    void printBinaryCandidates( byte[] arr) {
	for( byte b : arr)
	    System.out.print( b + " ");
    }
    
    boolean isContainedRegionRow( int candidate) {
	return false;
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
			//Printer.printCandidates( this);     // FOR DEBUGGING
			//Printer.printGrid( this);          // FOR DEBUGGING
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
