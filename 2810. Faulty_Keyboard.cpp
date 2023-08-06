class Solution {
public:
    string finalString(string s) {
        string str = "";
        for(auto &i: s){
            if(i=='i'){
                reverse(str.begin(),str.end());
            }else{
                str += i;
            }
        }
        return str;
    }
};
