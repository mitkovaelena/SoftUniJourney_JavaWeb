package org.softuni.residentEvil.domain.models.binding;

import org.softuni.residentEvil.annotations.PresentOrPassedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

public class AddVirusDTO extends EditVirusDTO{
    @NotNull(message = "Date released can not be empty")
    @PresentOrPassedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releasedOn;

    public AddVirusDTO() {
    }

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }
}
