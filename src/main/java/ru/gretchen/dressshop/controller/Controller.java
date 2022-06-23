package ru.gretchen.dressshop.controller;

import ru.gretchen.dressshop.exception.*;
import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.repository.DressRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Controller {
    private final DressRepository dressRepository;

    public Controller() throws RepositoryInitializeException {
        this.dressRepository = new DressRepository();
    }

    public Optional<DressEntity> getDress(Long id) {
        try {
            return dressRepository.getById(id);
        } catch (SQLException | DressNotFoundException e) {
            throw new GetDressException(e.getMessage());
        }
    }

    public List<DressEntity> getAllDresses() {
        try {
            return dressRepository.getAll();
        } catch (SQLException e) {
            throw new GetAllDressesException(e.getMessage());
        }
    }

    public DressEntity createDress(DressEntity dress) {
        try {
            return dressRepository.save(dress);
        } catch (SQLException e) {
            throw new CreateDressException(e.getMessage());
        }
    }

    public DressEntity updateDress(Long id, DressEntity dress) {
        try {
            return dressRepository.update(id, dress);
        } catch (SQLException e) {
            throw new UpdateDressException(e.getMessage());
        }
    }

    public void deleteDress(Long id) {
        try {
            dressRepository.delete(id);
        } catch (SQLException e) {
            throw new DeleteDressException(e.getMessage());
        }
    }
}
