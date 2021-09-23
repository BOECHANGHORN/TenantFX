package Property.PropertySearch;

public class FacilitiesPicker {
    private Boolean swimmingPool;
    private Boolean wifi;
    private Boolean tv;
    private Boolean fridge;
    private Boolean airCond;
    private Boolean waterHeater;

    public Boolean isSwimmingPool() {
        return swimmingPool;
    }

    public FacilitiesPicker setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
        return this;
    }

    public Boolean isWifi() {
        return wifi;
    }

    public FacilitiesPicker setWifi(boolean wifi) {
        this.wifi = wifi;
        return this;
    }

    public Boolean isTv() {
        return tv;
    }

    public FacilitiesPicker setTv(boolean tv) {
        this.tv = tv;
        return this;
    }

    public Boolean isFridge() {
        return fridge;
    }

    public FacilitiesPicker setFridge(boolean fridge) {
        this.fridge = fridge;
        return this;
    }

    public Boolean isAirCond() {
        return airCond;
    }

    public FacilitiesPicker setAirCond(boolean airCond) {
        this.airCond = airCond;
        return this;
    }

    public Boolean isWaterHeater() {
        return waterHeater;
    }

    public FacilitiesPicker setWaterHeater(boolean waterHeater) {
        this.waterHeater = waterHeater;
        return this;
    }
}
