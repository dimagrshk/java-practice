package com;

import java.text.DateFormat;
import java.util.Date;

public class Ticket {
    // atributs
    private String numberOfTicket;
    private String startPoint;
    private String destination;
    private String firstName;
    private String lastName;
    private Date dateApply;
    // methods


    public Ticket() { //default
    }

    public Ticket(String numberOfTicket, String startPoint, String destination, String firstName, String lastName, Date dateApply) throws IllegalArgumentException {
        String EMPTY = "";

        if (!numberOfTicket.equals(EMPTY)) this.numberOfTicket = numberOfTicket;
        else throw new IllegalArgumentException(Messages.EMPTY_NUMBER);

        if (!startPoint.equals(EMPTY)) this.startPoint = startPoint;
        else throw new IllegalArgumentException(Messages.EMPTY_START_POINT);

        if (!destination.equals(EMPTY)) this.destination = destination;
        else throw new IllegalArgumentException(Messages.EMPTY_DESTINATION);

        if (!firstName.equals(EMPTY)) this.firstName = firstName;
        else throw new IllegalArgumentException(Messages.EMPTY_FIRST_NAME);

        if (!lastName.equals(EMPTY)) this.lastName = lastName;
        else throw new IllegalArgumentException(Messages.EMPTY_LAST_NAME);

        this.dateApply = dateApply;
    }

    //Setters
    public void setNumberOfTicket(String numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void getDateAply(Date dateAply) { this.dateApply = dateAply; }

    // getters

    public String getNumberOfTicket() {
        return numberOfTicket;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getDestination() {
        return destination;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateApply() { return dateApply; }

    @Override
    public String toString() {
        return "Ticket{" +
                "numberOfTicket = " + numberOfTicket + '\n' +
                ", startPoint = " + startPoint + '\n' +
                ", destination = " + destination + '\n' +
                ", firstName = " + firstName + '\n' +
                ", lastName = " + lastName + '\n' +
                ", date Apply = " + DateFormat.getDateInstance(DateFormat.LONG).format(dateApply) + '\n' +
                '}';
    }


}
