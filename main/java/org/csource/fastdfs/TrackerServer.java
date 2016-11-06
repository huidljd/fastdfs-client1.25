/**
 * Copyright (C) 2008 Happy Fish / YuQing
 *
 * FastDFS Java Client may be copied only under the terms of the GNU Lesser
 * General Public License (LGPL).
 * Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
 */

package org.csource.fastdfs;

import java.net.InetSocketAddress;

import org.csource.fastdfs.conn.pool.AbstractConnectionSupplier;

/**
 * Tracker Server Info
 * 
 * @author Happy Fish / YuQing
 * @version Version 1.11
 */
public class TrackerServer extends AbstractConnectionSupplier{
	public TrackerServer(InetSocketAddress inetSockAddr) {
		super(inetSockAddr);
	}
	@Override
	public boolean isReturnConn() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setReturnConn(boolean isReturnconn) {
		throw new UnsupportedOperationException();
	}
}
