package kov

import kov.util.{GraphConsoleReader, Logger}

object EdmondsKarpMain extends App {
  val graph = new GraphConsoleReader().readGraph
  val maxFlow = new EdmondsKarp().maxFlow(graph)
  Logger.info(s"Max flow is $maxFlow")
}
