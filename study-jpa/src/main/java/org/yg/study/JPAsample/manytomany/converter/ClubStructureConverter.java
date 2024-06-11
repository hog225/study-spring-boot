package org.yg.study.JPAsample.manytomany.converter;

import org.yg.study.JPAsample.manytomany.enums.ClubStructureType;
import org.yg.study.JPAsample.manytomany.enums.Grade;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;


public class ClubStructureConverter implements AttributeConverter<ClubStructureType, String> {
    @Override
    public String convertToDatabaseColumn(ClubStructureType type) {
        return type.name();
    }

    @Override
    public ClubStructureType convertToEntityAttribute(String value) {
        return EnumSet.allOf(ClubStructureType.class).stream()
                .filter(type -> type.name().equals(value))
                .findFirst()
                .orElse(null);
    }
}
