#!/bin/bash

nim c -d:mingw --app:gui --cc:gcc -d:danger -d:strip $1
