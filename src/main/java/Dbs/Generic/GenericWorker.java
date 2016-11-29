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

    public Object updateAll() {
        return null;
    }

    public Object selectAll() {
        return null;
    }

    public Object selectOne(String itemToFind) {
        return null;
    }

    public Object delete() {
        return null;
    }

    public Object sort() {
        return null;
    }

    public Object tearDown() {
        return null;
    }

    public String getWorkerName(){
        return workerName;
    }
}
