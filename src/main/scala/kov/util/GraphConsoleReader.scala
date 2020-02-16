package kov.util

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.util.StringTokenizer

import kov.graph.FlowGraph

class GraphConsoleReader {
  val in: BufferedReader = new BufferedReader(new InputStreamReader(System.in))
  var tok = new StringTokenizer("")

  @throws[IOException]
  def nextInt: Int = next.toInt

  @throws[IOException]
  def next: String = {
    while ( {
      !tok.hasMoreElements
    }) tok = new StringTokenizer(in.readLine)
    tok.nextToken
  }

  def readGraph: FlowGraph = {
    val fastScanner = new GraphConsoleReader()
    val vertexCount = fastScanner.nextInt
    val edgeCount = fastScanner.nextInt
    val graph = new FlowBuilder(vertexCount)

    for (_ <- 0 until edgeCount) {
      val from = fastScanner.nextInt - 1
      val to = fastScanner.nextInt - 1
      val capacity = fastScanner.nextInt
      graph.addEdge(from, to, capacity)
    }

    graph.build
  }
}
