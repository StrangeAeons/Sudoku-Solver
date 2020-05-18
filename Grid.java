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

    class BinaryCandidates {
	private byte[][]   binaryCandidates;
	private byte[][]   binaryRegionRows;
	private byte[][]   binaryRegionColumns;
	private byte[][][] regionRows;
	private byte[][][] regionColumns;
	
	BinaryCandidates() {
	    binaryCandidates    = this.binaryCandidates();
	    binaryRegionRows    = this.binaryRegionRows();
	    binaryRegionColumns = this.binaryRegionColumns();
	    regionRows          = this.regionRows();
	    regionColumns       = this.regionColumns();
	}

	byte[][][] getRegions( int i) {
	    return i == 0 ? regionRows : regionColumns;
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

	byte[][] binaryRegionColumns() {
	    byte[][] arr = new byte[9][27];
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 81; j++) {
		    if( arr[i][j/27*9 + j%9] < 1)
			arr[i][j/27*9 + j%9] = binaryCandidates[i][j];
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

	byte[][][] regionRows() {
	    byte[][][] arr = new byte[9][9][2];
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 27; j++) {
		    if( binaryRegionRows[i][j] > 0)
			arr[i][j%3 + j/9*3][1] = (byte)(j/3);
		    arr[i][j%3 + j/9*3][0] += binaryRegionRows[i][j];
		}
	    }
	    return arr;
	}

	byte[][][] regionColumns() {
	    byte[][][] arr = new byte[9][9][2];
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 27; j++) {
		    if( binaryRegionColumns[i][j] > 0)
			arr[i][j/9*3 + j%9/3][1] = (byte)(j%9);
		    arr[i][j/9*3 + j%9/3][0] += binaryRegionColumns[i][j];		    
		}
	    }
	    return arr;
	}

	void printBinRegions() {
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 27; j++) {
		    System.out.print( binaryRegionRows[i][j] + " ");
		    
		}
		System.out.println();
	    }

	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 27; j++) {
		    System.out.print( binaryRegionColumns[i][j] + " ");
		    
		}
		System.out.println();
	    }	    
	}



	void printRegions() {
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 9; j++) {
		    for( int k = 0; k < 2; k++) {
			System.out.print( regionRows[i][j][k] + " ");
		    }
		    System.out.print("\t");
		}
		System.out.println();
	    }
	    System.out.print("\n\n");
	    for( int i = 0; i < 9; i++) {
		for( int j = 0; j < 9; j++) {
		    for( int k = 0; k < 2; k++) {
			System.out.print( regionColumns[i][j][k] + " ");
		    }
		    System.out.print("\t");
		}
		System.out.println();
	    }	    
	}
	
	class PointingRows implements PointingNumbers{

	    public PointingRows() {
		this.pointingNumbers();
	    }
	    
	    public void pointingNumbers() {
		for( int i = 0; i < 9; i++) {
		    for( int j = 0; j < 9; j++) {
			if( regionRows[i][j][0] == 1) {
			    int regionID       = j%9,
				pointingNumber = i + 1,
				rowID          = regionRows[i][j][1];
			    for( int k = 0; k < 81; k++) {
				if( cells[k].regionID() != regionID    &&
				    cells[k].rowID() == rowID          &&
				    cells[k].contains( pointingNumber) ) {
				    /*
				    System.out.printf("\t%-2d deleted from \t region : %-3d row : %-3d\n", pointingNumber, cells[k].regionID(), cells[k].rowID() );
				    System.out.printf("\t\tregionRows[%d][%d][0] : %d\n", i, j, regionRows[i][j][0]);
				    System.out.printf("\t\tregionID : %3d  cells[k].regionID() : %3d\n" , regionID, cells[k].regionID() );

				    System.out.printf("\t\tpointingNumber : %3d\n", pointingNumber);
				    System.out.printf("\t\trowID : %3d \tcells[k].rowID() : %3d\n", rowID, cells[k].rowID() );
				    */
				    cells[k].eliminateCandidate( pointingNumber);
				}
			    }
			}
		    }
		}
	    }
	}
	
	class PointingColumns implements PointingNumbers{

	    public PointingColumns() {
		this.pointingNumbers();
	    }
	    
	    public void pointingNumbers() {
		for( int i = 0; i < 9; i++) {
		    for( int j = 0; j < 9; j++) {
			if( regionColumns[i][j][0] == 1) {
			    int regionID       = j%9,
				pointingNumber = i + 1,
				columnID       = regionColumns[i][j][1];
			    for( int k = 0; k < 81; k++) {
				if( cells[k].regionID() != regionID    &&
				    cells[k].columnID() == columnID    &&
				    cells[k].contains( pointingNumber) ) {
				    
				    cells[k].eliminateCandidate( pointingNumber);
				}
			    }
			}
		    }
		}
	    }		
	}	
    }
}



