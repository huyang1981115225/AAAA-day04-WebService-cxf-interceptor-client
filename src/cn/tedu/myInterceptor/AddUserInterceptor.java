package cn.tedu.myInterceptor;

import java.util.List;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.xml.utils.DOMHelper;
import javax.xml.namespace.QName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 演示自定义拦截器
 * 
 * @author HY
 *
 */
public class AddUserInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private String name;
	private String password;

	public AddUserInterceptor(String name, String password) {
		super(Phase.PRE_PROTOCOL);// 准备协议化时拦截
		this.name = name;
		this.password = password;
	}
	
	/**
	 * <Envelope>
	 * 		<head>
	 * 			<atguigu>
	 * 				<name>zhangsan</name>
	 * 				<password>123456</password>
	 * 			</atguigu>
	 * 			<atguigu2>
	 * 				<name>lisi</name>
	 * 				<password>123456</password>
	 * 			</atguigu2>
	 * 		</head>
	 * 		<body>
	 * 			<sayHello>
	 * 				<arg0>BOB</arg0>
	 * 			</sayHello>
	 * 		</body>
	 * </Envelope>
	 */
	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		
		 List<Header> headers = msg.getHeaders();
		/**
		 * 			<atguigu>
		 * 				<name>zhangsan</name>
		 * 				<password>123456</password>
		 * 			</atguigu>
		 */
		 
		 Document document = DOMHelper.createDocument();
		 Element rootEle = document.createElement("atguigu");
		 Element nameEle = document.createElement("name");
		 nameEle.setTextContent(name);
		 rootEle.appendChild(nameEle);
		 
		 Element passwordEle = document.createElement("password");
		 passwordEle.setTextContent(password);
		 rootEle.appendChild(passwordEle);
		 
		 headers.add(new Header(new QName("atguigu"),rootEle));
		 
		 System.out.println("自己定义的拦截器client handleMessage()....");
	}
}
