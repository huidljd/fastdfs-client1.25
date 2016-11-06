package org.csource.fastdfs.conn.pool;

import java.net.InetSocketAddress;
import java.net.SocketException;

import org.apache.log4j.Logger;

/**
 * <B>说 明</B>:默认客户端与服务端的连接。
 * 
 * @author 作 者 名：陈 明<br/>
 *         E-mail ：chenming@vrvmail.com.cn
 * 
 * @version 版 本 号：1.0.0 <br/>
 *          创建时间：2016年11月1日 下午12:07:39
 */
public class DefaultConnectionSupplier extends AbstractConnectionSupplier {

	private static final Logger logger = Logger
			.getLogger(DefaultConnectionSupplier.class.getName());
	private volatile boolean isReturn;
	public DefaultConnectionSupplier(InetSocketAddress inetSockAddr) {
		super(inetSockAddr);
		try {
			socket.setKeepAlive(true);
		} catch (SocketException sx) {
			logger.error("Could not configure socket.", sx);
		}
	}

	@Override
	public boolean isReturnConn() {
		return isReturn;
	}

	@Override
	public void setReturnConn(boolean isReturnconn) {
		isReturn = isReturnconn;
	}
}
