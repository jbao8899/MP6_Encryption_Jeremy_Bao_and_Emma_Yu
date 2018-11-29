package com.example.jbao8.encryptionmp6;

public class BooleansForSettings {
    private static boolean toCapitalizeAfterRotation = false;
    private static String toAppend = "";
    private static String wordToAppendAfter = "";
    private static boolean appendOrNot = false;
    private static String toModify = "";
    private static String output = "";
    private static int shiftBy = 0;
    private static boolean rotateOrNot = false;

    public static boolean isRotateOrNot() {
        return rotateOrNot;
    }

    public static void setRotateOrNot(boolean rotateOrNot) {
        BooleansForSettings.rotateOrNot = rotateOrNot;
    }

    public static String getOutput() {
        return output;
    }


    public static int getShiftBy() {
        return shiftBy;
    }

    public static void setShiftBy(int shiftBy) {
        BooleansForSettings.shiftBy = shiftBy;
    }

    public static void setOutput(String output) {
        BooleansForSettings.output = output;
    }

    public static String getToModify() {
        return toModify;
    }

    public static void setToModify(String toModify) {
        BooleansForSettings.toModify = toModify;
    }

    public static String getWordToAppendAfter() {
        return wordToAppendAfter;
    }

    public static void setWordToAppendAfter(String wordToAppendAfter) {
        BooleansForSettings.wordToAppendAfter = wordToAppendAfter;
    }

    public static boolean isAppendOrNot() {
        return appendOrNot;
    }

    public static void setAppendOrNot(boolean appendOrNot) {
        BooleansForSettings.appendOrNot = appendOrNot;
    }

    public static boolean isToCapitalizeAfterRotation() {
        return toCapitalizeAfterRotation;
    }

    public static void setToCapitalizeAfterRotation(boolean toCapitalizeAfterRotation) {
        BooleansForSettings.toCapitalizeAfterRotation = toCapitalizeAfterRotation;
    }

    public static String getToAppend() {
        return toAppend;
    }

    public static void setToAppend(String toAppend) {
        BooleansForSettings.toAppend = toAppend;
    }
}
