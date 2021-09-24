package Property;

import Agent.Agent;
import Owner.Owner;
import Tenant.Tenant;

public class PropertyBuilder {
    protected int id;
    protected PropertyType type;
    protected Owner owner;
    protected Agent agent;
    protected String name;
    protected int roomNum;
    protected int bathRoomNum;
    protected PropertyAddress address;
    protected int size;
    protected PropertyFacilities facilities;
    protected Tenant tenant; //available if null else n/a
    protected String comment;
    protected double rate;
    protected boolean published;

    public PropertyBuilder(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PropertyBuilder setType(PropertyType type) {
        this.type = type;
        return this;
    }

    public PropertyBuilder setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public PropertyBuilder setAgent(Agent agent) {
        this.agent = agent;
        return this;
    }

    public PropertyBuilder setRoomNum(int roomNum) {
        this.roomNum = roomNum;
        return this;
    }

    public PropertyBuilder setBathRoomNum(int bathRoomNum) {
        this.bathRoomNum = bathRoomNum;
        return this;
    }

    public PropertyBuilder setAddress(PropertyAddress address) {
        this.address = address;
        return this;
    }

    public PropertyBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public PropertyBuilder setFacilities(PropertyFacilities facilities) {
        this.facilities = facilities;
        return this;
    }

    public PropertyBuilder setTenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public PropertyBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public PropertyBuilder setRate(double rate) {
        this.rate = rate;
        return this;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Property build() {
        validation();
        return new Property(this);
    }

    private void validation() {

    }
}
