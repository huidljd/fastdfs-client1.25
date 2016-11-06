package org.csource.fastdfs.conn.pool; 

import java.net.InetSocketAddress;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/** 
 *   <B>说       明</B>:表示fastdfs连接池对象。
 *
 * @author  作  者  名：陈  明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：1.0.0 <br/>
 *          创建时间：2016年11月1日 下午2:52:10 
 */
public class FastDFSConnectionPool extends GenericKeyedObjectPool<InetSocketAddress, ConnectionSupplier>{

	public FastDFSConnectionPool(
			KeyedPooledObjectFactory<InetSocketAddress, ConnectionSupplier> factory) {
		super(factory);
	}

	public FastDFSConnectionPool(
			KeyedPooledObjectFactory<InetSocketAddress, ConnectionSupplier> factory,
			GenericKeyedObjectPoolConfig config) {
		super(factory, config);
	}

	
}
 