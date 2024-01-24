package tables;

import db.MySQLConnector;
import objects.Group;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupTable extends AbsTable {

    private final static String TABLE_NAME = "groups";

    //create
    public GroupTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("name", "varchar(50)");
        columns.put("id_curator", "bigint");
        create();
    }

    //read
    public ArrayList<Group> selectAll() {
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }

    private ArrayList<Group> selectByQuery(String sqlQuery) {
        ArrayList<Group> groups = new ArrayList<>();
        //Сделать запрос на выборку
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект устройство и добавление его в результирующий массив
                groups.add(
                        new Group(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getLong("id_curator")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groups;
    }

    public void select(String[] columns, String[] where) {

        String columnStr = "*";
        if (columns.length > 0) {
            columnStr = String.join(",", columns);
        }

        String sqlQuery = String.format("SELECT %s FROM groups", columnStr);
    }

    //update
    public void insert(Group group) {
        //Подключиться к БД
        //Сделать запрос на добавление
        final String sqlQuery = String.format("INSERT INTO %s (name, id_curator VALUES ('%s', '%d')",
                tableName, group.getName(), group.getId_curator());
        db.executeRequest(sqlQuery);
    }

    public void update(Group group) {
        //Подключиться к БД
        //Сделать запрос на изменение
        final String sqlQuery = String.format("UPDATE %s SET name= '%s', id_curator= '%d' WHERE id= '%d'",
                tableName, group.getName(), group.getId_curator());
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

    public void updateByCuratorId(long id_curator) {
        //Подключиться к БД
        //Сделать запрос на изменение
        final String sqlQuery = String.format("UPDATE %s SET curator= '%s'",
                tableName, id_curator);
        db.executeRequest(sqlQuery);
    }
}
