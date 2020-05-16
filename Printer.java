class Printer {

    private Printer() {
    }

    static void printGrid( Grid grid) {
	for( int i = 0; i < 81; i++) {
	    if( i % 3 == 0)
		System.out.print("\t");
	    if( i % 9 == 0)
		System.out.println();
	    if( i % 27 == 0)
		System.out.println();		

	    int solution;
	    if( (solution = grid.getCell(i).getSolution() ) > 0)
		System.out.print( solution + " ");
	    else
		System.out.print( Cell.unsolved + " ");
	}
	System.out.println();
    }

    static void printCandidates( Grid grid) {
	int maxWidth = getMaxWidth( grid);

	for( int i = 0; i < 81; i++) {
	    int count = 0;
	    
	    if( i % 3 == 0 && i % 9 != 0)
		System.out.print(" || ");
	    if( i % 9 == 0)
		System.out.println();
	    if( i % 27 == 0)
		System.out.println();

	    System.out.print("(" + i + ")");
	    if( i < 10)
		count += 3;
	    else
		count += 4;
	    Cell cell = grid.getCell(i);
	    int[] arr = cell.getCandidates();

	    for( int a = 0; a < 9; a++) {
		if( arr[a] != 0) {
		    System.out.print( " " +  arr[a]);
		    count += 2;
		}
	    }
	    while( count < maxWidth) {
		System.out.print(" ");
		count++;
	    }
	}
	System.out.println();
    }

    private static int getMaxWidth( Grid grid) {
	int max = 0,
	    check = 0;
	for( int i = 0; i < 81; i++) {
	    Cell cell = grid.getCell( i);
	    if( (check = cell.numberOfCandidates() ) > max)
		max = check;
	}
	return max*2 + 4;
    }
}
