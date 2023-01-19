package pl.edu.agh.bankosdelakolunios.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagList {
    public enum Tag {
        TAG1, TAG2, TAG3, TAG4, TAG5;
    }

    public List<String> getTags(){
        List<String> list = new ArrayList<>();
        Arrays.stream(Tag.values()).toList().forEach(e -> list.add(e.toString()));
        return list;
    }
}
