// Day 9 of 100
// Hangman complete

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class HangMan {
    public static void main(String[] args) throws IOException
    {
        int fail = 0, triesLeft, level;
        String progress;
        char playerChoice;
        Scanner Take = new Scanner(System.in);
        System.out.print("\n\nLet's play Hangman.\nEnter a level: 1 to 3.\n[Higher the number, lower the tries]: ");
        level = setLevel(Take.nextInt());
        String Movie = getWord().toUpperCase();

        // System.out.println(Movie);  // Cheat
        
        List <Character> guess = new ArrayList<>();
        while (fail < level){
            triesLeft = level - fail;
            drawNoose(triesLeft);
            progress = printProgress(guess, Movie);
            System.out.println("\nRemaining tries are: " + triesLeft);
            if (Movie.equals(progress)){
                
                System.out.println("\nCongratulations.!! You've guessed it in time.\nYou have scored " + Math.round((triesLeft*100.00f)/level) + "\n\n");
                break;}
            else {
            System.out.println("Enter a letter..\n");
            playerChoice = Take.next().toUpperCase().charAt(0);
            guess.add(playerChoice);
            if (Movie.contains(String.valueOf(playerChoice))) {
                System.out.println(playerChoice +" - exists\n");}
            else{
                fail++;
                if (fail==level) {
                    System.out.println("\nGame Over (x_x)\n   The Movie was: " + Movie + "\n\n");
                    break;
                }
                System.out.println(playerChoice + " - Does not exist.");
                
            }}
            
        }
            Take.close();
    }

    public static void drawNoose(int hang){
        switch (hang) {

            case 1: System.out.println("  _______\n |     |\n |   (x_X)\n |    /|\\\n |    / \\\n |      \n_|_");
                break;
            case 2: System.out.println("  _______\n |     |\n |   (O_O)\n |    /|\\\n |    / \\\n |      \n_|_");
                break;
            case 3: System.out.println("  _______\n |     |\n |   (-_-)\n |    /|\\\n |    / \\\n |      \n_|_");
                break;
            case 4: System.out.println("  _______\n |     |\n |   (-_-)\n |    /|\\\n |    / \n |      \n_|_");
                break;
            case 5:System.out.println(" _______\n |     |\n |   (-_-)\n |    /|\\\n |      \n |      \n_|_");
                break;
            case 6: System.out.println("  _______\n |     |\n |   (-_-)\n |    /|\n |      \n |      \n_|_");
                break;
            case 7: System.out.println("  _______\n |     |\n |   (-_-)\n |     |\n |      \n |      \n_|_");
                break;
            case 8: System.out.println("  _______\n |     |\n |   (-_-)\n |      \n |      \n |      \n_|_");
                break;
            case 9: System.out.println("  _______\n |     |\n |     \n |      \n |      \n |      \n_|_");
                break;
            default:
        }
    }

    public static int setLevel(int Level) {
        switch (Level) {
            case 1: return 9;
            case 2: return 6;
            case 3: return 4;
            default: 
            System.out.println("Invalid option hence difficulty set to 2.");
            return 6;
        }
    }

    @SuppressWarnings("rawtypes")
    public static String printProgress(List words, String movie) {
        StringBuilder progressSoFar = new StringBuilder();
        for (int i = 0; i < movie.length(); i++) {      // Print word
            if (words.contains(movie.charAt(i))){
            progressSoFar.append(movie.charAt(i));
            System.out.print(movie.charAt(i));}
            else if (movie.charAt(i) == ' '){
            progressSoFar.append(" ");
            System.out.print(" ");}
            else{
            progressSoFar.append("_");
            System.out.print("_");}

        }
        System.out.println();
        return progressSoFar.toString();
    }

    public static String getWord () throws IOException {

        Scanner read = new Scanner(new File("./Files/Movies.txt"));
        List <String> movieList = new ArrayList<>();
        while (read.hasNext()){
            movieList.add(read.nextLine());
        }
        read.close();
        Random dice = new Random();
        String myMovie = movieList.get(dice.nextInt(movieList.size()));
        return myMovie;
    }

}
