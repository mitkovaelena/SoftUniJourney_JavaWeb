package solet;

import javache.http.HttpRequestImpl;

import java.io.InputStream;

public class HttpSoletRequestImpl extends HttpRequestImpl implements HttpSoletRequest {
    private InputStream requestStream;

    public HttpSoletRequestImpl(String requestContent, InputStream requestStream) {
        super(requestContent);
        this.requestStream = requestStream;
    }

    @Override
    public InputStream getRequestStream() {
        return this.requestStream;
    }
}