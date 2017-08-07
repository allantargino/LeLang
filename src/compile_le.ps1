Write-Output "- STARTING..."

Write-Host "Input: $($args[0])"
Write-Host "Output: $($args[1])"

$input = $args[0]
$output = $args[1]

java -cp ".;./antlr/antlr.jar;" LeCompiler $input $output

Write-Output "FINISHED."