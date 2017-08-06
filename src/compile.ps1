Write-Output "- STARTING..."

$input = "../examples/HelloWorld.le"
$output = "../examples/HelloWorld.c"

rm -r *.class
java -cp ./antlr/antlr.jar antlr.Tool grammar.g
Write-Output "- PARSER AND LEXER GENERATED."

if ($?) {
    javac -classpath "./antlr/antlr.jar;" *.java
    Write-Output "- ALL JAVA CLASSES COMPILED."

    if ($?) {
        java -cp ".;./antlr/antlr.jar;" LeCompiler $input $output
    }
    else {
        Write-Output "==> ERROR: JAVA CLASSES."
    }
}
else {
    Write-Output "==> ERROR: GRAMMAR ANALYSIS, PARSER OR LEXER PROBLEM."
}
Write-Output "FINISHED."