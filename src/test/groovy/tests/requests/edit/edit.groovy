package tests.requests.edit

import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.ResponseDto

import static tests.TestUtils.*;

Attrs attrs = new Attrs()
attrs.put("shortDescr", "12313123123")
attrs.put("descriptionRTF", "3213123123123")
ResponseDto.SingleValueResult result = connector.jsonRpcEdit('serviceCall$59319750', attrs)
logger.info(result.result.toString())