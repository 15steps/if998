package spout

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values
import org.apache.storm.utils.Utils
import kotlin.random.Random

class RandomNumberSpout : BaseRichSpout() {
    var _collector: SpoutOutputCollector? = null

    override fun nextTuple() {
        Utils.sleep(1000)
        val value = Random.nextInt(0, 100)
        println("emitting value $value")
        _collector?.emit(Values(value))
    }

    override fun open(conf: MutableMap<Any?, Any?>?, context: TopologyContext?, collector: SpoutOutputCollector?) {
        _collector = collector
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer?) {
        declarer?.declare(Fields("integer"))
    }
}