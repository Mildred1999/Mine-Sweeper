import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Square extends JButton{
	boolean mine;
	boolean flagged = false;
	int row,col;
	boolean revealed = false;
	MineSweeper game;
	
	public Square(boolean makemine) {
		mine = makemine;
		//if (mine) setText("*");
		addMouseListener(new SquareListener());
	}
	
	class SquareListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			System.out.println("Clicked button: "+e.getButton());
			
			if(flagged&&e.getButton()==1) return;
			else if(!flagged && e.getButton()==1) {
				expose(row, col);
				game.checkForWin();
			}
			else if(e.getButton()==3) { 
				System.out.println("flag");
				if(flagged) {
					flagged = false;
					game.minecount++;
					game.nmineslabel.setText(""+game.minecount);
					setBackground(null);
					return;
				}
				flagged=true;
				game.minecount--;
				game.nmineslabel.setText(""+game.minecount);
				setBackground(Color.yellow);
				return;
			}
			int minecount = 0;
			int r,c;
			if(mine) {
				setBackground(Color.red);
				setText("*");
				JOptionPane.showMessageDialog(null, "BOOM, Game over");
				return;
			}
			
			expose(row,col);
		}
			
			public void expose(int x, int y) {
				int r,c;
				int minecount = 0;
				if(game.board[x][y].revealed) return;
				game.board[x][y].setBackground(Color.white);
				game.board[x][y].revealed = true;
				for (r=x-1; r<=x+1;r++) {
					for(c=y-1;c<=y+1;c++) {
						if (r<0||r>=game.board.length)break;
						if(c<0||c>=game.board.length)continue;
						if(game.board[r][c].mine)
							minecount++;
					
				}
			}
			if (minecount>0) 
				game.board[x][y].setText(""+minecount);
			else {
				for (r=x-1; r<=x+1;r++) {
					for(c=y-1;c<=y+1;c++) {
						if (r<0||r>=game.board.length)break;
						if(c<0||c>=game.board.length)continue;
						expose(r,c);
						//game.board[x-r][y-c].doClick();
			}
				}
			}
			}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
	}
}

		
