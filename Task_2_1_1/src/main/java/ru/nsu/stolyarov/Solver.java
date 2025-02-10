package ru.nsu.stolyarov;

import java.util.ArrayList;

public class Solver{

    protected ArrayList<Integer> digits;
    protected boolean b;
    protected int cur;

    public boolean isSimple(int digit){
        for(int i = 2; i * i <= digit; i++){
            if(digit % i == 0){
                return false;
            }
        }
        return true;
    }

    public Solver(ArrayList<Integer> ar){
        digits = ar;
    }
    public boolean solve(int threadsAmount) throws InterruptedException {
        b = false;
        cur = 0;
        
        if(threadsAmount == 1){
            SolverThread mainThread = new SolverThread();
            mainThread.run();
        } else if (threadsAmount == 0) {
            if(digits.parallelStream().map(n -> isSimple(n)).filter(n -> !n).toList().isEmpty()){
                return false;
            }
            else{
                return true;
            }

        } else if (threadsAmount > 1) {
            ArrayList<SolverThread> threads = new ArrayList<>();
            for(int i = 0; i < threadsAmount; i++) {
                SolverThread anotherThread = new SolverThread();
                anotherThread.id = i;
                threads.add(anotherThread);
            }
            for(int i = 0; i < threadsAmount; i++) {
                threads.get(i).start();
            }
            for(int i = 0; i < threadsAmount; i++) {
                threads.get(i).join();
            }
        }

        return b;
    }

    private class SolverThread extends Thread{
        int id;

        private synchronized int increment(){
            cur++;
            return cur - 1;
        }
        @Override
        public void run(){
            while (!b && cur < digits.size()){
                int t = increment();
                if(t < digits.size() && !isSimple(digits.get(t))){
                    b = true;
                }
            }
        }
    }
}
