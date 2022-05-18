package org.yg.study.JPAsample.converter;

import javax.persistence.AttributeConverter;

public class TruncateStringConverter implements AttributeConverter<String, String> {

    private static final int LIMIT = 10;
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if(attribute == null)
            return null;
        else if(attribute.length() > 20) {
            return attribute.substring(0, 20);
        } else {
            return attribute;
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
