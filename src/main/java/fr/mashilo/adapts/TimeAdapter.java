package fr.mashilo.adapts;

import java.util.HashMap;
import java.util.Map;

public class TimeAdapter {

    public TimeAdapter(){}
    public int convert(String rawTime) {
        /*
        Transform a String like         2d10h2m80s
        To a number of seconds, so      209 000s
         */
        try{
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            map.put('s', 1);
            map.put('m', 60);
            map.put('h', 3600);
            map.put('d', 86400);

            int time = 0;

            StringBuilder tempNumbers = new StringBuilder(new StringBuilder());
            tempNumbers.append("0");

            for (int i = 0 ; i < rawTime.length() ; i++) {
                char c = rawTime.charAt(i);
                if (Character.isDigit(c)){
                    tempNumbers.append(c);
                } else{
                    int multiplier = map.get(c);
                    time += Integer.parseInt(tempNumbers.toString().toString()) * multiplier;
                    tempNumbers = new StringBuilder();
                }
            }

            return Math.max(time, 0);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

}
