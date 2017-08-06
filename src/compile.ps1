Write-Output "0. STARTING..."

$input = "../examples/HelloWorld.le"
$output = "../examples/HelloWorld.c"

java -cp ./antlr/antlr.jar antlr.Tool grammar.g
Write-Output "1. PARSER AND LEXER GENERATED."

if ($?) {
    javac -classpath "./antlr/antlr.jar;" *.java
    Write-Output "2. ALL JAVA CLASSES COMPILED."

    if ($?) {
        java -cp ".;./antlr/antlr.jar;" LeCompiler $input $output
        Write-Output "3. LE COMPILER EXECUTED."
    }
    else {
        Write-Output "==> ERROR: JAVA CLASSES."
    }
}
else {
    Write-Output "==> ERROR: GRAMMAR ANALYSIS, PARSER OR LEXER PROBLEM."
}
Write-Output "FINISHED."