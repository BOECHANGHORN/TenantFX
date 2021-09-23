package Property;

import Agent.*;
import Initializer.Initialization;
import Tenant.*;
import CSV.*;
import Owner.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PropertyDatabase implements ReadWrite<Property> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "/property.CSV");

    private final ArrayList<Property> propertyList;
    private static final PropertyDatabase instance = new PropertyDatabase();

    private PropertyDatabase() {
        propertyList = new ArrayList<Property>();
    }

    @Override
    public void init() {
        readData();
    }

    public static PropertyDatabase getInstance() {
        return instance;
    }

    // All the methods require to create instances
    private Owner getOwner(String ownerName) {
        return OwnerDatabase.getInstance().searchUser(ownerName);
    }

    private Agent getAgent(String agentName) {
        return AgentDatabase.getInstance().searchUser(agentName);
    }

    private Tenant getTenant(String tenantName) {
        return TenantDatabase.getInstance().searchUser(tenantName);
    }
    //Done

    //Method to unpack/pack address and facilities
    private PropertyAddress unpackAddress(String rawAddress) {
        rawAddress = rawAddress.substring(1, rawAddress.length() - 1);
        String[] splited = rawAddress.split("&");

        return new PropertyAddress(splited[0], splited[1], splited[2]);
    }

    private String packAddress(PropertyAddress address) {

        return "[" + address.getDetailAddress() + "&" +
                address.getPostalCode() + "&" +
                address.getState() + "]";
    }

    private PropertyFacilities unpackFacilities(String rawFacilities) {
        rawFacilities = rawFacilities.substring(1, rawFacilities.length() - 1);
        String[] splited = rawFacilities.split("&");

        boolean swimmingPool = splited[0].equals("t");
        boolean wifi = splited[1].equals("t");
        int tv = Integer.parseInt(splited[2]);
        int fridge = Integer.parseInt(splited[3]);
        int airCond = Integer.parseInt(splited[4]);
        int waterHeater = Integer.parseInt(splited[5]);

        return new PropertyFacilities(swimmingPool,wifi,tv,fridge,airCond,waterHeater);
    }

    private String packFacilities(PropertyFacilities facilities) {

        return "[" + (facilities.isSwimmingPool() ? "t" : "f") + "&" +
                (facilities.isWifi() ? "t" : "f") + "&" +
                facilities.getTv() + "&" +
                facilities.getFridge() + "&" +
                facilities.getAirCond() + "&" +
                facilities.getWaterHeater() + "]";
    }
    //Done

    public Property searchByID(int id) {
        for (Property property : propertyList)
            if (property.getId() == id)
                return property;
        return null;
    }

    @Override
    public int getNewID() {
        return propertyList.get(propertyList.size() - 1).getId() +1;
    }

    //CRUD
    @Override
    public void create(Property property) {
        propertyList.add(property);
        appendData(property);
    }

    @Override
    public ArrayList<Property> read() {
        return propertyList;
    }

    @Override
    public void update(Property property) {
        writeData(); //No efficient method for now
    }

    @Override
    public void delete(Property property) {
        propertyList.remove(property);
        writeData();
    }


    @Override
    public void readData() {
        ArrayList<ArrayList<String>> rawData = CSV.readCSV(FILE_PATH);

        for (ArrayList<String> rawRow : rawData) {
            PropertyBuilder pb = new PropertyBuilder(Integer.parseInt(rawRow.get(0)), rawRow.get(1));
            pb.setType(PropertyType.valueOf(rawRow.get(2)));
            pb.setOwner(getOwner(rawRow.get(3)));
            pb.setAgent(getAgent(rawRow.get(4)));
            pb.setRoomNum(Integer.parseInt(rawRow.get(5)));
            pb.setBathRoomNum(Integer.parseInt(rawRow.get(6)));
            pb.setAddress(unpackAddress(rawRow.get(7)));
            pb.setSize(Integer.parseInt(rawRow.get(8)));
            pb.setFacilities(unpackFacilities(rawRow.get(9)));

            if (!rawRow.get(10).isEmpty())
                pb.setTenant(getTenant(rawRow.get(10)));

            if (!rawRow.get(11).isEmpty())
                pb.setComment(rawRow.get(11));

            pb.setRate(Double.parseDouble(rawRow.get(12)));

            Property property = new Property(pb);
            propertyList.add(property);
        }
    }

    private ArrayList<String> rawProperty (Property property) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(property.getId()));
        result.add(property.getName());
        result.add(property.getType().toString());
        result.add(property.getOwner().getUserName());
        result.add(property.getAgent().getUserName());
        result.add(Integer.toString(property.getRoomNum()));
        result.add(Integer.toString(property.getBathRoomNum()));
        result.add(packAddress(property.getAddress()));
        result.add(Integer.toString(property.getSize()));
        result.add(packFacilities(property.getFacilities()));

        if (property.getTenant() != null)
            result.add(property.getTenant().getUserName());
        else
            result.add("");

        if (property.getComment() != null)
            result.add(property.getComment());
        else
            result.add("");

        result.add(Double.toString(property.getRate()));

        return result;
    }

    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Property property : propertyList)
            rawData.add(rawProperty(property));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    private void appendData(Property property) {
        CSV.appendCSV(rawProperty(property), FILE_PATH);
    }

}