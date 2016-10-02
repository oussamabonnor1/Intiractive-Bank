package com.company;

/**
 * Created by oussama and amine on 15/02/2016.
 */
public class Compte {
    private int numbre_compte;
    private float sold;
    //constrictor***
    public Compte(int numbercompte, float sold) {
        this.numbre_compte = numbercompte;
        this.sold = sold;
    }

    //getters and setters for variables***
    public int getNumbre_compte() {
        return numbre_compte;
    }

    public float getSold() {
        return sold;
    }

    public void setSold(float sold) {
        this.sold = sold;
    }

    //methods***
    public void depo(float valeur_a_deposer) {
        setSold(getSold() + valeur_a_deposer);
        System.out.println("votre sold a bien ete augmenter, nouveau sold: " + getSold() + " DA\n");
    }


    public void retrait(float valeur_a_deposer) {
        if (valeur_a_deposer > getSold()) {
            System.out.println("votre sold est inssufissant");
        } else {
            setSold(getSold() - valeur_a_deposer);
            System.out.println("votre sold a bien ete retirer, nouveau sold: " + getSold() + " DA\n");
        }
    }

    public void affichersold() {
        System.out.println("le solde est: " + getSold() + " DA\n");
    }

    void virer(float valeur, Compte destinaire) {
        if (valeur > sold) System.out.println("le sold du compte " + getNumbre_compte() + " est insufisant\n");
        else {
            setSold(getSold() - valeur);
            destinaire.setSold(destinaire.getSold() + valeur);
            System.out.println("le virement a bien ete effectuer:");
            System.out.println("le solde du compte donnant: " + getSold() + " DA");
            System.out.println("le solde du compte recevant: " + destinaire.getSold() + " DA\n");
        }
    }
}
