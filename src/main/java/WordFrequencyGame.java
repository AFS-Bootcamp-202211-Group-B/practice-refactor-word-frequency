import java.util.*;

import java.util.stream.Collectors;

public class WordFrequencyGame {


    public String getResult(String inputStr) {

        try {
            List<Input> inputList = splitInputStringToList(inputStr);

            //get the map for the next step of sizing the same word
            List<Input> inputCountList = sortList(inputList);
            return outputList(inputCountList);

        } catch (Exception e) {
            return "Calculate Error";
        }
    }


    private String outputList(List<Input> inputCountList)
    {
        return inputCountList.stream()
                .map(output -> output.getValue() + " " + output.getWordCount())
                .collect(Collectors.joining("\n"));
    }

    private List<Input> sortList(List<Input> inputList)
    {
        Map<String, List<Input>> map = getListMap(inputList);
        //split the input string with 1 to n pieces of spaces
        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        inputList = list;
        inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

        return inputList;
    }
    private List<Input> splitInputStringToList(String inputStr)
    {
        //split the input string with 1 to n pieces of spaces

        return  Arrays.stream(inputStr.split("\\s+"))
                .map(splitInputStr -> new Input(splitInputStr, 1))
                .collect(Collectors.toList());
    }

    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = inputList
                .stream()
                .collect(Collectors.groupingBy(Input::getValue));
        return map;
    }
}
