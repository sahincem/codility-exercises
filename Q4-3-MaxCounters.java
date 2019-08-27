// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
  
  public int[] solution(int N, int[] A) {

        // write your code in Java SE 8
        
        // N is number of counters;
        int[] B = new int[N];
        int[] C = new int[N];
        int maxBroadcast = 0;
        int bcLocation = 0;
        int maxLocal = 0;
        int newLocal = 0;
        
        
        for(int i = 0; i < A.length; i++){
            // Find an instance where all counters are incremented (broadcast event)
            // keep track of which index caused this to happen last and how many times 
            if (A[i] == N + 1){
                maxBroadcast = maxLocal;
                bcLocation = i;
            } 
            else{
                newLocal = B[A[i]-1];
                if (newLocal < maxBroadcast){
                    newLocal = maxBroadcast;
                }
                newLocal += 1;
                if (newLocal > maxLocal){
                    maxLocal = newLocal;
                }
                B[A[i]-1] = newLocal;
            }
        }
        
        // If there were no broadcasts then we are done
        if(maxBroadcast == 0){
            return B;
        }
        
        // Replay every action after the last broadcast index
        for(int i = bcLocation + 1; i < A.length; i++){
            newLocal = C[A[i]-1];
            newLocal += 1;
            if (newLocal > maxLocal){
                maxLocal = newLocal;
            }
            C[A[i]-1] = newLocal;
        }

        // Add the broadcast correction to all the counters
        for(int i = 0; i < N; i++){
            C[i] += maxBroadcast;
        }
        
        // Finally return the answer
        return C;
    }
   
}
