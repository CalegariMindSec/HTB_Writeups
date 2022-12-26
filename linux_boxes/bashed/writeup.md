# Writeup bashed

## Scan

```bash
# Nmap 7.92 scan initiated Sun Mar 20 18:45:33 2022 as: nmap -sS -Pn -p- -v -T5 -oN scan.txt 10.129.140.158
Warning: 10.129.140.158 giving up on port because retransmission cap hit (2).
Nmap scan report for 10.129.140.158
Host is up (0.15s latency).
Not shown: 65534 closed tcp ports (reset)
PORT   STATE SERVICE
80/tcp open  http

Read data files from: /usr/bin/../share/nmap
# Nmap done at Sun Mar 20 18:53:09 2022 -- 1 IP address (1 host up) scanned in 455.79 seconds
```

## Enumeration

**Diretory enum**

```bash
└─$ ffuf -u http://10.129.140.158/FUZZ -w /usr/share/seclists/Discovery/Web-Content/raft-large-directories.txt -t 100

        /'___\  /'___\           /'___\       
       /\ \__/ /\ \__/  __  __  /\ \__/       
       \ \ ,__\\ \ ,__\/\ \/\ \ \ \ ,__\      
        \ \ \_/ \ \ \_/\ \ \_\ \ \ \ \_/      
         \ \_\   \ \_\  \ \____/  \ \_\       
          \/_/    \/_/   \/___/    \/_/       

       v1.3.1 Kali Exclusive <3
________________________________________________

 :: Method           : GET
 :: URL              : http://10.129.140.158/FUZZ
 :: Wordlist         : FUZZ: /usr/share/seclists/Discovery/Web-Content/raft-large-directories.txt
 :: Follow redirects : false
 :: Calibration      : false
 :: Timeout          : 10
 :: Threads          : 100
 :: Matcher          : Response status: 200,204,301,302,307,401,403,405
________________________________________________

dev                     [Status: 301, Size: 314, Words: 20, Lines: 10]
php                     [Status: 301, Size: 314, Words: 20, Lines: 10]
fonts                   [Status: 301, Size: 316, Words: 20, Lines: 10]
css                     [Status: 301, Size: 314, Words: 20, Lines: 10]
images                  [Status: 301, Size: 317, Words: 20, Lines: 10]
uploads                 [Status: 301, Size: 318, Words: 20, Lines: 10]
js                      [Status: 301, Size: 313, Words: 20, Lines: 10]
server-status           [Status: 403, Size: 302, Words: 22, Lines: 12]
                        [Status: 200, Size: 7743, Words: 2956, Lines: 162]
                        [Status: 200, Size: 7743, Words: 2956, Lines: 162]
                        [Status: 200, Size: 7743, Words: 2956, Lines: 162]
:: Progress: [62283/62283] :: Job [1/1] :: 664 req/sec :: Duration: [0:01:42] :: Errors: 3 ::
```

## Exploitation

Após acharmos o diretório "dev", vemos que o mesmo possui uma webshell em php, no qual podemos usar para pegar a flag de user e baixarmos uma webshell que será executado com o netcat.

**User flag:**

```bash
www-data@bashed:/home/arrexel# ls
user.txt
www-data@bashed:/home/arrexel# cat user.txt
2c281f318555dbc1b856957c7147bfc1
```

## Privilege Escalation

Após pegar a user flag, podemos ver com "sudo -l" que existe outro usuário, no qual podemos virar usando "sudo -u scriptmanager /bin/bash". Virando esse usuário, podemos baixar e usar o script "pspy64" e ver que existe processos rodando como root em background, um deles é executar todo tipo de arquivo .py que está na pasta "scripts".

Sabendo disso, podemos colocar uma shell em .py nessa pasta e esperar que eseja executada. Logo, teremos root.

**Root flag:** 

```bash
cat root.txt
cc4f0afe3a1026d402ba10329674a8e2
```


