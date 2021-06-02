package com.linmsen.jpa.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DeletableBaseEntity extends BaseEntity{

    @Column(name = "deleted")
    public Boolean deleted;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
