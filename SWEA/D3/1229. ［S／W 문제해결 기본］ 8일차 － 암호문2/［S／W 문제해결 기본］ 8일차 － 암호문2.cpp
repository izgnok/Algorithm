#include <iostream>
using namespace std;

typedef struct Node {
    int num;
    struct Node *next;
    struct Node *pre;
}Node;

void addNode(Node *root, int num) {
    Node *newNode = new Node;
    newNode->next = NULL;
    newNode->num = num;
    
    if(root->next == NULL) {
        newNode->pre = root;
        root->next = newNode;
        return;
    }
    else {
        Node *tmp = root;
        while(tmp->next != NULL) tmp = tmp->next;
        newNode->pre = tmp;
        tmp->next= newNode;
    }
    return;
}

void InsertNode(Node *root, Node *tmpNode, int index) {
    Node *tmp = root;
    Node *tmp2 = tmp->next;
    Node *tmp3 = tmpNode->next;
    while(index>0) {
        tmp = tmp->next;
        tmp2 = tmp->next;
        index--;
    }
    tmp3->pre = tmp;
    tmp->next = tmp3;
      while(tmp3->next != NULL) tmp3 = tmp3->next;
    tmp3->next = tmp2;
    tmp2->pre = tmp3;
    return;
}

void DeleteNode(Node *root, int index, int count) {
    Node *tmp = root->next;
    while(index > 0) {
        tmp = tmp->next;
        index--;
    }
    Node *tmp2 = tmp;
    while(count > 0) {
        tmp2 = tmp2->next;
        count--;
    }
    Node *tmp3 = tmp->pre;
    tmp3->next = tmp2;
    tmp2->pre = tmp3;
    return;
}

int main() {
    int test_case =1;
    while(test_case<=10) {
        Node *root = new Node;
        int N;
        cin >> N;
        for(int i=0; i<N; i++) {
            int num;
            cin >> num;
            addNode(root, num);
        }
        int K;
        cin >> K;
        for(int i=0; i<K; i++) {
            char word;
            int index, count;
            cin >> word >> index >> count;
            Node *tmpNode = new Node;
               if(word == 'I') {
                for(int j=0; j<count; j++) {
                       int num;
                    cin >> num;
                    addNode(tmpNode, num);
                }
                InsertNode(root, tmpNode, index);
            }
            if(word =='D') {
                DeleteNode(root, index, count);
            }
        }
        cout << '#' << test_case << ' ';
        Node *printNode = root->next;
        for(int i=0; i<10; i++) {
            cout << printNode->num << ' ';
            printNode = printNode->next;
        }
        cout << endl;
        test_case++;
    }
    return 0;
}
