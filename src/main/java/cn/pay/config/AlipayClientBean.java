package cn.pay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AlipayClientBean {
    @Autowired
    private AlipayConfig alipayConfig;

    @Bean
    public AlipayClient alipayClient() {
        /**
         * DefaultAlipayClient(String serverUrl, String appId,
         * String privateKey, String format, String charset, String alipayPublicKey, String signType)
         */
        AlipayClient client = new DefaultAlipayClient(alipayConfig.getURL(),alipayConfig.getAPPID(),
                alipayConfig.getAPP_PRIVATE_KEY(),alipayConfig.getFormat(),alipayConfig.getCharset(),
                alipayConfig.getALIPAY_PUBLIC_KEY(),alipayConfig.getSIGN_TYPE());
        return client;
    }
}
