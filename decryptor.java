import java.util.Map;
import java.util.Iterator;
public class decryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String keystream = "";
    private String plain_text = "";
    private String cipher_text;

    public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) { //Constructor for the decryptor, since we use the cipher_text to decrypt, cipher_text should be assigned to text.
        map = _map;
        key = _key;
        cipher_text = text;

    }

    public void decrypt() {
        generate_keystream();
        generate_plain_text();
    }

    private void generate_keystream() { //To generate the keystream we look into the string length conditions.
        if(key.length()>cipher_text.length()){ //If the key's length is greater, we just take the substring.
            keystream = key.substring(0, cipher_text.length());
        }
        else if(key.length()<cipher_text.length()){ //If the cipher_text's length is greater we add the characters one by one using mod to get the corresponding letters in the key string correctly.
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<cipher_text.length(); i++){
                char c = key.charAt(i%key.length());
                sb.append(c);
            }
            keystream = sb.toString();
        }
        else{
            keystream = key;
        }

    }
    private void generate_plain_text() {
        if (keystream == null || cipher_text == null || map == null) { //Checking if necessary data is available.
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keystream.length(); i++) {
            char keystreamChar = keystream.charAt(i);
            char cipherChar = cipher_text.charAt(i); 
            Iterator<Character> iterator = map.get(keystreamChar).keySet().iterator();//Using an iterator to iterate over the rows in the column corresponding to the keystream character.
            while (iterator.hasNext()) {
                char rowChar = iterator.next();
                //Checking if the value in the row matches the ciphertext character
                if (map.get(rowChar).get(keystreamChar) == cipherChar) {
                    sb.append(rowChar); //Appending the rowChar if the condition is satisfied.
                    break; //No need to continue searching.
                }
            }
        }
        plain_text = sb.toString();
    }


    public String get_keystream() { //Returns keystream.
        return keystream;
    }

    public String get_plain_text() { //Returns plain_text.
        return plain_text;
    }
}
