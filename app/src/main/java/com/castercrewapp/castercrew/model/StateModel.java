package com.castercrewapp.castercrew.model;

public class StateModel {
    public String getStateId() {
        return StateId;
    }

    public void setStateId(String stateId) {
        StateId = stateId;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getStateStatus() {
        return StateStatus;
    }

    public void setStateStatus(String stateStatus) {
        StateStatus = stateStatus;
    }

    String StateId,StateName,StateStatus;
}
