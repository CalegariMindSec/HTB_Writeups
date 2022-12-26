# Lame Writeup

## Scan

```
# Nmap 7.92 scan initiated Mon Dec 27 09:10:40 2021 as: nmap -sV -Pn -v -oN scan.txt 10.129.166.5
Nmap scan report for 10.129.166.5
Host is up (0.16s latency).
Not shown: 996 filtered tcp ports (no-response)
PORT    STATE SERVICE     VERSION
21/tcp  open  ftp         vsftpd 2.3.4
22/tcp  open  ssh         OpenSSH 4.7p1 Debian 8ubuntu1 (protocol 2.0)
139/tcp open  netbios-ssn Samba smbd 3.X - 4.X (workgroup: WORKGROUP)
445/tcp open  netbios-ssn Samba smbd 3.X - 4.X (workgroup: WORKGROUP)
Service Info: OSs: Unix, Linux; CPE: cpe:/o:linux:linux_kernel

```

Tendo feito o scan, conseguimos ver dois vetores de ataque, o vsftpd e o samba. O vetor correto de ataque é o serviço samba com exploit publico no msfconsole.

* Exploit:
```
exploit/multi/samba/usermap_script
```

* User Flag:
```
cd home/makis
cat user.txt
c5c36bc30b80f86371ce1fee06cbc0ec
```
* Root Flag:
```
cd /root
cat root.txt
f7a66d7d0fb71f3e9d4470c1e9490943
```
