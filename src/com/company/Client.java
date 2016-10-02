package com.company;

import java.util.Scanner;

/**
 * Created by oussama and amine on 15/02/2016.
 */
public class Client {
    Scanner r = new Scanner(System.in);
    private String name;
    private int nb_comptes_total = 0;
    //tableau qui contient tout les comptes d un client
    public Compte[] comptes_du_client = new Compte[100];

    //constrictor***
    public Client(String name, int nbcomptestotal) {
        this.name = name;
        this.nb_comptes_total = nbcomptestotal;
    }

    //setters and getters for variables***
    public String getName() {
        return name;
    }

    public float getSold(Compte[] comptes_du_client) {
        float somme = 0;
        for (int i = 1; i <= getNb_comptes_total(); i++) {
            somme = somme + comptes_du_client[i].getSold();
        }
        return somme;
    }

    public int getNb_comptes_total() {
        return nb_comptes_total;
    }

    public void setNb_comptes_total(int nb_comptes_total) {
        this.nb_comptes_total = nb_comptes_total;
    }

    //methods***
    public void affichersold(Compte compte) {
        System.out.println("le solde du compte au numero " + compte.getNumbre_compte() + " est: " + compte.getSold() + " DA\n");
    }

    public void ajoutercompte() {
        setNb_comptes_total(getNb_comptes_total() + 1);
        comptes_du_client[nb_comptes_total] = new Compte((nb_comptes_total), 0);
        System.out.println("Mr." + getName() + " votre compte a bien ete creer");
        System.out.println("votre compte a le numero: " + (getNb_comptes_total()) + " contien: " + comptes_du_client[getNb_comptes_total()].getSold() + " DA\n");
    }

    public void ajouterplusieurcompte(int nbcomptes) {
        for (int i = getNb_comptes_total() + 1; i <= (getNb_comptes_total() + nbcomptes); i++) {
            comptes_du_client[i] = new Compte(i, 0);
            System.out.println("le compte n " + i + " a bien ete creer");
            System.out.print("entrer la valeur a deposer: ");
            comptes_du_client[i].setSold(r.nextFloat());
            System.out.println("le compte au numero: " + i + " a le sold: " + comptes_du_client[i].getSold() + " DA\n");
        }
        setNb_comptes_total(getNb_comptes_total() + nbcomptes);
    }

    public void supprimer_compte(int numbre_compte) {
        //si le compte est unique, la suppression est decliner***
        if (getNb_comptes_total() == 1)
            System.out.println("nous vous conseillons de ne pas supprimer votre unique compte\n");

        else {
            //s'il sagit du dernier compte de la liste, on supprime le dernier compte et son sold est ajouter au sold original du compte precedent***
            if (numbre_compte == getNb_comptes_total()) {
                comptes_du_client[getNb_comptes_total() - 1].setSold(comptes_du_client[getNb_comptes_total() - 1].getSold() + comptes_du_client[getNb_comptes_total()].getSold());
                comptes_du_client[getNb_comptes_total()] = null;
                setNb_comptes_total(getNb_comptes_total() - 1);
                System.out.println("le compte " + numbre_compte + " a bien ete suprimmer!\nSon sold est maintenant attribue au compte " + (numbre_compte - 1) + "\n");
            }

            //s'il sagit du premier compte alors son sold est affecter au compte n 2 (yaura tjr un compte n 2 car il ya un control avant)***
            else if (numbre_compte == 1) {
                comptes_du_client[2].setSold(comptes_du_client[2].getSold() + comptes_du_client[1].getSold());
                for (int i = numbre_compte; i < getNb_comptes_total(); i++) {
                    comptes_du_client[i] = comptes_du_client[i + 1];
                }
                comptes_du_client[getNb_comptes_total()] = null;
                setNb_comptes_total(getNb_comptes_total() - 1);
                System.out.println("le compte " + 1 + " a bien ete suprimmer!\nSon sold est maintenant attribue au compte " + 2 + "\n");

            } else {
                //control de valuer***
                if (numbre_compte <= 0 || numbre_compte > getNb_comptes_total())
                    System.out.println("Ce compte n'existe pas\n");

                else {
                    comptes_du_client[numbre_compte - 1].setSold(comptes_du_client[numbre_compte - 1].getSold() + comptes_du_client[numbre_compte].getSold());
                    for (int i = numbre_compte; i < getNb_comptes_total(); i++) {
                        comptes_du_client[i] = comptes_du_client[i + 1];
                    }
                    comptes_du_client[getNb_comptes_total()] = null;
                    setNb_comptes_total(getNb_comptes_total() - 1);
                    System.out.println("le compte " + numbre_compte + " a bien ete suprimmer!\nSon sold est maintenant attribue au compte " + (numbre_compte - 1) + "\n");
                }
            }
        }
    }


    //renflouement se fait si necessaire***
    public boolean renflouer(float diff) {
        //si val est vrai, alr la method a fonctionner et le sold du compte qui a subis le renflouement va maintenant devenir 0
        //si val est fausse alr le compte va retrouver son ancient sold et l utilisateur va retourner vers le menu***
        boolean val = true;
        int i = 1;
        //condition 1: continuer a incrementer le compteur tanque le compte acctuel n a pas de sold suffisent pour renflouer***
        //condition 2: le compteur doit etre tjr inferieur au nombre des comptes total du client
        // afin d eviter la boucle qui tombe vers 100 (valeur max des comptes possible)
        while (comptes_du_client[i].getSold() < diff && i < getNb_comptes_total()) {
            i++;
        }
        if (i > getNb_comptes_total()) {
            System.out.println("les soldes de vos comptes sont insuffisant");
            val = false;
        } else {
            comptes_du_client[i].setSold(comptes_du_client[i].getSold() - diff);
            System.out.println("le renflouement a ete effectuer, la valeur renflait est: " + diff + " par le compte: " + i);
            affichersold(comptes_du_client[i]);
        }
        return val;
    }
}
