package com.alanger.peruappstest.database;

public class DataBaseDesign {

    /***NO QUITAR LOS ESPACIOS ENTRE LAS PALABRAS CLAVE PORQ SON NECESARIAS**/

    //TODO:database name
    public static final String DATABASE_NAME="dataWorktime";

    //TODO:PALABRAS CLAVE
    private static final String TABLE_CREATE        = " CREATE TABLE IF NOT EXISTS ",// IF NOT EXISTS
            AUTOINCREMET        = "  AUTOINCREMENT ",
            PK                  = " PRIMARY KEY ",
            N_NULL              = " NOT NULL ",
            DEF_DATE_NOW        = " DEFAULT (datetime('now','localtime')) ",
            AND                 = " , ";

    public static final String  _SELECT     = " SELECT ",
            _AND        = " AND ",
            _FROM       = " FROM ",
            _WHERE      = " WHERE ",
            _ORDERBY    = " ORDER BY ",
            _STRASC     = " COLLATE UNICODE ASC ",
            _n = " , ";

    //TIPOS DE DATOS
    private static final String TYPE_INTEGER    = " INTEGER ",
            TYPE_VARCHAR    = " VARCHAR(100) ",
            TYPE_DATETIME   = " DATETIME ",
            TYPE_BOOLEAN    = " BOOLEAN ",
            TYPE_FLOAT      = " FLOAT ";


    //TABLE POST
    public static final String  TAB_POST  = "PostTab",
            TAB_POST_ID             = "id",
            TAB_POST_ID_TYPE        = TYPE_INTEGER,
            TAB_POST_USERID         = "userId",
            TAB_POST_USERID_TYPE    = TYPE_INTEGER,
            TAB_POST_TITTLE         = "title",
            TAB_POST_TITTLE_TYPE    = TYPE_VARCHAR,
            TAB_POST_BODY           = "body",
            TAB_POST_BODT_TYPE      = TYPE_VARCHAR;


    //TABLE POST
    public static final String  TAB_COMENT  = "ComentTab",
            TAB_COMENT_ID           = "id",
            TAB_COMENT_ID_TYPE      = TYPE_INTEGER,
            TAB_COMENT_DATETIME     = "dateTime",
            TAB_COMENT_DATETIME_TYPE= TYPE_DATETIME,
            TAB_COMENT_BODY         = "title",
            TAB_COMENT_BODY_TYPE    = TYPE_VARCHAR,
            TAB_COMENT_IDPOST       = "idPost",
            TAB_COMENT_IDPOST_TYPE  = TYPE_INTEGER;


    //TODO:CREATE TABLES
    public static final String CREATETABLE_POST =
            TABLE_CREATE+TAB_POST+"("+
                    TAB_POST_ID     + TAB_POST_ID_TYPE      + PK+ N_NULL+
                    AND+
                    TAB_POST_USERID + TAB_POST_USERID_TYPE  + N_NULL+
                    AND+
                    TAB_POST_TITTLE + TAB_POST_TITTLE_TYPE  + N_NULL+
                    AND+
                    TAB_POST_BODY   + TAB_POST_BODT_TYPE    + N_NULL+
                    ")";
    //TODO:CREATE TABLES
    public static final String CREATETABLE_COMENT =
            TABLE_CREATE+TAB_COMENT+"("+
                    TAB_COMENT_ID       + TAB_COMENT_ID_TYPE        + PK+ AUTOINCREMET+
                    AND+
                    TAB_COMENT_DATETIME + TAB_COMENT_DATETIME_TYPE  + DEF_DATE_NOW+
                    AND+
                    TAB_COMENT_BODY     + TAB_COMENT_BODY_TYPE      + N_NULL+
                    AND+
                    TAB_COMENT_IDPOST   + TAB_COMENT_IDPOST_TYPE    + N_NULL+
                    ")";

}
