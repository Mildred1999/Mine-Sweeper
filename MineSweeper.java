import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class MineSweeper extends JFrame{
	JPanel boardDisplay = new JPanel();
	Square[][]board; 
	JLabel nmines = new JLabel("Number of mines:");
	JTextField nmineslabel = new JTextField(10);
	JPanel statuspanel = new JPanel();
	int minecount;
	
	public MineSweeper(int size) {  // constructors have name of the class in it
		int row, col;
		board = new Square [size][size];
		Square square;
		setTitle("Mine Sweeper");
		setSize(size*60,size*60);
		setLayout(new BorderLayout());
		boardDisplay.setLayout(new GridLayout(size,size));
		for (row=0;row<size;row++)
			for(col=0;col<size;col++) {
				square = new Square(Math.random()<0.10);
				if (square.mine)
					minecount++;
				square.row = row;
				square.col = col;
				square.game = this;
				boardDisplay.add (square);
				board[row][col]=square;
			}
		add(boardDisplay, BorderLayout.CENTER);
		statuspanel.add(nmines);
		statuspanel.add(nmineslabel);
		nmineslabel.setText(""+minecount);
		add(statuspanel, BorderLayout.NORTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void checkForWin() {
		boolean win = true;
		 for (int r = 0; r<board.length;r++) {
			 for(int c = 0; c<board.length;c++) {
				 Square b = board[r][c];
				 if(!b.revealed && !b.mine)
					 win = false;
			 }
		 }
		 if(win==true) {
			 JOptionPane.showMessageDialog(null, "You Win!");
			 dispose();
		 }
	}
	public static void main(String[] args) {
		MineSweeper mygame1, yourgame;
		mygame1 = new MineSweeper(5);
		yourgame = new MineSweeper(10);
	}

}
