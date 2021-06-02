package com.example.EmployeeGateWaySecurity.model;

import java.util.Objects;

/**
 * Created by Praveenkumar on 5/30/2021.
 */
public abstract class AbstractEntity {

    private long id;

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
