package org.jazzteam;

import org.jazzteam.utility.Constants;
import org.jazzteam.utility.SegmentsGetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for getting a number with declination as a string record
 */
public class FullNumberStringFormat {

    public String create(long number) {
        StringBuilder stringFormat = new StringBuilder("");
        List<String> segments = SegmentsGetter.get(number);
        List<String> tmpSegments = new ArrayList<>();

        if (number < 0) {
            number = Math.abs(number);
            stringFormat.append("минус ");
        }

        if (segments.size() == 1) {
            stringFormat.append(new NumberStringFormat().createForUnityNumber(number));
        } else {
            int countSegments = segments.size();

            for (int i = 0; i < countSegments; i++) {
                if (!segments.get(i).equals("")) {
                    if (i != countSegments - 2) {
                        tmpSegments.add(new NumberStringFormat().createForUnityNumber(Long.parseLong(segments.get(i))));
                    } else {
                        tmpSegments.add(new NumberStringFormat().createForNumberInThousands(Long.parseLong(segments.get(i))));
                    }
                } else {
                    tmpSegments.add("");
                }
            }
        }

        List<String> declinations = getAsList(tmpSegments);
        for (int i = 0; i < declinations.size(); i++) {
            if (declinations.get(i).equals("")) {
                declinations.remove(i);
            } else {

                stringFormat.append(declinations.get(i) + " ");
            }
        }

        return stringFormat.toString().trim();
    }

    private List<String> getAsList(List<String> stringSegments) {
        String stringToFindEndings;
        String lastLetter;
        List<String> declinations = new ArrayList<>();

        for (int i = 0; i < stringSegments.size(); i++) {
            String lastTwoLetters = null;

            if (!stringSegments.get(i).equals("")) {
                stringToFindEndings = stringSegments.get(i).replaceAll(" ", "");
                lastLetter = String.valueOf(stringToFindEndings.charAt(stringToFindEndings.length() - 1));
                lastTwoLetters = stringToFindEndings.charAt(stringToFindEndings.length() - 2) + lastLetter;
            }

            if (i != stringSegments.size() - 2) {
                declinations.add(stringSegments.get(i));

                if (i == stringSegments.size() - 1) {
                    declinations.add("");
                } else {
                    declinations.add(getDeclinationForUnits(lastTwoLetters, stringSegments.size(), i));
                }
            } else {
                declinations.add(stringSegments.get(i));
                declinations.add(getDeclinationForThousands(lastTwoLetters));
            }
        }

        return declinations;
    }

    private String getDeclinationForThousands(String lastTwoLetters) {
        String declinationForThousands;
        String lastLetter = null;

        if (lastTwoLetters != null) {
            lastLetter = String.valueOf(lastTwoLetters.charAt(lastTwoLetters.length() - 1));
        }

        if (lastLetter != null) {
            if (lastLetter.equals("а") && !lastTwoLetters.equals("та")) {
                declinationForThousands = Constants.DECLINATIONS[1][0];
            } else if (lastLetter.equals("ь") || lastTwoLetters.equals("ти") || lastTwoLetters.equals("та") || lastTwoLetters.equals("то") || lastLetter.equals("к") || lastLetter.equals("т") || lastLetter.equals("о")) {
                declinationForThousands = Constants.DECLINATIONS[1][2];
            } else {
                declinationForThousands = Constants.DECLINATIONS[1][1];
            }
        } else {
            declinationForThousands = "";
        }

        return declinationForThousands;
    }

    private String getDeclinationForUnits(String lastTwoLetters, int sizeOfSegments, int indexOfSegment) {
        String declinationForUnits;
        String lastLetter = null;

        if (lastTwoLetters != null) {
            lastLetter = String.valueOf(lastTwoLetters.charAt(lastTwoLetters.length() - 1));
        }

        if (lastLetter != null) {
            if (lastLetter.equals("н")) {
                declinationForUnits = Constants.DECLINATIONS[sizeOfSegments - indexOfSegment - 1][0];
            } else if (lastLetter.equals("и") && !lastTwoLetters.equals("ти") || lastLetter.equals("а") && !lastTwoLetters.equals("та") || lastLetter.equals("е")) {
                declinationForUnits = Constants.DECLINATIONS[sizeOfSegments - indexOfSegment - 1][1];
            } else {
                declinationForUnits = Constants.DECLINATIONS[sizeOfSegments - indexOfSegment - 1][2];
            }
        } else {
            declinationForUnits = "";
        }

        return declinationForUnits;
    }
}
