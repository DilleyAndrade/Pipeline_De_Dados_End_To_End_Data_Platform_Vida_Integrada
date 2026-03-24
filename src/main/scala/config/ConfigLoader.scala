package config
import com.typesafe.config.ConfigFactory


case class DbConfig( pg_host: String, pg_port: String, pg_user: String, pg_password: String, pg_database: String, pg_driver: String )

object ConfigLoader {

  private val config = ConfigFactory.load()

  val pg_db: DbConfig = DbConfig(
    config.getString("db_pg.pg_host_conf"),
    config.getString("db_pg.pg_port_conf"),
    config.getString("db_pg.pg_user_conf"),
    config.getString("db_pg.pg_password_conf"),
    config.getString("db_pg.pg_database_conf"),
    config.getString("db_pg.pg_driver_conf"),
  )
}