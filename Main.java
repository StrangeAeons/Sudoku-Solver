import java.util.Scanner;

public class Main {

    public static void main( String[] args) {
	Scanner in = new Scanner(System.in);

	System.out.println("Enter the sudoku play grid row by row : ");
	System.out.println("(Enter 0 for empty cell)");

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

	Printer.printPossibles( playGrid);
	
	//Printer.printGrid( playGrid);	
	/*
	int changes = 0;
	do {
	    

	} while( changes != Cell.getNumberSolved() ); */
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
	System.out.println();
    }

    private static int getMaxWidth( Grid grid) {
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



/**********************************************************************************************

	playGrid.fillRegionsRowsColumns();
	for( int i = 0; i < 3; i++) {
	    for( int j = 0; j < 9; j++) {
		for( int k = 0; k < 10; k++) {
		    System.out.print( playGrid.regionsRowsColumns[i][j][k] + " ");
		}
		System.out.println();
	    }
	    System.out.println();
	}
