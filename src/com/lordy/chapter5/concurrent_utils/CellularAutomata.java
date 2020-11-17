package com.lordy.chapter5.concurrent_utils;




import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Barrier 栏栅
 */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                mainBoard.commitNewValues();
            }
        });
        this.workers = new Worker[count];
        for(int i = 0; i < count; i++){
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    public void start(){
        for(int i = 0; i < workers.length; i++){
            new Thread(workers[i]).start();
        }
        mainBoard.waitForConvergence();
    }


    private class Worker implements Runnable{


        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        private int computeValue(int x, int y){
            return 0;
        }


        @Override
        public void run() {
            while (!board.hasConverged()){
                for(int i = 0; i < board.getMaxX(); i++){
                    for(int y = 0; y < board.getMaxY(); y++){
                        board.setNewValue(i, y, computeValue(i, y));
                    }
                }
                try {
                    barrier.await();
                }catch (InterruptedException e){
                    return;
                }catch (BrokenBarrierException e){
                    return;
                }
            }
        }
    }
    interface Board{
        int getMaxX();
        int getMaxY();
        int getValue(int x, int y);
        int setNewValue(int x, int y, int value);
        void commitNewValues();
        boolean hasConverged();
        void waitForConvergence();
        Board getSubBoard(int numPartitions, int index);
    }
}
