package Graph.图的两种表示方法.邻接表表示方法;

import Graph.图的两种表示方法.邻接矩阵表示.GraphNode;

/**
 * 顶点表头定义
 */
public class VNode {

    // 是否被遍历过
    boolean visited = false;

    // 第一个邻接点
    AdjNode firstEdge;

    // 顶点数据
    GraphNode data;
}
