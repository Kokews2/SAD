# Aplicació Client/Servidor d'un Xat. Client Textual - Pràctica 2


## Components del Projecte
L'Aplicació de Xat Textual consta dels següents components:
- La implementació de les classes MyServerSocket i MySocket.
- La multiprocesament s'utilitza per al tractament eficient de les connexions dels clients.
- Hem creat la clase MonitorChat per a evitar problemes de concurrència de procèsos.
- La clase MonitorChat inclou un diccionari de parelles.

## Ús
Un cop l'aplicació està en marxa, els clients poden connectar-se al servidor i participar en una comunicació de xat basada en text. El servidor manté un diccionari de parelles per a una difusió eficient dels missatges. 

Un exemple d'execució del servidor seria indicant el port:
```sh
java EchoServer 8080
```

Un exemple d'execució d'un client seria, indicant el host i el port:
```sh
java EchoClient 127.0.0.1 8080
```
