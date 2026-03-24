package utils

import org.apache.spark.sql.{DataFrame, SparkSession}

object CsvUtils {
  def read_csv(spark: SparkSession, file_path:String):DataFrame = {
    spark
      .read
      .format("csv")
      .option("header", "true")
      .option("delimiter", ",")
      .option("encoding", "UTF-8")
      .load(file_path)
  }
}
