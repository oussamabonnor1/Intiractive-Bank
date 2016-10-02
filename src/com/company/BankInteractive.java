package com.company;

import java.util.Scanner;

/**
 * Created by oussama and amine on 15/02/2016.
 */
public class BankInteractive {
    Scanner r = new Scanner(System.in);
    public Client[] clients_de_la_bank = new Client[10];
    private int nb_total_client = 0;

    //constructor:==================
    public BankInteractive(int nb_total_client) {
        this.nb_total_client = nb_total_client;
    }

    //setters and getters:==========
    public int getNb_total_client() {
        return nb_total_client;
    }

    public void setNb_total_client(int nb_total_client) {
        this.nb_total_client = nb_total_client;
    }

    //methods:
    //ajouter un client a partir du tableau clients_de_bank***
    public void ajouterClient(String name) {
        setNb_total_client(getNb_total_client() + 1);
        clients_de_la_bank[getNb_total_client()] = new Client(name, 0);
        clients_de_la_bank[getNb_total_client()].comptes_du_client[0] = new Compte(0, 0);
        System.out.println("le client <<" + name + ">> a ete creer, son numero est 00" + getNb_total_client() + "\n");
    }

    public void bilanClient(int numero_du_client) {
        System.out.println("+=====le bilan de Mr." + clients_de_la_bank[numero_du_client].getName() + "=====+");
        for (int i = 1; i <= clients_de_la_bank[numero_du_client].getNb_comptes_total(); i++) {
            System.out.println("---pour le compte n" + i + ":---");
            clients_de_la_bank[numero_du_client].comptes_du_client[i].affichersold();
        }
        System.out.println("le sold total de Mr." + clients_de_la_bank[numero_du_client].getName() + " est " + clients_de_la_bank[numero_du_client].getSold(clients_de_la_bank[numero_du_client].comptes_du_client) + " DA");
        System.out.println("==========fin du bilan==========");
    }

    public void afficherBilan() {
        System.out.println("");
        System.out.println("--------bilan general de la bank--------");
        for (int i = 1; i <= getNb_total_client(); i++) {
            bilanClient(i);
        }
    }

    //menu, interaction et method d utilisation:
    public void interaction() {
        int answer = 0;
        while (answer != 4) {
            System.out.println("+-------------***MENU BANK***--------------+");
            System.out.println("| Quelle opération voulez-vous effectuer ? |");
            System.out.println("| 1) Ajouter un client                     |\n" +
                    "| 2) Effectuer une opération sur un client |\n" +
                    "| 3) Afficher un bilan general             |\n" +
                    "| 4) Quitter                               |\n" +
                    "+------------------------------------------+");
            System.out.print("Entrer le numero de votre choix: ");
            answer = r.nextInt();
            switch (answer) {
                case 1:
                    interactionAjoutClient();
                    break;
                case 2:
                    if (getNb_total_client() == 0) System.out.println("vous devez d'abord ajouter un client");
                    else {
                        interactionOperationClient();
                    }
                    break;
                case 3:
                    if (getNb_total_client() == 0) System.out.println("vous devez d'abord ajouter un client");
                    else {
                        afficherBilan();
                    }
                    break;
                case 4:
                    System.out.println("Merci d'avoir utiliser ce programme, good bye!");
                    break;
            }
        }
    }

    public void interactionAjoutClient() {
        System.out.print("Entrer le nom du client: ");
        ajouterClient(r.next());
    }

    public void interactionOperationClient() {
        int numero;
        int answer = 0;
        System.out.println("Quel client ?");
        for (int i = 1; i <= getNb_total_client(); i++) {
            System.out.println(i + ")- " + clients_de_la_bank[i].getName());
        }
        do {
            System.out.print("Numero du client: ");
            numero = r.nextInt();
            if (numero > getNb_total_client()) System.out.println("***Ce client n'existe pas***");
        }
        while (numero > getNb_total_client());
        while (answer != 9) {
            System.out.println("+----------------------------------------------------------------------------------------------------+\n" +
                    "|Quelle operation voulez-vous effectuer sur le client " + clients_de_la_bank[numero].getName() + " ?");
            System.out.println("| 1) Ajouter un compte                                                                               |\n" +
                    "| 2) Ajouter plusieur comptes                                                                        |\n" +
                    "| 3) Faire un depot                                                                                  |\n" +
                    "| 4) Faire un retrait                                                                                |\n" +
                    "| 5) Faire un virement                                                                               |\n" +
                    "| 6) Afficher le sold d'un compte                                                                    |\n" +
                    "| 7) Afficher un bilan du client                                                                     |\n" +
                    "| 8) Supprimer un compte                                                                             |\n" +
                    "| 9) Retour                                                                                          |\n" +
                    "+----------------------------------------------------------------------------------------------------+");
            System.out.print("Entrer le numero de votre choix: ");
            answer = r.nextInt();

            switch (answer) {
                case 1:
                    clients_de_la_bank[numero].ajoutercompte();
                    break;


                case 2:
                    System.out.println("Comme bien de compte souhaitez-vous ajouter ?");
                    System.out.print("Nombre de compte a ajouter: ");
                    clients_de_la_bank[numero].ajouterplusieurcompte(r.nextInt());
                    break;


                case 3:
                    interactionDepo(numero);
                    break;

                case 4:
                    InteractionRetrait(numero);
                    break;


                case 5:
                    interactionVirement(numero);
                    break;


                case 6:
                    System.out.println("Quel compte? :");
                    for (int i = 1; i <= clients_de_la_bank[numero].getNb_comptes_total(); i++) {
                        System.out.println("Compte n" + i);
                    }
                    Compte c5 = clients_de_la_bank[numero].comptes_du_client[r.nextInt()];
                    clients_de_la_bank[numero].affichersold(c5);
                    break;


                case 7:
                    bilanClient(numero);
                    break;


                case 8:
                    int a = 0;
                    if (clients_de_la_bank[numero].getNb_comptes_total() == 0)
                        System.out.println("creer d'abord un compte!");
                    else {
                        while (a < 1 || a > clients_de_la_bank[numero].getNb_comptes_total()) {
                            System.out.println("Supprimer quel compte? :");
                            for (int i = 1; i <= clients_de_la_bank[numero].getNb_comptes_total(); i++) {
                                System.out.println("Compte n" + i);
                            }
                            a = r.nextInt();
                        }
                        clients_de_la_bank[numero].supprimer_compte(a);
                    }
                    break;


                case 9:

                    break;
            }
        }

    }

    public void InteractionRetrait(int numero) {
        System.out.println("Quel compte? :");
        for (int i = 1; i <= clients_de_la_bank[numero].getNb_comptes_total(); i++) {
            System.out.println("Compte n" + i + " au sold de: " + clients_de_la_bank[numero].comptes_du_client[i].getSold() + " DA");
        }
        int b;
        System.out.print("Le compte: ");
        b = r.nextInt();

        //affectation vers un commpte intermediere***
        Compte c = clients_de_la_bank[numero].comptes_du_client[b];
        if (b > clients_de_la_bank[numero].getNb_comptes_total()) {
            System.out.println("Vous devez creer ce compte d'abord");
        } else {
            float valeur_a_retirer;
            do {
                System.out.print("Entrer la valeur a retirer: ");
                valeur_a_retirer = r.nextFloat();
                if (valeur_a_retirer <= 0) System.out.println("Reesseyer, valeur negative au null");
            }
            while (valeur_a_retirer <= 0);
            if (valeur_a_retirer > c.getSold()) {
                System.out.println("Solde insuffisant, voulez-vous faire un renfflouement? (O/N)");
                String renff_answer = r.next();
                if (renff_answer.equals("o") || renff_answer.equals("O")) {
                    float diff = valeur_a_retirer - c.getSold();
                    float settle = c.getSold();
                    c.setSold(0);
                    boolean test = clients_de_la_bank[numero].renflouer(diff);
                    if (!test) {
                        c.setSold(settle);
                    }
                } else {
                    System.out.println("Retrait annule\n");
                }
            } else {
                c.retrait(valeur_a_retirer);
            }
        }
    }


    public void interactionDepo(int numero) {
        System.out.println("deposer dans quel compte? :");
        for (int i = 1; i <= clients_de_la_bank[numero].getNb_comptes_total(); i++) {
            System.out.println("compte n" + i + " au sold de: " + clients_de_la_bank[numero].comptes_du_client[i].getSold() + " DA");
        }
        int a;
        a = r.nextInt();
        Compte c2 = clients_de_la_bank[numero].comptes_du_client[a];
        if (a > clients_de_la_bank[numero].getNb_comptes_total()) {
            System.out.println("vous devez creer ce compte d'abord\n");
        } else {

            float valeur_a_deposer;
            do {
                System.out.print("entrer la valeur a deposer: ");
                valeur_a_deposer = r.nextFloat();
            }
            while (valeur_a_deposer <= 0);
            c2.depo(valeur_a_deposer);
        }
    }

    public void interactionVirement(int numero) {

        System.out.println("retirer de quel compte? :");
        //premier compte:
        for (int i = 1; i <= clients_de_la_bank[numero].getNb_comptes_total(); i++) {
            System.out.println("compte n" + i + " au sold de: " + clients_de_la_bank[numero].comptes_du_client[i].getSold() + " DA");
        }
        int a = r.nextInt();
        Compte c3 = clients_de_la_bank[numero].comptes_du_client[a];
        if (a > clients_de_la_bank[numero].getNb_comptes_total()) {
            System.out.println("vous devez creer ce compte d'abord\n");
        } else {
            //deuxieme compte:
            System.out.println("envoyer vers quel compte? :");
            for (int i = 1; i <= clients_de_la_bank[numero].getNb_comptes_total(); i++) {
                System.out.println("compte n" + i + " au sold de: " + clients_de_la_bank[numero].comptes_du_client[i].getSold() + " DA");
            }
            int b = r.nextInt();
            Compte c4 = clients_de_la_bank[numero].comptes_du_client[b];
            if (b == a) System.out.println("vous ne pouvez pas transferer de l'argent vers le meme compte!\n");
            else if (b > clients_de_la_bank[numero].getNb_comptes_total()) {
                System.out.println("vous devez creer un compte d'abord\n");
            } else {
                System.out.print("entrer la valeur a deposer: ");
                c3.virer(r.nextFloat(), c4);
            }
        }
    }
}