package org.jazzteam.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for splitting a number into segments
 */
public class SegmentsGetter {

    public static List<String> get(long number) {
        List<String> segments = new ArrayList<>();

        if (number < 0) {
            number = Math.abs(number);
        }

        if (number > 0 && number < 1000) {
            segments.add(Long.toString(number));
        } else {
            String numberAsString = Long.toString(number);

            StringBuilder sb = new StringBuilder(numberAsString);

            for (int i = sb.length(); i >= 0; i = i - 3) {
                sb.insert(i, " ");
            }

            numberAsString = sb.toString().trim();

            List<String> tmpSegments = new ArrayList<>(Arrays.asList(numberAsString.split(" ")));

            for (String s : tmpSegments) {
                int count = 0;

                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '0') {
                        count++;
                    } else {
                        break;
                    }
                }

                segments.add(s.substring(count));
            }
        }

        return segments;
    }
}
