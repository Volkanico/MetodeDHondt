package com.company;

public class Main {

    public static void main(String[] args) {

        String[] nomsPartits =  {"Partit A", "Partit B", "Partit C", "Partit D", "Partit E"};
        int[] votsAconseguits = {120000, 100000, 40000, 5000, 2500};

        StaticMetodeDHondt.initHondt( 8, nomsPartits, votsAconseguits, 3);

        StaticMetodeDHondt.imprimirResultats();
        StaticMetodeDHondt.imprimirTaulaQuoficients();
    }

}


