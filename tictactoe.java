package javaseminarski;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class tictactoe implements ActionListener{

	//Create new window
	JFrame window = new JFrame("Tic Tac Toe");
	
	//Board size is always 9
	private final static int boardSize=9;
	
	//Create 9 buttons
    public static JButton board[];
    
    //if false then first player plays,else second
    boolean player = false;
    
    //Array for store player inputs
    String[] values = new String[9];
    
    //String that can be X or O
    String operation="";
    
    //If numberOfPlays is 0 then game over.
    private static int numberOfPlays=9;
   
    
    
    //Constructor-create game window, initialize board, buttons.
    public tictactoe()
    {
    	
    	
    	window.setSize(400,400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(3,3));
        window.setLocationRelativeTo(null);
        
        createBoard();
        setDefaultValueForValues();
        initializeButtons();
        
        window.setVisible(true);
    	
    }
    
    private void createBoard()
    {
    	 board= new JButton[boardSize];
    }
    private void setDefaultValueForValues()
    {
    	for (int i = 0; i < 9; i++)
    	{
            values[i] = "";
        }
    }

    private void initializeButtons()
    {
        for(int i = 0; i < 9; i++)
        {
            board[i] = new JButton();
            addListener(i);
            board[i].setFocusPainted(false);
            //Add buttons to board
            window.add(board[i]);       
        }
        setDefaultValueForButtons();
    }
    
    private void setDefaultValueForButtons()
    {
    	for(int i=0;i<9;i++)
    	    board[i].setText("");
    }
   

    private void addListener(int i)
    {
    	// Add ActionListener on every button
           board[i].addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent e)
	{
		numberOfPlays--;
		player=!player;
		
        if (player)
            operation="X";
        if (!player)
            operation="O";
        

        //Draw on board player input, set that button as enabled/false and add player operation in array with players inputs.
        for (int i = 0; i < 9; i++)
        {
            if (e.getSource() == board[i])
            {
            	board[i].setText(operation);
                board[i].setEnabled(false);
                values[i] = operation;
            }
        }
        //Every time player plays then check for win.
        haveWinner();
        	
	}
	private void haveWinner()
	{
		if (checkForWin()) {
			JDialog dialog = new JDialog(window, "Game Result"); 
            JLabel textInfo = new JLabel("Player "+ operation + " wins!"); 
            JButton btn = new JButton("Restart Game"); 
            JPanel gameResult = new JPanel(); 
            
            btn.addActionListener(new ActionListener() { 
            	  public void actionPerformed(ActionEvent e) { 
            		  for (int i = 0; i < 9; i++)
            			  board[i].setEnabled(true);
            		  dialog.dispose();
            	  } } );
            
            
            gameResult.add(textInfo); 
            gameResult.add(btn); 
            
            dialog.add(gameResult); 
 
            dialog.setSize(200, 100); 

            dialog.setVisible(true); 
            dialog.setLocationRelativeTo(null);
  
			restartGame();
			
        } else if (!checkForWin() && numberOfPlays==0) {
        	JDialog dialog = new JDialog(window, "Game Result"); 
            JLabel textInfo = new JLabel("Game has ended tie"); 
            JButton btn = new JButton("Restart Game"); 
            JPanel gameResult = new JPanel(); 
            
            btn.addActionListener(new ActionListener() { 
            	  public void actionPerformed(ActionEvent e) { 
            		  for (int i = 0; i < 9; i++)
            			  board[i].setEnabled(true);
            		  dialog.dispose();
            	  } } );
            
            
            gameResult.add(textInfo); 
            gameResult.add(btn); 
            
            dialog.add(gameResult); 
 
            dialog.setSize(200, 100); 

            dialog.setVisible(true); 
            dialog.setLocationRelativeTo(null);
  
			restartGame();
        }
	}
	 public boolean checkForWin()
     {
		 //horizontal
         if( checkElements(0,1) && checkElements(1,2) )
             return true;
         else if( checkElements(3,4) && checkElements(4,5) )
             return true;
         else if ( checkElements(6,7) && checkElements(7,8))
             return true;
         
         //vertical
         else if ( checkElements(0,3) && checkElements(3,6))
             return true;  
         else if ( checkElements(1,4) && checkElements(4,7))
             return true;
         else if ( checkElements(2,5) && checkElements(5,8))
             return true;
         
         //diagonal
         else if ( checkElements(0,4) && checkElements(4,8))
             return true;  
         else if ( checkElements(2,4) && checkElements(4,6))
             return true;
         else 
             return false;
         
     }
	 //Check if position a and position b have equals value
	 public boolean checkElements(int a, int b)
     {
         if ( board[a].getText().equals(board[b].getText()) && !board[a].getText().equals("") )
             return true;
         else
             return false;
     }
	 
	 private void restartGame()
	 {
		 numberOfPlays=9;
		 operation="";
		 setDefaultValueForValues();
		 setDefaultValueForButtons();
	 }
	 
	 //CREATE GAME
	 public static void main(String[] args) {
	        new tictactoe();
	    }
    
}
