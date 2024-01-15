package tests.requests.edit

import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.ResponseDto

import static tests.TestUtils.*;

Attrs attrs = new Attrs()
attrs.put("shortDescr", "twrhwrthrwth")
attrs.put("descriptionRTF", "dfhthrwthrw")
ResponseDto.SingleValueResultWithParams result = connector.jsonRpcEdit('serviceCall$59319750', attrs, ["UUID", "title"])
logger.info(result.result.toString())