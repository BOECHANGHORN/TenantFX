package Property;

public class PropertyFacilities {
    private boolean swimmingPool;
    private boolean wifi;
    private int tv;
    private int fridge;
    private int airCond;
    private int waterHeater;

    public PropertyFacilities(boolean swimmingPool, boolean wifi, int tv, int fridge, int airCond, int waterHeater) {
        this.swimmingPool = swimmingPool;
        this.wifi = wifi;
        this.tv = tv;
        this.fridge = fridge;
        this.airCond = airCond;
        this.waterHeater = waterHeater;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public int getTv() {
        return tv;
    }

    public void setTv(int tv) {
        this.tv = tv;
    }

    public int getFridge() {
        return fridge;
    }

    public void setFridge(int fridge) {
        this.fridge = fridge;
    }

    public int getAirCond() {
        return airCond;
    }

    public void setAirCond(int airCond) {
        this.airCond = airCond;
    }

    public int getWaterHeater() {
        return waterHeater;
    }

    public void setWaterHeater(int waterHeater) {
        this.waterHeater = waterHeater;
    }

    @Override
    public String toString() {
        return "PropertyFacilities{" +
                "swimmingPool=" + swimmingPool +
                ", wifi=" + wifi +
                ", tv=" + tv +
                ", fridge=" + fridge +
                ", airCond=" + airCond +
                ", waterHeater=" + waterHeater +
                '}';
    }
}
