# Aplicació Client/Servidor d'un Xat Gràfic amb Swing - Pràctica 3

## Descripció del Projecte
L'Aplicació de Xat Gràfic està implementada utilitzant Swing, una interfície gràfica d'usuari (GUI) de Java. L'aplicació proporciona una interfície gràfica amigable per a la comunicació de xat.

## Components del Projecte
L'aplicació consta dels següents components:
- La pantalla es divideix en tres àrees: l'àrea d'entrada, l'àrea de missatges i la llista d'usuaris.
- Els noms d'usuari es guarden en una JList utilitzant un DefaultListModel.

## Ús
Un cop l'aplicació s'engega, els usuaris poden interactuar amb la interfície gràfica per enviar i rebre missatges de xat. Els clients poden triar un nom d'usuari i unir-se al xat. La pantalla principal es divideix en dues seccions, una amb la llista d'usuaris i l'altra amb l'àrea de xat. Els usuaris veuran els altres que estan online un cop enviin el primer missatge.

Un exemple d'execució del servidor seria indicant el port:
```sh
java EchoServer 8080
```

Un exemple d'execució d'un client seria, indicant el host i el port:
```sh
java Xat 127.0.0.1 8080
```
