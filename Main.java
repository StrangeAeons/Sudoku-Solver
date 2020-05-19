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

	int givenSolutions = 0;
	for( int i = 0; i < arr.length; i++) {
	    int solution = arr[i];
	    if( solution > 0) {
		playGrid.getCell(i).setSolution( solution, playGrid);
		givenSolutions++;
	    }
	}
	int changes = 0;
	do {
	    changes = Cell.getNumberEliminatedCandidates();
	    playGrid.checkRegionsRowsColumns();	
	    //System.out.println("BC CALLED");
	    Grid.BinaryCandidates BC = playGrid.new BinaryCandidates();
	    BC.new PointingRows(); BC.new PointingColumns();
	    playGrid.fillRegionsRowsColumns();	    
	    BC.new BoxedColumns(); BC.new BoxedRows();
	    playGrid.fillRegionsRowsColumns();

	    //BC.printBinRegions();
	    BC.printRegions();

	    System.out.println("NUMBER CANDIDATES ELIMINATED : " + Cell.getNumberEliminatedCandidates() );	
	    System.out.println("NUMBER SOLVED : " + Cell.getNumberSolved() );	    
	}
	while( changes != Cell.getNumberEliminatedCandidates() );

	Printer.printCandidates( playGrid);
	Printer.printGrid( playGrid);
	System.out.println(givenSolutions + " solutions given.  " +
			   (Cell.getNumberSolved() - givenSolutions) + " SOLVED");
	PauseTest.main( new String[0]);
    }
}

