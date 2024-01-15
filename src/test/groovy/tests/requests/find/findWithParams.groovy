package tests.requests.find

import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.ResponseDto

import static tests.TestUtils.*;

ResponseDto.MultipleValueResultWithParams result = connector.jsonRpcFind("employee", new Query().put("title", ut.like('%Буров%')), ["UUID", "title"])
logger.info(result.result.toString())
