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
    private int testRealize;
    private ResultType resultType;

    public Result(UUID id, String firstName, String lastName, String gender, String email, String birthString, String testString, boolean result, double testSpecifity, ResultType resultType, int testRealize) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.birthString = birthString;
        this.testString = testString;
        this.result = result;
        this.testSpecifity = testSpecifity;
        this.resultType = resultType;
        this.testRealize = testRealize;
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


    public String toString() {
        return this.id.toString() + " - " + this.firstName + " - " + this.lastName;
    }

    public int getTestRealize() {
        return testRealize; //To change body of generated methods, choose Tools | Templates.
    }

     public void setTestRealize(int testRealize) {
        this.testRealize = testRealize;
    }
}