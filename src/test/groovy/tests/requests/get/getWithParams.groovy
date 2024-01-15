package tests.requests.get

import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.ResponseDto

import static tests.TestUtils.*;

ResponseDto.SingleValueResultWithParams result = connector.jsonRpcGet("employee", new Query().put("UUID", ut.eq('employee$12192601')), ["UUID", "title", "login"])
logger.info(result.result.toString())
