package utilities;

public class DataProvider {

    public Object[][] loginData(){
        return new Object[][]{
                {"admin", "12345", "success"},
                {"guest", "guest123", "failure"}

        };
    }
}
