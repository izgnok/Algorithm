#include <iostream>
using namespace std;

typedef struct Node {
    int num;
    Node* pre;
    Node* next;
} Node;

void AddNode(Node* root, int num) {
    Node *newNode = new Node;
    newNode->num = num;
    newNode->next = NULL;
    
    if(root->next == NULL) {
        root->next = newNode;
        newNode->pre = root;
    }
    else {
        Node *tmpNode = root;
        while(tmpNode->next != NULL) {
            tmpNode = tmpNode->next;
        }
        tmpNode->next = newNode;
        newNode->pre = tmpNode;
    }
}

void DeleteNode(Node *root) {
    Node *tmpNode = root->next;
    Node *tmpNode2 = tmpNode->next;
    
    while(tmpNode->next != NULL) {
        if(tmpNode->num == tmpNode2->num) {
            if(tmpNode2->next != NULL) tmpNode->pre->next = tmpNode2->next;
            else tmpNode->pre->next = NULL;
            if(tmpNode2->next != NULL) tmpNode2->next->pre = tmpNode->pre;
            tmpNode = tmpNode->pre;
            if(tmpNode->next != NULL) tmpNode2 = tmpNode->next;
        }
        else {
            if(tmpNode->next != NULL) tmpNode = tmpNode->next;
            if(tmpNode->next !=NULL) tmpNode2 = tmpNode->next;
        }
    }
}

int main() {
    int test_case = 1;
    while(test_case<=10) {
        int N;
        cin >> N;
        Node *root = new Node;
        root->next = NULL;
        root->pre = NULL;
        for(int i=0; i<N; i++) {
            char c;
            cin >> c;
            AddNode(root, c-'0');
        }
        DeleteNode(root);
        cout << '#' << test_case << ' ';
		Node* tmpNode = root->next;
        while(true) {
            cout << tmpNode->num;
            tmpNode = tmpNode->next;
            if(tmpNode == NULL) break;
        }
        cout << endl;
        test_case++;
    }
    return 0;
}