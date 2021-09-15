#!/bin/bash

# COMPILE THE FILES
javac -cp ".:lib/*" -sourcepath "src" -d "bin" src/registrationSystem/*.java

# RUN THE JAVA CLASS FILE
java -cp ".:lib/*:bin" registrationSystem.App
