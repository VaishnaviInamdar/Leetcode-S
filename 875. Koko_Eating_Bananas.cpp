class Solution {
public:
    bool canEatAll(vector<int>& piles,int givenHour,int h)
    {
      int actualHours = 0;
      
      for(auto t : piles)
      {
        actualHours += t/givenHour; 
        if(t%givenHour != 0)
        {
          actualHours++;
        }
        
      }
      return actualHours<=h;
    
    }
    int minEatingSpeed(vector<int>& piles, int h) {
        int n = piles.size();
        int l = 1;
        int r = *max_element(begin(piles), end(piles));
      
        while(l<r){
          int mid = l + (r-l)/2;
          
          if(canEatAll(piles,mid,h))
          {
             r = mid;
          }
          else
          {
            l = mid+1;
          }
        }
      return l;
    }
};
