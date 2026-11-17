public class preprocessor {
    private String initial_string;
    private String preprocessed_string;
    //Class to preprocess strings.	
    public preprocessor(String str) { //Constructor for preprocessor.
        initial_string = str;
    }

    public void preprocess() {
        capitalize();
        clean();
    }

    private void capitalize() { //Using toUpperCase to capitalize all of the letters.
        initial_string = initial_string.toUpperCase();
    }

    private void clean() { //Using replaceAll to remove unnecessary characters such as spaces, numbers and punctuation marks.
        preprocessed_string = initial_string.replaceAll("[^a-zA-Z]", "");
    }

    public String get_preprocessed_string() { //Returns preprocessed_string.
        return preprocessed_string;
    }
}
