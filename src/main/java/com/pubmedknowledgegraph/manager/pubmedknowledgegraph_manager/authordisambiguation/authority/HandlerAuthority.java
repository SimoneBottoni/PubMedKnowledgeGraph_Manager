package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation.authority;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.DisambiguationKey;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.DisambiguationAuthor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandlerAuthority {
    List<DisambiguationAuthor> disambiguationAuthorList = new ArrayList<>();

    public void Listparsing(String path) throws Exception {
        disambiguationAuthorList = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] AuthoritySplit = data.split("\t");
                if(AuthoritySplit.length>18) {
                    String id = AuthoritySplit[3];
                    String lastName = AuthoritySplit[6];
                    String firstName = AuthoritySplit[7];
                    String[] PMIDs = AuthoritySplit[18].split("\\|");
                    for (int i = 0; i < PMIDs.length; i++) {
                        DisambiguationAuthor disambiguationAuthor = new DisambiguationAuthor();
                        disambiguationAuthor.setDisambiguationKey(new DisambiguationKey());
                        disambiguationAuthor.getDisambiguationKey().setAuthorID(id);
                        disambiguationAuthor.setSource("Authority");
                        String firstName2 = Stream.of((firstName.split("\\|"))).limit(2).collect(Collectors.toList()).stream().collect(Collectors.joining("," ));
                        disambiguationAuthor.setFirstname(firstName2);
                        disambiguationAuthor.setLastname(Stream.of((lastName.split("\\|"))).limit(2).collect(Collectors.toList()).stream().collect(Collectors.joining("," )));
                        disambiguationAuthor.getDisambiguationKey().setPmid(PMIDs[i].split("_")[0]);
                        disambiguationAuthorList.add(disambiguationAuthor);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public List<DisambiguationAuthor> getAuthor() {
        return disambiguationAuthorList;
    }
}
