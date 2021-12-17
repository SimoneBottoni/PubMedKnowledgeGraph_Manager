package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.authordisambiguation;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.DisambiguationAuthor;

import java.util.List;
import java.util.stream.Collectors;

public class SolveDisambiguation
{

    public String getAuthorityID(List<DisambiguationAuthor> disambiguationAuthorList, String PMID, String foreName, String lastName) {
        List<DisambiguationAuthor> authorityList =  disambiguationAuthorList.stream().filter(x -> x.getSource().equals("Authority") && x.getDisambiguationKey().getPmid().equals(PMID)).collect(Collectors.toList());
        String authorityID = null;
        if(lastName!=null) {
            List<DisambiguationAuthor> authorityList2 = authorityList.stream().filter(x -> x.getLastname().contains(lastName)).collect(Collectors.toList());
            if (authorityList2.size() == 1) {
                authorityID = authorityList2.get(0).getDisambiguationKey().getAuthorID();
            } else if (authorityList2.size() > 1 && foreName!=null) {
                List<DisambiguationAuthor> authorityList3 = authorityList2.stream().filter(x -> x.getFirstname().contains(foreName)).collect(Collectors.toList());
                if (authorityList3.size() == 1) {
                    authorityID = authorityList3.get(0).getDisambiguationKey().getAuthorID();
                }
            }
        }
        return authorityID;
    }


    public String getSemanticScholarID(List<DisambiguationAuthor> disambiguationAuthorList, String PMID, String foreName, String lastName) {
        List<DisambiguationAuthor> semanticScholarList =  disambiguationAuthorList.stream().filter(x ->  x.getSource().equals("SemanticScholar") && x.getDisambiguationKey().getPmid().equals(PMID)).collect(Collectors.toList());
        String semanticScholarID = null;
        if(lastName!=null && semanticScholarList.size()>0) {
            List<DisambiguationAuthor> semanticScholarList2 = semanticScholarList.stream().filter(x -> x.getName().contains(lastName)).collect(Collectors.toList());
            if (semanticScholarList2.size() == 1) {
                semanticScholarID = semanticScholarList2.get(0).getDisambiguationKey().getAuthorID();
            } else if (semanticScholarList2.size() > 1 && foreName != null) {
                List<DisambiguationAuthor> semanticScholarList3 = semanticScholarList2.stream().filter(x -> x.getName().contains(foreName)).collect(Collectors.toList());
                if (semanticScholarList3.size() == 1) {
                    semanticScholarID = semanticScholarList3.get(0).getDisambiguationKey().getAuthorID();
                }
            }
        }
        return semanticScholarID;

    }


}
