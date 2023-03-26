package com.playground.EnumStudy;

import java.util.List;

public enum Color {
    RED("Dragon hue"),
    GREEN("Jungle flowers"),
    BLUE("Ocean fish blue");

    private String description;
    Color(String description) {
        this.description = description;
    }
    public void setDescription(Color color, String description) {
        color.name();
        color.description = description;
    }
    public String getDescription() {
        return description;
    }
}

class ColorTest {

    public static void main(String[] args) {
        List<Color> colorList = List.of(Color.RED, Color.BLUE, Color.GREEN);
        colorList.forEach(c -> {
            System.out.println(c + " : " + c.getDescription());
        });



//        Color c1 = Color.RED;
//        System.out.println(c1.getDescription());
//        c1.setDescription(Color.RED, "THIS IS A TEST DESC");
//        System.out.println(c1.getDescription());
    }
}