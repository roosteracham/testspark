package Graph.图的两种表示方法.邻接表表示方法;

import Graph.图的两种表示方法.邻接矩阵表示.Edge;
import Graph.图的两种表示方法.邻接矩阵表示.GraphNode;

import java.util.Scanner;

/**
 * 图节点定义
 */
public class GNode {

    int nv;
    int ne;
    VNode[] g; // 邻接表

    // 创建一个有v个顶点但没有边的图
    public void createGraph(int v) {
        this.nv = v;
        this.ne = 0;
        this.g = new VNode[v];
        for (int i = 0; i < v; i++) {
            VNode vNode = new VNode();
            g[i] = vNode;
        }
    }

    // 插入边
    public void insert(Edge edge) {

        // 插入<v1，v2>
        AdjNode node1 = new AdjNode();
        node1.vi = edge.v2;
        node1.weight = edge.weight;
        node1.next = this.g[edge.v1].firstEdge;
        this.g[edge.v1].firstEdge = node1;

        // 若是无向图还要插入<v2,v1>
        AdjNode node2 = new AdjNode();
        node2.vi = edge.v1;
        node2.weight = edge.weight;
        node2.next = this.g[edge.v2].firstEdge;
        this.g[edge.v2].firstEdge = node2;
    }

    // 构建图
    public void buildGraph() {

        // 创建只有顶点的图
        createGraph(4);

        Scanner scanner = new Scanner(System.in);
        Edge edge = new Edge();
        // 插入3条边
        for (int i = 0; i < 3; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int weight = scanner.nextInt();
            edge.v1 = v1;
            edge.v2 = v2;
            edge.weight = weight;
            insert(edge);
        }

        for (int i = 0; i < 4; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            GraphNode node = new GraphNode();
            node.a = a;
            node.b = b;
            g[i].data = node;
        }
    }
}
