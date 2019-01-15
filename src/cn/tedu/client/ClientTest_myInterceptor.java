package cn.tedu.client;

import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.message.Message;

import cn.tedu.myInterceptor.AddUserInterceptor;
import cn.tedu.ws.HelloWS;
import cn.tedu.ws.HelloWSImplService;
/**
 * 演示CXF的自己定义的拦截器-----客户端
 * @author HY
 *
 */
public class ClientTest_myInterceptor {
	public static void main(String[] args) {
		HelloWSImplService factory = new HelloWSImplService();
		HelloWS helloWS = factory.getHelloWSImplPort();
		
		//发送请求的客户端对象
		Client client = ClientProxy.getClient(helloWS);
		/**
		 * 客户端的自己定义的出拦截器
		 */
		List<Interceptor<? extends Message>> outInterceptors = client.getOutInterceptors();
		outInterceptors.add(new AddUserInterceptor("zhangsan", "123456"));
		
		String name = "CXF";
		String result = helloWS.sayHello(name);
		System.out.println(result);
		
		Integer age = 28;
		result = helloWS.introduce(name, age);
		System.out.println(result);
		
	}
}
