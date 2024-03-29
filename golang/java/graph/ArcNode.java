package com.cn.graph;

public class ArcNode {
	int adjvex;// 表头顶点的邻接顶点编号
	int data;// 边的信息
	int edge;
	ArcNode nextArc;// 指向下一个邻接顶点的指针

	public ArcNode(int adjvex, int data, int edge, ArcNode nextArc) {
		this.adjvex = adjvex;
		this.data = data;
		this.nextArc = nextArc;
		this.edge = edge;
	}

	public int getAdjvex() {
		return adjvex;
	}

	public void setAdjvex(int adjvex) {
		this.adjvex = adjvex;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public ArcNode getNextArc() {
		return nextArc;
	}

	public void setNextArc(ArcNode nextArc) {
		this.nextArc = nextArc;
	}
}
