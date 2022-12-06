package com.diploma.climber.enums;

public enum ClimbingTypes {
    BOULDERING("Bouldering"),
    SPORT("Rope climbing"),
    TRAD("Trad climbing"),
    SPEED("Speed climbing"),
    SUICIDE("Free solo");

    private String name;

    ClimbingTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
