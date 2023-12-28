package org.png.dto.projection;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class SimCardProjection {

    private Long id;
    private Long number;

    public SimCardProjection(Long id, Long number) {
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
