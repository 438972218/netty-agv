package com.xdcplus.xdcweb;

import com.xdcplus.xdcweb.basics.service.impl.TcpServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XdcWebApplicationTests {

    @Test
    void test() {
        TcpServer server = new TcpServer(8080);

        System.out.println("==========Start Server First==========");
        server.init();
    }

}
