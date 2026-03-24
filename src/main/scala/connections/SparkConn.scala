package connections
import org.apache.spark.sql.SparkSession

object SparkConn {
  def spark_conn(conName:String, connMaster:String): SparkSession = {
    SparkSession
      .builder()
      .appName(conName)
      .master(connMaster)
      .getOrCreate()
  }
}
