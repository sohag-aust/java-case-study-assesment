package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CertificateUpdateGeneratorTest {

    @Test
    public void generateQuotes() {
        CertificateUpdateGenerator certificateUpdateGenerator = new CertificateUpdateGenerator(10, 100);
        Stream<CertificateUpdate> quotes = certificateUpdateGenerator.generateQuotes();
        assertNotNull(quotes);
        assertEquals(10 * 100, quotes.count());
    }

    @Test
    public void generateISIN() {
        ISINGenerator isinGenerator = new ISINGenerator();
        List<String> isinList = isinGenerator.generateISINList();

        String checkDigit = isinGenerator.getCheckDigit("DE123456789");
        assertEquals("6", checkDigit);

        // try to change check number for checking with checkDigit
        String isinAt3 = isinList.get(2);
        System.out.println("isinAt3 : " + isinAt3); // CDMSXkgHRWB6

        String temp = isinAt3.substring(0, isinAt3.length()-1);
//        System.out.println("temp at 3 : " + temp);

        String first = temp.substring(0,3);
        String temper = "Y";
        String second = temp.substring(4, temp.length());

        String temperedCheckNumber = first.concat(temper).concat(second);
        System.out.println("temperedCheckNumber : " + temperedCheckNumber);

        // checking negative case
        String checkNegativeDigit = isinGenerator.getCheckDigit(isinAt3);
        assertNotEquals("9", checkNegativeDigit);
    }
}