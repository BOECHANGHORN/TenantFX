package Property;

import Agent.Agent;
import Owner.Owner;
import Tenant.Tenant;

public class Property {
    private int id;
    private String name;
    private PropertyType type;
    private Owner owner;
    private Agent agent;
    private int roomNum;
    private int bathRoomNum;
    private PropertyAddress address;
    private int size;
    private PropertyFacilities facilities;
    private Tenant tenant; //available if null else n/a
    private String comment;
    private double rate;
    private boolean published;

    public Property(PropertyBuilder propertyBuilder) {
        this.id = propertyBuilder.id;
        this.name = propertyBuilder.name;
        this.type = propertyBuilder.type;
        this.owner = propertyBuilder.owner;
        this.agent = propertyBuilder.agent;
        this.roomNum = propertyBuilder.roomNum;
        this.bathRoomNum = propertyBuilder.bathRoomNum;
        this.address = propertyBuilder.address;
        this.size = propertyBuilder.size;
        this.facilities = propertyBuilder.facilities;
        this.tenant = propertyBuilder.tenant;
        this.comment = propertyBuilder.comment;
        this.rate = propertyBuilder.rate;
        this.published = propertyBuilder.published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getBathRoomNum() {
        return bathRoomNum;
    }

    public void setBathRoomNum(int bathRoomNum) {
        this.bathRoomNum = bathRoomNum;
    }

    public PropertyAddress getAddress() {
        return address;
    }

    public void setAddress(PropertyAddress address) {
        this.address = address;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PropertyFacilities getFacilities() {
        return facilities;
    }

    public void setFacilities(PropertyFacilities facilities) {
        this.facilities = facilities;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "\n, type=" + type +
                "\n, owner=" + owner +
                "\n, agent=" + agent +
                "\n, roomNum=" + roomNum +
                "\n, bathRoomNum=" + bathRoomNum +
                "\n, address=" + address +
                "\n, size=" + size +
                "\n, facilities=" + facilities +
                "\n, tenant=" + tenant +
                "\n, comment='" + comment + '\'' +
                "\n, rate=" + rate +
                "\n, published=" + published +
                '}';
    }
}
