package tests.requests.find

import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.ResponseDto

import static tests.TestUtils.*;

ResponseDto.MultipleValueResult result = connector.jsonRpcFind("employee", new Query().put("title", ut.like("%Буров%")))
logger.info(result.result.toString())