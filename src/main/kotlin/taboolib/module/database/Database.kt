package taboolib.module.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import me.regadpole.config.DatabaseSource
import javax.sql.DataSource

object Database {

//    @Config("datasource.yml")
//    lateinit var settingsFile: Configuration
    lateinit var settingsFile: DatabaseSource

    /**
     * 创建一个关闭数据库连接的回调函数
     */
    fun prepareClose(func: Runnable) {
        Host.callbackClose += func
    }

    /**
     * 创建一个数据库连接池
     */
    fun createDataSource(host: Host<*>, hikariConfig: HikariConfig? = null): DataSource {
        return HikariDataSource(hikariConfig ?: createHikariConfig(host))
    }

    /**
     * 不使用配置文件创建一个数据库连接池
     */
    fun createDataSourceWithoutConfig(host: Host<*>): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = host.connectionUrl
        if (host is HostSQL) {
            config.username = host.user
            config.password = host.password
        } else {
            error("Unsupported host: $host")
        }
        return HikariDataSource(config)
    }

    /**
     * 创建一个 Hikari 配置
     */
    fun createHikariConfig(host: Host<*>): HikariConfig {
        val config = HikariConfig()
        config.jdbcUrl = host.connectionUrl
        when (host) {
            is HostSQL -> {
                config.driverClassName = settingsFile.getString("DefaultSettings.DriverClassName", "com.mysql.jdbc.Driver")
                config.username = host.user
                config.password = host.password
            }
            is HostSQLite -> {
                config.driverClassName = "org.sqlite.JDBC"
            }
            else -> {
                error("Unsupported host: $host")
            }
        }
        config.isAutoCommit = settingsFile.getBoolean("DefaultSettings.AutoCommit")
        config.minimumIdle = settingsFile.getInt("DefaultSettings.MinimumIdle")
        config.maximumPoolSize = settingsFile.getInt("DefaultSettings.MaximumPoolSize")
        config.validationTimeout = settingsFile.getLong("DefaultSettings.ValidationTimeout")
        config.connectionTimeout = settingsFile.getLong("DefaultSettings.ConnectionTimeout")
        config.idleTimeout = settingsFile.getLong("DefaultSettings.IdleTimeout")
        config.maxLifetime = settingsFile.getLong("DefaultSettings.MaxLifetime")
        if (settingsFile.contains("DefaultSettings.ConnectionTestQuery")) {
            config.connectionTestQuery = settingsFile.getString("DefaultSettings.ConnectionTestQuery", "")
        }
        return config
    }
}