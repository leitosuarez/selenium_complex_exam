package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public  class FileCsvScanner {
    String filePath = "src/test/resources/valid_users.csv";

    public ArrayList<String> validUsernameScanner() {
        ArrayList<String> validUsernames = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { //write here close file automatic after try

            String linea;
            //boolean esHeader = true;

            while ((linea = br.readLine()) != null){
                //System.out.println(linea);
                if (!(linea.contains("username,password"))){
                    String[] row = linea.split(",");
                    validUsernames.add(row[0]);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return validUsernames;
    }


}
