package org.anasoid.poc.jpa.mtm.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;


@MappedSuperclass
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @Column(name = "created_date")
    private Instant createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = Instant.now();
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
}


