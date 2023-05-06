package DAO;

import containers.CollectionStore;
import data.Dragon;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;

import static java.lang.System.getenv;

public class DragonDao implements Dao<Dragon> {

    private TreeSet<Dragon> dragons = new TreeSet<Dragon>();
    private CollectionStore collectionStore;


    private String fileName = getenv("laba5");

    /**
     * Конструктор объекта DAO через текущую коллекцию
     */
    public DragonDao() {
        this.collectionStore = new CollectionStore();
        try {
            readCollection();
        } catch (JAXBException e) {
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
        collectionStore.addToCollection(dragon);
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
        } catch (JAXBException e) {
            System.out.println("Ошибка в записи файла(библиотека)");
        } catch (IOException e) {
            System.out.println("Ошибка в записи файла(иное)");
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
     * Удаление дракона из текущей коллекции по ID
     *
     * @param dragon
     */
    @Override
    public void delete(Dragon dragon) {
        collectionStore.removeFromCollection(dragon);
    }

    /**
     * Метод осуществляет чтение коллекции из файла
     *
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    private void readCollection() throws JAXBException, IOException {
        var context = JAXBContext.newInstance(CollectionStore.class);
        var um = context.createUnmarshaller();
        collectionStore = (CollectionStore) um.unmarshal(new InputStreamReader(
                new FileInputStream(fileName), StandardCharsets.UTF_8));
    }

    /**
     * Метод осуществляет запись в XML-файл
     *
     * @param collection
     * @throws JAXBException
     * @throws IOException
     */
    private void writeCollection(TreeSet<Dragon> collection) throws JAXBException, IOException {
        var collectionStore = new CollectionStore();
        collectionStore.setDragonCollection(collection);
        var context = JAXBContext.newInstance(CollectionStore.class);
        var m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
        m.marshal(collectionStore, outputStreamWriter);
    }
}

