package Owner;

import Agent.Agent;
import CSV.*;
import Initializer.Initialization;
import Phone.Phone;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OwnerDatabase implements ReadWriteRole<Owner> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "/owner.CSV");

    private final ArrayList<Owner> ownerList;
    private final static OwnerDatabase instance = new OwnerDatabase();

    private OwnerDatabase () {
        ownerList = new ArrayList<Owner>();
    }

    @Override
    public void init() {
        readData();
    }

    public static OwnerDatabase getInstance() {
        return instance;
    }

    @Override
    public Owner searchUser(String userName) {
        for (Owner owner : ownerList)
            if (owner.getUserName().equals(userName))
                return owner;
        return null;
    }

    public ArrayList<Owner> getOwnerList() {
        return ownerList;
    }

    public int getNewID() {
        return ownerList.get(ownerList.size() - 1).getId() + 1;
    }

    //CRUD
    @Override
    public void create(Owner owner) {
        ownerList.add(owner);
        appendData(owner);
    }

    @Override
    public ArrayList<Owner> read() {
        return ownerList;
    }

    @Override
    public void update(Owner owner) {
        writeData(); //No efficient method for now
    }

    @Override
    public void delete(Owner owner) {
        ownerList.remove(owner);
        writeData();
    }

    @Override
    public void readData() {
        ArrayList<ArrayList<String>> rawData = CSV.readCSV(FILE_PATH);

        for (ArrayList<String> rawRow : rawData) {
            int id = Integer.parseInt(rawRow.get(0));
            String userName = rawRow.get(1);
            String password = rawRow.get(2);
            Phone phone = new Phone(rawRow.get(3));

            Owner owner = new Owner(id, userName, password, phone);
            ownerList.add(owner);
        }
    }


    private ArrayList<String> rawOwner (Owner owner) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(owner.getId())); //id
        result.add(owner.getUserName()); //userName
        result.add(owner.getPassword());
        result.add(owner.getPhone().getNumber()); //phone

        return result;
    }

    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Owner owner : ownerList)
            rawData.add(rawOwner(owner));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    private void appendData(Owner owner) {
        CSV.appendCSV(rawOwner(owner), FILE_PATH);
    }
}