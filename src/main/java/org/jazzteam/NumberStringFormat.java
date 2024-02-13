package org.jazzteam;

import org.jazzteam.utility.Constants;
import org.jazzteam.utility.SegmentsGetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for getting an only number as a string record
 */
public class NumberStringFormat {

    public String createForUnityNumber(long numericalFormat) {
        String stringFormat;
        String[] tmp = SegmentsGetter.get(numericalFormat).get(0).split("");
        List<String> nums = new ArrayList<>(Arrays.asList(tmp));

        if (numericalFormat > 0 && numericalFormat < 10) {
            stringFormat = Constants.UNITS[(int) (numericalFormat - 1)];
        } else if (numericalFormat > 10 && numericalFormat < 20) {
            stringFormat = Constants.NUMBER_FROM_11_TO_19[Integer.parseInt(nums.get(1)) - 1];
        } else if (numericalFormat >= 10 && numericalFormat < 100) {
            if (nums.get(1).equals("0")) {
                stringFormat = Constants.DOZENS[Integer.parseInt(nums.get(0)) - 1];
            } else {
                stringFormat = Constants.DOZENS[Integer.parseInt(nums.get(0)) - 1] + " " + Constants.UNITS[Integer.parseInt(nums.get(1)) - 1];
            }
        } else if (numericalFormat > 99 && numericalFormat < 1000) {
            if (nums.get(1).equals("0") && nums.get(2).equals("0")) {
                stringFormat = Constants.HUNDREDS[Integer.parseInt(nums.get(0)) - 1];
            } else if (nums.get(2).equals("0")) {
                stringFormat = Constants.HUNDREDS[Integer.parseInt(nums.get(0)) - 1] + " " + Constants.DOZENS[Integer.parseInt(nums.get(1)) - 1];
            } else if (nums.get(1).equals("0")) {
                stringFormat = Constants.HUNDREDS[Integer.parseInt(nums.get(0)) - 1] + " " + Constants.UNITS[Integer.parseInt(nums.get(2)) - 1];
            } else if (nums.get(1).equals("1")) {
                stringFormat = Constants.HUNDREDS[Integer.parseInt(nums.get(0)) - 1] + " " + Constants.NUMBER_FROM_11_TO_19[Integer.parseInt(nums.get(2)) - 1];
            } else {
                stringFormat = Constants.HUNDREDS[Integer.parseInt(nums.get(0)) - 1] + " " + Constants.DOZENS[Integer.parseInt(nums.get(1)) - 1] + " " + Constants.UNITS[Integer.parseInt(nums.get(2)) - 1];
            }
        } else {
            stringFormat = "ноль";
        }

        return stringFormat;
    }

    public String createForNumberInThousands(long numericalFormat) {
        String stringFormat = null;
        List<String> nums = SegmentsGetter.get(numericalFormat);

        if (Long.parseLong(nums.get(0)) > 0 && Long.parseLong(nums.get(0)) < 10) {
            stringFormat = Constants.REJECTED_UNITS[(int) (Long.parseLong(nums.get(0)) - 1)];
        } else if (Long.parseLong(nums.get(0)) > 10 && Long.parseLong(nums.get(0)) < 20) {
            String[] tmpNums = nums.get(0).split("");
            stringFormat = Constants.NUMBER_FROM_11_TO_19[Integer.parseInt(tmpNums[1]) - 1];
        } else if (Long.parseLong(nums.get(0)) >= 10 && Long.parseLong(nums.get(0)) < 100) {
            String[] tpmNums = nums.get(0).split("");
            String tmpStringFormat;

            if (tpmNums[1].equals("0")) {
                tmpStringFormat = Constants.DOZENS[Integer.parseInt(tpmNums[0]) - 1];
            } else {
                tmpStringFormat = Constants.DOZENS[Integer.parseInt(tpmNums[0]) - 1] + " " + Constants.REJECTED_UNITS[Integer.parseInt(tpmNums[1]) - 1];
            }

            stringFormat = tmpStringFormat;
        } else if (Long.parseLong(nums.get(0)) > 99 && Long.parseLong(nums.get(0)) < 1000) {
            String[] tpmNums = nums.get(0).split("");
            String tmpStringFormat;

            if (tpmNums[1].equals("0") && tpmNums[2].equals("0")) {
                tmpStringFormat = Constants.HUNDREDS[Integer.parseInt(tpmNums[0]) - 1];
            } else if (tpmNums[1].equals("0")) {
                tmpStringFormat = Constants.HUNDREDS[Integer.parseInt(tpmNums[0]) - 1] + " " + Constants.REJECTED_UNITS[Integer.parseInt(tpmNums[2]) - 1];
            } else if (tpmNums[2].equals("0")) {
                tmpStringFormat = Constants.HUNDREDS[Integer.parseInt(tpmNums[0]) - 1] + " " + Constants.DOZENS[Integer.parseInt(tpmNums[1]) - 1];
            } else if (tpmNums[1].equals("1")) {
                tmpStringFormat = Constants.HUNDREDS[Integer.parseInt(tpmNums[0]) - 1] + " " + Constants.NUMBER_FROM_11_TO_19[Integer.parseInt(tpmNums[2]) - 1];
            } else {
                tmpStringFormat = Constants.HUNDREDS[Integer.parseInt(tpmNums[0]) - 1] + " " + Constants.DOZENS[Integer.parseInt(tpmNums[1]) - 1] + " " + Constants.REJECTED_UNITS[Integer.parseInt(tpmNums[2]) - 1];
            }

            stringFormat = tmpStringFormat;
        }

        return stringFormat;
    }
}
