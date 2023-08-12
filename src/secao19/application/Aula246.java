package secao19.application;

import secao19.entities.Access;

import java.text.ParseException;

public class Aula246 {
    public static void main(String[] args) throws ParseException {
        Access teste1 = new Access();

        teste1.setName("amanda");
        teste1.setDtAccess("2018-08-26T20:45:08Z");

        System.out.println(teste1.getName()+"\n"+teste1.getDtAccess());
    }
}
