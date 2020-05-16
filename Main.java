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

	playGrid.checkRegionsRowsColumns();
	
	//Printer.printPossibles( playGrid);
	Printer.printGrid( playGrid);

	/*
	int changes = 0;
	do {
	    

	} while( changes != Cell.getNumberSolved() ); */
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
*********************************************************************************************/
