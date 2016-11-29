package Commons;

import Dbs.Contracts.Worker;

import java.util.concurrent.Callable;

public class WorkerAnalyser {
    private Worker worker;

    public WorkerAnalyser(Worker w){
        this.worker = w;
    }

    public void execBench(){
        System.out.println("Bench launched for " + worker.getWorkerName());

        executor(() -> worker.setUp(), "setUp");
        executor(() -> worker.insert(), "insert");
        executor(() -> worker.select(), "select");
        executor(() -> worker.update(), "update");
        executor(() -> worker.stat(), "stat");
        executor(() -> worker.delete(), "delete");

        System.out.println("Bench finished for " + worker.getWorkerName());
    }

    private void executor(Callable<?> func, String operation){
        long startTime = System.currentTimeMillis();

        try {
            func.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long stopTime = System.currentTimeMillis();

        System.out.println("Elapsed time was " + (stopTime - startTime) + " miliseconds for worker " + worker.getWorkerName() + " on " + operation + " operation");
    }
}
