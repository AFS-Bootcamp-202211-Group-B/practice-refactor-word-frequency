import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;

public class WordFrequencyGame {
    public String getResult(String inputString){


        if (inputString.split("\\s+").length==1) {
            return inputString + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] array = inputString.split("\\s+");

                List<Input> inputList = new ArrayList<>();
                for (String string : array) {
                    Input input = new Input(string, 1);
                    inputList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map =getListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()){
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input word : inputList) {
                    String string = word.getValue() + " " +word.getWordCount();
                    joiner.add(string);
                }
                return joiner.toString();
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
