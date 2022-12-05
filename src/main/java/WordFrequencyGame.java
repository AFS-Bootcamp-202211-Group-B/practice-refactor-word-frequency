import java.util.*;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String inputStr){
        String[] inputStringSplit = inputStr.split("\\s+");
        if (inputStringSplit.length==1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<Input> inputList = Arrays.asList(inputStringSplit).stream()
                                            .map(value->new Input(value,1))
                                            .collect(Collectors.toList());

                //get the map for the next step of sizing the same word
                inputList = getListMap(inputList);

                return inputList.stream()
                        .map(word -> String.format("%s %d",word.getValue(),word.getWordCount()))
                        .collect(Collectors.joining("\n"));

            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }


    private List<Input> getListMap(List<Input> inputList) {
        return inputList.stream()
                .map(input->input.getValue())
                .collect(Collectors.groupingBy( Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(inputEntry -> new Input(inputEntry.getKey(),inputEntry.getValue().intValue()))
                .sorted((w1, w2) -> w2.getWordCount() - w1.getWordCount())
                .collect(Collectors.toList());
    }


}
