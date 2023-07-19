import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener{
    Random random = new Random();
    JFrame f = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel label = new JLabel();
    JButton[] button = new JButton[9];
    boolean xTurn;
    ImageIcon icon = new ImageIcon("tictactoe.png");

    TicTacToe(){
        //set Frame
        f.setSize(800,800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.getContentPane().setBackground(new Color(50,50, 50));
        f.setVisible(true);
        f.setIconImage(icon.getImage());

        //label
        label.setBackground(new Color(25, 25, 25));
        label.setForeground(new Color(25, 255, 0));
        label.setFont(new Font("Ink Free",Font.BOLD,75));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic-Tac-Toe");
        label.setOpaque(true);

        //title panel
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        //button panel
        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(150,150,150 ));
        for(int i=0;i<9;i++){
            button[i] = new JButton();
            button[i].setBackground(new Color(255, 204, 255));
            button[i].setFont(new Font("MV Boli",Font.BOLD,170));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
            buttonPanel.add(button[i]);
        }

        //add panels in frame
        titlePanel.add(label);
        f.add(titlePanel,BorderLayout.NORTH);
        f.add(buttonPanel);

        turn();
    }

    //It shows in label ,which turn will be first (X or O)?
    public void turn(){
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            xTurn=true;
            label.setText("X's turn");
        }
        else{
            xTurn=false;  
            label.setText("O's turn");
        }
    }

    //action performed method
    public void actionPerformed(ActionEvent e){
        for(int i=0;i<9;i++)
        {
            if(e.getSource()==button[i]){
                if(xTurn){
                    if(button[i].getText()==""){
                        button[i].setForeground(new Color(0,0,204));
                        button[i].setText("X");
                        xTurn=false;
                        label.setText("O's turn");
                        checkWinners();
                    }
                }
                else{
                    if(button[i].getText()==""){
                        button[i].setForeground(new Color(255,0,0));
                        button[i].setText("O");
                        xTurn=true;
                        label.setText("X's turn");
                        checkWinners();
                    }
                }
            }
        }
        
    }
    public void checkWinners(){
        //check rows for "O" 
        if((button[0].getText()=="O") && (button[1].getText()=="O") && (button[2].getText()=="O")){
            oIsWinner(0,1,2);
        }
        else if((button[3].getText()=="O") && (button[4].getText()=="O") && (button[5].getText()=="O")){
            oIsWinner(3, 4, 5);
        }
        else if((button[6].getText()=="O") && (button[7].getText()=="O") && (button[8].getText()=="O")){
            oIsWinner(6, 7, 8);
        }
        //check columns for "O"
        else if((button[0].getText()=="O") && (button[3].getText()=="O") && (button[6].getText()=="O")){
            oIsWinner(0,3,6);
        }
        else if((button[1].getText()=="O") && (button[4].getText()=="O") && (button[7].getText()=="O")){
            oIsWinner(1, 4, 7);
        }
        else if((button[2].getText()=="O") && (button[5].getText()=="O") && (button[8].getText()=="O")){
            oIsWinner(2, 5, 8);
        }
        //check diagonally for "O"
        else if((button[0].getText()=="O") && (button[4].getText()=="O") && (button[8].getText()=="O")){
            oIsWinner(0, 4, 8);
        }
        else if((button[2].getText()=="O") && (button[4].getText()=="O") && (button[6].getText()=="O")){
            oIsWinner(2, 4, 6);
        }

        //check rows for "X" 
        else if((button[0].getText()=="X") && (button[1].getText()=="x") && (button[2].getText()=="X")){
            xIsWinner(0,1,2);
        }
        else if((button[3].getText()=="X") && (button[4].getText()=="X") && (button[5].getText()=="X")){
            xIsWinner(3, 4, 5);
        }
        else if((button[6].getText()=="X") && (button[7].getText()=="X") && (button[8].getText()=="X")){
            xIsWinner(6, 7, 8);
        }
        //check columns for "X"
        else if((button[0].getText()=="X") && (button[3].getText()=="X") && (button[6].getText()=="X")){
            xIsWinner(0,3,6);
        }
        else if((button[1].getText()=="X") && (button[4].getText()=="X") && (button[7].getText()=="X")){
            xIsWinner(1, 4, 7);
        }
        else if((button[2].getText()=="X") && (button[5].getText()=="X") && (button[8].getText()=="X")){
            xIsWinner(2, 5, 8);
        }
        //check diagonally for "X"
        else if((button[0].getText()=="X") && (button[4].getText()=="X") && (button[8].getText()=="X")){
            xIsWinner(0, 4, 8);
        }
        else if((button[2].getText()=="X") && (button[4].getText()=="X") && (button[6].getText()=="X")){
            xIsWinner(2, 4, 6);
        }
        else{
            tie();
        }
    }
    //it executed when O will win
    public void oIsWinner(int a,int b,int c){
        button[a].setBackground(Color.GREEN);
        button[b].setBackground(Color.GREEN);
        button[c].setBackground(Color.GREEN);
        for(int i=0;i<9;i++){
            button[i].setEnabled(false);
        }
        label.setText("O Wins");
        reset();
    }
    //it executed when X will win
    public void xIsWinner(int a,int b,int c){
        button[a].setBackground(Color.GREEN);
        button[b].setBackground(Color.GREEN);
        button[c].setBackground(Color.GREEN);
        for(int i=0;i<9;i++){
            button[i].setEnabled(false);
        }
        label.setText("X Wins");
        reset();
    }
    //it executed when game will tie
    public void tie(){
        boolean tiee = true;
        for(int i=0;i<9;i++){
            if(button[i].getText().isEmpty()) {
                tiee=true;
            }
            else{
                tiee=false;
            }
        }
        if(tiee==false){
            label.setText("Tie Game!!");
            for(int j=0;j<9;j++){
                button[j].setEnabled(false);   
            }
            reset();
        }
    }
    public void reset(){
        for (int i = 0; i < 9; i++) {
            button[i].setText("");
            button[i].setEnabled(true);
            button[i].setBackground(new Color(255, 204, 255));
        }
        xTurn = true;
        
    }
    //main method
    public static void main(String[] args) {
        new TicTacToe();
        
    }
}
