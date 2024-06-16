package org.yg.study.JPAsample.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

public class PrettySqlFormat implements MessageFormattingStrategy {

    private final Formatter formatter = new BasicFormatterImpl();


    @Override
    public String formatMessage(int i, String s, long l, String s1, String s2, String s3, String s4) {
        return formatter.format(s3);
    }
}