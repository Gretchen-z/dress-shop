package ru.gretchen.dressshop.model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import ru.gretchen.dressshop.model.Enumeration.Color;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dress")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DressEntity extends BaseEntity {
    @Column(name = "color")
    private Color color;

    @Column(name = "price")
    private Long price;

    @Column(name = "in_stock")
    private Long inStock;
}
