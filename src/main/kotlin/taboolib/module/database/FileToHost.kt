package taboolib.module.database

import me.regadpole.config.DatabaseSource
import java.io.File

fun File.getHost(): HostSQLite {
    return HostSQLite(this)
}

fun DatabaseSource.getHost(name: String): HostSQL {
    return HostSQL(this)
}