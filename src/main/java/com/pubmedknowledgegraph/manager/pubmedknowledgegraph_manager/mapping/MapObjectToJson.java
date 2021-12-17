package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.mapping;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapObjectToJson {

    private final JsonMapper jsonMapper;

    public MapObjectToJson(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public String mapToJson(List<NodeData> nodeList, List<EdgeData> edgeList,
                            Map<String, Legend> nodesLegends, Map<String, Legend> edgesLegends) {

        ObjectNode nodesAndEdges = jsonMapper.createObjectNode();
        nodesAndEdges.putPOJO("nodes", nodeList);
        nodesAndEdges.putPOJO("links", edgeList);

        ObjectNode nodesAndEdgesLegends = jsonMapper.createObjectNode();
        nodesAndEdgesLegends.putPOJO("nodeLegend", addNodesLegendsObject(new TreeMap<>(nodesLegends)));
        nodesAndEdgesLegends.putPOJO("edgeLegend", addEdgesLegendsObject(new TreeMap<>(edgesLegends)));

        ObjectNode result = jsonMapper.createObjectNode();
        result.putPOJO("elements", nodesAndEdges);
        result.putPOJO("legend", nodesAndEdgesLegends);

        return result.toPrettyString();
    }

    private ObjectNode addNodesLegendsObject(Map<String, Legend> nodesLegends) {
        ObjectNode objectNode = jsonMapper.createObjectNode();
        nodesLegends.forEach(objectNode::putPOJO);
        return objectNode;
    }

    private ObjectNode addEdgesLegendsObject(Map<String, Legend> edgesLegends) {
        ObjectNode objectNode = jsonMapper.createObjectNode();
        edgesLegends.forEach(objectNode::putPOJO);
        return objectNode;
    }

}
