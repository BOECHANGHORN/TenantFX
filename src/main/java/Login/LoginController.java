package Login;

import Agent.AgentDatabase;
import Owner.OwnerDatabase;
import Role.Role;

public class LoginController {

    public Role login(String userName, String password) throws UserNotExistExcpt, WrongPwdExcpt {

        Role user = AgentDatabase.getInstance().searchUser(userName); //try get from AgentDB
        if (user == null)
            user = OwnerDatabase.getInstance().searchUser(userName); //try get from OwnerDB

        if (user == null)
            throw new UserNotExistExcpt("User does not exist");
        else if (!user.getPassword().equals(password))
            throw new WrongPwdExcpt("Wrong password exception");

        return user;
    }
}
