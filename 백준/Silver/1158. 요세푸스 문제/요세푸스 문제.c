#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

typedef struct Node {
	int Data;
	struct Node *next;
}Node;

Node *Head = NULL;

void addNode(int Data) {
	Node *newNode = (Node*)malloc(sizeof(Node));
	Node *Temp;
	newNode->Data = Data;
	if (Head == NULL)
	{
		Head = newNode;
		newNode->next = newNode;
	}
	else 
	{
		Temp = Head;
		while (Temp->next != Head)
		{
			Temp = Temp->next;
		}
		Temp->next = newNode;
		newNode->next = Head;
	}
}

int DeleteNode(int Num) {
	Node *Temp;
	Node *PreTemp;
	Node *Search;
	int Data;
	PreTemp = Head;
	Temp = Head->next;
	if (Num == 1)
	{
		Search = Head;
		while (Search->next != Head)
		{
			Search = Search->next;
		}
		Data = PreTemp->Data;
		free(PreTemp);
		Search->next = Temp;
		Head = Temp;
	}
	else {
		for (int i = 0; i < (Num - 2); i++)
		{
			PreTemp = Temp;
			Temp = Temp->next;
		}
		Data = Temp->Data;
		PreTemp->next = Temp->next;
		Head = Temp->next;
		free(Temp);
	}
	return Data;
}

int main() {
	int N, K;
	scanf("%d %d", &N, &K);
	for (int i=1; i<=N; i++)
	{
		addNode(i);
	}
	printf("<");
	while (N > 0)
	{
		if (N == 1) printf("%d>", DeleteNode(K));
		else printf("%d, ", DeleteNode(K));
		N--;
	}
	return 0;
}
