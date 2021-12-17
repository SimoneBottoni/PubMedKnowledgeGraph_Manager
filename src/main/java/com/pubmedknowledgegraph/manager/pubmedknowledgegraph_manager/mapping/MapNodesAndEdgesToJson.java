package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Article;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.db.Book;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph.Edge;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph.EdgeData;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph.Node;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph.NodeData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MapNodesAndEdgesToJson {

    private final Logger logger = LogManager.getRootLogger();

    private final JsonMapper jsonMapper;
    private final Mapper mapper;

    public MapNodesAndEdgesToJson() {
        jsonMapper = new JsonMapper();
        mapper = new Mapper();
    }

    public String getNodesAndEdgesJson() {
        return new MapObjectToJson(jsonMapper).mapToJson(
                mapper.getNodesList(), mapper.getEdgesList(),
                mapper.getNodeLegends(), mapper.getEdgesLegends());
    }

    public void getArticleMapping(List<Article> objectList) {
        for (Article article : objectList) {
            getNodeAndRelation(article, "Article");
        }
    }

    public void getBookMapping(List<Book> objectList) {
        for (Object object : objectList) {
            getNodeAndRelation(object, "Book");
        }
    }

    private JsonNode createJsonNode(Object object) {
        return jsonMapper.valueToTree(object);
    }

    private void getNodeAndRelation(Object object, String type) {
        JsonNode jsonNode = createJsonNode(object);
        mapper.startMapping(jsonNode, type);
    }

    private List<Node> getNodesFromNodeData(List<NodeData> nodeDataList) {
        List<Node> nodes = new ArrayList<>();
        nodeDataList.forEach(nodeData -> nodes.add(new Node(nodeData)));
        return nodes;
    }

    private List<Edge> getEdgesFromEdgeData(List<EdgeData> edgeDataList) {
        List<Edge> edges = new ArrayList<>();
        edgeDataList.forEach(edgeData -> edges.add(new Edge(edgeData)));
        return edges;
    }

}
