/**
 * Spelling bee Class
 * Gives a list of all possible words made with 7 provided letters
 * List of words exceeds NYT Spelling Bee possibilities, so not all words the program returns will be answers to your
   Spelling bee puzzle, but all answers to the Spelling bee puzzle should be in the list returned by the program
 * @author Lucija Banovic
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpellingBee {

    CharSequence cl;
    Character l1, l2, l3, l4, l5, l6;

    public SpellingBee (CharSequence a, Character b, Character c, Character d, Character e, Character f, Character g) {
        cl = a;
        l1 = b;
        l2 = c;
        l3 = d;
        l4 = e;
        l5 = f;
        l6 = g;
    }

    public CharSequence getCl() {return cl;}
    public Character getL1() {return l1;}
    public Character getL2() {return l2;}
    public Character getL3() {return l3;}
    public Character getL4() {return l4;}
    public Character getL5() {return l5;}
    public Character getL6() {return l6;}


    public ArrayList<String> findWords () throws IOException {
        ArrayList<String> allWords = new ArrayList<>();
        ArrayList<String> validWords = new ArrayList<>();
        BufferedReader input = new BufferedReader(new FileReader("inputs/words.txt"));
        String line;

        while ((line = input.readLine()) != null) {
            allWords.add(line.toLowerCase());
        }

        input.close();

        ArrayList<Character> letters = new ArrayList<>();
        letters.add(getCl().charAt(0)); letters.add(getL1()); letters.add(getL2());
        letters.add(getL3()); letters.add(getL4()); letters.add(getL5()); letters.add(getL6());

        boolean contains = true;

        for (String word: allWords) {
            if (word.length()>=4 && word.contains(getCl())) {
                for (int i=0; i<word.length(); i++) {
                    if (!letters.contains(word.charAt(i))) {
                        contains = false;
                        break;
                    }
                }
                if (contains) {validWords.add(word);}
            }
            contains = true;
        }

        return validWords;
    }

    public String toString () {
        try {
            String str = "";
            ArrayList<String> list = findWords();
            char firstLetter = list.get(0).charAt(0);
            boolean first = true;

            for (int i=0; i<list.size(); i++) {
                if (first) {
                    str += firstLetter + ":\n";
                    first = false;
                }
                if (list.get(i).charAt(0) == firstLetter) {
                    str += "     " + list.get(i) + "\n";
                } else {
                    firstLetter = list.get(i).charAt(0);
                    first = true;
                }
            }
            return str;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        SpellingBee test = new SpellingBee("b", 'e', 'i', 'l', 'p', 'c', 'a');
        System.out.println(test);
    }
}
