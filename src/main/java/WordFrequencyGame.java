import java.util.*;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String inputStr){
        String[] inputStringSplit = inputStr.split("\\s+");
        try {
            //split the input string with 1 to n pieces of spaces
            List<Input> wordList = Arrays.asList(inputStringSplit).stream()
                    .map(value->new Input(value,1))
                    .collect(Collectors.toList());
            return getCountGroupByWord(wordList).stream()
                    .map(word -> String.format("%s %d",word.getValue(),word.getWordCount()))
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Calculate Error";
        }
    }


    private List<Input> getCountGroupByWord(List<Input> wordList) {
        return wordList.stream()
                .map(input->input.getValue())
                .collect(Collectors.groupingBy( Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(inputEntry -> new Input(inputEntry.getKey(),inputEntry.getValue().intValue()))
                .sorted((word1, word2) -> word2.getWordCount() - word1.getWordCount())
                .collect(Collectors.toList());
    }


}
