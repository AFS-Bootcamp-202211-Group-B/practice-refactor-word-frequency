import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;

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
        return joinWordCountStringTemp(inputList);
    }
    private StringJoiner joinWordCountStringTemp(List<Input> inputList){
        StringJoiner joiner = new StringJoiner("\n");
        inputList.stream().map(input -> input.getValue() + " " +input.getWordCount()).forEach(input ->joiner.add(input));
        return joiner;
    }

    private List<Input> countEachWord(List<Input> inputList) {
        Map<String, List<Input>> map =getListMap(inputList);

        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()){
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return list;
    }

    private static List<Input> splitInput(String inputString) {
        return Arrays.stream(inputString.split("\\s+"))
                .map(string -> new Input(string,1))
                .collect(Collectors.toList());

    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
            if (!map.containsKey(input.getValue())){
                ArrayList array = new ArrayList<>();
                array.add(input);
                map.put(input.getValue(), array);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
