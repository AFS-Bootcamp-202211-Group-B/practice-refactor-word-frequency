import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String INPUT_WORD_DELIMITER = "\\s+";
    private static final String OUTPUT_LINE_DELIMITER = "\n";

    public String getResult(String inputStr){
        String[] inputStringSplit = inputStr.split(INPUT_WORD_DELIMITER);
        try {
            //split the input string with 1 to n pieces of spaces
            List<String> wordList = Arrays.asList(inputStringSplit);
            List<Input> wordListCountByWord = getCountGroupByWord(wordList);
            return wordListCountByWord.stream()
                    .map(word -> String.format("%s %d",word.getWord(),word.getWordCount()))
                    .collect(Collectors.joining(OUTPUT_LINE_DELIMITER));
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }


    private List<Input> getCountGroupByWord(List<String> wordList) {
        return wordList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(inputEntry -> new Input(inputEntry.getKey(),inputEntry.getValue().intValue()))
                .sorted((word1, word2) -> word2.getWordCount() - word1.getWordCount())
                .collect(Collectors.toList());
    }


}
