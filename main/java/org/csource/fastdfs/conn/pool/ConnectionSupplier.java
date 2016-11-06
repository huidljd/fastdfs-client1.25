package org.csource.fastdfs.conn.pool; 

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/** 
 *   <B>说       明</B>:表示一个客户端与服务端的连接供应商。
 *
 * @author  作  者  名：陈  明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：1.0.0 <br/>
 *          创建时间：2016年11月1日 上午11:36:24 
 */
public interface ConnectionSupplier {
	void close();
	boolean isConnected();
	boolean isValid();
	InetSocketAddress getInetSocketAddress();
	OutputStream getOutputStream() throws IOException;
	InputStream getInputStream() throws IOException; 
	boolean isReturnConn();
	void setReturnConn(boolean isReturnconn);
}
 