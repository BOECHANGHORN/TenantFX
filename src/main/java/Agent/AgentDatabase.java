package Agent;

import CSV.*;
import Initializer.Initialization;
import Phone.Phone;
import Role.Role;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AgentDatabase implements ReadWriteRole<Agent> {
    private final File FILE_PATH = new File(Initialization.CUR_PATH + "/agent.CSV");
    private final ArrayList<Agent> agentList;
    private final static AgentDatabase instance = new AgentDatabase();

    private AgentDatabase () {
        agentList = new ArrayList<Agent>();
    }

    @Override
    public void init() {
        readData();
    }

    public static AgentDatabase getInstance() {
        return instance;
    }

    @Override
    public Agent searchUser(String userName) {
        for (Agent agent : agentList)
            if (agent.getUserName().equals(userName))
                return agent;
        return null;
    }

    @Override
    public int getNewID() {
        return agentList.get(agentList.size() - 1).getId() + 1;
    }

    //CRUD
    @Override
    public void create(Agent agent) {
        agentList.add(agent);
        appendData(agent);
    }

    @Override
    public ArrayList<Agent> read() {
        return agentList;
    }

    @Override
    public void update(Agent agent) {
        writeData(); //No efficient method for now
    }

    @Override
    public void delete(Agent agent) {
        agentList.remove(agent);
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

            Agent agent = new Agent(id, userName, password, phone);
            agentList.add(agent);
        }
    }

    private ArrayList<String> rawAgent (Agent agent) {
        ArrayList<String> result = new ArrayList<>();

        result.add(Integer.toString(agent.getId())); //id
        result.add(agent.getUserName()); //userName
        result.add(agent.getPassword());
        result.add(agent.getPhone().getNumber()); //phone

        return result;
    }

    @Override
    public void writeData() {
        ArrayList<ArrayList<String>> rawData = new ArrayList<>();

        for (Agent agent : agentList)
            rawData.add(rawAgent(agent));

        CSV.writeCSV(rawData, FILE_PATH);
    }

    private void appendData(Agent agent) {
        CSV.appendCSV(rawAgent(agent), FILE_PATH);
    }
}