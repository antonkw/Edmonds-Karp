package kov

import kov.bfs.BFS
import kov.graph.FlowGraph
import kov.util.Logger

import scala.annotation.tailrec

class EdmondsKarp {

  def maxFlow(graph: FlowGraph) = {
    @tailrec
    def maxFlowIteration(graph: FlowGraph, sum: Int = 0, bfsSink: FlowGraph => Option[List[Int]] = BFS.bfsMaxFromMin): Int = {
      bfsSink(graph) match {
        case Some(p) =>
          val e = p.sliding(2)
            .map { case List(a, b) => (a, b) }.toList
            .flatMap {
              case (from, to) =>
                graph.getIds(from)
                  .map(id => (id, graph.getEdge(id)))
                  .find(e => e._2.to == to && e._2.flow < e._2.capacity)
            }

          val min = e.map { case (_, e) => e.capacity - e.flow }.min
          val updatedGraph = e.map(_._1).foldLeft(graph) { case (g, id) => g.addFlow(id, min) }
          Logger.debug(s"Path ${p.mkString("->")} is enriched with $min")
          maxFlowIteration(updatedGraph, sum + min)

        case None => sum
      }
    }

    maxFlowIteration(graph)
  }
}
