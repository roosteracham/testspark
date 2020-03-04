package Graph.最小生成树;

import Graph.图的两种表示方法.邻接矩阵表示.Edge;
import Graph.图的两种表示方法.邻接矩阵表示.GraphDetail;
import Graph.图的两种表示方法.邻接表表示方法.GNode;

/**
 * 对有N个顶点的连通网图（边具有权值）来说，他的最小生成树由各个顶点和N-1条边组成
 *
 * 时间复杂度为0（V2),适用于稠密矩阵
 */
public class Prim {

    private static final int ERROR = -1;

    /**
     *
     * @param graph 原图
     * @param mst 最小生成树构成的图，一定是一个稀疏矩阵
     */
    public int prim(GraphDetail graph, GNode mst) {
        // 每个顶点到当前树的最小距离
        int[] dist = new int[graph.nv];
        // 当前树的权重
        int totalWeight = 0;
        // 收录的顶点个数
        int vCount = 0;
        // 初始化邻接表
        mst = new GNode();
        mst.createGraph(graph.nv);
        // 当前树的生成过程, 表示每个顶点的父顶点
        int[] parent = new int[graph.nv];
        int v = 0, w = 0;
        // 边信息
        Edge edge = new Edge();

        // 初始化，默认初始点下标是0
        for (v = 0; v < graph.nv; v++) {
            dist[v] = graph.graphRec[0][v];
            parent[v] = 0;
        }

        // 开始构建最小生成树
         dist[0] = 0;
        vCount++;
        // 表示V0为根节点，没有父顶点
        parent[0] = -1;
        while (true) {
            v = findMinDist(graph, dist);
            if (v == ERROR) {
                break;
            }

            edge.v1 = parent[v];
            edge.v2 = v;
            edge.weight = dist[v];
            mst.insert(edge);
            totalWeight += dist[v];
            dist[v] = 0;
            vCount++;

            for (w = 0; w < graph.nv; w++) {
                if (dist[w] != 0 && graph.graphRec[v][w] < Integer.MAX_VALUE) {
                    if (graph.graphRec[v][w] < dist[w]) {
                        dist[w] = graph.graphRec[v][w];
                        parent[w] = v;
                    }
                }
            }
        }
        if (vCount < graph.nv) {
            totalWeight = ERROR;
        }
        return totalWeight;
    }

    private int findMinDist(GraphDetail graph, int[] dist) {
        int minV = 0, v = 0;
        int minDist = Integer.MAX_VALUE;
        for (; v < graph.nv; v++) {
            if (dist[v] != 0 && dist[v] < minDist) {
                minDist =  dist[v];
                minV = v;
            }
        }
        if (minDist < Integer.MAX_VALUE) {
            return minV;
        }
        return ERROR;
    }
}
