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

         //split the input string with 1 to n pieces of spaces
         String[] wordsArr = inputStr.split(SPACES_REGEX);

        Map<String, Long> inputMap = countWordsFrequency(wordsArr);
        Stream<Input> inputStream = convertMapToInputStream(inputMap);
        List<Input> inputList = sortInputList(inputStream);

        // wordFreq
        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : inputList) {
            String s = w.getWord() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();

    }

    private List<Input> sortInputList(Stream<Input> inputStream) {
        return inputStream.sorted(Comparator.comparing(Input::getWordCount).reversed()).collect(Collectors.toList());
    }

    private Stream<Input> convertMapToInputStream(Map<String, Long> inputMap) {
        return inputMap.entrySet().stream()
                .map(input -> new Input(input.getKey(), input.getValue().intValue()));
    }

    private Map<String, Long> countWordsFrequency(String[] wordsArr) {
        return Arrays.stream(wordsArr)
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));
    }


}
