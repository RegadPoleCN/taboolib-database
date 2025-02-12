package me.regadpole.config

import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.io.File

class DatabaseSource() {
    private lateinit var config: ConfigurationNode

    constructor(node: ConfigurationNode) : this() {
        this.config = node
    }

    constructor(file: File) : this() {
        this.config = YamlConfigurationLoader.builder().file(file).build().load()
    }

    /*method to obtain a string list from the yml file */
    fun getStringList( nodePath: String?): List<String?> {
        val resultList: MutableList<String?> = ArrayList()
        val subNode = config.node(nodePath)
        if (subNode.isList) {
            val list = subNode.childrenList()
            for (item in list) {
                resultList.add(item.string)
            }
        }
        return resultList
    }

    /*method to obtain a long list from the yml file */
    fun getLongList( nodePath: String?): List<Long> {
        val resultList: MutableList<Long> = ArrayList()
        val subNode = config.node(nodePath)
        if (subNode.isList) {
            val list = subNode.childrenList()
            for (item in list) {
                resultList.add(item.long)
            }
        }
        return resultList
    }

    /*method to get boolean value from yml file*/
    fun getBoolean( nodePath: String?): Boolean {
        val aBoolean = config.node(nodePath!!.split(".")).boolean
        return aBoolean
    }

    /*method to get integer value from yml file*/
    fun getInt( nodePath: String?): Int {
        val integer = config.node(nodePath!!.split(".")).int
        return integer
    }

    /*method to get string from yml file*/
    fun getString( nodePath: String?, default:String): String {
        val string = config.node(nodePath!!.split(".")).string
        return string?:default
    }

    /*method to get long value from yml file*/
    fun getLong( nodePath: String?): Long {
        val long = config.node(nodePath!!.split(".")).long
        return long
    }

    fun contains(nodePath: String?): Boolean {
        return !config.node(nodePath!!.split(".")).isNull
    }
}