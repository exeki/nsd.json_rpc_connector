package tests.requests.edit

import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.ResponseDto

import static tests.TestUtils.*;

Attrs attrs = new Attrs()
attrs.put("shortDescr", "123131sdfsdfsdfsdfsf23123")
attrs.put("descriptionRTF", "3213123asfasfwsdefs123123")
ResponseDto.SingleValueResult result = connector.jsonRpcEdit(new Query().put("UUID", ut.eq('serviceCall$59319750')), attrs)
logger.info(result.result.toString())