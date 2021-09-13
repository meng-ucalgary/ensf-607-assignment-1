@REM CLEAR THE SCREEN
CLS

@REM COMPILE THE FILES
javac -cp ".;lib/*" -sourcepath "src" -d "bin" src/registrationSystem/*.java

@REM RUN THE JAVA CLASS FILE
java -cp ".;lib/*;bin" registrationSystem.App
