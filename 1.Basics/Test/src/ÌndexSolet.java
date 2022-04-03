import javache.http.HttpStatus;
import solet.BaseHttpSolet;
import solet.HttpSoletRequest;
import solet.HttpSoletResponse;
import solet.WebSolet;

import java.util.Date;

@WebSolet(route = "/index")
public class ÌndexSolet extends BaseHttpSolet {
    private static final String SERVER_NAME_AND_VERSION = "Javache/-1.0.0";
    private static final String SERVER_HEADER = "Server: ";
    private static final String DATE_HEADER = "Date: ";
    private static final String CONTENT_TYPE_HEADER = "Content-Type: ";
    private static final String CONTENT_TYPE_HTML = "text/html";

    @Override
    public void doGet(HttpSoletRequest request, HttpSoletResponse response) {
        response.setContent("<h1>Hi!</h1>".getBytes());
        response.setStatusCode(HttpStatus.OK);

        response.addHeader(SERVER_HEADER, SERVER_NAME_AND_VERSION);
        response.addHeader(DATE_HEADER, new Date().toString());
        response.addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_HTML);
    }
}
