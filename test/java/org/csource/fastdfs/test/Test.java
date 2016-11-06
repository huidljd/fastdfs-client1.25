/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
**/

package org.csource.fastdfs.test;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.csource.common.*;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.conn.pool.ConnectionSupplier;


/**
* client test
* @author Happy Fish / YuQing
* @version Version 1.18
*/
public class Test
{
	private static File FASTDFS_FILE = new File("src/test/resources/fastdfs.properties");	
	private static File LOCAL_FILE= new File("src/test/resources/fdfs_test_file.txt");	
	/**
	* entry point
	* @param args comand arguments
	*     <ul><li>args[0]: config filename</li></ul>
	*     <ul><li>args[1]: local filename to upload</li></ul>
	*/
  public static void main(String args[])
  {
  	
  	System.out.println("java.version=" + System.getProperty("java.version"));
  	  
  	String local_filename = LOCAL_FILE.getAbsolutePath();
  	
  	try
  	{
  		ClientGlobal.init(FASTDFS_FILE.getAbsolutePath());
  		System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
  		System.out.println("charset=" + ClientGlobal.g_charset);
 
        TrackerClient tracker = new TrackerClient();
        ConnectionSupplier connection = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(connection, storageServer);
        
        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("fileName", local_filename);
        String fileId = client.upload_file1(local_filename, null, metaList);
        System.out.println("upload success. file id is: " + fileId);
        
        int i = 0;
        while (i++ < 10) {
        	byte[] result = client.download_file1(fileId);
        	System.out.println(i + ", download result is: " + result.length);
        }
        TimeUnit.SECONDS.sleep(20);
  	}
  	catch(Exception ex)
  	{
  		ex.printStackTrace();
  	}
  }
}
