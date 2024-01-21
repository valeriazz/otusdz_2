package objects;

import db.MySQLConnector;

public class Group {

    public void updateByCuratorId(long id_curator) {
        //Подключиться к БД
        //Сделать запрос на изменение
        final String sqlQuery = String.format("UPDATE %s SET curator= '%s'",
                tableName, id_curator);
        db.executeRequest(sqlQuery);
    }
}

