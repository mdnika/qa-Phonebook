package com.ait.phonebook.fw;

import com.ait.phonebook.models.Contact;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataproviders {

  @DataProvider
  public Iterator<Object[]> addNewContact(){
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[] {"Oliver", "Kann", "1234567890", "kann@gm.com", "Berlin", "politiker"});
    list.add(new Object[] {"Karl", "Kanr", "1234567890", "kanr@gm.com", "Mainz", "politiker"});
    list.add(new Object[] {"Oliver", "Sax", "1234567890", "sax@gm.com", "Berlin", "politikerin"});
    return list.iterator();
  }
  @DataProvider
  public Iterator<Object[]> addNewContactFromCSV() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader
        (new File("src/test/resources/contact.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(",");
      list.add(new Object[]{new  Contact().setName(split[0])
          .setSurname(split[1])
          .setPhone(split[2])
          .setEmail(split[3])
          .setAddress(split[4])
          .setDescription(split[5])
      });
      line = reader.readLine();
    }
    return list.iterator();
  }

}
