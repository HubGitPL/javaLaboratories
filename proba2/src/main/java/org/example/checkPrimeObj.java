package org.example;

public class checkPrimeObj {
    private boolean checkPrimeObj(int number){
        if(number < 2) return false;
        for(int i = 2; i < number; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
