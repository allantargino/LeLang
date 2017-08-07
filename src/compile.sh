#!/bin/bash
echo "- STARTING..."

input="../examples/HelloWorld.le"
output="../examples/HelloWorld.c"

# Cleaning old files
find . -type f -name '*.class' -delete
find . -type f -name 'LeLexer*' -delete
find . -type f -name 'LeParser*' -delete

java -cp ./antlr/antlr.jar antlr.Tool grammar.g
echo "- PARSER AND LEXER GENERATED."
if [ $? -eq 0 ]; then
   javac -classpath ./antlr/antlr.jar:. *.java
   echo "- ALL JAVA CLASSES COMPILED."
   if [ $? -eq 0 ]; then
        java -cp ./antlr/antlr.jar:. LeCompiler $input $output
   else 
       echo "==> ERROR: JAVA CLASSES."
   fi
else
   echo "==> ERROR: GRAMMAR ANALYSIS, PARSER OR LEXER PROBLEM."
fi

echo "- FINISHED."