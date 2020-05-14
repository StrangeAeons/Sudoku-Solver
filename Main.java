import java.util.Scanner;

public class Main {

    public static void main( String[] args) {
	Scanner in = new Scanner(System.in);

	System.out.println("Enter the sudoku play grid row by row : ");
	System.out.println("(Enter 0 for empty cell)");
	//System.out.println("Spaces are allowed but not necessary");

	int[] arr = new int[81];
	for( int i = 0; i < arr.length; i++) {
	    arr[i] = in.nextInt();
	}

	Grid playGrid = new Grid();
	
	for( int i = 0; i < arr.length; i++) {
	    int solution = arr[i];
	    if( solution > 0) {
		playGrid.getCell(i).setSolution( solution, playGrid);
	    }
	}
	/*	int changes = 0;
	do{
	    changes = Cell.getEventCount();

	    Algorithms.preliminaryStep( playGrid);
	    Algorithms.hiddenSingles( playGrid);
	    Printer.printGrid( playGrid);	    
	    } while( changes != Cell.getEventCount() );*/

	int count = 0;
	while( count < 6) {
	    Algorithms.preliminaryStep( playGrid);

	    Printer.printPossibles( playGrid);		
	    Printer.printGrid( playGrid);

	    Algorithms.hiddenSingles( playGrid);
	    
	    Printer.printPossibles( playGrid);		
	    Printer.printGrid( playGrid);
	    count++;
	}
    }
}

class Algorithms {

    private Algorithms() {
    }
    
    static void preliminaryStep( Grid grid) {
	for( int i = 0; i < 9; i++) 
	    for( int j = 1; j < 10; j++) {
		if( grid.regionContains( j, i) ) {
		    grid.deleteFromRegion( j, i);
		}
		if( grid.rowContains( j, i) ) {
		    grid.deleteFromRow( j, i);
		}
		if( grid.columnContains( j, i) ) {
		    grid.deleteFromColumn( j, i);
		}
	    }
	
	for( int i = 0; i < 81; i++)
	    grid.getCell(i).isSolved();
    }

    static void hiddenSingles( Grid grid) {
	
	int[][] regions = grid.getRegionsArray(),
	        rows    = grid.getRowsArray(),
	        cols    = grid.getColsArray();

	for( int i = 0; i < 9; i++) {
	    for(int j = 1; j < 10; j++) {
		if( regions[i][j] == 1) {
		    Cell cell = grid.searchRegionforSingle( j, i);
		    cell.setSolution( j, grid);
		}
	    }
	}
	
	for( int i = 0; i < 9; i++) {
	    for( int j = 0; j < 10; j++) {
		if( rows[i][j] == 1) {
		    Cell cell = grid.searchRow( j, i);
		    cell.setSolution( j, grid);
		}
	    }
	}


	for( int i = 0; i < 9; i++) {
	    for( int j = 0; j < 10; j++) {
		if( cols[i][j] == 1) {
		    Cell cell = grid.searchCol( j, i);
		    cell.setSolution( j, grid);
		}
	    }
	}

	for( int i = 0; i < 81; i++)
	    grid.getCell(i).isSolved();	
    }
}
	    
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

    static void printPossibles( Grid grid) {
	int maxWidth = getMaxWidth( grid);

	for( int i = 0; i < 81; i++) {
	    int count = 0;
	    
	    if( i % 3 == 0 && i % 9 != 0)
		System.out.print(" || ");
	    if( i % 9 == 0)
		System.out.println();
	    if( i % 27 == 0)
		System.out.println();

	    Cell cell = grid.getCell(i);
	    int[] arr = cell.getPossibles();

	    for( int a = 0; a < 9; a++) {
		if( arr[a] != 0) {
		    System.out.print( " " + arr[a]);
		    count += 2;
		}
	    }
	    while( count < maxWidth) {
		System.out.print(" ");
		count++;
	    }
	}
    }

    static int getMaxWidth( Grid grid) {
	int max = 0,
	    check = 0;
	for( int i = 0; i < 81; i++) {
	    Cell cell = grid.getCell( i);
	    if( (check = cell.numberOfPossibles() ) > max)
		max = check;
	}
	return max*2 + 2;
    }
}



/********************************************************
	for( int i = 0; i < 9; i++) {
	    for( int j = 0; j < 10; j++)
		System.out.print( regions[i][j]);
	    System.out.println();
	}
	System.out.println("\n");
       
********************************************************/
