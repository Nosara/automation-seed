package seed.automation.steps;

import cucumber.runtime.java.guice.ScenarioScoped;

import java.util.HashMap;
import java.util.Map;



@ScenarioScoped
public class Storage {
    private  Map<String, String> storage = new HashMap();

    public  void set(String key, String value) {
        storage.put(key, value);
    }

    public  String get(String key) {
        return storage.get(key);
    }
}
