package Commons.DataSetGenerator;


import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private List<String> stringDocuments;
    private List<Document> documentDocuments;

    private List<String> stringCriterias;
    private List<Document> documentCriterias;

    private String magicFirstName;

    private Bson magicFilter;
    private int numberOfElements;

    private String updatedDocument;

    private Document updatedDocumentDocument;
    public DataSet(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        populateDataSet();
    }

    private void populateDataSet(){
        stringDocuments = new ArrayList<>();
        documentDocuments = new ArrayList<>();
        stringCriterias = new ArrayList<>();
        documentCriterias = new ArrayList<>();

        for(int i = 0; i < numberOfElements; i++){
            String doc = DocumentGenerator.getDocument();

            stringDocuments.add(doc);
            documentDocuments.add(Document.parse(doc));

            updateSearchCriterias(new JSONObject(doc).getString("firstname"));
            setMagicAttributes(i);
        }

        setDocumentCriterias();
    }

    private void updateSearchCriterias(String firstnameToAdd) {
        boolean criteriaAlreadyAdd = stringCriterias.stream().anyMatch(obj-> obj.equals(firstnameToAdd));

        if(!criteriaAlreadyAdd){
            stringCriterias.add(firstnameToAdd);
        }
    }

    private void setDocumentCriterias() {
        stringCriterias.forEach(item->
           documentCriterias.add(new Document("firstname", item))
        );
    }

    private void setMagicAttributes(int actualIndex) {
        if(actualIndex == (numberOfElements / 2)){
            JSONObject objUser = new JSONObject(stringDocuments.get(actualIndex));

            magicFirstName = objUser.getString("firstname");
            magicFilter = Filters.eq("firstname", magicFirstName);

            updatedDocument = objUser.put("updated", "updated").toString();
            updatedDocumentDocument = new Document("$set", Document.parse(updatedDocument));

            System.out.println("magicDocument -> " + stringDocuments.get(actualIndex));
            System.out.println("updatedDocument -> " + updatedDocument);
        }
    }

    public List<String> getDataset(){
        return stringDocuments;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public String getMagicFirstName() {
        return magicFirstName;
    }

    public String getUpdatedDocument() {
        return updatedDocument;
    }

    public List<Document> getDocumentDocuments() {
        return documentDocuments;
    }

    public Bson getMagicFilter() {
        return magicFilter;
    }

    public Document getUpdatedDocumentDocument() {
        return updatedDocumentDocument;
    }

    public List<String> getStringCriterias() {
        return stringCriterias;
    }

    public List<Document> getDocumentCriterias() {
        return documentCriterias;
    }
}
