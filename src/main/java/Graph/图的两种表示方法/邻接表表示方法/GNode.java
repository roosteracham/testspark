package Graph.图的两种表示方法.邻接表表示方法;

import Graph.图的两种表示方法.邻接矩阵表示.Edge;
import Graph.图的两种表示方法.邻接矩阵表示.GraphNode;

import java.util.LinkedList;
import java.util.List;
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

        // 若是无向图还要插入<v2,v1>
        AdjNode node2 = new AdjNode();
        node2.vi = edge.v1;
        node2.weight = edge.weight;
        node2.next = this.g[edge.v2].firstEdge;
        this.g[edge.v2].firstEdge = node2;

        // 插入<v1，v2>
        AdjNode node1 = new AdjNode();
        node1.vi = edge.v2;
        node1.weight = edge.weight;
        node1.next = this.g[edge.v1].firstEdge;
        this.g[edge.v1].firstEdge = node1;
    }

    // 构建图
    public void buildGraph(boolean bfs) {

        if (bfs) {
            stack = new LinkedList<>();
        }

        // 创建只有顶点的图
        createGraph(8);

        Scanner scanner = new Scanner(System.in);
        Edge edge = new Edge();
        // 插入3条边
        for (int i = 0; i < 9; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
//            int weight = scanner.nextInt();
            edge.v1 = v1;
            edge.v2 = v2;
//            edge.weight = weight;
            insert(edge);
        }

//        for (int i = 0; i < 4; i++) {
//            int a = scanner.nextInt();
//            int b = scanner.nextInt();
//            GraphNode node = new GraphNode();
//            node.a = a;
//            node.b = b;
//            g[i].data = node;
//        }
    }

    // visit
    public void visit(int v) {
        g[v].visited = true;
        System.out.println("visiting node: " + v + ", data: " + g[v].data);
    }

    // 深度优先遍历
    // 适用于邻接表存储的图，时间复杂度为O(V + E)
    public void dfs(int v) {
        // 先访问节点
        visit(v);

        AdjNode node = g[v].firstEdge;

        // 递归访问，从下一个临界点作为顶点开始访问
        for (; node != null; node = node.next) {
            VNode nextAdjNode = g[node.vi];
            if (!nextAdjNode.visited) {
                dfs(node.vi);
            }
        }
    }

    public List<Integer> stack;
    // 广度优先搜索
    public void bfs(int v) {
        // 访问v这个顶点
        visit(v);
        for (AdjNode node = g[v].firstEdge; node != null; node = node.next) {
            stack.add(node.vi);
        }
        while (!stack.isEmpty()) {
            Integer vi = stack.remove(0);
            if (!g[vi].visited) {
                bfs(vi);
            }
        }
    }
}
