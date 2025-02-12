package me.regadpole.config

import top.alazeprt.aconfiguration.file.FileConfiguration
import top.alazeprt.aconfiguration.file.YamlConfiguration
import java.io.File

class DatabaseSource() {
    private lateinit var config: FileConfiguration

    constructor(config: FileConfiguration) : this() {
        this.config = config
    }

    constructor(file: File) : this() {
        this.config = YamlConfiguration.loadConfiguration(file)
    }

    /*method to obtain a string list from the yml file */
    fun getStringList( nodePath: String?): List<String?> {
        return config.getStringList(nodePath)
    }

    /*method to obtain a long list from the yml file */
    fun getLongList( nodePath: String?): List<Long> {
        return config.getLongList(nodePath)
    }

    /*method to get boolean value from yml file*/
    fun getBoolean( nodePath: String?): Boolean {
        return config.getBoolean(nodePath)
    }

    /*method to get integer value from yml file*/
    fun getInt( nodePath: String?): Int {
        return config.getInt(nodePath)
    }

    /*method to get string from yml file*/
    fun getString( nodePath: String?, default:String): String {
        return config.getString(nodePath, default)
    }

    /*method to get long value from yml file*/
    fun getLong( nodePath: String?): Long {
        return config.getLong(nodePath)
    }

    fun contains(nodePath: String?): Boolean {
        return config.contains(nodePath)
    }
}