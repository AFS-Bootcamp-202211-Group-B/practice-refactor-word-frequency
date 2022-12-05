import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyGame {
    public static final String SPACES_REGEX = "\\s+";

    public String getResult(String inputStr) {

        if (inputStr.split(SPACES_REGEX).length == 1) {
            return inputStr + " 1";
        } else {

            try {
                 //split the input string with 1 to n pieces of spaces
                 String[] arr = inputStr.split(SPACES_REGEX);

                Map<String, Long> inputMap = Arrays.stream(arr)
                        .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

                Stream<Input> inputStream = inputMap.entrySet().stream()
                        .map(input -> new Input(input.getKey(), input.getValue().intValue()));
                List<Input> inputList = inputStream.sorted(Comparator.comparing(Input::getWordCount).reversed()).collect(Collectors.toList());

                // wordFreq
                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : inputList) {
                    String s = w.getWord() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();

            } catch (Exception e) {

                return "Calculate Error";
            }
        }
    }



}
