# Jerry WriteUp

# Scan e Enum

```
# Nmap 7.92 scan initiated Mon Dec 27 09:47:34 2021 as: nmap -sV -Pn -v -oN scan.txt 10.129.1.110
Nmap scan report for 10.129.1.110
Host is up (0.16s latency).
Not shown: 999 filtered tcp ports (no-response)
PORT     STATE SERVICE VERSION
8080/tcp open  http    Apache Tomcat/Coyote JSP engine 1.1

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Mon Dec 27 09:47:54 2021 -- 1 IP address (1 host up) scanned in 20.36 seconds
```
Após abrir a aplicação no browser e entrar no manager, conseguimos ver as credenciais default para fazer login.

Tendo feito login podemos fazer upload de um arquivo war com shell reversa.

* User and Root Flag's:

```
cd C:\Users\Administrator\Desktop\flags
type "2 for the price of 1.txt"
user.txt
7004dbcef0f854e0fb401875f26ebd00

root.txt
04a8b36e1545a455393d067e772fe90e
```
