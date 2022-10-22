package edu.bbte.idde.vbim2101.backend.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
