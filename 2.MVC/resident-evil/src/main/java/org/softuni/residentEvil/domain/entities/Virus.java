package org.softuni.residentEvil.domain.entities;

import org.softuni.residentEvil.utils.enums.MagnitudeEnum;
import org.softuni.residentEvil.utils.enums.MutationEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "viruses")
public class Virus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    private String sideEffects;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private Boolean isDeadly;

    @Column(nullable = false)
    private Boolean isCurable;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MutationEnum mutation;

    @Column(nullable = false)
    private Integer turnoverRate;

    @Column(nullable = false)
    private Integer hoursUntilTurn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MagnitudeEnum magnitude;

    @Column(nullable = false)
    private Date releasedOn;

    @ManyToMany
    @JoinTable(name = "viruses_capitals",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "virus_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "capital_id"))
    private Set<Capital> capitals;

    public Virus() {
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

    public MutationEnum getMutation() {
        return mutation;
    }

    public void setMutation(MutationEnum mutation) {
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

    public MagnitudeEnum getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(MagnitudeEnum magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Set<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Capital> capitals) {
        this.capitals = capitals;
    }
}
