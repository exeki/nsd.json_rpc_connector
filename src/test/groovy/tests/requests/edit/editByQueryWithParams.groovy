package tests.requests.edit

import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*;

HashMap attrs = new HashMap()
attrs.put("shortDescr", "qweasdzxc")
attrs.put("descriptionRTF", "qweasdzxc")
HashMap query = new HashMap()
query.put("UUID", ut.eq('serviceCall$59319750'))
RpcRequestDto.Edit dto = new RpcRequestDto.Edit("serviceCall", query, attrs)
dto.setView(["UUID", "title"])
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
