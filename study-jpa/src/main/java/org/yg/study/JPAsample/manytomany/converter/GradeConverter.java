package org.yg.study.JPAsample.manytomany.converter;

import org.yg.study.JPAsample.manytomany.enums.Grade;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.EnumSet;


public class GradeConverter implements AttributeConverter<Grade, String> {
    @Override
    public String convertToDatabaseColumn(Grade type) {
        return type.name();
    }

    @Override
    public Grade convertToEntityAttribute(String value) {
        return EnumSet.allOf(Grade.class).stream()
                .filter(type -> type.name().equals(value))
                .findFirst()
                .orElse(Grade.AMATEUR);
    }
}
