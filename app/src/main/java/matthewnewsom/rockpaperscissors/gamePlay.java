package matthewnewsom.rockpaperscissors;

import java.util.Random;

/**
 * Created by Matthew on 3/11/2015.
 */
public class gamePlay {
    String mChoice;

    // Function sets the choice of the user based on button click

    public String setChoice(int choice) {

        if (choice == 1) {
            mChoice = "Paper";
        } else if (choice == 2) {
            mChoice = "Scissors";
        } else if (choice == 3) {
            mChoice = "Rock";
        }

        return mChoice;
    }

    // Method sets the computer choice using a random integer
    public String compChoice() {
        String[] mCompChoice = {"Paper", "Scissors", "Rock"};

        Random rand = new Random();
        int maxIndex = mCompChoice.length;
        int generatedIndex = rand.nextInt(maxIndex);

        return mCompChoice[generatedIndex];

    }
    //Method determines who wins

    public String winDeterm(String player, String computer) {

        String winner = "Win";
        String loser = "Lose";
        String tie = "Draw";
        if (player.equals(computer))
            return tie;
        else if (player.equals("Rock") && (computer.equals("Paper")))
            return loser;
        else if (player.equals("Rock") && (computer.equals("Scissors")))
            return winner;
        else if (player.equals("Paper") && (computer.equals("Scissors")))
            return loser;
        else if (player.equals("Paper") && (computer.equals("Rock")))
            return winner;
        else if (player.equals("Scissors") && (computer.equals("Rock")))
            return loser;
        else return winner;


    }

}
