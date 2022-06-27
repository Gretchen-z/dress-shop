package ru.gretchen.dressshop.controller;

import ru.gretchen.dressshop.exception.GetShopException;
import ru.gretchen.dressshop.exception.RepositoryInitializeException;
import ru.gretchen.dressshop.exception.ShopNotFoundException;
import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.model.ShopEntity;
import ru.gretchen.dressshop.repository.ShopRepository;

import java.util.List;
import java.util.Set;

public class ShopController {
    private final ShopRepository shopRepository;

    public ShopController() throws RepositoryInitializeException {
        this.shopRepository = new ShopRepository();
    }

    public ShopEntity getShop(Long id) {
        try {
            return shopRepository.getById(id);
        } catch (ShopNotFoundException e) {
            throw new GetShopException(e.getMessage());
        }
    }

    public Set<DressEntity> getDresses(Long id) {
        return shopRepository.getDresses(id);
    }

    public List<ShopEntity> getAllShops() {
        return shopRepository.getAll();
    }

    public ShopEntity createShop(ShopEntity shop) {
        return shopRepository.save(shop);
    }

    public ShopEntity updateShop(Long id, ShopEntity shop) {
        return shopRepository.update(id, shop);
    }

    public void deleteShop(Long id) {
        shopRepository.delete(id);
    }
}
