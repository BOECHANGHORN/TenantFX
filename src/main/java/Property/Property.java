package Property;

import Agent.Agent;
import Owner.Owner;
import Tenant.Tenant;
import Agent.AgentDatabase;
import Owner.OwnerDatabase;
import Tenant.TenantDatabase;

import java.io.Serializable;

/**
 * <h1>Property Class</h1>
 * The Property class is a model class stores
 * the data fields of Property
 *
 * @author Tan Kai Yuan
 * @version 1.0
 * @since 2021-10-08
 */
public class Property implements Serializable{
    private static final long serialVersionUID = 6543585098267757680L;

    private int id;
    private String name;
    private PropertyType type;
    transient private Owner owner;
    private int ownerID;
    transient private Agent agent;
    private int agentID;
    private int roomNum;
    private int bathRoomNum;
    private PropertyAddress address;
    private int size;
    private PropertyFacilities facilities;
    transient private Tenant tenant; //available if null else n/a
    private int tenantID; //available if 0 else n/a
    private String comment;
    private double rate;
    private boolean published;

    /**
     * Instantiates a new Property.
     *
     * @param propertyBuilder the property builder
     */
    public Property(PropertyBuilder propertyBuilder) {
        this.id = propertyBuilder.id;
        this.name = propertyBuilder.name;
        this.type = propertyBuilder.type;
        this.owner = propertyBuilder.owner;
        this.ownerID = propertyBuilder.owner.getId();
        this.agent = propertyBuilder.agent;
        this.agentID = propertyBuilder.agent.getId();
        this.roomNum = propertyBuilder.roomNum;
        this.bathRoomNum = propertyBuilder.bathRoomNum;
        this.address = propertyBuilder.address;
        this.size = propertyBuilder.size;
        this.facilities = propertyBuilder.facilities;
        this.tenant = propertyBuilder.tenant;
        this.tenantID = this.tenant == null ? 0 : this.tenant.getId();
        this.comment = propertyBuilder.comment;
        this.rate = propertyBuilder.rate;
        this.published = propertyBuilder.published;
    }

    /**
     * For PropertyDatabase to avoid mal-reference
     *
     */
    protected void setOwnerViaID() {
        Owner owner = OwnerDatabase.getInstance().searchByID(ownerID);
        setOwner(owner);
    }

    /**
     * For PropertyDatabase to avoid mal-reference
     *
     */
    protected void setAgentViaID() {
        Agent agent = AgentDatabase.getInstance().searchByID(agentID);
        setAgent(agent);
    }

    /**
     * For PropertyDatabase to avoid mal-reference
     *
     */
    protected void setTenantViaID() {
        if (tenantID == 0)
            setTenant(null);
        else {
            Tenant tenant = TenantDatabase.getInstance().searchByID(tenantID);
            setTenant(tenant);
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public PropertyType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(PropertyType type) {
        this.type = type;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
        this.ownerID = owner.getId();
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
        this.agentID = agent.getId();
    }

    /**
     * Gets room num.
     *
     * @return the room num
     */
    public int getRoomNum() {
        return roomNum;
    }

    /**
     * Sets room num.
     *
     * @param roomNum the room num
     */
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * Gets bath room num.
     *
     * @return the bath room num
     */
    public int getBathRoomNum() {
        return bathRoomNum;
    }

    /**
     * Sets bath room num.
     *
     * @param bathRoomNum the bath room num
     */
    public void setBathRoomNum(int bathRoomNum) {
        this.bathRoomNum = bathRoomNum;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public PropertyAddress getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(PropertyAddress address) {
        this.address = address;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets facilities.
     *
     * @return the facilities
     */
    public PropertyFacilities getFacilities() {
        return facilities;
    }

    /**
     * Sets facilities.
     *
     * @param facilities the facilities
     */
    public void setFacilities(PropertyFacilities facilities) {
        this.facilities = facilities;
    }

    /**
     * Gets tenant.
     *
     * @return the tenant
     */
    public Tenant getTenant() {
        return tenant;
    }

    /**
     * Sets tenant.
     *
     * @param tenant the tenant
     */
    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
        this.tenantID = tenant == null ? 0 : tenant.getId();
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Is published boolean.
     *
     * @return the boolean
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * Sets published.
     *
     * @param published the published
     */
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
