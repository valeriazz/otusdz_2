package tables;

import db.MySQLConnector;
import objects.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupTable extends AbsTable {
    private final static String TABLE_NAME = "groups_t";

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

    private ArrayList<Group> selectByQuery(String sqlQuery){
        ArrayList<Group> groups = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                groups.add(new Group(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("id_curator")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groups;
    }

    //update
    public void insert(Group group){
        db = new MySQLConnector();
        final String sqlRequest = String.format("INSERT INTO %s (name, id_curator) VALUES ('%s', %d)",
                tableName, group.getName(), group.getId_curator());
        db.executeRequest(sqlRequest);
    }

//        public void updateByCuratorId(Group group) {
//        //Подключиться к БД
//        db = new MySQLConnector();
//        //Сделать запрос на изменение
//        final String sqlQuery = String.format("UPDATE %s SET id_curator= '%d' WHERE id= '%d'",
//                tableName, group.getId_curator(), group.getId());
//        db.executeRequest(sqlQuery);
//    }

    public void updateByCuratorId(Group group){
        db = new MySQLConnector();
        String sqlQuery = String.format("UPDATE groups_t SET " +
                "id_curator= 4 WHERE id = '3' ");
        db.executeRequest(sqlQuery);

    }

    public void selectAllGroupsAndCurators() throws SQLException {
        db = new MySQLConnector();
        String sqlRequest = String.format("SELECT groups_t.id, groups_t.name, groups_t.id_curator, " +
                "curators.fio\n" +
                "FROM groups_t\n" +
                "JOIN curators ON groups_t.id_curator = curators.id");
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        while (rs.next()) {
            System.out.println("Список групп с их кураторами: |" +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4));
        }
    }
}