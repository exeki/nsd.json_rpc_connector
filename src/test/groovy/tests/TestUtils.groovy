package tests

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams
import ru.kazantsev.nsd.json_rpc_connector.Connector
import ru.kazantsev.nsd.json_rpc_connector.ConditionUtilities

class TestUtils {
    static ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    static Logger logger = LoggerFactory.getLogger(TestUtils)
    static ConnectorParams params = ConnectorParams.byConfigFile("INT_SD_DEV")
    static Connector connector = new Connector(params)
    static ConditionUtilities ut = ConditionUtilities.getInstance()
}
