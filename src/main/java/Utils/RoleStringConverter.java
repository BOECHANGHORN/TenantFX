package Utils;

import Role.Role;
import javafx.util.StringConverter;

public class RoleStringConverter extends StringConverter<Role> {
    @Override
    public String toString(Role x) {
        return x == null ? null : x.getUserName();
    }

    @Override
    public Role fromString(String s) {
        return null;
    }
}
