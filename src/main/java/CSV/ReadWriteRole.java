package CSV;

import Role.*;

public interface ReadWriteRole<T extends Role> extends ReadWrite<T> {
    //search user by user name
    T searchUser(String username);
}
