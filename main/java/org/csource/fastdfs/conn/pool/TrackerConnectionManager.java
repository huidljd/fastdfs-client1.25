package org.csource.fastdfs.conn.pool; 

import java.net.InetSocketAddress;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.log4j.Logger;

/** 
 *   <B>说       明</B>:表示fastdfs的TrackerServer的连接管理类。
 *
 * @author  作  者  名：陈  明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：1.0.0 <br/>
 *          创建时间：2016年11月2日 下午2:38:24 
 */
public final class TrackerConnectionManager {
	private static final Logger logger = Logger.getLogger(TrackerConnectionManager.class.getName());
	private static final GenericKeyedObjectPool<InetSocketAddress, ConnectionSupplier> fastDFSConnectionPool = DefaultFastDFSConnectionPoolFactory.INSTANCE.getFastDFSConnectionPool();
	public static final TrackerConnectionManager INSTANCE = new TrackerConnectionManager();
	private TrackerConnectionManager(){}
	
	public void returnConnection(InetSocketAddress inetSockAddr,ConnectionSupplier connectionSupplier) {
		if (!connectionSupplier.isReturnConn()) {
			fastDFSConnectionPool.returnObject(inetSockAddr, connectionSupplier);
			connectionSupplier.setReturnConn(true);
		}
	}
	
	public void invalidateObject(InetSocketAddress inetSockAddr,ConnectionSupplier connectionSupplier) {
		try {
			fastDFSConnectionPool.invalidateObject(inetSockAddr, connectionSupplier);
		} catch (Exception e) {
			logger.error("fastdfs connection invalidate error:-->"+inetSockAddr, e);
		}
	}
	
	public ConnectionSupplier getNewConnection(InetSocketAddress inetSockAddr) {
		ConnectionSupplier connectionSupplier = null;
		try {
			int retryCount = 3;
			boolean valid = false;
			do {
				connectionSupplier = fastDFSConnectionPool.borrowObject(inetSockAddr);
				connectionSupplier.setReturnConn(false);
				valid = connectionSupplier.isValid();
				if (valid) {
					return connectionSupplier;
				} else {
					connectionSupplier.close();
				}
				retryCount--;
			} while (!valid && retryCount > 0);
		} catch (Exception e) {
			logger.error("borrow fastdfs connection error:-->"+inetSockAddr, e);
		}
		return connectionSupplier;
	}
	
	public void dumpPoolInfo(InetSocketAddress inetSockAddr) {
		if (logger.isDebugEnabled()) {
			int numActive = fastDFSConnectionPool.getNumActive(inetSockAddr);
			logger.debug("active connection count :-->{"+numActive+"}");
			int numIdle = fastDFSConnectionPool.getNumIdle(inetSockAddr);
			logger.debug("idle connection count :-->{"+numIdle+"}");
			long borrowedCount = fastDFSConnectionPool.getBorrowedCount();
			logger.debug("borrowed connection count :-->{"+borrowedCount+"}");
			long returnedCount = fastDFSConnectionPool.getReturnedCount();
			logger.debug("return connection count :-->{"+returnedCount+"}");
			long destroyedCount = fastDFSConnectionPool.getDestroyedCount();
			logger.debug("destroyed connection count :-->{"+destroyedCount+"}");
			
		}
	}
}
 