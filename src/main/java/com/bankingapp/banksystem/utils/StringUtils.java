package com.bankingapp.banksystem.utils;

public class StringUtils {

    public static String changeAccountType(String accountType) {
        return accountType.equals("primary") ? "savings" : "primary";
    }
}
