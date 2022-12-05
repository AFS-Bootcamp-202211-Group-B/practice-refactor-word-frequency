import java.util.*;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;
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
                Map<String, List<Input>> map =getListMap(inputList);

                inputList = map.entrySet().stream()
                                .map(inputEntry -> new Input(inputEntry.getKey(),inputEntry.getValue().size()))
                                .collect(Collectors.toList());

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                return inputList.stream()
                        .map(word -> String.format("%s %d",word.getValue(),word.getWordCount()))
                        .collect(Collectors.joining("\n"));

            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
