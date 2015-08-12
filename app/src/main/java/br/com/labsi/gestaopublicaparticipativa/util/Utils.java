package br.com.labsi.gestaopublicaparticipativa.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.util.Log;

/**
 * Created by Marcelo on 06/08/2015.
 */
public class Utils {
    private static  Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }

    public String getEmail(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);

        if (account == null) {
            return null;
        } else {
            return account.name;
        }
    }

    public String getUsername(Context context) {
        // String email;
        AccountManager manager = AccountManager.get(context);
        Account account = getAccount(manager);
        if (account == null) {
            return "";
        } else {
            String email = account.name;
            String[] parts = email.split("@");
            if (parts.length > 0 && parts[0] != null)
                return parts[0];
            else
                return "";
        }
    }
}