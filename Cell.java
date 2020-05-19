class Cell {

    public static final char unsolved = '_';

    private static int
	numberSolved,
	numberEliminatedCandidates,
	numberOfCells;

    private int
	solution,
	cellNumber,
	regionID,
	columnID,
	rowID,
	sectorRowID,
	sectorColID;	

    //  The ints in this array represent potential solutions for the cell.
    //  Once we have narrowed the potential solutions down to one, the cell is solved.
    private int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    Cell(){
	cellNumber = numberOfCells++;
	regionID    = cellNumber / 27 * 3 + cellNumber % 9 / 3;
	columnID    = cellNumber % 9;
	rowID       = cellNumber / 9;
	sectorRowID = regionID / 3;
	sectorColID = regionID % 3;	
    }
    
    boolean contains( int i){
	if( arr[i - 1] == i)
	    return true;
	return false;
    }
    
    void eliminateCandidate( int i) {
	arr[i - 1] = 0;
	numberEliminatedCandidates++;
	/*if( this.numberOfCandidates() == 1) {
	    System.out.println("'" + this.checkSolved() + "'" + " in cell "  // FOR DEBUGGING
	       + this.cellNumber + " is a naked single.");
	       }*/
    }
	
    int getCellNumber() {
	return cellNumber;
    }

    int checkSolved() {
	int count = 0,
	    tempSolution = 0;

	if( solution > 0)
	    return 0;
	
	for( int i : arr) {
	    if( i > 0) {
		tempSolution = i;
		count++;
	    }
	}
	if( count == 1)
	    return tempSolution;
	return 0;
	}
    
    int columnID() {
	return columnID;
    }

    int numberOfCandidates() {
	int count = 0;
	for( int i = 0; i < 9; i++) {
	    if( arr[i] > 0)
		count++;	    
	}
	return count;
    }
    
    int regionID() {
	return regionID;
    }

    int rowID() {
	return rowID;
    }

    int sectorRowID() {
	return sectorRowID;
    }

    int sectorColID() {
	return sectorColID;
    }
    
    int getSolution() {
	return solution;
    }

    static int getNumberSolved() {
	return numberSolved;
    }

    static int getNumberEliminatedCandidates() {
	return numberEliminatedCandidates;
    }

    int[] getCandidates() {
	return arr;
	}

    void setSolution( int solution, Grid grid) {
	if( solution == 0 || this.solution != 0)
	    return;

	this.solution = solution;
	numberEliminatedCandidates += this.numberOfCandidates() - 1;
	for( int i = 0; i < 9; i++) {
	    arr[i] = 0;
	}
	
	grid.fillRegionsRowsColumns();

	
	int regionID = this.regionID(),
	    rowID    = this.rowID(),
	    colID    = this.columnID();

	for( int i = 0; i < 81; i++) {
	    Cell cell = grid.getCell(i);
	    int region = cell.regionID(),
		col    = cell.columnID(),
		row    = cell.rowID();   

	    for( int j = 1; j < 10; j++) {
		if( region == regionID || row == rowID || col == colID) {
		    if( cell.contains( solution) ) {
			cell.eliminateCandidate( solution);
			grid.fillRegionsRowsColumns();			
		    }
		}
	    }
	}
	grid.checkRegionsRowsColumns();
	numberSolved++;
    }
}
