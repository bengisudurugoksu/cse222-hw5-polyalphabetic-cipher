import java.util.Map;
public class encryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String keystream = "";
    private String plain_text;
    private String cipher_text = "";
    //Class to encrypt data.
    public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) { //Constructor for encryptor.
        map = _map;
        key = _key;
        plain_text = text; //Since we use plain_text to encrypt data, plain_text should be assigned to text.
    }

    public void encrypt() {
        generate_keystream();
        generate_cipher_text();
    }

    private void generate_keystream() { //To generate the keystream we look into the string length conditions.
        if(key.length()>plain_text.length()){ //If the key's length is greater, we just take the substring.
            keystream = key.substring(0, plain_text.length());
        }
        else if(key.length()<plain_text.length()){ //If the plain_text's length is greater we add the characters one by one using mod to get the corresponding letters in the key string correctly.
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<plain_text.length(); i++){
                    char c = key.charAt(i%key.length());
                    sb.append(c);
            }
            keystream = sb.toString();
        }
        else{
            keystream = key;
        }

    }

    private void generate_cipher_text() { //
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<plain_text.length();i++) { //Iterating over each character in the plain_text.
            char outerKey = plain_text.charAt(i); //Getting the outer key (plain_text character) at index i.
            char innerKey = keystream.charAt(i); //Getting the inner key (keystream character) at index i.
            Map<Character, Character> innermap = map.get(outerKey); //Getting the map corresponding to the outer key.

            if(innermap != null && innermap.containsKey(innerKey)){ //Checking if the inner map is not null and contains the inner key.
                sb.append(innermap.get(innerKey));  //Appending the corresponding cipher_text character to the StringBuilder
            }
        }
        cipher_text = sb.toString();
    }

    public String get_keystream() { //Returns keystream.
        return keystream;
    }

    public String get_cipher_text() { //Returns cipher_text.
        return cipher_text;
    }
}
