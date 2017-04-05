/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Library {

    public static String subsequences(String word) {

        if(word.isEmpty()){
            return "";
        }
        else {
            char firstLetter = word.charAt(0);
            String restOfWord = word.substring(1);

            String subsequencesOfRest = subsequences(restOfWord);

            String result = "";
            for (String subsequence : subsequencesOfRest.split(",", -1)){
                result += "," + subsequence;
                result += "," + firstLetter + subsequence;
            }
            //wywalic przecinek
            result = result.substring(1);
            return result;
        }

    }
}