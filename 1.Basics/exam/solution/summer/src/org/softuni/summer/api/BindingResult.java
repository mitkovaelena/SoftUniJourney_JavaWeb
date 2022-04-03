package org.softuni.summer.api;

import java.util.*;

public final class BindingResult {
    private static final String DEFAULT_REJECTED_FIELD_MESSAGE = "Mapping of the %s field failed.";

    private List<BindingError> errors;

    public BindingResult(){
        this.errors = new ArrayList<>();
    }

    public List<BindingError> getErrors() {
        return errors;
    }

    public void setErrors(List<BindingError> errors) {
        this.errors = errors;
    }

    public void addError(String fieldName) {
        this.errors.add(new BindingError(fieldName, String.format(DEFAULT_REJECTED_FIELD_MESSAGE, fieldName)));
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new BindingError(fieldName, message));
    }

    public boolean hasErrors(){
        return this.errors.size() > 0;
    }
}
