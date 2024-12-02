package tests.requests.edit

import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*;

HashMap attrs = new HashMap<>()
attrs.put("shortDescr", "123131sdfsdfsdfsdfsf23123")
attrs.put("descriptionRTF", "3213123asfasfwsdefs123123")
HashMap query = new HashMap()
query.put("UUID", ut.eq('serviceCall$59374017'))
RpcRequestDto.Edit dto = new RpcRequestDto.Edit("serviceCall", query, attrs)
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
