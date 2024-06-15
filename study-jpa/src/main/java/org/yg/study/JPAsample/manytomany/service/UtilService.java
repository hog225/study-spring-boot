package org.yg.study.JPAsample.manytomany.service;

import java.util.Random;

public class UtilService {
    public static final String[] countryCodes = {
            "US", "CA", "MX", "BR", "AR", "GB", "FR", "DE", "IT", "ES",
            "CN", "JP", "KR", "IN", "RU", "AU", "NZ", "ZA", "EG", "NG",
            "KE", "GH", "DZ", "MA", "SN", "ET", "TN", "UG", "CM", "ZA",
            "AE", "SA", "IR", "IQ", "TR", "IL", "JO", "LB", "SY", "YE",
            "TH", "VN", "MY", "SG", "ID", "PH", "BD", "LK", "PK", "AF"
    };
    public static String getRandomCountry() {
        Random random = new Random();
        int index = random.nextInt(countryCodes.length);
        return countryCodes[index];
    }
}
