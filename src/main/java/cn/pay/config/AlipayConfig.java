package cn.pay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlipayConfig {

    @Value("${ALIPAY.URL}")
    private String URL;
    @Value("${ALIPAY.APPID}")
    private String APPID;
    @Value("${ALIPAY.APP_PRIVATE_KEY}")
    private String APP_PRIVATE_KEY;
    @Value("${ALIPAY.ALIPAY_PUBLIC_KEY}")
    private String ALIPAY_PUBLIC_KEY;
    @Value("${ALIPAY.SIGN_TYPE}")
    private String SIGN_TYPE;

    private String format = "json";
    private String charset = "UTF-8";

    public String getURL() {
        return URL;
    }

    public String getAPPID() {
        return APPID;
    }

    public String getAPP_PRIVATE_KEY() {
        return APP_PRIVATE_KEY;
    }

    public String getALIPAY_PUBLIC_KEY() {
        return ALIPAY_PUBLIC_KEY;
    }

    public String getSIGN_TYPE() {
        return SIGN_TYPE;
    }

    public String getFormat() {
        return format;
    }

    public String getCharset() {
        return charset;
    }
}
