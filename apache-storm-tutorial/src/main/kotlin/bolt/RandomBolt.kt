package bolt

import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Values

class RandomBolt : BaseRichBolt() {
    var _collector: OutputCollector? = null

    override fun prepare(stormConf: MutableMap<Any?, Any?>?, context: TopologyContext?, collector: OutputCollector?) {
        _collector = collector
    }

    override fun execute(input: Tuple?) {
        val value = input?.getInteger(0) ?: 0
        _collector?.emit(input, Values(value * 2, value * 3))
        _collector?.ack(input)
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer?) {
        declarer?.declare(Fields("integer"))
    }
}