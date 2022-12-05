import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;

import static oracle.jrockit.jfr.events.Bits.intValue;

public class WordFrequencyGame {

    private static final int MIN_WORD_LENGTH = 1;

    public String getResult(String inputString){


        if (inputString.split("\\s+").length== MIN_WORD_LENGTH) {
            return inputString + " 1";
        } else {

            try {
                List<Input> inputList = splitInput(inputString);
                StringJoiner joiner = generateWordCountString(inputList);
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private StringJoiner generateWordCountString(List<Input> inputList) {
        List<Input> list = countEachWord(inputList);

        inputList = list;

        inputList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

        StringJoiner joiner = joinWordCountString(inputList);
        return joiner;
    }

    private StringJoiner joinWordCountString(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        inputList.stream().map(input -> input.getValue() + " " +input.getWordCount()).forEach(input ->joiner.add(input));
        return joiner;
    }

    private List<Input> countEachWord(List<Input> inputList) {
        Map<String, Long> map = inputList.stream()
                .collect( Collectors.groupingBy( Input::getValue, Collectors.counting()));


        return map.entrySet().stream().map(entry ->new Input(entry.getKey(), intValue(entry.getValue()))).collect(Collectors.toList());
    }

    private static List<Input> splitInput(String inputString) {
        return Arrays.stream(inputString.split("\\s+"))
                .map(string -> new Input(string,1))
                .collect(Collectors.toList());

    }

}
