package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.controller;

import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.mapping.MapNodesAndEdgesToJson;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.ArticleAuthor;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.ArticleTag;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.luceneutil.ArticleView;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.luceneutil.SearchResponse;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.luceneutil.SearchObject;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.ArticleRepository;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.repository.TagRepository;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/article")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final EntityManager entityManager;

    @Autowired
    public ArticleController(EntityManagerFactory entityManagerFactory, ArticleRepository articleRepository, TagRepository tagRepository) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/pmid/{pmid}")
    public ResponseEntity<String> getArticleByPmid(@PathVariable String pmid) {
        MapNodesAndEdgesToJson mapNodesAndEdgesToJson = new MapNodesAndEdgesToJson();
        List<Article> articleList = articleRepository.getArticlesByPmid(pmid);
        if (articleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pmid not found.");
        }
        mapNodesAndEdgesToJson.getArticleMapping(articleList);
        return ResponseEntity.status(HttpStatus.OK).body(mapNodesAndEdgesToJson.getNodesAndEdgesJson());
    }

    @GetMapping("/tag/{cui}")
    public ResponseEntity<String> getArticleByTag(@PathVariable String cui) {
        MapNodesAndEdgesToJson mapNodesAndEdgesToJson = new MapNodesAndEdgesToJson();
        List<Article> articleList = articleRepository.getArticlesByTag(cui);
        if (articleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CUI not found.");
        }
        for (Article article : articleList) {
            ArticleTag articleTag = article.getArticleTagList().stream().filter(score -> cui.equals(score.getTag().getCUI())).findFirst().get();
            if (article.getArticleTagList().size() < 9) {
                article.setArticleTagList(article.getArticleTagList().stream().sorted(Comparator.comparing(ArticleTag::getScore)).limit(article.getArticleTagList().size()).collect(Collectors.toList()), false);
            } else {
                article.setArticleTagList(article.getArticleTagList().stream().sorted(Comparator.comparing(ArticleTag::getScore)).limit(9).collect(Collectors.toList()), false);
            }
            article.setArticleTagList(Collections.singletonList(articleTag));
        }
        if (articleList.size() < 100) {
            mapNodesAndEdgesToJson.getArticleMapping(articleList.subList(0, articleList.size()));
        } else {
            mapNodesAndEdgesToJson.getArticleMapping(articleList.subList(0, 100));
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapNodesAndEdgesToJson.getNodesAndEdgesJson());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<String> getArticleByName(@PathVariable String name) {
        String cui = tagRepository.getTagByName(name).getCUI();
        MapNodesAndEdgesToJson mapNodesAndEdgesToJson = new MapNodesAndEdgesToJson();
        List<Article> articleList = articleRepository.getArticlesByTag(cui);
        if (articleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CUI not found.");
        }
        for (Article article : articleList) {
            ArticleTag articleTag = article.getArticleTagList().stream().filter(score -> cui.equals(score.getTag().getCUI())).findFirst().get();
            if (article.getArticleTagList().size() < 9) {
                article.setArticleTagList(article.getArticleTagList().stream().sorted(Comparator.comparing(ArticleTag::getScore)).limit(article.getArticleTagList().size()).collect(Collectors.toList()), false);
            } else {
                article.setArticleTagList(article.getArticleTagList().stream().sorted(Comparator.comparing(ArticleTag::getScore)).limit(9).collect(Collectors.toList()), false);
            }
            article.setArticleTagList(Collections.singletonList(articleTag));
        }
        if (articleList.size() < 100) {
            mapNodesAndEdgesToJson.getArticleMapping(articleList.subList(0, articleList.size()));
        } else {
            mapNodesAndEdgesToJson.getArticleMapping(articleList.subList(0, 100));
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapNodesAndEdgesToJson.getNodesAndEdgesJson());
    }

    @GetMapping("/search/pmid/{pmid}")
    public ResponseEntity<Article> getSearchedArticleByPmid(@PathVariable String pmid) {
        List<Article> articleList = articleRepository.getArticlesByPmid(pmid);
        for (Article article : articleList) {
            article.setArticleTagList(article.getArticleTagList().stream().sorted(
                    (t, t1) -> Float.compare(Float.parseFloat(t1.getScore()), Float.parseFloat(t.getScore())))
                    .collect(Collectors.toList()), false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(articleList.get(0));
    }

    @PostMapping(path = "/search",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SearchResponse> fullTextSearch(@RequestBody SearchObject searchObject) {
        System.out.println(searchObject.getText());
        SearchSession searchSession = Search.session(entityManager);

        if (searchObject.getSearchType().equals("author")) {
            if (searchObject.isDateFiler()) {
                SearchResult<Article> articleSearchResultFiltered = searchSession.search(Article.class)
                        .where(f -> f.bool()
                                .must(f.match()
                                        .field("articleAuthorList.author.lastName")
                                        .field("articleAuthorList.author.collectiveName")
                                        .matching(searchObject.getText()))
                                .must(f.range()
                                        .field("articleJournalList.PubDate")
                                        .between(searchObject.getDateFilterTextLow(), searchObject.getDateFilterTextHigh())))
                        .sort(f -> f.field("articleJournalList.PubDate").asc())
                        .fetch(searchObject.getPageNumber() * searchObject.getResultsPerPage(), searchObject.getResultsPerPage());
                System.out.println(articleSearchResultFiltered.total().hitCount());
                return ResponseEntity.status(HttpStatus.OK).body(new SearchResponse(mapToArticleView(articleSearchResultFiltered.hits()), articleSearchResultFiltered.total().hitCount()));
            } else {
                SearchResult<Article> articleSearchResult = searchSession.search(Article.class)
                        .where(f -> f.match()
                                .field("articleAuthorList.author.lastName")
                                .field("articleAuthorList.author.collectiveName")
                                .matching(searchObject.getText()))
                        .sort(SearchSortFactory::score)
                        .fetch(searchObject.getPageNumber() * searchObject.getResultsPerPage(), searchObject.getResultsPerPage());
                System.out.println(articleSearchResult.total().hitCount());
                return ResponseEntity.status(HttpStatus.OK).body(new SearchResponse(mapToArticleView(articleSearchResult.hits()), articleSearchResult.total().hitCount()));
            }
        } else if (searchObject.getSearchType().equals("journal")) {
            if (searchObject.isDateFiler()) {
                SearchResult<Article> articleSearchResultFiltered = searchSession.search(Article.class)
                        .where(f -> f.bool()
                                .must(f.match()
                                        .field("articleJournalList.journal.Title")
                                        .matching(searchObject.getText()))
                                .must(f.range()
                                        .field("articleJournalList.PubDate")
                                        .between(searchObject.getDateFilterTextLow(), searchObject.getDateFilterTextHigh())))
                        .sort(f -> f.field("articleJournalList.PubDate").asc())
                        .fetch(searchObject.getPageNumber() * searchObject.getResultsPerPage(), searchObject.getResultsPerPage());
                System.out.println(articleSearchResultFiltered.total().hitCount());
                return ResponseEntity.status(HttpStatus.OK).body(new SearchResponse(mapToArticleView(articleSearchResultFiltered.hits()), articleSearchResultFiltered.total().hitCount()));
            } else {
                SearchResult<Article> articleSearchResult = searchSession.search(Article.class)
                        .where(f -> f.match()
                                .field("articleJournalList.journal.Title")
                                .matching(searchObject.getText()))
                        .sort(SearchSortFactory::score)
                        .fetch(searchObject.getPageNumber() * searchObject.getResultsPerPage(), searchObject.getResultsPerPage());
                System.out.println(articleSearchResult.total().hitCount());
                return ResponseEntity.status(HttpStatus.OK).body(new SearchResponse(mapToArticleView(articleSearchResult.hits()), articleSearchResult.total().hitCount()));
            }
        } else {
            if (searchObject.isDateFiler()) {
                SearchResult<Article> articleSearchResultFiltered = searchSession.search(Article.class)
                        .where(f -> f.bool()
                                .must(f.match()
                                        .field("articleTitle")
                                        .field("abstractText")
                                        .field("articleTagList.tag.CUI")
                                        .field("articleTagList.tag.preferredName")
                                        .field("articleAuthorList.author.lastName")
                                        .field("articleAuthorList.author.collectiveName")
                                        .field("articleJournalList.PubDate")
                                        .field("articleJournalList.journal.Title")
                                        .matching(searchObject.getText()))
                                .must(f.range()
                                        .field("articleJournalList.PubDate")
                                        .between(searchObject.getDateFilterTextLow(), searchObject.getDateFilterTextHigh())))
                        .sort(f -> f.field("articleJournalList.PubDate").asc())
                        .fetch(searchObject.getPageNumber() * searchObject.getResultsPerPage(), searchObject.getResultsPerPage());
                System.out.println(articleSearchResultFiltered.total().hitCount());
                return ResponseEntity.status(HttpStatus.OK).body(new SearchResponse(mapToArticleView(articleSearchResultFiltered.hits()), articleSearchResultFiltered.total().hitCount()));
            } else {
                SearchResult<Article> articleSearchResult = searchSession.search(Article.class)
                        .where(f -> f.match()
                                .field("articleTitle")
                                .field("abstractText")
                                .field("articleTagList.tag.CUI")
                                .field("articleTagList.tag.preferredName")
                                .field("articleAuthorList.author.lastName")
                                .field("articleAuthorList.author.collectiveName")
                                .field("articleJournalList.PubDate")
                                .field("articleJournalList.journal.Title")
                                .matching(searchObject.getText()))
                        .sort(SearchSortFactory::score)
                        .fetch(searchObject.getPageNumber() * searchObject.getResultsPerPage(), searchObject.getResultsPerPage());
                System.out.println(articleSearchResult.total().hitCount());
                return ResponseEntity.status(HttpStatus.OK).body(new SearchResponse(mapToArticleView(articleSearchResult.hits()), articleSearchResult.total().hitCount()));
            }
        }
    }

    private List<ArticleView> mapToArticleView(List<Article> articleList) {
        List<ArticleView> articleViewList = new ArrayList<>();
        articleList.forEach(article -> articleViewList.add(
                new ArticleView(article.getPmid(),
                        article.getArticleTitle(),
                        article.getAbstractText(),
                        sortArticleTag(removeDuplicatedTag(article.getArticleTagList())),
                        removeNotAuthor(article.getArticleAuthorList()),
                        article.getArticleJournalList())));
        articleList.forEach(article -> System.out.println("pmid: " + article.getPmid()));
        return articleViewList;
    }

    private List<ArticleTag> sortArticleTag(List<ArticleTag> articleTagList) {
        articleTagList.sort((articleTag1, articleTag2) -> Float.compare(
                Float.parseFloat(articleTag1.getScore()),
                Float.parseFloat(articleTag2.getScore())));
        return articleTagList;
    }

    private List<ArticleAuthor> removeNotAuthor(List<ArticleAuthor> articleAuthorList) {
        List<ArticleAuthor> authorList = new ArrayList<>();
        for (ArticleAuthor articleAuthor : articleAuthorList) {
            if (articleAuthor.getRole().equals("Author")) {
                authorList.add(articleAuthor);
            }
        }
        return authorList;
    }

    private List<ArticleTag> removeDuplicatedTag(List<ArticleTag> articleTagList) {
        Map<String, ArticleTag> articleTagMap = new HashMap<>();
        for (ArticleTag articleTag : articleTagList) {
            if (!articleTagMap.containsKey(articleTag.getTag().getCUI())) {
                articleTagMap.put(articleTag.getTag().getCUI(), articleTag);
            } else {
                if (Float.parseFloat(articleTagMap.get(articleTag.getTag().getCUI()).getScore()) <
                        Float.parseFloat(articleTag.getScore())) {
                    articleTagMap.replace(articleTag.getTag().getCUI(), articleTag);
                }
            }
        }
        return new ArrayList<>(articleTagMap.values());
    }
}
