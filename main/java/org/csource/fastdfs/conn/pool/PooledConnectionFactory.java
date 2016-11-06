package org.csource.fastdfs.conn.pool; 

import java.net.InetSocketAddress;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/** 
 *   <B>说       明</B>:表示客户端与fastdfs服务端的Socket连接池，
 *   			  定义了被池化的Socket的创建，初始化，激活，钝化以及销毁功能。
 *
 * @author  作  者  名：陈  明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：1.0.0 <br/>
 *          创建时间：2016年11月1日 下午2:36:03 
 */
public class PooledConnectionFactory extends BaseKeyedPooledObjectFactory<InetSocketAddress, ConnectionSupplier> {

	@Override
	public ConnectionSupplier create(InetSocketAddress inetSocketAddress) throws Exception {
		return new DefaultConnectionSupplier(inetSocketAddress);
	}

	@Override
	public PooledObject<ConnectionSupplier> wrap(ConnectionSupplier connectionSupplier) {
		return new DefaultPooledObject<ConnectionSupplier>(connectionSupplier);
	}

	@Override
	public void destroyObject(InetSocketAddress key,
			PooledObject<ConnectionSupplier> p) throws Exception {
		p.getObject().close();
	}

	@Override
	public boolean validateObject(InetSocketAddress key,
			PooledObject<ConnectionSupplier> p) {
		return p.getObject().isValid();
	}
	
	

}
 