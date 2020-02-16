package kov.graph

case class FlowGraph(edges: Vector[Edge], graph: Vector[Vector[Int]]) {
  def size: Int = graph.length

  def getIds(from: Int): Vector[Int] = graph(from)

  def getEdge(id: Int): Edge = edges(id)

  def addFlow(id: Int, flow: Int): FlowGraph = {
    val edge = edges(id)
    val backwardId = if (id % 2 == 0) id + 1 else id - 1
    val backwardEdge = edges(backwardId)

    val edgesUpdated = edges
        .updated(id, edge.copy(flow = edge.flow + flow))
        .updated(backwardId, backwardEdge.copy(flow = backwardEdge.flow - flow))

    FlowGraph(edgesUpdated, graph)
  }
}