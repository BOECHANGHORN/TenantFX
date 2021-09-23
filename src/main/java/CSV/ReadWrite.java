package CSV;

import Role.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface ReadWrite<T> {
    //Initialization
    void init();

    int getNewID();

    //CRUD
    void create(T t);
    ArrayList<T> read();
    void update(T t);
    void delete(T t);

    //Read write into DB
    void readData();
    void writeData();
}