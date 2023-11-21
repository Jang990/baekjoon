class Solution {
    int[][] user;
    int[] emoticon;
    
    int maxSubscriber = 0;
    int maxSalesAmount = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        user = users;
        emoticon = emoticons;
        rec(0, new int[emoticon.length]);
        answer[0] = maxSubscriber;
        answer[1] = maxSalesAmount;
        return answer;
    }
    
    void rec(int depth, int[] discountRates) {
        if(depth == emoticon.length) {
            calcualteResult(discountRates);
            return;
        }
        
        discountRates[depth] = 10;
        rec(depth+1, discountRates);
        
        discountRates[depth] = 20;
        rec(depth+1, discountRates);
        
        discountRates[depth] = 30;
        rec(depth+1, discountRates);
        
        discountRates[depth] = 40;
        rec(depth+1, discountRates);
    }
    
    void calcualteResult(int[] discountRates) {
        int nowSubscriber = 0;
        int nowSalesAmount = 0;
        
        for(int i = 0; i < user.length; i++) {
            int userSalesAmount = 0;
            for(int j = 0; j < emoticon.length; j++) {
                int emoticonPrice = emoticon[j];
                int discountRate = discountRates[j];
                if(discountRate >= user[i][0])
                    userSalesAmount += (emoticonPrice / 100 * (100 - discountRate));
            }
            if(userSalesAmount >= user[i][1]) {
                nowSubscriber++;
            } else {
                nowSalesAmount += userSalesAmount;
            }
        }
        
        if(nowSubscriber > maxSubscriber) {
            maxSubscriber = nowSubscriber;
            maxSalesAmount = nowSalesAmount;
        } else if(nowSubscriber == maxSubscriber) {
            maxSalesAmount = Math.max(maxSalesAmount, nowSalesAmount);
        }
    }
}