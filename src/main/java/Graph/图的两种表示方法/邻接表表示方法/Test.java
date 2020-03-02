package Graph.图的两种表示方法.邻接表表示方法;

public class Test {
    public static void main(String[] args) {
        testDFS();
    }

    static void testDFS() {
        GNode gNode = new GNode();
//        gNode.createGraph(4);
        gNode.buildGraph(true);
        gNode.bfs(0);
    }
}
