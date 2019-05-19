package topology

import bolt.RandomBolt
import org.apache.storm.Config
import org.apache.storm.LocalCluster
import org.apache.storm.topology.TopologyBuilder
import org.apache.storm.utils.Utils
import spout.RandomNumberSpout

fun main(args: Array<String>) {
    val builder = TopologyBuilder().apply {
        setSpout("random", RandomNumberSpout(), 10)
        setBolt("r1", RandomBolt(), 3)
            .shuffleGrouping("random")
    }
    val config = Config().apply {
        setDebug(true)
        setNumWorkers(2)
    }
    val cluster = LocalCluster()
    cluster.submitTopology("random_topology", config, builder.createTopology())
    Utils.sleep(10_000)
    cluster.killTopology("random_topology")
    cluster.shutdown()
}