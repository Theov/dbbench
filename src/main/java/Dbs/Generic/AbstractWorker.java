package Dbs.Generic;

import Commons.UuidUtil;
import Dbs.Contracts.Worker;


public class AbstractWorker implements Worker {
    protected boolean errorOccured = false;
    protected long numberOfElements = 100000;

    protected String getUuid(int i){
        String output = UuidUtil.getUuid();

        if( i == (numberOfElements / 2)){
            output = "magicEntry";
        }

        return output;
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
}
