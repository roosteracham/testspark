package Graph.图的两种表示方法.邻接矩阵表示;


import java.util.Scanner;

/**
 * 临界矩阵保存图
 * 1、使用临界矩阵保存顶点的关系，即边的详情，包括便对应的顶点和变得权重
 * 2、使用一个顶点数组保存各个顶点信息
 * 缺点：
 * 使用了|nv|平方个空间存储节点，而且，对于无向图会有数据冗余，
 * 另外不适用于稀疏矩阵，稀疏矩阵里面包含很多无效的数据（两个顶点之间没有变的话）
 */
public class GraphDetail {

    public static final int INFINITY = Integer.MAX_VALUE;
    public static int maxV;
    // 顶点的个数
    public int nv;

    // 边的个数
    public int ne;

    // 邻接矩阵
    public int[][] graphRec= new int[maxV][maxV];

    // 顶点数据
    public GraphNode[] nodes = new GraphNode[maxV];

    // 创建一个空的图
    // 只有顶点，但没有边的图
    public void createEmptyGraph(int vertexNum) {
        this.nv = vertexNum;
        this.ne = 0;

        for (int i = 0; i < this.nv; i++) {
            for (int j = 0; j < this.nv; j++) {
                graphRec[i][j] = INFINITY;
            }
        }
    }

    // 插入一个节点, 只需要更新邻接矩阵的权重
    public void insertEdge(Edge edge) {
        graphRec[edge.v1][edge.v2] = edge.weight;
        graphRec[edge.v2][edge.v1] = edge.weight;
    }

    public static void main(String[] args) {
        GraphDetail graphDetail = new GraphDetail();
        graphDetail.createEmptyGraph(4);
        int[][] rec = graphDetail.graphRec;

        int edgeNum = 3;

        Scanner scanner = new Scanner(System.in);

        Edge edge = new Edge();


        // 构建邻接矩阵
        for (int i = 0; i < edgeNum; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int weight = scanner.nextInt();

            edge.v1 = v1;
            edge.v2 = v2;
            edge.weight = weight;

            graphDetail.insertEdge(edge);
        }

        // 存储顶点信息
        for (int i = 0; i < 4; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            GraphNode node = new GraphNode();
            node.a = a;
            node.b = b;
            graphDetail.nodes[i] = node;
        }

    }


}
