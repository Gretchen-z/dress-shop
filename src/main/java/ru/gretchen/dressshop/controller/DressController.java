package ru.gretchen.dressshop.controller;

import ru.gretchen.dressshop.exception.*;
import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.repository.DressRepository;

import java.util.List;

public class DressController {
    private final DressRepository dressRepository;

    public DressController() throws RepositoryInitializeException {
        this.dressRepository = new DressRepository();
    }

    public DressEntity getDress(Long id) {
        try {
            return dressRepository.getById(id);
        } catch (DressNotFoundException e) {
            throw new GetDressException(e.getMessage());
        }
    }

    public List<DressEntity> getAllDresses() {
        return dressRepository.getAll();
    }

    public long getCountOfExpensiveDresses() {
        return dressRepository.getCountOfDressWithPriceMoreThan2000();
    }

    public DressEntity createDress(DressEntity dress) {
        return dressRepository.save(dress);
    }

    public DressEntity updateDress(Long id, DressEntity dress) {
        return dressRepository.update(id, dress);
    }

    public void deleteDress(Long id) {
        dressRepository.delete(id);
    }
}
