package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.compositekeys.DisambiguationKey;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.DisambiguationAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisambiguationAuthorRepository extends JpaRepository<DisambiguationAuthor, DisambiguationKey> {
 @Query("SELECT d FROM disambiguation_author d WHERE d.disambiguationKey.authorID=:authorID")
 public List<DisambiguationAuthor> getDisambiguationAuthorityByAuthorID(@Param("authorID") String authorID);

 @Query("SELECT d FROM disambiguation_author d WHERE d.disambiguationKey.pmid=:PMID")
 public List<DisambiguationAuthor> getDisambiguationAuthorityByPmid(@Param("PMID") String PMID);

 @Query("SELECT d FROM disambiguation_author d WHERE d.disambiguationKey.pmid IN (?1)")
 public List<DisambiguationAuthor> getDisambiguationAuthorityByPmids(List<String> PMIDs);
}