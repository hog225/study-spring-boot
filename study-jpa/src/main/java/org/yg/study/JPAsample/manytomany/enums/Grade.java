package org.yg.study.JPAsample.manytomany.enums;

public enum Grade {
    PRO, AMATEUR;

    public static Grade getRandomGrade() {
        return values()[(int) (Math.random() * values().length)];
    }

}
