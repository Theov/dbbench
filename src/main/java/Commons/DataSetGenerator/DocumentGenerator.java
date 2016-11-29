package Commons.DataSetGenerator;

public class DocumentGenerator {
    private static String[] lastNameArray = {"SMITH", "JOHNSON", "WILLIAMS", "BROWN", "JONES", "MILLER", "DAVIS", "GARCIA", "RODRIGUEZ", "WILSON"};
    private static String[] firstNameArray = {"Sophia", "Jackson", "Aiden", "Emma", "Olivia", "Aiden", "Isabella", "Liam", "Mia", "Lucas"};
    private static String[] countryArray = {"United States", "France", "Germany", "Spain", "Portugal", "England", "Irland", "China", "Japan", "India"};
    private static String[] streetArray = {"Liberty street", "Glory street", "Legacy street", "Hope street", "Galaxy street", "Star street", "brown street", "Diamond street", "Sky street", "Street street"};

    public static String getDocument() {
        StringBuilder output = new StringBuilder();
        output.append("{ ")
                .append("\"lastname\":\"")
                .append(getLastName())
                .append("\", ")
                .append("\"firstname\":\"")
                .append(getFirstName())
                .append("\", ")
                .append("\"age\":")
                .append(getRandomNumberBetween(18, 90))
                .append(", ")
                .append("\"gender\":\"")
                .append(getGender())
                .append("\", ")
                .append("\"address\":{")
                .append("\"country\":\"")
                .append(getCountry())
                .append("\", ")
                .append("\"street\":\"")
                .append(getStreet())
                .append("\", ")
                .append("\"number\":")
                .append(getRandomNumberBetween(1, 300))
                .append("}")
                .append(" }");
        return output.toString();
    }

    private static String getGender() {
        return (getRandomNumberBetween(0, 1) == 0 ? "m" : "f");
    }

    private static String getLastName() {
        return lastNameArray[getRandomNumberBetween(0, 9)];
    }

    public static String getFirstName() {
        return firstNameArray[getRandomNumberBetween(0, 9)];
    }

    private static String getCountry() {
        return countryArray[getRandomNumberBetween(0, 9)];
    }

    private static String getStreet() {
        return streetArray[getRandomNumberBetween(0, 9)];
    }

    private static int getRandomNumberBetween(int Min, int Max){
        return (int) (Math.random() * ( Max - Min ));
    }
}
