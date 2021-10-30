package seed.automation;

import com.google.inject.Inject;
import io.restassured.http.Header;

import java.util.ArrayList;
import java.util.List;



public class Headers {
    public ArrayList<Header> headerList = new ArrayList<Header>();

    @Inject
    public  List<Header> setHeader(String property, String value){
        headerList.add(new Header(property, value));
        return headerList;
    }


    public void deleteList(){
        headerList.clear();
    }
}
