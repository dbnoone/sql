import db.DB;
import menu.Menu;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Программа написана Паликовым М.Е.");
        boolean begin = true;
        while (begin) {
            int user_choice = Menu.main_menu();
            switch (user_choice) {
                case 1 -> {
                    villains_names();
                }
                case 2 -> {
                    minions_names();
                }
                case 3 -> {
                    add_minions();
                }
                case 4 -> {
                    villain_delete();
                }
                case 5 -> {
                    begin = false;
                }
            }
        }
    }

    private static void villains_names() throws SQLException {
        String query_string = "SELECT COUNT(m_v.minion_id) as count, vl.name FROM `villains` as vl inner join `minions_villains` as m_v on vl.id = m_v.villain_id group by vl.name";
        DB.query(query_string);
        while (DB.rs.next()) {
            int minions_count = DB.rs.getInt("count");
            if (minions_count > 2) {
                System.out.println(DB.rs.getString("name") + " - " + DB.rs.getString("count"));
            }
        }
    }

    private static void minions_names() throws SQLException {
        int villain_id = Menu.villain_id();
        String query_string = "select `name` from `villains` where `id`=" + villain_id;
        DB.query(query_string);
        boolean villain_exists = false;
        while (DB.rs.next()) {
            System.out.println("Villain: " + DB.rs.getString("name"));
            villain_exists = true;
        }
        if (villain_exists) {
            boolean minions_exists = false;
            query_string = "select m.name as name, m.age as age from `minions` as m where m.id in " +
                    "(select m_v.minion_id from `minions_villains` as m_v where m_v.villain_id = " + villain_id + ")";

            DB.query(query_string);
            int minion_counter = 1;
            while (DB.rs.next()) {
                System.out.println(
                        minion_counter + ". " + DB.rs.getString("name") + " - " + DB.rs.getString("age")
                );
                minion_counter++;
                minions_exists = true;
            }
            if (!minions_exists) {
                System.out.println("(миньонов нет)");
            }
        } else {
            System.out.println("Такого злодея нет");
        }
    }

    private static void add_minions() {
        int town_id = 0;
        String[] minion_data = Menu.get_line().split(" ");
        String minion_name = minion_data[1];
        String minion_age = minion_data[2];
        String minion_town = minion_data[3];

        String query_string = "";

        String[] villain_data = Menu.get_line().split(" ");
        String villain_name = villain_data[1];

        try {
            query_string = "select * from towns where name = '" + minion_town + "'";
            DB.query(query_string);
            if (!DB.rs.next()) {
                query_string = "insert into towns (name, country) values ('" + minion_town + "', 'no_country')";
                DB.insert(query_string);
                System.out.println("Город " + minion_town + " был добавлен.");
            }

            query_string = "select * from towns where name = '" + minion_town + "'";
            DB.query(query_string);
            if (DB.rs.next()) {
                town_id = DB.rs.getInt("id");
            }

            query_string = "select * from villains where name = '" + villain_name + "'";
            DB.query(query_string);

            if (!DB.rs.next()) {
                query_string = "insert into villains (`name`, `evilness_factor`) values ('" + villain_name + "', 'bad')";
                DB.insert(query_string);
                System.out.println("Злодей " + villain_name + " был добавлен.");
            }

            query_string = "insert into minions (name, age, town_id) values ('" + minion_name + "', '" + minion_age + "', " + town_id + ")";
            DB.insert(query_string);
            System.out.println("Успешно добавлен " + minion_name + ", чтобы быть миньоном " + villain_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void villain_delete() throws SQLException {
        int villain_id = Menu.villain_id();
        String query_string;
        query_string = "select name from villains where id = " + villain_id;
        DB.query(query_string);
        if (DB.rs.next()) {
            String villain_name = DB.rs.getString("name");
            query_string = "select count(minion_id) as counter from minions_villains where villain_id = " + villain_id;
            DB.query(query_string);
            if (DB.rs.next()) {
                int minions_count = DB.rs.getInt("counter");
                query_string = "delete from minions_villains where villain_id = " + villain_id;
                DB.delete(query_string);
                query_string = "delete from villains where id = " + villain_id;
                DB.delete(query_string);
                System.out.println(villain_name + " был удален");
                System.out.println(minions_count + " миньонов освобождены");
            }
        } else {
            System.out.println("Злоей с таким id не найден");
        }
    }
}
