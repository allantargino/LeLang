#!/bin/bash
echo "0. STARTING..."

input="../examples/HelloWorld.le"
output="../examples/HelloWorld.c"

java -cp ./antlr/antlr.jar antlr.Tool grammar.g
echo "1. PARSER AND LEXER GENERATED."
if [ $? -eq 0 ]; then
   javac -classpath ./antlr/antlr.jar:. *.java
   echo "2. ALL JAVA CLASSES COMPILED."
   if [ $? -eq 0 ]; then
        java -cp ./antlr/antlr.jar:. MainClass $input $output
        echo "3. LE COMPILER EXECUTED."
   else 
       echo "==> ERROR: JAVA CLASSES."
   fi
else
   echo "==> ERROR: GRAMMAR ANALYSIS, PARSER OR LEXER PROBLEM."
fi

echo "FINISHED."