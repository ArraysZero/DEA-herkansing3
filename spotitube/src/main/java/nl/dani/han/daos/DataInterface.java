package nl.dani.han.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DataInterface {
    public Connection connect() throws SQLException, IOException;
}
