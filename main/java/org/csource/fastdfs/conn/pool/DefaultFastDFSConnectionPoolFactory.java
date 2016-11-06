package org.csource.fastdfs.conn.pool; 

import java.net.InetSocketAddress;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

/** 
 *   <B>说       明</B>:表示默认fastdfs连接池工厂类。
 *
 * @author  作  者  名：陈  明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：1.0.0 <br/>
 *          创建时间：2016年11月1日 下午3:10:57 
 */
public final class DefaultFastDFSConnectionPoolFactory {
	public static final DefaultFastDFSConnectionPoolFactory INSTANCE = new DefaultFastDFSConnectionPoolFactory();
	private final GenericKeyedObjectPool<InetSocketAddress, ConnectionSupplier> fastDFSConnectionPool;
	private DefaultFastDFSConnectionPoolFactory() {
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		ConnectionPoolConfig connectionPoolConfig = new ConnectionPoolConfig();
		fastDFSConnectionPool = new FastDFSConnectionPool(pooledConnectionFactory,connectionPoolConfig);
	}
	
	public GenericKeyedObjectPool<InetSocketAddress, ConnectionSupplier> getFastDFSConnectionPool() {
		return fastDFSConnectionPool;
	}
	
	
}
 