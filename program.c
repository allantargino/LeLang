#include <stdio.h>
#include <stdbool.h>
int main()
{
	int a;
	int b;
	int c;
	int const d = 89;
	int numero;
	int const e = 77;
	int x;
	int y;
	float myDec;
	a= 3 + 5 + 90;
	
	b= 4;
	
	myDec= 3.14f;
	
	printf("Digite um numero ");
	
	scanf("%d", &c);

		if( c > 2  && a > 2 ){
printf("if1");
			if( c > 2 ){
printf("if2");
				}else{
printf("else2");
					}
}else{
printf("else1");
					}

						while( c > 2 ){
		printf("while ");
								c= c - 1;
							}

						printf("Fim do programa");
						
						return 0;
}
