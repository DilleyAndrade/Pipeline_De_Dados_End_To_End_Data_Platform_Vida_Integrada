package batch

import connections.SparkConn
import connectors.PostgresConnector
import utils.CsvUtils

object BatchFirstInjestion {
  def main(args:Array[String]):Unit = {

    def postgre_injestion():Unit = {

      println(s"Iniciando injestão no banco Postgres ${"=" * 100}")

      println(s"Criando SparkSession ${"=" * 100}")
      val spark = SparkConn.spark_conn("pg_injestion", "local[*]")


      val csv_path = "src/data/first_injestion/erp_postgre.csv"

      println(s"Lendo arquivo CSV ${"=" * 100}")
      val df = CsvUtils.read_csv(spark, csv_path)
      df.count()
      df.show(3)

      println(s"Iniciando teste de conexão postgres ${"=" * 100}")
      val test_pg = PostgresConnector.postgre_test_conn(spark)
      println(test_pg)

      println(s"Carregando dados no Banco Postgres ${"=" * 100}")
      PostgresConnector.postgre_write(df, "transaction", "overwrite", df.count())
    }

    postgre_injestion()

  }
}
