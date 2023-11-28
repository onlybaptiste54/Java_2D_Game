# Asteroid Game


# Conception

Le code est fait à l'aide du modèle MVC avec le patron de conception Observable.
Toutes les classes extends de Entity étant la classe mère de notre programme
De cette classe, découle des projectiles, des ennemies et le joueur.

# Structure du code

## Package controller
Le package controller regroupe tous les EventHandler qui permettent de définir
les actions du joueur dans le jeu, celles-ci vont modifier les données stockées
dans le modèle.

## Package model

La package model regroupe toutes les classes contenant les données de notre
jeu, ces données sont modifiés par les interactions du Jeu (Game.java) ou
par les interactions du joueur (Package controller).

## Package vue

Le package vue regroupe toutes les classes permettant de mettre sous forme
visuel les données que nous stockons dans notre jeu, toutes les vues correspondantes
à une classe model implements observable.

# Objectif

Accumulez des points en vivant le plus longtemps possible face à des ennemis de plus en plus durs !
Vous pourrez enregistrer votre score à la fin du jeu.
Un classement sera fait à la fin de chaque partie !


## Monstre

Il y a 3 types de monstre :
 - Astéroïde, se déplace linéairement, facile à esquiver ;
 - Missile, suit les mouvements du vaisseau, obligation de le détruire ;
 - Boss, se déplace aléatoirement, envois des projectiles, a plusieurs vie ;

## Objet

Il y a 3 types d'item :
 - Lesser bonus, donne 100 pts au score ;
 - Middle bonus, donne 500 pts au score ;
 - Big bonus, donne 2000 pts au score ;

## Phases de jeu
### Première phase
Vous croiserez des astéroïdes qui se déplacent linéairement, il est facile de les esquiver et 
il n'est pas obligé de tirer dessus.

### Seconde phase
Vous croiserez des missiles envoyés par un vaisseau ennemi, ces missiles sont à tête chercheuse.
Vous devrez les détruire pour y survivre. Déplacement esquive et attaque seront des éléments essentiels
à votre survie

### Troisième phase
Vous croisez enfin l'ennemi qui vous envoyez tous ces missiles. Cet ennemi a plusieurs vies.
Vous devrez le toucher à plusieurs reprises pour en venir à bout.

# Contributeur
Baptiste Beroual

Gregory Dardenne
