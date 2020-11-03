import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class WebServiceCamelDemo {

    public static void main(String[] args) throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        context.start();
        context.addRoutes(new RouteBuilder() {
            /**
             * payload的形式访问。
             * sayGood表示的是operationName
             * ser=指namespace，
             * 下面是参数。
             * <ser:sayGood xmlns:ser="http://webservice.study.nmm.com/">
             *     <name>word</name>
             *     <sex>male</sex>
             * </ser:sayGood>
             * @throws Exception
             */
            @Override
            public void configure() throws Exception {
                from("jetty:http://0.0.0.0:8888/hello/webservice2http")
                        .to("cxf:" +
                                "http://localhost:9000/hello/webservice" + // service address
                                "?wsdlURL=http://localhost:9000/hello/webservice?wsdl" +
//                                "&serviceClass=com.nmm.study.webservice.HelloService" +
                                "&dataFormat=PAYLOAD")
                        .convertBodyTo(String.class)
                        .to("log:output");
                /**camel默认识别第一个operation作为参数，如果我们想要指定可以通过defaultOperationName来指定*/
                from("jetty:http://0.0.0.0:8888/hello/sayGood")
                        .to("cxf:" +
                                "http://localhost:9000/hello/webservice" + // service address
                                "?wsdlURL=http://localhost:9000/hello/webservice?wsdl" +
                                "&defaultOperationName=sayGood" +
//                                "&serviceClass=com.nmm.study.webservice.HelloService" +
                                "&dataFormat=PAYLOAD")
                        .convertBodyTo(String.class)
//                        .setHeader("operationName",constant("sayGood"))
                        .to("log:output");

                from("jetty:http://0.0.0.0:8888/hello/getInfo")
                        .to("cxf:" +
                                "http://localhost:9000/hello/webservice" + // service address
                                "?wsdlURL=http://localhost:9000/hello/webservice?wsdl" +
                                "&defaultOperationName=getInfo" +
//                                "&serviceClass=com.nmm.study.webservice.HelloService" +
                                "&dataFormat=PAYLOAD")
                        .convertBodyTo(String.class)
//                        .setHeader("operationName",constant("sayGood"))
                        .to("log:output");
                /**
                 * 复杂入参：
                 * <ser:validateInfo xmlns:ser="http://webservice.study.nmm.com/">
                 * 	<userinfo>
                 * 		<name>word</name>
                 * 		<age>12</age>
                 * 		<birthday>2010-10-11</birthday>
                 * 	</userinfo>
                 * </ser:validateInfo>
                 */
                from("jetty:http://0.0.0.0:8888/hello/validateInfo")
                        .to("cxf:" +
                                "http://localhost:9000/hello/webservice" + // service address
                                "?wsdlURL=http://localhost:9000/hello/webservice?wsdl" +
                                "&defaultOperationName=validateInfo" +
//                                "&serviceClass=com.nmm.study.webservice.HelloService" +
                                "&dataFormat=PAYLOAD")
                        .convertBodyTo(String.class)
//                        .setHeader("operationName",constant("sayGood"))
                        .to("log:output");
            }
        });
    }
}
