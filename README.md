# Projet-S4-Java
###################################         A LIRE AVANT TOUTE INSTALLATION           ######################################

Il est possible de rencontrer une erreur lorsqu'on cherche a exécuter les .jar sur son PC. Elle est due à une version obsolète du JDK (inférieure à 21).
Pour résoudre ce problème, il faut installer la version la plus récente de Java pour les développeurs :

Pour Windows
Étape 1 : Télécharger le JDK

Accédez à la page de téléchargement officielle d’Oracle : recherchez “Oracle JDK Downloads”.

Sélectionnez le fichier d’installation correspondant à votre système (généralement “Windows x64 Installer” au format .exe ou .msi).

Étape 2 : Installer le JDK

Double-cliquez sur le fichier téléchargé.

Suivez les instructions de l’assistant d’installation :

Cliquez sur “Next” (Suivant).

Choisissez le dossier d’installation (par défaut : C:\Program Files\Java\jdk-[version]).

Cliquez sur “Install”.

Étape 3 : Configurer la variable d’environnement JAVA_HOME

Ouvrez les “Paramètres système avancés” (clic droit sur le menu Démarrer > Système > Paramètres système avancés).

Cliquez sur “Variables d’environnement”.

Dans “Variables système”, cliquez sur “Nouveau…”.

Nom de la variable : JAVA_HOME

Valeur de la variable : chemin du dossier d’installation du JDK (exemple : C:\Program Files\Java\jdk-23)

Validez par “OK”.

Étape 4 : Vérifier l’installation

Ouvrez une invite de commande et tapez : 'java -version' ou 'javac -version'
Vous devez voir s’afficher la version installée du JDK.


###########################################################################################################



Applications Java à but pédagogique illustrant des notions clés du cours de L1

Le code source ainsi que les .jar exécutables sont disponibles sur ce repository

Initialisation tableaux : illustration de l'initialisation de tableaux de types int, double, String, char et boolean suivant les deux méthodes les plus répandues, avce la possibilité de modifier les paramètres du tableau et son contenu

Chaînes de caractères : illustration des fonctions length(), charAt(), substring(), indexOf() et toUpperCase() sur une chaîne de caractères quelconque avec la possibilité de personnaliser des paramètres de chaque fonction

Boucles : illustration d'une exécution par étapes de boucle for sur un print avec la possibilité de changer les paramètres du for

Car Cdr : illustration du princnipe de Car et Cdr en Scheme. Attention, cette version est un prototype en bonus pour illustrer les perspectives du projet
