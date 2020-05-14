class Cell {

    public static final char unsolved = '_';

    // numberSolved is used to determine if any changes took place in
    // the most recent iteration of the solve algorithms.
    private static int eventCount;

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

    void eliminatePotentialSolution( int i) {
	arr[i - 1] = 0;
	eventCount++;
    }

    int getCellNumber() {
	return cellNumber;
    }

    int columnID() {
	return cellNumber % 9;
    }

    static int getEventCount() {
	return eventCount;
    }

    int numberOfPossibles() {
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

    static void incrementEventCount(){
	eventCount++;
    }    
    
    boolean isSolved() {
	int count = 0,
	    tempSolution = 0;

	if( solution > 0)
	    return true;
	
	for( int i : arr) {
	    if( i > 0) {
		tempSolution = i;
		count++;
	    }
	}
	if( count > 1)
	    return false;
	
	setSolution( tempSolution);
	return true;	
    }

    int[] getPossibles() {
	return arr;
    }

    void setSolution( int solution) {
	this.solution = solution;
	for( int i = 0; i < 9; i++) {
	    arr[i] = 0;
	}
    }
    
    void setSolution( int solution, Grid grid) {
	this.solution = solution;
	for( int i = 0; i < 9; i++) {
	    arr[i] = 0;
	}

	Cell[] cells = grid.getCells();
	
	int regionID = this.regionID(),
	    rowID    = this.rowID(),
	    colID    = this.columnID();

	for( int i = 0; i < 81; i++) {
	    for( int j = 1; j < 10; j++) {
		if( cells[i].regionID() == regionID) {
		    if( cells[i].contains( solution) ) {
			cells[i].eliminatePotentialSolution( solution);
			eventCount++;			
		    }
		}
		if( cells[i].rowID() == rowID) {
		    if( cells[i].contains( solution) ) {
			cells[i].eliminatePotentialSolution( solution);
			eventCount++;			
		    }		
		}
		if( cells[i].columnID() == colID) {
		    if( cells[i].contains( solution) ) {
			cells[i].eliminatePotentialSolution( solution);
			eventCount++;			
		    }
		}
	    }
	}
    }
}
