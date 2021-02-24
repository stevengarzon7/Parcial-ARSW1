package eci.arsw.covidanalyzer.model;

import java.util.UUID;

public class Result {
    private UUID id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String birthString;
    private String testString;
    private boolean result;
    private double testSpecifity;
    private ResultType resultType;

    public Result() {

    }


    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthString;
    }

    public String getTestDate() {
        return testString;
    }

    public boolean isResult() {
        return result;
    }

    public double getTestSpecifity() {
        return testSpecifity;
    }
   
    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(String birthString) {
        this.birthString = birthString;
    }

    public void setTestDate(String testString) {
        this.testString = testString;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setTestSpecifity(double testSpecifity) {
        this.testSpecifity = testSpecifity;
    }

    public String getBirthString() {
        return birthString;
    }

    public void setBirthString(String birthString) {
        this.birthString = birthString;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

   
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return ((Result) o).getId().equals(this.id);
    }


    @Override
    public String toString() {
        return this.id.toString() + " - " + this.firstName + " - " + this.lastName;
    }
}