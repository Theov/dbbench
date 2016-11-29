package Commons;

import Commons.DataSetGenerator.DataSet;
import Dbs.Mongo.MongoWorker;
import Dbs.PostgreSQL.PostgreWorker;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public void execBench() {
        DataSet dataSet = new DataSet(1000);
        List<WorkerAnalyser> workerCollection = new ArrayList<>();

        workerCollection.add( new WorkerAnalyser(new PostgreWorker(dataSet)) );
        workerCollection.add( new WorkerAnalyser(new MongoWorker(dataSet)) );

        workerCollection.forEach(item->item.execBench());
    }
}
