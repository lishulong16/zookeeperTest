### zookeeper

```$xslt
cap 被证明
```

> 分佈式系統CAP理論:consistency availability partition tolerance
```
wget http://mirror.bit.edu.cn/apache/zookeeper/zookeeper-3.5.3-beta/zookeeper-3.5.3-beta.tar.gz


scp -r ./zookeeper-3.5.3-beta.tar.gz root@slave1:/home/wecash/dev/
scp -r ./zookeeper-3.5.3-beta.tar.gz root@slave2:/home/wecash/dev/
scp -r ./zookeeper-3.5.3-beta.tar.gz root@slave3:/home/wecash/dev/


tar -xvf zookeeper-3.5.3-beta.tar.gz 


```

```

[root@master conf]# cat zoo.cfg 
# The number of milliseconds of each tick
tickTime=2000
# The number of ticks that the initial 
# synchronization phase can take
initLimit=10
# The number of ticks that can pass between 
# sending a request and getting an acknowledgement
syncLimit=5
# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just 
# example sakes.
dataDir=/tmp/zookeeper
# the port at which the clients will connect
clientPort=2181
# the maximum number of client connections.
# increase this if you need to handle more clients
#maxClientCnxns=60
#
# Be sure to read the maintenance section of the 
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
#autopurge.snapRetainCount=3
# Purge task interval in hours
# Set to "0" to disable auto purge feature
#autopurge.purgeInterval=1
server.n=ip:2888:3888
[root@master conf]# 

```

一旦运行配置文件会出现下面的情况
```

[root@master conf]# vim zoo.cfg

initLimit=10
syncLimit=5
clientPort=2181
tickTime=2000
dataDir=/tmp/zookeeper
dynamicConfigFile=/home/wecash/dev/zookeeper/conf/zoo.cfg.dynamic.100000000
~         

[root@master conf]# vim zoo.cfg.dynamic.100000000 

server.1=192.168.6.8:2888:3888:participant
server.2=192.168.6.9:2888:3888:participant
server.3=192.168.6.10:2888:3888:participant
server.4=192.168.6.11:2888:3888:participant

```

一致 有leader 数据树
存储在内存中的 也是一种数据库？

客户端连接

```
[root@master bin]# ./zkCli.sh -server slave1:2181

```

写的时候要通知所有节点

读的时候会非常快。


应用场景：

> 配置一致

HA 高可用
持久化节点，临时的节点 主被切换

pub/sub 发布订阅

naming service

load banlance

分布式锁


---


```$xslt
[root@master bin]# ./zkCli.sh -server slave1:2181
Connecting to slave1:2181
2017-06-30 21:03:07,536 [myid:] - INFO  [main:Environment@109] - Client environment:zookeeper.version=3.5.3-beta-8ce24f9e675cbefffb8f21a47e06b42864475a60, built on 04/03/2017 16:19 GMT
2017-06-30 21:03:07,540 [myid:] - INFO  [main:Environment@109] - Client environment:host.name=master
2017-06-30 21:03:07,540 [myid:] - INFO  [main:Environment@109] - Client environment:java.version=1.8.0_131
2017-06-30 21:03:07,543 [myid:] - INFO  [main:Environment@109] - Client environment:java.vendor=Oracle Corporation
2017-06-30 21:03:07,543 [myid:] - INFO  [main:Environment@109] - Client environment:java.home=/usr/java/jdk1.8.0_131/jre
2017-06-30 21:03:07,543 [myid:] - INFO  [main:Environment@109] - Client environment:java.class.path=/home/wecash/dev/zookeeper/bin/../build/classes:/home/wecash/dev/zookeeper/bin/../build/lib/*.jar:/home/wecash/dev/zookeeper/bin/../lib/slf4j-log4j12-1.7.5.jar:/home/wecash/dev/zookeeper/bin/../lib/slf4j-api-1.7.5.jar:/home/wecash/dev/zookeeper/bin/../lib/netty-3.10.5.Final.jar:/home/wecash/dev/zookeeper/bin/../lib/log4j-1.2.17.jar:/home/wecash/dev/zookeeper/bin/../lib/jline-2.11.jar:/home/wecash/dev/zookeeper/bin/../lib/jetty-util-9.2.18.v20160721.jar:/home/wecash/dev/zookeeper/bin/../lib/jetty-servlet-9.2.18.v20160721.jar:/home/wecash/dev/zookeeper/bin/../lib/jetty-server-9.2.18.v20160721.jar:/home/wecash/dev/zookeeper/bin/../lib/jetty-security-9.2.18.v20160721.jar:/home/wecash/dev/zookeeper/bin/../lib/jetty-io-9.2.18.v20160721.jar:/home/wecash/dev/zookeeper/bin/../lib/jetty-http-9.2.18.v20160721.jar:/home/wecash/dev/zookeeper/bin/../lib/javax.servlet-api-3.1.0.jar:/home/wecash/dev/zookeeper/bin/../lib/jackson-mapper-asl-1.9.11.jar:/home/wecash/dev/zookeeper/bin/../lib/jackson-core-asl-1.9.11.jar:/home/wecash/dev/zookeeper/bin/../lib/commons-cli-1.2.jar:/home/wecash/dev/zookeeper/bin/../zookeeper-3.5.3-beta.jar:/home/wecash/dev/zookeeper/bin/../src/java/lib/*.jar:/home/wecash/dev/zookeeper/bin/../conf:
2017-06-30 21:03:07,544 [myid:] - INFO  [main:Environment@109] - Client environment:java.library.path=/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
2017-06-30 21:03:07,544 [myid:] - INFO  [main:Environment@109] - Client environment:java.io.tmpdir=/tmp
2017-06-30 21:03:07,544 [myid:] - INFO  [main:Environment@109] - Client environment:java.compiler=<NA>
2017-06-30 21:03:07,552 [myid:] - INFO  [main:Environment@109] - Client environment:os.name=Linux
2017-06-30 21:03:07,552 [myid:] - INFO  [main:Environment@109] - Client environment:os.arch=amd64
2017-06-30 21:03:07,552 [myid:] - INFO  [main:Environment@109] - Client environment:os.version=3.10.0-514.el7.x86_64
2017-06-30 21:03:07,552 [myid:] - INFO  [main:Environment@109] - Client environment:user.name=root
2017-06-30 21:03:07,552 [myid:] - INFO  [main:Environment@109] - Client environment:user.home=/root
2017-06-30 21:03:07,552 [myid:] - INFO  [main:Environment@109] - Client environment:user.dir=/home/wecash/dev/zookeeper/bin
2017-06-30 21:03:07,552 [myid:] - INFO  [main:Environment@109] - Client environment:os.memory.free=11MB
2017-06-30 21:03:07,554 [myid:] - INFO  [main:Environment@109] - Client environment:os.memory.max=247MB
2017-06-30 21:03:07,554 [myid:] - INFO  [main:Environment@109] - Client environment:os.memory.total=15MB
2017-06-30 21:03:07,562 [myid:] - INFO  [main:ZooKeeper@865] - Initiating client connection, connectString=slave1:2181 sessionTimeout=30000 watcher=org.apache.zookeeper.ZooKeeperMain$MyWatcher@2530c12
2017-06-30 21:03:07,579 [myid:] - INFO  [main:ClientCnxnSocket@236] - jute.maxbuffer value is 4194304 Bytes
2017-06-30 21:03:07,596 [myid:slave1:2181] - INFO  [main-SendThread(slave1:2181):ClientCnxn$SendThread@1113] - Opening socket connection to server slave1/192.168.6.9:2181. Will not attempt to authenticate using SASL (unknown error)
Welcome to ZooKeeper!
JLine support is enabled
2017-06-30 21:03:07,778 [myid:slave1:2181] - INFO  [main-SendThread(slave1:2181):ClientCnxn$SendThread@948] - Socket connection established, initiating session, client: /192.168.6.8:35198, server: slave1/192.168.6.9:2181
2017-06-30 21:03:07,804 [myid:slave1:2181] - INFO  [main-SendThread(slave1:2181):ClientCnxn$SendThread@1381] - Session establishment complete on server slave1/192.168.6.9:2181, sessionid = 0x200003e8d7e0002, negotiated timeout = 30000

WATCHER::

WatchedEvent state:SyncConnected type:None path:null
[zk: slave1:2181(CONNECTED) 0] 
[zk: slave1:2181(CONNECTED) 0] 
[zk: slave1:2181(CONNECTED) 0] ls /
[app, onepeice, zookeeper]
[zk: slave1:2181(CONNECTED) 1] ls /onepeice
[]
[zk: slave1:2181(CONNECTED) 2] get /onepeice
onepeice
[zk: slave1:2181(CONNECTED) 3] 
```