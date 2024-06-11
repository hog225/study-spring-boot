package org.yg.study.JPAsample.manytomany.converter;

import org.yg.study.JPAsample.manytomany.enums.GearType;
import org.yg.study.JPAsample.manytomany.enums.Grade;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;


public class GearTypeConverter implements AttributeConverter<GearType, String> {
    @Override
    public String convertToDatabaseColumn(GearType type) {
        return type.name();
    }

    @Override
    public GearType convertToEntityAttribute(String value) {
        return EnumSet.allOf(GearType.class).stream()
                .filter(type -> type.name().equals(value))
                .findFirst()
                .orElse(null);
    }
}
