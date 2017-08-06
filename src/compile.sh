#!/bin/bash
echo "STARTING..."
java -cp ./antlr/antlr.jar antlr.Tool grammar.g
echo "PARSER AND LEXER GENERATED"
if [ $? -eq 0 ]; then
   javac -classpath ./antlr/antlr.jar:. *.java
   echo "ALL CLASSES COMPILED"
   if [ $? -eq 0 ]; then
       java -cp ./antlr/antlr.jar:. LeCompiler.java
   else 
       echo "GAVE BAD COMPILING"
   fi
else
   echo "GAVE BAD TO GENERATE PARSER"
fi
