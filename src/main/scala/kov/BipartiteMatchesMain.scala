package kov

import kov.graph.FlowGraph
import kov.util.{FlowBuilder, GraphConsoleReader, Logger}

object BipartiteMatchesMain {
  def main(args: Array[String]): Unit = {
    val reader = new GraphConsoleReader()
    val adjMatrix = read(reader)
    val graph = graph(adjMatrix)
    val correspondences = new BipartiteMatches().findMatches(graph, adjMatrix.size, adjMatrix(0).size)
    val answer = for {
      i <- adjMatrix.indices
      index = i + 1
    } yield correspondences.getOrElse(index, -1)
    Logger.info(answer.mkString(" "))
  }

  /**
   * Read i j dims to parse bipartite pairs as adjacency matrix
   * @param read
   * @return
   */
  def read(read: GraphConsoleReader): Vector[Vector[Boolean]] = {
    val numLeft: Int = read.nextInt
    val numRight: Int = read.nextInt
    val adjMatrix = Array.ofDim[Boolean](numLeft, numRight)


    for {
      i <- 0 until numLeft
      j <- 0 until numRight
    } adjMatrix(i)(j) = read.nextInt == 1

    adjMatrix.map(_.toVector).toVector
  }

  def graph(adj: Vector[Vector[Boolean]]): FlowGraph = {
    val firstClassCount = adj.size
    val secondClassCount = adj(0).size
    val nodeCount = firstClassCount + secondClassCount

    val source = 0
    val sink = nodeCount + 1
    val g = new FlowBuilder(nodeCount + 2)

    for {
      i <- adj.indices
      j <- adj(0).indices
      hasEdge = adj(i)(j)
      if hasEdge
    } {
      g.addEdge(i + 1, j + firstClassCount + 1, 1)
    }

    for (i <- 1 to firstClassCount) g.addEdge(source, i, 1)
    for (i <- firstClassCount + 1 to firstClassCount + secondClassCount) g.addEdge(i, sink, 1)

    g.build
  }


}
