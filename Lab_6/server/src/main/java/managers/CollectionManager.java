package managers;

import DAO.DragonDao;
import data.Dragon;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.TreeSet;

/**
 * Класс, управляющий самой коллекцией
 */
public class CollectionManager {
    private DragonDao dragonDao = new DragonDao();
    private Map<ZonedDateTime, Integer> dateFilter ;

    public CollectionManager(){
        this.dragonDao = new DragonDao();
    }
    /**
     * Метод возвращает коллекцию, с которой работает пользователь
     * @return labWorksCollection
     */
    public TreeSet<Dragon> getCollection() {
        return dragonDao.getAll();
    }

    /**
     * Обновляет дракона с указанным id
     * @param id
     */
    public void updateDragon(Dragon dragon, int id){
        dragonDao.update(dragon, id);
    }

    /**
     * Метод возвращает тип коллекции
     * @return collectionType
     */
    public String collectionType(){
        return dragonDao.getAll().getClass().getName();
    }

    /**
     * Метод возвращает размер коллекции (количество элементов в нём)
     * @return collectionSize
     */
    public int collectionSize(){
        return dragonDao.getAll().size();
    }

    /**
     * Возвращает элемент коллекции с таким же ID
     * @param id
     * @return labWork
     */
    public Dragon getSameId(int id){
        return dragonDao.get(id);
    }

    /**
     * Метод добавляет элемент в коллекцию
     * @param dragon
     */
    public void addToCollection(Dragon dragon) {
        dragonDao.create(dragon);
    }

    /**
     * Метод удаляет элемент из коллекции
     * @param dragon
     */
    public void removeFromCollection(Dragon dragon) {
        dragonDao.delete(dragon);
    }

    /**
     * Метод удаляет из коллекции все элементы, меньшие заданного
     * @param comparableDragon
     */
    public void removeLower(Dragon comparableDragon){
        while (dragonDao.getAll().lower(comparableDragon) != null){
            removeFromCollection(dragonDao.getAll().higher(comparableDragon));
        }
    }

    /**
     * Метод удаляет из коллекции все элементы, большие заданного
     * @param comparableDragon
     */
    public void removeGreater(Dragon comparableDragon){
        while (dragonDao.getAll().higher(comparableDragon) != null){
            removeFromCollection(dragonDao.getAll().higher(comparableDragon));
        }
    }

    /**
     * Метод очищает коллекцию (удаляет все элементы)
     */
    public void clearCollection(){
        for (Dragon dragon:dragonDao.getAll()){
            dragonDao.delete(dragon);
        }
    }

    /**
     * Метод генерирует значение ID для нового элемента в коллекции
     * @return lastID
     */
    public int generateNextId() {
            int id = dragonDao.getAll().stream()
                    .mapToInt(Dragon::getId)
                    .filter(dragon -> dragon >= 0)
                    .max().orElse(0);
            return id + 1;
    }

    public Dragon getFirst(){
      return dragonDao.getAll().first();
    }
    /**
     * Метод сохраняет (загружает) коллекцию в XML-файл
     */
    public void saveCollection() {
        dragonDao.save();
    }

    /**
     * Метод выводить коллекцию в строковом формате
     * @return CollectionToString
     */
    @Override
    public String toString() {
        if ((dragonDao.getAll().isEmpty()) || dragonDao.getAll() == null )  return "Колекция пуста!";
        String str = "Все элементы коллекции:";
        for (Dragon dragon : dragonDao.getAll()) {
            str = str + "\n\n" + dragon;
        }
        return str;
    }


    /**
     * Метод возвращает словарь из пар Время создания--Количество драконов
     * @return словарь значений
     */
    public Map groupCountingByCreationDate(){
        for (Dragon dragon: dragonDao.getAll()) {
            dateFilter.put(dragon.getCreationDate(),+1);
        }
        return dateFilter;
    }
}