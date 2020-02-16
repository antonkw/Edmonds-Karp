package kov.bfs

import kov.graph.FlowGraph
import kov.util.Logger

import scala.annotation.tailrec
import scala.collection.immutable.{BitSet, Queue}

object BFS {
  def bfs(root: Int, target: Int, graph: FlowGraph): Option[List[Int]] = {
    @tailrec
    def iteration(queue: Queue[Int], visited: BitSet, trace: Map[Int, Int]): Option[List[Int]] = {
      Logger.debug(s"Queue $queue visited $visited trace $trace")
      if (queue.nonEmpty) {
        val (node, remained) = queue.dequeue

        if (node != target) {
          val edges = graph.getIds(node).map(graph.getEdge).filter(e => e.flow < e.capacity && !visited.contains(e.to))
          val (visitedUpd, traceUpd, remainedUpd) = edges.foldLeft((visited, trace, remained)) {
            case ((visited, trace, queue), edge) => (visited + edge.to, trace + (edge.to -> node), queue.enqueue(edge.to))
          }
          iteration(remainedUpd, visitedUpd, traceUpd)
        } else {
          Some(tracePath(root, target, List(), trace))
        }
      } else {
        None
      }
    }

    iteration(Queue(root), BitSet(), Map())
  }

  def bfsMaxFromMin(graph: FlowGraph): Option[List[Int]] = bfs(0, graph.size - 1, graph)

  @tailrec
  private def tracePath(root: Int, toTrace: Int, path: List[Int], traceMap: Map[Int, Int]): List[Int] = {
    if (toTrace == root) root :: path
    else tracePath(root, traceMap(toTrace), toTrace :: path, traceMap)
  }
}
