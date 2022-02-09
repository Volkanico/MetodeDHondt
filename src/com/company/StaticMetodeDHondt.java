package com.company;

public class StaticMetodeDHondt {

    private static int esconsPerAssignar;           /* escons totals a repartir */

    private static int[] votsPartits;               /* vots aconseguits per cada partit que participa */
    private static String[] nomsPartits;            /* noms de cada partit que participa */

    private static float percentatgeMinim = 0.03F;          /* numero per realitzar el % per sebre el minim
                                                            per si entra a la reparticio d'escons */
    private static float[][] quoficientsHondt;

    private static boolean[] teVotsMinims;         /* compleix amb mes del 3% dels vots totals si/no */

    private static int[] esconsAssignats;           /* escons que ha aconseguit cada partit despres de repartir */

    public static int[] initHondt (
            int escons,

            String[] partits,

            int[] votsAconseguits,

            int percentatgeEliminacioPartits
    ) {
        teVotsMinims = new boolean[partits.length];

        esconsPerAssignar = escons;

        nomsPartits = partits;

        votsPartits = votsAconseguits;

        percentatgeMinim = (float) percentatgeEliminacioPartits / 100;

        votsTotals();
        determinarPartitsAmbVotsMinims();

        quoficientsHondt = new float[votsPartits.length][escons];

        construirTaulaHondt();

        esconsAssignats = new int[escons];
        calculHondt();

        return esconsAssignats;
    }

    public static  void imprimirResultats() {

        System.out.println();
        System.out.println("El recompte de vots totals es de: " + votsTotals());
        System.out.println();
        System.out.println("Numero de vots de cada partit: ");
        System.out.println();

            for (int i = 0; i < votsPartits.length; i++) {
                System.out.println("El " + nomsPartits[i] + ": " + votsPartits[i]);
        }
        System.out.println();
        System.out.println("Nomes participen els partits que tenguin mes del 3% del total dels vots.");
        System.out.println();

        int partitsQueTenenVotsMinims = 0;
        for(int k = 0; k < teVotsMinims.length; k++) {
        if (teVotsMinims[k] == true) {
            partitsQueTenenVotsMinims++; }
        }
        System.out.println("El numero de Partits que participen son: " + partitsQueTenenVotsMinims);
        System.out.println();
    }

    public static void imprimirTaulaQuoficients() {

        for (int i = 0; i < quoficientsHondt.length; i++) {
            for (int j = 0; j < quoficientsHondt[i].length; j++) {
                    System.out.print(" | ");
                    System.out.print(quoficientsHondt[i][j]);
                    System.out.print(" | ");
            }
            System.out.println();
        }

        System.out.println();
        for (int x = 0; x < nomsPartits.length; x++) {
            System.out.println("El " + nomsPartits[x] + " ha aconseguit " + esconsAssignats[x] + " escons");
        }
        System.out.println();

        for (int p = 0; p < teVotsMinims.length; p++) {

            System.out.println("El " + nomsPartits[p] + ", si es true, ha superat els vots minims, si es false, no:  " + teVotsMinims[p] );
        }
        System.out.println();
    }

    public static void calculHondt() {

        for (int i = 0; i < esconsPerAssignar; i++) {

            int auxIndex = 0;
            float numMesGran = 0;

            for ( int j = 0; j < quoficientsHondt.length; j++) {
                if (! teVotsMinims[j] ){
                    continue;
                }
                if ( quoficientsHondt[j][esconsAssignats[j]] > numMesGran) {
                    numMesGran = quoficientsHondt[j][esconsAssignats[j]];
                    auxIndex = j;
                }
            }
            esconsAssignats[auxIndex]++;
        }
    }

    public static void determinarPartitsAmbVotsMinims() {

        for (int i = 0; i < votsPartits.length; i++) {

            teVotsMinims[i] = (votsPartits[i] > votsTotals() * percentatgeMinim) ;

        }
    }

    public static int votsTotals() {
        int total = 0 ;

        for ( int i = 0; i < votsPartits.length; i++) {
            total = total + votsPartits[i];
        }
        return total;
    }

    public static void construirTaulaHondt() {

        for (int i = 0; i < quoficientsHondt.length ; i++) {
            for (int j = 0; j <  quoficientsHondt[i].length ; j++) {

                quoficientsHondt[i][j] = votsPartits[i] / (j + 1);
            }
        }
    }
}










