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

    //update
    public void insert(Curator curator) {
        //Подключиться к БД
        //Сделать запрос на добавление
        final String sqlQuery = String.format("INSERT INTO %s (fio) VALUES ('%s')",
                tableName, curator.getFio());
        db.executeRequest(sqlQuery);
    }
}