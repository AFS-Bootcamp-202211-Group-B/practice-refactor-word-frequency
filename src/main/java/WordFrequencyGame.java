import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;

public class WordFrequencyGame {
    public String getResult(String inputStr){

        if (inputStr.split("\\s+").length==1) {
            return inputStr + " 1";
        } else {
            try {

                List<Input> inputList = splitBySpace(inputStr);

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map =getListMap(inputList);

                List<Input> list = countEachWord(map);
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                return generateResultString(inputList);

            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private String generateResultString(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : inputList) {
            String s = w.getValue() + " " +w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Input> countEachWord(Map<String, List<Input>> map) {
        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()){
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return list;
    }

    public List<Input> splitBySpace(String inputStr){
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }
        return inputList;
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        inputList.forEach(input -> map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input));

        return map;
    }
}
