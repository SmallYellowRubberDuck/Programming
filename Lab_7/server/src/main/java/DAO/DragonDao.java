package DAO;

import com.mysql.cj.protocol.a.ZonedDateTimeValueEncoder;
import containers.CollectionStore;
import data.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;

public class DragonDao implements Dao<Dragon> {

    private TreeSet<Dragon> dragons = new TreeSet<Dragon>();
    private CollectionStore collectionStore;
    private Connection connection;

    /**
     * Конструктор объекта DAO через текущую коллекцию
     */
    public DragonDao() {
        this.connection = null;
        try {
            this.connection = DriverManager.getConnection("jdbc:pg://localhost:8081/studs",
                    "s367163", "YiOY5NnxokLIzF8B");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        this.collectionStore = new CollectionStore();
        try {
            readCollection();
        } catch (SQLException e) {
            System.out.println("Ошибка чтения из файла(библиотека)");
            collectionStore.setDragonCollection(new TreeSet<Dragon>());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла(иное)");
        }
    }

    /**
     * Получение дракона из текущей коллекции по ID
     *
     * @param id
     * @return
     */
    @Override
    public Dragon get(int id) {
        for (Dragon dragon : collectionStore.getDragonCollection()) {
            if (Objects.equals(dragon.getId(), id)) return Optional.ofNullable(dragon).get();
        }
        return null;
    }

    /**
     * Создание нового элемента в текущей коллекции
     *
     * @param dragon
     */
    @Override
    public void create(Dragon dragon) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO DragonBorn (DName, DType, Age, Color, Cave_Depth, Cave_Treasures," +
                            " CoordinateX, CoordinateY, DCharacter, CreationDate, login) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
            st.setString(1, dragon.getName());
            st.setString(2, dragon.getType().toString());
            st.setInt(3, dragon.getAge());
            st.setString(4, dragon.getColor().toString());
            st.setLong(5, dragon.getCave().getDepth());
            st.setInt(6, dragon.getCave().getNumberOfTreasures());
            st.setInt(7, dragon.getCoordinates().getX());
            st.setLong(8, dragon.getCoordinates().getY());
            st.setString(9, dragon.getCharacter().toString());
            st.setString(10, dragon.getCreationDate().toString());
            st.setString(11, dragon.getLogin());
            st.execute();
            st.close();
            readCollection();
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Ошибка добавления дракона в БД");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка обновления коллекции в памяти");
        }
    }

    /**
     * Получение текущей коллекции драконов
     *
     * @return
     */
    @Override
    public TreeSet<Dragon> getAll() {
        return collectionStore.getDragonCollection();
    }

    /**
     * Сохранение коллекции в файл
     */
    @Override
    public void save() {
        try {
            writeCollection(collectionStore.getDragonCollection());
        }catch (SQLException e){
            System.err.println("Ошибка в записи файла(БД)");
        } catch (IOException e) {
            System.err.println("Ошибка в записи файла(иное)");
        }
    }

    /**
     * Обновление данных дракона по ID
     *
     * @param dragon
     * @param id
     */
    @Override
    public void update(Dragon dragon, int id) {
        Dragon dragonOld = get(Objects.requireNonNull(id, "Id cannot be null"));
        dragonOld.setName(dragon.getName());
        dragonOld.setType(dragon.getType());
        dragonOld.setAge(dragon.getAge());
        dragonOld.setColor(dragon.getColor());
        dragonOld.setCave(dragon.getCave());
        dragonOld.setCoordinates(dragon.getCoordinates());
        dragonOld.setCharacter(dragon.getCharacter());
        dragonOld.setCreationDate(ZonedDateTime.now());
    }

    /**
     * Добавление нового пользователя
     */
    public String addUser(String login, String password){
        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO Users (login, password) VALUES (?,?);");
            st.setString(1, login);
            st.setString(2, password);
            st.execute();
            st.close();
        }catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка добавления пользователя в БД");
            return "0";
        }
        return "1";
    }

    /**
     * Проверка авторизации пользователя
     */
    public String checkUser(String login, String password){
        try {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT password FROM Users WHERE login=?;");
            st.setString(1, login);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
            String correctPass = resultSet.getString("password");
            if (correctPass.equals(password)) return "1";
            st.close();}
            return "0";
        }catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Пользователь не прошел аутентификацию");
            return "0";
        }
    }

    /**
     * Удаление дракона из текущей коллекции по ID
     *
     * @param dragon
     */
    @Override
    public void delete(Dragon dragon) {
        try {
            PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM DragonBorn " +
                            " WHERE id=?;");
            st.setInt(1, dragon.getId());
            st.execute();
            st.close();
            readCollection();
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Ошибка добавления дракона в БД");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка обновления коллекции в памяти");
        }
    }

    /**
     * Метод осуществляет чтение коллекции из файла
     *
     * @return
     * @throws SQLException
     * @throws IOException
     */
    private void readCollection() throws IOException, SQLException {
            TreeSet<Dragon> dragons = new TreeSet<>();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM DragonBorn;");
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                dragons.add(new Dragon(
                        resultSet.getInt("id"),
                        resultSet.getString("DName"),
                        new Coordinates(resultSet.getInt("CoordinateX"), resultSet.getLong("CoordinateY")),
                        ZonedDateTime.parse(resultSet.getString("CreationDate")),
                        resultSet.getInt("Age"),
                        Color.valueOf(resultSet.getString("Color")),
                        DragonType.valueOf(resultSet.getString("DType")),
                        DragonCharacter.valueOf(resultSet.getString("DCharacter")),
                        new DragonCave(resultSet.getLong("Cave_Depth"), resultSet.getInt("Cave_Treasures")),
                        resultSet.getString("login")
                ));
            }
            collectionStore.setDragonCollection(dragons);

    }

    /**
     * Метод осуществляет запись в XML-файл
     *
     * @param collection
     * @throws SQLException
     * @throws IOException
     */
    private void writeCollection(TreeSet<Dragon> collection) throws SQLException,IOException {
        PreparedStatement st0 = connection.prepareStatement("TRUNCATE TABLE DragonBorn;");
        st0.execute();
        st0.close();
            for (Dragon dragon : collection) {
                PreparedStatement st = connection.prepareStatement(
                        "REPLACE INTO DragonBorn (DName, DType, Age, Color, Cave_Depth, Cave_Treasures," +
                                " CoordinateX, CoordinateY, DCharacter, CreationDate, login) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
                st.setString(1, dragon.getName());
                st.setString(2, dragon.getType().toString());
                st.setInt(3, dragon.getAge());
                st.setString(4, dragon.getColor().toString());
                st.setLong(5, dragon.getCave().getDepth());
                st.setInt(6, dragon.getCave().getNumberOfTreasures());
                st.setInt(7, dragon.getCoordinates().getX());
                st.setLong(8, dragon.getCoordinates().getY());
                st.setString(9, dragon.getCharacter().toString());
                st.setString(10, dragon.getCreationDate().toString());
                st.setString(11, dragon.getLogin());
                st.execute();
                st.close();
            }
    }
}

