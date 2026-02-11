package com.solvians.showcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ISINGenerator {

    private static final Map<Character, Integer> lookup = new HashMap<>();

    public ISINGenerator() {
        generateLookup();
    }

    public List<String> generateISINList() {
        List<String> isinList = new ArrayList<>();

        for(int i=65;i<90;i++) {
            int j=i+1;

            char a = (char)i;
            char b = (char)j;

            String newValue = String.valueOf(a).concat(String.valueOf(b)).concat(getAlphanumericValue());
            System.out.println("\n\nnewValue: " + newValue);
            String isin = getISIN(newValue);
//            String isin = getISIN("DE123456789");
            System.out.println("Final ISIN : " + isin);
            isinList.add(isin);
        }

        return isinList;
    }

    /***
     * TODO : hint : as the problem statement doesn't mention the alphanumeric char could be upper and lower that why i am considering both upper and lower
     * also the lookup only mention the table for upper case starting from A=10 and Z=35
     * so i am considering a=36 and z=61
     */
    private String getAlphanumericValue() {
        String alphanumericString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        ThreadLocalRandom random = ThreadLocalRandom.current();

        StringBuilder resultString = new StringBuilder(9);
        for (int i = 0; i < 9; i++) {
            int randomIndex = random.nextInt(alphanumericString.length());
            resultString.append(alphanumericString.charAt(randomIndex));
        }
        return resultString.toString();
    }

    public String getISIN(String isin) {
        String checkDigit = getCheckDigit(isin);
        return isin.concat(checkDigit);
    }

    public String getCheckDigit(String isin) {
        StringBuilder checkDigitBuilder = new StringBuilder();

        for(int i=0;i<isin.length();i++) {
            char ch = isin.charAt(i);
            int lookupValue;

            if((ch >= 'A' && ch <= 'Z') || (ch>='a' && ch <= 'z')) {
                lookupValue = lookup.get(ch);
            } else {
                lookupValue = Integer.parseInt(String.valueOf(ch));
            }

            if(lookupValue>=10) {
                StringBuilder temp = new StringBuilder();

                while(lookupValue != 0) {
                    int div = lookupValue%10;
                    temp.append(div);
                    lookupValue/=10;
                }
                temp.reverse();
                checkDigitBuilder.append(temp);
            }
            else {
                checkDigitBuilder.append(lookupValue);
            }
        }

        StringBuilder multiply = new StringBuilder();
        String first = checkDigitBuilder.reverse().toString();

        for(int i=0;i<first.length();i++) {
            int value;
            if(i%2 == 0) {
                value = Integer.parseInt(String.valueOf(first.charAt(i)));
                value*=2;
                multiply.append(value);
            }
            else {
                value = Integer.parseInt(String.valueOf(first.charAt(i)));
                multiply.append(value);
            }
        }

        int sum = 0;
        for(int i=0;i<multiply.length();i++) {
            sum += Integer.parseInt(String.valueOf(multiply.charAt(i)));
        }

        int multiplication=1;
        int i=1;
        while(sum > multiplication) {
            multiplication = i*10;
            i++;
        }

        System.out.println("sum : " + sum);
        int checkDigit = multiplication-sum;
        System.out.println("checkDigit : " + checkDigit);
        return String.valueOf(checkDigit);
    }

    private void generateLookup() {
        for(int i=65;i<=90;i++) {
            char ch = (char)i;
            lookup.put(ch, (i-55));
        }

        for(int i=97;i<=122;i++) {
            char ch = (char) i;
            lookup.put(ch, (i-61));
        }

//        System.out.println("lookup value of a: " + lookup.get('a'));
//        System.out.println("lookup value of Z: " + lookup.get('Z'));
    }
}
