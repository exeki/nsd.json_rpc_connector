package tests.requests.edit

import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*;

Attrs attrs = new Attrs()
attrs.put("shortDescr", "123131sdfsdfsdfsdfsf23123")
attrs.put("descriptionRTF", "3213123asfasfwsdefs123123")
Query query = new Query().put("UUID", ut.eq('serviceCall$59374017'))
RpcRequestDto.Edit dto = new RpcRequestDto.Edit("serviceCall", query, attrs)
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
