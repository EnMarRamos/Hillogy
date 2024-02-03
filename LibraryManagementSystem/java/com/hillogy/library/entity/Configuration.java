package com.hillogy.library.entity;

import com.hillogy.library.modal.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "configuration")
public class Configuration {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "config_id")
    private Long configId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Configuration(){

    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
