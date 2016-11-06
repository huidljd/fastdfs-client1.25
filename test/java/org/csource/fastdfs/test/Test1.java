
package org.csource.fastdfs.test;

import java.io.*;
import java.net.*;

import org.csource.common.*;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.conn.pool.ConnectionSupplier;

public class Test1
{
private static File FASTDFS_FILE = new File("src/test/resources/fastdfs.properties");
  public static void main(String args[])
  {
  	try
  	{
		ClientGlobal.init(FASTDFS_FILE.getAbsolutePath());
		System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
		System.out.println("charset=" + ClientGlobal.g_charset);
  		
		TrackerGroup tg = new TrackerGroup(new InetSocketAddress[]{new InetSocketAddress("192.168.0.23", 22122)});
		TrackerClient tc = new TrackerClient(tg);
		
		ConnectionSupplier connection = tc.getConnection();
		if (connection == null)
		{
			System.out.println("getConnection return null");
			return;
		}

		StorageServer storageServer = tc.getStoreStorage(connection);
		if (storageServer == null)
		{
			System.out.println("getStoreStorage return null");
		}
		
		StorageClient1 sc1 = new StorageClient1(connection, storageServer);
		
		NameValuePair[] meta_list = null;  //new NameValuePair[0];
		String item = "c:/windows/system32/notepad.exe";
		String fileid = sc1.upload_file1(item, "exe", meta_list); //此行异常
		
		System.out.println("Upload local file "+item+" ok, fileid="+fileid);
		/* for test only */
  		System.out.println("active test to storage server: " + ProtoCommon.activeTest(storageServer.getInputStream(),storageServer.getOutputStream()));
  		
  		storageServer.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	}
}
