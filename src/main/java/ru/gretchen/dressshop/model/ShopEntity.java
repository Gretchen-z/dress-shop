package ru.gretchen.dressshop.model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "shop")
public class ShopEntity extends BaseEntity{
    public ShopEntity(String address) {
        this.address = address;
    }

    @Column(name = "address")
    private String address;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shop_dress",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "dress_id"))
    private Set<DressEntity> dresses = new HashSet<>();

    public void addDress(DressEntity dress) {
        dresses.add(dress);
    }

    public void deleteDress(DressEntity dress) {
        dresses.remove(dress);
    }
}
