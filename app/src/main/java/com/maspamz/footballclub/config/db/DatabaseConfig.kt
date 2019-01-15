package com.maspamz.footballclub.config.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.maspamz.footballclub.config.data.Favorite
import com.maspamz.footballclub.config.data.FavoriteClub
import org.jetbrains.anko.db.*

/**
 * Created by Maspamz on 12/09/2018.
 *
 */

class DatabaseConfig(ctx:Context):ManagedSQLiteOpenHelper(ctx, "db_favorite.db", null, 1){

    //TODO 1 Declaration variable for synchronized
    companion object {
        private var instance: DatabaseConfig? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseConfig {
            if(instance ==null){
                instance = DatabaseConfig(ctx.applicationContext)
            }
            return instance as DatabaseConfig
        }
    }

    //TODO 2 For making Table tb_favorite and tb_favorite_club
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.tb_favorite, true,
                Favorite.id to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.id_event to TEXT,
                Favorite.date_event to TEXT,
                Favorite.id_club1 to TEXT,
                Favorite.name_club1 to TEXT,
                Favorite.score_club1 to TEXT,
                Favorite.id_club2 to TEXT,
                Favorite.name_club2 to TEXT,
                Favorite.score_club2 to TEXT)

        db.createTable(FavoriteClub.tb_favorite_club, true,
                "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteClub.team_id to TEXT,
                FavoriteClub.team_name to TEXT,
                FavoriteClub.team_badge to TEXT,
                FavoriteClub.team_description to TEXT)
    }

    //TODO 3 Function for remove Table
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.dropTable(Favorite.tb_favorite,true)

        db.dropTable(FavoriteClub.tb_favorite_club,true)
    }

}

//TODO 4 Access for Context
val Context.database: DatabaseConfig
    get() = DatabaseConfig.getInstance(applicationContext)