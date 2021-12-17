package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph.EdgeData;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph.Legend;
import com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph.NodeData;

import java.util.*;

public class Mapper {

    private final Map<String, NodeData> nodesList;
    private final List<EdgeData> edgesList;

    private final Map<String, Legend> nodeLegends;
    private final Map<String, Legend> edgesLegends;

    public Mapper() {
        this.nodesList = new HashMap<>();
        this.edgesList = new ArrayList<>();
        this.nodeLegends = new HashMap<>();
        this.edgesLegends = new HashMap<>();
    }

    public List<NodeData> getNodesList() {
        return new ArrayList<>(nodesList.values());
    }

    public List<EdgeData> getEdgesList() {
        return edgesList;
    }

    public Map<String, Legend> getNodeLegends() {
        return nodeLegends;
    }

    public Map<String, Legend> getEdgesLegends() {
        return edgesLegends;
    }

    public void startMapping(JsonNode jsonNode, String type) {
        map(jsonNode, type);
    }

    private void map(JsonNode jsonNode, String type) {
        NodeData mainNodeData = createMainNode(type);
        if (!setPmidVersionUpdate(mainNodeData, jsonNode)) {
            return;
        }
        for (Iterator<Map.Entry<String, JsonNode>> entryIterator = jsonNode.fields(); entryIterator.hasNext(); ) {
            Map.Entry<String, JsonNode> map = entryIterator.next();
            if (map.getValue().isNull()) {
                continue;
            }
            switch (map.getKey()) {
                case "cancellationDate":
                //case "owner":
                case "status":
                //case "versionDate":
                //case "indexingMethod":
                //case "dateCompleted":
                case "dateRevised":
                case "pubModel":
                case "articleTitle":
                //case "startPage":
                //case "endPage":
                case "medlinePgn":
                case "abstractText":
                case "copyrightInformation":
                //case "vernacularTitle":
                //case "coiStatement":
                case "publicationStatus":
                case "pubDate":
                //case "beginningDate":
                //case "endingDate":
                //case "volume":
                //case "volumeTitle":
                //case "edition":
                //case "medium":
                //case "contributionDate":
                case "bookTitle":
                case "collectionTitle":
                    mainNodeData.addProperties(map.getKey(), map.getValue().asText());
                    break;
                case "articleJournalList":
                    getNodesRelationsFromListWithRelationFields(mainNodeData, map.getValue().elements(),
                            "Journal", Collections.singletonList("journal"),
                            "isin", Collections.singletonList("isoabbreviation"));
                    break;
                case "articleAuthorList":
                case "bookAuthorList":
                    for (Iterator<JsonNode> it = map.getValue().elements(); it.hasNext(); ) {
                        JsonNode subNode = it.next();
                        NodeData authorNodeData = createNodeData("Author", subNode.findValue("author"));
                        EdgeData articleAuthorEdgeData = createEdgeData("writes");
                        articleAuthorEdgeData.addProperties("role", subNode.findValue("role").asText());
                        articleAuthorEdgeData.addProperties("equalContrib", subNode.findValue("equalContrib").asText());
                        if (subNode.findValue("lastName").asText().equals("null")) {
                            authorNodeData.addProperties("lastName", subNode.findValue("collectiveName").asText());
                        }
                        authorNodeData.setPk(authorNodeData.getProperties().get("lastName").asText());
                        addToLists(authorNodeData.getLabel() + authorNodeData.getProperties().get("authorID").asText(),
                                mainNodeData, articleAuthorEdgeData, authorNodeData);
                        /*
                        getNodesRelationsFromList(authorNodeData, subNode.findValue("author").get("identifierList").elements(),
                                "Identifier", "has_id", Collections.singletonList("identifier"));

                        for (Iterator<JsonNode> subAffIt = subNode.findValue("affiliationList").elements(); subAffIt.hasNext(); ) {
                            JsonNode subAffNode = subAffIt.next();
                            NodeData affiliationNodeData = createNodeData("Affiliation", subAffNode);
                            EdgeData authorIdentifierEdgeData = createEdgeData("works_for");
                            affiliationNodeData.setPk(affiliationNodeData.getProperties().get("affiliation").asText());
                            addToLists(affiliationNodeData.getLabel() + affiliationNodeData.getProperties().get("affiliation").asText(),
                                    authorNodeData, authorIdentifierEdgeData, affiliationNodeData);

                            getNodesRelationsFromList(affiliationNodeData, subAffNode.get("identifierList").elements(),
                                    "Identifier", "has_id", Collections.singletonList("identifier"));
                        }
                        */
                    }
                    break;
                    /*
                case "articleGrantList":
                case "bookGrantList":
                    getNodesRelationsFromListWithRelationFields(mainNodeData, map.getValue().elements(),
                            "GrantAgency", Collections.singletonList("grantAgency"),
                            "wins", Arrays.asList("country", "agency"));
                    break;
                case "articleELocationIDList":
                case "bookELocationIDList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "ELocationID", "has_eID", Collections.singletonList("eLocationID"));
                    break;
                case "languageList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "Language", "has_language", Collections.singletonList("language"));
                    break;
                case "spaceFlightMissionList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "SpaceFlightMission", "has_spaceFlightMission",
                            Collections.singletonList("spaceFlightMission"));
                    break;
                case "geneSymbolList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "GeneSymbol", "has_geneSymbol", Collections.singletonList("geneSymbol"));
                    break;
                case "accessionNumberList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "AccessionNumber", "has_accessionNumber",
                            Collections.singletonList("accessionNumber"));
                    break;
                case "publicationTypeList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "PublicationType", "has_publicationType", Collections.singletonList("ui"));
                    break;
                case "articleDateList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "ArticleDate", "has_articleDate", Arrays.asList("date", "dateType"));
                    break;
                case "chemicalList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "Chemical", "has_chemical", Collections.singletonList("ui"));
                    break;
                case "supplMeshNameList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "SupplMeshName", "has_supplMeshName", Collections.singletonList("ui"));
                    break;
                case "citationSubsetList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "CitationSubset", "has_citationSubset",
                            Collections.singletonList("citationSubset"));
                    break;
                case "commentsCorrectionsList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "CommentsCorrections", "has_commentsCorrections",
                            Collections.singletonList("id"));
                    break;
                case "meshHeadingList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "MeshHeading", "has_meshHeading", Collections.singletonList("ui"));
                    break;
                case "otherIDList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "OtherID", "has_otherID", Arrays.asList("otherID", "source"));
                    break;
                case "otherAbstractList":
                    for (Iterator<JsonNode> it = map.getValue().elements(); it.hasNext(); ) {
                        JsonNode subNode = it.next();
                        NodeData otherAbstractNodeData = createNodeData("OtherAbstract", subNode);
                        otherAbstractNodeData.addProperties("language", subNode.findValue("language").asText());
                        EdgeData articleOtherAbstractEdgeData = createEdgeData("has_otherAbstract");
                        otherAbstractNodeData.setPk(otherAbstractNodeData.getProperties().get("abstractText").asText());
                        addToLists(otherAbstractNodeData.getLabel() +
                                        otherAbstractNodeData.getProperties().get("abstractText").asText() +
                                        otherAbstractNodeData.getProperties().get("language").asText(),
                                mainNodeData, articleOtherAbstractEdgeData, otherAbstractNodeData);
                    }
                    break;
                case "keywordList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "Keyword", "has_keyword", Collections.singletonList("keyword"));
                    break;
                case "generalNoteList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "GeneralNote", "has_generalNote", Arrays.asList("owner", "note"));
                    break;
                case "articleHistoryList":
                case "bookHistoryList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "History", "has_history", Arrays.asList("status", "date"));
                    break;
                case "articleIDList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "ArticleID", "has_articleID", Arrays.asList("articleId", "type"));
                    break;
                case "articleObjectList":
                case "bookObjectList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "Object", "has_object", Collections.singletonList("id"));
                    break;
                case "articleReferenceList":
                case "bookReferenceList":
                    for (Iterator<JsonNode> it = map.getValue().elements(); it.hasNext(); ) {
                        JsonNode subNode = it.next();
                        NodeData referenceNodeData = createNodeData("Reference", subNode);
                        EdgeData articleReferenceEdgeData = createEdgeData("has_reference");
                        referenceNodeData.setPk(referenceNodeData.getProperties().get("citation").asText());
                        addToLists(referenceNodeData.getLabel() + referenceNodeData.getProperties().get("citation").asText(),
                                mainNodeData, articleReferenceEdgeData, referenceNodeData);

                        getNodesRelationsFromList(referenceNodeData, subNode.get("articleIDList").elements(),
                                "ArticleID", "has_articleID", Arrays.asList("articleId", "type"));
                    }
                    break;
                     */
                case "articleTagList":
                case "bookTagList":
                    getNodesRelationsFromListWithRelationFields(mainNodeData, map.getValue().elements(),
                            "Tag", Collections.singletonList("tag"),"has_tag", Collections.singletonList("cui"));
                    break;
                    /*
                case "publisherList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "Publisher", "published_by", Arrays.asList("name", "location"));
                    break;
                case "itemList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "Item", "has_item", Arrays.asList("item", "type"));
                    break;
                case "isbnList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "ISBN", "has_isbn", Collections.singletonList("isbn"));
                    break;
                case "locationLabelList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "LocationLabel", "has_location", Collections.singletonList("location"));
                    break;
                case "sectionList":
                    getNodesRelationsFromList(mainNodeData, map.getValue().elements(),
                            "Section", "has_section", Collections.singletonList("title"));
                    break;
                     */
                default:
                    break;
            }
        }
    }

    private NodeData createMainNode(String type) {
        if (type.equals("Article")) {
            return createNodeData("Article", null);
        } else {
            return createNodeData("Book", null);
        }
    }

    private boolean setPmidVersionUpdate(NodeData mainNodeData, JsonNode jsonNode) {
        mainNodeData.addProperties("pmid", jsonNode.get("pmid").textValue());
        mainNodeData.addProperties("version", jsonNode.get("version").asText());
        mainNodeData.addProperties("update", jsonNode.get("update").asText());
        mainNodeData.setPk(jsonNode.get("pmid").textValue());
        String key = mainNodeData.getLabel() +
                mainNodeData.getProperties().get("pmid").asText() +
                mainNodeData.getProperties().get("version").asText() +
                mainNodeData.getProperties().get("update").asText();
        if (nodesList.containsKey(key)) {
            return false;
        }
        nodesList.put(key, mainNodeData);
        return true;
    }


    private NodeData createNodeData(String nodeLabel, JsonNode jsonNode) {
        NodeData nodeData = new NodeData(nodeLabel);
        setLabel(true, nodeData, null);
        if (jsonNode != null) {
            addToNodeFields(nodeData, jsonNode);
        }
        return nodeData;
    }

    private EdgeData createEdgeData(String edgeLabel) {
        EdgeData edgeData = new EdgeData(edgeLabel);
        setLabel(false, null, edgeData);
        return edgeData;
    }

    private void getNodesRelationsFromList(NodeData firstNodeData, Iterator<JsonNode> iterator, String nodeLabel,
                                           String relationLabel, List<String> pkFields) {
        while (iterator.hasNext()) {
            JsonNode subNode = iterator.next();
            NodeData nodeData = createNodeData(nodeLabel, subNode);
            nodeData.setPk(subNode.findValue(pkFields.get(0)).asText());
            EdgeData edgeData = createEdgeData(relationLabel);

            addToLists(nodeLabel + getPKFromNode(nodeData, pkFields),
                    firstNodeData, edgeData, nodeData);
        }
    }

    private void getNodesRelationsFromListWithRelationFields(NodeData firstNodeData, Iterator<JsonNode> iterator, String nodeLabel,
                                                             List<String> findValue, String relationLabel, List<String> pkFields) {
        while (iterator.hasNext()) {
            JsonNode subNode = iterator.next();
            NodeData nodeData = createNodeData(nodeLabel, null);
            nodeData.setPk(subNode.findValue(pkFields.get(0)).asText());
            findValue.forEach(value -> addToNodeFields(nodeData, subNode.findValue(value)));
            EdgeData edgeData = createEdgeData(relationLabel);
            addToRelationFields(edgeData, subNode);

            addToLists(nodeLabel + getPKFromNode(nodeData, pkFields),
                    firstNodeData, edgeData, nodeData);
        }
    }

    private String getPKFromNode(NodeData nodeData, List<String> pkFields) {
        StringBuilder stringBuilder = new StringBuilder();
        pkFields.forEach(pkField -> stringBuilder.append(nodeData.getProperties().get(pkField).asText()));
        return stringBuilder.toString();
    }

    private void addToLists(String node2MapKey, NodeData nodeData, EdgeData edgeData, NodeData nodeData2) {
        if (nodesList.containsKey(node2MapKey)) {
            edgeData.setTarget(nodesList.get(node2MapKey).getId());
        } else {
            nodesList.put(node2MapKey, nodeData2);
            edgeData.setTarget(nodeData2.getId());
        }
        edgeData.setSource(nodeData.getId());
        edgesList.add(edgeData);
    }

    private void addToNodeFields(NodeData nodeData, JsonNode jsonNode) {
        jsonNode.fields().forEachRemaining(
                entry -> {
                    if (!entry.getValue().isNull()) {
                        if (entry.getValue().isTextual() || entry.getValue().isLong()) {
                            nodeData.addProperties(entry.getKey(), entry.getValue().asText());
                        }
                    }
                }
        );
    }

    private void addToRelationFields(EdgeData edgeData, JsonNode jsonNode) {
        jsonNode.fields().forEachRemaining(
                entry -> {
                    if (!entry.getValue().isNull()) {
                        if (entry.getValue().isTextual()) {
                            edgeData.addProperties(entry.getKey(), entry.getValue().asText());
                        }
                    }
                }
        );
    }

    private void setLabel(boolean isNode, NodeData nodeData, EdgeData edgeData) {
        if (isNode) {
            if (!nodeLegends.containsKey(nodeData.getLabel())) {
                Legend legend = new Legend(nodeData.getBackgroundColor(), nodeData.getTagColor());
                nodeLegends.put(nodeData.getLabel(), legend);
            }
        } else {
            if (!edgesLegends.containsKey(edgeData.getLabel())) {
                Legend legend = new Legend(edgeData.getBackgroundColor(), edgeData.getTagColor());
                edgesLegends.put(edgeData.getLabel(), legend);
            }
        }
    }
}
