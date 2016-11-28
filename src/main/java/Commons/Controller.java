package Commons;

import Dbs.Mongo.MongoWorker;
import Dbs.PostgreSQL.PostgreWorker;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public void execBench() {
        List<WorkerAnalyser> workerCollection = new ArrayList<>();

        workerCollection.add( new WorkerAnalyser(new PostgreWorker(), "PostgreSQL") );
        workerCollection.add( new WorkerAnalyser(new MongoWorker(), "MongoDB") );

        workerCollection.forEach(item->item.execBench());
    }
}
