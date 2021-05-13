package com.androidy.hungerbox.data

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

object HungerBoxDb {

    lateinit var database: MongoDatabase

    fun init(dbName: String){
        database = KMongo.createClient().getDatabase(dbName)
    }

}