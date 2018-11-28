package com.example.jbao8.encryptionmp6;

public class BooleansForSettings {
    private static boolean toCapitalizeAfterRotation = false;
    private static String toAppend = "";
    private static String whenToAppend = "";
    private static boolean appendOrNot = false;
    private static String toModify = "";
    private static String output = "";
    public static String getOutput() {
        return output;
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

    public static String getWhenToAppend() {
        return whenToAppend;
    }

    public static void setWhenToAppend(String whenToAppend) {
        BooleansForSettings.whenToAppend = whenToAppend;
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
