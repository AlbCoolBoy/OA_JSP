package com.example.OA_JSP;

public class Dept {
    private String number;
    private String name;
    private String location;

    public Dept() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
