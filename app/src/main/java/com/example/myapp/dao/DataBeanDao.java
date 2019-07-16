package com.example.myapp.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.myapp.weichaData.DataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATA_BEAN".
*/
public class DataBeanDao extends AbstractDao<DataBean, Long> {

    public static final String TABLENAME = "DATA_BEAN";

    /**
     * Properties of entity DataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Idd = new Property(0, Long.class, "idd", true, "_id");
        public final static Property CourseId = new Property(1, int.class, "courseId", false, "COURSE_ID");
        public final static Property Id = new Property(2, int.class, "id", false, "ID");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Order = new Property(4, int.class, "order", false, "ORDER");
        public final static Property ParentChapterId = new Property(5, int.class, "parentChapterId", false, "PARENT_CHAPTER_ID");
        public final static Property UserControlSetTop = new Property(6, boolean.class, "userControlSetTop", false, "USER_CONTROL_SET_TOP");
        public final static Property Visible = new Property(7, int.class, "visible", false, "VISIBLE");
    }


    public DataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: idd
                "\"COURSE_ID\" INTEGER NOT NULL ," + // 1: courseId
                "\"ID\" INTEGER NOT NULL ," + // 2: id
                "\"NAME\" TEXT," + // 3: name
                "\"ORDER\" INTEGER NOT NULL ," + // 4: order
                "\"PARENT_CHAPTER_ID\" INTEGER NOT NULL ," + // 5: parentChapterId
                "\"USER_CONTROL_SET_TOP\" INTEGER NOT NULL ," + // 6: userControlSetTop
                "\"VISIBLE\" INTEGER NOT NULL );"); // 7: visible
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DataBean entity) {
        stmt.clearBindings();
 
        Long idd = entity.getIdd();
        if (idd != null) {
            stmt.bindLong(1, idd);
        }
        stmt.bindLong(2, entity.getCourseId());
        stmt.bindLong(3, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
        stmt.bindLong(5, entity.getOrder());
        stmt.bindLong(6, entity.getParentChapterId());
        stmt.bindLong(7, entity.getUserControlSetTop() ? 1L: 0L);
        stmt.bindLong(8, entity.getVisible());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DataBean entity) {
        stmt.clearBindings();
 
        Long idd = entity.getIdd();
        if (idd != null) {
            stmt.bindLong(1, idd);
        }
        stmt.bindLong(2, entity.getCourseId());
        stmt.bindLong(3, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
        stmt.bindLong(5, entity.getOrder());
        stmt.bindLong(6, entity.getParentChapterId());
        stmt.bindLong(7, entity.getUserControlSetTop() ? 1L: 0L);
        stmt.bindLong(8, entity.getVisible());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DataBean readEntity(Cursor cursor, int offset) {
        DataBean entity = new DataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // idd
            cursor.getInt(offset + 1), // courseId
            cursor.getInt(offset + 2), // id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.getInt(offset + 4), // order
            cursor.getInt(offset + 5), // parentChapterId
            cursor.getShort(offset + 6) != 0, // userControlSetTop
            cursor.getInt(offset + 7) // visible
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DataBean entity, int offset) {
        entity.setIdd(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCourseId(cursor.getInt(offset + 1));
        entity.setId(cursor.getInt(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setOrder(cursor.getInt(offset + 4));
        entity.setParentChapterId(cursor.getInt(offset + 5));
        entity.setUserControlSetTop(cursor.getShort(offset + 6) != 0);
        entity.setVisible(cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DataBean entity, long rowId) {
        entity.setIdd(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DataBean entity) {
        if(entity != null) {
            return entity.getIdd();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DataBean entity) {
        return entity.getIdd() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
