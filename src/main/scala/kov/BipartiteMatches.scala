package kov

import kov.graph.FlowGraph

class BipartiteMatches {
  def findMatches(graph: FlowGraph, firstClassCount: Int, secondClassCount: Int): Map[Int, Int] = {
    val flows = new EdmondsKarp().maxFlowWithGraph(graph)
    val edges = flows
      ._2.edges
      .filter(e => e.from != 0 && e.to != firstClassCount + secondClassCount + 1 && e.flow > 0 && e.from < e.to)
    val correspondences = edges.map(e => e.from -> (e.to - firstClassCount)).toMap
    correspondences
  }

}
