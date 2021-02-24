package devoirINF4063;

import java.util.Stack;
import java.util.Scanner;

/**
 * @author Feisal Aden Darar Le programme permet de convertir un langage source
 *         au programme en code objet
 */
public class petitCompilateur {
  /**
   * 
   * Cette methode permet d'evaluer la priorit� des operateurs
   * 
   * @param operateur
   * @return la priorit�
   */
  public static int priorite(char op) {
    if (op == '=')
      return 5;
    else if (op == '+' || op == '-')
      return 10;
    else if (op == '*' || op == '/')
      return 20;
    else if (op == '^')
      return 30;
    else
      return 0;

  }

  /**
   * Methode permet de convertir les operateur en une expression code objet
   * 
   * @param operateur
   * @return une String
   */
  public static String operateur(char op) {
    if (op == '=')
      return "AFF";
    else if (op == '+')
      return "ADD";
    else if (op == '-')
      return "SOU";
    else if (op == '*')
      return "MUL";
    else if (op == '/')
      return "DIV";
    else if (op == '^')
      return "EXP";
    else
      return "";

  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Stack<Character> pile = new Stack<Character>();

    Scanner lecteur = new Scanner(System.in);
    System.out.println("Veuillez saisir les expressions en separant par un point virgule");

    String textSource = lecteur.nextLine();

    String[] expression = textSource.split(";");

    /**
     * La section suivante permet de determiner les experssion postfix�e
     * 
     */
    int n = 1;
    for (String infixee : expression) {
      String postFixee = "";
      System.out.println("L'expresion " + n + " en programme code objet est:");
      char[] element = infixee.toCharArray();
      for (int i = 0; i < element.length; i++) {
        if (Character.isLetter(element[i])) {
          postFixee += element[i];

        }
        if (element[i] == '(') {
          pile.push(element[i]);

        } else if (element[i] == '+' || element[i] == '-' || element[i] == '*' || element[i] == '/' || element[i] == '='
            || element[i] == '^') {

          while (!pile.empty() && pile.peek() != '(' && priorite(element[i]) <= priorite(pile.peek())) {

            postFixee += pile.pop();
          }
          pile.push(element[i]);

        } else if (element[i] == ')') {
          while (pile.peek() != '(') {
            postFixee += pile.pop();
          }
          pile.pop();
        }

      }
      while (!pile.empty()) {
        postFixee += pile.pop();

      }
      // System.out.println(postFixee);

      /**
       * Cette section permet de convertir le postfixee en programe code objet
       * Etant donn� que les op�rande sont en alphabet majuscule le registre est
       * une letter miniscule
       */
      String reg = "r";
      int nbReg = 0;
      for (int j = 0; j < postFixee.length(); j++) {
        if (Character.isLetter(postFixee.charAt(j)))
          pile.push(postFixee.charAt(j));
        else {
          if (!pile.empty()) {
            String operand = "";

            operand += pile.pop();
            operand += pile.pop();
            if (postFixee.charAt(j) != '=') {

              if ((operand.charAt(1) != reg.charAt(0)) && (operand.charAt(0) != reg.charAt(0))) {
                System.out.println(operateur(postFixee.charAt(j)) + "  " + reg.toUpperCase() + nbReg + " ,"
                    + operand.charAt(1) + " , " + operand.charAt(0));
                pile.push(reg.charAt(0));
                nbReg++;

              } else if ((operand.charAt(0) == reg.charAt(0)) ^ (operand.charAt(1) == reg.charAt(0))) {
                nbReg--;
                if (operand.charAt(0) == reg.charAt(0)) {

                  System.out.println(operateur(postFixee.charAt(j)) + "  " + reg.toUpperCase() + nbReg + " , "
                      + operand.charAt(1) + " ," + operand.toUpperCase().charAt(0) + nbReg);
                  pile.push(reg.charAt(0));
                  nbReg++;

                } else if (operand.charAt(1) == reg.charAt(0)) {

                  System.out.println(operateur(postFixee.charAt(j)) + "  " + reg.toUpperCase() + nbReg + " , "
                      + operand.toUpperCase().charAt(1) + nbReg + " ," + operand.charAt(0));
                  pile.push(reg.charAt(0));
                  nbReg++;

                }
              } else if ((operand.charAt(1) == reg.charAt(0)) && (operand.charAt(0) == reg.charAt(0))) {
                nbReg--;

                System.out.println(operateur(postFixee.charAt(j)) + "  " + reg.toUpperCase() + --nbReg + " ,"
                    + operand.toUpperCase().charAt(0) + nbReg + " , " + operand.toUpperCase().charAt(0) + ++nbReg);
                pile.push(reg.charAt(0));

              }

            } else {

              nbReg--;

              System.out.println(operateur(postFixee.charAt(j)) + "  " + operand.charAt(1) + " , "
                  + operand.toUpperCase().charAt(0) + nbReg);

            }
          }
        }
      }
      n++;
     
    }
    lecteur.close();

  }

}
