package taboolib.module.database

import java.io.File

fun File.getHost(): HostSQLite {
    return HostSQLite(this)
}

//fun ConfigurationSection.getHost(name: String): HostSQL {
//    return HostSQL(getConfigurationSection(name) ?: Configuration.empty())
//}