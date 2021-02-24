# JavaProgram
Java programing projects
Avec des poids et des objets à peser prédéfinis,  le programme permet de déterminer le nombre minimal de poids nécessaire ainsi que tous les combinaison des poids possible pour peser chaque objets.
Le code petitCompilateur.java est un programme qui convertie des expressions d'un langage source en langage du code objet. Le programme en langage source contiendra une suite d’instructions séparées par un symbole de point-virgule. Les variables sont dénotées par les identificateurs à une lettre majuscule A, B,. .., Z. Chaque expression du programme code objet est écrite sur une ligne et elle doit être suivie par ses arguments séparés par des virgules. Les arguments sont les variables du langage source et les registres de votre ordinateur, représentés par les symboles R0, R1, . . ., R9.
Example :
C = D*C^E*F+D+(C+E); N= C+D^C
Résultats:
L'expresion 1 en programme code objet est:
EXP  R0 ,C , E
MUL  R0 , D ,R0
MUL  R0 , R0 ,F
ADD  R0 , R0 ,D
ADD  R1 ,C , E
ADD  R0 ,R0 , R1
AFF  C , R0
L'expresion 2 en programme code objet est:
EXP  R0 ,D , C
ADD  R0 , C ,R0
AFF  N , R0
