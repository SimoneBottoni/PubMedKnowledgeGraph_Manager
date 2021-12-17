package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation.semanticScholar;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.DisambiguationKey;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.DisambiguationAuthor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HandlerSemanticScholar {
    Set<DisambiguationAuthor> disambiguationAuthorsList;

    public Set Listparsing(String path) throws Exception {
        disambiguationAuthorsList = new HashSet<>();
        Scanner scanner = new Scanner(new File(path));
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {

            String s = (String) scanner.next();
            Object obj = new JSONParser().parse(s);
            JSONObject jo = (JSONObject) obj;

            String pmid = (String) jo.get("pmid");
            if(!pmid.isEmpty()) {
                JSONArray authors = (JSONArray) jo.get("authors");

                for (Object o : authors) {
                    JSONObject author = (JSONObject) o;
                    String Name = (String) author.get("name");
                    JSONArray ids = (JSONArray) author.get("ids");
                    if (ids.size() > 0) {
                        String idsString = (String) ids.get(0);
                        DisambiguationAuthor disambiguationAuthor = new DisambiguationAuthor();
                        disambiguationAuthor.setDisambiguationKey(new DisambiguationKey());
                        disambiguationAuthor.getDisambiguationKey().setAuthorID(idsString);
                        disambiguationAuthor.getDisambiguationKey().setPmid(pmid);
                        disambiguationAuthor.setSource("SemanticScholar");
                        disambiguationAuthor.setName(Name);
                        disambiguationAuthorsList.add(disambiguationAuthor);
                    }
                }
            }
        }
        return disambiguationAuthorsList;
    }

    public Set<DisambiguationAuthor> getDisambiguationAuthorsList() {
        return disambiguationAuthorsList;
    }
}
