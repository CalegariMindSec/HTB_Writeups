# Armageddon WriteUp

## Scan and Enumeration

#### Comando: sudo nmap -sV -Pn -v 10.129.166.24 -oN scan.txt

```
# Nmap 7.92 scan initiated Tue Dec 28 11:06:34 2021 as: nmap -sV -Pn -v -oN scan.txt 10.129.166.24
Nmap scan report for 10.129.166.24
Host is up (0.16s latency).
Not shown: 998 closed tcp ports (reset)
PORT   STATE SERVICE VERSION
22/tcp open  ssh     OpenSSH 7.4 (protocol 2.0)
80/tcp open  http    Apache httpd 2.4.6 ((CentOS) PHP/5.4.16)

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Tue Dec 28 11:06:45 2021 -- 1 IP address (1 host up) scanned in 10.42 seconds
```

## Exploitation

Tendo visto as tecnologias, podemos perceber que o site usa Drupal na versão 7. Esse será nosso vetor de ataque. Para mais informações --> https://github.com/dreadlocked/Drupalgeddon2

#### Comando: ruby drupalgeddon2.rb http://10.129.166.24

Após executar o exploit e ter a shell, podemos ver o conteúdo do arquivo ```/etc/passwd```, Assim, conseguimos a informação da existência do usuário ```brucetherealadmin```.

Tendo o usuário, podemos usar a wordlist rockyou para fazer bruteforce de senha no ssh. Com o bruteforce, conseguimos a senha ```booboo```.

* User Flag:
Logar no ssh e pegar a flag no home do user

```
cat user.txt
6e264a89ae4c9100175eedd5e443d95c
```

## Privilege Escalation

Após ter pego a flag de user, podemos ver o vetor para privesc usando o "sudo -l". Usando o sudo podemos ver que o vetor é o snap install. Para mais informações para privilege escalation desse tipo --> https://notes.vulndev.io/notes/redteam/privilege-escalation/misc-1

Após criar o payload e instalar com ```sudo /usr/bin/snap install payload.snap --dangerous --devmode``` podemos trocar para o user dirty_sock e depois usar o sudo -i para virar root.

* Root Flag:

```
cat root.txt
5002f40ea8ff07c9ec9f7b1646607deb
```
