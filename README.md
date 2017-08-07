# LeProgrammingLanguage

### Requirements
* You must have JDK installed on your machine.

### Usage
* Linux: Run *compile.sh* file to compile the language. 
* Windows: Run *compile.ps1* file to compile the language.

You can modify these scripts to compile any programs in Le.
To use a Windows IDE, visit: [LeLang.IDE](https://github.com/allantargino/lelang.ide)

### Example
```c
program myProgram
{
	# variable declaration
	int a, b, c;
	bool cond;
	cte int d:=89, e:=77;
	decimal myDec;
	str name;

	# program body
	a:=3+5+90*2;
	cond:=false;

	myDec:=3.14f;
       
	if(c>2 && a>2)
		Write("if1");
		if(cond)
			Write("if2");
		else
			Write("else2");
		endif
	else
		Write("else1");
	endif
	
	while(c>10)
		Write("o");
		c := c - 1;
	next

	Write("Your Name ");
	Read(name);
	Write("The End");
}

```
