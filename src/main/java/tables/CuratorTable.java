package tables;

import db.MySQLConnector;
import objects.Curator;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CuratorTable extends AbsTable {
    private final static String TABLE_NAME = "curators";

    //create
    public CuratorTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(50)");
        create();
    }

    //read
    public ArrayList<Curator> selectAll() {
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }

    private ArrayList<Curator> selectByQuery(String sqlQuery) {
        ArrayList<Curator> curators = new ArrayList<>();
        //Сделать запрос на выборку
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект устройство и добавление его в результирующий массив
                curators.add(
                        new Curator(
                                rs.getLong("id"),
                                rs.getString("fio")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return curators;
    }

    public void select(String[] columns, String[] where) {

        String columnStr = "*";
        if (columns.length > 0) {
            columnStr = String.join(",", columns);
        }

        String sqlQuery = String.format("SELECT %s FROM curators", columnStr);
    }

    //update
    public void insert(Curator curator) {
        //Подключиться к БД
        //Сделать запрос на добавление
        final String sqlQuery = String.format("INSERT INTO %s (fio) VALUES ('%s')",
                tableName, curator.getFio());
        db.executeRequest(sqlQuery);
    }

    public void update(Curator curator) {
        //Подключиться к БД
        //Сделать запрос на изменение
        final String sqlQuery = String.format("UPDATE %s SET fio= '%s' WHERE id= '%d'",
                tableName, curator.getFio());
        db.executeRequest(sqlQuery);
    }

    //delete
    public void delete(long id) {
        //Подключиться к БД
        //Сделать запрос на удаление
        final String sqlQuery = String.format("DELETE FROM %s WHERE id= '%d'",
                tableName, id);
        db.executeRequest(sqlQuery);
    }
}
