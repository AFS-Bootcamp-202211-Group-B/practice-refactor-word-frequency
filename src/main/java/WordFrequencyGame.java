import java.util.*;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String inputStr){

        if (inputStr.split("\\s+").length==1) {
            return inputStr + " 1";
        }

        try {
            List<Input> inputList = splitInputString(inputStr);
            Map<String, List<Input>> map =getListMap(inputList);
            List<Input> list = calculateNumberOfWordsInMap(map);
            sortTheList(list);
            return getStringJoiner(list).toString();
        }
        catch (Exception e) {
            return "Calculate Error";
        }

    }

    private void sortTheList(List<Input> list) {
        list.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private List<Input> calculateNumberOfWordsInMap(Map<String, List<Input>> map) {
        List<Input> list = new ArrayList<>();
        map.entrySet().stream().forEach(input -> list.add(new Input(input.getKey(), input.getValue().size())));
        return list;
    }

    private StringJoiner getStringJoiner(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        inputList.stream().forEach(input -> joiner.add(input.getValue() + " " + input.getWordCount()));
        return joiner;
    }

    private List<Input> splitInputString(String inputStr) {
        List<Input> inputList = Arrays.stream(inputStr.split("\\s+"))
                .map(str -> new Input(str,1))
                .collect(Collectors.toList());
        return inputList;
    }

    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        inputList.forEach(input -> map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input));
        return map;
    }


}
