package org.yg.study.JPAsample.manytomany.enums;

public enum GearType {
    CADDY_BAG,
    CLUB;

    public static GearType getRandomGearType() {
        return values()[(int) (Math.random() * values().length)];
    }
}
