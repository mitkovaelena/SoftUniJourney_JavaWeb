package org.softuni.residentEvil.domain.models.binding;

import org.softuni.residentEvil.annotations.PresentOrPassedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

public class EditVirusDTO {
    private Long id;

    @NotNull(message = "Name can not be empty")
    @Size(min = 3, max = 10, message = "Invalid name")
    private String name;

    @NotNull(message = "Description can not be empty")
    @Size(min = 5, max = 100, message = "Invalid description")
    private String description;

    @Size(max = 50, message = "Invalid side effects")
    private String sideEffects;

    @NotNull(message = "Creator can not be empty")
    @Pattern(regexp = "^[c|C]rop$", message = "Invalid creator")
    private String creator;

    private Boolean isDeadly;

    private Boolean isCurable;

    @NotNull(message = "Mutation can not be empty")
    private String mutation;

    @NotNull(message = "Turnover rate can not be empty")
    @Min(value = 0, message = "Invalid turnover rate")
    @Max(value = 100, message = "Invalid turnover rate")
    private Integer turnoverRate;

    @NotNull(message = "Hours until turn can not be empty")
    @Min(value = 1, message = "Invalid Hours until turn")
    @Max(value = 12, message = "Invalid Hours until turn")
    private Integer hoursUntilTurn;

    @NotNull(message = "Magnitude can not be empty")
    private String magnitude;

    private Set<String> capitals;

    public EditVirusDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getIsDeadly() {
        return isDeadly;
    }

    public void setIsDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getIsCurable() {
        return isCurable;
    }

    public void setIsCurable(Boolean curable) {
        isCurable = curable;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public Set<String> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<String> capitals) {
        this.capitals = capitals;
    }
}
