package kov.util

import kov.graph.{Edge, FlowGraph}

import scala.collection.mutable.ArrayBuffer

class FlowBuilder {
  private val edges: ArrayBuffer[Edge] = ArrayBuffer();

  private var graph: Array[ArrayBuffer[Int]] = _
  def this(n: Int) = {
    this()
    this.graph = (for (_ <- 0 until n) yield ArrayBuffer[Int]()).toArray;
  }

  def addEdge(from: Int, to: Int, capacity: Int) {
    val forwardEdge = Edge(from, to, capacity, 0)
    //residual capacity is equal to original capacity of the edge minus current flow (0)
    val backwardEdge = Edge(to, from, capacity, capacity)
    graph(from) += edges.size
    edges += forwardEdge
    graph(to) += edges.size
    edges += backwardEdge
  }

  def build = FlowGraph(edges.toVector, graph.map(_.toVector).toVector)
}
