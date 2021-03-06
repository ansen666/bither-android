package net.bither.bitherj.api.http;

import net.bither.bitherj.AbstractApp;
import net.bither.bitherj.api.ConnectHttps;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class BaseHttpsResponse<T> {

    private static boolean isTrust = false;
    protected T result;
    private String mUrl;
    private Logger log=null;


    public  BaseHttpsResponse(){
        log=LoggerFactory.getLogger(BaseHttpResponse.class);
    }

    public void logInfo(String logMsg){
        log.info(logMsg);
    }

    public T getResult() {
        logInfo(result.toString());
        return result;
    }

    public abstract void setResult(String response) throws Exception;

    protected synchronized void trustCerts() {
        if (!isTrust) {
            ConnectHttps.trustCerts(AbstractApp.trustCert);
            isTrust = true;
        }

    }

    public String getUrl() {
        return mUrl;
    }

    protected void setUrl(String url) {
        logInfo(url);
        this.mUrl = url;
    }

    protected String getStringFromIn(InputStream in) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                HttpSetting.REQUEST_ENCODING));
        String tempLine = rd.readLine();
        StringBuffer tempStr = new StringBuffer();
        String crlf = System.getProperty("line.separator");
        while (tempLine != null) {
            tempStr.append(tempLine);
            tempStr.append(crlf);
            tempLine = rd.readLine();
        }
        rd.close();

        return tempStr.toString();
    }


}
