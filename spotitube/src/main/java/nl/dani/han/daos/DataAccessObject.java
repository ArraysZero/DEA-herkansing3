package nl.dani.han.daos;

import nl.dani.han.dtos.DataTransferObject;
import nl.dani.han.exceptions.DataAccessException;

public interface DataAccessObject {

    public DataTransferObject getAll() throws DataAccessException;

    public DataTransferObject getById(int id) throws DataAccessException;

    public void deleteById(int id) throws DataAccessException;

}
