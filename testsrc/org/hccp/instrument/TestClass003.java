package org.hccp.instrument;

import com.slamd.http.HTTPClient;
import com.slamd.http.HTTPException;
import com.slamd.http.HTTPRequest;

import java.net.MalformedURLException;
import java.net.URL;


/**
 */
public class TestClass003 {


    public void doHttpRequest() {

        try {
            HTTPRequest request = new HTTPRequest(true, new URL("http://www.hccp.org"));
            HTTPClient client = new HTTPClient();
            client.sendRequest(request);
        } catch (MalformedURLException e) {
            //do nothing
        } catch (HTTPException e) {
            // do nothing
        }


    }

}
