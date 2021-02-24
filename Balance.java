package devoirINF4063;
/**         La complexit� de l'algorithm est de Teta n2 (ij) , le programme est en mesure de mesurer tous les poids dans la limite du nombre naturel .
 * 
 * Le programme utilise la programmation dynamique pour remplir deux tableaux principaux pour le nombre minimun 
 * (Tableau pour le nb min de poid et un autre tableau pour leur index) et un autre tableau  le nb  de fa�on 
 * de p�ser un Objet avec les poids founis. Puis les resultats sont formater selon le protocol souhait�
 *
 * 
 */

import java.util.Arrays;
import java.util.Scanner;

public class Balance {
  // les attribues, comme les poids max a mesurer est de 100 kg , les tailles des tableaux ont �t� ajust� � 101
  private int minTotPoids[];
  private int indexPoids[];
  private int poidSelect[];
  private int difFaconPeser[];
  private String result;
  private static Scanner lecteur;

  public Balance(int taille) {
    minTotPoids = new int[taille];
    indexPoids = new int[minTotPoids.length];
     poidSelect= new int[minTotPoids.length];
     difFaconPeser = new int[minTotPoids.length];
    
    minTotPoids[0] = 0;
    indexPoids[0] = -1;
    difFaconPeser[0] = 1;
    result = " ";
    for (int i = 1; i < minTotPoids.length; i++) {
      minTotPoids[i] = taille; // le poids d'objet � peser ne depasse pas 100 kg
                            // selon l'�nonc�
      indexPoids[i] = -1;
      difFaconPeser[i] = 0;
    }

  }
// Methode de remplissage des tableaux principaux.
  public void setTabResult(int[] poidFourni) {
    for (int j = 1; j < poidFourni.length; j++) {

      for (int i = poidFourni[j]; i < minTotPoids.length; i++) {
        difFaconPeser[i] += difFaconPeser[i - poidFourni[j]];
        if (minTotPoids[i] >= (1 + minTotPoids[i - poidFourni[j]])) {
          minTotPoids[i] = minTotPoids[i - poidFourni[j]] + 1;
          indexPoids[i] = j;
          

        }
        
      }
    }
  }

  public void poidSelectionner(int[] pObjt, int[] poidFourni) {

    for (int i = 0; i < pObjt.length; i++) {

      if (minTotPoids[pObjt[i]] == minTotPoids.length) {
        System.out.printf("%d - L�objet de poids de %d kg ne peut pas etre pes�\n", i + 1, pObjt[i]);
      } else {
        System.out.printf("%d - L�objet de poids de %d kg peut �tre pes� avec le nombre minimal de %d  poids ", i + 1,
            pObjt[i], minTotPoids[pObjt[i]]);
        
        // Extraction des poids utils�s et leur facteur respectives
        int j = 1;
        poidSelect[0] = 0;
        int diference = 0;
        int[] facteur = new int[minTotPoids.length];
        facteur[0] = 0;
        poidSelect[j] = poidFourni[indexPoids[pObjt[i]]];
        diference = pObjt[i] - poidSelect[j];
        facteur[j] = 1;
        j++;
        while (diference > 0) {
          if (poidFourni[indexPoids[diference]] != poidSelect[j - 1]) {
            poidSelect[j] = poidFourni[indexPoids[diference]];
            diference -= poidSelect[j];
            facteur[j] = 1;
            j++;

          } else {
            facteur[j - 1]++;
            diference -= poidFourni[indexPoids[diference]];

          }

        }
       // Boucle d'impression des resultats en format desir�s

        result += "(" + facteur[1] + " fois " + poidSelect[1] + "kg ";
        for (int k = 2; k <= j; k++) {

          if (facteur[k] != 0) {

            result += " et " + facteur[k ] + " fois " + poidSelect[k] + "kg ";
          } else
            continue;
          
        }
        result += ")";

        // }
        System.out.printf(" %s \n", result);
        result = "";// reinitialisation du resultat pour refleter les infos de
                    // l'objet suivant
        System.out.printf("  - L�objet de poids de %d kg peut �tre pes� de %d fa�ons diff�rentes \n\n", pObjt[i],
            difFaconPeser[pObjt[i]]);
      }
    }

  }

  /**
   * M�thode Main
   * 
   * @param args
   */

  public static void main(String[] args) {
    lecteur = new Scanner(System.in);
    System.out.println("Veuillez saisir le nombre de poids disponible");
      int nWeight = lecteur.nextInt();
    // tableau de poids fournis
    int poiDisp[] = new int[nWeight + 1];
    // Tableau de poids disponible
    System.out.printf("Veuillez saisir les %d poids disponibles\n", nWeight);
    for (int i = 1; i <= nWeight; i++) {

      poiDisp[i] = lecteur.nextInt();
    }

    System.out.println("Veuillez saisir le nombre d\' objet � peser");
    int nObject = lecteur.nextInt();
    System.out.printf("Veuillez saisir les poids de  %d objects\n", nObject);
    // Tableau de poids des objets � peser
    int poidObjet[] = new int[nObject];
    for (int i = 0; i < nObject; i++) {
      poidObjet[i] = lecteur.nextInt();
    }
Arrays.sort(poidObjet);
int poidMax = poidObjet[nObject - 1];
Balance minPoid = new Balance(++poidMax);
    // le nombre de poids minimun utilise et les index de poids correspondant
    minPoid.setTabResult(poiDisp);
    // Selection et affiche les nombre minimale avec le denominateur et maximale
    // de fa�on de p�ser l'Objet.
    minPoid.poidSelectionner(poidObjet, poiDisp);

  }
}
