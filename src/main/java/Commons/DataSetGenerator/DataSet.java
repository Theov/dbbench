package Commons.DataSetGenerator;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private List<String> documents;

    public DataSet(int numberOfElements) {
        populateDataSet(numberOfElements);
    }

    public void populateDataSet(int numberOfElements){
        documents = new ArrayList<>();

        for(int i = 0; i < numberOfElements; i++){
            documents.add(DocumentGenerator.getDocument());
        }
    }

    public List<String> getDataset(){
        return documents;
    }
}
