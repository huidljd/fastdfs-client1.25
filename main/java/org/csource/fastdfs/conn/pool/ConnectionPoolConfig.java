package org.csource.fastdfs.conn.pool; 

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/** 
 *   <B>说       明</B>:表示连接池的配置。
 *
 * @author  作  者  名：陈  明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：1.0.0 <br/>
 *          创建时间：2016年11月1日 下午2:56:41 
 */
public class ConnectionPoolConfig extends GenericKeyedObjectPoolConfig {

	public ConnectionPoolConfig() {
		//从池中借出的对象的最大数目500
		setMaxTotal(500); 
		//每个Key的最大
		setMaxTotalPerKey(20);
		//休眠时间超时的时候是否test一下先
        setTestWhileIdle(true);
        setBlockWhenExhausted(true);
        //-1表示获取不到永远等待。
        //获取池对象等待10秒
        setMaxWaitMillis(10000);
        // 视休眠时间超过了180秒的对象为过期
        setMinEvictableIdleTimeMillis(180000); 
        // 每过60秒进行一次后台对象清理的行动
        setTimeBetweenEvictionRunsMillis(60000); 
        setNumTestsPerEvictionRun(-1);
	}

	
}
 