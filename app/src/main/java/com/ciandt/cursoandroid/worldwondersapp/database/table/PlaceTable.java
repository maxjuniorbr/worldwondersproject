package com.ciandt.cursoandroid.worldwondersapp.database.table;

public interface PlaceTable {
    String TABLE_NAME = "place";

    // Estrutura para preenchimento dos dados do banco local
    String ID = "_id";
    String PLACE_NAME = "place_name";
    String PLACE_COUNTRY = "place_country";
    String PLACE_DESCRIPTION = "place_description";
    String PLACE_IMAGE_URL = "place_image_url";
    String[] ALL_COLUMNS = {ID, PLACE_NAME, PLACE_COUNTRY, PLACE_DESCRIPTION, PLACE_IMAGE_URL};
    String SQL_CREATE = String.format("CREATE TABLE %1$s " +
                    "(%2$s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%3$s TEXT, " +
                    "%4$s TEXT, " +
                    "%5$s TEXT, " +
                    "%6$s TEXT)",
            TABLE_NAME,
            ID,
            PLACE_NAME,
            PLACE_COUNTRY,
            PLACE_DESCRIPTION,
            PLACE_IMAGE_URL);
    // Estrutura para preenchimento do JSON
    String JSON_ID = "id";
    String JSON_PLACE_NAME = "name";
    String JSON_PLACE_COUNTRY = "country";
    String JSON_PLACE_DESCRIPTION = "description";
    String JSON_PLACE_IMAGE_URL = "image_url";
    String WHERE_ID_EQUALS = " 0 = 0 ";
}
