package mapWithNameAndPassword;

import java.util.HashMap;
import java.util.Map;

/**
 * Temporary class for store customers names and passwords
 */
public class CustomerNameAndPass {

    public static Map<String, String> map;

    /*
    This method creates every time new Map, but it is not right.
    We need to save all entered customers names and passwords.
    If we create HashMap such as this class field - method doPost from
    RigisterServlet class will not work.
     */
    public void customersNamesAndPasswords(String name, String password) {
        map = new HashMap<>();
        map.put(name, password);
    }

    /*
    Just shows in log names and values from map
     */
    public void mapToString() {
        for (Map.Entry<String, String> matToConsole : map.entrySet()) {
            System.out.println("name from map: "
                    + matToConsole.getKey() +
                    " password from map:" + matToConsole.getValue());
        }
    }
}
