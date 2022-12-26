# Legacy WriteUp

## Scan and Enumeration

**Comando**: sudo nmap -sS -Pn -p 139,445,3389 -v -T5 10.129.1.111 --script=smb-vuln*
```
Nmap scan report for 10.129.1.111
Host is up (0.21s latency).

PORT     STATE  SERVICE
139/tcp  open   netbios-ssn
445/tcp  open   microsoft-ds
3389/tcp closed ms-wbt-server

Host script results:
|_smb-vuln-ms10-061: ERROR: Script execution failed (use -d to debug)
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
|       https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2017-0143
|       https://blogs.technet.microsoft.com/msrc/2017/05/12/customer-guidance-for-wannacrypt-attacks/
|_      https://technet.microsoft.com/en-us/library/security/ms17-010.aspx
|_smb-vuln-ms10-054: false
| smb-vuln-ms08-067:
|   VULNERABLE:
|   Microsoft Windows system vulnerable to remote code execution (MS08-067)
|     State: VULNERABLE
|     IDs:  CVE:CVE-2008-4250
|           The Server service in Microsoft Windows 2000 SP4, XP SP2 and SP3, Server 2003 SP1 and SP2,
|           Vista Gold and SP1, Server 2008, and 7 Pre-Beta allows remote attackers to execute arbitrary
|           code via a crafted RPC request that triggers the overflow during path canonicalization.
|           
|     Disclosure date: 2008-10-23
|     References:
|       https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2008-4250
|_      https://technet.microsoft.com/en-us/library/security/ms08-067.aspx

NSE: Script Post-scanning.
Initiating NSE at 09:55
Completed NSE at 09:55, 0.00s elapsed
Read data files from: /usr/bin/../share/nmap
Nmap done: 1 IP address (1 host up) scanned in 7.42 seconds
           Raw packets sent: 3 (132B) | Rcvd: 3 (128B)
```

## Exploitation

Como visto na saída do nmap o alvo aparenta ser vulnerável a ms17-010 e a ms08-067. Usaremos o exploit da ms08-067 do msfconsole para invadir a máquina.

Tendo usado o exploit e pegado reverse shell, podemos pegar a user e root flag.

* User flag:

```
cd C:\Documents and Settings\john\Desktop
type user.txt
e69af0e4f443de7e36876fda4ec7644f
```

* Root flag:

```
cd C:\Documents and Settings\Administrator\Desktop
type root.txt
993442d258b0e0ec917cae9e695d5713
```
