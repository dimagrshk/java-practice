package com;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;





public class Main {

    private static final Logger log = Logger.getLogger(Main.class);


    public static void main(String[] args) {
        // TODO input from console
        String pathFile = "tickets.csv";
        List<Ticket> list = new ArrayList<>();
        list = readFile(pathFile, list);

        list.forEach(System.out::println);

        List<IdTicket> idList = new ArrayList<>();
        list.forEach(ticket -> {
            IdTicket it = null;
            try {
                it = new IdTicket(
                        ticket.getNumberOfTicket(),
                        ticket.getStartPoint(),
                        ticket.getDestination(),
                        ticket.getFirstName(),
                        ticket.getLastName(),
                        ticket.getDateApply()
                );
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
            }
            idList.add(it);
        });

        idList.sort(Comparator.naturalOrder());
        idList.forEach(System.out::println);

        String json = collectionToJson(idList);
        String finalMessage = jsonToFile(json, pathFile.replace(Messages.CSV_FORMAT,Messages.JSON_FORMAT));
        log.info(finalMessage);
    }

    public static Ticket lineToTicket(String [] ss) {
        Ticket result = null;
        Date dateApply = stringToDate(ss[5]);
        if (dateApply == null) {
            return result;
        }
        else {
            try {
                result = new Ticket(
                        ss[0],
                        ss[1],
                        ss[2],
                        ss[3],
                        ss[4],
                        dateApply
                );
            }
            catch (IllegalArgumentException e){
                log.error(e.getMessage());
            }
        }
        return result;
    }


    public static Date stringToDate(String s) {

        Date result = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Messages.DATE_PATTERN);
            result = dateFormat.parse(s);
            return result;
        }
        catch(ParseException e) {
            log.info(e.getMessage());
            return result;
        }
    }

    public static <A> List readFile(String path, List<A> list){
        BufferedReader br = null;
        String line;
        String csvSplitBy = ",";
        // TODO change class csv

        try {
            br = new BufferedReader(new FileReader(path));
            String _ = br.readLine();
            while ((line = br.readLine()) != null) {
                String [] ticketBuffer = line.split(csvSplitBy);
                A ticket = (A) lineToTicket(ticketBuffer);
                if (ticket != null) {
                    list.add(ticket);
                    log.info(Messages.ADDED_MESSAGE);
                } else {
                    log.info(Messages.DECLINED_MESSAGE);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return list;
    }



    public static <A> String collectionToJson(List<A> objects) {
        String s = null;
        ObjectMapper mapper = new ObjectMapper();
        try {

            s = mapper.writeValueAsString(objects);
            log.info(Messages.CONFIRM_TRANSFORM);
            return s;

        } catch (JsonGenerationException e) {
            log.error(e.getMessage());
        } catch (JsonMappingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private static String jsonToFile(String json, String path) {
        if (json.equals(null)){
            return "Invalid json: " + json;
        }
        else {
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                writer.write(json);
                writer.close();
                return Messages.CONFIRM_SAVE;
            } catch (IOException e){
                return e.getMessage();
            }

        }
    }
}
