package Dbs.Generic;

import Commons.DataSetGenerator.DataSet;
import Dbs.Contracts.Worker;

public class GenericWorker implements Worker {
    protected DataSet dataSet;
    protected String workerName = "generic";

    public GenericWorker(DataSet ds){
        dataSet = ds;
    }

    public Object setUp() {
        return null;
    }

    public Object insert() {
        return null;
    }

    public Object update() {
        return null;
    }

    public Object select() {
        return null;
    }

    public Object delete() {
        return null;
    }

    public Object stat() {
        return null;
    }

    public String getWorkerName(){
        return workerName;
    }
}
