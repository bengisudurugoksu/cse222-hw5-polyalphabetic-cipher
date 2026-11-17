import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;
//The class to create the alphabet table using a map.
public class alphabet {
    private Set<Character> english_alphabet = new LinkedHashSet<Character>();
    private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();

    public alphabet() {
        fill_english_alphabet();
        fill_map();
    }

    private void fill_english_alphabet() { 
        for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            english_alphabet.add(c);
        }
    }

    private void fill_map() { //This function uses two iterators. First to go through the english_alphabet set and second two add the inner letters going through the english_alphabet again.
        Iterator<Character> iterator1 = english_alphabet.iterator();
        Iterator<Character> iterator2;

        while (iterator1.hasNext()) { //Creates a substitutionmap for each letter in the alphabet. 
            char rowLetter = iterator1.next();
            Map<Character, Character> substitutionMap = new HashMap<>();

            iterator2 = english_alphabet.iterator();
            char currentChar = rowLetter;

            while (iterator2.hasNext()) { //The substitution letter is the current letter being iterated by iterator2 and the corresponding key is currentChar. After assigning the substitution character currentChar is incremented to the next letter in the alphabet.
                char substitution = iterator2.next();
                substitutionMap.put(substitution, currentChar);
                currentChar++;
                if (currentChar > 'Z') { //If currentChar becomes Z, then we increment to A once again to fill the rest of the map. Logically it shifts. We start from the letter which is the row letter and increment.
                    currentChar = 'A';
                }
            }

            map.put(rowLetter, substitutionMap); //We add the rowLetter and the substitutionMap to fill the map.
        }
    }




    public void print_map() {
        System.out.println("*** Viegenere Cipher ***\n\n");
        System.out.println("    " + english_alphabet);
        System.out.print("    ------------------------------------------------------------------------------");
        for(Character k: map.keySet()) {
            System.out.print("\n" + k + " | ");
            System.out.print(map.get(k).values());
        }
        System.out.println("\n");

    }

    public Map get_map() {
        return map;
    }
}

