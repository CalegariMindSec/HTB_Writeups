# Writeup scriptkiddie

## Scan

```
# Nmap 7.92 scan initiated Thu Jan 27 10:33:13 2022 as: nmap -sV --open -v -T5 -p- -Pn -oN fullnmap.txt 10.129.182.219
Nmap scan report for 10.129.182.219
Host is up (0.15s latency).
Not shown: 61703 closed tcp ports (conn-refused), 3830 filtered tcp ports (no-response)
Some closed ports may be reported as filtered due to --defeat-rst-ratelimit
PORT     STATE SERVICE VERSION
22/tcp   open  ssh     OpenSSH 8.2p1 Ubuntu 4ubuntu0.1 (Ubuntu Linux; protocol 2.0)
5000/tcp open  http    Werkzeug httpd 0.16.1 (Python 3.8.5)
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Thu Jan 27 10:34:10 2022 -- 1 IP address (1 host up) scanned in 57.11 seconds
```

## Enumeration

Após feito o scan, podemosver que o porta 5000 possui um painel com algumas tools, uma delas sendo o msfvenom. Dando uma pesquisada, podemos encontrar que o msfvenom tem um rce pra template em APK, uma vulnerabilidade que pode ser encontrada aqui: [Rapid7 Metasploit Framework msfvenom APK Template Command Injection](https://www.rapid7.com/db/modules/exploit/unix/fileformat/metasploit_msfvenom_apk_template_cmd_injection/).

Podemos usar esse exploit para gerar um template de reverse shell para pegarmos

acesso à máquina. 

## Exploitation

Tendo executado o exploit e criado o template APK, podemos colocar no host nas opções da tool e esperar a reverse shell.

**User Flag:**

```
$ cat user.txt
cat user.txt
03874555e6f413403e0547ecf3e3822d
```

## Privilege Escalation

Para root, foi usado uma vulnerabilidade recente chamada pwnkit https://github.com/arthepsy/CVE-2021-4034.git que é uma vulnerabilidade encontrada para kernel linux, so precisamos baixar o exploit compilado e executar na máquina. Feito isso, seremos root.

**Root flag:**

```

```
