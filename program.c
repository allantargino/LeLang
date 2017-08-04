#include <stdio.h>
#include <stdbool.h>
int main()
{
	int a;
	int b;
	int const c = 3;
	int const d = 3;
	bool f;
	int x;
	char* name;
	char* outro;
	int y;
	float myDec;
	a = 3 + 5 + 90;
	b = 4;
	f = false;
	myDec = 3.14f;
	f = true;
	printf("%d", f);
	name = "allan";
	name = "emily";
	printf("Digite um numero ");
	scanf("%d", &x);
	printf("Digite outro numero ");
	scanf("%d", &y);
	printf("Digite um nome ");
	scanf("%s", outro);
	printf("%s", outro);
	printf("%s", name);
	printf("%.6f", myDec);
	printf("fim");
	return 0;
}
