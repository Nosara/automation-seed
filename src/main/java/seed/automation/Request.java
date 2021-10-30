package seed.automation;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;


public class Request {

    public List<Header> headerList;
    RequestSpecification request;


    public Request() {
        request = null;
        RestAssured.reset();
        request = RestAssured.given().log().all();
    }

    public Response getRequest(String body, String path, String url, List<Header> headerList,
                               String parameterPath, String parameterBody){
        return this.makeRequest(HttpMethod.GET, body, path, url, headerList, parameterPath, parameterBody );
    }

    public Response postRequest(String body, String path, String url, List<Header> headerList,
                                String parameterPath, String parameterBody){
        return this.makeRequest(HttpMethod.POST, body, path, url, headerList, parameterPath, parameterBody );
    }

    public Response putRequest(String body, String path, String url, List<Header> headerList,
                               String parameterPath, String parameterBody){
        return this.makeRequest(HttpMethod.PUT, body, path, url, headerList, parameterPath, parameterBody );
    }

    public Response patchRequest(String body, String path, String url, List<Header> headerList,
                                 String parameterPath, String parameterBody){
        return this.makeRequest(HttpMethod.PATCH, body, path, url, headerList, parameterPath, parameterBody );
    }

    public Response makeRequest(HttpMethod method, String body, String path, String url, List<Header> headerList,
                                  String parameterPath, String parameterBody) {
        request.baseUri(url);
        Headers header = new Headers(headerList);
        request.headers(header);
        String pathParameter;
        if (!body.isEmpty()) {
            request.body(new Utilities().getInfo("bodies", body));
        }

        if (parameterPath != null) {
            pathParameter = getParameterSplited(path);
            request.pathParam(pathParameter, parameterPath);

        }

        switch (method) {
            case GET:
                return request.get(path);
            case POST:
                return request.post(path);
            case PATCH:
                return request.patch(path);
            case PUT:
                return request.put(path);
            default:
                throw new IllegalStateException("Not a valid HTTP method - " + method);
        }
    }

    private String getParameterSplited(String path) {
        String pathParameter;
        pathParameter = path.split("\\{")[1].replace("}", "");
        return pathParameter;
    }
}