package com;

import java.util.Date;

public class IdTicket extends Ticket implements Comparable<IdTicket> {

    //fields
    public static int id_count;
    public int id;

    //methods
    public IdTicket() {
        this.id_count +=1;
        this.id = id_count;
    }


    public IdTicket(String numberOfTicket, String startPoint, String destination, String firstName, String lastName, Date dateAply) throws IllegalArgumentException {
        super(numberOfTicket, startPoint, destination, firstName, lastName, dateAply);
        this.id_count +=1;
        this.id = id_count;
    }


    @Override
    public int compareTo(IdTicket o) {
        return this.getDateApply().compareTo(o.getDateApply());
    }

    @Override
    public String toString(){
        return  "ID: " + id + " " + super.toString();
    }

}
