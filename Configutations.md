

Table of contents
===================
[TOC]

Engine Configurations 
===================

Two sets of configurations are set for **GMQL** Engine :
	
> - Repository configurations, set in <i class="icon-cog"></i> [repository.xml](https://github.com/DEIB-GECO/GMQL/blob/master/GMQL-Repository/src/main/resources/repository.xml) file.  
> - Executors configurations  set in <i class="icon-cog"></i> [executor.xml](https://github.com/DEIB-GECO/GMQL/blob/master/GMQL-SManager/src/main/resources/executor.xml) file. 

An addition configuration is required if you are using the remote setup.
 > - Remote configurations  set in <i class="icon-cog"></i> [remote.xml](https://github.com/DEIB-GECO/GMQL/blob/master/WSC/src/main/resources/remote.xml) file. 
 > 
Repository Configurations: 
-------------------------------------


Property Name  | Default  | Meaning
------------------ | ----------- | ----------
GMQL_REPO_TYPE|LOCAL| The type of repository considered for this installation. If the repository is set to **LOCAL**, the data will be all stored on the Local file system of the application node. If the mode is set to **HDFS**, the samples will be stored on Hadoop Distributed File System (HDFS). If the mode is set to **REMOTE**, the samples will be stored on Hadoop Distributed File System (HDFS) of a remote cluster. When the mode is set to REMOTE, LAUNCHER_MODE must be set to REMOTE_CLUSTER. In all cases, the configuration of **GMQL_HOME** should be set, since it will hold the datasets description files. `Mandatory configuration.`
GMQL_LOCAL_HOME |/tmp/repo/| Sets the home directory <i class="icon-folder-open"></i> for **GMQL** repository on the local file system of the Application server. Home directory of **GMQL** contains: the description of GMQL datasets<i class="icon-folder-open"></i> , the metadata for the datasets <i class="icon-folder-open"></i> , and the history of the execution. In case the **repository mode** was set to **Local**, the regions generated fromt he execution are stored in **regions** <i class="icon-folder-open"></i>  folder under the repository home directory. `Mandatory configuration.` 
GMQL_DFS_HOME | /user/repo/ | The home folder <i class="icon-folder-open"></i> of the  repository on HDFS. In case of **repository mode** set to **YARN** execution, the sample data are stored in the regions folder in HDFS under the repository home folder. `Considered only when GMQL_REPO is set to HDFS.`
[HADOOP_HOME](http://hadoop.apache.org/)|/usr/local/hadoop/|The location of Hadoop installation. `Considered only when GMQL_REPO is set to HDFS.`
HADOOP_CONF_DIR|/usr/local/hadoop/etc/hadoop/|The location of Hadoop configuration folder <i class="icon-folder-open"></i>. `Considered only when GMQL_REPO is set to HDFS.`
GMQL_CONF_DIR|$GMQL_INSTALLATION/conf|The folder location <i class="icon-folder-open"></i> of GMQL configuration files. 
REMOTE_HDFS_NAMESPACE | hdfs://your-host:8020/ | Cluster host and port. This is required only if you are running on remote setup.

Launcher Configurations: 
-------------------------------------

Property Name  | Default  | Meaning
------------------ | ----------- | ----------
LAUNCHER_MODE|LOCAL| The launcher execution mode, can be set to: **LOCAL**, **CLUSTER**, or **REMOTE_CLUSTER** .
SPARK_HOME | /usr/local/spark/ | Spark home directory <i class="icon-folder-open"></i>. `Considered only when LAUNCHER_MODE is set to CLUSTER or REMOTE_CLUSTER.`
CLI_JAR_NAME | GMQL-Cli-2.0-jar-with-dependencies.jar | Name of the fat Jar of Command Line Interface (CLI), this is used for the **cluster** launcher mode. `Considered only when LAUNCHER_MODE is set to CLUSTER or REMOTE_CLUSTER.`
CLI_CLASS|it.polimi.genomics.cli.GMQLExecuteCommand| The class path of the Command Line Interface of GMQL. Can be changed only by the developers, better to leave it on the defaults.
LIB_DIR_LOCAL | $GMQL_INSTALLATION/lib/| Location of the library folder, containing the repository jar, and the executable jars of GMQL. The default is inside the package installation folder.
LIB_DIR_HDFS | /user/repo/lib/ | Location on HDFS of the library folder, where the cli jar file is stored. `Considered only when LAUNCHER_MODE is set to CLUSTER or REMOTE_CLUSTER.`


Remote Configurations: 
-------------------------------------

Property Name  | Meaning | Example
------------------ | ----------- | ---------
CLI_JAR_PATH | HDFS path of the CLI interface jar. This jar will be submitted to Spark through Livy. | `/user/hdfs-user/GMQL-Cli.jar`.
CLI_CLASS | The class path of the Command Line Interface of GMQL. Can be changed only by the developers, better to leave it on the defaults. | it.polimi.genomics.cli.GMQLExecuteCommand
KNOX_GATEWAY | Knox Gateway URL.  This URL must be accessible from the machine instantiating the Remote Launcher. | `https:cluster-host:8443/gateway/default`
KNOX_SERVICE_PATH | This string appendend to KNOX_GATEWAY forms the rest API URL. | /webhdfs/v1/
KNOX_USERNAME | Knox service username | your-username
KNOX_PASSWORD | Knox service password | \*******
LIVY_BASE_URL | Livy REST API base url | `http://your-host:8998/batches`

> **Note:**

> - GMQL Engine can be connected to several repositories types; such as Local repository, [HDFS](https://hadoop.apache.org/docs/r1.2.1/hdfs_design.html) repository, or Remote repository. Currently, only one repository type can be set for each installation.
> - The genomic engine can run on several types of implementations. The supported implementations are: [Apache Spark](http://spark.apache.org/), [Apache Flink](https://flink.apache.org/), and [SciDB](http://www.paradigm4.com/). the default one is Spark.
> - Apache Spark, and Flink can have one of three Launchers: LOCAL, CLUSTER, and REMOTE_CLUSTER. The remote cluster is executed though calls through web services (enabled for Spark though [Livy](http://livy.io/)).
> - When running on remote setup GMQL_REPO_TYPE, LAUNCHER_MODE and `remote.xml` must be set only on the machine instantiating the launcher. The cluster should be set in CLUSTER mode.
