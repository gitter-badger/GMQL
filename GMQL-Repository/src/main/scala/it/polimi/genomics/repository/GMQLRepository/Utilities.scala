package it.polimi.genomics.repository.GMQLRepository

/**
  * Created by abdulrahman on 12/04/16.
  */

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.nio.file.Paths
import java.util.concurrent.ExecutionException

/**
  *
  * @author abdulrahman kaitoua <abdulrahman dot kaitoua at polimi dot it>
  */
object Utilities {
  private val logger: Logger = LoggerFactory.getLogger(Utilities.getClass)
  var USERNAME: String = "abdulrahman"
  val HDFS: String = "MAPREDUCE"
  val LOCAL: String = "LOCAL"

   var RepoDir: String = null
   var HDFSRepoDir: String = null
   var MODE: String = null
   var CoreConfigurationFiles: String = null
   var HDFSConfigurationFiles: String = null
   var GMQLHOME: String = null

  def apply() {
    val gmql: String = System.getenv("GMQL_HOME")
    val user: String = System.getenv("USER")
    val dfs: String = System.getenv("GMQL_DFS_HOME")
    val exec: String = System.getenv("GMQL_EXEC")



    if (gmql == null) GMQLHOME = "/Users/abdulrahman/gmql_repository"
    else GMQLHOME = gmql

    if (user == null) Utilities.USERNAME = "gmql_user"
    else Utilities.USERNAME = user

    if (dfs == null) this.HDFSRepoDir = "/user/akaitoua/gmql_repo/"
    else this.HDFSRepoDir = dfs

    if (exec == null) {
      logger.error("Environment variable GMQL_EXEC is empty... execution set to LOCAL")
      this.MODE = Utilities.HDFS
    } else this.MODE = exec.toUpperCase

    RepoDir = GMQLHOME + "/data/"


    val coreConf: String = System.getenv("HADOOP_CONF_DIR")
    val hdfsConf: String = System.getenv("HADOOP_CONF_DIR")
    CoreConfigurationFiles = (if (coreConf == null) "/usr/local/Cellar/hadoop/2.7.2/libexec/etc/hadoop/" else coreConf) + "/core-site.xml"
    HDFSConfigurationFiles = (if (hdfsConf == null) "/usr/local/Cellar/hadoop/2.7.2/libexec/etc/hadoop/" else hdfsConf) + "/hdfs-site.xml"
    logger.debug("GMQLHOME = " + gmql + "," + GMQLHOME)
    logger.debug("HDFS Repository = " + dfs + "," + HDFSRepoDir)
    logger.debug("MODE = " + exec + "," + MODE)
    logger.debug("user = " + user + "," + Utilities.USERNAME)
  }

  def deleteFromLocalFSRecursive(dir: File) {
    var files: Array[File] = null
    if (dir.isDirectory) {
      files = dir.listFiles
      if (!(files == null)) {
        for (file <- files) {
          deleteFromLocalFSRecursive(file)
        }
      }
      dir.delete
    }
    else dir.delete
  }
}
