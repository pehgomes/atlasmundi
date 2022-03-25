package br.com.atlasmundi.atlasmundi.application.util;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static boolean isValidPhoneNumber(String phoneNumber) {

        if (phoneNumber == null)
            return false;

        if (phoneNumber.startsWith("0"))
            phoneNumber = phoneNumber.substring(1);

        Pattern p = Pattern.compile("\\d{10}|\\d{11}");
        Matcher m = p.matcher(phoneNumber);

        return m.matches();
    }

    public static boolean isValidTaxId(String taxId) {

        if (taxId == null) return false;

        if (taxId.equals("00000000000") || taxId.equals("11111111111")
                || taxId.equals("22222222222")
                || taxId.equals("33333333333")
                || taxId.equals("44444444444")
                || taxId.equals("55555555555")
                || taxId.equals("66666666666")
                || taxId.equals("77777777777")
                || taxId.equals("88888888888")
                || taxId.equals("99999999999")
                || (taxId.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (taxId.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (taxId.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            if ((dig10 == taxId.charAt(9)) && (dig11 == taxId.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    private static int digitsCalculate(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
}
