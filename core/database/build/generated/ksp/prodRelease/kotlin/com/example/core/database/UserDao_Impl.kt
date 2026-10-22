package com.example.core.database

import androidx.paging.PagingSource
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.coroutines.createFlow
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUserEntity: EntityInsertAdapter<UserEntity>

  private val __updateAdapterOfUserEntity: EntityDeleteOrUpdateAdapter<UserEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfUserEntity = object : EntityInsertAdapter<UserEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `users` (`id`,`name`,`url`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: UserEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.url)
      }
    }
    this.__updateAdapterOfUserEntity = object : EntityDeleteOrUpdateAdapter<UserEntity>() {
      protected override fun createQuery(): String = "UPDATE OR REPLACE `users` SET `id` = ?,`name` = ?,`url` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: UserEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.url)
        statement.bindText(4, entity.id)
      }
    }
  }

  public override suspend fun insertAll(users: List<UserEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfUserEntity.insert(_connection, users)
  }

  public override suspend fun insertUser(user: UserEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfUserEntity.handle(_connection, user)
  }

  public override fun observeUsers(): Flow<List<UserEntity>> {
    val _sql: String = "SELECT * FROM users"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _result: MutableList<UserEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: UserEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          _item = UserEntity(_tmpId,_tmpName,_tmpUrl)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun pagingSource(): PagingSource<Int, UserEntity> {
    val _sql: String = "SELECT * FROM users ORDER BY id ASC"
    val _rawQuery: RoomRawQuery = RoomRawQuery(_sql)
    return object : LimitOffsetPagingSource<UserEntity>(_rawQuery, __db, "users") {
      protected override suspend fun convertRows(limitOffsetQuery: RoomRawQuery, itemCount: Int): List<UserEntity> = performSuspending(__db, true, false) { _connection ->
        val _stmt: SQLiteStatement = _connection.prepare(limitOffsetQuery.sql)
        limitOffsetQuery.getBindingFunction().invoke(_stmt)
        try {
          val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
          val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
          val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
          val _result: MutableList<UserEntity> = mutableListOf()
          while (_stmt.step()) {
            val _item: UserEntity
            val _tmpId: String
            _tmpId = _stmt.getText(_columnIndexOfId)
            val _tmpName: String
            _tmpName = _stmt.getText(_columnIndexOfName)
            val _tmpUrl: String
            _tmpUrl = _stmt.getText(_columnIndexOfUrl)
            _item = UserEntity(_tmpId,_tmpName,_tmpUrl)
            _result.add(_item)
          }
          _result
        } finally {
          _stmt.close()
        }
      }
    }
  }

  public override fun observeUser(id: String): Flow<UserEntity?> {
    val _sql: String = "SELECT * FROM users WHERE id = ?"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          _result = UserEntity(_tmpId,_tmpName,_tmpUrl)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserById(id: String): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _result: UserEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          _result = UserEntity(_tmpId,_tmpName,_tmpUrl)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearAll() {
    val _sql: String = "DELETE FROM users"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
