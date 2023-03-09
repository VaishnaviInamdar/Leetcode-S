brute force code:
class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        
        set<ListNode *> s;
        ListNode * temp=head;
        while(temp){
            if(s.find(temp)!=s.end())return temp;
            else{
                s.insert(temp);
            }
            temp=temp->next;
        }
        return NULL;
        
    }
};
