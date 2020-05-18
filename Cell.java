class Cell {

    public static final char unsolved = '_';

    // numberSolved is used to determine if any changes took place in
    // the most recent iteration of the solve algorithms.
    private static int numberSolved;

    private static int numberOfCells;
    private int solution;
    private int cellNumber;

    //  The ints in this array represent potential solutions for the cell.
    //  Once we have narrowed the potential solutions down to one, the cell is solved.
    private int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    
    Cell(){
	cellNumber = numberOfCells++;
    }
    
    boolean contains( int i){
	if( arr[i - 1] == i)
	    return true;
	return false;
    }
    
    void eliminateCandidate( int i) {
	arr[i - 1] = 0;
	if( this.numberOfCandidates() == 1) {
	    /* System.out.println("'" + this.checkSolved() + "'" + " in cell "  // FOR DEBUGGING
	       + this.cellNumber + " is a naked single.");*/	
	}
    }
	
    int getCellNumber() {
	return cellNumber;
    }

    int columnID() {
	return cellNumber % 9;
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
	return cellNumber / 27 * 3 + cellNumber % 9 / 3;
    }

    int rowID() {
	return cellNumber / 9;
    }
    
    int getSolution() {
	return solution;
    }

    static int getNumberSolved() {
	return numberSolved;
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

    int[] getCandidates() {
	return arr;
    }

    void setSolution( int solution, Grid grid) {
	if( solution == 0 || this.solution != 0)
	    return;

	this.solution = solution;
	
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
