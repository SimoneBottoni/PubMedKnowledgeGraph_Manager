package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.graph;

public enum Colors {

    // Nodes
    Article("red"),
    Book("magenta"),
    Journal("purple"),
    Author("blue"),
    Identifier("cyan"),
    Affiliation("teal"),
    GrantAgency("green"),
    ELocationID("gray"),
    Language("cool-gray"),
    SpaceFlightMission("warm-gray"),
    GeneSymbol("red"),
    AccessionNumber("magenta"),
    PublicationType("purple"),
    ArticleDate("blue"),
    Chemical("cyan"),
    SupplMeshName("teal"),
    CitationSubset("green"),
    CommentsCorrections("gray"),
    MeshHeading("cool-gray"),
    OtherID("warm-gray"),
    OtherAbstract("red"),
    Keyword("magenta"),
    GeneralNote("purple"),
    History("blue"),
    ArticleID("cyan"),
    Object("teal"),
    Reference("green"),
    Tag("gray"),
    Publisher("cool-gray"),
    Item("warm-gray"),
    ISBN("red"),
    LocationLabel("magenta"),
    Section("purple"),

    // eEdges
    isin("red"),
    writes("magenta"),
    has_id("purple"),
    works_for("blue"),
    wins("cyan"),
    has_eID("teal"),
    has_language("green"),
    has_spaceFlightMission("gray"),
    has_geneSymbol("cool-gray"),
    has_accessionNumber("warm-gray"),
    has_publicationType("red"),
    has_articleDate("magenta"),
    has_chemical("purple"),
    has_supplMeshName("blue"),
    has_citationSubset("cyan"),
    has_commentsCorrections("teal"),
    has_meshHeading("green"),
    has_otherID("gray"),
    has_otherAbstract("cool-gray"),
    has_keyword("warm-gray"),
    has_generalNote("red"),
    has_history("magenta"),
    has_articleID("purple"),
    has_object("blue"),
    has_reference("cyan"),
    has_tag("teal"),
    published_by("green"),
    has_item("gray"),
    has_isbn("cool-gray"),
    has_location("warm-gray"),
    has_section("red");

    private final String value;

    Colors(String value) {
        this.value = value;
    }

    public static String getHexColor(String value) {
        switch (value) {
            case "red":
                return "#ffb3b8";
            case "magenta":
                return "#ffafd2";
            case "purple":
                return "#d4bbff";
            case "blue":
                return "#a6c8ff";
            case "cyan":
                return "#82cfff";
            case "teal":
                return "#3ddbd9";
            case "green":
                return "#6fdc8c";
            case "gray":
                return "#c6c6c6";
            case "cool-gray":
                return "#c1c7cd";
            case "warm-gray":
                return "#cac5c4";
        }
        return "#ffb3b8";
    }

    @Override
    public String toString() {
        return value;
    }

}
