package connectors

import config.ConfigLoader
import org.apache.spark.sql.{DataFrame, SparkSession}




object PostgresConnector {

  val db = ConfigLoader.pg_db


  val url = s"jdbc:postgresql://${db.pg_host}:${db.pg_port}/${db.pg_database}"

  val test_query = "(SELECT 1 AS test_conn)"

  def postgre_test_conn(spark:SparkSession):Unit = {
    try{
      spark
        .read
        .format("jdbc")
        .option("url", url)
        .option("query", test_query)
        .option("user", db.pg_user)
        .option("password", db.pg_password)
        .option("driver", "org.postgresql.Driver")
        .load()

      println(s"Conexão com o postgre estabelecida com sucesso! ${"=" * 100}")

    }
    catch {
      case e: Exception =>
        println(s"Erro na conexão com o Postgre! Erro: ${e}")
    }
  }


  def postgre_write(dataframe:DataFrame, table_name:String, save_mode:String, total_lines:Long):Unit = {
    dataframe
      .write
      .format("jdbc")
      .option("url", url)
      .option("dbtable", table_name)
      .option("user", db.pg_user)
      .option("password", db.pg_password)
      .option("driver", db.pg_driver)
      .mode(save_mode)
      .save()

    println(s"${total_lines} Linhas adicionadas ao Postgre com sucesso!")
  }
}


